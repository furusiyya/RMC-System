package view_Main;


import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import View_ProductSection.ProductSailingGUI;
import controller_MainAndSailSection.PasswordHandler;

import javax.swing.JCheckBox;

import model_MenuSection.DbLink;

import javax.swing.JTextField;

public class PasswordGUI{

	public JButton OkPass;
	public JFrame frame;
	boolean admin = false;
	boolean selected = false;
	public PasswordGUI(boolean admin,OptionPane_GUI oGui,ProductSailingGUI pGui) {
		this.admin = admin;
		int adm = 0,user = 0; 
		try {
			DbLink.connect();
			ResultSet rs = DbLink.query("select adminenable,userenable from geranium.stock");
			while(rs.next()){
				adm = rs.getInt("adminenable");
				user = rs.getInt("userenable");
				
			}
			if(admin){
				if(adm == 0){selected = false;}
				else{
					selected = true;
				}
			}else{
				if(user == 0)selected = false;
				else{
					selected = true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			selected = false;
		}
		initialize(oGui,pGui);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public JPanel panel_1;
	public JPasswordField confPassField;
	public JPasswordField newPassField;
	public JPasswordField oldPassField;
	public JCheckBox enablePass;
	private JLabel lblConfirmPassword;
	private JLabel lblNewPassword;
	private JLabel lblOldPassword;
	private JTextField txtPasswordSettings;
	private void initialize(OptionPane_GUI oGui,ProductSailingGUI pGui) {
//		frame = new JFrame();
//		frame.setAlwaysOnTop(true);
//		frame.setResizable(false);
//		frame.setTitle(title());
//		frame.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
//		frame.setType(Type.UTILITY);
//		frame.getContentPane().setBackground(SystemColor.control);
//		frame.setBounds(157, 0, 693, 560);
//		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
		PasswordHandler handler = new PasswordHandler(this,admin,oGui,pGui);
		
		panel_1 = new JPanel();
		panel_1.setBounds(155, 0, 413, 476);
		//frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(240,240,240));
		
		lblOldPassword = new JLabel("Old password:");
		lblOldPassword.setForeground(Color.BLACK);
		lblOldPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblOldPassword.setBounds(15, 78, 107, 23);
		panel_1.add(lblOldPassword);
		
		lblNewPassword = new JLabel("New password:");
		lblNewPassword.setForeground(Color.BLACK);
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewPassword.setBounds(15, 112, 120, 23);
		panel_1.add(lblNewPassword);
		
		lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setForeground(Color.BLACK);
		lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(15, 146, 120, 23);
		panel_1.add(lblConfirmPassword);
		
		confPassField = new JPasswordField();
		confPassField.addActionListener(handler);
		confPassField.addFocusListener(handler);
		confPassField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confPassField.setEditable(false);
		confPassField.setBackground(Color.WHITE);
		confPassField.setBounds(201, 146, 178, 23);
		panel_1.add(confPassField);
		
		OkPass = new JButton("Save");
		OkPass.addMouseListener(handler);
		OkPass.addActionListener(handler);
		OkPass.setForeground(Color.BLACK);
		OkPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		OkPass.setBounds(290, 436, 89, 23);
		panel_1.add(OkPass);
		
		newPassField = new JPasswordField();
		newPassField.addActionListener(handler);
		newPassField.addFocusListener(handler);
		newPassField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		newPassField.setEditable(false);
		newPassField.setBackground(Color.WHITE);
		newPassField.setBounds(201, 112, 178, 23);
		panel_1.add(newPassField);
		
		oldPassField = new JPasswordField();
		oldPassField.addActionListener(handler);
		oldPassField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		oldPassField.setBounds(201, 78, 178, 23);
		panel_1.add(oldPassField);
		
		enablePass = new JCheckBox("Enable Password");
		enablePass.setSelected(selected);
		enablePass.addMouseListener(handler);
		enablePass.addActionListener(handler);
		enablePass.setBackground(new Color(240,240,240));
		enablePass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		enablePass.setBounds(15, 42, 169, 23);
		panel_1.add(enablePass);
		
		txtPasswordSettings = new JTextField();
		txtPasswordSettings.setFocusable(false);
		txtPasswordSettings.setEditable(false);
		txtPasswordSettings.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtPasswordSettings.setForeground(SystemColor.textHighlightText);
		txtPasswordSettings.setText("Password settings:");
		txtPasswordSettings.setBackground(new Color(34, 139, 34));
		txtPasswordSettings.setBounds(0, 0, 413, 28);
		panel_1.add(txtPasswordSettings);
		txtPasswordSettings.setColumns(10);
		
//		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
}
