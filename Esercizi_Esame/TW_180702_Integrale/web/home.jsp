<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">


<html>
<%@ page session="true"%>
<% request.getSession(); // Solo scopo di creare una sessione per le richieste xhr%>

<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/scripts.js"></script>
<script></script>
<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Home</title>
</head>
<body>

	
	<form action="" method="get">
		<p>Intervallo di integrazione A: <input type="text" id="A"></p>
		<p>Intervallo di integrazione B: <input type="text" id="B"></p>
		<p><input type="button" name="send" value="send" onclick="sendRequest()" ></p>
		<p>Risultato: <input type="text" id="result" readonly></p>
	</form>
</body>
</html>