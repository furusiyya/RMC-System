package view_Employees;

import java.awt.Color;

import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.jdesktop.swingx.prompt.PromptSupport;

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

import model_MenuSection.DbLink;

import controller_Employees.viewEmployee_Handler;
import javax.swing.ImageIcon;


public class ViewEmployee {

//	private JFrame frame;
	public JPanel panel;
	public JTextField idField;
	public JTextField nameField;
	public JTextField cnicField;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	public JTextField phField;
	public JTextField addField;
	public JTextField salaryField;
	public JTextField desigField;
	public JButton deleteBtn,saveBtn;
public JComboBox<String> box;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewEmployee window = new ViewEmployee();
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
	public ViewEmployee() {
		initialize();
	}
	public ArrayList<String> id = null;
	public ArrayList<String> arr = null;
	public String[] array  = null;
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
		viewEmployee_Handler handler = new viewEmployee_Handler(this);
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
		box.setBounds(495, 80, 200, 37);
				panel.add(box);
				JLabel lblNewLabel = new JLabel("Employee id");
				lblNewLabel.setBounds(134, 80, 125, 38);
			    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblNewLabel);
			     idField = new JTextField();
			     idField.addFocusListener(handler);
			    idField.setBounds(250, 81, 200, 37);
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
			    idField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			    panel.add(idField);
			    idField.setColumns(10);
			    
			    nameField = new JTextField();
			    nameField.addFocusListener(handler);
			    nameField.setBounds(250, 130, 200, 37);
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
			    nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			    panel.add(nameField);
			    nameField.setColumns(10);
			    
			    cnicField = new JTextField();
			    cnicField.addFocusListener(handler);
			    cnicField.setBounds(250, 178, 200, 37);
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
			    cnicField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			    panel.add(cnicField);
			    cnicField.setColumns(10);
			    
			    lblNewLabel_6 = new JLabel("Employee");
			    lblNewLabel_6.setBounds(42, 22, 89, 38);
			    lblNewLabel_6.setForeground(Color.GRAY);
			    lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
			    lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
			    panel.add(lblNewLabel_6);
			    
			    lblNewLabel_7 = new JLabel("VIew,Edit & Delete");
			    lblNewLabel_7.setBounds(135, 22, 156, 38);
			    lblNewLabel_7.setForeground(new Color(34, 139, 34));
			    lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
			    lblNewLabel_7.setVerticalAlignment(SwingConstants.TOP);
			    panel.add(lblNewLabel_7);
			    
			    JLabel lblEmployeeName = new JLabel("Name");
			    lblEmployeeName.setBounds(134, 130, 125, 38);
			    lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblEmployeeName);
			    
			    JLabel lblEmployeeCnic = new JLabel("CNIC");
			    lblEmployeeCnic.setBounds(134, 178, 125, 38);
			    lblEmployeeCnic.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblEmployeeCnic);
			    
			    JLabel lblEmployeeNumber = new JLabel("Employee Ph#");
			    lblEmployeeNumber.setBounds(134, 226, 125, 38);
			    lblEmployeeNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblEmployeeNumber);
			    
			    JLabel lblEmployeeAddress = new JLabel("Address");
			    lblEmployeeAddress.setBounds(134, 274, 125, 38);
			    lblEmployeeAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    panel.add(lblEmployeeAddress);
			    
			    JLabel lblSalary = new JLabel("Salary");
			    lblSalary.setBounds(134, 322, 125, 38);
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
			    phField.setBounds(250, 226, 200, 37);
			    phField.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
			    addField.setBounds(250, 274, 200, 37);
			    addField.addFocusListener(handler);
			    addField.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
			    salaryField.setBounds(250, 322, 200, 37);
			    salaryField.addFocusListener(handler);
			    salaryField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			    salaryField.setColumns(10);
			    panel.add(salaryField);
			    
			    desigField = new JTextField();
			    desigField.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String str = desigField.getText();
						if(str.length() >3){
								saveBtn.requestFocusInWindow();
							
						}else{
							desigField.setText(null);
						}
					}
				});
			    desigField.setFont(new Font("Tahoma", Font.PLAIN, 14));
			    desigField.addFocusListener(handler);
			    desigField.setColumns(10);
			    desigField.setBounds(250, 370, 200, 37);
			    panel.add(desigField);
			    PromptSupport.setPrompt("XXXXXXXXXXXXX (without '-')", cnicField);
			    PromptSupport.setPrompt("XXXXXXXXXXX (without '-')", phField);
			 
			    JLabel lblDesignation = new JLabel("Designation");
			    lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 16));
			    lblDesignation.setBounds(134, 370, 125, 38);
			    panel.add(lblDesignation);
			    
			    saveBtn = new JButton("SAVE");
			    saveBtn.setIcon(new ImageIcon(ViewEmployee.class.getResource("/icons/apply.png")));
			    saveBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			    saveBtn.addActionListener(handler);
			    saveBtn.setBounds(570, 184, 125, 29);
			    panel.add(saveBtn);
			    
			    deleteBtn = new JButton("DELETE");
			    deleteBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
			    deleteBtn.setIcon(new ImageIcon(ViewEmployee.class.getResource("/icons/button_cancel.png")));
			    deleteBtn.addActionListener(handler);
			    deleteBtn.setBounds(570, 233, 125, 29);
			    panel.add(deleteBtn);
			
	}
}
