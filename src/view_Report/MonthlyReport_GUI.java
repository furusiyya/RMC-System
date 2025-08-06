package view_Report;


import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonthlyReport_GUI {

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MonthlyReport_GUI window = new MonthlyReport_GUI();
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
	public MonthlyReport_GUI() {
		DbLink.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JPanel panel;
	static JPanel tablePanel ;
	public JLabel totalSale,discountLabel;
	public static DefaultTableModel model;
	public static JTable table;
	public JComboBox<Integer> monthBox,yearBox;
	public String[] sql = null;
	public ArrayList<Date> date = null;
	public JLabel lblNewLabel_2;
	
	@SuppressWarnings({ "serial", "unchecked", "rawtypes" })
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
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			
		}
		
//		frame = new JFrame();
//		frame.setBounds(100, 100, 887, 623);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Monthly Register"));
		panel.setBackground(Color.WHITE);
		panel.setBounds(51, 36, 793, 536);
//		frame.getContentPane().add(panel);
		
		panel.setLayout(null);
		Integer[] array = new Integer[12];
		int num = 01;
		for(int i = 0 ; i < array.length;i++)
			{array[i]=num;num++;}
		
		monthBox = new JComboBox(array);
		monthBox.setOpaque(false);
		monthBox.setBounds(22, 24, 74, 30);
		panel.add(monthBox);
		array = new Integer[45];
		num = 2015;
		for(int i = 0 ; i < array.length;i++)
			{array[i]=num;num++;}
		yearBox = new JComboBox(array);
		yearBox.setOpaque(false);
		yearBox.setBounds(94, 24, 74, 30);
		panel.add(yearBox);
		
		totalSale = new JLabel();
		totalSale.setText("Cash: ");
		totalSale.setForeground(Color.BLACK);
		totalSale.setFont(new Font("Dialog", Font.PLAIN, 14));
		totalSale.setBounds(544, 24, 233, 30);
		panel.add(totalSale);
		
		discountLabel = new JLabel();
		discountLabel.setText("Discount: ");
		discountLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		discountLabel.setForeground(Color.BLACK);
		discountLabel.setBounds(544, 53, 233, 30);
		panel.add(discountLabel);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setBounds(10, 125, 256, 14);
		panel.add(lblNewLabel_2);
		
		refund = new JLabel();
		refund.setText("Refund: ");
		refund.setForeground(Color.BLACK);
		refund.setFont(new Font("Dialog", Font.PLAIN, 14));
		refund.setBounds(544, 84, 233, 30);
		panel.add(refund);
		String[] columnTitle = {"ID","Description","Qty","Gross Total"};
		model = new DefaultTableModel(columnTitle, 0)
		{public boolean isCellEditable(int row, int column) {
			    return column == 20;   
				}
			};
		tablePanel = new JPanel();
		tablePanel.setFocusable(false);
		tablePanel.setBounds(10, 125, 738, 400);
		tablePanel.setLayout(new BorderLayout());
		table  = new JTable(model);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setRowHeight(30);
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
		
		
		monthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalSale.setText("");
				discountLabel.setText("");
				lblNewLabel_2.setText("");
				model.setRowCount(0);
				add();
			}
			});
		yearBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalSale.setText("");
				discountLabel.setText("");
				lblNewLabel_2.setText("");
				model.setRowCount(0);
				add();
			}
			});
		
	}
	
	public void add(){
		try{
			double total = 0,discount = 0,loss = 0;
			boolean flag = false;
			ResultSet rs = DbLink.query("SELECT s.ID,m.DESCRIPTION,m.PRICE,SUM(s.QUANTITY) AS QUANTITY"
								+ " FROM GERANIUM.NETSALE s natural join GERANIUM.MENU m WHERE "
								+ "EXTRACT(MONTH FROM s.INVOICEDATE) ="+monthBox.getSelectedItem()
								+" AND  EXTRACT(YEAR FROM s.INVOICEDATE) ="+yearBox.getSelectedItem()+
								" GROUP BY(s.ID) order by QUANTITY DESC");
				while(rs.next()){
					Object[] obj = {rs.getString("ID"),rs.getString("DESCRIPTION"),rs.getInt("QUANTITY"),
																		(rs.getInt("QUANTITY")*rs.getInt("PRICE"))};
					total+=(rs.getInt("QUANTITY")*rs.getInt("PRICE")); 
					model.addRow(obj);
					flag = true;
				}
				
				rs = DbLink.query("SELECT s.ID,SUM(s.QUANTITY) AS QUANTITY"
						+ " FROM GERANIUM.NETSALE s WHERE "
						+ "EXTRACT(MONTH FROM s.INVOICEDATE) ="+monthBox.getSelectedItem()+" AND  EXTRACT(YEAR FROM s.INVOICEDATE) ="
						+yearBox.getSelectedItem()+" AND ID LIKE 'd%' GROUP BY(s.ID) order by QUANTITY DESC");
				String str = "";
				while(rs.next()){
					str+=rs.getString("ID")+",";
					str+=rs.getString("QUANTITY")+",";
					flag = true;
				}
				/**If no record found then return, no need to move forward*/
				if(!flag){
					totalSale.setText("Cash: 0");
					discountLabel.setText("Discount: 0");
					refund.setText("Refund: 0");	
					return;}
				String[] dealId = str.split(",");
				for(int j = 0 ; j < dealId.length; j+=2){
					rs = DbLink.query("SELECT DISTINCT DESCRIPTION,DEALPRICE FROM GERANIUM.DEALS WHERE dealID = '"+dealId[j]+"'");
					while(rs.next()){
						Object[] obj = {dealId[j],rs.getString("DESCRIPTION"),dealId[j+1],
													(Integer.parseInt(dealId[j+1])*rs.getDouble("DEALPRICE"))};
						total+= rs.getDouble("DEALPRICE")*Double.parseDouble(dealId[j+1]);
						model.addRow(obj);
					}
				}
				
				rs = DbLink.query("SELECT DISCOUNT FROM GERANIUM.NETSALE  WHERE "
						+ "EXTRACT(MONTH FROM INVOICEDATE) ="+monthBox.getSelectedItem()+" AND  EXTRACT(YEAR FROM INVOICEDATE) ="
						+yearBox.getSelectedItem()+" AND DISCOUNT != 0 GROUP BY(CODE)");
				while(rs.next())discount+=rs.getInt("DISCOUNT");
				
				rs = DbLink.query("SELECT * from GERANIUM.FAILED WHERE "
						+ "EXTRACT(MONTH FROM INVOICEDATE) ="+monthBox.getSelectedItem()+" AND  EXTRACT(YEAR FROM INVOICEDATE) ="+yearBox.getSelectedItem()+"");
				while(rs.next()){
					loss += rs.getInt("LOSS");
				}
				
				/*Writing to Month final revenu register*/
				rs = DbLink.query("SELECT TOTALSALE,DISCOUNT,REFUND FROM GERANIUM.UTILITY WHERE EXTRACT(MONTH FROM MONTH) = "+monthBox.getSelectedItem()
						+" And EXTRACT(YEAR FROM MONTH) ="+yearBox.getSelectedItem()+"");
				str = "";
				while(rs.next()){
					str+=rs.getString("TOTALSALE")+","; 
					str+=rs.getString("DISCOUNT")+",";  
					str+=rs.getString("REFUND")+","; 
				}
				String[] mreport;
				try {
					mreport = str.split(",");
					mreport[0] = ""+(total-discount-loss);
					mreport[1] = ""+discount;
					mreport[2] = ""+loss;
					DbLink.writeSale("UPDATE GERANIUM.UTILITY set TOTALSALE = '"+mreport[6]+"', DISCOUNT = '"
								+mreport[7]+"',REFUND='"+mreport[8]+"' WHERE EXTRACT(MONTH FROM MONTH) = "
								+(Integer.parseInt(""+monthBox.getSelectedItem())-1)
								+" And EXTRACT(YEAR FROM MONTH) ="+yearBox.getSelectedItem()+"");
				} catch (ArrayIndexOutOfBoundsException e) {
//					e.printStackTrace();
					System.err.println("Class: monthlyReport_GUI in report selctoin");
				}
				total = total-discount-loss;
				totalSale.setText("Cash: "+total);
				discountLabel.setText("Discount: "+discount);
				refund.setText("Refund: "+loss);	
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println("Exception in retreiving sale report");
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
		}
		
	
}

	private JLabel refund;
	}
