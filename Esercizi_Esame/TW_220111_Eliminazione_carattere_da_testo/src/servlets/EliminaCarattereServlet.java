package servlets;


import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.User;

public class EliminaCarattereServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private List<User> users = new ArrayList<User>();
	private static final String fowardLocation = "home.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestText = req.getParameter("request");

		if (requestText != null) {
			Random rnd = new Random();
			int randChar = rnd.nextInt(27);
			String alfabeto = "abcdefghijklmnopqrstuvwxyz";
			char daEliminare = alfabeto.charAt(randChar);
			String toString = "" + daEliminare;
			System.out.println("Carattere da eliminare: " + daEliminare);
			String result = requestText.replaceAll(toString, "");
			req.setAttribute("request", result);
			req.getRequestDispatcher("eliminaCarattere.jsp").forward(req, resp);

		} else {
			throw new ServletException("Can't understand request");
		}

	}

}
