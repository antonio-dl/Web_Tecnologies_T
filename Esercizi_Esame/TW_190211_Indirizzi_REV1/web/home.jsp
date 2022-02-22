<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.*"%>

<script type="text/javascript" src="scripts/utils.js"></script>
<script></script>


<html>
<head>
<meta name="Author" content="Antonio De Luca">
<title>Home</title>
</head>
<%
String ris = (String) request.getAttribute("response");
if(ris == null){
	ris = "";
}
%>

<body>


	<form action="IndirizziServlet" method="post">
		<p>
			Inserire cognome: <input type="text" name="cognome">
		</p>
		<p>
			<textarea name="ris" rows="4" cols="50"><%=ris%></textarea>
		</p>
		<p>
			<input type="submit" name="send" value="send">
		</p>
		<p>
			<input type="submit" name="logout" value="logout">
		</p>
	</form>
</body>
</html>