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

  public Heater create(String prix, String puissance) {
    System.out.println("Creating heater ...");
    Heater heater = new Heater(prix, puissance);
    tx.begin();
    try {
      manager.persist(heater);
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    System.out.println("Heater Drevice created with succes !!! ");
    return heater;
  }


  public Heater getHeaterById(int id) {
    return manager.createQuery("Select a From Heater a where id =" + id, Heater.class).getSingleResult();
  }

  public void delete(int idRes) {
    tx.begin();
    try {
      manager.remove(getHeaterById(idRes));
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    System.out.println("Heater  deleted with succes !!! ");
  }

}
