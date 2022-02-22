<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/text.js"></script>


<meta charset="UTF-8" />
<head>
<meta name="Author" content="Antonio De Luca">
<title>Home</title>
</head>
<body>
	<form id="form" action="EliminaCarattereServlet" method="post">
		<h3>Inserire Testo (€ per terminare):</h3>
		<textarea id="textarea" name="request" rows="100" cols="100" onkeyup=checkSend()></textarea>
	</form>
</body>
</html>