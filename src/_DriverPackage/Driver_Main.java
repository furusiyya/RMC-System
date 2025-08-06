package _DriverPackage;

import java.awt.EventQueue;
import view_Main.MainGUI;

public class Driver_Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainGUI();
//				System.out.println(getSerialNumber());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

}
