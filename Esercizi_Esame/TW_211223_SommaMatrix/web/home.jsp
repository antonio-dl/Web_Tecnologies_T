<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.util.List"%>
<%@ page import="java.time.*"%>

<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/matrix.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-2.2.4.js"></script>

<html>
<head>
<meta name="Author" content="Antonio De Luca">
<title>Home</title>
</head>
<body onload="disableSend()">


	<form action="MatriciServlet" method="post" onkeyup="validate()">
		<select id="modalita">
			<option value="Single">Nessun parallelismo</option>
			<option value="2">2</option>
			<option value="4">4</option>
		</select>

		<p class="A">Matrice A:
		<div id="matrixA">
			<div>
				<input type="text" size="3" id="A0"><input type="text"
					size="3" id="A1"><input type="text" size="3" id="A2"><input
					type="text" size="3" id="A3"> <input type="text" size="3"
					id="A4"><input type="text" size="3" id="A5"><input
					type="text" size="3" id="A6"><input type="text" size="3"
					id="A7">
			</div>
			<div>
				<input type="text" size="3" id="A8"><input type="text"
					size="3" id="A9"><input type="text" size="3" id="A10"><input
					type="text" size="3" id="A11"> <input type="text" size="3"
					id="A12"><input type="text" size="3" id="A13"><input
					type="text" size="3" id="A14"><input type="text" size="3"
					id="A15">
			</div>
			<div>
				<input type="text" size="3" id="A16"><input type="text"
					size="3" id="A17"><input type="text" size="3" id="A18"><input
					type="text" size="3" id="A19"> <input type="text" size="3"
					id="A20"><input type="text" size="3" id="A21"><input
					type="text" size="3" id="A22"><input type="text" size="3"
					id="A23">
			</div>
			<div>
				<input type="text" size="3" id="A24"><input type="text"
					size="3" id="A25"><input type="text" size="3" id="A26"><input
					type="text" size="3" id="A27"> <input type="text" size="3"
					id="A28"><input type="text" size="3" id="A29"><input
					type="text" size="3" id="A30"><input type="text" size="3"
					id="A31">
			</div>
			<div>
				<input type="text" size="3" id="A32"><input type="text"
					size="3" id="A33"><input type="text" size="3" id="A34"><input
					type="text" size="3" id="A35"> <input type="text" size="3"
					id="A36"><input type="text" size="3" id="A37"><input
					type="text" size="3" id="A38"><input type="text" size="3"
					id="A39">
			</div>
			<div>
				<input type="text" size="3" id="A40"><input type="text"
					size="3" id="A41"><input type="text" size="3" id="A42"><input
					type="text" size="3" id="A43"> <input type="text" size="3"
					id="A44"><input type="text" size="3" id="A45"><input
					type="text" size="3" id="A46"><input type="text" size="3"
					id="A47">
			</div>
			<div>
				<input type="text" size="3" id="A48"><input type="text"
					size="3" id="A49"><input type="text" size="3" id="A50"><input
					type="text" size="3" id="A51"> <input type="text" size="3"
					id="A52"><input type="text" size="3" id="A53"><input
					type="text" size="3" id="A54"><input type="text" size="3"
					id="A55">
			</div>
			<div>
				<input type="text" size="3" id="A56"><input type="text"
					size="3" id="A57"><input type="text" size="3" id="A58"><input
					type="text" size="3" id="A59"> <input type="text" size="3"
					id="A60"><input type="text" size="3" id="A61"><input
					type="text" size="3" id="A62"><input type="text" size="3"
					id="A63">
			</div>
		</div>
		<div id="matrixB">
			<p>Matrice B:</p>
			<div>
				<input type="text" size="3" id="B0"><input type="text"
					size="3" id="B1"><input type="text" size="3" id="B2"><input
					type="text" size="3" id="B3"> <input type="text" size="3"
					id="B4"><input type="text" size="3" id="B5"><input
					type="text" size="3" id="B6"><input type="text" size="3"
					id="B7">
			</div>
			<div>
				<input type="text" size="3" id="B8"><input type="text"
					size="3" id="B9"><input type="text" size="3" id="B10"><input
					type="text" size="3" id="B11"> <input type="text" size="3"
					id="B12"><input type="text" size="3" id="B13"><input
					type="text" size="3" id="B14"><input type="text" size="3"
					id="B15">
			</div>
			<div>
				<input type="text" size="3" id="B16"><input type="text"
					size="3" id="B17"><input type="text" size="3" id="B18"><input
					type="text" size="3" id="B19"> <input type="text" size="3"
					id="B20"><input type="text" size="3" id="B21"><input
					type="text" size="3" id="B22"><input type="text" size="3"
					id="B23">
			</div>
			<div>
				<input type="text" size="3" id="B24"><input type="text"
					size="3" id="B25"><input type="text" size="3" id="B26"><input
					type="text" size="3" id="B27"> <input type="text" size="3"
					id="B28"><input type="text" size="3" id="B29"><input
					type="text" size="3" id="B30"><input type="text" size="3"
					id="B31">
			</div>
			<div>
				<input type="text" size="3" id="B32"><input type="text"
					size="3" id="B33"><input type="text" size="3" id="B34"><input
					type="text" size="3" id="B35"> <input type="text" size="3"
					id="B36"><input type="text" size="3" id="B37"><input
					type="text" size="3" id="B38"><input type="text" size="3"
					id="B39">
			</div>
			<div>
				<input type="text" size="3" id="B40"><input type="text"
					size="3" id="B41"><input type="text" size="3" id="B42"><input
					type="text" size="3" id="B43"> <input type="text" size="3"
					id="B44"><input type="text" size="3" id="B45"><input
					type="text" size="3" id="B46"><input type="text" size="3"
					id="B47">
			</div>
			<div>
				<input type="text" size="3" id="B48"><input type="text"
					size="3" id="B49"><input type="text" size="3" id="B50"><input
					type="text" size="3" id="B51"> <input type="text" size="3"
					id="B52"><input type="text" size="3" id="B53"><input
					type="text" size="3" id="B54"><input type="text" size="3"
					id="B55">
			</div>
			<div>
				<input type="text" size="3" id="B56"><input type="text"
					size="3" id="B57"><input type="text" size="3" id="B58"><input
					type="text" size="3" id="B59"> <input type="text" size="3"
					id="B60"><input type="text" size="3" id="B61"><input
					type="text" size="3" id="B62"><input type="text" size="3"
					id="B63">
			</div>
		</div>
		<div id="matrixC">
			<p>Matrice Risultato:</p>
			<div>
				<input type="text" size="3" id="C0"><input type="text"
					size="3" id="C1"><input type="text" size="3" id="C2"><input
					type="text" size="3" id="C3"> <input type="text" size="3"
					id="C4"><input type="text" size="3" id="C5"><input
					type="text" size="3" id="C6"><input type="text" size="3"
					id="C7">
			</div>
			<div>
				<input type="text" size="3" id="C8"><input type="text"
					size="3" id="C9"><input type="text" size="3" id="C10"><input
					type="text" size="3" id="C11"> <input type="text" size="3"
					id="C12"><input type="text" size="3" id="C13"><input
					type="text" size="3" id="C14"><input type="text" size="3"
					id="C15">
			</div>
			<div>
				<input type="text" size="3" id="C16"><input type="text"
					size="3" id="C17"><input type="text" size="3" id="C18"><input
					type="text" size="3" id="C19"> <input type="text" size="3"
					id="C20"><input type="text" size="3" id="C21"><input
					type="text" size="3" id="C22"><input type="text" size="3"
					id="C23">
			</div>
			<div>
				<input type="text" size="3" id="C24"><input type="text"
					size="3" id="C25"><input type="text" size="3" id="C26"><input
					type="text" size="3" id="C27"> <input type="text" size="3"
					id="C28"><input type="text" size="3" id="C29"><input
					type="text" size="3" id="C30"><input type="text" size="3"
					id="C31">
			</div>
			<div>
				<input type="text" size="3" id="C32"><input type="text"
					size="3" id="C33"><input type="text" size="3" id="C34"><input
					type="text" size="3" id="C35"> <input type="text" size="3"
					id="C36"><input type="text" size="3" id="C37"><input
					type="text" size="3" id="C38"><input type="text" size="3"
					id="C39">
			</div>
			<div>
				<input type="text" size="3" id="C40"><input type="text"
					size="3" id="C41"><input type="text" size="3" id="C42"><input
					type="text" size="3" id="C43"> <input type="text" size="3"
					id="C44"><input type="text" size="3" id="C45"><input
					type="text" size="3" id="C46"><input type="text" size="3"
					id="C47">
			</div>
			<div>
				<input type="text" size="3" id="C48"><input type="text"
					size="3" id="C49"><input type="text" size="3" id="C50"><input
					type="text" size="3" id="C51"> <input type="text" size="3"
					id="C52"><input type="text" size="3" id="C53"><input
					type="text" size="3" id="C54"><input type="text" size="3"
					id="C55">
			</div>
			<div>
				<input type="text" size="3" id="C56"><input type="text"
					size="3" id="C57"><input type="text" size="3" id="C58"><input
					type="text" size="3" id="C59"> <input type="text" size="3"
					id="C60"><input type="text" size="3" id="C61"><input
					type="text" size="3" id="C62"><input type="text" size="3"
					id="C63">
			</div>
		</div>
		<input type="button" id="single" value="Single" onclick="sendSingle()"><input
			type="button" id="multi2" value="2" onclick="send2Multi()"><input
			type="button" id="multi4" value="4" onclick="send4Multi()">
	</form>
</body>
</html>