package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import beans.Matrici;

public class MultiMatrixServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
	
		Matrici m = null;
		try {
			m = gson.fromJson(req.getReader(), Matrici.class);
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			System.out.println("error parsing");
			e.printStackTrace();
		}
		
		
		int[] result = m.subtract();
		
		
		resp.getWriter().println(gson.toJson(result));
		
		
		
	}

}
