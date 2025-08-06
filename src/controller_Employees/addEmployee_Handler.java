package controller_Employees;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import view_Employees.AddEmployee;


	public class addEmployee_Handler implements ActionListener,FocusListener{

		AddEmployee gui;
		public addEmployee_Handler(AddEmployee modifyStock) {
			gui = modifyStock;
			DbLink.connect();
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("ADD")){
				if(confirmation("Please review all fields before 'YES'?","Warning")==0){
					addRec();
				}
			}
			
		}
		
		int confirmation(String str,String str1){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null,str ,str1,dialogButton);
				return dialogResult;
			}
		 
		void addRec(){
			if(DbLink.writeSale("insert into geranium.employee(id,name,cnic,number,address,salary,designation) "
					+ " values ('"+gui.idField.getText()+"','"+gui.nameField.getText()+"',"+gui.cnicField.getText()+","+gui.phField.getText()
					+",'"+gui.addField.getText()+"',"+gui.salaryField.getText()+",'"+gui.desigField.getText()+"')")){
				JOptionPane.showMessageDialog(gui.panel,"Successfully saved!");
				gui.idField.setText(null);
				gui.nameField.setText(null);
				gui.cnicField.setText(null);
				gui.phField.setText(null);
				gui.addField.setText(null);
				gui.salaryField.setText(null);
				gui.desigField.setText(null);
				
			}else{
				JOptionPane.showMessageDialog(gui.panel,"Error occure, Try again!");
			}
		}


		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void focusLost(FocusEvent e) {
			if(e.getSource() == gui.nameField){
				String str = gui.nameField.getText();
				if(str.length() > 3){
				}else{
					gui.nameField.setText(null);
//					gui.nameField.requestFocusInWindow();
				}
			}
		
		else if(e.getSource() == gui.idField){
			String str = gui.idField.getText();
			if(str.length() > 3){
					try {
						boolean exist = false;
						ResultSet rs = DbLink.query("select id from geranium.employee");
						while(rs.next()){
							if(rs.getString("ID").equalsIgnoreCase(str))exist = true;
						}
						if(!exist){
						}else{
							gui.idField.setText(null);
//							gui.idField.requestFocusInWindow();
						}
					} catch (SQLException e1) {
						}
			}else{
				gui.idField.setText(null);
//				gui.idField.requestFocusInWindow();
				
			}
		}
			
			
		else if(e.getSource() == gui.cnicField){
			String str = gui.cnicField.getText();
			if(str.length() == 13){
				try {
					Long.parseLong(str);
				} catch (NumberFormatException e1) {
					gui.cnicField.setText(null);
//					gui.cnicField.requestFocusInWindow();
				}
				
			}else{
				gui.cnicField.setText(null);
//				gui.cnicField.requestFocusInWindow();
			}
		}
			
		else if(e.getSource() == gui.phField){
			String str = gui.phField.getText();
			if(str.length() >= 7){
				try {
					Long.parseLong(str);
				} catch (NumberFormatException e1) {
					gui.phField.setText(null);
//					gui.phField.requestFocusInWindow();
				}
				
			}else{
				gui.phField.setText(null);
//				gui.phField.requestFocusInWindow();
			}
		}
			
		else if(e.getSource() == gui.addField){
			String str = gui.addField.getText();
			if(str.length() > 5){
			}else{
				gui.addField.setText(null);
//				gui.addBtn.requestFocusInWindow();
			}
		}
		else if(e.getSource() == gui.salaryField){
			String str = gui.salaryField.getText();
			if(str.length() > 2){
				try {
					Integer.parseInt(str);
				} catch (NumberFormatException e1) {
					gui.salaryField.setText(null);
//					gui.salaryField.requestFocusInWindow();
				}
				
			}else{
				gui.salaryField.setText(null);
//				gui.salaryField.requestFocusInWindow();
			}
		}
		else if(e.getSource() == gui.desigField){
			String str = gui.desigField.getText();
			if(str.length() >3){
				
			}else{
				gui.desigField.setText(null);
//				gui.desigField.requestFocusInWindow();
			}
		}
			
		}
		
	}


