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

  public ElectronicDevice create(String prix, String name, String marque)  {
    tx.begin();
    ElectronicDevice elec = new ElectronicDevice(prix, name,marque) ;

    try {
      manager.persist(elec);
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    System.out.println("ElecDevice was  created with succes !!! ");
    return elec;
  }

  public void delete(int idRes) {
    tx.begin();
    try {
      manager.remove(getDeviceById(idRes));
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    System.out.println("ElecDevice  deleted with succes !!! ");
  }

  public ElectronicDevice findById(int id) {

    return manager.find(ElectronicDevice.class, id);
  }


  public ElectronicDevice getDeviceById(int id) {
    return manager.createQuery("Select a From ElectronicDevice a where id =" + id, ElectronicDevice.class).getSingleResult();
  }

  /**
   *   Assurer qu'une personne ne voit pas les devices d'une autre
   */
  public ElectronicDevice getUserDeviceById(int uid, int did) {
    return manager.createQuery("Select a From ElectronicDevice a where id =" + did+" and id_person = "+uid, ElectronicDevice.class).getSingleResult();
  }
}
