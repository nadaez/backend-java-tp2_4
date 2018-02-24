package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;
import domain.Home;

public class ElectronicDeviceDAO {
	
	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("mysqlperso");
	EntityManager manager = factory.createEntityManager();
	
	EntityTransaction tx = manager.getTransaction();
	
	

	
	public ElectronicDevice create(String marque,String name){

//		EntityManagerFactory factory = Persistence
//				.createEntityManagerFactory("mysql");
//		EntityManager manager = factory.createEntityManager();
//		
//		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		ElectronicDevice elec=new ElectronicDevice();

		try {

			elec.setName(name);
			elec.setMarque(marque);
//			List<Person> personLista=new ArrayList<Person>();
//			personLista.add(firstPerson);
//			home1.setPersonnes(personLista);
			manager.persist(elec);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		System.out.println("ElecDevice was  created with succes !!! ");
		return elec;
//		manager.close();
//		factory.close();

	}
	public void delete(int idRes){

//		EntityManagerFactory factory = Persistence
//				.createEntityManagerFactory("mysql");
//		EntityManager manager = factory.createEntityManager();
//		
//		EntityTransaction tx = manager.getTransaction();
tx.begin();
try {
			  manager.remove(findById(idRes));	
			  } catch (Exception e) {
					e.printStackTrace();
				}
			  
			  tx.commit();
			System.out.println("ElecDevice  deleted with succes !!! ");

	
	}
	
	public ElectronicDevice findById(int id){
		
//tx.begin();
		return manager.find(ElectronicDevice.class, id);
//		manager.close();
//		factory.close();
}
	
}