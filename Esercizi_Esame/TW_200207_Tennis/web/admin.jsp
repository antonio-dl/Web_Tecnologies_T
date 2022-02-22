<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page session="true"%>


<%@ page import="java.time.*"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.Campo"%>


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
	%>
	<h4>Lista dei campi e delle prenotazioni</h4>
	<%
	List<Campo> campi = (List<Campo>) application.getAttribute("campi");
	for (Campo c : campi) {
	%><p>
		Campo: <%=c.getId()%>
		<br>
		<%
		if (!c.isPrenotabile()) {
			LocalDateTime orario = c.getPrenotazione().getOrario();
			long durata = Duration.between(LocalDateTime.now(), orario.plusHours(72)).toMinutes();
		%>
		minuti mancanti alla prenotazione:
		<%=durata%><br>
		<%
		}
		%>
	</p>
	<%
	}
	
	} else { %>
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