<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.util.List" %>
<%@ page import="java.time.*" %>

<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/matrix.js"></script>


<html>
<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Home</title>
</head>
<body onload="disableSend()">

	
	<form action="MatriciServlet" method="post" onkeyup="validate()">
	<p> Matrice A:
		<div><input type="text" size="3" id="A0"><input type="text" size="3" id="A1"><input type="text" size="3" id="A2"><input type="text" size="3" id="A3"></div>
		<div><input type="text" size="3" id="A4"><input type="text" size="3" id="A5"><input type="text" size="3" id="A6"><input type="text" size="3" id="A7"></div>
	<p> Matrice B: </p>
		<div><input type="text" size="3" id="B0"><input type="text" size="3" id="B1"><input type="text" size="3" id="B2"><input type="text" size="3" id="B3"></div>
		<div><input type="text" size="3" id="B4"><input type="text" size="3" id="B5"><input type="text" size="3" id="B6"><input type="text" size="3" id="B7"></div>
		
	<p> Matrice Risultato:</p>
		<div><input type="text" size="3" id="C0"><input type="text" size="3" id="C1"><input type="text" size="3" id="C2"><input type="text" size="3" id="C3"></div>
		<div><input type="text" size="3" id="C4"><input type="text" size="3" id="C5"><input type="text" size="3" id="C6"><input type="text" size="3" id="C7"></div>
		<input  type="button" id="single" value="SingleThread" onclick="sendSingle()"><input type="button" id="multi" value="Multi Thread" onclick="sendMulti()">
	</form>
</body>
</html>