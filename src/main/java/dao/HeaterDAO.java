package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.Heater;
import domain.Home;
import domain.Person;

public class HeaterDAO {
	
	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("mysqlperso");
	EntityManager manager = factory.createEntityManager();
	
	EntityTransaction tx = manager.getTransaction();
	
	
public Heater create(String puissance){

	
	Heater heaterTest = new Heater();

	tx.begin();

	try {

		heaterTest.setPuissance(puissance);
		manager.persist(heaterTest);

	} catch (Exception e) {
		e.printStackTrace();
	}
	tx.commit();
	System.out.println("Heater Drevice created with succes !!! ");
	return heaterTest;
//	manager.close();
//	factory.close();

}
}
	

