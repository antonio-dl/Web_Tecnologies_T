<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>
<%@ page import="com.google.gson.Gson"%>

<script type="text/javascript" src="scripts/utils.js"></script>
<script></script>


<html>
<head>
<meta name="Author" content="Antonio De Luca">
<title>Visualizza Utenti</title>
</head>
<body>

<h2>Lista dei gruppi e degli utenti</h2>
	<%
	Map<String, List<UserSession>> mappa = (Map<String, List<UserSession>>) application.getAttribute("mappa");
	for (String gruppo : mappa.keySet()) {
	%><h4><%=gruppo%>: </h4>
	<ul>
		<%
		for (UserSession us : mappa.get(gruppo)) {
		%>
		<li><%=us.getUser().getUsername()%></li>
		<%
		}
		%>

	</ul>
	<%
	}
	%>



</body>
</html>