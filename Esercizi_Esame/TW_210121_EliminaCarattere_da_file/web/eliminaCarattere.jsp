<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.google.gson.Gson"%>
<script type="text/javascript" src="scripts/utils.js"></script>

<meta charset="UTF-16" />
<head>
<meta name="Author" content="Antonio De Luca">
<title>Fine</title>
</head>

<%
String requestText = request.getParameter("request");

FileReader fr = new FileReader(application.getRealPath(requestText));
BufferedReader bf = new BufferedReader(fr);

FileWriter fw = new FileWriter(application.getRealPath(requestText + "temp123"));
BufferedWriter bw = new BufferedWriter(fw);

Random rnd = new Random();
int randChar = rnd.nextInt(27);
String alfabeto = "abcdefghijklmnopqrstuvwxyz";
if (rnd.nextBoolean())
	alfabeto = alfabeto.toUpperCase();
char daEliminare = alfabeto.charAt(randChar);
System.out.println("JSP: Carattere da eliminare: " + daEliminare);
int countMaiusc = 0;
String result = "";

int readedChar;
while ((readedChar = bf.read()) != -1) {
	if ((char)readedChar != daEliminare) {
		bw.write((char)readedChar);
		result += (char)readedChar;
		if (alfabeto.toUpperCase().contains((char) readedChar + ""))
			countMaiusc++;
	}
}

File file = new File(application.getRealPath(requestText + "temp333"));
file.renameTo(new File(requestText));

bf.close();
bw.close();

Gson gson = new Gson();
System.out.println("JSP0: JSON Inviato Counter: " + gson.toJson(countMaiusc));
System.out.println("JSP0: JSON Inviato Testo: " + gson.toJson(result));
request.setAttribute("count", gson.toJson(countMaiusc));
request.setAttribute("result", gson.toJson(result));

request.getRequestDispatcher("response.jsp").forward(request, response);



%>

<body>
	<p>
		Lunghezza Testo Risultante:
		<%=result.length()%></p>
	<p>
		<%=result%></p>
</body>
</html>