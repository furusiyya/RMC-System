package controller_ProductSection;


import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model_MenuSection.DbLink;
import View_ProductSection.cashPanelGUI;
import model_MainSailRecordFililngSection.AdressFillingSection;
import model_MainSailRecordFililngSection.GenerateInvoice;

public class cashPanelGUI_Handler implements ActionListener,KeyListener,WindowListener,ItemListener,MouseListener{

	SailingGUI_Handler pGui;
	cashPanelGUI gui;
	double total = 0,remaining = 0, given = 0, change = 0;
	double discount = 0;
	public cashPanelGUI_Handler(cashPanelGUI gui, SailingGUI_Handler pGui) {
		this.gui = gui;
		this.pGui = pGui;
		total = parse(pGui.gui.totalField.getText());
	}

	public void actionPerformed(ActionEvent e) {
			
	
	if(e.getSource().equals(gui.cancelBtn)){
		pGui.gui.frame.setEnabled(true);
		pGui.gui.frame.setAlwaysOnTop(true);
        gui.frame.dispose();
        pGui.gui.frame.setAlwaysOnTop(false);
        pGui.gui.cField.requestFocusInWindow();
	}
	
	else if(e.getActionCommand() == "Rs. 50"){
			given = given + 50.0;
			setGiven();
	}
	
	else if(e.getActionCommand() == "Rs. 100"){
		given+=100;
		setGiven();
	}
	
	else if(e.getActionCommand() == "Rs. 200"){
		given+=200;	
		setGiven();
	}
	
	else if(e.getActionCommand() == "Rs. 500"){
		given+=500;	
		setGiven();
	}
	
	else if(e.getActionCommand() == "Rs. 1000"){
		given+=1000;	
		setGiven();
	}
	
	else if(e.getActionCommand() == "Rs. 2000"){
		given+=2000;	
		setGiven();	
	}
	
	else if(e.getActionCommand() == "Rs. 5000"){
		given+=5000;	
		setGiven();
	}
	
	else if(e.getActionCommand() == "C"){
		given = 0;
		gui.cashField.setText(null);
		gui.balanceField.setText(null);
		gui.okBtn.setEnabled(false);
		gui.invoiceBtn.setEnabled(false);
		}
	
	else if(e.getSource().equals(gui.deliveryBtn)){
		try {
			  gui.deliveryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		boolean flag = gui.deliveryBtn.isSelected();
		if(flag){
			retAddress();
		}else{
			gui.addressArea.setText(null);
			gui.cCField.requestFocusInWindow();
		}
		} finally {
			  gui.deliveryBtn.setCursor(Cursor.getDefaultCursor());
			}
	}
	
	else if(e.getSource().equals(gui.okBtn)){
		try {
			  gui.okBtn.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			addToSaleRegisters();
			pGui.gui.frame.setEnabled(true);
			pGui.gui.frame.setAlwaysOnTop(true);
	        gui.frame.dispose();
	        pGui.gui.frame.setAlwaysOnTop(false);
	        pGui.clearButtonFun();
	        pGui.gui.invoiceNo.setText("Invoice # NZ-0"+(invoiceCode+1));
	        pGui.gui.cField.requestFocusInWindow();
		} finally {
			  gui.okBtn.setCursor(Cursor.getDefaultCursor());
			}
		}
	
	
	else if(e.getSource().equals(gui.invoiceBtn)){
		try {
			  gui.invoiceBtn.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//		System.out.println(invoice());
		GenerateInvoice.printInvoice(invoice());
		} finally {
			  gui.invoiceBtn.setCursor(Cursor.getDefaultCursor());
			}
	}
	
//	else if(e.getSource().equals(gui.invoiceBtn)){
//		Printsupport ps=new Printsupport();
//		 Object printitem [][]=ps.getTableData(pGui.gui.table);
//		 ps.setItems(printitem);
//		       
//		 PrinterJob pj = PrinterJob.getPrinterJob();
//		 pj.setPrintable(null,ps.getPageFormat(pj));
//		       try {
//		            pj.print();
//		           
//		            }
//		        catch (PrinterException ex) {
//		                ex.printStackTrace();
//		            }
//	}
	
	}
	
	double parse(String value){
		return Double.parseDouble(value);
	}
	
	void remaining(){
		gui.remainingField.setText((total-discount)+"");
	}
	
	void setGiven(){
		gui.cashField.setText(""+given);
		acceptCash();
	}
	
	void applyDiscount(int index){
		discount =  ( total * index ) / 100;
		discount = Math.floor(discount);
		remaining();
		acceptCash();
	}
	
	void acceptCash(){
		double temp = 0;
		if(!((temp = Double.parseDouble(gui.cashField.getText()))<(total-discount) )){
			gui.okBtn.setEnabled(true);gui.invoiceBtn.setEnabled(true);
			gui.balanceField.setText((temp-(total-discount))+"");
		}else{
			gui.okBtn.setEnabled(false);
			gui.invoiceBtn.setEnabled(false);
			gui.balanceField.setText(null);
		}
	}
	
	void retAddress(){
		if(gui.cCField.getText().length()!=4 && gui.cCField.getText().length()!=3){
			gui.cCField.setText(null);
			gui.addressArea.setText(null);
			gui.deliveryBtn.setSelected(false);
			gui.cCField.requestFocusInWindow();
		}else{
			
		if(gui.cPField.getText().length()!=7){
				gui.cPField.setText(null);		
				gui.addressArea.setText(null);
				gui.deliveryBtn.setSelected(false);
				gui.cPField.requestFocusInWindow();
			}else{
				try{
					Integer.parseInt(gui.cCField.getText());
					Long.parseLong(gui.cPField.getText());
					try {
						ResultSet rs = DbLink.retrieveAddress(Long.parseLong(gui.cCField.getText()+gui.cPField.getText()));
						String str = "";
						while(rs.next()){
							name=""+rs.getString("NAME");
							str+="Name: "+name;
							address =""+rs.getString("Address");
							str+="\nAddress: "+address;
						}
						gui.addressArea.setText(str);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}catch(NumberFormatException e){
					e.printStackTrace();
					gui.cPField.setText(null);
					gui.cCField.setText(null);
					gui.addressArea.setText(null);
					gui.deliveryBtn.setSelected(false);
					gui.cCField.requestFocusInWindow();
				}
			}
		}
				
	}

	
	
	int invoiceCode = 0;
	boolean addToSaleRegisters(){
//		tableTotalCounter();
		boolean success = true;
		String temp = "Self";
		if(gui.deliveryBtn.isSelected())temp = "delivery";
		invoiceCode = DbLink.invoiceCode()+1;
		int quantity = 0;
		try {
			int index = 0;
			for(int i = 0 ; i < pGui.gui.table.getRowCount() ; i++){
				try {
					quantity = Integer.parseInt(""+pGui.gui.table.getValueAt(i,2));
					long pId = Long.parseLong(gui.cCField.getText()+gui.cPField.getText());
					
						if(DbLink.saleRegister(invoiceCode, pGui.codes.get(index),quantity,temp,discount
								,pId ) ){
							success =  true;
							index++;
								}
								else{
									System.out.println("Exception");
								}
				} catch (NumberFormatException e) {
					System.out.print(invoiceCode+" "+pGui.codes.get(index)+" "+quantity+" "+temp+" "+discount+" "+0);
					DbLink.saleRegister(invoiceCode, pGui.codes.get(index),quantity,temp,discount,0);
					index++;
				}
				
			}
			return success;
		}catch (Exception e) {
			e.printStackTrace();
				success = false;
				return success;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(gui.cashField)){
			try {
				Double.parseDouble(gui.cashField.getText());	
				acceptCash();
			} catch (NumberFormatException e1) {
				gui.cashField.setText(null);
			}
		}
		else if(e.getSource().equals(gui.cPField)){
//			gui.cPField.setText(gui.cPField.getText().replaceAll( "[^\\d]", "" ));
			gui.deliveryBtn.setSelected(false);
			gui.addressArea.setText(null);
			name = address = null;
			if(gui.cPField.getText().length()==7)gui.deliveryBtn.requestFocusInWindow();
		}
		else if(e.getSource().equals(gui.cCField)){
			gui.deliveryBtn.setSelected(false);
			gui.addressArea.setText(null);
			name = address = null;
			if(gui.cCField.getText().length()>2){
				if(gui.cCField.getText().charAt(1)=='3' && gui.cCField.getText().length()==4){
					gui.cPField.requestFocusInWindow();
				}
					else if(gui.cCField.getText().charAt(1)!='3' && gui.cCField.getText().length()==3){
						gui.cPField.requestFocusInWindow();
					}
			}
		}
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		pGui.gui.frame.setEnabled(true);
		pGui.gui.frame.setAlwaysOnTop(true);
        e.getWindow().dispose();
        pGui.gui.frame.setAlwaysOnTop(false);
        pGui.gui.cField.requestFocusInWindow();
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
		
	}

	public void windowIconified(WindowEvent arg0) {
		
	}

	public void windowOpened(WindowEvent arg0) {
		
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			applyDiscount(5*gui.disField.getSelectedIndex());
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2 && e.getSource().equals(gui.addressArea)){
//			gui.frame.setEnabled(false);
//			new customerGUI(null,gui).frmPersonalize.isAlwaysOnTop();
			cashPanel();
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		gui.invoiceBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.okBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.deliveryBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cfifty.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cFiveHundred.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cFiveThousand.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cHundred.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.totalField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cTwoHundred.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cTwoThousand.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.disField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cThousand.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.cTenThousand.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	String name = "";
	String address = "";
	JTextField namefield, addressfield;
	void cashPanel(){
	gui.frame.setAlwaysOnTop(true);
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Name");
		panel.add(label);
		namefield = new JTextField(6);
		namefield.setText(name);
		panel.add(namefield);
		label = new JLabel("Address");
		panel.add(label);
		addressfield = new JTextField(35);
		addressfield.setText(address);
		panel.add(addressfield);
		String[] options = new String[]{"OK", "Cancel"};
		int option = JOptionPane.showOptionDialog(gui.frame, panel, "Personalize",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
		gui.frame.setAlwaysOnTop(false);
		namefield.requestFocusInWindow();
		if(option == 0) // pressing OK button
		{
			checkAddress();
		}
	}
	
	boolean checkAddress(){
		if(namefield.getText().length()<3){
			JOptionPane.showMessageDialog(null, "Name is too short!");
			namefield.requestFocusInWindow();
			return false;
		}else{
			if(addressfield.getText().length()<10){
				JOptionPane.showMessageDialog(null, "Address is too short!");
				JOptionPane.showMessageDialog(null, "Invalid Customer Name!");
				addressfield.requestFocusInWindow();
				return false;
			}else{
				DbLink.writeSale("delete from geranium.customers where customer_Id ="+
			Long.parseLong(gui.cCField.getText()+gui.cPField.getText())+"");
				return DbLink.writeSale("INSERT INTO GERANIUM.CUSTOMERS (customer_Id,name,address) "
				+ "values ("+Long.parseLong(gui.cCField.getText()+gui.cPField.getText())+",'"+namefield.getText()+"','"+addressfield.getText()+"')");
			}
		}
	}
	
	String invoice(){
		String address = "  "+AdressFillingSection.RetrieveAddress();
		String header = (address+"\n  Invoice No: NZ-0"+(DbLink.invoiceCode()+1)+"\n  Date:   "+DateFormat.getDateTimeInstance().format(new Date())+
				"\n  .......................................\n  "
				+" Product         Price   Qty     Total\n  .......................................\n");
		String temp = null;
		int quan = 0,totalQuantity = 0;
		String des = "";
		int unitPrice = 0;
		int subTotal = 0;
		int total = 0;
		for(int i = 0 ; i < pGui.gui.table.getRowCount(); i++){
			temp = pGui.gui.table.getValueAt(i, 1).toString();
			
			try {
				totalQuantity+=quan = Integer.parseInt(pGui.gui.table.getValueAt(i, 2)+"");
				unitPrice = (int) Double.parseDouble(pGui.gui.table.getValueAt(i, 3)+"");
				total = (int)Double.parseDouble(pGui.gui.table.getValueAt(i, 4)+"");

					subTotal+=total;
					des+= ("    "+temp+"\n                 "+unitPrice+"     "+quan+"      "+total);
					des+="\n  ";
				
			} catch (NumberFormatException e1) {
				String quanstr = pGui.gui.table.getValueAt(i, 2)+"";
				String unitPricestr = pGui.gui.table.getValueAt(i, 3)+"";
				String totalstr =pGui.gui.table.getValueAt(i, 4)+"";
				subTotal+=total;
				System.out.println("Eroooooooooooooooor\nErrrrrrrrrrrrrrrrro");
				des+= ("  "+temp+"\n                 "+unitPricestr+"     "+quanstr+"      "+totalstr);des+="\n";
			}
			
		}
		subTotal = (int) pGui.tableTotalCounter();
		des+=("\n  .......................................\n"
				+"          Gross Total:   "+totalQuantity+"      "+subTotal);
		des+=("\n  .......................................\n        Discount: 	  	"+(int)discount+
				"\n        Net total: 	 "+(int)(subTotal - discount)+"\n        Cash: 	  	    "+(int)given+"\n        "
						+ "Balance:    "+(int)(given-subTotal)+"\n\n   \n\n  \n\n\n\n   \n\n");
		String invoice = header+des;
		return invoice;}
	
}
