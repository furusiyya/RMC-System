package view_Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import View_ProductSection.ProductSailingGUI;
import controller_MainAndSailSection.OptionPane_Handler;

import java.awt.CardLayout;

public class OptionPane_GUI {

	public JFrame frame;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OptionPane_GUI window = new OptionPane_GUI();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public OptionPane_GUI(ProductSailingGUI gui) {
		initialize(gui);
		frame.setVisible(true);
		list.requestFocusInWindow();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public JPanel passPanel,addPanel,allertPanel,cardPanel;
	@SuppressWarnings("rawtypes")
	public JList list;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(ProductSailingGUI gui) {
		frame = new JFrame();
		frame.setBounds(100, 100, 586, 553);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setType(Type.UTILITY);
		OptionPane_Handler handler = new OptionPane_Handler(this,gui);
		frame.addWindowListener(handler);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setBounds(0, 0, 158, 514);
		frame.getContentPane().add(panel);
		
		
		String[] buttons = {"Password","Address"};
		panel.setLayout(null);
		list = new JList(buttons);
		list.setForeground(Color.DARK_GRAY);
		list.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		list.addListSelectionListener(handler);
		list.setSelectionBackground(new Color(124,123,173));
		list.setSelectionForeground(Color.WHITE);
		list.setBounds(10, 5, 148, 498);
		panel.add(list);
		cardPanel = new JPanel();
		cardPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		cardPanel.setBounds(157, 0, 413, 514);
		frame.getContentPane().add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
		
	}

}
