package servlets;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import beans.User;

public class ContaMaiusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private String dir;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		String nameDir = application.getInitParameter("dir");
		this.dir = application.getRealPath(nameDir);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long start = System.nanoTime();

		String request = req.getReader().readLine();
		System.out.println(request);
		String array[] = request.split("@");
		String result = countMaiusc(array);
		
		long passato = System.nanoTime() - start;
		result += "Tempo impiegato: " + passato + "ns!";
		System.out.println(result);
		resp.getWriter().print(result);
		
	}

	private String countMaiusc(String[] array) {
		String result = "";
		for (String s : array) {
			result += s + " numero maiuscole: " + contaMaiuscole(s) + "\n";
		}
		
		return result;
	}

	private int contaMaiuscole(String s) {
		int numMaiuscole = 0;
		try {
			String path = dir + "\\" + s;
			List<String> lines = Files.readAllLines(Paths.get(path));
			for (String str : lines) {
				str = str.replaceAll("[^A-Z]+", "");
				numMaiuscole += str.trim().length();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return numMaiuscole;

	}

}
