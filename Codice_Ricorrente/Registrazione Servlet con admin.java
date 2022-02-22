package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.User;

public class EsempioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	List<User> users = new ArrayList<User>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		createUsers();

	}

	private void createUsers() { // Crea Utenti
		users.add(new User("DeLuca", "Antonio"));
		users.add(new User("Rossi", "Marco"));
		users.add(new User("Amarino", "Mallo"));
		users.add(new User("Pino", "Cammino"));
		users.add(new User("Salvo", "Errori"));
		

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usr = req.getParameter("usr");
		String pwd = req.getParameter("pwd");

		if (usr != null && pwd != null) {
			if (usr.equals("admin") && pwd.equals("admin")) {
				req.getRequestDispatcher("admin.jsp").forward(req, resp);
				return;
			} else {
				User user = getUser(usr, pwd);
			}

		}
	}

	private User getUser(String usr, String pwd) {
		User temp = new User(usr, pwd);
		for (Users u : users) {
			if (u.equals(temp)) {
				return u;
			}
		}
		return temp;
	}

}
