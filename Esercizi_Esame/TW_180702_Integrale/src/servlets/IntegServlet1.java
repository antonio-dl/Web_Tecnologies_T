package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import beans.User;

public class IntegServlet1 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		HttpSession session = req.getSession();
		
		int nRichieste = (int) session.getAttribute("req");
		if (nRichieste > 5) {
			
			System.out.println("Eccedute Richieste");
			return;
		}
					
		Gson gson = new Gson();
		String request = req.getReader().readLine();
		System.out.println("Richiesta: " + request);
		String lines[] = request.split(" ");
		float parsedA = gson.fromJson(lines[0],float.class);
		float parsedB = gson.fromJson(lines[1],float.class);
		
		float result = calcolaIntegrale(parsedA,parsedB);
		System.out.println("Risultato: " + result);
		resp.getWriter().print(gson.toJson(result));
		
	}

	private float calcolaIntegrale(float estrInf, float estrSup) {
		return estrSup - estrInf;
	}

}
