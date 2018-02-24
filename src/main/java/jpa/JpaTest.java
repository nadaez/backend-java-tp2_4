package jpa;

import dao.ElectronicDeviceDAO;
import dao.PersonDAO;
import domain.*;

public class JpaTest {  
	
	public static void main(String[] args) {
		
		ElectronicDeviceDAO elecDeviceDao= new ElectronicDeviceDAO();
		
		PersonDAO personDao= new PersonDAO();
		
		ElectronicDevice phone1=elecDeviceDao.create("Iphone", "Iphone10");
		
		Person personne1 =  personDao.create("mcok", "nada", "nabe") ;
		
		personDao.updateElecDevice(personne1, phone1);
		
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
