package view_MenuSection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

import model_MenuSection.DbLink;
import controller_MenuSection.M_SpecialDeal_Handler;

public class M_SpecialDeals {

	private JFrame frame;
	public JPanel panel;
	public JTable table;
	public DefaultTableModel model;
	public JTextField idField;
	private JLabel lblNewLabel_5;
	public JTextField desField;
	public JButton updateBtn,deleteBtn;
	public JComboBox<String> box;
	public String[] arr;
	public JTextField priceField;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SpecialDeals window = new SpecialDeals();
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
	public M_SpecialDeals() {
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
		panel.setBorder(BorderFactory.createTitledBorder("New Deal"));
		panel.setBounds(0, 12, 1015, 575);
		frame = new JFrame();
		M_SpecialDeal_Handler handler = new M_SpecialDeal_Handler(this);
		frame.setBackground(SystemColor.controlHighlight);
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		@SuppressWarnings("unused")
		Dimension dim = toolkit.getScreenSize();
		frame.setSize(1033,620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		
		String[] columnTitle = {"Sr.","ID","Description","Quantity","UnitPrice"};
		model = new DefaultTableModel(columnTitle, 0){
			public boolean isCellEditable(int row, int column) {
			       return column == 3;
			   }
			};
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(71, 182, 650, 337);
		
		table  = new JTable(model);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
				table.setFillsViewportHeight(true);
				panel.setLayout(null);
				table.setRowHeight(30);
				table.setLocation(21, 184);
				table.setSize(629, 500);
				tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
				tablePanel.setLayout(null);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 650, 337);
				tablePanel.add(scrollPane);
				panel.add(tablePanel);
				
				
				JLabel lblNewLabel = new JLabel("Deal Id");
			    lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
			    lblNewLabel.setBounds(71, 35, 76, 26);
			    panel.add(lblNewLabel);
			    
			    idField = new JTextField();
			    idField.requestFocusInWindow();
			    idField.addFocusListener(handler);
			    idField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						desField.requestFocusInWindow();
						
					}
				});
			    idField.setFont(new Font("Dialog", Font.PLAIN, 16));
			    idField.setBounds(162, 31, 157, 30);
			    panel.add(idField);
			    idField.setColumns(10);
			    
			    lblNewLabel_5 = new JLabel("Description");
			    lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 14));
			    lblNewLabel_5.setBounds(71, 72, 92, 26);
			    panel.add(lblNewLabel_5);
			    
			    desField = new JTextField();
			    desField.addFocusListener(handler);
			    desField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						box.requestFocusInWindow();
						
					}
				});
			    
			    desField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    desField.setBounds(162, 68, 157, 30);
			    panel.add(desField);
			    desField.setColumns(10);
			    ArrayList<String> array = new ArrayList<String>(0);
			    try{
			    	ResultSet rs = DbLink.query("SELECT DISTINCT ID FROM GERANIUM.MENU order by ID");
					    	while(rs.next()){
						    		array.add(rs.getString("ID"));
					    	}
			    }catch(SQLException e){
			    	e.printStackTrace();
			    }
			    arr = new String[array.size()];
			    for(int i = 0 ; i < arr.length ; i++)arr[i] = array.get(i);
			    
			    box = new JComboBox<String>(arr);
			    box.addActionListener(handler);
			    box.setBounds(570, 104, 151, 26);
			    panel.add(box);
//			     
			    updateBtn = new JButton("Update");
			    updateBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			    updateBtn.setIcon(new ImageIcon(M_SpecialDeals.class.getResource("/icons/apply.png")));
			     updateBtn.addActionListener(handler);
			    updateBtn.setBounds(591, 531, 130, 23);
			    panel.add(updateBtn);
			    
			    deleteBtn = new JButton("Delete");
			    deleteBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			    deleteBtn.addActionListener(handler);
			    deleteBtn.setBounds(449, 531, 130, 23);
			    deleteBtn.setIcon(new ImageIcon(M_SpecialDeals.class.getResource("/org/jdesktop/swingx/plaf/basic/resources/error16.png")));
			    panel.add(deleteBtn);
			    
			    priceField = new JTextField();
			    priceField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    priceField.setColumns(10);
			    priceField.setBounds(162, 104, 157, 30);
			    panel.add(priceField);
			    
			    JLabel lblPrice = new JLabel("Price");
			    lblPrice.setFont(new Font("Dialog", Font.PLAIN, 14));
			    lblPrice.setBounds(71, 108, 76, 25);
			    panel.add(lblPrice);
			    
			    lblNewLabel_1 = new JLabel("Select product");
			    lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
			    lblNewLabel_1.setBounds(570, 87, 121, 15);
			    panel.add(lblNewLabel_1);
			
	}
}
