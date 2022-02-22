package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.User;

public class IntegraleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private List<User> users = new ArrayList<User>();

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
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(600);
		InfoSessione info = getInfoSessione(session);
		if (info == null) {
			info = new InfoSessione();
			info.setStart(System.currentTimeMillis());
			info.setCounter(0);
			session.setAttribute("infosessione", info);
		} else if (System.currentTimeMillis() - info.getStart() > 60 * 60 * 1000 || info.getCounter() > 20) {
			if (System.currentTimeMillis() - info.getStart() > 60 * 60 * 1000)
				session.invalidate();
			return;
		} 
		
		info.addCounter();
		System.out.println("Richiesta n :" + info.getCounter());
		BufferedReader reader = req.getReader();
		String request = reader.readLine();
		System.out.println(request);

		Gson gson = new Gson();
		String[] estremi = request.split(",");
		Float estremoInf = Float.parseFloat(estremi[0].replace("[", ""));
		Float estremoSup = Float.parseFloat(estremi[1].replace("]", ""));

		Float result = risolviIntegrale(estremoInf, estremoSup);

		resp.getOutputStream().print(gson.toJson(result));

	}

	private synchronized InfoSessione getInfoSessione(HttpSession session) {
		return (InfoSessione) session.getAttribute("infosessione");
	}

	private Float risolviIntegrale(Float estremoInf, Float estremoSup) {
		return estremoSup - estremoInf;
	}

}
