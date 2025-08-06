package controller_ProductSection;

	import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import View_ProductSection.ProductSailingGUI;
import View_ProductSection.TodayPurchase;
import model_MenuSection.DbLink;

	public class TodayPurchase_Handler implements ActionListener,KeyListener,WindowListener{

		TodayPurchase gui;ProductSailingGUI pGui;
		public TodayPurchase_Handler(TodayPurchase modifyStock,ProductSailingGUI pGui) {
			gui = modifyStock;this.pGui = pGui;
			DbLink.connect();
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Submit")){
				addToTable();
				gui.desField.requestFocusInWindow();
			}
			else if(e.getActionCommand().equals("Delete")){
				if(confirmation("Do you want to permanently delete this?","Warning")==0){
					deleteRow();
				}
			}
			
			else if(e.getActionCommand().equals("Update")){
				if(confirmation("Have you reviewed code and id's?","Confirmaton")==0){
				while(gui.table.getRowCount()!=0 && addRow() == true)addRow();
				pGui.frame.setAlwaysOnTop(true);
				gui.frmVendorRegister.setVisible(false);
				gui.frmVendorRegister.dispose();
				pGui.frame.setEnabled(true);
				pGui.frame.setAlwaysOnTop(false);
				pGui.cField.requestFocusInWindow();
				}
			}
		}
		
		int confirmation(String str,String str1){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (gui.frmVendorRegister,str ,str1,dialogButton);
				return dialogResult;
			}
		 
		int i = 0;
		private void addToTable(){
			if(!(gui.desField.getText().trim().equals(null)) && !(gui.desField.getText().trim().equals(""))
					&& !(gui.qField.getText().trim().equals(null)) && !(gui.qField.getText().trim().equals(""))){
				i++;
						Object[] str = {i,gui.desField.getText().trim(),gui.TypeField.getText().trim(),gui.colorField.getText(),
								gui.qField.getText().trim(),gui.priceField.getText().trim()};
							gui.model.addRow(str);
							gui.desField.setText(null);gui.TypeField.setText(null);
							gui.qField.setText(null);gui.colorField.setText(null);gui.priceField.setText(null);
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
					double quan = Integer.parseInt(str.get(3));
					double price = Integer.parseInt(str.get(4));
					if(DbLink.writeSale("INSERT INTO GERANIUM.SUPPLY (DESCRIPTION,TYPE,COLOR,QUANTITY,PRICE)"
											+ " VALUES ('"+str.get(0)+"','"+str.get(1)+"','"+str.get(2)+"',"+quan+","+price+")")){
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
					e.printStackTrace();
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

		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosing(WindowEvent arg0) {
			pGui.frame.setAlwaysOnTop(true);
			gui.frmVendorRegister.setVisible(false);
			gui.frmVendorRegister.dispose();
			pGui.frame.setEnabled(true);
			pGui.frame.setAlwaysOnTop(false);
			pGui.cField.requestFocusInWindow();
			
		}

		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
	}


