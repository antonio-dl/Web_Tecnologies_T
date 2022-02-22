<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="beans.User"%>


<script type="text/javascript">

	
			
</script>
<html>
<head>
	<meta name="Author" content="Antonio De Luca">
	<title>Home</title>
</head>
<body>

<%
User user = (User) session.getAttribute("user");
	String ris= user.getMessaggio();
if (request.getAttribute("response")!=null){
	ris= ris +"\n" + (String) request.getAttribute("response");
}


%>
<p> Utente: <%= user.getUsername()%></p>
<p> Abilita' : <%= user.getAbilita()%> </p>
	  

	
	<form action="TennisServlet" method="post">
		<p><textarea name="response" rows="3" cols="50"><%=ris %></textarea></p>
		<p><input type="submit" name="prenota" value="prenota"></p>
		<p><input type="submit" name="finalizza" value="finalizza"></p>
	</form>
</body>
</html>

