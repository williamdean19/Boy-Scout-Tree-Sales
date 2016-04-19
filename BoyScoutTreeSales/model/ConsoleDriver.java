package model;

import java.util.Properties;

public class ConsoleDriver {

	public static void main(String[] args)
	{

		Properties p = new Properties();
		p.setProperty("DateOfBirth", "4/19/1985");
		p.setProperty("DateStatusUpdated", "4/12/2016");
		p.setProperty("Email", "wdean2@u.brockport.edu");
		p.setProperty("FirstName", "William");
		p.setProperty("LastName", "Dean");
		p.setProperty("MiddleName", "J");
		p.setProperty("PhoneNumber", "555-555-5555");
		p.setProperty("Status", "Active");
		p.setProperty("TroopID", "1");

		//Constructor using user inputed data:
		Scout s1 = new Scout(p);
		
		System.out.println(s1);
		
		s1.save();
		
		Scout s2 = new Scout("13");
		System.out.println(s2);
		s2.setPhoneNumber("444-444-4444");
		s2.save();	
		
		s2.delete();
		
		
		
	}

}
