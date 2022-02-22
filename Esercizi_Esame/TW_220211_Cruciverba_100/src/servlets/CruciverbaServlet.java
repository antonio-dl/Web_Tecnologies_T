package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

public class CruciverbaServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	private ServletContext application;

	private List<String> cruciverba = new ArrayList<String>(400);
	private List<String> soluzioni = new ArrayList<String>(20);

	public static void main(String[] args) {
		Character c = 'a';
		for (int j = 0; j < 10; j++) {

			for (int i = 0; i < 10; i++) {
				System.out.format("%c", 'a' + j);
			}
			System.out.println();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		for (int i = 0; i < 100; i++) {
			cruciverba.add("-");
		}
		try {
			// BufferedReader bf = new BufferedReader(new
			// FileReader(application.getRealPath("cruciverba.txt")));
			// soluzioni = Arrays.asList(bf.readLine().split(" "));

			soluzioni = Files.readAllLines(Paths.get(application.getRealPath("parole_valide.txt")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Ricevuto GET");
		String result = "";
		resp.setCharacterEncoding("UTF-8");
		Writer rw = resp.getWriter();
		for (int i = 0; i < 100; i++) {
			result += cruciverba.get(i) + " ";
		}
		System.out.println("Inviando:" + result.trim());
		rw.write(result.trim());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("RicevutoPost:");
		String request = req.getReader().readLine();
		String[] array = request.split(" ");
		String cell = array[0];
		String cr = array[1];

		int index = Integer.parseInt(cell.substring(1));
		System.out.println("Posizione: " + index + " Carattere: " + cr);

		cruciverba.set(index, cr.substring(0, 1));

		checkCruciverbaOrizzontale();
		checkCruciverbaVerticale();
		doGet(req, resp);

	}

	private void checkCruciverbaOrizzontale() {
		for (int i = 0; i < 10; i++) {
			String riga = "";
			for (int j = 0; j < 10; j++) {
				riga += cruciverba.get(i * 10 + j);
			}
			String[] split = riga.split("€");
			for (String s : split) {
				if (soluzioni.contains(s)) {
					System.out.println("Trovata soluzione alla riga:" + i + "!!!!");
				}
			}
		}

	}

	private void checkCruciverbaVerticale() {
		for (int i = 0; i < 10; i++) {
			String colonna = "";
			for (int j = 0; j < 10; j++) {
				
				colonna += cruciverba.get(i + j * 10);
			}
			String[] split = colonna.split("€");
			for (String s : split) {
				if (soluzioni.contains(s)) {
					System.out.println("Trovata soluzione alla colonna:" + i + "!!!!");
				}
			}
		}

	}

}
