package dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import domain.Heater;
import domain.Home;
import domain.Person;
import domain.*;


public class PersonDAO{

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("mysqlperso");
	EntityManager manager = factory.createEntityManager();

	EntityTransaction tx = manager.getTransaction();



	public Person create(String nom,String Prenom,String email) {
		tx.begin();
		Person p1 = new Person();

		try {

			 p1.setName(nom);
				p1.setMail(email);
				p1.setFirstname(Prenom);
			manager.persist(p1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		System.out.println("Heate Drevice created with succes !!! ");
		return p1;
//		manager.close();
//		factory.close();

	}



	public Person findById(int id){

		tx.begin();

		return manager.find(Person.class, id);
}

  public Person getPersonById(int id) {
    return manager.createQuery("Select a From Person a where id =" + id, Person.class).getSingleResult();
  }




  public void deleteById(int id){
    List<Person> resultList = manager.createQuery("Select a From Person a where a.id= '" + id +"'", Person.class).getResultList();
    for(Person x : resultList) {
      tx.begin();
      manager.remove(x);
      tx.commit();
    }
  }

public void updateHome(Person person, Home home) {

	tx.begin();

    person.getHomes().add(home);
    manager.merge(person);
	tx.commit();


//    query.setParameter("residence",findbyid(idres));

}


public void updateElecDevice(Person person, ElectronicDevice elec) {

	tx.begin();

    person.getElectronicDevices().add(elec);
    manager.merge(person);
	tx.commit();


//    query.setParameter("residence",findbyid(idres));

}
}

//
//
//
//public void createPersons() {
//	int numOfEmployees = EntityManagerHelper.getEntityManager().createQuery("Select a From Person a", Person.class).getResultList().size();
//    if (numOfEmployees == 0) {
//        Home house = new Home();
//        house.setAdr("Charles_Foulon");
//       new  HomeDAO().create(house);
//        Person p1 = new Person();
//        p1.setNom("Father");
//		p1.setMail("houssaineezziraiy@gmail.com");
//		p1.setPrenom("Houssaine");
//		this.create(p1);
//
//    }
//
//}
//
//@Override
//public Person read(Long id) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public Person update(Person t) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public void delete(Person t) {
//	// TODO Auto-generated method stub
//
//}
//
//@Override
//public Person create(Person t) {
//	EntityManagerHelper.beginTransaction();
//
//	EntityManagerHelper.getEntityManager().persist(t);
//
//	EntityManagerHelper.commit();
//
//
//
//	return t;
//}
//}
//
//
//
