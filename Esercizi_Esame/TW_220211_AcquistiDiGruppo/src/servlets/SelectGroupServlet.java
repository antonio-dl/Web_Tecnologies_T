package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Gruppi;
import beans.User;
import beans.UserSession;

public class SelectGroupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private List<User> users = new ArrayList<User>();
	private Map<String, List<UserSession>> mappa = new HashMap<String, List<UserSession>>(5);
	private static final String fowardLocation = "carrello.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		String path = application.getRealPath("gruppi.json");
		Gruppi gruppi = null;
		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
			Gson gson = new Gson();
			gruppi = gson.fromJson(bf, Gruppi.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String s : gruppi.getListaGruppi()) {
			mappa.put(s, new ArrayList<UserSession>(4));
		}
		application.setAttribute("mappa", mappa);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String gruppo = req.getParameter("gruppo");
		User currentUser = (User) session.getAttribute("user");
		if (currentUser != null && gruppo != null) {
			session.setAttribute("gruppo", gruppo);
			UserSession us = new UserSession(currentUser, false);
			mappa.get(gruppo).add(us);
			req.getRequestDispatcher(fowardLocation).forward(req, resp);

		} else {
			throw new ServletException("Can't understand request");
		}

	}

}
