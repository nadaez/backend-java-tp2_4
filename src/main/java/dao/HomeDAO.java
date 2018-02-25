package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.*;
import domain.Home;

public class HomeDAO {

  EntityManagerFactory factory = Persistence
    .createEntityManagerFactory("mysqlperso");
  EntityManager manager = factory.createEntityManager();

  EntityTransaction tx = manager.getTransaction();

  public Home create(String adresse) {
    tx.begin();
    Home home1 = new Home();
    try {
      home1.setAdresse(adresse);
      manager.persist(home1);

    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    System.out.println("Home  created with succes !!! ");
    return home1;
  }


  public Home create(String taille, String adresse, int nb_pieces) {
    tx.begin();
    Home home1 = new Home(taille,adresse, nb_pieces);
    try {
      manager.persist(home1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    System.out.println("Home  created with succes !!! ");
    return home1;
  }

  public Home getHomeById(int id) {
    return manager.createQuery("Select a From Home a where id =" + id, Home.class).getSingleResult();
  }

  public void deleteById(int id) {
    List<Home> resultList = manager.createQuery("Select a From Home a where a.id= '" + id + "'", Home.class).getResultList();
    for (Home x : resultList) {
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

  /**
   *   Assurer qu'une personne ne voit pas les devices d'une autre
   */
  public Heater getHomeHeaterById(int hid, int heat_id) {
    return manager.createQuery("Select a From Heater a where id =" + heat_id+" and id_home = "+hid, Heater.class).getSingleResult();
  }

  /**
   * Assurer qu'une personne ne voit pas les devices d'une autre
   */
  public Home getPersonHomeById(int pid, int hid) {
    return manager.createQuery("Select a From Home a where id =" + hid + " and id_person = " + pid, Home.class).getSingleResult();
  }
}
