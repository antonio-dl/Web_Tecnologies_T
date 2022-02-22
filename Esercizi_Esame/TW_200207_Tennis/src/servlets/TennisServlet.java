package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.Campo;
import beans.Prenotazione;
import beans.User;

public class TennisServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;

	private List<User> users = new ArrayList<User>();
	private List<Campo> campi = new ArrayList<Campo>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		createUsers();
		inizializeCampi();

	}

	private void inizializeCampi() {
		for (int i = 0; i <= 4; i++) {

			campi.add(new Campo(i));
		}

	}

	private void createUsers() { // Crea Utenti
		users.add(new User("De Luca", "Antonio", 'A'));
		users.add(new User("Rossi", "Marco", 'B'));
		users.add(new User("Amarino", "Mallo", 'C'));

	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("login") != null && req.getParameter("login").equals("login")) {
			doLogin(req, resp);
		} else if (req.getParameter("prenota") != null && req.getParameter("prenota").equals("prenota")) {
			doPrenotazione(req, resp);

		} else if (req.getParameter("finalizza") != null && req.getParameter("finalizza").equals("finalizza")) {
			doFinalizza(req, resp);

		}
		
			updateCampi();
			saveCampi();

	}

	private synchronized void saveCampi() {
		application.setAttribute("campi", campi);
	}

	private void updateCampi() {

		for(Campo c : campi) {
			if(c.isGiocando()) {
				if(c.getPrenotazione().getOrario().plusHours(1).isBefore(LocalDateTime.now())) { // Partita finita
					c.eliminaPrenotazione();
					
				}
			}
			if(!c.isPrenotabile()) {
				if(c.getPrenotazione().getOrario().plusHours(72).isBefore(LocalDateTime.now())) {
					c.getPrenotazione().getGiocatore1().setMessaggio("La tua prenotazione e' stata annullata :(");
					c.eliminaPrenotazione();
					
				}
			}
		}
	}

	private void doFinalizza(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute("user");
		boolean trovato = false;
		String response = "Nessun match trovato :(";
		for (Campo c : campi) {
			if (!c.isPrenotabile() && !trovato) {
				Prenotazione p = c.getPrenotazione();
				if (p.getGiocatore1().getAbilita() == u.getAbilita()) {
					p.setGiocatore2(u);
					trovato = true;
					response = "Match Trovato! Contro ="+ p.getGiocatore1().getUsername() + "  Data: " + p.getOrario().toString();
				}
			}
			if (trovato) {
				req.setAttribute("response", response);
			} else {
				req.setAttribute("response", response);
			}
			

		}
		
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	private void doPrenotazione(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute("user");
		boolean trovato = false;
		String response = "";
		for (Campo c : campi) {
			if (c.isPrenotabile() && !trovato) {
				Prenotazione p = new Prenotazione(u, LocalDateTime.now());
				c.setPrenotazione(p);
				trovato = true;
				response = "Trovato campo " + c.getId() + "!";
			}
		}
		if (trovato) {
			req.setAttribute("response", response);
		} else {
			response = "Nessun campo libero :(";
			req.setAttribute("response", response);
		}

		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		User u = authenicateUser(req);
		session.setAttribute("user", u);
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	private User authenicateUser(HttpServletRequest req) {
		String usr = req.getParameter("usr");
		String pwd = req.getParameter("pwd");
		for (User u : users) {
			if (u.getUsername().equals(usr) && u.getPassword().equals(pwd)) {
				return u;
			}

		}
		char abilita = req.getParameter("abilita").toUpperCase().charAt(0);
		User u = new User(usr, pwd, abilita);
		users.add(u);
		return u;
	}

}
