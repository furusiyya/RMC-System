package model_MainSailRecordFililngSection;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Date;

import javax.swing.JOptionPane;


import model_MainAndSailRecords.Address;

public class AdressFillingSection {

	/**
	 * File name Identifier
	 */
	private static final String ADRESS_DATA_FILE = "sysdd.dat";
	
	
	/**
	 * (Used in sailing history Section Saving invoices in file
	 * @param invoice
	 * @return
	 */
	public static boolean SaveaAddress(Address address) {
		//getInput();
		boolean success = false;
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(ADRESS_DATA_FILE));
			/** Write object into the file **/
			outputStream.writeObject(address);
			
		} 	
		catch(IOException e) {
			System.out.println("IO Exception while opening file");
			JOptionPane.showMessageDialog(null, "Please contact with service provider!");
			} 
		finally {
			/**cleanup code which closes output stream if its object was created**/
			try {
				if(outputStream != null) {
					outputStream.close();
					/**flag of success**/
					success = true;
				}

			} catch (IOException e) {
				System.out.println("IO Exception while closing file");
				JOptionPane.showMessageDialog(null, "Please contact with service provider!");
			}
		}
		return success;
	}
	
	
	
	/**
	 * Retrieves address from file and returns address
	 * @return Address
	 */
	public static Address RetrieveAddress() {
		//getInput();
		Address address = null;
		/** Input stream for retrieving ADress**/
		ObjectInputStream inputStream = null;
		try
		{
			/**Opening file for retrieving**/
			inputStream = new ObjectInputStream(new FileInputStream(ADRESS_DATA_FILE));
			/**End Of File flag**/
			boolean EOF = false;
			/** Keep reading file until file ends**/
			while(!EOF) {
				try {
					address = (Address) inputStream.readObject();
				} catch (ClassNotFoundException e) {
					System.out.println("Class not found");
					JOptionPane.showMessageDialog(null, "Please contact with service provider!");
				} catch (EOFException end) {
					/** EOFException is raised when file ends
					*set End Of File flag to true so that loop exits
					**/
					EOF = true;
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("Cannot find file");
			JOptionPane.showMessageDialog(null, "Please contact with service provider!");
		} catch (IOException e) {
			System.out.println("IO Exception while opening stream");
			JOptionPane.showMessageDialog(null, "Please contact with service provider!");
			e.printStackTrace();
		} finally { /** cleanup code to close stream if it was opened**/
			try {
				if(inputStream != null)
					inputStream.close( );
			} catch (IOException e) {
				System.out.println("IO Exception while closing file");
				JOptionPane.showMessageDialog(null, "Please contact with service provider!");
			}
		}
		/** 
		 * returns ArrayList
		 */
		return address;
	}
	
	/**
	 *changing address
	 * @return
	 */
	public static boolean changeAdress(Address add){
		Address address = AdressFillingSection.RetrieveAddress();
		address.shopName = add.shopName;
		address.description = add.description;
		address.phoneNo = add.phoneNo;
		return SaveaAddress(address);
	}
	
	static void getInput(){
		try{
			PrintStream errstream = new PrintStream(new FileOutputStream("AddressFillingError"+new Date()+".txt"),true);
			System.setErr(errstream);
			errstream.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{	
		}	
	}
	
}
