package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.Indirizzo;
import beans.InfoSessione;
import beans.User;

public class IndirizziServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private List<Indirizzo> indirizzi = new ArrayList<Indirizzo>(10);
	private List<User> users = new ArrayList<User>(10);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		createIndirizzi();
		createUsers();
		application.setAttribute("infosessioni", new ArrayList<InfoSessione>(20));

	}

	private void createUsers() { // Crea Utenti
		users.add(new User("DeLuca", "Antonio"));
		users.add(new User("Rossi", "Marco"));
		users.add(new User("Amarino", "Mallo"));

	}

	private void createIndirizzi() {
		Indirizzo i = new Indirizzo();
		i.setCognome("De Luca");
		i.setVia("VIA GRANDE");
		indirizzi.add(i);

		i = new Indirizzo();
		i.setCognome("Mallo");
		i.setVia("VIA DELLE PESCHE");
		indirizzi.add(i);

		i = new Indirizzo();
		i.setCognome("Errori");
		i.setVia("VIA DELLE BANANE");
		indirizzi.add(i);

		i = new Indirizzo();
		i.setCognome("Mallo");
		i.setVia("VIA DELLE PRUGNE");
		indirizzi.add(i);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		session.setMaxInactiveInterval(300);// Intervallo di 5m
		if (isLogin(req)) {
			String usr = req.getParameter("usr");
			String pwd = req.getParameter("pwd");
			if (usr.equals("admin") && pwd.equals("admin")) {
				req.getRequestDispatcher("/admin.jsp").forward(req, resp);
			} else {
				boolean trovato = false;
				for (int i = 0; i < users.size() && !trovato; i++) {
					if (users.get(i).getUsername().equals(usr) && users.get(i).getPassword().equals(pwd)) {
						session.setAttribute("isRegistrato", true);
						trovato = true;
					}
				}
				if (trovato)
					req.getRequestDispatcher("/home.jsp").forward(req, resp);
				else
					resp.sendError(401, "Nessun utente con questo nome");
			}
		} else if (isRequest(req)) {
			List<InfoSessione> infosessioni = (List<InfoSessione>) application.getAttribute("infosessioni");
			InfoSessione currentInfoSessione = getInfoSessione(session, infosessioni);
			currentInfoSessione.setEndTime(session.getLastAccessedTime() + 1000 * 300); // Aggiorno il timer di morte

			String cognome = req.getParameter("cognome");
			String response = "Indirizzi trovati per " + cognome + ":\n";
			for (Indirizzo i : indirizzi) {
				if (i.getCognome().equals(cognome))
					response += "\n" + i.getVia();
			}
			req.setAttribute("response", response);
			req.getRequestDispatcher("home.jsp").forward(req, resp);
			currentInfoSessione.getRequests().add(response);
		}

	}

	private InfoSessione getInfoSessione(HttpSession session, List<InfoSessione> infosessioni) {
		boolean trovato = false;
		for (int i = 0; i < infosessioni.size() && !trovato; i++) {
			if (infosessioni.get(i).getId() == session.getId())
				return infosessioni.get(i);
		}
		InfoSessione result = new InfoSessione();
		result.setId(session.getId());
		result.setStartTime(System.currentTimeMillis());
		if (session.getAttribute("isRegistrato") != null)
			result.setRegistrato(true);
		else
			result.setRegistrato(false);
		infosessioni.add(result);
		return result;
	}

	private boolean isRequest(HttpServletRequest req) {
		if (req.getParameter("send") != null && req.getParameter("send").equals("send"))
			return true;
		return false;
	}

	private boolean isLogin(HttpServletRequest req) {
		if (req.getParameter("login") != null && req.getParameter("login").equals("login"))
			if (req.getParameter("usr") != null && req.getParameter("pwd") != null)
				return true;
		return false;
	}

}
