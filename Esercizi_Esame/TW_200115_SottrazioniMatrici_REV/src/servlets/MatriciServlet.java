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
		BufferedReader reader = req.getReader();
		String tipoRichiesta = reader.readLine().trim();
		String[] matriceA = reader.readLine().trim().split(" ");
		String[] matriceB = reader.readLine().trim().split(" ");
		if (tipoRichiesta.equals("single")) {
			String result = sottraiMatrici(matriceA, matriceB);
			resp.getOutputStream().println(result);
		} else {
			SubMatrixThread[] thread = new SubMatrixThread[8];
			for (int i = 0; i < 4; i++) {
				int start = 2*i;
				thread[i] = new SubMatrixThread(matriceA, matriceB,start);
				thread[i].start();
			}
			String result = "";
			for (int i = 0; i < 4; i++) {
				try {
					thread[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				result += " " + thread[i].getMatriceResult();
				result = result.trim();
			}
			
			resp.getOutputStream().println(result);
			
		}

	}

	private String sottraiMatrici(String[] matriceA, String[] matriceB) {
		String result = "";
		for (int i = 0; i < matriceA.length; i++) {
			result += " " + (Float.parseFloat(matriceA[i]) - Float.parseFloat(matriceB[i]));
		}
		return result.trim();
	}

}
