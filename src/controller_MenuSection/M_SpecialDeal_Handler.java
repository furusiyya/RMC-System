package controller_MenuSection;

	import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import view_MenuSection.M_SpecialDeals;


	public class M_SpecialDeal_Handler implements ActionListener,KeyListener,FocusListener{

		M_SpecialDeals gui;
		public M_SpecialDeal_Handler(M_SpecialDeals modifyStock) {
			gui = modifyStock;
			DbLink.connect();
		}
		int price;
		String id;
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == gui.box){
				gui.idField.setBackground(Color.white);
				
				int index = gui.box.getSelectedIndex();
				String str = gui.arr[index];
				try {
					boolean flag = true;
					ResultSet rs = DbLink.query("Select * from GERANIUM.MENU WHERE ID = '"+str+"'");
					while (rs.next()) {
						i++;
						String id = rs.getString("ID");
						for(int i = 0 ; i < gui.table.getRowCount() ; i++){
						if(gui.table.getValueAt(i, 1).equals(id))flag = false;}
						
									if(flag){
										Object[] strr = {i,id,rs.getString("DESCRIPTION"),1,rs.getInt("PRICE")};
									gui.model.addRow(strr);}
									else{
										JOptionPane.showMessageDialog(null, "Item is already in table!");
									}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getActionCommand().equals("Delete")){
				if(confirmation("Do you want to permanently delete this?","Warning")==0){
					deleteRow();
				}
			}
			
			else if(e.getActionCommand().equals("Update")){
					int price;
					try {
						price = Integer.parseInt(gui.priceField.getText().trim());
						if(id.length() > 0){
						if(confirmation("Have you reviewed  codes and id's?","Confirmaton")==0){
							while(gui.table.getRowCount()!=0 && addRow(id,gui.desField.getText(),price) == true)addRow(id,gui.desField.getText(),price);
							gui.idField.setText(null);
							gui.desField.setText(null);
							gui.priceField.setText(null);
							gui.idField.requestFocusInWindow();
							
						}}else{
							gui.idField.requestFocusInWindow();
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();gui.priceField.requestFocusInWindow();
					}
					
			}
		}
		
		int confirmation(String str,String str1){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null,str ,str1,dialogButton);
				return dialogResult;
			}
		 
		int i = 0;
		

		@SuppressWarnings("unused")
		boolean addRow(String id,String des,int price){
			for(int i = 0 ; i < gui.table.getRowCount(); i++){
			ArrayList<String> str = new ArrayList<String>(0);
			for(int j = 1; j < gui.model.getColumnCount() ; j++){
					str.add(gui.table.getValueAt(i, j).toString().trim());
				}
			try {
				
					double quan = Double.parseDouble(str.get(2));
					int unitPrice = Integer.parseInt(str.get(3));
					DbLink.writeSale("INSERT INTO GERANIUM.DEALS(DEALID,ID,DESCRIPTION,QUANTITY,DEALPRICE) VALUES "
																+ "('"+id+"','"+str.get(0)+"','"+des+"',"+quan+","+price+")");
						gui.model.removeRow(i);
						gui.table.setSelectionBackground(Color.blue);
						return true;
					
				}catch(NumberFormatException e){
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

		public void focusGained(FocusEvent e) {
		}

		public void focusLost(FocusEvent e) {
			if(e.getSource() == gui.idField){
				id = null;
				i = 0;
				id = gui.idField.getText().trim();
				try {
					ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.MENU WHERE ID = '"+id+"'");
					while(rs.next() && i < 1){
						i++;
					}
					rs = DbLink.query("SELECT * FROM GERANIUM.DEALS WHERE DEALID = '"+id+"'");
					while(rs.next() && i < 1){
						i++;
					}
					if(i > 0){
						gui.idField.setText(null);
						gui.idField.requestFocusInWindow();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}

		
	}


