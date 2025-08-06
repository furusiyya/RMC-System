package controller_MenuSection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import model_MenuSection.DbLink;
import view_MenuSection.R_GenerateReport_GUI;


public class R_GenerateReport_Handler implements ActionListener{

	R_GenerateReport_GUI gui;
	int fileIndex = 1;
	public R_GenerateReport_Handler(R_GenerateReport_GUI viewCustomer) {
		gui = viewCustomer;
		DbLink.connect();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Save")){
			if(confirmation("You are sure about provided amounts?","Confirmation")==0){
				if(!(calculateSale())){
					JOptionPane.showMessageDialog(gui.panel, "Please fill all fields!");
				}else{
					gui.elecField.setText(null);
					gui.gassField.setText(null);
					gui.waterField.setText(null);
					gui.otherField.setText(null);
					gui.taxField.setText(null);
					gui.phField.setText(null);
				}
			}
			
		}
	}
	
	int confirmation(String str,String str1){
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, str,str1,dialogButton);
		return dialogResult;
	}	
	double total = 0,discount = 0,loss = 0,purchase = 0,miscellaneous = 0;
	boolean calculateSale(){
			try{
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int mon = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);
				total = 0;discount = 0;loss = 0;
				int flag = 1;
				ResultSet rs = DbLink.query("SELECT s.ID,m.PRICE,SUM(s.QUANTITY) AS QUANTITY"
									+ " FROM GERANIUM.NETSALE s natural join GERANIUM.MENU m WHERE "
									+ "EXTRACT(MONTH FROM s.INVOICEDATE) ="+mon
									+" AND  EXTRACT(YEAR FROM s.INVOICEDATE) ="+year+
									" GROUP BY(s.ID) order by QUANTITY DESC");
					while(rs.next()){
						total+=(rs.getInt("QUANTITY")*rs.getInt("PRICE"));
						flag++;
					}
					if(flag==1){
						JOptionPane.showMessageDialog(gui.panel, "No sale record found!");
						return false;}
					rs = DbLink.query("SELECT s.ID,SUM(s.QUANTITY) AS QUANTITY"
							+ " FROM GERANIUM.NETSALE s WHERE "
							+ "EXTRACT(MONTH FROM s.INVOICEDATE) ="+mon+" AND  EXTRACT(YEAR FROM s.INVOICEDATE) ="
							+year+" AND ID LIKE 'd%' GROUP BY(s.ID) order by QUANTITY DESC");
					String str = "";
					while(rs.next()){
						str+=rs.getString("ID")+",";
						str+=rs.getString("QUANTITY")+",";
					}
					
					String[] dealId = str.split(",");
					for(int j = 0 ; j < dealId.length; j+=2){
						rs = DbLink.query("SELECT DISTINCT DEALPRICE FROM GERANIUM.DEALS WHERE dealID = '"+dealId[j]+"'");
						while(rs.next()){
							total+= rs.getDouble("DEALPRICE")*Double.parseDouble(dealId[j+1]);
						}
					}
					
					rs = DbLink.query("SELECT DISCOUNT FROM GERANIUM.NETSALE  WHERE "
							+ "EXTRACT(MONTH FROM INVOICEDATE) ="+mon+" AND  EXTRACT(YEAR FROM INVOICEDATE) ="
							+year+" AND DISCOUNT != 0 GROUP BY(CODE)");
					while(rs.next())discount+=rs.getInt("DISCOUNT");
					
					rs = DbLink.query("SELECT * from GERANIUM.FAILED WHERE "
							+ "EXTRACT(MONTH FROM INVOICEDATE) ="+mon+" AND  EXTRACT(YEAR FROM INVOICEDATE) ="+year+"");
					while(rs.next()){
						loss += rs.getInt("LOSS");
					}
					total = total-discount-loss;
					
					rs = DbLink.query("SELECT * FROM GERANIUM.SUPPLY WHERE "
							+ "EXTRACT(MONTH FROM SUPPDATE ) ="+mon+" AND  EXTRACT(YEAR FROM SUPPDATE ) ="+year+"");
						while(rs.next()){
						purchase+= (rs.getInt("QUANTITY")*rs.getDouble("PRICE"));
						}
					rs = DbLink.query("SELECT AMOUNT FROM GERANIUM.MISCELLANEOUS WHERE "
								+ "EXTRACT(MONTH FROM DATE) ="+mon+" AND  EXTRACT(YEAR FROM DATE) ="+year+"");
					while(rs.next()){
						miscellaneous+= rs.getInt("AMOUNT");
						}
					if(!(gui.elecField.getText().length()>1&&gui.gassField.getText().length()>1&&gui.phField.getText().length()>1
							&&gui.taxField.getText().length()>1&&gui.waterField.getText().length()>1&&
							gui.otherField.getText().length()>1))return false;
					
					DbLink.writeSale("DELETE FROM GERANIUM.UTILITY WHERE "
								+ "EXTRACT(MONTH FROM MONTH) ="+(mon+1)+" AND  EXTRACT(YEAR FROM MONTH) ="+year+"");
					
					return DbLink.writeSale("insert into geranium.utility(elec,gass,ph,tax,water,other,totalsale,discount,refund,"
							+ "purchase,miscellaneous) values ("
							+gui.elecField.getText()+","+gui.gassField.getText()
								+","+gui.phField.getText()+","+gui.taxField.getText()+","
										+gui.waterField.getText()+","+gui.otherField.getText()+","+total+","+discount+","
											+loss+","+purchase+","+miscellaneous+")");
			}catch(SQLException e){
				e.printStackTrace();
				System.err.println("Genarete Report Handler: Exception in calculating total");
				return false;
//				JOptionPane.showMessageDialog(null, "Please contact service provider!");
			}
	}

	
	public void getInput(){
		try{
			PrintStream errstream = new PrintStream(new FileOutputStream("ModifyGUI_HandlerError"+new Date()+".txt"),true);
			System.setErr(errstream);
			errstream.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{	
		}	
	}
}
