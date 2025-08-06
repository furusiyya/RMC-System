package View_ProductSection;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import model_MenuSection.DbLink;
import controller_ProductSection.SailingGUI_Handler;


public class ProductSailingGUI {

	/**
	 * Initialize the contents of the frame.
	 */
	public JFrame frame;
	public JPanel panel, salePanel, catalogPanel;
	public JTextField cField,cNField,qField;
	public JTable table,ctable,ctable2;
	public JButton enterButton,clearButton,stockBtn,toolsBtn,returnBtn, 
				   purchaseBtn, btnTodayreport,deleteBtn, okBtn,customer_info,btnMiscellaneous;
	public JScrollPane scrollPane;
	public DefaultTableModel model, catalogModel, catalogModel2;
	public JComboBox<String> codeBox;
	public JLabel invoiceNo;
	/**
	 * Create the application.
	 */
	public ProductSailingGUI() {
		initialize();
	}

	SailingGUI_Handler handler = null;
	

	@SuppressWarnings("serial")
	private void initialize() {
		handler = new SailingGUI_Handler(this);
		
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
		
		frame = new JFrame();
		frame.setTitle("Naan-Zilla");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
//		Dimension dim = toolkit.getScreenSize();
		frame.setSize(1366,748);
		frame.setResizable(false);
		frame.addKeyListener(handler);
		
		/**
		 * Card Panel
		 */
		frame.getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBounds(257, 0, 1101, 662);
		frame.getContentPane().add(panel);
		/**
		 * --------------------------------------------------------------------------------------------------
		 */
		
		
		/**
		 * Panel as a sale page
		 */
		panel.setLayout(null);
		salePanel = new JPanel();
		salePanel.setLayout(null);
		salePanel.setBounds(0, 0, 1101, 662);
		salePanel.setBackground(SystemColor.controlHighlight);
		
		/*--------------------------------------------------------------------------------------------------*/
		
		
		/**
		 * Panel contain catalog Table and input Fields
		 */
		
		/*Field to enter product code*/
		cField = new JTextField();
		cField.setBounds(102, 84, 179, 32);
		cField.setForeground(Color.DARK_GRAY);
		cField.setBackground(new Color(255,255,255));
		cField.setFont(new Font("Dialog", Font.PLAIN, 20));
				cField.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
				qField.requestFocusInWindow();
			}
		});
		salePanel.add(cField);
		cField.setColumns(10);
		/*--------------------------------------------------------------------------------------------------*/
		
		/*Field to enter product quantity*/
		qField = new JTextField();
		PromptSupport.setPrompt("1", qField);
		qField.setBounds(329, 84, 178, 32);
		qField.setForeground(Color.DARK_GRAY);
		qField.setBackground(new Color(255,255,255));
		qField.setFont(new Font("Dialog", Font.PLAIN, 20));
		qField.addActionListener(handler);
		salePanel.add(qField);
		qField.setColumns(10);
		/*--------------------------------------------------------------------------------------------------*/
		
		/* Button to add products*/
		enterButton= new JButton("");
		enterButton.setToolTipText("Add to cart");
		enterButton.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/btnplus.png")));
		enterButton.setBounds(330, 18, 74, 40);
		enterButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		enterButton.addMouseListener(handler);
		enterButton.addActionListener(handler);
		salePanel.add(enterButton);
		/*--------------------------------------------------------------------------------------------------*/
		
		/*To remove product from catalog*/
		deleteBtn = new JButton("");
		deleteBtn.setToolTipText("Discard from cart");
		deleteBtn.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/minus.png")));
		deleteBtn.setBounds(430, 18, 74, 40);
		salePanel.add(deleteBtn);
		deleteBtn.addActionListener(handler);

		/*To clear catalog page*/
		clearButton = new JButton("");
		clearButton.setToolTipText("Clear cart");
		clearButton.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/editnew.png")));
		clearButton.setBounds(205, 18, 74, 40);
		salePanel.add(clearButton);
		clearButton.addActionListener(handler);
		clearButton.addMouseListener(handler);
		clearButton.setFont(new Font("Consolas", Font.PLAIN, 12));
		
		/*--------------------------------------------------------------------------------------------------*/
		panel.add(salePanel);
		/*--------------------------------------------------------------------------------------------------*/
		
		/**
		 * Product Catalog table code
		 */
		String[] columnTitle = {"Line","Items","Units","Unit Price","Value"};
		model = new DefaultTableModel(columnTitle, 0){
		public boolean isCellEditable(int row, int column) {
		       switch(column){
		       case 2:return column == 2;
		       default:return false;
		       }
		   }
		};
				
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(39, 158, 468, 476);
		tablePanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		tablePanel.setBorder(BorderFactory.createTitledBorder("Cart"));
		tablePanel.setLayout(new BorderLayout());
//		tablePanel.setBackground(new Color(230, 230, 230));
		table  = new JTable(model);
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setMaxWidth(36);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMinWidth(45);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(80);
		table.getColumnModel().getColumn(4).setMaxWidth(110);
		table.getColumnModel().getColumn(4).setMinWidth(100);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
//		table.setBackground(SystemColor.text);
		table.setBorder(null);
		table.setFillsViewportHeight(true);
		table.addMouseListener(handler);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table.setLocation(21, 184);
		table.setFocusable(false);
		table.setSize(629, 500);
		table.setLocation(21, 184);
		table.setSize(629, 500);
		
		tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
		tablePanel.add(new JScrollPane(table),BorderLayout.CENTER);
		salePanel.add(tablePanel);
		
		JLabel lblNewLabel = new JLabel("Product");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNewLabel.setBounds(39, 88, 62, 32);
		salePanel.add(lblNewLabel);
		
		JLabel lblQ = new JLabel("Units");
		lblQ.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblQ.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQ.setBounds(284, 88, 42, 32);
		salePanel.add(lblQ);
		
		
		customer_info = new JButton("");
		customer_info.setToolTipText("Add new customer");
		customer_info.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/kuser.png")));
		customer_info.setBounds(106, 18, 74, 40);
		customer_info.addActionListener(handler);
		salePanel.add(customer_info);
		
		
		/**
		 * Catalog table Implementation
		 */

		JPanel catalogPanel = new JPanel();
//		catalogPanel.setBackground(new Color(230, 230, 230));
		catalogPanel.setBounds(566, 158, 489, 476);
		catalogPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		catalogPanel.setBorder(BorderFactory.createTitledBorder("Catalog"));
		catalogPanel.setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		    tabbedPane.addTab("<html><body><table width='150'>Products</table></body></html>",returnTable());
		    addData(1);
		    tabbedPane.addTab("<html><body><table width='150'>Deals</table></body></html>",returnTable2());
		    catalogPanel.add(tabbedPane);
		    addData(2);
		salePanel.add(catalogPanel);
		
		totalField = new JTextField();
		totalField.setHorizontalAlignment(SwingConstants.CENTER);
		totalField.setFocusable(false);
		totalField.setEditable(false);
		totalField.setForeground(Color.DARK_GRAY);
		totalField.setFont(new Font("Dialog", Font.PLAIN, 20));
		totalField.setColumns(10);
		totalField.setBackground(Color.WHITE);
		totalField.setBounds(609, 84, 178, 32);
		salePanel.add(totalField);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTotal.setBounds(566, 84, 42, 32);
		salePanel.add(lblTotal);
		
		okBtn = new JButton("");
		okBtn.setToolTipText("Enter Cash");
		okBtn.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/cash.png")));
		okBtn.setBounds(981, 76, 74, 40);
		okBtn.addActionListener(handler);
		salePanel.add(okBtn);
		
		invoiceNo = new JLabel();
		invoiceNo.setFont(new Font("Dialog", Font.PLAIN, 14));
		invoiceNo.setText("Invoice # NZ-0"+(DbLink.invoiceCode()+1));
		invoiceNo.setHorizontalAlignment(SwingConstants.TRAILING);
		invoiceNo.setBounds(850, 18, 205, 32);
		salePanel.add(invoiceNo);
		/*--------------------------------------------------------------------------------------------------*/
		logoPanel();
	}
	
	
	void logoPanel(){
		JPanel logo_panel = new JPanel();
		logo_panel.setBackground(new Color(230, 230, 230));
		logo_panel.setBounds(6, 0, 251, 662);
		logo_panel.setLayout(null);
		frame.getContentPane().add(logo_panel);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/finalTrex.png")));
		lblNewLabel_1.setBounds(0, 0, 239, 247);
		logo_panel.add(lblNewLabel_1);
		returnBtn = new JButton(" Search");
		returnBtn.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/search22.png")));
		returnBtn.setToolTipText("Search Invoice to refund");
		returnBtn.addActionListener(handler);
		returnBtn.setBounds(33, 299, 167, 42);
		logo_panel.add(returnBtn);
		
		purchaseBtn = new JButton(" Vendor");
		purchaseBtn.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/greenled.png")));
		purchaseBtn.setToolTipText("Vendor Register");
		purchaseBtn.addActionListener(handler);
		purchaseBtn.setBounds(33, 353, 167, 42);
		logo_panel.add(purchaseBtn);
		
		btnTodayreport = new JButton(" Reports");
		btnTodayreport.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/kontact.png")));
		btnTodayreport.setToolTipText("Sale Register\n");
		btnTodayreport.addActionListener(handler);
		btnTodayreport.setBounds(33, 407, 167, 42);
		logo_panel.add(btnTodayreport);
		
		stockBtn = new JButton("Administrator");
		stockBtn.setToolTipText("Add or Edit Poducts, Deals, Human Resources ");
		stockBtn.addActionListener(handler);
		stockBtn.setBounds(33, 515, 167, 42);
		logo_panel.add(stockBtn);
		stockBtn.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/yast_sysadmin.png")));
		
				toolsBtn = new JButton("Configuration");
				toolsBtn.setToolTipText("Passwords, Address settings");
				toolsBtn.setBounds(33, 569, 167, 42);
				logo_panel.add(toolsBtn);
				toolsBtn.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/package_settings.png")));
				
				final JLabel timeLabel = new JLabel("");
				timeLabel.setBounds(33, 255, 178, 32);
				logo_panel.add(timeLabel);
				timeLabel.setBackground(new Color(230, 230, 230));
				
				btnMiscellaneous = new JButton(" Payment");
				btnMiscellaneous.setIcon(new ImageIcon(ProductSailingGUI.class.getResource("/icons/pay.png")));
				btnMiscellaneous.setToolTipText("For cash used randomly");
				btnMiscellaneous.setBounds(33, 461, 167, 42);
				btnMiscellaneous.addActionListener(handler);
				logo_panel.add(btnMiscellaneous);
				Timer timer = new Timer(500, new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                timeLabel.setText("  "+DateFormat.getDateTimeInstance().format(new Date()));
		            }
		        });
		        timer.setRepeats(true);
		        timer.setCoalesce(true);
		        timer.setInitialDelay(0);
		        timer.start();
				toolsBtn.addActionListener(handler);
	}
	
	@SuppressWarnings("serial")
	JPanel returnTable(){
		String[] column = {"ID","Description"};
		catalogModel = new  DefaultTableModel(column, 0){
			public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		JPanel tPanel = new JPanel();
		tPanel.setBounds(0, 0, 300, 300);
//		tPanel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		tPanel.setLayout(new BorderLayout());
		ctable  = new JTable(catalogModel);
		ctable.setGridColor(Color.GRAY);
		ctable.setFont(new Font("Andale Mono", Font.PLAIN, 18));
		ctable.setRowHeight(40);
//		ctable.setBackground(SystemColor.text);
		ctable.setBorder(null);
		ctable.setLocation(21, 184);
		ctable.setFocusable(false);
		ctable.setSize(300, 300);
		ctable.addMouseListener(handler);
		tPanel.add(ctable.getTableHeader(),BorderLayout.NORTH);
		tPanel.add(new JScrollPane(ctable),BorderLayout.CENTER);
		return tPanel;
	}
	  
	
	@SuppressWarnings("serial")
	JPanel returnTable2(){
		String[] column = {"ID","Description"};
		catalogModel2 = new  DefaultTableModel(column, 0){
			public boolean isCellEditable(int row, int column) {
			       return false;
			   }
			};
		JPanel tPanel2 = new JPanel();
		tPanel2.setBounds(0, 0, 300, 300);
//		tPanel2.setBorder(UIManager.getBorder("DesktopIcon.border"));
		tPanel2.setLayout(new BorderLayout());
		ctable2  = new JTable(catalogModel2);
		ctable2.setGridColor(Color.GRAY);
		ctable2.setFont(new Font("Andale Mono", Font.PLAIN, 18));
		ctable2.setRowHeight(40);
//		ctable2.setBackground(SystemColor.windowBorder);
		ctable2.setBorder(null);
		ctable2.setLocation(21, 184);
		ctable2.setFocusable(false);
		ctable2.setSize(300, 300);	
		ctable2.addMouseListener(handler);
		tPanel2.add(ctable2.getTableHeader(),BorderLayout.NORTH);
		tPanel2.add(new JScrollPane(ctable2),BorderLayout.CENTER);
		return tPanel2;
	}
	
//	void action(){
//		int row = ctable.getSelectedRow();
//		int col = ctable.getSelectedColumn();
//		code = ""+catalogModel.getValueAt(row, col);
//		searProduct();
//	}
//	void action2(){
//		int row = ctable2.getSelectedRow();
//		int col = ctable2.getSelectedColumn();
//		code = ""+catalogModel2.getValueAt(row, col);
//		searProduct();
//	}
	
	void addData(int tab){
		String[] str = new String[2];
		try {
			ResultSet rs = null;
			if(tab==1){
				rs  = DbLink.query("Select description, ID from geranium.menu order by ID");
				while(rs.next()){
					str[0] = rs.getString("ID");
					str[1] = rs.getString("DESCRIPTION");
					catalogModel.addRow(str);
				}
			}
				else if(tab==2){
					rs  = DbLink.query("Select distinct dealid, description from geranium.deals order by dealid");
					while(rs.next()){
						str[0] = rs.getString("dealId");
						str[1] = rs.getString("description");
						catalogModel2.addRow(str);
					}	
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Passwords
	 */
	static char[] masterKey = {'s','a','a','d','4','2','7','6','2','6'};
	public JTextField totalField;
	public static char[] getmasterKey(){
		return masterKey;	}
}
