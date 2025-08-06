package model_MainAndSailRecords;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Address implements Serializable{

	public String shopName= null, phoneNo = null,description = null;
	public Address(String shopName,String phoneNo,String disc){
		this.shopName = shopName.trim();
		this.phoneNo = phoneNo.trim();
		description = disc.trim();
	}
	
	public String toString(){
		String address = "";
		int strSize = 0;
		//**Setting shopName
		if(shopName!=""&& shopName != null){
		strSize = shopName.length();
		String shopNam = shopName;
		if(strSize > 38){
			shopNam = shopNam.substring(0,37);
		}
		
		for(int i = 0; i < (39-shopNam.length())/2; i ++){
			String str = " ";
			str+=shopNam;
			shopNam = str;
		}
		address+=(shopNam+"\n");
		}
		String label = null;
		//**setting PhoneNumber
		if(phoneNo !="" && phoneNo !=null){
		strSize = phoneNo.length();
		label = phoneNo;
		if(strSize > 38){
			label = label.substring(0,37);
		}
		label = ("Phone No# "+label);
		for(int i = 0; i < (39-label.length())/2; i ++){
			String str = " ";
			str+=label;
			label = str;
		}
		
		}
		
		//**setting description
		if(description != null && description != ""){
			String dis = description;
			if( dis.length() > 30 &&  dis.length() <= 60){
				int size = dis.length();
				String str1 =  dis.substring(0, size/2+5);
				String str2  =  dis.substring(size/2+6,size);
				for(int i = 0; i < (39-str1.length())/2; i ++){
					String str = " ";
					str+=str1;
					str1 = str;
					str = " ";
					str+=str2;
					str2 = str;
				}
				address+= (str1+"\n    "+str2+"\n");}
			
			if( dis.length() > 60 &&  dis.length() < 120){
				int size = dis.length()/4;
				String str1 =  dis.substring(0, size);
				String str2  =  dis.substring(size,2*size);
				String str3  =  dis.substring(2*size,3*size);
				String str4  =  dis.substring(3*size,4*size);
				for(int i = 0; i < (39-str1.length())/2; i ++){
					String str = " ";
					str+=str1;
					str1 = str;
					str = " ";
					str+=str2;
					str2 = str;
					str = " ";
					str+=str3;
					str3 = str;
					str = " ";
					str+=str4;
					str4 = str;
				}
				
				address+= (str1+"\n"+str2+"\n"+str3+"\n"+str4+"\n");}	
			
			if(dis.length()<=30){
				String str1 =  dis;
				for(int j = 0 ; j < (39-str1.length())/2; j ++){
					String str = " ";
					str+=str1;
					str1 = str;
				}
				address+= str1+"\n";	
			}address+=(label+"\n");
		}
		return address;
	}
}
