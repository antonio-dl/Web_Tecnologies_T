package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.infoSessione;

public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final int oreRecenti = 24;

	ServletContext application;

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
		if (checkAdminAuthorization(req)) {
			processRequest(resp.getWriter());
		} else {
			resp.sendError(403, "Bad request");
		}
	}

	private void processRequest(PrintWriter out) {
		// TODO Sostituire la richiesta con una valida

		@SuppressWarnings("unchecked")
		Map<String, infoSessione> mappa = (Map<String, infoSessione>) application.getAttribute("sessioniTotali");
		out.println("<HTML><HEAD><TITLE>Hello World!</TITLE>" + "</HEAD><BODY><H1>Hello World!</H1>");
		printHtmlStats(mappa, out);

		out.print("</BODY></HTML>");

		out.close();
	}

	private void printHtmlStats(Map<String, infoSessione> mappa, PrintWriter out) {
		if (mappa == null || mappa.isEmpty()) {
			out.println("<H3>Nessuna sessione!</H3>");
		} else {
			int notActiveCounter = 0;
			int totalCounter = 0;
			int notAuthCounter = 0;
			int notAuthNorActiveCounter = 0;
			out.println("<p>");

			for (Map.Entry<String, infoSessione> entry : mappa.entrySet()) {
				if (isFresh(entry.getValue())) {
					totalCounter++;
					String id = entry.getKey();
					infoSessione info = entry.getValue();
					if (!info.isLogged())
						notAuthCounter++;
					out.println("id: " + id + "<br>");
					out.println("created: " + info.getCreated().toString() + "<br>");
					if (!info.isStillValid()) {
						out.println("ended: " + info.getEnd().toString() + "<br>");
						notActiveCounter++;
						if (!info.isLogged())
							notAuthNorActiveCounter++;
					}
					out.println("ricerche:");

					for (String s : info.getQueries()) {
						out.println("\t\t[" + s + "]<br>");
					}
					out.println("<hr>");
				}

			}
			out.println("</p>");
			out.println("<p>");
			printStats(out, notAuthCounter, notActiveCounter, totalCounter, notAuthNorActiveCounter);
			out.println("</p>");

		}
	}

	private void printStats(PrintWriter out, int notAuthCounter, int notActiveCounter, int totalCounter, int notAuthNorActiveCounter) {
		out.println();
		out.println("<hr>");
		out.println();
		out.println("<p>");
		out.println("<b> Utenti nelle ultime " + oreRecenti + " ore: </b> " + totalCounter + "<br>");
		out.println("<b> Utenti nelle ultime " + oreRecenti + " ore non autenticati: </b> " + notAuthCounter + "<br>" );
		out.println("<b> Utenti in sessione attiva: </b> " + (totalCounter - notActiveCounter) + "<br>");
		out.println("<b> Utenti in sessione attiva non autenticati: </b> " + (notAuthCounter - notAuthNorActiveCounter) + "<br>");
		out.println("<hr>");
	}

	private boolean isFresh(infoSessione info) {
		if (info.isStillValid() && info.getEnd().plusHours(oreRecenti).isAfter(LocalDateTime.now())) {
			return true;
		}
		return false;
	}

	static boolean checkAdminAuthorization(HttpServletRequest req) {
		String login = req.getParameter("login");
		String usr = req.getParameter("usr");
		String psw = req.getParameter("pwd");

		if (login != null && usr != null && usr != null)
			if (login.equals("login") && usr.equals("admin") & psw.equals("admin"))
				return true;

		return false;

	}
}