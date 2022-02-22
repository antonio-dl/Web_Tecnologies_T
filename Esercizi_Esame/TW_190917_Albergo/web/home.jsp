<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>


<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/json.js"></script>

<script type="text/javascript">

	function callServlet(){
		var id = myGetElementById('id').value;
		var din = myGetElementById('din').value;
		var dout = myGetElementById('dout').value;
		
		id = parseInt(id);
		din = parseInt(din);
		dout = parseInt(dout);
		
		if (isNaN(id)||id>4||isNaN(din) || isNaN(dout) || din>dout || dout>365){
			myGetElementById('id').value="";
			myGetElementById('din').value="";
			myGetElementById('dout').value="";
			alert("Errore: dati errati!");
		}else{
			myGetElementById('req').submit();
		}
	}
			
</script>
<html>
<html>
<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Home</title>
</head>
<body>

<%
String ris="";
String stringa = "";
if (request.getAttribute("result")!=null){
	ris= (String) request.getAttribute("result");
	if(ris.equals("0.0"))
		stringa = "Non e' stata trovata una stanza libera :(";
	else
		stringa = "Trovato una camera a " + ris + "! Accetta?";
} %>
	
	<form id="req" action="RegistrazioneServlet" method="get">
		<p>Inserire numero Hotel : <input id="id" type="text" name="hotel"></p>
		<p>Inserire data IN : <input id="din" type="text" name="din"></p>
		<p>Inserire data OUT : <input id="dout" type="text" name="dout" onchange="callServlet()"></p>
	</form>
	<% if (!ris.equals("")) {
	%>
	<form id="confirm" action="RegistrazioneServlet" method="post">
		<p><textarea name="ris" rows="2" cols="50"><%=stringa %></textarea></p>
		<%if(!ris.equals("0.0")){ %>
		<p><input type="submit" name="accept" value="accept"></p>
		<p><input type="submit" name="refuse" value="refuse"></p>
		<%} %>
	</form>
	<%} %>
</body>
</html>