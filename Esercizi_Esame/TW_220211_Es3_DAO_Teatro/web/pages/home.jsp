<html>
	<head>
		<meta name="Author" content="pisi79, cg">
		<title>Home JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
	
		<div id="main" class="clear">
			<p>Welcome to the '09_TecWeb_solved' project.</p>
			<br/>
			<p>You can have even more fun, here!</p>
		</div>
	
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
