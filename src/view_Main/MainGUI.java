package view_Main;

import java.awt.Color;

import javax.swing.*;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.Dialog.ModalExclusionType;
import java.awt.SystemColor;

import javax.swing.border.MatteBorder;

import controller_MainAndSailSection.MainHandler;
import model_MenuSection.DbLink;

import java.awt.Window.Type;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainGUI {

	public JFrame frame;
	public JPasswordField passwordField;
	public JButton okBtn;
	public JButton cancelBtn;
	public JRadioButton adminBtn;
	public JRadioButton userBtn;

	/**
	 * Create the application.
	 */
	public MainGUI() {
		getInput();
		if(getSecurity() == false)
		{
			DbLink.allow();
		}
		else{
		initialize();
		frame.setVisible(true);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBackground(SystemColor.control);
		frame.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		frame.setBounds(100, 100, 438, 166);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainHandler handler = new MainHandler(this);
		ButtonGroup group = new ButtonGroup();
		frame.getContentPane().setLayout(null);
		frame.setLocation(300, 240);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(169, 169, 169)));
		panel.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		panel.setBounds(0, 0, 432, 137);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		adminBtn = new JRadioButton("Admin");
		adminBtn.addActionListener(handler);
		adminBtn.addMouseListener(handler);
		adminBtn.setForeground(SystemColor.menuText);
		adminBtn.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		adminBtn.setBounds(39, 17, 100, 29);
		panel.add(adminBtn);
		adminBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		group.add(adminBtn);
		 userBtn = new JRadioButton("User");
		 userBtn.addMouseListener(handler);
		userBtn.addActionListener(handler);
		userBtn.setForeground(SystemColor.menuText);
		userBtn.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		userBtn.setBounds(120, 17, 100, 29);
		panel.add(userBtn);
		userBtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		group.add(userBtn);
		
		passLabel = new JLabel("Enter Password");
		passLabel.setForeground(SystemColor.menuText);
		passLabel.setBounds(39, 54, 145, 29);
		panel.add(passLabel);
		passLabel.setFont(new Font("Consolas", Font.ITALIC, 18));
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(handler);
		passwordField.setEditable(false);
		passwordField.setBounds(194, 52, 214, 29);
		panel.add(passwordField);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		okBtn = new JButton("OK");
		okBtn.addMouseListener(handler);
		okBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		okBtn.setEnabled(false);
		okBtn.addActionListener(handler);
		okBtn.setForeground(SystemColor.textHighlightText);
		okBtn.setBackground(new Color(0, 102, 102));
		okBtn.setBounds(194, 92, 107, 23);
		panel.add(okBtn);
		
		cancelBtn = new JButton("Cnacel");
		cancelBtn.addMouseListener(handler);
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelBtn.addActionListener(handler);
		cancelBtn.setForeground(SystemColor.textHighlightText);
		cancelBtn.setBackground(new Color(0, 102, 102));
		cancelBtn.setBounds(306, 92, 102, 23);
		panel.add(cancelBtn);
		
	}
	public JLabel passLabel;
	
	boolean getSecurity(){
		int admin = 0,user = 0; 
		try {
			DbLink.connect();
			ResultSet rs = DbLink.query("select adminenable,userenable from geranium.stock");
			while(rs.next()){
				admin = rs.getInt("adminenable");
				user = rs.getInt("userenable");
			}
			if(admin == 0 && user == 0)return false;
			else{return true;}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void getInput(){
		try{
			PrintStream errstream = new PrintStream(new FileOutputStream("Err.txt"),true);
			System.setErr(errstream);
			errstream.close();
		}catch(IOException e){
		}finally{	
		}	
	}
}
