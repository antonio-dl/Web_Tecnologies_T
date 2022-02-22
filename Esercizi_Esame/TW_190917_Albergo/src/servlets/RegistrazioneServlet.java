package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.Albergo;
import beans.Prenotazione;
import beans.Richiesta;

public class RegistrazioneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;

	private List<Albergo> alberghi = new ArrayList<Albergo>();
	private Set<Richiesta> poolRichieste = new HashSet<Richiesta>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		try {
			File file = new File(application.getRealPath("lista.txt"));
			inizializzaListaAlberghi(new FileReader(file));
			savePrenotazioni();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

	private void inizializzaListaAlberghi(FileReader fileReader) throws IOException {
		BufferedReader reader = new BufferedReader(fileReader);
		String line = null;
		while ((line = reader.readLine()) != null) {
			String a[] = line.split(",");
			alberghi.add(new Albergo(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Double.parseDouble(a[2])));
		}
		reader.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("hotel"));
		int din = Integer.parseInt(req.getParameter("din"));
		int dout = Integer.parseInt(req.getParameter("dout"));

		Albergo richiesto = trovaAlbergo(id);
		int stanza;
		if (richiesto != null) {
			stanza = richiesto.getCameraLibera(din, dout);
			poolRichieste.add(new Richiesta(id, din, dout));
			double prezzo = 0;
			if (stanza > 0) {
				prezzo = calcolaPrezzo(richiesto, din, dout);
			}
			req.setAttribute("result", String.valueOf(prezzo));

			Prenotazione richiestaPren = new Prenotazione(stanza, din, dout);
			req.getSession().setAttribute("albergoRichiesto", richiesto);
			req.getSession().setAttribute("prenRichiesta", richiestaPren);
		}
		req.getRequestDispatcher("home.jsp").forward(req, resp);

	}

	private double calcolaPrezzo(Albergo richiesto, int din, int dout) {
		return richiesto.getPrezzo() * (dout - din) * (1f + calcolaMaggiorazione(richiesto, din, dout));

	}

	private double calcolaMaggiorazione(Albergo richiesto, int din, int dout) {
		double percAumento = 0.10f;
		long numeroInteressati = poolRichieste.stream()
				.filter(r -> (r.getIdAlbergo() == richiesto.getId() && r.doesCollide(din, dout))).count();
		return numeroInteressati * percAumento;
	}

	private Albergo trovaAlbergo(int id) {
		for (Albergo a : alberghi) {
			if (a.getId() == id)
				return a;
		}
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sessione = req.getSession();

		Prenotazione richiestaPren = (Prenotazione) sessione.getAttribute("prenRichiesta");
		Albergo albergoRichiesto = (Albergo) sessione.getAttribute("albergoRichiesto");
		String reqType = req.getParameter("accept");
		if (richiestaPren != null && albergoRichiesto != null && reqType != null) {
			if (reqType.equals("accept")) {
				albergoRichiesto.addPrenotazione(richiestaPren.getCamera(), richiestaPren.getInizio(),
						richiestaPren.getFine());
				savePrenotazioni();
			}
		}
		sessione.invalidate();
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}

	private synchronized void savePrenotazioni() {
		application.setAttribute("alberghi", alberghi);

	}
}
