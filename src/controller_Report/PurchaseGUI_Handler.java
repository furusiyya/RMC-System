package controller_Report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import view_Report.Purchase_GUI;

public class PurchaseGUI_Handler implements ActionListener{

	Purchase_GUI gui;
	public PurchaseGUI_Handler(Purchase_GUI purchase_GUI) {
		// TODO Auto-generated constructor stub
		gui = purchase_GUI;
//		getInput();
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == gui.jBox){
			gui.totalPurchase.setText(null);
			gui.model.setRowCount(0);
			load();
		}
		
	}
	public void load(){
		int i = 0;
		int j = gui.jBox.getSelectedIndex();
		ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.SUPPLY "
				+ "WHERE SUPPDATE = '"+gui.sql[j]+"'");
		double total = 0;
		try {
			while(rs.next()){
			i++;
			Object[] obj = {i,rs.getString("DESCRIPTION"),rs.getString("TYPE"),
					rs.getInt("QUANTITY"),rs.getString("COLOR"),rs.getDouble("Price")};
			gui.model.addRow(obj);
			total+= (rs.getInt("QUANTITY")*rs.getDouble("PRICE"));
			}
			gui.totalPurchase.setText("Total Purchase: "+total);	
		} catch (SQLException e) {
			System.out.println("Exception in loading retrieving supply");
			JOptionPane.showMessageDialog(null, "Please contact service provider");
		}
	}
	public void getInput(){
		try{
			PrintStream errstream = new PrintStream(new FileOutputStream("PurchaseGUI_HandlerError"+new Date()+".txt"),true);
			System.setErr(errstream);
			errstream.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{	
		}	
	}
}
