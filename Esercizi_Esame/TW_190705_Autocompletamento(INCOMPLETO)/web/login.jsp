<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>



<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Home</title>
</head>
<body>
	<form action="IndirizziServlet" method="post">
		<p>Username: <input type="text" name="usr"></p>
		<p>Password: <input type="text" name="pwd"></p>
		<input type="submit" name="login" value="login">
	</form>
</body>
</html>

<jsp:forward page="/IndirizziServlet"></jsp:forward>
