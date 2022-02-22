<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.*" %>

<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/script.js"></script>
<script></script>


<html>
<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Home</title>
</head>
<body>

	
	<form id = 'form' action="EliminaCarattereServlet" method="post">
		<p>Inserire nome file (esempio.txt):</p>
		<p><textarea name="request" rows="4" cols="50" onkeyup="checkSend(this)"></textarea></p>
	</form>
</body>
</html>