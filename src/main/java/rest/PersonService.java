package rest;

import dao.PersonDAO;
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

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> getPersons() {
    List<Person> resultList = manager.createQuery("Select a From Person a", Person.class).getResultList();
    System.out.println("Person quantuty :" + resultList.size());
    return resultList;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Person getPerson(@PathParam("id") String id) {
    System.out.println("Get person -> " + id);
    Person resultList = personDAO.getPersonById(Integer.parseInt(id));
    return resultList;
  }

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

  @POST
  @Path("/home")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Person> postHome(JSONObject hom) {
    System.out.println("Post Home " + hom);
    tx.begin();
    try {
      Home home = new Home();
      Person person = personDAO.findById((int) hom.getLong("person_id"));
      home.setName(hom.getString("name"));
      home.setAdresse(hom.getString("adresse"));
      person.addHome(home);
      manager.persist(home);
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
    System.out.println("delete person -> "+id);
    PersonDAO PersonDAO = new PersonDAO();
    PersonDAO.deleteById(id);

  }


}



