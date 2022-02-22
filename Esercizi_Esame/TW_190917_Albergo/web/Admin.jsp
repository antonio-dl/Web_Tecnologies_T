<%@page import="com.sun.glass.ui.Application"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page session="true"%>


<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="beans.Albergo"%>
<%@ page import="beans.Prenotazione"%>
<%@ page import="beans.Richiesta"%>

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
		List<Albergo> alberghi;
		try {
			alberghi = (ArrayList<Albergo>) application.getAttribute("alberghi");
		} catch (Exception e) {
			throw e;
		}
	%>
	<h2>Lista alberghi e le loro prenotazioni</h2>
	<%
	for (Albergo a : alberghi) {
		Set<Integer> stanze = a.getPrenotazioni().keySet();
	%>
	<p>
		ID:
		<%=a.getId()%>
		<br>
		<%
		for (Integer s : stanze) {
		%>
		Prenotazioni stanza:
		<%=s.intValue()%>
	<ul>
		<%
		for (Prenotazione p : a.getPrenotazioni().get(s)) {
		%>
		<li>Inizio: <%=p.getInizio()%>&nbsp;&nbsp;&nbsp;&nbsp;Fine: <%=p.getFine()%>
		</li>
		<%
		}
		%>
	</ul>
	<%
	}
	%>
	<hr>
	<%
	}
	} else {
	%>
	<h3>Inserire le credenziali di amministratore:</h3>
	<form action="Admin.jsp" method="post">

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