package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.User;

public class MatriciServlet extends HttpServlet {

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
//		HttpSession session = req.getSession();
//		Integer counter = (Integer) session.getAttribute("counter");
//		if (counter == null) {
//			counter = 0;
//			session.setAttribute("counter", counter);
//		}
//		counter++;
//		System.out.println("Richiesta n.: " + counter);
//		if (counter <= 10) {
			BufferedReader reader = req.getReader();
			String[] matriceA = reader.readLine().trim().split(" ");
			String[] matriceB = reader.readLine().trim().split(" ");
			String result = sommaMatrici(matriceA, matriceB);
			System.out.println("Size Matrice A:" + matriceA.length);
			System.out.println("Size Matrice B:" + matriceB.length);
			System.out.println("Risultato:" + result);
			resp.getOutputStream().println(result);

//		} else {
//
//			req.getRequestDispatcher("error.html").forward(req, resp);
//		}
	}

	private String sommaMatrici(String[] matriceA, String[] matriceB) {
		String result = "";
		for (int i = 0; i < matriceA.length; i++) {
			result += " " + (Integer.parseInt(matriceA[i]) + Integer.parseInt(matriceB[i]));
		}
		return result.trim();
	}

}
