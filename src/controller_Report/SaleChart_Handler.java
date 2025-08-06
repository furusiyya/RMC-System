package controller_Report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import view_Report.SaleChartGUI;

public class SaleChart_Handler implements ActionListener{

	SaleChartGUI gui;
	public SaleChart_Handler(SaleChartGUI reportSaleGUI) {
		this.gui = reportSaleGUI;
		DbLink.connect();
//		getInput();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == gui.dField){
			if(gui.dField.getDate() != null){
				clear();
				if(search()){
						if((netCodes.size())>0){
							gui.totalInvoice.setText("Of  "+netCodes.size());
							addToTable();
						}
					}
			}
		}
		if(e.getSource() == gui.nextInvoice){
			currentIndex++;
			gui.model.setRowCount(0);
			gui.customerName.setText("");
			addToTable();
		}
		if(e.getSource() == gui.backBtn){
			currentIndex--;
			gui.customerName.setText("");
			gui.model.setRowCount(0);
			addToTable();
			}
		}
	
	void clear(){
		gui.netTotal.setText("0.0");
		gui.nextInvoice.setEnabled(false);
		gui.backBtn.setEnabled(false);
		gui.totalAmount.setText(null);
		netCodes = new ArrayList<Integer>(0);
		currentIndex = 0;
		gui.netTotalLabel.setText(null);
		gui.refundLabel.setText(null);
		gui.invoiceCode.setText(null);
		gui.customerName.setText(null);
		gui.customerContact.setText(null);
		gui.model.setRowCount(0);
		gui.currentInvoice.setText(null);
		gui.totalInvoice.setText(null);
	}
	
	void clearToLoad(){
		gui.netTotalLabel.setText("");
		gui.invoiceCode.setText("");
		gui.customerName.setText("");
		gui.customerContact.setText("");
		gui.model.setRowCount(0);
	}
	
	ArrayList<Integer> netCodes = null;
	boolean search(){
		try {
			netCodes = new ArrayList<Integer>(0);
			System.out.println(new java.sql.Date(gui.dField.getDate().getTime()));
			ResultSet rs = DbLink.query("SELECT DISTINCT CODE FROM GERANIUM.NETSALE WHERE INVOICEDATE = '"+new java.sql.Date(gui.dField.getDate().getTime())+"'");
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


	int currentIndex = 0;
	boolean addToTable(){
		int i = 0;
				try {
					clearToLoad();
					gui.currentInvoice.setText(""+(currentIndex+1));
					double total = 0;
					int dis = 0;
					ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.NETSALE natural join geranium.menu WHERE INVOICEDATE = '"
										+new java.sql.Date(gui.dField.getDate().getTime())+"' AND CODE = "+netCodes.get(currentIndex)+"");
					gui.invoiceCode.setText("NZ-0"+netCodes.get(currentIndex));
					while(rs.next()){
							i++;
							if(i==1){
							dis = rs.getInt("DISCOUNT");
							gui.discountLabel.setText(""+dis);
							gui.customerContact.setText(""+rs.getString("CUSTOMER_ID"));
							}
							Object[] obj = {i,rs.getString("DESCRIPTION"),rs.getInt("QUANTITY"),rs.getDouble("PRICE")};
							double temp = rs.getDouble("PRICE")*rs.getInt("QUANTITY");
							total+= temp;
							gui.model.addRow(obj);
						}

					if(gui.discountLabel.getText().equals("0"))gui.discountLabel.setText("NA");
					if(gui.customerContact.getText().equals(""))gui.discountLabel.setText("NA");
					rs = DbLink.query("SELECT * FROM GERANIUM.NETSALE WHERE INVOICEDATE = '"
							+new java.sql.Date(gui.dField.getDate().getTime())+"' AND"
									+ " CODE = "+netCodes.get(currentIndex)+" and ID like 'd%'");
					String str = "";
					while(rs.next()){
						str+=rs.getString("ID")+",";
						str+=rs.getString("QUANTITY")+",";
						dis = rs.getInt("DISCOUNT");
					}
					gui.discountLabel.setText(""+dis);
					if(gui.discountLabel.getText().equals("0"))gui.discountLabel.setText("NA");
					if(str.length()>2){
					String[] dealId = str.split(",");
					System.out.println(netCodes.get(currentIndex)+"  ========= "+dealId.length);
					for(int j = 0 ; j < dealId.length; j+=2){
						rs = DbLink.query("SELECT DISTINCT DESCRIPTION,DEALPRICE FROM GERANIUM.DEALS WHERE dealID = '"+dealId[j]+"'");
						while(rs.next()){
							i++;
							Object[] obj = {i,rs.getString("DESCRIPTION"),dealId[j+1],rs.getDouble("DEALPRICE")};
							double temp = rs.getDouble("DEALPRICE")*Double.parseDouble(dealId[j+1]);
							total+= temp;
							gui.model.addRow(obj);
						}
					}
					}
					gui.totalAmount.setText(""+(total));
					gui.netTotalLabel.setText(""+(total-dis));
					System.out.println("1");
					if(gui.customerContact.getText().length()<5){
						gui.customerName.setText("NA");
						gui.customerContact.setText("NA");			
					}else{
					rs = DbLink.query("SELECT NAME FROM GERANIUM.CUSTOMERS WHERE CUSTOMER_ID = '"+gui.customerContact.getText()+"'");
					while(rs.next()){
						gui.customerName.setText(rs.getString("Name"));
					}
					}
					rs = DbLink.query("SELECT * from GERANIUM.FAILED WHERE INVOICEDATE = '"
							+new java.sql.Date(gui.dField.getDate().getTime())+"' and CODE = "+netCodes.get(currentIndex)+"");
					int refund = 0;
					while(rs.next()){
						refund += rs.getInt("LOSS");
					}
					gui.refundLabel.setText("Refund of Invoice: "+refund);
					if(currentIndex<(netCodes.size()-1)){
						gui.nextInvoice.setEnabled(true);
					}
					else{
						gui.nextInvoice.setEnabled(false);
					}
					if(currentIndex>0){
						gui.backBtn.setEnabled(true);
					}
					else{
						gui.backBtn.setEnabled(false);
					}
					return true;
				}catch(SQLException e) {
					JOptionPane.showMessageDialog(null,"Please contact service provider!");
					e.printStackTrace();
					return false;}
		
		}
	
	
	void totalCounter(){
		double closingCash = 0;
		for(int k = 0; k<netCodes.size() ; k++){
		try {
			double total = 0;
			int dis = 0,i=0;
			ResultSet rs = DbLink.query("SELECT PRICE, QUANTITY, DISCOUNT FROM GERANIUM.NETSALE natural join geranium.menu WHERE INVOICEDATE = '"
								+new java.sql.Date(gui.dField.getDate().getTime())+"' AND CODE = "+netCodes.get(k)+"");
			while(rs.next()){
					if(i==0){
					dis = rs.getInt("DISCOUNT");i++;}
					double temp = rs.getDouble("PRICE")*rs.getInt("QUANTITY");
					total+= temp;
				}
			rs = DbLink.query("SELECT * FROM GERANIUM.NETSALE WHERE INVOICEDATE = '"
					+new java.sql.Date(gui.dField.getDate().getTime())+"' AND"
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
			closingCash+=(total-dis);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Please contact service provider!");
			e.printStackTrace();
			}
		
		}
		gui.netTotal.setText("Closing Cash of day: "+closingCash);
	}
	
	
	public void getInput(){
		try{
			PrintStream errstream = new PrintStream(new FileOutputStream("SaleChart_HandlerError"+new Date()+".txt"),true);
			System.setErr(errstream);
			errstream.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{	
		}	
	}
}
