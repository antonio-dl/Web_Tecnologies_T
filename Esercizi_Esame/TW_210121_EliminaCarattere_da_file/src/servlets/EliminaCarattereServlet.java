package servlets;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EliminaCarattereServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext application;
	private static final String fowardLocation = "eliminaCarattere.jsp";
	private List<Long> timeStampsRequest = new ArrayList<Long>(20);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = this.getServletContext();
		application.setAttribute("timeStampRequest", timeStampsRequest);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer counter = (Integer) req.getSession().getAttribute("counter");
		if(counter == null) {
			counter = 0;
			req.getSession().setAttribute("counter", 0);
		}
		counter++;
		req.getSession().setAttribute("counter", counter);
		
		String requestText = req.getParameter("request");
		String path = application.getRealPath(requestText);
		System.out.println(path);
		
		
		FileReader fr = new FileReader(path);
		BufferedReader bf = new BufferedReader(fr);
		
		FileWriter fw = new FileWriter(application.getRealPath(requestText + "temp123"));
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		
		Random rnd = new Random();
		int randChar = rnd.nextInt(27);
		String alfabeto = "abcdefghijklmnopqrstuvwxyz";
		if(rnd.nextBoolean())
			alfabeto = alfabeto.toUpperCase();
		char daEliminare = alfabeto.charAt(randChar);
		System.out.println("Servlet: Carattere da eliminare: " + daEliminare);
		
		int readedChar;
		while((readedChar = bf.read()) != -1) {
			if((char) readedChar != daEliminare)
				bw.write((char)readedChar);
		}
		
		File file = new File(application.getRealPath(requestText + "temp123"));
		file.renameTo( new File(requestText));
		
		bf.close();
		bw.close();
		
		
		req.getRequestDispatcher(fowardLocation).forward(req, resp);
		timeStampsRequest.add(System.currentTimeMillis());
		
	}

}
