package view_MenuSection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller_MenuSection.M_UpdateHandler;

public class M_UpdateMenu {

//	private JFrame frame;
	public JPanel panel;
	public JTable table;
	public DefaultTableModel model;
	public JTextField idField;
	private JLabel lblNewLabel_1;
	public JTextField nameField;
	private JLabel lblNewLabel_5;
	public JTextField sPriceField;
	public JButton submitBtn,updateBtn,deleteBtn;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdateMenu window = new UpdateMenu();
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
	public M_UpdateMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		panel.setBorder(UIManager.getBorder("DesktopIcon.border"));
		panel.setBorder(BorderFactory.createTitledBorder("New Product"));
		panel.setBounds(0, 12, 1015, 575);
//		frame = new JFrame();
		M_UpdateHandler handler = new M_UpdateHandler(this);
//		frame.setBackground(SystemColor.windowBorder);
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		@SuppressWarnings("unused")
		Dimension dim = toolkit.getScreenSize();
//		frame.setSize(1029,613);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		
//		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		
		String[] columnTitle = {"Sr.","ID","Description","Price"};
		model = new DefaultTableModel(columnTitle, 0);
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(71, 182, 650, 337);
		
		table  = new JTable(model);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
				table.setFillsViewportHeight(true);
				panel.setLayout(null);
				table.setLocation(21, 184);
				table.setSize(629, 500);
				table.setRowHeight(30);
				tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
				tablePanel.setLayout(null);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 650, 337);
				tablePanel.add(scrollPane);
				panel.add(tablePanel);
				
				
				JLabel lblNewLabel = new JLabel("Product Id");
			    lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
			    lblNewLabel.setBounds(71, 35, 76, 26);
			    panel.add(lblNewLabel);
			    
			    idField = new JTextField();
			    idField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						nameField.requestFocusInWindow();
						
					}
				});
			    idField.setFont(new Font("Dialog", Font.PLAIN, 16));
			    idField.setBounds(162, 31, 157, 30);
			    panel.add(idField);
			    idField.setColumns(10);
			    
			    lblNewLabel_1 = new JLabel("Description");
			    lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 14));
			    lblNewLabel_1.setBounds(71, 72, 92, 26);
			    panel.add(lblNewLabel_1);
			    
			    nameField = new JTextField();
			    nameField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						sPriceField.requestFocusInWindow();
						
					}
				});
			    nameField.setFont(new Font("Dialog", Font.PLAIN, 16));
			    nameField.setBounds(162, 68, 157, 30);
			    panel.add(nameField);
			    nameField.setColumns(10);
			    
			    lblNewLabel_5 = new JLabel("Price");
			    lblNewLabel_5.setFont(new Font("Dialog", Font.PLAIN, 14));
			    lblNewLabel_5.setBounds(71, 108, 76, 25);
			    panel.add(lblNewLabel_5);
			    
			    sPriceField = new JTextField();
			    sPriceField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						submitBtn.requestFocusInWindow();
						
					}
				});
			    sPriceField.setFont(new Font("Dialog", Font.PLAIN, 16));
			    sPriceField.setBounds(162, 104, 157, 30);
			    panel.add(sPriceField);
			    sPriceField.setColumns(10);
			    
			     updateBtn = new JButton("Update");
			     updateBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			     updateBtn.setIcon(new ImageIcon(M_UpdateMenu.class.getResource("/icons/apply.png")));
			     updateBtn.addActionListener(handler);
			    updateBtn.setBounds(591, 531, 130, 23);
			    panel.add(updateBtn);
			    
			    deleteBtn = new JButton("Delete");
			    deleteBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			    deleteBtn.addActionListener(handler);
			    deleteBtn.setBounds(449, 531, 130, 23);
			    deleteBtn.setIcon(new ImageIcon(M_UpdateMenu.class.getResource("/org/jdesktop/swingx/plaf/basic/resources/error16.png")));
			    panel.add(deleteBtn);
			    
			    submitBtn = new JButton("Submit");
			    submitBtn.setIcon(new ImageIcon(M_UpdateMenu.class.getResource("/org/jdesktop/swingx/plaf/linux/resources/combo-gtk.png")));
			    submitBtn.addActionListener(handler);
			    submitBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			    submitBtn.setBounds(564, 108, 157, 25);
			    panel.add(submitBtn);
			
	}
}
