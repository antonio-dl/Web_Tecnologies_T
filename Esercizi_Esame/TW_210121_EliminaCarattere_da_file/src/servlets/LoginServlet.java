package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.User;

/**
 * Servlet di login con registrazione e alcuni account gia'inclusi Salvato nella
 * sessione Aggiunta della lista di utenti nel contesto dell applicazione
 * 
 * @author De Luca
 * 
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private List<User> users = new ArrayList<User>();
	private Set<HttpSession> sessions = new HashSet<HttpSession>();
	private static final String fowardLocation = "home.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		createUsers();
		application.setAttribute("users", users);
		application.setAttribute("sessions", sessions);
		

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

		System.out.println(usr);
		System.out.println(pwd);
		System.out.println(type);

		if (usr != null && pwd != null) {
			if (usr.equals("admin") && pwd.equals("admin")) {
				req.getRequestDispatcher("admin.jsp").forward(req, resp);
				return;
			}
		}

		if (type != null && type.equals("registra")) {
			User newUser = new User(usr, pwd);
			users.add(newUser);
			req.getSession().setAttribute("usr", newUser);
			sessions.add(req.getSession());
			req.getRequestDispatcher(fowardLocation).forward(req, resp);

		} else if (type != null && type.equals("login")) {
			User search = new User(usr, pwd);
			int index;
			if ((index = users.indexOf(search)) != -1) {
				req.getSession().setAttribute("usr", users.get(index));
				sessions.add(req.getSession());
				req.getRequestDispatcher(fowardLocation).forward(req, resp);
				

			} else {
				throw new ServletException("Can't find user");
			}
		} else {
			throw new ServletException("Can't understand request");
		}

	}

}
