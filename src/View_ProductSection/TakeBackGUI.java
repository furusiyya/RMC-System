package View_ProductSection;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import controller_ProductSection.TakeBack_Handler;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

public class TakeBackGUI {

	public JFrame frame;
	public JTextField invoiceCode;
	private JTextField txtCustomerInformation;
	public JLabel invoiceDate,netTotal,grossTotal,discountLabel,customerId_label;
	public JButton btnReport;
	public JTextArea disField ;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TakeBackGUI window = new TakeBackGUI();
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
	TakeBack_Handler handler = null;
	private JPanel tablePanel;
	public DefaultTableModel model;
	public JTable table;
	public JTextField lossField;
	private JLabel lblCustomerId;
	@SuppressWarnings("unused")
	public TakeBackGUI(ProductSailingGUI gui) {
		handler = new TakeBack_Handler(this,gui);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setTitle("Report Invoice");
		frame.setType(Type.UTILITY);
		frame.addWindowListener(handler);
		invoiceCode = new JTextField();
		invoiceCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		invoiceCode.setBounds(104, 37, 148, 27);
		frame.getContentPane().add(invoiceCode);
		invoiceCode.setColumns(10);
		invoiceCode.addKeyListener(handler);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		invoiceDate = new JLabel();
		invoiceDate.setForeground(Color.DARK_GRAY);
		invoiceDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		invoiceDate.setFocusable(false);
	    invoiceDate.setBounds(374, 37, 148, 27);
	    frame.getContentPane().add(invoiceDate);
	    
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
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
		frame.setBounds(100, 100, 785, 561);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JLabel lblNewLabel = new JLabel("Invoice code");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 38, 97, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Invoice date");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(283, 37, 97, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtCustomerInformation = new JTextField();
		txtCustomerInformation.setFocusable(false);
		txtCustomerInformation.setEditable(false);
		txtCustomerInformation.setForeground(SystemColor.textHighlightText);
		txtCustomerInformation.setBackground(new Color(0, 128, 0));
		txtCustomerInformation.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtCustomerInformation.setText("Refund Invoice:");
		txtCustomerInformation.setBounds(0, 0, 769, 32);
		frame.getContentPane().add(txtCustomerInformation);
		txtCustomerInformation.setColumns(10);
		
	
		tablePanel = new JPanel();
		tablePanel.setBounds(10, 114, 749, 269);
		frame.getContentPane().add(tablePanel);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		String[] columnTitle = {"Index","Description","Quantity","Price","Product id"};
		model = new DefaultTableModel(columnTitle, 0){
			public boolean isCellEditable(int row, int column) {
			       return column == 30;
			   }
			
			};
		table  = new JTable(model);
		table.setFillsViewportHeight(true);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table.setLocation(21, 184);
		table.setSize(629, 500);
		tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
		tablePanel.add(new JScrollPane(table),BorderLayout.CENTER);
		table.setLocation(21, 184);
		table.setSize(629, 500);
		
		btnReport = new JButton("Report");
		btnReport.addActionListener(handler);
		btnReport.setEnabled(false);
		btnReport.setBounds(648, 496, 111, 25);
		frame.getContentPane().add(btnReport);
		
		disField = new JTextArea();
		disField.setBounds(10, 438, 749, 47);
		frame.getContentPane().add(disField);
		
		JLabel lblLoss = new JLabel("Loss:");
		lblLoss.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoss.setBounds(10, 400, 76, 27);
		frame.getContentPane().add(lblLoss);
		
		lossField = new JTextField();
		lossField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				disField.requestFocusInWindow();
			}
		});
		lossField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lossField.setColumns(10);
		lossField.setBounds(67, 400, 148, 27);
		frame.getContentPane().add(lossField);
		
		lblCustomerId = new JLabel("Customer ID");
		lblCustomerId.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCustomerId.setBounds(10, 77, 97, 27);
		frame.getContentPane().add(lblCustomerId);
		
		customerId_label = new JLabel("");
		customerId_label.setFont(new Font("Dialog", Font.PLAIN, 14));
		customerId_label.setBounds(104, 75, 148, 27);
		frame.getContentPane().add(customerId_label);
		
		JLabel lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblGrossTotal.setBounds(528, 37, 79, 27);
		frame.getContentPane().add(lblGrossTotal);
		
		JLabel lblNetTotal = new JLabel("Net Total");
		lblNetTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNetTotal.setBounds(534, 75, 73, 27);
		frame.getContentPane().add(lblNetTotal);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDiscount.setBounds(283, 75, 97, 27);
		frame.getContentPane().add(lblDiscount);
		
		discountLabel = new JLabel();
		discountLabel.setForeground(Color.DARK_GRAY);
		discountLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		discountLabel.setFocusable(false);
		discountLabel.setBounds(374, 75, 148, 27);
		frame.getContentPane().add(discountLabel);
		
		grossTotal = new JLabel();
		grossTotal.setForeground(Color.DARK_GRAY);
		grossTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		grossTotal.setFocusable(false);
		grossTotal.setBounds(611, 37, 148, 27);
		frame.getContentPane().add(grossTotal);
		
		netTotal = new JLabel();
		netTotal.setForeground(Color.DARK_GRAY);
		netTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		netTotal.setFocusable(false);
		netTotal.setBounds(611, 75, 148, 27);
		frame.getContentPane().add(netTotal);
	}
}
