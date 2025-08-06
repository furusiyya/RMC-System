package view_Report;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.BevelBorder;

import java.awt.CardLayout;

import View_ProductSection.ProductSailingGUI;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JList;

import java.awt.Window.Type;

public class ReportGUI {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReportGUI window = new ReportGUI();
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
	public ReportGUI(ProductSailingGUI gui) {
		this.gui = gui;
		initialize();
	}
ProductSailingGUI gui;
	public JPanel cardPanel;
	/**
	 * Initialize the contents of the frame.
	 */
public	JLayeredPane panel;
	@SuppressWarnings("rawtypes")
	public JList list;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent arg0) {
				gui.frame.setAlwaysOnTop(true);
				frame.setVisible(false);
				frame.dispose();
				gui.frame.setEnabled(true);
				gui.frame.setAlwaysOnTop(false);
				gui.cField.requestFocusInWindow();
			}
			
			public void windowClosed(WindowEvent arg0) {
			}
			
			public void windowActivated(WindowEvent arg0) {
				}
		});
		frame.setTitle("Reports");
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1035, 621);
		frame.getContentPane().setLayout(null);
		
		panel= new JLayeredPane();
		panel.setBounds(0, 0, 1019, 582);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(21, 21, 200, 570);
		panel_1.setBackground(Color.WHITE);
		cardPanel = new JPanel();
		cardPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cardPanel.setBounds(231, 21, 1100, 570);
		cardPanel.setBackground(Color.WHITE);
		panel.add(panel_1,1);
//		BufferedImage myPicture;
//		try {
//			myPicture = ImageIO.read(new File("reports-prvw.png"));
//			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//			picLabel.setBounds(0, -25, 200, 236);
//			//panel_1.add(picLabel);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String[] buttons = {"Invoice register","Daily Sale register","Monthly Sale Register","Vendor register","Sale by Customer Register","Miscellaneous Expenses"};
		list = new JList(buttons);
		list.setForeground(Color.DARK_GRAY);
		list.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel_1.setLayout(null);
		list.setSelectedIndex(0);
		list.setSelectionBackground(new Color(124,123,173));
		list.setSelectionForeground(Color.WHITE);
		list.setBounds(10, 8, 190, 490);
		panel_1.add(list);
		panel.add(cardPanel,1);
		cardPanel.setLayout(new CardLayout(0, 0));
		list.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				if( list.getSelectedIndex() == 0){
					try {
						 cardPanel.remove(test);
						test = new SaleChartGUI().reportSalePanel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					} catch (NullPointerException e1){
						test = new SaleChartGUI().reportSalePanel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					}
				}
				
				else if( list.getSelectedIndex() == 3){
					try {
						 cardPanel.remove(test);
						test = new Purchase_GUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					} catch (NullPointerException e1) {
						test = new Purchase_GUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					}
				}
				
				
				else if( list.getSelectedIndex() == 1){
					try {
						 cardPanel.remove(test);
						test = new SaleReportGUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					} catch (NullPointerException e1) {
						test = new SaleReportGUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					}
				}
				
				else if( list.getSelectedIndex() == 2){
					try {
						 cardPanel.remove(test);
						test = new MonthlyReport_GUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					} catch (NullPointerException e1) {
						test = new MonthlyReport_GUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					}
				}
				
				else if( list.getSelectedIndex() == 4){
					try {
						 cardPanel.remove(test);
						test = new SaleByCustomerGUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					} catch (NullPointerException e1) {
						test = new SaleByCustomerGUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					}
				}
				else if( list.getSelectedIndex() == 5){
					try {
						 cardPanel.remove(test);
						test = new MiscellaneousReportGUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					} catch (NullPointerException e1) {
						test = new MiscellaneousReportGUI().panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					}
				}
				
				
			}
		});	
	}
	JPanel test = null;
}
