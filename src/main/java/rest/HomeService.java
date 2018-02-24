package rest;

import java.io.PrintWriter;
import java.security.Permissions;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import domain.Heater;
import domain.Home;

@Path("/home")
public class HomeService {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlperso");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction tx = manager.getTransaction();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Home> getHomes() {
		List<Home> resultList = manager.createQuery("Select a From Home a", Home.class).getResultList();
		System.out.println("Home quantuty :" + resultList.size());
		return resultList;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Home getHome(@PathParam("id") String id) {
		System.out.println("Get " + id);
		Home resultList = getHomeById(Integer.parseInt(id));
		return resultList;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Home> postHome(JSONObject home) {
		tx.begin();
		try {
			Home h = new Home();
			h.setName(home.getString("name"));
			manager.persist(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		return getHomes();
	}
	
	@POST
	@Path("/heater")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Home> postHeater(JSONObject heater) {
		System.out.println("Post heater " + heater);
		tx.begin();
		try {
			Heater h = new Heater();
			Home home = (Home) getHomeById((int)heater.getLong("home_id"));
			h.setName(heater.getString("name"));
			
			home.addHeater(h);
			manager.persist(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		return getHomes();
	}
	
	public Home getHomeById(int id) {
		return manager.createQuery("Select a From Home a where id =" + id, Home.class).getSingleResult();
	}
}
