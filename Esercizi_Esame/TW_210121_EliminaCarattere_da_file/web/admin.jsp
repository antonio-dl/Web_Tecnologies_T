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
	Set<HttpSession> sessions = (Set<HttpSession>) application.getAttribute("sessions");
	for(HttpSession s : sessions){
		%> <p>Sessione id: <%= s.getId() %> <br> Numero richieste <%= (Integer) s.getAttribute("counter") %><hr></p><%
	}
	int countValidRequest = 0;
	List<Long> timeStampsRequests = (List<Long>)application.getAttribute("timeStampRequest");
	for(Long l : timeStampsRequests){
		if(System.currentTimeMillis() - l <= 1000 * 60 * 60)
			countValidRequest++;
	}
	%> <p> NUMERO RICHIESTE NELL ULTIMA ORA: <%= countValidRequest %>
	 <% 
	
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