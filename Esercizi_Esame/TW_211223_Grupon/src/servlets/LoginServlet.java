package servlets;

import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Gruppi;
import beans.User;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private List<User> users = new ArrayList<User>();
	private static final String fowardLocation = "selectGroup.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		createUsers();
		application.setAttribute("users", users);
		createGruppiJSON("gruppi.json");

	}

	private void createGruppiJSON(String string) {
		String path = application.getRealPath("gruppi.json");
		System.out.println("Path: " + path);
		try (PrintWriter pw = new PrintWriter(path)) {
			Gson gson = new Gson();
			List<String> lista = new ArrayList<String>(4);
			lista.add("Gruppo 1");
			lista.add("Gruppo 2");
			lista.add("Gruppo 3");
			Gruppi gruppi = new Gruppi();
			gruppi.setListaGruppi(lista);
			gson.toJson(gruppi, pw);

		} catch (IOException e) {
			e.printStackTrace();
		}

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
		System.out.println("Ricevuta nuova richiesta");
		String usr = req.getParameter("usr");
		String pwd = req.getParameter("pwd");
		String type = req.getParameter("login");

		if (type != null && type.equals("registra") && usr != null && pwd != null) {
			User newUser = new User(usr, pwd);
			users.add(newUser);
			req.getSession().setAttribute("user", newUser);
			req.getRequestDispatcher(fowardLocation).forward(req, resp);

		} else if (type != null && type.equals("login") && usr != null && pwd != null) {
			User search = new User(usr, pwd);
			int index;
			if ((index = users.indexOf(search)) != -1) {
				req.getSession().setAttribute("user", users.get(index));
				req.getRequestDispatcher(fowardLocation).forward(req, resp);

			} else {
				throw new ServletException("Can't find user");
			}
		} else {
			throw new ServletException("Can't understand request");
		}

	}

}
