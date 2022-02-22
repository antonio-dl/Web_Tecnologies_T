package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import beans.Matrici;

public class SingleMatrixServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ServletContext application;
	int counter = 0;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		application.setAttribute("time", System.currentTimeMillis());
		application.setAttribute("counter", 0);
		application.setAttribute("sessioni", new ArrayList<HttpSession>(20));
		

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long start = (Long) application.getAttribute("time");
		long now = System.currentTimeMillis();

		if(now - start >= 3600000) {
			counter = 0;
			application.setAttribute("time", now);
			
		}
		counter++;
		application.setAttribute("counter", counter);
		
		HttpSession currentSession = req.getSession();
		if(currentSession.isNew()) {
			List<HttpSession> sessioni = (List<HttpSession>) application.getAttribute("sessioni");
			sessioni.add(currentSession);
			application.setAttribute("sessioni",sessioni);
		}
		
		Gson gson = new Gson();

		Matrici matrici = null;
		matrici = gson.fromJson(req.getReader(), Matrici.class);
		int[] result = matrici.subtract();

		resp.getWriter().println(gson.toJson(result));

	}

}
