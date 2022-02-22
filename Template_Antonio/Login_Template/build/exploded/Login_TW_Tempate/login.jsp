<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>

<script type="text/javascript" src="scripts/utils.js"></script>
<script type="text/javascript" src="scripts/scripts.js"></script>

<script>


	function checkUsr() {
		var regex = /^[a-zA-Z0-9_-]+$/;

		var usrNode = document.mygetElementById('usr');
		if (!regex.test(usrNode.valu)) {
			alert("Nome utente non valido!");
			usrNode.value = "";
		}


	}

	function validate(){
		//alert("Ricordati di validare se c'e' da validare!");
		return true;
	}




</script>


<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Home</title>
</head>

<body>
	<form action="LoginServlet" method="post" onsubmit="return validate()">
		<p>Username: <input id="usr" type="text" name="usr" onchange="checkUsr()"></p>
		<p>Password: <input id="pwd" type="text" name="pwd"></p>
		<p><input type="submit" name="login" value="login">
		<input type="submit" name="login" value="registra"></p>

	</form>
</body>

</html>