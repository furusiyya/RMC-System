package view_Employees;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import model_MenuSection.DbLink;

import controller_Employees.addEmployee_Handler;
import javax.swing.ImageIcon;


public class AddEmployee {

	private JFrame frame;
	public JPanel panel;
	public DefaultTableModel model;
	public JTextField idField;
	public JTextField nameField;
	public JTextField cnicField;
	public JButton addBtn;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	public JTextField phField;
	public JTextField addField;
	public JTextField salaryField;
	public JTextField desigField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddEmployee window = new AddEmployee();
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
	public AddEmployee() {
		initialize();
	}

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
		frame = new JFrame();
		addEmployee_Handler handler = new addEmployee_Handler(this);
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		@SuppressWarnings("unused")
		Dimension dim = toolkit.getScreenSize();
		frame.setSize(1029,613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.getContentPane().add(panel);
		panel.setBackground(Color.WHITE);
		
		String[] columnTitle = {"Sr.","ID","Description","Price"};
		model = new DefaultTableModel(columnTitle, 0);
				panel.setLayout(null);
				
				
				JLabel lblNewLabel = new JLabel("Employee id");
				lblNewLabel.setBounds(250, 82, 125, 38);
			    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblNewLabel);
			     idField = new JTextField();
			     idField.addFocusListener(handler);
			    idField.setBounds(398, 82, 200, 37);
			    idField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String str = idField.getText();
						if(str.length() > 3){
								nameField.requestFocusInWindow();
						}else{
							idField.setText(null);
						}
					}
				});
			    idField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    panel.add(idField);
			    idField.setColumns(10);
			    
			    nameField = new JTextField();
			    nameField.addFocusListener(handler);
			    nameField.setBounds(398, 130, 200, 37);
			    nameField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String str = nameField.getText();
						if(str.length() > 3){
								cnicField.requestFocusInWindow();
						}else{
							nameField.setText(null);
						}
						
					}
				});
			    nameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    panel.add(nameField);
			    nameField.setColumns(10);
			    
			    cnicField = new JTextField();
			    cnicField.addFocusListener(handler);
			    cnicField.setBounds(398, 178, 200, 37);
			    cnicField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String str = cnicField.getText();
						if(str.length() == 13){
							try {
								Long.parseLong(str);
								phField.requestFocusInWindow();
							} catch (NumberFormatException e) {
								cnicField.setText(null);
							}
							
						}else{
							cnicField.setText(null);
						}
						
					}
				});
			    cnicField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    panel.add(cnicField);
			    cnicField.setColumns(10);
			    
			     addBtn = new JButton("ADD");
			     addBtn.setIcon(new ImageIcon(AddEmployee.class.getResource("/icons/btnplus.png")));
			     addBtn.setBounds(473, 450, 125, 29);
			     addBtn.addActionListener(handler);
			    panel.add(addBtn);
			    
			    lblNewLabel_6 = new JLabel("Recruit:");
			    lblNewLabel_6.setBounds(105, 22, 81, 38);
			    lblNewLabel_6.setForeground(Color.GRAY);
			    lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
			    lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
			    panel.add(lblNewLabel_6);
			    
			    lblNewLabel_7 = new JLabel("Add record");
			    lblNewLabel_7.setBounds(196, 22, 122, 38);
			    lblNewLabel_7.setForeground(new Color(34, 139, 34));
			    lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
			    lblNewLabel_7.setVerticalAlignment(SwingConstants.TOP);
			    panel.add(lblNewLabel_7);
			    
			    JLabel lblEmployeeName = new JLabel("Name");
			    lblEmployeeName.setBounds(250, 130, 125, 38);
			    lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblEmployeeName);
			    
			    JLabel lblEmployeeCnic = new JLabel("CNIC");
			    lblEmployeeCnic.setBounds(250, 178, 125, 38);
			    lblEmployeeCnic.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblEmployeeCnic);
			    
			    JLabel lblEmployeeNumber = new JLabel("Employee Ph#");
			    lblEmployeeNumber.setBounds(250, 226, 125, 38);
			    lblEmployeeNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblEmployeeNumber);
			    
			    JLabel lblEmployeeAddress = new JLabel("Address");
			    lblEmployeeAddress.setBounds(250, 274, 125, 38);
			    lblEmployeeAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblEmployeeAddress);
			    
			    JLabel lblSalary = new JLabel("Salary");
			    lblSalary.setBounds(250, 322, 125, 38);
			    lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblSalary);
			    
			    phField = new JTextField();
			    phField.addFocusListener(handler);
			    phField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String str = phField.getText();
						if(str.length() >= 7){
							try {
								Long.parseLong(str);
								addField.requestFocusInWindow();
							} catch (NumberFormatException e) {
								phField.setText(null);
							}
							
						}else{
							phField.setText(null);
						}
					}
				});
			    phField.setBounds(398, 226, 200, 37);
			    phField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    phField.setColumns(10);
			    panel.add(phField);
			    
			    addField = new JTextField();
			    addField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String str = addField.getText();
						if(str.length() > 5){
								salaryField.requestFocusInWindow();
						}else{
							addField.setText(null);
						}
					}
				});
			    addField.setBounds(398, 274, 200, 37);
			    addField.addFocusListener(handler);
			    addField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    addField.setColumns(10);
			    panel.add(addField);
			    
			    salaryField = new JTextField();
			    salaryField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String str = salaryField.getText();
						if(str.length() > 2){
							try {
								Integer.parseInt(str);
								desigField.requestFocusInWindow();
							} catch (NumberFormatException e) {
								salaryField.setText(null);
							}
							
						}else{
							salaryField.setText(null);
						}
					}
				});
			    salaryField.setBounds(398, 322, 200, 37);
			    salaryField.addFocusListener(handler);
			    salaryField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    salaryField.setColumns(10);
			    panel.add(salaryField);
			    
			    desigField = new JTextField();
			    desigField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String str = desigField.getText();
						if(str.length() >3){
								addBtn.requestFocusInWindow();
							
						}else{
							desigField.setText(null);
						}
					}
				});
			    desigField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    desigField.addFocusListener(handler);
			    desigField.setColumns(10);
			    desigField.setBounds(398, 370, 200, 37);
			    panel.add(desigField);
			    PromptSupport.setPrompt("XXXXXXXXXXXXX (without '-')", cnicField);
			    PromptSupport.setPrompt("XXXXXXXXXXX (without '-')", phField);
			 
			    JLabel lblDesignation = new JLabel("Designation");
			    lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    lblDesignation.setBounds(250, 370, 125, 38);
			    panel.add(lblDesignation);
			
	}
}
