<%-- 
    Document   : edititems
    Created on : Mar 21, 2013, 4:03:11 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Item"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemManager"%>
<%@page import="net.grocerypricebook.model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.CategoriesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	CategoriesManager catMan = new CategoriesManager();
	ItemManager itemManager = new ItemManager();
	int userId = (Integer) session.getAttribute("userId");
	ArrayList<Category> categories = catMan.getCategories(userId);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Items</title>
	</head>	
	<style type="text/css">
		.center{
			margin:auto;
		}
		td.item{
			padding-left: 20px;
		}
		td.category{
			font-weight: bold;
		}
	</style>
	
	<body>
		<h1>Edit Items</h1>
		<h2><a href="welcome.jsp">Home</a></h2>
		<table class="center">
			<% for(Category cat : categories) { %>
			<tr>
				<td class="category"><%= cat.getName() %></td>
				<% ArrayList<Item> items = itemManager.getItemsInCategory(userId, cat.getCatId());
				   for(Item item: items){
				%>
			</tr>
			<td class="item">
				<%= item.getName() %>
			</td>
			<tr>
				<% } %>	
				<td>
			</tr>
			<% } %>
		</table>
	</body>
</html>