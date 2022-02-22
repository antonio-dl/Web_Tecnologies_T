<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.*"%>
<%@ page import="com.google.gson.Gson"%>

<script type="text/javascript" src="scripts/utils.js"></script>
<script></script>

<%
Gson gson = new Gson();
String counterJson = (String) request.getAttribute("count");
String resultJson = (String) request.getAttribute("result");

int countMaiusc = gson.fromJson(counterJson, Integer.class);
String result = gson.fromJson(resultJson, String.class);
%>

<html>
<head>
<meta name="Author" content="Antonio De Luca">
<title>Risultato</title>
</head>
<body>


	<form id='form' action="EliminaCarattereServlet" method="post">
		<p>
			<textarea id="request" rows="4" cols="50">Caratteri Maiuscoli contati: <%=countMaiusc%>
Testo risultante: <%=result%>
		</textarea>
		</p>
	</form>
</body>
</html>