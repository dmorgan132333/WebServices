<%-- 
    Document   : itemstest
    Created on : Mar 20, 2013, 6:55:42 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	ItemManager itemMan = new ItemManager();
	ArrayList<Item> items = itemMan.getAllItems(1);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Items Test</title>
	</head>
	<body>
		<h1>Hello World!</h1>
		<% for(Item item: items) {%>
			<%= item.getName() %><br/>
		<% } %>
	</body>
</html>
