package view_Main;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import View_ProductSection.ProductSailingGUI;

public class selectPass_GUI {

	public JPanel panel;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					selectPass_GUI window = new selectPass_GUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
	public selectPass_GUI(OptionPane_GUI oGui,ProductSailingGUI pGui) {
		initialize(oGui,pGui);
//		panel.setVisible(true);
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(OptionPane_GUI oGui,ProductSailingGUI pGui) {
		
//		frame = new JFrame();
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBounds(157, 0, 413, 514);
//		frame.getContentPane().add(panel);
		 JTabbedPane tabbedPane = new JTabbedPane();
		    tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		    tabbedPane.addTab("<html><body><table width='80' hieght='30'>Admin</table></body></html>",new PasswordGUI(true,oGui,pGui).panel_1);
		    tabbedPane.addTab("<html><body><table width='80' hieght='30'>User</table></body></html>",new PasswordGUI(false,oGui,pGui).panel_1);
		    panel.add(tabbedPane,BorderLayout.CENTER);
		
	}
}
