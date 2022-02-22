<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="beans.*"%>
<%@ page import="com.google.gson.Gson"%>

<script type="text/javascript" src="scripts/utils.js"></script>
<script></script>


<html>
<head>
<meta name="Author" content="Antonio De Luca">
<title>Home</title>
</head>
<body>

	<%!private List<String> getGruppi(String path) {
		List<String> lista = new ArrayList<String>(4);
		//lista.add("Gruppo 1");
		//lista.add("Gruppo 2");
		//lista.add("Gruppo 3");
		Gruppi gruppi = null;
		try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
			Gson gson = new Gson();
			gruppi = gson.fromJson(bf, Gruppi.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return gruppi.getListaGruppi();
	}%>

	<% User usr = (User) session.getAttribute("user");
	List<String> gruppi = getGruppi(application.getRealPath("gruppi.json"));
	%>
	<h3>
		Benvenuto
		<%=usr.getUsername()%></h3>

	<p>Seleziona il gruppo di cui vuoi fare parte:
	<form action="SelectGroupServlet" method="post">
		<select name="gruppo" id="gruppo">
			<%
			for (String s : gruppi) {
			%>
			<option value="<%=s%>"><%=s%>
			</option>
			<%
			}
			%>
		</select>
		<p>
			<input type="submit" name="seleziona" value="send">
		</p>
	</form>
	
	<a href="visualizzaUtenti.jsp">Visualizza Gli utenti inscritti nei gruppi qui!</a>
</body>
</html>