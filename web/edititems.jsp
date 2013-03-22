<%-- 
    Document   : edititems
    Created on : Mar 21, 2013, 4:03:11 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.CategoriesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	CategoriesManager catMan = new CategoriesManager();
	int userId = (Integer) session.getAttribute("userId");
	ArrayList<Category> categories = catMan.getCategories(userId);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Items</title>
	</head>
	<body>
		<h1>Edit Items</h1>
		<h2><a href="/welcome.jsp">Home</a></h2>
		<table>
			<% for(Category cat : categories) { %>
			<tr>
				<td><%= cat.getName() %></td>
			</tr>
			<% } %>
		</table>
	</body>
</html>
