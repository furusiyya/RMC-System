package controller_Report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JOptionPane;




import model_MenuSection.DbLink;
import view_Report.SaleByCustomerGUI;

public class SaleByCustomer_Handler implements ActionListener,MouseListener{

	SaleByCustomerGUI gui;
	public SaleByCustomer_Handler(SaleByCustomerGUI reportSaleGUI) {
		// TODO Auto-generated constructor stub
		this.gui = reportSaleGUI;
		DbLink.connect();
//		getInput();
	}
	String sheet = null;
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == gui.dField){
			gui.model.setRowCount(0);
			int row = gui.ctable.getSelectedRow();
			try {
				searchByDate(""+gui.customerModel.getValueAt(row, 1));
			} catch (ArrayIndexOutOfBoundsException e1) {
				
			}
		}
	}
	
	void clear(){
		gui.totalNetSale.setText("0.0");
	}
	
	ArrayList<Integer> netCodes = null;
	boolean search(String customer_Id){
		try {
			netCodes = new ArrayList<Integer>(0);
			ResultSet rs = DbLink.query("SELECT DISTINCT CODE FROM GERANIUM.NETSALE WHERE CUSTOMER_ID = '"+customer_Id+"'");
			while(rs.next()){
				netCodes.add(rs.getInt("CODE"));
			}
		Collections.sort(netCodes);
		totalCounter();
		return true;
		} catch (SQLException e) {
			System.out.println("Exception in searching record in search()");
			JOptionPane.showMessageDialog(null, "Please contact service provider");
			return false;
		}
	}
	
	boolean searchByDate(String customer_Id){
		try {
			netCodes = new ArrayList<Integer>(0);
			ResultSet rs = DbLink.query("SELECT DISTINCT CODE FROM GERANIUM.NETSALE WHERE CUSTOMER_ID = '"+customer_Id+"'"
					+ " AND INVOICEDATE = '"+new java.sql.Date(gui.dField.getDate().getTime())+"'");
			while(rs.next()){
				netCodes.add(rs.getInt("CODE"));
			}
		Collections.sort(netCodes);
		totalCounter();
		return true;
		} catch (SQLException e) {
			System.out.println("Exception in searching record in search()");
			JOptionPane.showMessageDialog(null, "Please contact service provider");
			return false;
		}
	}
	
	void totalCounter(){
		int invoicesTotal =0,invoicesDiscount = 0, invoicesRefund = 0;
		for(int k = 0; k<netCodes.size() ; k++){
		try {
			double total = 0;
			int dis = 0;
			ResultSet rs = DbLink.query("SELECT PRICE, QUANTITY, DISCOUNT FROM GERANIUM.NETSALE natural join geranium.menu WHERE"
					+ " CODE = "+netCodes.get(k)+"");
			while(rs.next()){
					dis = rs.getInt("DISCOUNT");
					double temp = rs.getDouble("PRICE")*rs.getInt("QUANTITY");
					total+= temp;
				}
			rs = DbLink.query("SELECT * FROM GERANIUM.NETSALE WHERE "
							+ " CODE = "+netCodes.get(k)+" and ID like 'd%'");
			String str = "";
			while(rs.next()){
				str+=rs.getString("ID")+",";
				str+=rs.getString("QUANTITY")+",";
				dis = rs.getInt("DISCOUNT");
			}
			String[] dealId = str.split(",");
			for(int j = 0 ; j < dealId.length; j+=2){
				rs = DbLink.query("SELECT DISTINCT DEALPRICE FROM GERANIUM.DEALS WHERE dealID = '"+dealId[j]+"'");
				while(rs.next()){
					double temp = rs.getDouble("DEALPRICE")*Double.parseDouble(dealId[j+1]);
					total+= temp;
				}
			}
			rs = DbLink.query("SELECT * from GERANIUM.FAILED WHERE CODE = "+netCodes.get(k)+"");
			int refund = 0;
			while(rs.next()){
				refund += rs.getInt("LOSS");
			}
			invoicesTotal +=(int)(total-dis);
			invoicesDiscount += dis;
			invoicesRefund += refund;
			Object[] obj = {k,netCodes.get(k),(total-dis),dis,refund};
			gui.model.addRow(obj);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Please contact service provider!");
			e.printStackTrace();
			}
		
		}
		gui.totalNetSale.setText("Purchase: "+invoicesTotal);
		gui.refundLabel.setText("Refund: "+invoicesRefund);
		gui.discountLabel.setText("Discount: "+invoicesDiscount);
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

	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2 && e.getSource().equals(gui.ctable)){
			gui.model.setRowCount(0);
			int row = gui.ctable.getSelectedRow();
			search(""+gui.customerModel.getValueAt(row, 1));
		}
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}