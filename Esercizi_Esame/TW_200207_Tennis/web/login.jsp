<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>

<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/json.js"></script>

<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Home</title>
</head>
<body>
	<form action="TennisServlet" method="post">
		<p>Username: <input id ="usr" type="text" name="usr" onchange="checkUsr()"></p>
		<p>Password: <input id ="pwd" type="text" name="pwd"></p>
		<p>Livello Abilita': <input id ="abl" type="text" name="abilita"></p>
		<input type="submit" name="login" value="login">
	</form>
</body>
</html>
