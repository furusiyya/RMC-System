package view_MenuSection;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.BevelBorder;

import java.awt.CardLayout;
import javax.swing.JList;

public class R_RecordGUI {

//	private JFrame frame;

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
	R_GenerateReport_GUI generateReport = null;
	R_ViewReport_GUI viewReport = null;
	/**
	 * Create the application.
	 */
	public R_RecordGUI() {
		initialize();
		generateReport = new R_GenerateReport_GUI();
		viewReport = new R_ViewReport_GUI();
	}


	public JPanel cardPanel;
	/**
	 * Initialize the contents of the frame.
	 */
public	JLayeredPane panel;
	@SuppressWarnings("rawtypes")
	public JList list;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 1109, 671);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
		
		panel= new JLayeredPane();
		panel.setBounds(0, 0, 606, 606);
//		frame.getContentPane().add(panel);
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
		String[] buttons = {"Generate Report","View Monthly Reports"};
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
						 test = generateReport.panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					} catch (NullPointerException e1){
						test = generateReport.panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					}
				}
				if( list.getSelectedIndex() == 1){
					try {
						 cardPanel.remove(test);
						 test = viewReport.panel;
						 cardPanel.add(test);
						 cardPanel.revalidate();
						 cardPanel.setVisible(true);
					} catch (NullPointerException e1){
						test = viewReport.panel;
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
