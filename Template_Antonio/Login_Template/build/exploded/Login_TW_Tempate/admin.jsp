<%@ page session="true"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>


<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
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