package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.User;

public class AutoCompletamentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private List<Gruppo> gruppi = new ArrayList<Gruppo>();
	private List<User> users = new ArrayList<User>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		createUsers();

	}

	private void createUsers() { // Crea Utenti
		users.add(new User("De Luca", "Antonio"));
		users.add(new User("Rossi", "Marco"));
		users.add(new User("Amarino", "Mallo"));

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
