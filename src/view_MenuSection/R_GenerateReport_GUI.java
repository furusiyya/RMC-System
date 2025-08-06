package view_MenuSection;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import controller_MenuSection.R_GenerateReport_Handler;
import javax.swing.JTextArea;


public class R_GenerateReport_GUI {

//	private JFrame frame;
	public JPanel panel;
	public JButton saveBtn;
	public DefaultTableModel model;
	public JLabel lblNewLabel;
	public JLabel lblGass;
	public JLabel lblPhon;
	public JLabel lblTax;
	public JLabel lblWater;
	public JLabel lblOther;
	public JTextField elecField;
	public JTextField gassField;
	public JTextField phField;
	public JTextField taxField;
	public JTextField waterField;
	public JTextField otherField;
	private JPanel panel_1;
	private JPanel panel_2;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ModifyMenu window = new ModifyMenu();
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
	public R_GenerateReport_GUI() {
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
//		frame = new JFrame();
//		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
//		Dimension dim = toolkit.getScreenSize();
//		frame.setSize(dim.width,dim.height);
		panel = new JPanel();
		panel.setBounds(0, 12, 906, 590);
		panel.setBorder(UIManager.getBorder("Please Use this facility only once in month"));
//		frame.setBackground(SystemColor.windowBorder);
//		
		R_GenerateReport_Handler handler = new R_GenerateReport_Handler(this); 
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		frame.getContentPane().add(panel);
		panel.setBackground(new Color(240 ,240 , 240));
				panel.setLayout(null);
				
				panel_2 = new JPanel();
				panel_2.setBorder(BorderFactory.createTitledBorder("Generate Final Report"));
				panel_2.setBounds(59, 24, 599, 547);
				panel.add(panel_2);
				panel_2.setLayout(null);
				
				JTextArea txtrGenerateOverallReport = new JTextArea();
				txtrGenerateOverallReport.setBounds(53, 29, 505, 86);
				panel_2.add(txtrGenerateOverallReport);
				txtrGenerateOverallReport.setFocusable(false);
				txtrGenerateOverallReport.setEditable(false);
				txtrGenerateOverallReport.setBackground(new Color(255, 204, 51));
				txtrGenerateOverallReport.setFont(new Font("Dialog", Font.PLAIN, 16));
				txtrGenerateOverallReport.setLineWrap(true);
				txtrGenerateOverallReport.setWrapStyleWord(true);
				txtrGenerateOverallReport.setText("Generate overall report of your last month by providing amounts of utility bills (of last month). Select View Report on left side to view final reports.\nCarefull! This facility works for last month Only.");
				
						panel_1 = new JPanel();
						panel_1.setBounds(181, 127, 377, 367);
						panel_2.add(panel_1);
						panel_1.setBorder(BorderFactory.createTitledBorder("Enter utility bills"));
						panel_1.setLayout(null);
						
						lblNewLabel = new JLabel("Electricity");
						lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
						lblNewLabel.setBounds(26, 32, 60, 24);
						panel_1.add(lblNewLabel);
						
						lblGass = new JLabel("Gass");
						lblGass.setFont(new Font("Arial", Font.PLAIN, 14));
						lblGass.setBounds(26, 68, 60, 24);
						panel_1.add(lblGass);
						
						lblPhon = new JLabel("Phon#");
						lblPhon.setFont(new Font("Arial", Font.PLAIN, 14));
						lblPhon.setBounds(26, 111, 60, 24);
						panel_1.add(lblPhon);
						
						lblTax = new JLabel("Tax");
						lblTax.setFont(new Font("Arial", Font.PLAIN, 14));
						lblTax.setBounds(26, 151, 60, 24);
						panel_1.add(lblTax);
						
						lblWater = new JLabel("Water");
						lblWater.setFont(new Font("Arial", Font.PLAIN, 14));
						lblWater.setBounds(26, 192, 60, 24);
						panel_1.add(lblWater);
						
						lblOther = new JLabel("Other");
						lblOther.setFont(new Font("Arial", Font.PLAIN, 14));
						lblOther.setBounds(26, 229, 60, 24);
						panel_1.add(lblOther);
						
						elecField = new JTextField();
						elecField.setFont(new Font("Dialog", Font.PLAIN, 14));
						
						elecField.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
									gassField.requestFocusInWindow();
								
							}
						});
						elecField.setBounds(159, 31, 187, 28);
						panel_1.add(elecField);
						elecField.setColumns(10);
						
						gassField = new JTextField();
						gassField.setFont(new Font("Dialog", Font.PLAIN, 14));
						gassField.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
									phField.requestFocusInWindow();
							}
						});
						gassField.setColumns(10);
						gassField.setBounds(159, 71, 187, 28);
						panel_1.add(gassField);
						
						phField = new JTextField();
						phField.setFont(new Font("Tahoma", Font.PLAIN, 14));
						phField.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
									taxField.requestFocusInWindow();
								
							}
						});
						phField.setColumns(10);
						phField.setBounds(159, 111, 187, 28);
						phField.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
									taxField.requestFocusInWindow();
							}
						});
						panel_1.add(phField);
						
						taxField = new JTextField();
						taxField.setFont(new Font("Tahoma", Font.PLAIN, 14));
						taxField.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
									waterField.requestFocusInWindow();
							}
						});
						taxField.setColumns(10);
						taxField.setBounds(159, 151, 187, 28);
						panel_1.add(taxField);
						
						waterField = new JTextField();
						waterField.setFont(new Font("Tahoma", Font.PLAIN, 14));
						waterField.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
									otherField.requestFocusInWindow();
							}
						});
						waterField.setColumns(10);
						waterField.setBounds(159, 191, 187, 28);
						panel_1.add(waterField);
						
						otherField = new JTextField();
						otherField.setFont(new Font("Tahoma", Font.PLAIN, 14));
						otherField.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
									saveBtn.requestFocusInWindow();
							}
						});
						otherField.setColumns(10);
						otherField.setBounds(159, 228, 187, 28);
						panel_1.add(otherField);
						
						saveBtn = new JButton("Save");
						saveBtn.setIcon(new ImageIcon(R_GenerateReport_GUI.class.getResource("/icons/apply.png")));
						saveBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
						saveBtn.setBounds(237, 295, 109, 23);
						saveBtn.addActionListener(handler);
						panel_1.add(saveBtn);
				
	}
}
