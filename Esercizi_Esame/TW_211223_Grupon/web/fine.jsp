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
<title>Home</title>
</head>
<body>
	<%
	User currentUser = (User) session.getAttribute("user");
	String gruppo = (String) session.getAttribute("gruppo");
	Map<String, List<UserSession>> mappa = (Map<String, List<UserSession>>) application.getAttribute("mappa");
	List<UserSession> listaGruppo = mappa.get(gruppo);
	boolean allClear = true;
	for (UserSession us : listaGruppo) {
		if (us.getUser().equals(currentUser))
			us.setFinito(true);
		if (us.isFinito() == false) {
			allClear = false;
		}
		if (allClear) {
			mappa.put(gruppo, new ArrayList<UserSession>(5));
		}
	}
	%>

	<h3>La tua richiesta e' stata ricevuta</h3>
	<br>
	<br>
	<%
	if (allClear) {
	%>
	<h4>Tutti gli utenti hanno finito gli acquisti!</h4>
	<%
	} else {
	%>
	<h4>In attesa di altri utenti!</h4>
	<%
	}
	%>


</body>
</html>