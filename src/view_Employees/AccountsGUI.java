package view_Employees;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller_Employees.Accounts_Handler;
import model_MenuSection.DbLink;
import javax.swing.ImageIcon;



public class AccountsGUI {

//	private JFrame frame;
	public JPanel panel;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	public JTextField payField;
	public JButton saveBtn;
public JComboBox<String> box;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AccountsGUI window = new AccountsGUI();
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
	public AccountsGUI() {
		initialize();
	}
	public ArrayList<String> id = null;
	public ArrayList<String> arr = null;
	public String[] array  = null;
	public JLabel balanceField;
	public DefaultTableModel model;
	public JTable table;
	public JLabel advnaceFeild;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DbLink.connect();
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
		panel.setBounds(0, 0, 1015, 575);
//		frame = new JFrame();
		Accounts_Handler handler = new Accounts_Handler(this);
//		frame.setBackground(new Color(0, 51, 255));
//		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
//		@SuppressWarnings("unused")
//		Dimension dim = toolkit.getScreenSize();
//		frame.setSize(1029,613);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		
//		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		
		
		panel.setLayout(null);
		arr = new ArrayList<String>(0);
		id = new ArrayList<String>(0);
		try {
			ResultSet rs = DbLink.query("select id,name from geranium.employee");
			while(rs.next()){
				arr.add(rs.getString("name"));
				id.add(rs.getString("id"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		array = new String[arr.size()];
		for(int i = 0  ; i < array.length ; i++)array[i] = arr.get(i);
		box = new JComboBox<String>(array);	
		box.addActionListener(handler);
		box.setFont(new Font("Tahoma", Font.PLAIN, 13));
		box.setBounds(435, 71, 200, 37);
				panel.add(box);
				advnaceFeild = new JLabel("");
				advnaceFeild.setForeground(new Color(139, 0, 0));
				advnaceFeild.setBounds(92, 71, 309, 39);
			    advnaceFeild.setFont(new Font("Tahoma", Font.PLAIN, 24));
			    panel.add(advnaceFeild);
			    
			    lblNewLabel_6 = new JLabel("Employee:");
			    lblNewLabel_6.setBounds(42, 22, 88, 38);
			    lblNewLabel_6.setForeground(Color.GRAY);
			    lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
			    lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
			    panel.add(lblNewLabel_6);
			    
			    lblNewLabel_7 = new JLabel("ACOUNTS");
			    lblNewLabel_7.setBounds(128, 22, 135, 38);
			    lblNewLabel_7.setForeground(new Color(34, 139, 34));
			    lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
			    lblNewLabel_7.setVerticalAlignment(SwingConstants.TOP);
			    panel.add(lblNewLabel_7);
			    
			    JLabel lblSalary = new JLabel("Pay:");
			    lblSalary.setBounds(92, 177, 125, 38);
			    lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblSalary);
			    
			    payField = new JTextField();
			    payField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String str = payField.getText();
						if(str.length() > 2){
							try {
								Integer.parseInt(str);
								saveBtn.requestFocusInWindow();
							} catch (NumberFormatException e) {
								payField.setText(null);
							}
							
						}else{
							payField.setText(null);
						}
					}
				});
			    payField.setBounds(157, 178, 200, 37);
			    payField.addFocusListener(handler);
			    payField.setFont(new Font("Dialog", Font.PLAIN, 14));
			    payField.setColumns(10);
			    panel.add(payField);
			    
			    saveBtn = new JButton("OK");
			    saveBtn.setIcon(new ImageIcon(AccountsGUI.class.getResource("/icons/apply.png")));
			    saveBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			    saveBtn.addActionListener(handler);
			    saveBtn.setBounds(510, 184, 125, 29);
			    panel.add(saveBtn);
			    
			    balanceField = new JLabel("");
			    balanceField.setForeground(new Color(139, 0, 0));
			    balanceField.setFont(new Font("Tahoma", Font.PLAIN, 24));
			    balanceField.setBounds(92, 121, 309, 39);
			    panel.add(balanceField);
			    
			    
			    String[] columnTitle = {"Date","Payed Amount"};
				model = new DefaultTableModel(columnTitle, 0);
				JPanel tablePanel = new JPanel();
				tablePanel.setBounds(92, 254, 616, 284);
				
				table  = new JTable(model);
				table.setRowHeight(30);
				table.setFont(new Font("Dialog", Font.PLAIN, 14));
				table.setEnabled(false);
				table.setRowSelectionAllowed(false);
						table.setFillsViewportHeight(true);
						panel.setLayout(null);
						table.setLocation(21, 184);
						table.setSize(629, 500);
						tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
						tablePanel.setLayout(null);
						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(0, 0, 616, 284);
						tablePanel.add(scrollPane);
						panel.add(tablePanel);
			
	}
}
