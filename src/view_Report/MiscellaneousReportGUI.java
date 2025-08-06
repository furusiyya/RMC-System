package view_Report;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import model_MenuSection.DbLink;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdesktop.swingx.JXDatePicker;

public class MiscellaneousReportGUI {

//	private JFrame frame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MiscellaneousReportGUI window = new MiscellaneousReportGUI();
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
	public MiscellaneousReportGUI() {

		DbLink.connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JPanel panel ;
	public JLabel totalNetSale;
	public DefaultTableModel model;
	public JTable table;
	public JXDatePicker dField;
	private void initialize() {
		//frame = new JFrame();
		panel = new JPanel();
	    add();
	}
	
	@SuppressWarnings("serial")
	void add(){
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
//		frame.setBounds(100, 100, 887, 623);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		panel.setBackground(Color.WHITE);
		panel.setBounds(51, 36, 793, 536);
		//frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		String[] columnTitle ={"Description","Expense"};
		model = new DefaultTableModel(columnTitle, 0)
		{
			public boolean isCellEditable(int row, int column) {
			    return (column==8);  
				}
			};
		JPanel tablePanel = new JPanel();
		tablePanel.setFocusable(false);
		tablePanel.setBounds(194, 108, 485, 416);
		tablePanel.setLayout(new BorderLayout());
		table  = new JTable(model);
		table.setRowHeight(30);
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
//		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
//		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
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
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(24, 24, 655, 72);
	    panel_1.setLayout(null);
	    panel_1.setBorder(BorderFactory.createTitledBorder("Product sales by date"));
	    panel.add(panel_1);
	    
	    totalNetSale = new JLabel();
	    totalNetSale.setBounds(410, 23, 233, 30);
	    panel_1.add(totalNetSale);
	    totalNetSale.setText("Total Expense: ");
	    totalNetSale.setFont(new Font("Dialog", Font.PLAIN, 15));
	    totalNetSale.setForeground(Color.DARK_GRAY);
	    totalNetSale.setFocusable(false);
	    dField = new JXDatePicker();
	    dField.setBounds(22, 24, 148, 30);
	    panel_1.add(dField);
	    dField.setOpaque(false);
	    dField.getEditor().setFont(new Font("Tahoma", Font.PLAIN, 14));
	    dField.setFormats(simpleDateFormat);
	    dField.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    panel.setVisible(true);
	    dField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(dField.getDate() != null){
					model.setRowCount(0);
					totalNetSale.setText("Total Expense: ");
					int total = 0;
					try {
						ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.MISCELLANEOUS WHERE DATE = '"
														+new java.sql.Date(dField.getDate().getTime())+"'");
							while(rs.next()){
							Object[] obj = {rs.getString("DESCRIPTION"),rs.getInt("AMOUNT")};
							total+=rs.getInt("AMOUNT");
							model.addRow(obj);
							}
							totalNetSale.setText("Total Expense: "+total);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
