package controller_ProductSection;


import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import view_Main.OptionPane_GUI;
import view_MenuSection.DashBoardMain;
import view_Report.ReportGUI;
import View_ProductSection.ProductSailingGUI;
import View_ProductSection.TakeBackGUI;
import View_ProductSection.TodayPurchase;
import View_ProductSection.cashPanelGUI;
import View_ProductSection.customerGUI;
import View_ProductSection.miscellaneousGUI;
import model_MenuSection.DbLink;
public class SailingGUI_Handler extends MouseAdapter implements ActionListener,KeyListener {
		
	/**Instance variables for storing Objects of used classes*/ 
	public ProductSailingGUI gui;
	cashPanelGUI cGUI = null;
	public SailingGUI_Handler(ProductSailingGUI gui) {
		this.gui = gui;
		DbLink.connect();
		
		}
	
	String table = null;
	boolean enablecash = false,invoice = false,error = false;
	int invoiceCode = 0;
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == gui.qField){
			searProduct();
			tableTotalCounter();
		}
		
		else if(e.getSource().equals(gui.enterButton)){
			try {
				  waitCursor(gui.enterButton);
				  searProduct();
				  tableTotalCounter();
				} finally {
				  gui.enterButton.setCursor(Cursor.getDefaultCursor());
				}
		}
		
		else if(e.getSource().equals(gui.deleteBtn)){
			try {
				  waitCursor(gui.deleteBtn);
				  try {
						int index = gui.table.getSelectedRow();
						codes.remove(index);
						gui.model.removeRow(index);
						tableTotalCounter();
					} catch (ArrayIndexOutOfBoundsException e1) {
						
					}catch(NullPointerException e2){
						
					}
				} finally {
				  gui.deleteBtn.setCursor(Cursor.getDefaultCursor());
				}
		}
		
		else if(e.getSource() == gui.clearButton){
			try {
				  waitCursor(gui.clearButton);
				  clearButtonFun();
			} finally {
				  gui.clearButton.setCursor(Cursor.getDefaultCursor());
				}
		}
		

		
		else if(e.getSource().equals(gui.returnBtn)){
			try {
				waitCursor(gui.returnBtn);
				System.out.print("0");
				new TakeBackGUI(gui).frame.setVisible(true);
				gui.frame.setEnabled(true);
			} finally {
				  gui.returnBtn.setCursor(Cursor.getDefaultCursor());
				}
		}

		else if(e.getSource() == gui.stockBtn){
			try {
				waitCursor(gui.stockBtn);
				if(!open){
				gui.frame.setAlwaysOnTop(true);
				JPanel panel = new JPanel();
				JLabel label = new JLabel("Enter a password:");
				JPasswordField pass = new JPasswordField(15);
				panel.add(label);
				panel.add(pass);
				String[] options = new String[]{"OK", "Cancel"};
				int option = JOptionPane.showOptionDialog(gui.frame, panel, "Admin Password",
				                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				                         null, options, options[1]);
				gui.frame.setAlwaysOnTop(false);
				pass.requestFocusInWindow();
				if(option == 0) // pressing OK button
				{
				    char[] password = pass.getPassword();
				    String str = new String(password);
//					   for(int i = 0 ; i < password.length ; i++)str+=password[i];
					   try {
						   String pas = new String();
						   ResultSet rs = DbLink.query("SELECT ADMIN FROM GERANIUM.STOCK");
						   while(rs.next()){
							pas = rs.getString("ADMIN");
						   }
						   if(getHash(str).equals(getHash(pas)))stock();
						  else{
							  JOptionPane.showMessageDialog(gui.frame, "Incorrect password!");
						  }
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				}else{
					stock();
				}
			
			} finally {
				  gui.stockBtn.setCursor(Cursor.getDefaultCursor());
				}
		}


		else if(e.getSource().equals(gui.purchaseBtn)){
			try {
				  waitCursor(gui.purchaseBtn);
				  new TodayPurchase(gui).frmVendorRegister.setVisible(true);
					gui.frame.setEnabled(true);
				} finally {
				  gui.purchaseBtn.setCursor(Cursor.getDefaultCursor());
				}	
			
		}
		
		else if(e.getSource().equals(gui.okBtn)){
			try {
				  waitCursor(gui.okBtn);
				  tableTotalCounter();
				  new cashPanelGUI(this).frame.setVisible(true);
					gui.frame.setEnabled(true);
				} finally {
				  gui.okBtn.setCursor(Cursor.getDefaultCursor());
				}	
			
	}
		
		else if(e.getSource().equals(gui.btnTodayreport)){
			try {
				  waitCursor(gui.btnTodayreport);
				  new ReportGUI(gui).frame.setVisible(true);
					gui.frame.setEnabled(true);
				} finally {
				  gui.btnTodayreport.setCursor(Cursor.getDefaultCursor());
				}	
			
	}
		
		else if(e.getSource() == gui.toolsBtn){
			try {
				waitCursor(gui.toolsBtn);
				gui.frame.setEnabled(true);
				 new OptionPane_GUI(gui);
				} finally {
				  gui.toolsBtn.setCursor(Cursor.getDefaultCursor());
				}
		}
		
		else if(e.getSource().equals(gui.customer_info)){
			try {
				waitCursor(gui.customer_info);
				new customerGUI(gui).frmPersonalize.setVisible(true);
				} finally {
				  gui.customer_info.setCursor(Cursor.getDefaultCursor());
				}
		}
		else if(e.getSource().equals(gui.btnMiscellaneous)){
			try {
				waitCursor(gui.customer_info);
				new miscellaneousGUI(gui).frmPersonalize.setVisible(true);
				} finally {
				  gui.customer_info.setCursor(Cursor.getDefaultCursor());
				}
		}
	}

	
	/**
	 * method for setting addStock GUI to frame
	 */
	boolean open = false;
	DashBoardMain main = null;
	void stock(){
		if(!open){
			main = new DashBoardMain();
			gui.panel.remove(gui.salePanel);
			gui.panel.add(main.panel);
			gui.panel.revalidate();
			gui.panel.setVisible(true);
			open = true;
		}
		else{	
			clearButtonFun();
			gui.panel.remove(main.panel);
			gui.panel.add(gui.salePanel);
			gui.panel.revalidate();
			gui.panel.setVisible(true);
			gui.panel.setVisible(false);
			gui.panel.setVisible(true);
			open = false;
			gui.cField.requestFocusInWindow();
		}
		
	}
	
	boolean checkStatus(String str){
		boolean flag = false;
		for(int i = 0 ; i < codes.size(); i++){
			if(codes.get(i).equalsIgnoreCase(str))flag  = true;
		}
		return flag;
	}
	
	boolean tableCheck = false;
	int i = 0;
	ArrayList<String> codes= new ArrayList<String>(0);
	ArrayList<String> des = new ArrayList<String>(0);
	
	public void searProduct() {
		String code = "",quan = "";
		int quantity = 0;
		if(!(code=this.gui.cField.getText()).trim().equals("")){	
			if(!checkStatus(code)){
			try {
				quan=gui.qField.getText().trim();
				try{
				quantity = Integer.parseInt(quan);}
				catch (NumberFormatException e) {
//					e.printStackTrace();
					 quantity = 1;
				}
				ResultSet rs = DbLink.searchProduct(code, quantity);
				try {
					boolean success = false;
					while(rs.next()){
						i++;success = true;
						codes.add(rs.getString("ID"));
						Object[] obj = {i,rs.getString("DESCRIPTION"),quantity,rs.getString("PRICE"),(quantity*Double.parseDouble(rs.getString("PRICE")))};
						gui.model.addRow(obj);
						gui.cField.setText(null);gui.qField.setText(null);
						gui.cField.requestFocusInWindow();
						tableCheck = true;
					}
					if(!success){
						rs = DbLink.query("SELECT * FROM GERANIUM.DEALS WHERE DEALID = '"+code+"'");
						int j = 0;
						while(rs.next() && j < 1){
							success = true;
							i++;
							j++;
							codes.add(rs.getString("DEALID"));
							des.add(rs.getString("DESCRIPTION"));
							Object[] obj = {i,rs.getString("DESCRIPTION"),quantity,rs.getInt("DEALPRICE"),(quantity*Double.parseDouble(rs.getString("DEALPRICE")))};
							gui.model.addRow(obj);
							gui.cField.setText(null);gui.qField.setText(null);
							gui.cField.requestFocusInWindow();
							tableCheck = true;
						}
					}
					
					if(!success){Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null,"Not found!");
					}
//					if(resetTotal){
//						gui.totalField.setText(null);
//						resetTotal = false;
//					}
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Contact service provider!");
				}
				}catch(Exception e){
					e.printStackTrace();
					 Toolkit.getDefaultToolkit().beep();
				}
			}else{
				JOptionPane.showMessageDialog(null,"Pleas check! Product is already in sale table");
				gui.cField.setText("");
			}
			}else{
			gui.totalField.setText("Incomplete Info");
		}	
	}
	
	void clearButtonFun(){
		i = 0;codes= new ArrayList<String>(0);table = null;
		des = new ArrayList<String>(0);;
		 gui.cField.setText(null);
		 gui.qField.setText(null);
		 gui.model.setRowCount(0);
		 gui.cField.requestFocusInWindow();
		 enablecash = false;
		 tableCheck = false;
		 gui.totalField.setText(null);
	}
	
		/**
	 * For setting wait Cursor
	 * @param button
	 */
	void waitCursor(JButton button){
		button.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
	
	public double tableTotalCounter(){
	double total = 0;
		double temp1 = 0,temp2 = 0;
		for(int i = 0 ; i < gui.table.getRowCount() ; i++){
			try {
				temp1 = Double.parseDouble(""+gui.table.getValueAt(i, 2));
				temp2 = Double.parseDouble(""+gui.table.getValueAt(i, 3));
				gui.model.setValueAt((temp1*temp2), i, 4);
				total+=(temp1*temp2);
			} catch (NumberFormatException e) {
				continue;
			}  
		}
		gui.totalField.setText(""+total);
		return total;
		
	}
	


	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(gui.table)){
			tableTotalCounter();
		}
		if(e.getClickCount()==2 && e.getSource().equals(gui.ctable)){
			int row = gui.ctable.getSelectedRow();
			int col = gui.ctable.getSelectedColumn();
			gui.cField.setText(""+gui.catalogModel.getValueAt(row, col));
			searProduct();
			tableTotalCounter();
		}
		if(e.getClickCount()==2 && e.getSource().equals(gui.ctable2)){
			int row = gui.ctable2.getSelectedRow();
			int col = gui.ctable2.getSelectedColumn();
			gui.cField.setText(""+gui.catalogModel2.getValueAt(row, col));
			searProduct();tableTotalCounter();
		}
	}
	
	public void mouseEntered(MouseEvent arg0) {
		gui.enterButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.toolsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.customer_info.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.okBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}


	
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			try {
				  gui.enterButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				///  searProduct();
				} finally {
				  gui.enterButton.setCursor(Cursor.getDefaultCursor());
				}
		}
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	String getHash(String pass){
		try{byte[] bytesOfMessage = pass.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		pass = new String(md.digest(bytesOfMessage));
		return pass;
	   } catch (UnsupportedEncodingException e1) {
		e1.printStackTrace();
		return null;
	   } catch (NoSuchAlgorithmException e1) {
		e1.printStackTrace();
		return null;
	   }
	}

}
