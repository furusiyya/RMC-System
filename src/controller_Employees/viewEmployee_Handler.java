package controller_Employees;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import view_Employees.ViewEmployee;


	public class viewEmployee_Handler implements ActionListener,FocusListener{
		String id = null;
		ViewEmployee gui;
		public viewEmployee_Handler(ViewEmployee modifyStock) {
			gui = modifyStock;
			DbLink.connect();
		}
		int index = -1;
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("SAVE")){
				if(confirmation("Please review all fields before 'YES'?","Warning")==0){
					addRec();
				}
			}
			else if(e.getSource() == gui.box){
				index = gui.box.getSelectedIndex();
				id = gui.id.get(index);
				try {
					ResultSet rs = DbLink.query("select * from geranium.employee where id = '"+id+"'");
					while(rs.next()){
						gui.idField.setText(rs.getString("ID"));
						gui.nameField.setText(rs.getString("name"));
						gui.cnicField.setText(""+rs.getLong("CNIC"));
						gui.phField.setText(""+rs.getLong("number"));
						gui.addField.setText(rs.getString("address"));
						gui.salaryField.setText(""+rs.getInt("salary"));
						gui.desigField.setText(rs.getString("designation"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getActionCommand().equals("DELETE")){
				if(confirmation("Are you sure to delete??","Warning")==0){
					if(id!= null){
					DbLink.writeSale("delete from geranium.employee where id = '"+id+"'");
					gui.arr.remove(index);
					gui.array[index] = null;
					gui.box = new JComboBox<String>(gui.array);
					gui.idField.setText(null);
					gui.nameField.setText(null);
					gui.cnicField.setText(null);
					gui.phField.setText(null);
					gui.addField.setText(null);
					gui.salaryField.setText(null);
					gui.desigField.setText(null);
					gui.panel.revalidate();
					}
					else{
						JOptionPane.showMessageDialog(gui.panel,"Please first make some selection!");	
					}
				}
			}
		}
		
		int confirmation(String str,String str1){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null,str ,str1,dialogButton);
				return dialogResult;
			}
		 
		void addRec(){
			if(id != null){
			if(DbLink.writeSale("update geranium.employee set id = '"+gui.idField.getText()+"' "
					+ " ,name ='"+gui.nameField.getText()+"', cnic = "+gui.cnicField.getText()+" , number = "+gui.phField.getText()
					+" , address  = '"+gui.addField.getText()+"' ,salary = "+gui.salaryField.getText()
					+", designation = '"+gui.desigField.getText()+"' where id = '"+id+"'")){
				JOptionPane.showMessageDialog(gui.panel,"Successfully saved!");
				
			}else{
				JOptionPane.showMessageDialog(gui.panel,"Error occure, Try again!");
			}}else{
				JOptionPane.showMessageDialog(gui.panel,"Please first make some selection!");	
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


