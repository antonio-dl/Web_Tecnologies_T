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
	
	<h3>Carrello del gruppo: <%=(String) session.getAttribute("gruppo") %></h3>
	
	
	<form action="fine.jsp" method="post">
	 <%-- Bottone farlocco :) --%>
		<input type="button" value="aggiungi prodotto">
		<input type="submit" name="finalizza" value="finalizza">
	</form>
</body>
</html>