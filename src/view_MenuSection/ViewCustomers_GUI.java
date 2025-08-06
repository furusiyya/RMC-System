package view_MenuSection;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import controller_MenuSection.ViewCustomers_Handler;


public class ViewCustomers_GUI {

//	private JFrame frame;
	public JPanel panel;
	public JTable table;
	public JButton saveBtn,deleteBtn;
	public DefaultTableModel model;
	public JTextField idField;

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
	public ViewCustomers_GUI() {
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
//		frame = new JFrame();
//		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
//		Dimension dim = toolkit.getScreenSize();
//		frame.setSize(dim.width,dim.height);
		panel = new JPanel();
		panel.setBounds(0, 12, 902, 594);
		panel.setBorder(UIManager.getBorder("Search to edit"));
//		frame.setBackground(SystemColor.windowBorder);
//		
		ViewCustomers_Handler handler = new ViewCustomers_Handler(this); 
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		frame.getContentPane().add(panel);
		panel.setBackground(new Color(240 ,240 , 240));
		
		String[] columnTitle = {"Sr.","Customer Id","Name","Address"};
		model = new DefaultTableModel(columnTitle, 0){
			public boolean isCellEditable(int row, int column) {
			    switch(column){
			    case 2:return column == 2;
			    default:return column == 3;	
			    }   
				}
			
			};
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(71, 73, 831, 467);
		tablePanel.setLayout(new BorderLayout());
		
		table  = new JTable(model);
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
				table.setFillsViewportHeight(true);
				table.setLocation(21, 184);
				table.setSize(629, 500);
				table.setRowHeight(30);
				tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);
				tablePanel.add(new JScrollPane(table),BorderLayout.CENTER);
				
				JLabel lblNewLabel = new JLabel("Search by Id to edit");
				lblNewLabel.setBounds(71, 35, 138, 26);
				lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
				
				idField = new JTextField();
				idField.setBounds(216, 34, 157, 30);
				idField.addKeyListener(handler);
				idField.setFont(new Font("Dialog", Font.PLAIN, 14));
				idField.setColumns(10);
				
				saveBtn = new JButton("SAVE");
				saveBtn.setIcon(new ImageIcon(ViewCustomers_GUI.class.getResource("/icons/apply.png")));
				saveBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
				saveBtn.setBounds(760, 552, 130, 23);
				saveBtn.addActionListener(handler);
				
				deleteBtn = new JButton("DELETE");
				deleteBtn.setFont(new Font("Dialog", Font.PLAIN, 14));
				deleteBtn.setBounds(618, 551, 130, 25);
				deleteBtn.setIcon(new ImageIcon(ViewCustomers_GUI.class.getResource("/org/jdesktop/swingx/plaf/basic/resources/error16.png")));
				deleteBtn.addActionListener(handler);
				panel.setLayout(null);
				panel.add(lblNewLabel);
				panel.add(idField);
				panel.add(tablePanel);
				panel.add(saveBtn);
				panel.add(deleteBtn);
				
				//handler.loadAll();
	}
}
