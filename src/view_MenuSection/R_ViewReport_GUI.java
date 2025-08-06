package view_MenuSection;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;

import model_MenuSection.DbLink;

import javax.swing.JTextArea;


public class R_ViewReport_GUI {

//	private JFrame frame;
	public JPanel panel;
	public DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					R_ViewReport_GUI window = new R_ViewReport_GUI();
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
	public R_ViewReport_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		frame.getContentPane().add(panel);
		panel.setBackground(new Color(240 ,240 , 240));
				panel.setLayout(null);
				
				panel.setLayout(null);
				Object[] mon = {"January","Febraury","March","April","May","June",
								"July","August","September","November","October","November","December"};
				Integer[] array = new Integer[45];
				int num = 2015;
				for(int i = 0 ; i < array.length;i++)
					{array[i]=num;num++;}
				
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(BorderFactory.createTitledBorder("Select Month and Year Below"));
				panel_1.setBounds(59, 24, 599, 547);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				monthBox = new JComboBox(mon);
				monthBox.setBounds(53, 23, 106, 30);
				panel_1.add(monthBox);
				monthBox.setOpaque(false);
				yearBox = new JComboBox(array);
				yearBox.setBounds(159, 23, 74, 30);
				panel_1.add(yearBox);
				yearBox.setOpaque(false);
				
				JPanel tablePanel = new JPanel();
				tablePanel.setBounds(53, 70, 504, 454);
				panel_1.add(tablePanel);
				tablePanel.setBorder(BorderFactory.createTitledBorder(""));	
				tablePanel.setLayout(null);
				cash = new JLabel("Revenue: ");
				cash.setBounds(51, 30, 416, 34);
				tablePanel.add(cash);
				cash.setFont(new Font("Dialog", Font.PLAIN, 14));
				
				discount = new JLabel("Discount: ");
				discount.setFont(new Font("Dialog", Font.PLAIN, 14));
				discount.setBounds(51, 76, 416, 34);
				tablePanel.add(discount);
				
				refund = new JLabel("Refund: ");
				refund.setFont(new Font("Dialog", Font.PLAIN, 14));
				refund.setBounds(51, 122, 416, 34);
				tablePanel.add(refund);
				
				purchase = new JLabel("Purchase: ");
				purchase.setFont(new Font("Dialog", Font.PLAIN, 14));
				purchase.setBounds(51, 168, 416, 34);
				tablePanel.add(purchase);
				
				miscell = new JLabel("Miscellaneous: ");
				miscell.setFont(new Font("Dialog", Font.PLAIN, 14));
				miscell.setBounds(51, 214, 416, 34);
				tablePanel.add(miscell);
				
				utilit = new JLabel("Utilities: ");
				utilit.setFont(new Font("Dialog", Font.PLAIN, 14));
				utilit.setBounds(51, 260, 416, 34);
				tablePanel.add(utilit);
				
				result = new JTextArea();
				result.setForeground(Color.WHITE);
				result.setFont(new Font("Dialog", Font.PLAIN, 14));
				result.setFocusable(false);
				result.setEditable(false);
				result.setLineWrap(true);
				result.setWrapStyleWord(true);
				result.setBackground(new Color(65, 105, 225));
				result.setBounds(12, 317, 480, 90);
				tablePanel.add(result);
				
					yearBox.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							int mon = monthBox.getSelectedIndex()+1;
							try {
								ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.UTILITY WHERE EXTRACT(MONTH FROM MONTH)="+(mon+1)
										+" AND  EXTRACT(YEAR FROM MONTH) ="+yearBox.getSelectedItem()+"");
								int utility = 0;
								String str = "";
								int total = 0;
								while(rs.next()){
									str = "Electricity: "+rs.getInt("ELEC");
									utility =rs.getInt("ELEC");
									str += " Gass: "+rs.getInt("GASS");
									utility+=rs.getInt("GASS");
									str += " Phone: "+rs.getInt("PH");
									utility+=rs.getInt("PH");
									str += " Tax: "+rs.getInt("TAX");
									utility+=rs.getInt("TAX");
									str += " Water: "+rs.getInt("WATER");
									utility+=rs.getInt("WATER");
									str += " Other: "+rs.getInt("Other");
									utility+=rs.getInt("OTHER");
									cash.setText("Revenue: "+(total=rs.getInt("TOTALSALE")));
									discount.setText("Discount: "+rs.getInt("DISCOUNT"));
									refund.setText("REFUND: "+rs.getInt("REFUND"));
									total = total-rs.getInt("PURCHASE");
									purchase.setText("Purchase: "+rs.getInt("PURCHASE"));
									total = total-rs.getInt("MISCELLANEOUS");
									miscell.setText("Miscellaneous: "+(rs.getInt("MISCELLANEOUS")));
								}
								utilit.setText("Utility Bills: "+utility);
								total = total-utility;
								str += "\n\n	------      Profit  =  "+total+"      ------";
								result.setText(str);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
				monthBox.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						int mon = monthBox.getSelectedIndex()+1;
						try {
							ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.UTILITY WHERE EXTRACT(MONTH FROM MONTH)="+(mon+1)
									+" AND  EXTRACT(YEAR FROM MONTH) ="+yearBox.getSelectedItem()+"");
							int utility = 0;
							String str = "";
							int total = 0;
							while(rs.next()){
								str = "Electricity: "+rs.getInt("ELEC");
								utility =rs.getInt("ELEC");
								str += " Gass: "+rs.getInt("GASS");
								utility+=rs.getInt("GASS");
								str += " Phone: "+rs.getInt("PH");
								utility+=rs.getInt("PH");
								str += " Tax: "+rs.getInt("TAX");
								utility+=rs.getInt("TAX");
								str += " Water: "+rs.getInt("WATER");
								utility+=rs.getInt("WATER");
								str += " Other: "+rs.getInt("Other");
								utility+=rs.getInt("OTHER");
								cash.setText("Revenue: "+(total=rs.getInt("TOTALSALE")));
								discount.setText("Discount: "+rs.getInt("DISCOUNT"));
								refund.setText("REFUND: "+rs.getInt("REFUND"));
								total = total-rs.getInt("PURCHASE");
								purchase.setText("Purchase: "+rs.getInt("PURCHASE"));
								total = total-rs.getInt("MISCELLANEOUS");
								miscell.setText("Miscellaneous: "+(rs.getInt("MISCELLANEOUS")));
							}
							utilit.setText("Utility Bills: "+utility);
							total = total-utility;
							str += "\n\n	------      Profit  =  "+total+"      ------";
							result.setText(str);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
	}
	@SuppressWarnings("rawtypes")
	JComboBox yearBox,monthBox;
	JLabel cash,discount,refund,purchase,miscell,utilit;
	JTextArea result;
}