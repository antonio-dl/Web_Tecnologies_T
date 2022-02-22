<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page session="true"%>


<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>


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
		%> <h3>BENVENUTO AMMINISTRATORE!</h3> <%
		
		List<InfoSessione> infoSessioni = (List<InfoSessione>) application.getAttribute("infosessioni");
		
		%><h4>Grandezza sessioni: <%= infoSessioni.size() %> </h4><%
		
		for (InfoSessione i : infoSessioni) {
			if (System.currentTimeMillis() - i.getEndTime() < 24 * 60 * 60 * 1000) {
	%>
	<p>
		<%= i.printHTML() %>
	</p>
	<%
	}
	}

	} else {
	%>
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