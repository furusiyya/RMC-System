package controller_MenuSection;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import view_MenuSection.ModifyMenu;


public class ModifyGUI_Handler implements ActionListener,KeyListener{

	ModifyMenu gui;
	int fileIndex = 1;
	public ModifyGUI_Handler(ModifyMenu modifyStock) {
		gui = modifyStock;
		DbLink.connect();
//		getInput();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("SAVE")){
			if(confirmation("Do you want to save changes?","Confirmation")==0)updateTablede();
			
		}
		else if(e.getActionCommand().equals("DELETE")){
			if(confirmation("Do you want to permanently delete this?","Warning")==0)deleteRow();
		}
	}
	
	int confirmation(String str,String str1){
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, str,str1,dialogButton);
		return dialogResult;
	}	
	
	
	
	void searProduct(String code) {
		if(!(code.equals("") && code.equals(null))){
			try {
				gui.model.setRowCount(0);
				int i = 0;
				ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.MENU WHERE ID LIKE '"+code+"%' ORDER BY ID");
				while(rs.next()){
					i++;
					Object[] obj = {i,rs.getString("ID"),rs.getString("DESCRIPTION"),rs.getString("PRICE")};
					gui.model.addRow(obj);
				}
			} catch (SQLException e) {
				System.out.println("Exception in recieving data from RS");
				JOptionPane.showMessageDialog(null, "Please contact with service provider!");
			}
			}
		else{
			System.out.println("Incomplete Info");
		}
	}
	
	
	void updateTablede(){
		ArrayList<Object> str = new ArrayList<Object>(0);
		int row = gui.table.getSelectedRow();
		try {
			str.add(gui.table.getValueAt(row, 1));
			str.add(gui.table.getValueAt(row, 2));
			str.add(gui.table.getValueAt(row, 3));
			try{
					double sPrice = Double.parseDouble(""+str.get(2));
					gui.table.setSelectionBackground(Color.blue);
					if(DbLink.updateExisted(str.get(0).toString(),str.get(1).toString().trim(), sPrice)){
							gui.table.setSelectionBackground(Color.blue);
					}
					else{
							Toolkit.getDefaultToolkit().beep();
							gui.table.setSelectionBackground(Color.RED);
						}
				}
				catch(NumberFormatException e){
					Toolkit.getDefaultToolkit().beep();
					gui.table.setSelectionBackground(Color.RED);
				}
				
		} catch (ArrayIndexOutOfBoundsException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Select product fist!");
		}
	}
	
	void deleteRow(){
		int row = gui.table.getSelectedRow();
		try {
			String str = ""+gui.table.getValueAt(row, 1);
				if(DbLink.deleteROw(str)){
					gui.model.removeRow(row);
					gui.table.setSelectionBackground(Color.blue);
				}
				else{
					Toolkit.getDefaultToolkit().beep();
					gui.table.setSelectionBackground(Color.RED);
				}
		} catch (ArrayIndexOutOfBoundsException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Select product fist!");
		}
	}
	
	public void keyPressed(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) {
		gui.table.setSelectionBackground(Color.blue);
		searProduct(gui.idField.getText().trim());
	}
	
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	
	public void getInput(){
		try{
			PrintStream errstream = new PrintStream(new FileOutputStream("ModifyGUI_HandlerError"+new Date()+".txt"),true);
			System.setErr(errstream);
			errstream.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{	
		}	
	}
}
