package View_ProductSection;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model_MenuSection.DbLink;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Font;

public class customerGUI implements FocusListener, WindowListener,KeyListener{

	public JFrame frmPersonalize;
	private JTextField cPField;
	private JTextField cNField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					customerGUI window = new customerGUI();
//					window.frmPersonalize.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public customerGUI(ProductSailingGUI gui) {
		this.gui=gui;
		initialize();
		panel.setVisible(true);
	}
	ProductSailingGUI gui = null;
	JTextArea addressArea;
	private JTextField cCField;
	public JPanel panel;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPersonalize = new JFrame();
		frmPersonalize.setTitle("Personalize");
		frmPersonalize.setBounds(100, 100, 445, 259);
//		frmPersonalize.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPersonalize.getContentPane().setLayout(null);
		frmPersonalize.addWindowListener(this);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 0, 431, 229);
		frmPersonalize.getContentPane().add(panel);
		
		
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(37, 24, 82, 27);
		panel.add(lblNewLabel);
		
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setBounds(37, 63, 82, 27);
		panel.add(lblName);
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAddress.setBounds(37, 102, 82, 27);
		panel.add(lblAddress);
		
		cPField = new JTextField();
		cPField.setFont(new Font("Dialog", Font.PLAIN, 14));
		cPField.addFocusListener(this);
		cPField.addKeyListener(this);
		cPField.setBounds(240, 24, 156, 27);
		panel.add(cPField);
		cPField.setColumns(10);
		
		cNField = new JTextField();
		cNField.setFont(new Font("Dialog", Font.PLAIN, 14));
		cNField.setColumns(10);
		cNField.setBounds(137, 63, 259, 27);
		panel.add(cNField);
		
		addressArea = new JTextArea();
		addressArea.setFont(new Font("Dialog", Font.PLAIN, 14));
		addressArea.setLineWrap(true);
		addressArea.setWrapStyleWord(true);
		addressArea.setBounds(137, 108, 259, 61);
		panel.add(addressArea);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(customerGUI.class.getResource("/icons/button_cancel.png")));
		btnCancel.setBounds(312, 192, 107, 25);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(customerGUI.class.getResource("/icons/apply.png")));
		btnSave.setBounds(215, 192, 89, 25);
		panel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkAddress())closeWindow();
			}
		});
		cCField = new JTextField();
		cCField.setFont(new Font("Dialog", Font.PLAIN, 14));
		cCField.addFocusListener(this);
		cCField.setColumns(10);
		cCField.addKeyListener(this);
		cCField.setBounds(137, 24, 89, 27);
		panel.add(cCField);
		
		frmPersonalize.setVisible(true);
	}

	
	public boolean checkAddress(){
		if(cNField.getText().length()<3){
			JOptionPane.showMessageDialog(this.frmPersonalize, "Name is too short!");
			cNField.requestFocusInWindow();
			return false;
		}else{
			if(addressArea.getText().length()<10){
				JOptionPane.showMessageDialog(this.frmPersonalize, "Name is too short!");
				cNField.requestFocusInWindow();
				return false;
			}else{
				DbLink.writeSale("delete from geranium.customers where customer_Id ="+
						Long.parseLong(cCField.getText()+cPField.getText())+"");
				return DbLink.writeSale("INSERT INTO GERANIUM.CUSTOMERS (customer_Id,name,address) "
				+ "values ("+Long.parseLong(cCField.getText()+cPField.getText())+",'"+cNField.getText()+"','"+addressArea.getText()+"')");
			}
		}
	}
	public void closeWindow(){
		gui.frame.setEnabled(true);
			gui.frame.setAlwaysOnTop(true);
	        frmPersonalize.dispose();
	        gui.frame.setAlwaysOnTop(false);
	        gui.cField.requestFocusInWindow();
	}
		
	void retAddress(){
		if( cCField.getText().length()!=4 &&  cCField.getText().length()!=3){
			 cCField.setText(null);
			 addressArea.setText(null);
			 cCField.requestFocusInWindow();
		}else{
			if( cPField.getText().length()!=7){
				 cPField.setText(null);		
				 addressArea.setText(null);
				 cPField.requestFocusInWindow();
			}else{
				try{
					Integer.parseInt( cCField.getText());
					Long.parseLong( cPField.getText());
					try {
						ResultSet rs = DbLink.retrieveAddress(Long.parseLong( cCField.getText()+ cPField.getText()));
						cNField.setText(null);addressArea.setText(null);
						while(rs.next()){
							cNField.setText(""+rs.getString("NAME"));
							addressArea.setText(""+rs.getString("Address"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}catch(NumberFormatException e){
					e.printStackTrace();
					 cPField.setText(null);
					 cCField.setText(null);
					 addressArea.setText(null);
					 cCField.requestFocusInWindow();
				}
			}
		}
}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
		retAddress();
	}


	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowClosing(WindowEvent e) {
		gui.frame.setEnabled(true);
		gui.frame.setAlwaysOnTop(true);
        e.getWindow().dispose();
        gui.frame.setAlwaysOnTop(false);
        gui.cField.requestFocusInWindow();
	}


	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getSource().equals(cCField)){
			if(cCField.getText().length()>2){
				if(cCField.getText().charAt(1)=='3' && cCField.getText().length()==4){
					cPField.requestFocusInWindow();
					retAddress();
				}
					else if(cCField.getText().charAt(1)!='3' && cCField.getText().length()==3){
						cPField.requestFocusInWindow();
						retAddress();
					}
			}
			}
			if(e.getSource().equals(cPField)){
				if(cPField.getText().length()==7){cNField.requestFocusInWindow();retAddress();}
			}
	}
}