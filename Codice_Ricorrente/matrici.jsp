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
<body>


	<form action="CruciverbaServlet" method="post">
		<%
		int nRighe = 10;
		int nColonne = 10;
		int nCelle = nRighe*nColonne;
		
		for (int i = 0; i < nCelle; i++) {
			if (i % nColonne == 0) {
		%><div>
			<%
			}
			%>
			<input type='text' size='3' id='A<%=i%>' onkeyup=validate(this)>
			<%
			if (i % nColonne == nColonne-1) {
			%>
		</div>
		<%
		}
		}
		%>
		
	</form>
</body>