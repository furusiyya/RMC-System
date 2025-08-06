package model_MenuSection;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import View_ProductSection.ProductSailingGUI;

public class DbLink {

	static Connection con = null;
	static Statement st = null;
	
	
	public static void allow(){
		java.sql.Date obj = new java.sql.Date(new java.util.Date().getTime());
		try {
			ResultSet rs = DbLink.query("SELECT * FROM GERANIUM.ALLERTS");
			   while(rs.next()){
				 obj = rs.getDate("EXPIRY");
			   }
			   if(new Date().before(obj))
					new ProductSailingGUI().frame.setVisible(true);
				else{
					String ad = new String(),us = new String();
					int aden = 0 , usen = 0;
					rs = DbLink.query("select * from geranium.stock");
						while(rs.next()){
							ad = rs.getString("admin");
							us = rs.getString("USER");
							aden = rs.getInt("adminenable");
							usen = rs.getInt("userenable");
						}
				aden = usen = 1;
				 DbLink.writeSale("delete from GERANIUM.STOCK");
				 DbLink.writeSale("INSERT INTO GERANIUM.STOCK (ADMIN,user,adminenable,userenable) values ('"+ad+"','"+us+"',"+aden+","+usen+")");
				JOptionPane.showMessageDialog(null, "Your current version is expire!");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void connect(){
		//getInput();
		try {
			Class.forName("org.h2.Driver");
			File currentDirFile = new File(".");
			String helper = currentDirFile.getAbsolutePath();
			helper = helper.substring(0,helper.length()-1)+"lib\\database";
			con = DriverManager.getConnection("jdbc:h2:file:"+helper,"sa","NAANZILLA@naanzilla");
			st = con.createStatement();
			/**
             * Create and select a database for use. 
             */
            st.execute("CREATE SCHEMA IF NOT EXISTS GERANIUM");
            st.execute("USE GERANIUM");
            
            
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DBlink: Exception class not found");
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			Toolkit.getDefaultToolkit().beep();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBlink: Exception in connecting");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			Toolkit.getDefaultToolkit().beep();
			
		}
	}
	
	
	public static boolean insert(String id,String description,double Price){
		//getInput();
		try {
				st.execute("INSERT INTO GERANIUM.MENU (ID ,DESCRIPTION ,PRICE )"
				+ " VALUES ('"+id+"','"+description+"',"+Price+")");
			return true;
			} catch (SQLException e) {
				Toolkit.getDefaultToolkit().beep();
				System.out.println("DBlink: Exception in updating Stock");
//				JOptionPane.showMessageDialog(null, "Please contact service provider!");
				return false;
			}
	}
	
	
	public static boolean updateExisted(String id,String description,double Price){
		//getInput();
		try {
			st.execute("UPDATE GERANIUM.MENU SET DESCRIPTION = '"+description+"', PRICE = "
					+Price+" WHERE ID = '"+id+"'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Product code and Expiry already exist!");
			Toolkit.getDefaultToolkit().beep();
			return false;
		}
	}
	
	public static boolean updateExistedCustomers(String id,String name,String address){
		//getInput();
		try {
			st.execute("UPDATE GERANIUM.CUSTOMERS SET NAME = '"+name+"', ADDRESS = '"
					+address+"' WHERE CUSTOMER_ID = '"+id+"'");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please contact with service provider!");
			Toolkit.getDefaultToolkit().beep();
			return false;
		}
	}
	
	public static ResultSet query(String query){
		//getInput();
		try {
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBlink: Exception in Executing Query");
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			Toolkit.getDefaultToolkit().beep();
			return null;
		}
	}
	
	
	public static boolean deleteROw(String str){
		//getInput();
		try {
			st.execute("DELETE FROM GERANIUM.MENU WHERE ID = '"+str+"'");
			return true;
		} catch (SQLException e) {
			System.out.println("DBlink: Exception in deleting row");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			return false;
		}
	}
	
	
	public static boolean writeSale(String query){
		//getInput();
		try {
			System.out.println(query);
			st.execute(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("DBlink: Exception in writting sale");
//			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			Toolkit.getDefaultToolkit().beep();
			return false;
		}
	}
	
	public static ResultSet searchProduct(String str,int quantity){
		//getInput();
				try {
					ResultSet rs = st.executeQuery("SELECT * FROM GERANIUM.MENU WHERE ID = '"+str+"' OR DESCRIPTION = '"+str+"'");
					return rs;
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("DBlink: Exception in searching stock");
					JOptionPane.showMessageDialog(null, "Please contact service provider!");
					Toolkit.getDefaultToolkit().beep();
					return null;
				}
	}
	
	
	static boolean saveSupply(String description,String type,int quantity,String color){
		//getInput();
		try {
	    	st.execute("INSERT INTO GERANIUM.SUPPLY (DESCRIPTION ,TYPE,QUANTITY,COLOR)"
		    		+ " VALUES ('"+description+"','"+type+"',"+quantity+",'"+color+"'");
		  			return true;
			} catch (SQLException e) {
				System.out.println("DBlink: Exception in saving supply");
				JOptionPane.showMessageDialog(null, "Please contact service provider!");
				return false;
			}
	}
	
	
	public static boolean closeConnections(){
		//getInput();
		 try {
			  st.close();
		      con.close();
		      return true;
		} catch (SQLException e) {
			System.out.println("DBlink: Exception in closing connections");
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			Toolkit.getDefaultToolkit().beep();
			return false;
		}
	}


	public static int invoiceCode(){
		//getInput();
		try {
			int netCode = 0;
			ResultSet codeRs = st.executeQuery("SELECT CODE FROM GERANIUM.NETSALE");
			while(codeRs.next()){
				netCode = codeRs.getInt("CODE");}
				return netCode;			
		} catch (SQLException e) {
			System.out.println("DBlink: Exception in getting invoiceCode");
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			Toolkit.getDefaultToolkit().beep();
			return 0;
		}
	}
		
		
	public static boolean saleRegister(int code,String ID,int quan,String type,double dis,long customer_Id){
		//getInput();
			try {
				st.execute("INSERT INTO GERANIUM.NETSALE (CODE,ID,QUANTITY,DISCOUNT,TYPE,customer_Id) "
						+ "VALUES ("+code+",'"+ID+"',"+quan+","+dis+",'"+type+"',"+customer_Id+")");
				return true;
			} catch (SQLException e) {
				e.getSQLState();
				e.printStackTrace();
				System.out.println("DBlink: Exception in adding sale to sale Register");
				JOptionPane.showMessageDialog(null, "Please contact service provider!");
				Toolkit.getDefaultToolkit().beep();
				return false;
			}
		}
	

	public static int searchInvoice(int invoiceCode){

		//getInput();
		try {
			System.out.println(invoiceCode);
			ResultSet codeRs = st.executeQuery("SELECT * FROM GERANIUM.NETSALE "
						+ "WHERE CODE = "+invoiceCode+"");
			int count = 0;
			while(codeRs.next() && count <1)count++;
			if (count>0){
			System.out.println("Found");
			return 1;}
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBlink: Exception in searching invoice from NetSale");
			JOptionPane.showMessageDialog(null, "Please contact service provider!");
			return 0;
		}
	}
	
	public static ResultSet retrieveAddress(long phone){
		ResultSet rs;
		try {
			rs = st.executeQuery("SELECT * FROM GERANIUM.CUSTOMERS WHERE customer_Id = "+phone+"");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
//	static void getInput(){
//		try{
//			PrintStream errstream = new PrintStream(new FileOutputStream("DBLinkError"+new Date()+".txt"),true);
//			System.setErr(errstream);
//			errstream.close();
//		}catch(IOException e){
//			e.printStackTrace();
//		}finally{	
//		}	
//	}
}
