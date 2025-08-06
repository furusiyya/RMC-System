package view_Main;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import View_ProductSection.ProductSailingGUI;
import model_MainAndSailRecords.Address;
import model_MainSailRecordFililngSection.AdressFillingSection;
import controller_MainAndSailSection.AddressHandler;

public class AddressGUI {

	public JFrame frmShopDetails;
	public JTextField phoneNo;
	public JTextField shopName;
	public JTextArea addressArea;
	public JButton addreeOk;

	public JPanel panel;
	private JTextField txtContactOn;
	private JLabel lblNewLabel_3;
	/**
	 * Create the application.
	 */
	public AddressGUI(OptionPane_GUI oGui,ProductSailingGUI gui) {
		initialize(oGui,gui);
	//	frmShopDetails.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(OptionPane_GUI oGui,ProductSailingGUI gui) {
//		frmShopDetails = new JFrame();
//		frmShopDetails.setAlwaysOnTop(true);
//		frmShopDetails.setTitle("Address");
//		frmShopDetails.setResizable(false);
//		frmShopDetails.setBounds(100, 100, 657, 571);
//		frmShopDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		frmShopDetails.getContentPane().setLayout(null);
		
		AddressHandler handler = new AddressHandler(this,gui,oGui);
		Address address = AdressFillingSection.RetrieveAddress();
		
		panel = new JPanel();
		panel.setBackground(new Color(240,240,240));
		panel.setBounds(155, 0, 413, 514);
		panel.setBackground(new Color(240,240,240));
	//	frmShopDetails.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Shop name:");
		lblNewLabel.setBounds(16, 74, 85, 24);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.BLACK);
		panel.add(lblNewLabel);
		
		shopName = new JTextField();
		shopName.setBounds(121, 74, 254, 24);
		shopName.setForeground(Color.DARK_GRAY);
		shopName.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(shopName);
		shopName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone number:");
		lblNewLabel_2.setBounds(16, 109, 97, 24);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_2);
		
		phoneNo = new JTextField();
		phoneNo.setBounds(121, 109, 254, 24);
		phoneNo.setForeground(Color.DARK_GRAY);
		phoneNo.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(phoneNo);
		phoneNo.setColumns(10);
			
			
		JLabel lblNewLabel_1 = new JLabel("Address:");
		lblNewLabel_1.setBounds(16, 144, 70, 24);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);
		
		addressArea= new JTextArea(8,30);
		addressArea.setWrapStyleWord(true);
		addressArea.setBounds(121, 155, 254, 183);
		addressArea.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.activeCaptionBorder, SystemColor.activeCaptionBorder, SystemColor.activeCaptionBorder, SystemColor.activeCaptionBorder));
		addressArea.setLineWrap(true);
		addressArea.setFont(new Font("Arial", Font.PLAIN, 14));
		addressArea.setForeground(Color.DARK_GRAY);
		addressArea.setBackground(SystemColor.text);
		panel.add(addressArea);
		
		try{shopName.setText(address.shopName);
			phoneNo.setText(address.phoneNo);
			addressArea.setText(address.description);
		}catch(NullPointerException e){
		}
		
		addreeOk = new JButton("Save");
		addreeOk.setForeground(Color.BLACK);
		addreeOk.addActionListener(handler);
		addreeOk.addMouseListener(handler);
		addreeOk.setBounds(290, 479, 89, 23);
		addreeOk.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(addreeOk);
		
		txtContactOn = new JTextField();
		txtContactOn.setFocusable(false);
		txtContactOn.setEditable(false);
		txtContactOn.setText("Contact on:");
		txtContactOn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtContactOn.setBackground(new Color(34, 139, 34));
		txtContactOn.setForeground(SystemColor.textHighlightText);
		txtContactOn.setBounds(0, 0, 413, 28);
		panel.add(txtContactOn);
		txtContactOn.setColumns(10);
		
		lblNewLabel_3 = new JLabel("This address is directly printed in top of invoice");
		lblNewLabel_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_3.setBounds(16, 39, 359, 24);
		panel.add(lblNewLabel_3);
	}
}
