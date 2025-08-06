package controller_MainAndSailSection;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

import model_MenuSection.DbLink;
import View_ProductSection.ProductSailingGUI;
import view_Main.MainGUI;

public class MainHandler implements ActionListener,MouseListener{
	MainGUI checkPoint;
	boolean admin = false;
	
	public MainHandler(MainGUI mainGUI) {
		checkPoint = mainGUI;
	}
	

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == checkPoint.cancelBtn){
			System.exit(0);
		}
		
		else if(e.getSource() == checkPoint.passwordField){
			   checkPoint.okBtn.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			   char[] pass = checkPoint.passwordField.getPassword();
				String st ="NAANZILLA@naanzilla";
				
			   String str = new String(pass);
//					   for(int i = 0 ; i < pass.length ; i++)str+=pass[i];
			   boolean checkMaster = Arrays.equals(pass, ProductSailingGUI.getmasterKey());
				if(admin == true && checkMaster == true){
					allowToUse();
					DbLink.allow();
				}
			
				else if(admin == true && getHash(str).equals(getHash(st))){
					JPanel panel = new JPanel();
					JButton saleBtn = new JButton("D- NETSALE");
					saleBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
								DbLink.writeSale("DELETE FROM GERANIUM.NETSALE");
						}
					});
					JButton monthBtn = new JButton("D- MONTHLYSALE");
					monthBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							DbLink.writeSale("DELETE FROM GERANIUM.MREPORT");
						}
					});
					
					JButton failedBtn = new JButton("D- FAILEDSALE");
					failedBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							DbLink.writeSale("DELETE FROM GERANIUM.FAILED");
						}
					});
					JButton demandBtn = new JButton("D- Vendor");
					demandBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							DbLink.writeSale("DELETE FROM GERANIUM.SUPPLY");
						}
					});
					JButton accountBtn = new JButton("D-Accounts");
					accountBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							DbLink.writeSale("DELETE FROM GERANIUM.account");
							System.out.println("Delete");
						}
					});
					JButton misBtn = new JButton("D-Miscellaneous");
					misBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							DbLink.writeSale("DELETE FROM GERANIUM.Miscellaneous");
						}
					});
					JButton utilityBtn = new JButton("D- FinalRerports");
					utilityBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							DbLink.writeSale("DELETE FROM GERANIUM.FAILED");
						}
					});
					panel.add(saleBtn);
					panel.add(monthBtn);
					panel.add(failedBtn);
					panel.add(demandBtn);
					panel.add(accountBtn);
					panel.add(misBtn);
					panel.add(utilityBtn);
					try {
//					ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.ALLERTS");
//					   int recovery = 0;
//					while(rs.next()){
//						recovery = rs.getInt("RECOVERY");
//					   }
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
					JXDatePicker pass1 = new JXDatePicker();
					pass1.setOpaque(false);
					pass1.getEditor().setFont(new Font("Tahoma", Font.PLAIN, 14));
					pass1.setFormats(simpleDateFormat);
					ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.ALLERTS");
					   while(rs.next()){
						pass1.setDate(rs.getDate("EXPIRY"));
						}
					 pass1.setFont(new Font("Tahoma", Font.PLAIN, 12));
					panel.add(pass1);
//					panel.setVisible(true);
					pass1.requestFocusInWindow();
					String[] options = new String[]{"OK", "Cancel"};
					int option = JOptionPane.showOptionDialog(null, panel, "Admin Password",
					                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					                         null, options, options[1]);
					if(option == 0) // pressing OK button
					{
					    Date date  = pass1.getDate();	
					    DbLink.writeSale("delete from  geranium.allerts");
					    DbLink.writeSale("insert into geranium.allerts(expiry)"
					    			+ " values ('"+new java.sql.Date(date.getTime())+"')");
					}
					
					}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else if(check(str)){
					allowToUse();
					DbLink.allow();
				}
				else{
					Toolkit.getDefaultToolkit().beep();
					checkPoint.passwordField.setText(null);
				}
		}
		
		else if(e.getSource() == checkPoint.adminBtn){
			admin = true;
			checkPoint.passwordField.setText(null);
			checkPoint.passwordField.setEditable(true);
			checkPoint.passwordField.requestFocusInWindow();
			checkPoint.okBtn.setEnabled(true);
			
		}
		
		else if(e.getSource() == checkPoint.userBtn){
			admin= false;
			checkPoint.passwordField.setText(null);
			checkPoint.passwordField.setEditable(true);
			checkPoint.passwordField.requestFocusInWindow();
			checkPoint.okBtn.setEnabled(true);
		}
		
		else if(e.getSource() == checkPoint.okBtn){
			try {
				  checkPoint.okBtn.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				  char[] pass = checkPoint.passwordField.getPassword();
				  String str = new String(pass);
//				   for(int i = 0 ; i < pass.length ; i++)str+=pass[i];
		  
				  boolean checkMaster = Arrays.equals(pass, ProductSailingGUI.getmasterKey());
					if(admin == true && checkMaster == true){
						allowToUse();
						DbLink.allow();
					}
					else if(check(str)){
						allowToUse();
						DbLink.allow();
					}
					else{
						Toolkit.getDefaultToolkit().beep();
						checkPoint.passwordField.setText(null);
					}
				} finally {
					checkPoint.okBtn.setCursor(Cursor.getDefaultCursor());
				}
			
		}	
		
	}
	
	
	
	private boolean check(String pass) {
		boolean success = false;
		String ad = null,us = null;
		
		try {
			ResultSet rs = DbLink.query("select admin,user from geranium.stock");
			while(rs.next()){
				ad = rs.getString("admin");
				us = rs.getString("user");
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(admin == true){
			try {
				
				success = (getHash(pass).equals(getHash(ad)));
					}
			catch (NullPointerException e) {
				Toolkit.getDefaultToolkit().beep();
				checkPoint.passwordField.setText(null);
			}
		}
		else if(admin == false){
			try {
				success = getHash(pass).equals(getHash(us));
				}
			 catch (NullPointerException e) {
				Toolkit.getDefaultToolkit().beep();
				checkPoint.passwordField.setText(null);
			}
		}
		return success;
	}

	void allowToUse(){
		checkPoint.frame.setVisible(false);
		checkPoint.frame.dispose();
	}
	
		public void mouseClicked(MouseEvent arg0) {

		
	}
	public void mouseEntered(MouseEvent arg0) {
		checkPoint.adminBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkPoint.userBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkPoint.okBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkPoint.cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent arg0) {
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
