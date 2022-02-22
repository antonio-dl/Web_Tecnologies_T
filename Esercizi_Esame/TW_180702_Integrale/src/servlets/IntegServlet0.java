package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;


public class IntegServlet0 extends HttpServlet {

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
		HttpSession session = req.getSession();
		
		
		if (session.getAttribute("req") == null || session.getAttribute("fistAction") == null) {
			session.setAttribute("req", 0);
			session.setAttribute("fistAction", System.currentTimeMillis());
			session.setMaxInactiveInterval(600);
		}
		
		int nRichieste = (int) session.getAttribute("req");
		long firstAction = (long) session.getAttribute("fistAction");
		
		long currentTime = System.currentTimeMillis();
		if (currentTime - firstAction >= 60 * 60 * 1000) {
			session.invalidate();
			this.doPost(req, resp);
		}
		if (nRichieste > 5) {
			System.out.println("Eccedute Richieste");
			return;
		}
		nRichieste++;
		session.setAttribute("req", nRichieste);
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
