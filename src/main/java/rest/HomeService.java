package rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.HeaterDAO;
import org.codehaus.jettison.json.JSONObject;

import dao.HomeDAO;
import domain.Heater;
import domain.Home;

@Path("/home")
public class HomeService {
  EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlperso");
  EntityManager manager = factory.createEntityManager();
  EntityTransaction tx = manager.getTransaction();
  HomeDAO homeDAO = new HomeDAO();
  private HeaterDAO heaterDAO = new HeaterDAO();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Home> getHouses() {
    List<Home> resultList = manager.createQuery("Select a From Home a", Home.class).getResultList();
    System.out.println("Home quantuty :" + resultList.size());
    return resultList;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Home getHome(@PathParam("id") String id) {
    System.out.println("Getting home " + id);
    Home resultList = new Home();
    try {
      resultList = getHomeById(Integer.parseInt(id));
    } catch (Exception e) {

    }
    System.out.println("Get home " + resultList);
    return resultList;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Home postHome(JSONObject home) {
    tx.begin();
    Home h = null;
    try {
      h = homeDAO.create(home.getString("taille"), home.getString("adresse"), home.getInt("nb_pieces"));
      manager.persist(h);
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    return h;
  }

  @GET
  @Path("/{id}/heater")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Heater> getHomeHeaters(@PathParam("id") int id) {
    System.out.println("Get home heaters " + id);
    List<Heater> resultList = null;
    try {
      resultList = homeDAO.getHomeById(id).getHeaters();
    }catch (Exception e){
      resultList = null;
    }
    return resultList;
  }

  @GET
  @Path("/{id}/heater/{idh}")
  @Produces(MediaType.APPLICATION_JSON)
  public Heater getHome(@PathParam("id") int id, @PathParam("idh") int idh) {
    System.out.println("Get heater " + idh);
    Heater resultList;
    try {
      resultList = homeDAO.getHomeHeaterById(id, idh);
    }catch (Exception e){
      resultList = null;
    }
    return resultList;
  }

  @POST
  @Path("{id}/heater")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Heater> postHeater(@PathParam("id") int id, JSONObject heater) {
    System.out.println("Post heater " + heater);
    tx.begin();
    List<Heater> heaterList = null;
    try {
      Heater h = heaterDAO.create(heater.getString("prix"),heater.getString("puissance"));
      Home home = (Home) getHomeById(id);
      homeDAO.update(h, home);
      heaterList =  home.getHeaters();
    } catch (Exception e) {
      e.printStackTrace();
    }
    tx.commit();
    return heaterList;
  }

  /**
   * Supprime un Heater à une personne à partir des 2  id
   *
   * @param  id
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}/heater/{idh}")
  public void deletePersonElectronicDevice(@PathParam("idh") int id, @PathParam("idh") int idh) {
    System.out.println("delete heater ->  "+idh);
    try {
      Heater heater = homeDAO.getHomeHeaterById(id,idh);
      if (heater != null){
        heaterDAO.delete(idh);
      }
    }catch (Exception e){

    }
  }


  public Home getHomeById(int id) {
    return manager.createQuery("Select a From Home a where id =" + id, Home.class).getSingleResult();
  }

  /**
   * Supprimer les residences dont l'adresse est pass� en param�tre
   * param : @adresse : l'adresse des r�sidences a supprimer
   *
   * @param id
   */
  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void deleteHomeById(@PathParam("id") int id) {
    homeDAO.deleteById(id);
  }
}
