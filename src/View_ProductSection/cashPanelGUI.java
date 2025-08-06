package View_ProductSection;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import controller_ProductSection.SailingGUI_Handler;
import controller_ProductSection.cashPanelGUI_Handler;

import java.awt.Color;

import javax.swing.JPanel;

public class cashPanelGUI {

	public JFrame frame;
	/**
	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					cashPanelGUI window = new cashPanelGUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	cashPanelGUI_Handler handler = null;
	private JPanel panel;
	public JTextField totalField,balanceField;
	@SuppressWarnings("rawtypes")
	public JComboBox disField;
	public JCheckBox deliveryBtn;
	public JTextField cashField;
	private JPanel panel_5;
	public JButton cfifty, cHundred, cTwoHundred, cFiveHundred, cThousand, cTwoThousand, cFiveThousand, cTenThousand,
					okBtn,cancelBtn, invoiceBtn;
	public JTextField remainingField, cPField,cCField;
	public JTextArea addressArea;
	public cashPanelGUI(SailingGUI_Handler gui) {
		handler = new cashPanelGUI_Handler(this,gui);
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.addWindowListener(handler);
		frame.setTitle("Payment");
		panel = new JPanel();
		panel.setBounds(6, 12, 797, 535);
		//panel.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(112, 132, 156));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel);
	    
		initialize();
		totalField.setText(gui.gui.totalField.getText());
		remainingField.setText(gui.gui.totalField.getText());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		 try {
			    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			        if ("Nimbus".equals(info.getName())) {
			            UIManager.setLookAndFeel(info.getClassName());
			            break;
			        }
			    }
			} catch (Exception e) {
				System.out.print("Nimbus not Found");
			}
		frame.setBounds(100, 100, 817, 589);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		/*Button to Show total amount*/
		panel.setLayout(null);
		totalField = new JTextField("");
		totalField.setFont(new Font("Dialog", Font.PLAIN, 20));
		totalField.setBounds(141, 30, 167, 30);
		totalField.setForeground(new Color(204, 0, 0));
		totalField.setEditable(false);
		totalField.setFocusable(false);
		totalField.setBackground(SystemColor.text);
		totalField.setHorizontalAlignment(SwingConstants.CENTER);
//		totalField.setFont(new Font("Vrinda", Font.PLAIN, 50));
		panel.add(totalField);
		
		/*Button to Show Balance amount*/
		balanceField = new JTextField("");
		balanceField.setFont(new Font("Dialog", Font.PLAIN, 20));
		balanceField.setBounds(189, 148, 167, 30);
		balanceField.setForeground(new Color(204, 0, 0));
		balanceField.setFocusable(false);
		balanceField.setEditable(false);
		balanceField.setHorizontalAlignment(SwingConstants.CENTER);
		balanceField.setBackground(Color.WHITE);
//		balanceField.setFont(new Font("Vrinda", Font.PLAIN, 40));
		panel.add(balanceField);
		

		String[] aray = new String[21];
		aray[0] = "Discount";
		for(int i = 1 ; i < aray.length;i++){
			aray[i] = (i*5)+"%";
		}
		disField = new JComboBox(aray);
		disField.setFont(new Font("Dialog", Font.PLAIN, 14));
		disField.setBounds(580, 30, 101, 30);
		disField.addItemListener(handler);
		panel.add(disField);
		
		
		cashField = new JTextField();
		cashField.setFont(new Font("Dialog", Font.PLAIN, 20));
		cashField.setHorizontalAlignment(SwingConstants.CENTER);
		cashField.setToolTipText("");
		cashField.setBounds(189, 105, 167, 30);
		cashField.setForeground(new Color(255, 69, 0));
		cashField.setBackground(new Color(255,255,255));
//		cashField.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		cashField.addFocusListener(handler);
		cashField.addActionListener(handler);
		cashField.addKeyListener(handler);
		cashField.setColumns(10);
		panel.add(cashField);
		
		invoiceBtn = new JButton("");
		invoiceBtn.setIcon(new ImageIcon(cashPanelGUI.class.getResource("/icons/fileprint.png")));
		invoiceBtn.setBounds(400, 456, 93, 44);
		invoiceBtn.setEnabled(false);
		//invoiceBtn.setBackground(new Color(51,51,51));
		//invoiceBtn.setForeground(Color.DARK_GRAY);
		invoiceBtn.setFont(new Font("Calibri", Font.ITALIC, 12));
		//invoiceBtn.addFocusListener(handler);
		invoiceBtn.addActionListener(handler);
		invoiceBtn.addMouseListener(handler);
		panel.add(invoiceBtn);
		
		
		panel_5 = new JPanel();
		panel_5.setBounds(478, 105, 295, 296);
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(4, 2, 0, 0));
		
		cfifty = new JButton("Rs. 50");
		panel_5.add(cfifty);
		cfifty.setForeground(Color.DARK_GRAY);
		cfifty.setBackground(new Color(193,193,193));
		cfifty.addMouseListener(handler);
		cfifty.addActionListener(handler);
		//cfifty.addFocusListener(handler);
		cfifty.setFont(new Font("Calibri", Font.PLAIN, 22));
		
			cHundred = new JButton("Rs. 100");
			panel_5.add(cHundred);
			//cHundred.addFocusListener(handler);
			cHundred.setBackground(new Color(193,193,193));
			cHundred.setForeground(Color.DARK_GRAY);
			cHundred.setFont(new Font("Calibri", Font.PLAIN, 22));
			cHundred.addMouseListener(handler);
			cHundred.addActionListener(handler);
			
			cTwoHundred = new JButton("Rs. 200");
			panel_5.add(cTwoHundred);
			cTwoHundred.setForeground(Color.DARK_GRAY);
			cTwoHundred.setBackground(new Color(193,193,193));
			//cTwoHundred.addFocusListener(handler);
			cTwoHundred.addMouseListener(handler);
			cTwoHundred.addActionListener(handler);
			cTwoHundred.setFont(new Font("Calibri", Font.PLAIN, 22));
			
			cFiveHundred = new JButton("Rs. 500");
			panel_5.add(cFiveHundred);
			cFiveHundred.setForeground(Color.DARK_GRAY);
			cFiveHundred.setBackground(new Color(193,193,193));
			//cFiveHundred.addFocusListener(handler);
			cFiveHundred.addMouseListener(handler);
			cFiveHundred.addActionListener(handler);
			cFiveHundred.setFont(new Font("Calibri", Font.PLAIN, 22));
			
			cThousand = new JButton("Rs. 1000");
			panel_5.add(cThousand);
			cThousand.setForeground(Color.DARK_GRAY);
			//cThousand.addFocusListener(handler);
			cThousand.setBackground(new Color(193,193,193));
			cThousand.addMouseListener(handler);
			cThousand.addActionListener(handler);
			cThousand.setFont(new Font("Calibri", Font.PLAIN, 22));
			
			cTwoThousand = new JButton("Rs. 2000");
			panel_5.add(cTwoThousand);
			cTwoThousand.setForeground(Color.DARK_GRAY);
			cTwoThousand.setBackground(new Color(193,193,193));
			//cTwoThousand.addFocusListener(handler);
			cTwoThousand.addMouseListener(handler);
			cTwoThousand.addActionListener(handler);
			cTwoThousand.setFont(new Font("Calibri", Font.PLAIN, 22));
			
			cFiveThousand = new JButton("Rs. 5000");
			panel_5.add(cFiveThousand);
			cFiveThousand.setForeground(Color.DARK_GRAY);
			cFiveThousand.setBackground(new Color(193,193,193));
			//cFiveThousand.addFocusListener(handler);
			cFiveThousand.addMouseListener(handler);
			cFiveThousand.addActionListener(handler);
			cFiveThousand.setFont(new Font("Calibri", Font.PLAIN, 22));
			
			cTenThousand = new JButton("C");
			panel_5.add(cTenThousand);
			cTenThousand.setForeground(new Color(255, 0, 0));
			cTenThousand.setBackground(new Color(193,193,193));
			//cTenThousand.addFocusListener(handler);
			cTenThousand.addMouseListener(handler);
			cTenThousand.addActionListener(handler);
			cTenThousand.setFont(new Font("Dialog", Font.BOLD, 22));
			
			okBtn = new JButton("OK");
			okBtn.addMouseListener(handler);
			okBtn.setIcon(new ImageIcon(cashPanelGUI.class.getResource("/icons/button_ok.png")));
			okBtn.setBounds(532, 456, 101, 44);
			okBtn.setEnabled(false);
			okBtn.addActionListener(handler);
			panel.add(okBtn);
			
			cancelBtn = new JButton("Cancel");
			cancelBtn.addMouseListener(handler);
			cancelBtn.setIcon(new ImageIcon(cashPanelGUI.class.getResource("/icons/button_cancel.png")));
			cancelBtn.addActionListener(handler);
			cancelBtn.setBounds(658, 456, 127, 44);
			panel.add(cancelBtn);
			
			remainingField = new JTextField("");
			remainingField.setFont(new Font("Dialog", Font.PLAIN, 20));
			remainingField.setHorizontalAlignment(SwingConstants.CENTER);
			remainingField.setForeground(new Color(204, 0, 0));
			remainingField.setFocusable(false);
			remainingField.setEditable(false);
			remainingField.setBackground(Color.WHITE);
			remainingField.setBounds(403, 30, 167, 30);
			panel.add(remainingField);
			
			JLabel lblNewLabel = new JLabel("Total");
			lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblNewLabel.setBounds(96, 30, 42, 30);
			panel.add(lblNewLabel);
			
			JLabel lblRemaining = new JLabel("Remaining");
			lblRemaining.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblRemaining.setBounds(320, 30, 81, 30);
			panel.add(lblRemaining);
			
			JLabel lblGiven = new JLabel("Given");
			lblGiven.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblGiven.setBounds(70, 106, 70, 30);
			panel.add(lblGiven);
			
			JLabel lblChange = new JLabel("Change");
			lblChange.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblChange.setBounds(70, 148, 70, 30);
			panel.add(lblChange);
			
			cPField = new JTextField();
			cPField.setFont(new Font("Dialog", Font.PLAIN, 20));
			cPField.setHorizontalAlignment(SwingConstants.CENTER);
			cPField.setForeground(new Color(255, 69, 0));
			cPField.setColumns(10);
			cPField.addKeyListener(handler);
			cPField.setBackground(Color.WHITE);
			cPField.setBounds(249, 216, 107, 30);
			panel.add(cPField);
			
			cCField = new JTextField();
			cCField.setFont(new Font("Dialog", Font.PLAIN, 20));
			cCField.setHorizontalAlignment(SwingConstants.CENTER);
			cCField.setForeground(new Color(255, 69, 0));
			cCField.setColumns(10);
			cCField.addKeyListener(handler);
			cCField.setBackground(Color.WHITE);
			cCField.setBounds(189, 216, 54, 30);
			panel.add(cCField);
			
			JLabel lblCon = new JLabel("Phone");
			lblCon.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblCon.setBounds(70, 216, 79, 30);
			panel.add(lblCon);
			
			deliveryBtn = new JCheckBox("");
			deliveryBtn.addMouseListener(handler);
			deliveryBtn.setToolTipText("Home delivery");
			deliveryBtn.setBounds(189, 258, 54, 30);
			deliveryBtn.addActionListener(handler);
			panel.add(deliveryBtn);
			
			addressArea = new JTextArea();
			addressArea.setFont(new Font("Dialog", Font.PLAIN, 14));
			addressArea.setBounds(70, 316, 286, 85);
			panel.add(addressArea);
			addressArea.setWrapStyleWord(true);
			addressArea.setLineWrap(true);
			addressArea.setEditable(false);
			addressArea.addMouseListener(handler);
			
			JLabel lblShip = new JLabel("Ship");
			lblShip.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblShip.setBounds(70, 258, 79, 30);
			panel.add(lblShip);
	}
} 

