package jpa;

import dao.ElectronicDeviceDAO;
import dao.HeaterDAO;
import dao.HomeDAO;
import dao.PersonDAO;
import domain.*;

public class JpaTest {  
	
	public static void main(String[] args) {
		
		ElectronicDeviceDAO elecDeviceDao= new ElectronicDeviceDAO();
		
		PersonDAO personDao= new PersonDAO();
		
		HeaterDAO heaterDao = new HeaterDAO();
		
		HomeDAO homeDao = new HomeDAO();
	
		ElectronicDevice phone1=elecDeviceDao.create("Iphone", "Iphone10");
		
		Heater heater1=heaterDao.create("165789479");

		Home home1=homeDao.create("Portugal");
		
		Person personne1 =  personDao.create("mcok", "nada", "nabe") ;
		
		personDao.updateElecDevice(personne1, phone1);
		
		homeDao.update(heater1, home1);
		
	homeDao.deleteById(36);		
	
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
