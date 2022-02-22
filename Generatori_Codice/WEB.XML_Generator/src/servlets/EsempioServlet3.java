package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.User;

public class EsempioServlet3 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private List<User> users = new ArrayList<User>();
	private static final String fowardLocation = "admin.jsp";

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
		String type = req.getParameter("login");

		// if (usr != null && pwd != null) {
		// if (usr.equals("admin") && pwd.equals("admin")) {
		// req.getRequestDispatcher("admin.jsp").forward(req, resp);
		// return;
		// } else {
		// User user = getUser(usr, pwd);
		// }
		// }
		if (type != null && type.equals("registra")) {
			User newUser = new User(usr, pwd);
			users.add(newUser);
			req.getSession().setAttribute("usr", newUser);
			req.getRequestDispatcher(fowardLocation).forward(req, resp);
			
		} else if (type != null && type.equals("login")) {
			User search = new User(usr, pwd);
			int index;
			if ((index = users.indexOf(search)) != -1) {
				req.getSession().setAttribute("usr", users.get(index));
				req.getRequestDispatcher(fowardLocation).forward(req, resp);
				
			} else {
				throw new ServletException("Can't find user");
			}
		} else {
			throw new ServletException("Can't understand request");
		}

	}

}
