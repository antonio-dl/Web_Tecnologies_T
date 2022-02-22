<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>


<html>
<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/CountMaiusc.js"></script>
<head>
<meta name="Author" content="Antonio De Luca">
<title>Home</title>
</head>
<body>
	<p>Scegliere tre files:</p>
	<form id="form">
		<select name="file" id="file" multiple onclick="checkSelected(this)">
			<%
			String dir = application.getInitParameter("dir");
			File f = new File(application.getRealPath(dir));
			System.out.println("Apertura File:");
			for (String s : f.list()) {
			%>
			<option value=<%=s%>><%=s%></option>
			<%
			}
			%>

		</select> <br> <br>
		
	<p>Risultato:</p>
		<textarea id="textarea" rows="4" cols="30" readonly> </textarea>
	</form>




</body>
</html>