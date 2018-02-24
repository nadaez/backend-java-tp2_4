package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.Heater;
import domain.Home;
import domain.Person;
import domain.Home;

public class HomeDAO {
	
	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("mysqlperso");
	EntityManager manager = factory.createEntityManager();
	
	EntityTransaction tx = manager.getTransaction();
	
	

	
	public Home create(String adresse){

//		EntityManagerFactory factory = Persistence
//				.createEntityManagerFactory("mysql");
//		EntityManager manager = factory.createEntityManager();
//		
//		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Home home1=new Home();

		try {

			home1.setAdresse(adresse);
//			List<Person> personLista=new ArrayList<Person>();
//			personLista.add(firstPerson);
//			home1.setPersonnes(personLista);
			manager.persist(home1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		System.out.println("Home  created with succes !!! ");
		return home1;
//		manager.close();
//		factory.close();

	}
	public void delete(long l){

//		EntityManagerFactory factory = Persistence
//				.createEntityManagerFactory("mysql");
//		EntityManager manager = factory.createEntityManager();
//		
//		EntityTransaction tx = manager.getTransaction();
tx.begin();
try {
			  manager.remove(findById(l));	
			  } catch (Exception e) {
					e.printStackTrace();
				}
			  
			  tx.commit();
			System.out.println("Home  deleted with succes !!! ");
//						manager.close();
//			factory.close();
	
	}
	
	public Home findById(long l){
		
//tx.begin();
		return manager.find(Home.class, l);
//		manager.close();
//		factory.close();
}	
	
	
	
	public void deleteById(int id){
		List<Home> resultList = manager.createQuery("Select a From Home a where a.id= '" + id +"'", Home.class).getResultList();
		for(Home x : resultList) {
			tx.begin();
			manager.remove(x);
			tx.commit();
		}
	}
	

public void update(Heater heater, Home home) {

    tx.begin();
    home.getHeaters().add(heater);
    manager.merge(home);
	tx.commit();



}	
}