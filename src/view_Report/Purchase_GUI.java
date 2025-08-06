package view_Report;


import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import model_MenuSection.DbLink;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;

import controller_Report.PurchaseGUI_Handler;

import java.awt.Font;

public class Purchase_GUI {

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Purchase_GUI window = new Purchase_GUI();
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
	public Purchase_GUI() {

		DbLink.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JPanel panel ;
	public JLabel totalPurchase;
	public DefaultTableModel model;
	public JTable table;
	public JComboBox<Date> jBox;
	public Date[] sql = null;
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
		PurchaseGUI_Handler handler = new PurchaseGUI_Handler(this);
//		frame = new JFrame();
//		frame.setBounds(100, 100, 887, 623);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder("Vendor Register"));
		panel.setBounds(51, 36, 793, 536);
//		frame.getContentPane().add(panel);
		panel.setLayout(null);
		ArrayList<Date> date = new ArrayList<Date>(0);
		ResultSet rs = DbLink.query("SELECT DISTINCT SUPPDATE FROM GERANIUM.SUPPLY");
		try {
			while(rs.next()){
				date.add(rs.getDate("SUPPDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql = new Date[date.size()];
		for(int i = 0 ; i < sql.length ; i++)sql[i]=date.get(i);
		jBox = new JComboBox<Date>(sql);
		jBox.addActionListener(handler);
		jBox.setOpaque(false);
		jBox.setBounds(22, 24, 148, 30);
		panel.add(jBox);
		
		totalPurchase = new JLabel();
		totalPurchase.setFont(new Font("Dialog", Font.PLAIN, 15));
		totalPurchase.setBounds(544, 24, 233, 30);
		panel.add(totalPurchase);
		
		String[] columnTitle = {"Sr","Description","Type","Quantity","Color","Unit Price"};
		model = new DefaultTableModel(columnTitle, 0);
//		{
//			public boolean isCellEditable(int row, int column) {
//			    switch(column){
//			    case 5:return column == 5;
//			    default:return column == 6;	
//			    }   
//				}
//			
//			};
		JPanel tablePanel = new JPanel();
		tablePanel.setFocusable(false);
		tablePanel.setBounds(12, 66, 745, 428);
		tablePanel.setLayout(new BorderLayout());
		table  = new JTable(model);
		table.setRowHeight(30);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFocusable(false);
		table.setFillsViewportHeight(true);
		table.setLocation(21, 184);
		table.setSize(629, 500);
		tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFocusable(false);
		tablePanel.add(scrollPane,BorderLayout.CENTER);
		panel.add(tablePanel);
		if(date.size() > 0)handler.load();
	}

}
