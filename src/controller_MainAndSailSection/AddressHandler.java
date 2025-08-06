package controller_MainAndSailSection;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import View_ProductSection.ProductSailingGUI;
import model_MainAndSailRecords.Address;
import model_MainSailRecordFililngSection.AdressFillingSection;
import view_Main.AddressGUI;
import view_Main.OptionPane_GUI;

public class AddressHandler implements ActionListener,MouseListener{
	AddressGUI test;
	String shopname,phoneNo,description;
	ProductSailingGUI pGui;OptionPane_GUI oGui;
	public AddressHandler(AddressGUI gui,ProductSailingGUI pGui,OptionPane_GUI oGui) {
		test = gui;this.pGui = pGui;this.oGui = oGui;
	}

	public void actionPerformed(ActionEvent e) {
		try{
			test.addreeOk.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Address address = new Address(test.shopName.getText(), test.phoneNo.getText(), test.addressArea.getText());
			AdressFillingSection.SaveaAddress(address);
			 pGui.frame.setEnabled(true);
             pGui.frame.setAlwaysOnTop(true);
             oGui.frame.setVisible(false);
             oGui.frame.dispose();
             pGui.frame.setAlwaysOnTop(false);
             pGui.cField.requestFocusInWindow();
		}finally {
			 test.addreeOk.setCursor(Cursor.getDefaultCursor());
			}
		
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		test.addreeOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
