package rest;

import dao.ElectronicDeviceDAO;
import dao.HeaterDAO;
import dao.HomeDAO;
import dao.PersonDAO;
import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;
import org.codehaus.jettison.json.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonService {
  EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlperso");
  EntityManager manager = factory.createEntityManager();
  EntityTransaction tx = manager.getTransaction();
  PersonDAO personDAO = new PersonDAO();
  HomeDAO homeDAO = new HomeDAO();
  ElectronicDeviceDAO electronicDeviceDAO = new ElectronicDeviceDAO();
  private HeaterDAO heaterDAO = new HeaterDAO();


  public Person getPersonById(int id) {
    return manager.createQuery("Select a From Person a where id =" + id, Person.class).getSingleResult();
  }


  /**
   * Retourne la liste des personnes
   *
   * @return une liste
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> getPersons() {
    List<Person> resultList = manager.createQuery("Select a From Person a", Person.class).getResultList();
    System.out.println("Personnes :" + resultList.size());
    return resultList;
  }

  /**
   * Retourne la personne dont id est passé en parametre
   *
   * @param id id
   * @return une personne
   */
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Person getPerson(@PathParam("id") String id) {
    System.out.println("Get person -> " + id);
    Person resultList = personDAO.getPersonById(Integer.parseInt(id));
    return resultList;
  }

  /**
   * Ajoute une nouvelle personne
   *
   * @param pers les informations de la personne en json
   * @return toutes les personnes de la base
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> postPerson(JSONObject pers) {
    tx.begin();
    try {
      Person person = new Person();
      person.setName(pers.getString("name"));
      person.setFirstname(pers.getString("firstname"));
      person.setMail(pers.getString("mail"));
      person.setAge(pers.getString("age"));
      manager.persist(person);
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    return getPersons();
  }

  /**
   * Supprime une personne à partir de son id
   *
   * @param id id
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public void deletePersonById(@FormParam("id") int id) {
    System.out.println("delete person -> " + id);
    personDAO.deleteById(id);
  }


  /**
   * Retourne les Homes d'une personne
   *
   * @param id
   * @return liste de Home
   */
  @GET
  @Path("/{id}/home")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Home> getPersonHouses(@PathParam("id") String id) {
    System.out.println("Get person houses-> " + id);
    Person resultList = personDAO.getPersonById(Integer.parseInt(id));
    return resultList.getHomes();
  }

  /**
   * Une maison appartenant à une personne
   *
   * @param idh id de la maison
   * @return la maison ajoutée
   */
  @GET
  @Path("/{id}/home/{idh}")
  @Produces(MediaType.APPLICATION_JSON)
  public Home getHome(@PathParam("idh") String idh) {
    System.out.println("Get " + idh);
    int id = 0;
    Home resultList = null;
    try {
      id = Integer.parseInt(idh);
      resultList = homeDAO.getHomeById(id);
    } catch (Exception e) {
    }
    return resultList;
  }

  /**
   * Supprime une Home à une personne à partir des 2  id
   *
   * @param id
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}/home/{idh}")
  public void deletePersonHome(@PathParam("idh") String id) {
    System.out.println("delete person home ->  " + id);
    homeDAO.deleteById(Integer.parseInt(id));
  }

  /**
   * Ajoute une Home à une personne
   *
   * @param h
   * @return
   */
  @POST
  @Path("/{id}/home")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Home> postHome(@PathParam("id") int id, JSONObject h) {
    System.out.println("Post Home " + h);
    List<Home> homes = null;
    tx.begin();
    try {
      Home home = homeDAO.create(h.getString("taille"), h.getString("adresse"), h.getInt("nb_pieces"));
      Person person = getPersonById(id);
      personDAO.updateHome(person, home);
      homes = person.getHomes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    return homes;
  }


  @GET
  @Path("/{id}/home/{idh}/heater")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Heater> getPersonHomeHeaters(@PathParam("id") String id, @PathParam("idh") String idh) {
    System.out.println("Get person home heaters " + id + "," + idh);
    List<Heater> resultList = null;
    try {
      int p = Integer.parseInt(id);
      int h = Integer.parseInt(idh);
      resultList = homeDAO.getPersonHomeById(p, h).getHeaters();
    } catch (Exception e) {
      resultList = null;
    }
    return resultList;
  }

  @GET
  @Path("/{id}/home/{idh}/heater/{idhe}")
  @Produces(MediaType.APPLICATION_JSON)
  public Heater getHome(@PathParam("id") String id, @PathParam("idh") String idh, @PathParam("idhe") String idhe) {
    System.out.println("Get person home heater " + id + "," + idh + "," + idhe);
    Heater resultList = null;
    Home home = null;
    try {
      int p = Integer.parseInt(id);
      int h = Integer.parseInt(idh);
      int he = Integer.parseInt(idhe);
      home = homeDAO.getPersonHomeById(p, h);
      System.out.println(home.getAdresse());
      if (home != null) {
        resultList = homeDAO.getHomeHeaterById(h, he);
        System.out.println(resultList);
      }
    } catch (Exception e) {
      resultList = null;
    }
    return resultList;
  }

  @POST
  @Path("/{id}/home/{idh}/heater")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Heater> postHeater(@PathParam("id") String id, @PathParam("idh") String idh, JSONObject heater) {
    System.out.println("Post heater " + heater);
    tx.begin();
    List<Heater> heaterList = null;
    try {
      int p = Integer.parseInt(id);
      int h = Integer.parseInt(idh);
      System.out.println("Post heater 2" + heater);
      Home home = homeDAO.getPersonHomeById(p, h);
      if (home != null) {
        String prix = heater.getString("prix");
        String puiss = heater.getString("puissance");
        System.out.println("Post heater 3" + home.getAdresse() + prix + puiss);
        Heater heat = heaterDAO.create(prix, puiss);
        System.out.println("Post heater 4" + heater);
        homeDAO.update(heat, home);
      }
      heaterList = home.getHeaters();
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    return heaterList;
  }

  /**
   * Supprime un Heater à une personne à partir des 2  id
   *
   * @param id
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}/home/{idh}/heater/{idhe}")
  public List<Heater> deletePersonHomeHeater(@PathParam("idh") String id, @PathParam("idh") String idh, @PathParam("idhe") String idhe) {
    System.out.println("delete p h heater ->  " + id + "," + idh + "," + idhe);
    Home home;
    List<Heater> heaterList = null;
    try {
      int p = Integer.parseInt(id);
      int h = Integer.parseInt(idh);
      int he = Integer.parseInt(idhe);
      home = homeDAO.getPersonHomeById(p, h);
      if (home != null) {
        Heater heater = homeDAO.getHomeHeaterById(h, he);
        if (heater != null) {
          heaterDAO.delete(he);
        }
        heaterList = home.getHeaters();
      }
    } catch (Exception e) {

    }
    return heaterList;
  }


  /**
   * ElectroniqueDevice
   */

  /**
   * Retourne les ElectronicDevices d'une personne
   *
   * @param id
   * @return liste de ElectronicDevice
   */
  @GET
  @Path("/{id}/electro")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ElectronicDevice> getPersonElectroDevices(@PathParam("id") String id) {
    System.out.println("Get person E_devices-> " + id);
    Person resultList = personDAO.getPersonById(Integer.parseInt(id));
    return resultList.getElectronicDevices();
  }

  /**
   * Une maison appartenant à une personne
   *
   * @param idh id de la maison
   * @return la maison ajoutée
   */
  @GET
  @Path("/{id}/electro/{idh}")
  @Produces(MediaType.APPLICATION_JSON)
  public ElectronicDevice getElectronicDevice(@PathParam("id") String id, @PathParam("idh") String idh) {
    System.out.println("Get electro " + id + "," + idh);
    int idp = 0;
    int ide = 0;
    ElectronicDevice resultList;

    try {
      idp = Integer.parseInt(id);
      ide = Integer.parseInt(idh);
      resultList = electronicDeviceDAO.getUserDeviceById(idp, ide);

    } catch (Exception e) {
      resultList = null;
    }
    return resultList;
  }

  /**
   * Supprime une ElectronicDevice à une personne à partir des 2  id
   *
   * @param id
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}/electro/{idh}")
  public void deletePersonElectronicDevice(@PathParam("idh") String id) {
    System.out.println("delete person ElectronicDevice ->  " + id);
    electronicDeviceDAO.delete(Integer.parseInt(id));
  }

  /**
   * Ajoute un ElectronicDevice à une personne
   *
   * @param device
   * @return
   */
  @POST
  @Path("/{id}/electro")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ElectronicDevice> postElectronicDevice(@PathParam("id") int id, JSONObject device) {
    System.out.println("Post ElectronicDevice " + device);
    List<ElectronicDevice> electronicDevices = null;
    tx.begin();
    try {
      ElectronicDevice electronicDevice = electronicDeviceDAO.create(device.getString("prix"), device.getString("name"),
        device.getString("marque"));
      Person person = getPersonById(id);
      personDAO.addDevice(person, electronicDevice);
      electronicDevices = person.getElectronicDevices();
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    return electronicDevices;
  }
}
