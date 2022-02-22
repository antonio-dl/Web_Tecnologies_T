<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Cart"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<%@ page import="java.util.*"%>


<html>
<head>
<meta name="Author" content="pisi79, cg">
<title>Checkout JSP</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/default.css" type="text/css" />
</head>

<body>

<%@ include file="../fragments/header.jsp"%>
<%@ include file="../fragments/menu.jsp"%>
<jsp:useBean id="cart" class="it.unibo.tw.web.beans.Cart" scope="session" /> 

<div id="main" class="clear">

Items in cart:<br />


<table class="formdata">
	<tr>
		<th style="width: 33%">Description</th>
		<th style="width: 33%">Price</th>
		<th style="width: 33%">Your order</th>
	</tr>
	
	
	<% 
	for( Item anItem : cart.getItems() ){  
	%> 
		<tr>
			<td><%= anItem.getDescription() %></td>
			<td><%= anItem.getPrice() %> &#8364;</td>
			<td><%= cart.getOrder(anItem) %></td>
		</tr>
	<% } %>
	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
<br/>
<p>
Total: <span style="font-size: x-large; color: red;"><%= it.unibo.tw.web.functions.Total.total(cart) %> &#8364;</span>
</p>
<br />

<%

String email = request.getParameter("email");
email = email == null ? "" : email;

%>

Shipment information (* = mandatory):<br />
<form action="checkout" method="post">
<table>
	
	<tr>
		<td><label>Email (*)</label></td>
		<td><input type="text" name="email" value="<%= email %>"></td>
		<td><strong><%= email.length() > 0 ? "" : "(mandatory)" %></strong></td>
	</tr>
</table>

<input type="submit" name="Conferma" value="Confirm order" />
<input type="submit" name="Conferma" value="Show order" />
<input type="submit" name="Conferma" value="Show all" />

</form>

<%
Object orderObj = request.getAttribute("order");
if( orderObj != null ){
	List<Item> order = (List<Item>)orderObj;
%>
	<h3>Order related to: <%= email %></h3>
	<ul>
<%
	for(Item i : order){
%>
		<li><%= i.getDescription() %>, <%= i.getPrice() %> &#8364;, <%= i.getQuantity() %></li>
<%	
	}
%>
	</ul>
<%
}
%>


<%
Object ordersObj = request.getAttribute("orders");
if( ordersObj != null ){
	Hashtable<String,List<Item>> orders = (Hashtable<String,List<Item>>)ordersObj;
	Set<String> emails = orders.keySet();
	for(String e : emails){
%>
	<h3>Order related to: <%= e %></h3>
	<ul>
<%
		List<Item> order = orders.get(e);
		for(Item i : order){
%>
			<li><%= i.getDescription() %>, <%= i.getPrice() %> &#8364;, <%= i.getQuantity() %></li>
<%	
		}
%>
		</ul>
		<br />
<%
	}
}
%>

</div>

<%@ include file="../fragments/footer.jsp"%>

</body>
</html>
