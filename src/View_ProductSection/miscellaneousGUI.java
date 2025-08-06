package View_ProductSection;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

public class miscellaneousGUI implements WindowListener{

	public JFrame frmPersonalize;
	private JTextField amountField;

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
	public miscellaneousGUI(ProductSailingGUI gui) {
		this.gui=gui;
		initialize();
		panel.setVisible(true);
	}
	ProductSailingGUI gui = null;
	JTextArea descriptionArea;
	public JPanel panel;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPersonalize = new JFrame();
		frmPersonalize.setTitle("Miscellaneous");
		frmPersonalize.setBounds(100, 100, 446, 196);
//		frmPersonalize.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPersonalize.getContentPane().setLayout(null);
		frmPersonalize.addWindowListener(this);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 0, 431, 163);
		frmPersonalize.getContentPane().add(panel);
		
		
		
		JLabel lblName = new JLabel("Amount");
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setBounds(37, 12, 82, 27);
		panel.add(lblName);
		JLabel lblAddress = new JLabel("Reason");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAddress.setBounds(37, 51, 82, 27);
		panel.add(lblAddress);
		
		amountField = new JTextField();
		amountField.setFont(new Font("Dialog", Font.PLAIN, 14));
		amountField.setColumns(10);
		amountField.setBounds(137, 12, 259, 27);
		panel.add(amountField);
		
		descriptionArea = new JTextArea();
		descriptionArea.setFont(new Font("Dialog", Font.PLAIN, 14));
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setBounds(137, 51, 259, 61);
		panel.add(descriptionArea);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(miscellaneousGUI.class.getResource("/icons/button_cancel.png")));
		btnCancel.setBounds(312, 124, 107, 25);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(miscellaneousGUI.class.getResource("/icons/apply.png")));
		btnSave.setBounds(212, 124, 89, 25);
		panel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(add())closeWindow();
				else{
					descriptionArea.setText("");
					amountField.setText("");
				}
			}
		});
		
		frmPersonalize.setVisible(true);
	}

	
	public boolean add(){
		if(descriptionArea.getText().length()<3){JOptionPane.showMessageDialog(this.frmPersonalize, "Reason should be greater than 5 characters");return false;}
		return DbLink.writeSale("INSERT INTO GERANIUM.MISCELLANEOUS (DESCRIPTION,AMOUNT) VALUES ('"+descriptionArea.getText().trim()
				+"','"+amountField.getText()+"')");	
	}
	
	public void closeWindow(){
		gui.frame.setEnabled(true);
			gui.frame.setAlwaysOnTop(true);
	        frmPersonalize.dispose();
	        gui.frame.setAlwaysOnTop(false);
	        gui.cField.requestFocusInWindow();
	}



	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void windowClosing(WindowEvent e) {
			gui.frame.setEnabled(true);
				gui.frame.setAlwaysOnTop(true);
		        frmPersonalize.dispose();
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
		

}
