package rest;

import java.io.PrintWriter;
import java.security.Permissions;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import domain.Person;

@Path("/persons")
public class SampleWebService {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlperso");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction tx = manager.getTransaction();
	
	@GET
//	@Path("/persons")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getHomes() {
		List<Person> resultList = manager.createQuery("Select a From Home a", Person.class).getResultList();
		System.out.println("List of Home:" + resultList.size());
        return resultList;
    }
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject postHome() {
        try {
			return new JSONObject().put("msg", "Hello, how are you? POST");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}

