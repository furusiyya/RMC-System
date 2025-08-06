package controller_ProductSection;


import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import View_ProductSection.ProductSailingGUI;
import View_ProductSection.TakeBackGUI;

public class TakeBack_Handler implements ActionListener,KeyListener,WindowListener{
	
	TakeBackGUI gui;ProductSailingGUI pGui;
	public TakeBack_Handler(TakeBackGUI gui,ProductSailingGUI pGui) {
		this.gui = gui;this.pGui = pGui;
//		getInput();
	}
	int i = 0;
	int sheet = 0;
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == gui.btnReport){
				if(writeTakeBack()){
						gui.model.setRowCount(0);
						gui.btnReport.setEnabled(false);
						i = 0;
						gui.invoiceDate.setText("");
						pGui.frame.setAlwaysOnTop(true);
						gui.model.setRowCount(0);
						gui.frame.setVisible(false);
						gui.frame.dispose();
						pGui.frame.setAlwaysOnTop(false);
						pGui.frame.setEnabled(true);
						pGui.cField.requestFocusInWindow();
					}
			}
		
	}
	
	String register = null;
	int netTotal=0;
	boolean addToTable(int invoiceCode){
		try {
			netTotal = 0;
			double total = 0;
			int dis = 0;
			ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.NETSALE natural join geranium.menu WHERE CODE = "+invoiceCode+"");
			boolean flage = false;
			while(rs.next()){
					i++;
					if(i==1){
					dis = rs.getInt("DISCOUNT");
					gui.discountLabel.setText(""+dis);
					gui.customerId_label.setText(""+rs.getString("CUSTOMER_ID"));
					gui.invoiceDate.setText(rs.getString("INVOICEDATE"));
					flage = true;
					}
					Object[] obj = {i,rs.getString("DESCRIPTION"),rs.getInt("QUANTITY"),rs.getDouble("PRICE"),rs.getString("ID")};
					double temp = rs.getDouble("PRICE")*rs.getInt("QUANTITY");
					total+= temp;
					gui.model.addRow(obj);
				}
			if(flage == false){
			gui.discountLabel.setText("NA");
			gui.customerId_label.setText("NA");}
			if(gui.discountLabel.getText().trim().equals("0"))gui.discountLabel.setText("NA");
			rs = DbLink.query("SELECT * FROM GERANIUM.NETSALE WHERE CODE = "+invoiceCode+" and ID like 'd%'");
			String str = "";
			while(rs.next()){
				str+=rs.getString("ID")+",";
				str+=rs.getString("QUANTITY")+",";
				dis = (int)rs.getDouble("DISCOUNT");
			}
			if(dis>0)gui.discountLabel.setText(""+dis);
			String[] dealId = str.split(",");
			for(int j = 0 ; j < dealId.length; j+=2){
				rs = DbLink.query("SELECT DISTINCT DESCRIPTION,DEALPRICE,DEALID FROM GERANIUM.DEALS WHERE dealID = '"+dealId[j]+"'");
				while(rs.next()){
					i++;
					Object[] obj = {i,rs.getString("DESCRIPTION"),dealId[j+1],rs.getDouble("DEALPRICE"),rs.getString("DEALID")};
					double temp = rs.getDouble("DEALPRICE")*Double.parseDouble(dealId[j+1]);
					total+= temp;
					gui.model.addRow(obj);
				}
			}
			gui.grossTotal.setText(""+(int)(total));
			gui.netTotal.setText(""+(netTotal = (int)total-dis));
				
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("TakeBack handler: Exception in add to table");
			JOptionPane.showMessageDialog(null,"Contact service provider!");
			return false;
		}
	}
	
	boolean writeTakeBack(){
		try {
			if(Integer.parseInt(gui.lossField.getText())>netTotal){
				JOptionPane.showMessageDialog(gui.frame, "Amount of loss is greater than Nettotal!");
				return false;}
				if(DbLink.writeSale("INSERT INTO GERANIUM.FAILED  (CODE,DESCRIPTION,LOSS)"
					+" values ('"+gui.invoiceCode.getText()+"','"+gui.disField.getText()
					+"',"+gui.lossField.getText()+")")){
						return true;}
		else{
			gui.lossField.setBackground(Color.gray);
			gui.disField.setBackground(Color.gray);
			return false;
		}	
		}catch (Exception e) {
							e.printStackTrace();
							return  false;
				}
		}
	
	int invoiceCode = 0;
	boolean checkFields(){
		try{
		invoiceCode = Integer.parseInt(gui.invoiceCode.getText());
		if(invoiceCode != 0){
			return true;
		}else{
			Toolkit.getDefaultToolkit().beep();
		}
		}catch(NumberFormatException e){
			return false;
		}
		return false;
	}

	
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getSource() == gui.invoiceCode){
				gui.model.setRowCount(0);
				gui.btnReport.setEnabled(false);
				i = 0;
				gui.invoiceDate.setText("");
				if(checkFields()){
				if((DbLink.searchInvoice(invoiceCode))!=0){
						System.out.println("Adding");
						if(addToTable(invoiceCode)){
							register = "NETSALE";
							gui.btnReport.setEnabled(true);
					}
				
				}else{
					gui.model.setRowCount(0);
				}
			}
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void getInput(){
		try{
			PrintStream errstream = new PrintStream(new FileOutputStream("RefundInvoice_Error"+new Date()+".txt"),true);
			System.setErr(errstream);
			errstream.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{	
		}	
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		pGui.frame.setEnabled(true);
		pGui.frame.setAlwaysOnTop(true);
        e.getWindow().dispose();
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
