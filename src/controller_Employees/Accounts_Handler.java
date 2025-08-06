package controller_Employees;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model_MenuSection.DbLink;
import view_Employees.AccountsGUI;


	public class Accounts_Handler implements ActionListener,FocusListener{
		String id = null;
		AccountsGUI gui;
		public Accounts_Handler(AccountsGUI modifyStock) {
			gui = modifyStock;
			DbLink.connect();
		}
		int index = -1;
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("OK")){
				if(confirmation("Please review cash before 'YES'?","Warning")==0){
					addRec();
				}
			}
			else if(e.getSource() == gui.box){gui.model.setRowCount(0);
			
				index = gui.box.getSelectedIndex();
				id = gui.id.get(index);
				int total  = 0;
				try {
					ResultSet rs = DbLink.query("select * from geranium.account where id = '"+id+"'");
					while(rs.next()){
						Object[] obj = {rs.getDate("payed"),rs.getInt("cash")};
						total +=rs.getInt("cash");
						gui.model.addRow(obj);
					}
					System.out.println(total);
					rs = DbLink.query("SELECT DATEDIFF(month,hiredate,(getdate())) AS diff,salary from geranium.employee where id = '"+id+"'");
					int salary = 0,diff = 0;
					while(rs.next()){
						diff = rs.getInt("diff");
						salary = rs.getInt("salary");
					}
					System.out.println(diff);
					System.out.println(salary);
					if(total > (salary*diff)){
						gui.advnaceFeild.setText("Advance: "+(total-(salary*diff)));
						gui.balanceField.setText("Balance: 0.0");
					}else{
						gui.advnaceFeild.setText("Advance: 0.0");
						gui.balanceField.setText("Balance: "+((salary*diff)-total));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
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
			if(gui.payField.getText().length()>1){
				DbLink.writeSale("insert into geranium.account(id,cash) values ('"+id+"',"+gui.payField.getText()+")");
				gui.payField.setText(null);
				gui.model.setRowCount(0);
				gui.balanceField.setText("Balance: 0.0");
				gui.advnaceFeild.setText("Advance: 0.0");
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
			if(e.getSource() == gui.payField){
			String str = gui.payField.getText();
			if(str.length() > 2){
				try {
					Integer.parseInt(str);
				} catch (NumberFormatException e1) {
					gui.payField.setText(null);
				}
				
			}else{
				gui.payField.setText(null);
			}
			}	}
}
		
	


