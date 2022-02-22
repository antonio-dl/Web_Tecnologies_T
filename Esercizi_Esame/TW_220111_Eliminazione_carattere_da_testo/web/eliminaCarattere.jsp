<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.util.*"%>
<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/text.js"></script>

<meta charset="UTF-16" />
<head>
<meta name="Author" content="Antonio De Luca">
<title>Fine</title>
</head>

<%
String requestText = (String) request.getAttribute("request");
Random rnd = new Random();
int randChar = rnd.nextInt(27);
String alfabeto = "abcdefghijklmnopqrstuvwxyz";
char daEliminare = alfabeto.charAt(randChar);
String toString = "" + daEliminare;
System.out.println("JSP: Carattere da eliminare: " + daEliminare);
String result = requestText.replaceAll(toString, "");


%>

<body>
	<p>Lunghezza Testo Risultante: <%= result.length() %></p>
	<p> <%= result %></p>
</body>
</html>