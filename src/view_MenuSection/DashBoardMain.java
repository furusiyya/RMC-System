package view_MenuSection;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.CompoundBorder;





import view_Employees.EmployeeGUI;
import java.awt.SystemColor;
public class DashBoardMain {

//	public JFrame frame;
	private JPanel panel_1;
	public JPanel panel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StockMainn window = new StockMainn();
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
	public DashBoardMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
//		frame = new JFrame();
	    panel = new JPanel();
	    panel.setBackground(SystemColor.controlHighlight);
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
//		 Toolkit toolkit =  Toolkit.getDefaultToolkit ();
//			Dimension dim = toolkit.getScreenSize();
//		    frame.setSize(dim.width,dim.height);
//		    panel.setSize(dim.width,dim.height);
//		 frame.setBounds(0, 0, 1101, 662);
		 panel.setBounds(0, 0, 1101, 662);
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.getContentPane().setLayout(null);
//	    frame.getContentPane().add(panel);
	    
	    
	    panel_1 = new JPanel();
	    panel_1.setBounds(0, 17, 1351, 640);
	    panel_1.setLayout(new BorderLayout());
	    panel.setLayout(null);
	    JTabbedPane tabbedPane = new JTabbedPane();
	    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    tabbedPane.addTab("<html><body><table width='150'>Menu</table></body></html>",new M_MenuGUI().panel);
	    tabbedPane.addTab("<html><body><table width='150'>Edit Catalog</table></body></html>",new ModifyMenu().panel);
	    tabbedPane.addTab("<html><body><table width='150'>Employees</table></body></html>",new EmployeeGUI().panel);
	    tabbedPane.addTab("<html><body><table width='150'>Customers</table></body></html>",new ViewCustomers_GUI().panel);
	    tabbedPane.addTab("<html><body><table width='150'>Monthly Register</table></body></html>",new R_RecordGUI().panel);
	    panel_1.add(tabbedPane,BorderLayout.CENTER);
	    panel_1.setBorder(new CompoundBorder());
	    panel.add(panel_1);
	    
	    panel.setVisible(true);
	   // frame.setVisible(true);
		
	}
}
