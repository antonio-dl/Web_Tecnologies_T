package servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.User;
import beans.infoSessione;

public class IndirizziServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final int timeUntilSessionExpires = 600;

	ServletContext application;
	// Mappa Ricerca Indirizzi
	Map<String, List<String>> indirizzi = new HashMap<String, List<String>>();
	Set<User> users = new HashSet<User>();
	Map<String, infoSessione> sessioniTotali = new HashMap<String, infoSessione>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		createIndirizzi();
		createUsers();

	}

	private void createUsers() { // Crea Utenti
		users.add(new User("DeLuca", "Antonio"));
		users.add(new User("Rossi", "Marco"));
		users.add(new User("Amarino", "Mallo"));

	}

	private void createIndirizzi() { // Crea Indirizzi
		indirizzi.clear();
		List<String> i = new ArrayList<String>();
		i.add("VIA PASSO");
		i.add("VIA SASSO");
		i.add("VIA DELLE TELE");
		indirizzi.put("De Luca", i);
		i = new ArrayList<String>();
		i.add("VIA NANO");
		i.add("VIA BANANO");
		indirizzi.put("Rossi", i);
		i = new ArrayList<String>();
		i.add("VIALE OSSAS");
		i.add("VIA BAMBA");
		indirizzi.put("Brando", i);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		infoSessione info = gestisciAccesso(req, resp); // Null -> forward to admin or login.jsp
		if (info != null)
			gestisciRichiesta(req, resp, info);

	}

	private void gestisciRichiesta(HttpServletRequest req, HttpServletResponse resp, infoSessione info)
			throws ServletException, IOException {
		String result = "";
		String tipoRichiesta = getTipoRichiesta(req);
		if (tipoRichiesta != null) {
			if (tipoRichiesta.equals("send")) {
				String cognome = req.getParameter("cognome");
				result = trovaIndirizzi(cognome);
				info.addQuery(result);
				req.setAttribute("result", result);
				saveSessioniToApplication();

			} else if (tipoRichiesta.equals("logout")) {
				invalidateSession(req, resp, req.getSession(), info);
			}

		}
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	private void saveSessioniToApplication() {
		application.setAttribute("sessioniTotali", sessioniTotali);

	}

	private String trovaIndirizzi(String cognome) {
		String result = "";
		if (indirizzi.containsKey(cognome)) {
			for (String i : indirizzi.get(cognome)) {
				result = result + i + "\n";
			}
		}
		return result;
	}

	private String getTipoRichiesta(HttpServletRequest req) {
		if (req.getParameter("send") != null && req.getParameter("send").equals("send")
				&& req.getParameter("cognome") != null)
			return "send";
		if (req.getParameter("logout") != null && req.getParameter("logout").equals("logout"))
			return "logout";
		return null;

	}

	private infoSessione gestisciAccesso(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		infoSessione info = null;
		if (AdminServlet.checkAdminAuthorization(req)) {
			req.getRequestDispatcher("/AdminServlet").forward(req, resp);
		} else {

			info = getInfoSessione(req, resp);
		}
		return info;
	}

	private infoSessione getInfoSessione(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(timeUntilSessionExpires);
		String idSessione = session.getId();
		infoSessione info = sessioniTotali.get(idSessione);
		if (info == null) {
			boolean isLogged = setAuth(req);
			info = new infoSessione(isLogged, timeUntilSessionExpires);
			sessioniTotali.put(idSessione, info);
		} else if (info.isStillValid()) {
			info.updateLastAction();
		} else {
			invalidateSession(req, resp, session, info);
		}
		saveSessioniToApplication();
		return info;
	}

	/**
	 * Invalida la sessione, chiude infoSessione, ritorna al login
	 * 
	 */
	private void invalidateSession(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
			infoSessione info) throws ServletException, IOException {
		session.invalidate();
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		info = null;
	}

	private boolean setAuth(HttpServletRequest req) {
		String login = req.getParameter("login");
		String usr = req.getParameter("usr");
		String psw = req.getParameter("pwd");

		if (login != null && login.equals("login") && usr != null && psw != null) {

			User temp = new User(usr,psw);
			if (users.parallelStream().anyMatch(u -> u.equals(temp))) {
				req.getSession().setAttribute("auth", true);
				return true;
			}
		}
		req.getSession().setAttribute("auth", false);
		return false;
	}

}
