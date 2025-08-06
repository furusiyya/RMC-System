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
import view_Report.SaleReportGUI;

public class SaleReport_Handler implements ActionListener{

	SaleReportGUI gui;
	public SaleReport_Handler(SaleReportGUI reportSaleGUI) {
		// TODO Auto-generated constructor stub
		this.gui = reportSaleGUI;
		DbLink.connect();
//		getInput();
	}
	String sheet = null;
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == gui.dField){
			clear();
			if(gui.dField.getDate() != null){
				totalCounter();
				add();
			}
		}
	}
	
	void clear(){
		gui.totalNetSale.setText("0.0");
	}
	
	public void add(){
		try{
			gui.model.setRowCount(0);
			ResultSet rs = DbLink.query("SELECT s.ID,m.DESCRIPTION,SUM(s.QUANTITY) AS QUANTITY"
							+ " FROM GERANIUM.NETSALE s natural join GERANIUM.MENU m WHERE s.INVOICEDATE = '"
													+new java.sql.Date(gui.dField.getDate().getTime())+"' GROUP BY(s.ID) "
															+ "ORDER BY QUANTITY DESC");
			while(rs.next()){
				Object[] obj = {rs.getString("ID"),rs.getString("DESCRIPTION"),rs.getInt("QUANTITY")};
				gui.model.addRow(obj);
			}
			
			rs = DbLink.query("SELECT ID,SUM(QUANTITY) AS QUANTITY  FROM GERANIUM.NETSALE WHERE INVOICEDATE = '"
													+new java.sql.Date(gui.dField.getDate().getTime())+
													"'  and ID like 'd%' GROUP BY(ID)  order by QUANTITY DESC");
			String str = "";
			while(rs.next()){
				str+=rs.getString("ID")+",";
				str+=rs.getString("QUANTITY")+",";
			}
			String[] dealId = str.split(",");
			for(int j = 0 ; j < dealId.length; j+=2){
				rs = DbLink.query("SELECT DISTINCT DESCRIPTION FROM GERANIUM.DEALS WHERE dealID = '"+dealId[j]+"'");
				while(rs.next()){
					Object[] obj = {dealId[j],rs.getString("DESCRIPTION"),dealId[j+1]};
					gui.model.addRow(obj);
				}
			}
	
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Exception in retreiving sale report");
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
		}
		
	}
	
	void totalCounter(){
		try {
			double total = 0;
			int cash = 0;
			ResultSet rs = DbLink.query("SELECT sum((quantity*price))  as total FROM"
					+ " GERANIUM.NETSALE natural join geranium.MENU where invoicedate   = '"
													+new java.sql.Date(gui.dField.getDate().getTime())+"'");
			while(rs.next()){
				total+=rs.getDouble("TOTAL");
			}
			
			rs = DbLink.query("SELECT * FROM GERANIUM.NETSALE WHERE INVOICEDATE = '"
					+new java.sql.Date(gui.dField.getDate().getTime())+"' AND ID like 'd%'");
			String str = "";
			while(rs.next()){
				str+=rs.getString("ID")+",";
				str+=rs.getString("QUANTITY")+",";
			}
			String[] dealId = str.split(",");
			for(int j = 0 ; j < dealId.length; j+=2){
				rs = DbLink.query("SELECT DISTINCT DEALPRICE FROM GERANIUM.DEALS WHERE dealID = '"+dealId[j]+"'");
				while(rs.next()){
					double temp = rs.getDouble("DEALPRICE")*Double.parseDouble(dealId[j+1]);
					total+= temp;
				}
			}
			int discount = 0;
			rs = DbLink.query("SELECT DISCOUNT FROM GERANIUM.NETSALE  WHERE INVOICEDATE = '"
					+new java.sql.Date(gui.dField.getDate().getTime())+"' AND DISCOUNT != 0 GROUP BY(CODE)");
			while(rs.next())discount+=rs.getInt("DISCOUNT");
			
			rs = DbLink.query("SELECT * from GERANIUM.FAILED WHERE INVOICEDATE = '"
					+new java.sql.Date(gui.dField.getDate().getTime())+"'");
			int refund = 0;
			while(rs.next()){
				refund += rs.getInt("LOSS");
			}
			total = total-discount-refund;
			gui.totalNetSale.setText("Closing Cash: "+(int)(total+cash));
			gui.discountLabel.setText("Discount Cash: "+discount); 
			gui.refundLabel.setText("Refund Cash: "+refund);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void getInput(){
		try{
			PrintStream errstream = new PrintStream(new FileOutputStream("SaleReport_HandelrError"+new Date()+".txt"),true);
			System.setErr(errstream);
			errstream.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{	
		}	
	}
	
}