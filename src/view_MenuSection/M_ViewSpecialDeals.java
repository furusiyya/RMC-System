package view_MenuSection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;

import java.awt.Font;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

import model_MenuSection.DbLink;
import controller_MenuSection.M_ViewSpecialDeal_Handler;

public class M_ViewSpecialDeals {

	private JFrame frame;
	public JPanel panel;
	public JTable table;
	public DefaultTableModel model;
	public JButton deleteBtn;
	public JComboBox<String> box;
	public String[] arr;
	public JLabel price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_ViewSpecialDeals window = new M_ViewSpecialDeals();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public M_ViewSpecialDeals() {
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
		panel = new JPanel();
		panel.setBounds(0, 12, 1015, 575);
		panel.setBorder(BorderFactory.createTitledBorder("View and Remove"));
		frame = new JFrame();
		M_ViewSpecialDeal_Handler handler = new M_ViewSpecialDeal_Handler(this);
		frame.setBackground(SystemColor.windowBorder);
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		@SuppressWarnings("unused")
		Dimension dim = toolkit.getScreenSize();
		frame.setSize(1033,620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		
		String[] columnTitle = {"Sr.","Product Id","Description","Quantity"};
		model = new DefaultTableModel(columnTitle, 0){
			public boolean isCellEditable(int row, int column) {
			       return column == 3;
			   }
			};
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(71, 128, 650, 280);
		
		table  = new JTable(model);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
				table.setFillsViewportHeight(true);
				panel.setLayout(null);
				table.setLocation(21, 184);
				table.setRowHeight(30);
				table.setSize(629, 500);
				tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
				tablePanel.setLayout(null);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 650, 280);
				tablePanel.add(scrollPane);
				panel.add(tablePanel);
			    ArrayList<String> array = new ArrayList<String>(0);
			    try{
			    	ResultSet rs = DbLink.query("SELECT DISTINCT DEALID FROM GERANIUM.DEALS");
					    	while(rs.next()){
						    		array.add(rs.getString("DEALID"));
					    	}
			    }catch(SQLException e){
			    	e.printStackTrace();
			    }
			    arr = new String[array.size()];
			    for(int i = 0 ; i < arr.length ; i++)arr[i] = array.get(i);
//			    
			    box = new JComboBox<String>(arr);
			    box.addActionListener(handler);
			    box.setBounds(71, 52, 142, 26);
			    panel.add(box);
			    
			    deleteBtn = new JButton("Delete");
			    deleteBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			    deleteBtn.addActionListener(handler);
			    deleteBtn.setBounds(591, 90, 130, 23);
			    deleteBtn.setIcon(new ImageIcon(M_ViewSpecialDeals.class.getResource("/org/jdesktop/swingx/plaf/basic/resources/error16.png")));
			    panel.add(deleteBtn);
			    
			    price = new JLabel("");
			    price.setForeground(new Color(205, 133, 63));
			    price.setFont(new Font("Dialog", Font.PLAIN, 16));
			    price.setBounds(71, 90, 151, 26);
			    panel.add(price);
			
	}
}
