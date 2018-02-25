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


public class PersonDAO {
  EntityManagerFactory factory = Persistence
    .createEntityManagerFactory("mysqlperso");
  EntityManager manager = factory.createEntityManager();
  EntityTransaction tx = manager.getTransaction();

  public Person create(String nom, String Prenom, String email) {
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
  }

  public Person findById(int id) {
    tx.begin();
    return manager.find(Person.class, id);
  }

  public Person getPersonById(int id) {
    return manager.createQuery("Select a From Person a where id =" + id, Person.class).getSingleResult();
  }

  public void deleteById(int id) {
    List<Person> resultList = manager.createQuery("Select a From Person a where a.id= '" + id + "'", Person.class).getResultList();
    for (Person x : resultList) {
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
  }

  public void addDevice(Person person, ElectronicDevice elec) {
    tx.begin();
    person.getElectronicDevices().add(elec);
    manager.merge(person);
    tx.commit();
  }
}
