<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>



<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Login</title>
</head>
<body>
	<form action="LoginServlet" method="post">
		<p>Username: <input type="text" name="usr"></p>
		<p>Password: <input type="text" name="pwd"></p>
		<input type="submit" name="login" value="login">
	</form>
	<div>Registrati:</div>
	<form action="LoginServlet" method="post">
		<p>Username: <input type="text" name="usr"></p>
		<p>Password: <input type="text" name="pwd"></p>
		<input type="submit" name="login" value="registra">
	</form>
</body>
</html>