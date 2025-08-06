package controller_MainAndSailSection;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import View_ProductSection.ProductSailingGUI;
import model_MenuSection.DbLink;
import view_Main.OptionPane_GUI;
import view_Main.PasswordGUI;

public class PasswordHandler implements ActionListener,FocusListener,MouseListener{
	
	PasswordGUI gui;
	boolean admin = false;
	boolean changing = false;OptionPane_GUI oGui;ProductSailingGUI pGui;
	public PasswordHandler(PasswordGUI gui,boolean admin,OptionPane_GUI oGui,ProductSailingGUI pGui) {
		this.gui = gui;this.oGui = oGui;
		this.pGui = pGui;this.admin = admin;
		
		try {
			ResultSet rs = DbLink.query("select * from geranium.stock");
			while(rs.next()){
				ad = rs.getString("admin");
				us = rs.getString("USER");
				aden = rs.getInt("adminenable");
				usen = rs.getInt("userenable");
			}}catch(SQLException e){}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == gui.OkPass){
			if(changing == true){
				if(checkMatch()){
					 char[] pass = gui.confPassField.getPassword();
					 String str = new String(pass);
					 if(admin){
						 DbLink.writeSale("delete from GERANIUM.STOCK");
						 DbLink.writeSale("INSERT INTO GERANIUM.STOCK (ADMIN,user,adminenable,userenable) values ('"+str+"','"+us+"',"+aden+","+usen+")");
					 }
					 else{
						 DbLink.writeSale("delete from GERANIUM.STOCK");
						 DbLink.writeSale("INSERT INTO GERANIUM.STOCK (ADMIN,user,adminenable,userenable) values ('"+ad+"','"+str+"',"+aden+","+usen+")");
					 }
					 pGui.frame.setEnabled(true);
		             pGui.frame.setAlwaysOnTop(true);
		             oGui.frame.setVisible(false);
		             oGui.frame.dispose();
		             pGui.frame.setAlwaysOnTop(false);
		             pGui.cField.requestFocusInWindow();
				}
				else{
					gui.newPassField.setText(null);
					gui.confPassField.setText(null);
					Toolkit.getDefaultToolkit().beep();
				}
			}
			if(changing == false){
				 boolean enable = gui.enablePass.isSelected();
				 int num = 0;
				 if(enable){
					 num = 1;
				 }else{
					 num = 0;
				 }
				 if(admin){
					 DbLink.writeSale("delete from GERANIUM.STOCK");
					 DbLink.writeSale("INSERT INTO GERANIUM.STOCK (ADMIN,user,adminenable,userenable) values ('"+ad+"','"+us+"',"+num+","+usen+")");
				 }
				 else{
					 DbLink.writeSale("delete from GERANIUM.STOCK");
					 DbLink.writeSale("INSERT INTO GERANIUM.STOCK (ADMIN,user,adminenable,userenable) values ('"+ad+"','"+us+"',"+aden+","+num+")");
				 }
				 pGui.frame.setEnabled(true);
	             pGui.frame.setAlwaysOnTop(true);
	             oGui.frame.setVisible(false);
	             oGui.frame.dispose();
	             pGui.frame.setAlwaysOnTop(false);
	             pGui.cField.requestFocusInWindow();
			}
		}
	}
	
	String ad = new String(),us = new String();
	int aden= 0 ,usen = 0;
	boolean checkMatch(){
		return Arrays.equals(gui.newPassField.getPassword(), gui.confPassField.getPassword());
	}
	
	public void focusLost(FocusEvent e){
		}
	
	public void focusGained(FocusEvent e) {

		if(e.getSource() == gui.newPassField){
			char[] pass = gui.oldPassField.getPassword();
			 String str = new String(pass);
//			   for(int i = 0 ; i < pass.length ; i++)str+=pass[i];
//			 	str = getHash(str);
				if(admin){
						  if(getHash(str).equals(getHash(ad))){
							  gui.oldPassField.setEditable(false);
								gui.newPassField.setEditable(true);
						  }else{
								gui.oldPassField.setText(null);
								Toolkit.getDefaultToolkit().beep();
						  }
					  
					  }else{
						  
						  if(getHash(str).equals(getHash(us))){
							  gui.oldPassField.setEditable(false);
								gui.newPassField.setEditable(true);
						  }else{
								gui.oldPassField.setText(null);
								Toolkit.getDefaultToolkit().beep();
						  }
						  
					  }
					
			}
		
		
		if(e.getSource() == gui.confPassField){
			if(gui.newPassField.getPassword().length > 3 && gui.newPassField.getPassword().length < 10){
				gui.confPassField.setEditable(true);
				changing = true;
			}else{
				JOptionPane.showMessageDialog(gui.frame, "Password length should be greater than 3 characters and less than 10");
				gui.newPassField.setText(null);
				Toolkit.getDefaultToolkit().beep();
				gui.newPassField.requestFocusInWindow();
			}
		}
		
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		gui.OkPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gui.enablePass.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent arg0) {
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
