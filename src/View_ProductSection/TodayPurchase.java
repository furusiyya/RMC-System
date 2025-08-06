package View_ProductSection;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import controller_ProductSection.TodayPurchase_Handler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view_MenuSection.M_UpdateMenu;

import java.awt.BorderLayout;

public class TodayPurchase {

	public JFrame frmVendorRegister;
	public JPanel panel;
	public JTable table;
	public DefaultTableModel model;
	public JTextField desField;
	private JLabel lblNewLabel_1;
	public JTextField TypeField;
	private JLabel lblNewLabel_5;
	public JTextField colorField;
	public JButton submitBtn,updateBtn,deleteBtn;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	public JTextField qField;
	public JTextField priceField;
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
	@SuppressWarnings("unused")
	public TodayPurchase(ProductSailingGUI gui) {
//		handler = new TakeBack_Handler(this,gui);
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
		frmVendorRegister = new JFrame();
		frmVendorRegister.setResizable(false);
		frmVendorRegister.setAlwaysOnTop(true);
		frmVendorRegister.setTitle("Vendor Register");
		frmVendorRegister.setType(Type.UTILITY);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1068, 503);
		TodayPurchase_Handler handler = new TodayPurchase_Handler(this,gui);
		frmVendorRegister.addWindowListener(handler);
		frmVendorRegister.setBackground(new Color(0, 51, 255));
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		frmVendorRegister.setSize(1072,529);
		frmVendorRegister.getContentPane().setLayout(null);
		
		frmVendorRegister.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		
		String[] columnTitle = {"Sr.","Description","Tpye","Color","Quantity","Unit pirce"};
		model = new DefaultTableModel(columnTitle, 0);
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(7, 248, 1039, 199);
		
		table  = new JTable(model);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
				table.setFillsViewportHeight(true);
				table.setBorder(BorderFactory.createTitledBorder(""));
				panel.setLayout(null);
				table.setLocation(21, 184);
				table.setRowHeight(30);
				table.setSize(629, 500);
				tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
				tablePanel.setLayout(null);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 1039, 199);
				tablePanel.add(scrollPane);
				panel.add(tablePanel);
				
				
				JLabel lblNewLabel = new JLabel("Color:");
			    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    lblNewLabel.setBounds(279, 116, 76, 26);
			    panel.add(lblNewLabel);
			    
			    desField = new JTextField();
			    desField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						TypeField.requestFocusInWindow();
						
					}
				});
			    desField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    desField.setBounds(385, 32, 157, 31);
			    panel.add(desField);
			    desField.setColumns(10);
			    
			    lblNewLabel_1 = new JLabel("Description:");
			    lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    lblNewLabel_1.setBounds(275, 34, 92, 26);
			    panel.add(lblNewLabel_1);
			    
			    TypeField = new JTextField();
			    TypeField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						colorField.requestFocusInWindow();
						
					}
				});
			    TypeField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    TypeField.setBounds(385, 75, 157, 31);
			    panel.add(TypeField);
			    TypeField.setColumns(10);
			    
			    lblNewLabel_5 = new JLabel("Type:");
			    lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    lblNewLabel_5.setBounds(279, 78, 76, 25);
			    panel.add(lblNewLabel_5);
			    
			    colorField = new JTextField();
			    colorField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						qField.requestFocusInWindow();
						
					}
				});
			    colorField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    colorField.setBounds(385, 116, 157, 31);
			    panel.add(colorField);
			    colorField.setColumns(10);
			    
			     updateBtn = new JButton("Update");
			     updateBtn.addActionListener(handler);
			    updateBtn.setBounds(916, 458, 130, 23);
			    panel.add(updateBtn);
			    
			    deleteBtn = new JButton("Delete");
			    deleteBtn.addActionListener(handler);
			    deleteBtn.setBounds(776, 458, 130, 23);
			    deleteBtn.setIcon(new ImageIcon(M_UpdateMenu.class.getResource("/org/jdesktop/swingx/plaf/basic/resources/error16.png")));
			    panel.add(deleteBtn);
			    
			    submitBtn = new JButton("Submit");
			    submitBtn.setIcon(new ImageIcon(M_UpdateMenu.class.getResource("/org/jdesktop/swingx/plaf/linux/resources/combo-gtk.png")));
			    submitBtn.addActionListener(handler);
			    submitBtn.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 15));
			    submitBtn.setBounds(849, 90, 171, 50);
			    panel.add(submitBtn);
			    
			    lblNewLabel_6 = new JLabel("Purchase Chart:");
			    lblNewLabel_6.setForeground(Color.GRAY);
			    lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
			    lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
			    lblNewLabel_6.setBounds(95, 5, 118, 26);
			    panel.add(lblNewLabel_6);
			    
			    lblNewLabel_7 = new JLabel("Today");
			    lblNewLabel_7.setForeground(new Color(34, 139, 34));
			    lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
			    lblNewLabel_7.setVerticalAlignment(SwingConstants.TOP);
			    lblNewLabel_7.setBounds(216, 5, 122, 26);
			    panel.add(lblNewLabel_7);
			    
			    JLabel lblQuantity = new JLabel("Quantity:");
			    lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    lblQuantity.setBounds(279, 161, 76, 26);
			    panel.add(lblQuantity);
			    
			    qField = new JTextField();
			    qField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						priceField.requestFocusInWindow();
						
					}
				});
			    qField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    qField.setColumns(10);
			    qField.setBounds(385, 159, 157, 31);
			    panel.add(qField);
			    
			    priceField = new JTextField();
			    priceField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						submitBtn.requestFocusInWindow();
						
					}
				});
			    priceField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    priceField.setColumns(10);
			    priceField.setBounds(385, 197, 157, 31);
			    panel.add(priceField);
			    
			    JLabel lblPrice = new JLabel("Unit price:");
			    lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    lblPrice.setBounds(279, 199, 76, 26);
			    panel.add(lblPrice);
			
	
	}
}
