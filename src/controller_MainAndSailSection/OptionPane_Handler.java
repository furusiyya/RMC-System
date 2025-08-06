package controller_MainAndSailSection;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import View_ProductSection.ProductSailingGUI;
import view_Main.AddressGUI;
import view_Main.OptionPane_GUI;
import view_Main.selectPass_GUI;

public class OptionPane_Handler implements ListSelectionListener,WindowListener{

	
	OptionPane_GUI gui;
	ProductSailingGUI pGui;
	public OptionPane_Handler(OptionPane_GUI gui,ProductSailingGUI pGui) {
		this.gui = gui;this.pGui = pGui;
	}
	
	JPanel test;
	public void valueChanged(ListSelectionEvent e)  {
		
		if(gui.list.getSelectedIndex() == 0){
			try {
				gui.cardPanel.remove(test);
				test = new selectPass_GUI(gui,pGui).panel;
				gui.cardPanel.add(test);
				gui.cardPanel.revalidate();
				gui.cardPanel.setVisible(true);
			} catch (NullPointerException e1){
				test = new selectPass_GUI(gui,pGui).panel;
				gui.cardPanel.add(test);
				gui.cardPanel.revalidate();
				gui.cardPanel.setVisible(true);
			}
		}
		
		else if(gui.list.getSelectedIndex() == 1){
			try {
				gui.cardPanel.remove(test);
				test = new AddressGUI(gui,pGui).panel;
				gui.cardPanel.add(test);
				gui.cardPanel.revalidate();
				gui.cardPanel.setVisible(true);
			} catch (NullPointerException e1) {
				test = new AddressGUI(gui,pGui).panel;
				gui.cardPanel.add(test);
				gui.cardPanel.revalidate();
				gui.cardPanel.setVisible(true);
			}
		}
		
	}
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(WindowEvent e) {
		pGui.frame.setEnabled(true);
		pGui.frame.setAlwaysOnTop(true);
        e.getWindow().dispose();
        pGui.frame.setAlwaysOnTop(false);
        pGui.cField.requestFocusInWindow();
	}
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}


