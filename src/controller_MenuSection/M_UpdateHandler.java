package controller_MenuSection;

	import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import view_MenuSection.M_UpdateMenu;


	public class M_UpdateHandler implements ActionListener,KeyListener{

		M_UpdateMenu gui;
		public M_UpdateHandler(M_UpdateMenu modifyStock) {
			gui = modifyStock;
			DbLink.connect();
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Submit")){
				addToTable();
				gui.idField.requestFocusInWindow();
			}
			else if(e.getActionCommand().equals("Delete")){
				if(confirmation("Do you want to permanently delete this?","Warning")==0){
					deleteRow();
				}
			}
			
			else if(e.getActionCommand().equals("Update")){
				if(confirmation("Have you reviewed  codes and id's??","Confirmaton")==0){
				while(gui.table.getRowCount()!=0 && addRow() == true)addRow();gui.idField.requestFocusInWindow();
				}
			}
		}
		
		int confirmation(String str,String str1){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null,str ,str1,dialogButton);
				return dialogResult;
			}
		 
		int i = 0;
		private void addToTable(){
			if(!(gui.idField.getText().trim().equals(null)) && !(gui.idField.getText().trim().equals(""))
					&& !(gui.nameField.getText().trim().equals(null)) && !(gui.nameField.getText().trim().equals(""))
					&& !(gui.sPriceField.getText().trim().equals(null)) && !(gui.sPriceField.getText().trim().equals(""))){
				i++;
						Object[] str = {i,gui.idField.getText().trim(),gui.nameField.getText().trim(),gui.sPriceField.getText().trim()};
							gui.model.addRow(str);
							gui.idField.setText(null);gui.nameField.setText(null);
							gui.sPriceField.setText(null);
			}
				
			else{
				Toolkit.getDefaultToolkit().beep();
			}
		}
		

		@SuppressWarnings("unused")
		boolean addRow(){
			for(int i = 0 ; i < gui.table.getRowCount(); i++){
			ArrayList<String> str = new ArrayList<String>(0);
			for(int j = 1; j < gui.model.getColumnCount() ; j++){
					str.add(gui.table.getValueAt(i, j).toString().trim());
				}
			try {
					double sPrice = Double.parseDouble(str.get(2));
					if(DbLink.insert(str.get(0),str.get(1),sPrice)){
						gui.model.removeRow(i);
						gui.table.setSelectionBackground(Color.blue);
						return true;
					}
					else{
						Toolkit.getDefaultToolkit().beep();
						gui.table.setRowSelectionInterval(i,i);
						gui.table.setSelectionBackground(Color.RED);
						return false;
					}
				}
				catch(NumberFormatException e){
					gui.table.setRowSelectionInterval(i,i);
					gui.table.setSelectionBackground(Color.RED);
					JOptionPane.showMessageDialog(null, "Product information invalid");
					Toolkit.getDefaultToolkit().beep();
					gui.table.setSelectionBackground(Color.red);
					return false;}
			}
			return false;
		}

		void deleteRow(){
			int row = gui.table.getSelectedRow();
			try {
				gui.model.removeRow(row);
			} catch (ArrayIndexOutOfBoundsException e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Select product first");
			}
		}
		
		public void keyPressed(KeyEvent e) {
			
		}
		
		public void keyReleased(KeyEvent e) {
			gui.table.setSelectionBackground(Color.blue);
		}
		
		public void keyTyped(KeyEvent arg0) {
			
		}

		
	}


