package view_Report;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import controller_Report.SaleChart_Handler;

public class SaleChartGUI {

//	public JFrame frame;
	/**
	 * Launch the application.
////	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReportSaleGUI window = new ReportSaleGUI();
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
	SaleChart_Handler handler = null;
	public JPanel reportSalePanel;
	private JPanel tablePanel,panel_1;
	public DefaultTableModel model;
	public JTable table;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	public JLabel customerContact,customerName;
	public JLabel totalAmount,lblInvoiceCode;
	public JLabel invoiceCode;
	public JXDatePicker dField;
	public JButton nextInvoice,backBtn;
	public JLabel totalInvoice,currentInvoice;
	public JLabel netTotal;
	private JPanel panel;
	private JLabel lblGrossTotal;
	private JLabel lblNetTotal;
	public JLabel netTotalLabel, discountLabel;
	public JLabel refundLabel;
	public SaleChartGUI() {
		handler = new SaleChart_Handler(this);
//		frame = new JFrame();
//		frame.setResizable(false);
//		frame.setAlwaysOnTop(true);
//		frame.getContentPane().setLayout(null);
		reportSalePanel = new JPanel();
//		reportSalePanel.setBackground(Color.WHITE);
//		frame.getContentPane().setBackground(new Color(112, 132, 156));
//		frame.getContentPane().add(reportSalePanel);
		reportSalePanel.setBounds(21, 36, 827, 564);
		
	  //  frame.getContentPane().add(txtCustomerInformation);
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
		reportSalePanel.setLayout(null);
		
		tablePanel = new JPanel();
		tablePanel.setBounds(3, 209, 784, 343);
		reportSalePanel.add(tablePanel);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		String[] columnTitle = {"Index","Description","Quantity","Price"};
		model = new DefaultTableModel(columnTitle, 0){
			public boolean isCellEditable(int row, int column) {
			       return column == 6;
			   }
			};
		table  = new JTable(model);
		table.setFont(new Font("Dialog", Font.PLAIN, 15));
		table.setFocusable(false);
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table.setLocation(21, 184);
		table.setSize(629, 500);
		tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFocusable(false);
		tablePanel.add(scrollPane,BorderLayout.CENTER);
		table.setLocation(21, 184);

		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setSize(629, 500);
		
		panel_1 = new JPanel();
		panel_1.setBounds(4, 124, 780, 82);
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(Color.WHITE);
		reportSalePanel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Customer");
		lblNewLabel_1.setBounds(12, 45, 75, 23);
		lblNewLabel_1.setFocusable(false);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_1);
		
		customerName = new JLabel("");
		customerName.setBounds(99, 45, 128, 25);
		customerName.setFocusable(false);
		customerName.setForeground(new Color(184, 134, 11));
		customerName.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.add(customerName);
		
		lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setBounds(273, 12, 75, 23);
		lblNewLabel_3.setFocusable(false);
		lblNewLabel_3.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_3);
		
		customerContact = new JLabel("");
		customerContact.setBounds(360, 12, 110, 23);
		customerContact.setFocusable(false);
		customerContact.setForeground(new Color(184, 134, 11));
		customerContact.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.add(customerContact);
		
		totalAmount = new JLabel("");
		totalAmount.setBounds(658, 12, 110, 23);
		totalAmount.setForeground(new Color(184, 134, 11));
		totalAmount.setFocusable(false);
		totalAmount.setFont(new Font("Dialog", Font.PLAIN, 15));
		panel_1.add(totalAmount);
		lblInvoiceCode = new JLabel("Invoice");
		lblInvoiceCode.setBounds(12, 10, 67, 23);
		panel_1.add(lblInvoiceCode);
		lblInvoiceCode.setFocusable(false);
		lblInvoiceCode.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		invoiceCode = new JLabel("");
		invoiceCode.setForeground(new Color(184, 134, 11));
		invoiceCode.setFont(new Font("Dialog", Font.PLAIN, 15));
		invoiceCode.setBounds(99, 10, 128, 25);
		panel_1.add(invoiceCode);
		
		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDiscount.setFocusable(false);
		lblDiscount.setBounds(273, 45, 75, 23);
		panel_1.add(lblDiscount);
		
		discountLabel = new JLabel("");
		discountLabel.setForeground(new Color(184, 134, 11));
		discountLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		discountLabel.setFocusable(false);
		discountLabel.setBounds(360, 45, 110, 23);
		panel_1.add(discountLabel);
		
		lblGrossTotal = new JLabel("Gross Total");
		lblGrossTotal.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblGrossTotal.setFocusable(false);
		lblGrossTotal.setBounds(548, 12, 98, 23);
		panel_1.add(lblGrossTotal);
		
		lblNetTotal = new JLabel("Net Total");
		lblNetTotal.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNetTotal.setFocusable(false);
		lblNetTotal.setBounds(548, 45, 98, 23);
		panel_1.add(lblNetTotal);
		
		netTotalLabel = new JLabel("");
		netTotalLabel.setForeground(new Color(184, 134, 11));
		netTotalLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		netTotalLabel.setFocusable(false);
		netTotalLabel.setBounds(658, 45, 110, 23);
		panel_1.add(netTotalLabel);
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    
	    panel = new JPanel();
	    panel.setBounds(0, 0, 789, 125);
	    reportSalePanel.add(panel);
	    panel.setLayout(null);
	    panel.setBorder(BorderFactory.createTitledBorder("Invoice register"));
	    dField = new JXDatePicker();
	    dField.setBounds(22, 24, 148, 30);
	    panel.add(dField);
	    dField.setOpaque(false);
	    dField.addActionListener(handler);
	    dField.getEditor().setFont(new Font("Tahoma", Font.PLAIN, 14));
	    dField.setFormats(simpleDateFormat);
	    dField.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    
	    netTotal = new JLabel("2335.0");
	    netTotal.setBounds(544, 24, 233, 30);
	    panel.add(netTotal);
	    netTotal.setText("Closing Cash of day: ");
	    netTotal.setForeground(Color.DARK_GRAY);
	    netTotal.setFocusable(false);
	    netTotal.setFont(new Font("Dialog", Font.PLAIN, 15));
	    
	    backBtn = new JButton("");
	    backBtn.setBounds(22, 57, 74, 40);
	    panel.add(backBtn);
	    backBtn.setEnabled(false);
	    backBtn.addActionListener(handler);
	    backBtn.setIcon(new ImageIcon(SaleChartGUI.class.getResource("/icons/menu-left.png")));
	    
	    nextInvoice = new JButton("");
	    nextInvoice.setBounds(96, 57, 74, 40);
	    panel.add(nextInvoice);
	    nextInvoice.setEnabled(false);
	    nextInvoice.addActionListener(handler);
	    nextInvoice.setIcon(new ImageIcon(SaleChartGUI.class.getResource("/icons/menu-right.png")));
	    
	    totalInvoice = new JLabel("");
	    totalInvoice.setBounds(714, 100, 63, 25);
	    panel.add(totalInvoice);
	    totalInvoice.setHorizontalAlignment(SwingConstants.CENTER);
	    totalInvoice.setFont(new Font("Dialog", Font.PLAIN, 14));
	    totalInvoice.setFocusable(false);
	    
	    currentInvoice = new JLabel("");
	    currentInvoice.setHorizontalAlignment(SwingConstants.RIGHT);
	    currentInvoice.setBounds(668, 100, 50, 25);
	    panel.add(currentInvoice);
	    currentInvoice.setFont(new Font("Dialog", Font.PLAIN, 14));
	    currentInvoice.setFocusable(false);
	    
	    refundLabel = new JLabel("Refund Cash:");
	    refundLabel.setForeground(Color.DARK_GRAY);
	    refundLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
	    refundLabel.setFocusable(false);
	    refundLabel.setBounds(544, 57, 233, 30);
	    panel.add(refundLabel);
		
	}
}
