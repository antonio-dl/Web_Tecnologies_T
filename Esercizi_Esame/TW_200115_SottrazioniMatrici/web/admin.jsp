<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page session="true"%>


<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
</head>
<%!boolean isAuthenticated(HttpServletRequest req) {
		String usr = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		if (usr != null && pwd != null && usr.equals("admin") && pwd.equals("admin")) {
			return true;
		}
		return false;
	}%>



<body>
	<%
	if (isAuthenticated(request)) {
		List<HttpSession> sessioni = (List<HttpSession>) application.getAttribute("sessioni");
		
		int counter = (Integer) application.getAttribute("counter");
	%>
<h4>Admin page</h4>
<p>Numero di sessioni attive: <%= sessioni.size() %> </p>
<p>Numero di richieste: <%= counter %> </p>

	<% } else { %>
	<h3>Inserire le credenziali di amministratore:</h3>
	<form action="admin.jsp" method="post">

		<p>
			Username:&nbsp;<input type="text" name="user">
		</p>
		<p>
			Password:&nbsp;<input type="text" name="pwd">
		</p>
		<input type="submit" name="login" value="login">
	</form>
	<%
	}
	%>

</body>
</html>