package controller_MenuSection;

	import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import view_MenuSection.M_ViewSpecialDeals;


	public class M_ViewSpecialDeal_Handler implements ActionListener,KeyListener{

		M_ViewSpecialDeals gui;
		public M_ViewSpecialDeal_Handler(M_ViewSpecialDeals modifyStock) {
			gui = modifyStock;
			DbLink.connect();
		}
		int price;
		String id;
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == gui.box){
				gui.price.setText(null);
				gui.model.setRowCount(0);
				int index = gui.box.getSelectedIndex();
				String str = gui.arr[index];
				int price = 0;i = 0;
				try {
					ResultSet rs = DbLink.query("Select * from GERANIUM.DEALS WHERE DEALID = '"+str+"'");
					while (rs.next()) {
						i++;
						price = rs.getInt("DEALPRICE");
								Object[] strr = {i,rs.getString("ID"),rs.getString("DESCRIPTION"),rs.getInt("QUANTITY")};
									gui.model.addRow(strr);}
					gui.price.setText("Price: "+price);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getActionCommand().equals("Delete")){
				if(confirmation("Do you want to permanently delete this?","Warning")==0){
					deleteRow();
				}
			}
			
			
		}
		
		int confirmation(String str,String str1){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null,str ,str1,dialogButton);
				return dialogResult;
			}
		 
		int i = 0;
	

		void deleteRow(){
			int row = gui.box.getSelectedIndex();
			String str = gui.arr[row];
			try {
				DbLink.writeSale("DELETE FROM GERANIUM.DEALS WHERE DEALID = '"+str+"'");
				gui.model.setRowCount(0);i = 0;gui.price.setText(null);gui.box.remove(row);
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


