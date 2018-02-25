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

		ElectronicDevice phone1=elecDeviceDao.create("200", "Nexus 6","Google");

		Heater heater1=heaterDao.create("152e","200w");

		Home home1=homeDao.create("1000m²", "Rennes",4);

		Person personne1 =  personDao.create("mcok", "nada", "nabe@gmail.com") ;

		personDao.addDevice(personne1, phone1);

		homeDao.update(heater1, home1);

	personDao.updateHome(personne1,home1);


	}

















}
