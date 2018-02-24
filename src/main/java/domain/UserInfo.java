package domain;

import jdk.nashorn.internal.ir.RuntimeNode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "userinfo", urlPatterns = { "/UserInfo" })
public class UserInfo extends HttpServlet {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysqlperso");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction tx = manager.getTransaction();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		tx.begin();
		try {
			createPerson(request.getParameter("name"), request.getParameter("firstname"), request.getParameter("age"));
			
			response.setContentType("text/html");			
			PrintWriter out = response.getWriter();
			out.println("<HTML>\n<BODY>\n" + "<H1>Add success </H1>\n" + "<UL>\n" + " <LI>Nom: "
					+ request.getParameter("name") + "\n" + " <LI>Prenom: " + request.getParameter("firstname") + "\n"
					+ " <LI>Age: " + request.getParameter("age") + "\n" + "</UL>\n\n");
			
			outPersonList(request, response);
			
			out.println("</BODY></HTML>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		manager.close();
		factory.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		tx.begin();
		try {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();

			out.println("<HTML>\n<BODY>\n" + "<H1>Recapitulatif des informations des personnes </H1>\n");

			outPersonList(req, resp);

			out.println("</BODY></HTML>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		manager.close();
		factory.close();
	}

	public void createPerson(String name, String firstname, String age) {

		Person p = new Person();
		p.setName(name);
		p.setFirstname(firstname);
		p.setAge(age);
		manager.persist(p);
	}

	public void outPersonList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Person> resultList = manager.createQuery("Select a From Person a", Person.class).getResultList();
		System.out.println("List of Person:" + resultList.size());

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<table>\n  <tr>\n  <th>Nom</th>\n  <th>Firstname</th> \n <th>Age</th>\n </tr> \n ");

		for (Person next : resultList) {
			out.println(" <tr> > \n  <td>" + next.getName() + "</td> \n  <td>" + next.getFirstname() + "</td> \n  <td>"
					+ next.getAge() + "</td> \n  </tr> \n ");
		}

		out.println("</table> ");

	}
}
