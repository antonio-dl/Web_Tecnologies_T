<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>



<html>
<head>
<meta name="Author" content="Antonio De Luca">
<title>Home</title>
</head>
<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/matrixCount.js"></script>

<body>

	<p>Inserire Matrice A:</p>
	<div>
		<input type="text" size="2" id="a0" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="a1" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="a2" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="a3" onchange="disableSend()">&nbsp;
	</div>
	<div>
		<input type="text" size="2" id="a4" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="a5" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="a6" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="a7" onchange="disableSend()">&nbsp;
	</div>

	<p>Inserire Matrice B:</p>
	<div>
		<input type="text" size="2" id="b0" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="b1" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="b2" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="b3" onchange="disableSend()">&nbsp;
	</div>
	<div>
		<input type="text" size="2" id="b4" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="b5" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="b6" onchange="disableSend()">&nbsp;
		<input type="text" size="2" id="b7" onchange="disableSend()">&nbsp;
	</div>

	<p>
		<input type="button" value="Validate" onclick="validate()">&nbsp;
		<input type="button" id="Single" value="Single" disabled="disabled" onclick="sendSingle()">&nbsp;
		<input type="button" id="Multi" value="Multi" disabled="disabled" onclick="sendMulti()">
	<p>Risultato A-B :</p>
	<div>
		<input type="text" size="2" id="r0" readonly">&nbsp;
		<input type="text" size="2" id="r1" readonly">&nbsp;
		<input type="text" size="2" id="r2" readonly">&nbsp;
		<input type="text" size="2" id="r3" readonly">&nbsp;
	</div>
	<div>
		<input type="text" size="2" id="r4" readonly">&nbsp;
		<input type="text" size="2" id="r5" readonly">&nbsp;
		<input type="text" size="2" id="r6" readonly">&nbsp;
		<input type="text" size="2" id="r7" readonly">&nbsp;
	</div>

<div>Sei un amministratore? <a href="admin.jsp">Clicca QUI!</a></div>
</body>
</html>