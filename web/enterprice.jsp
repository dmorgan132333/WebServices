<%-- 
    Document   : enterprice
    Created on : Mar 24, 2013, 4:47:33 PM
    Author     : mike
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="net.grocerypricebook.model.dbmanagers.GroceryStoreManager"%>
<%@page import="net.grocerypricebook.model.GroceryStore"%>
<%@page import="net.grocerypricebook.model.Unit"%>
<%@page import="net.grocerypricebook.model.Item"%>
<%@page import="net.grocerypricebook.model.dbmanagers.UnitManager"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemManager"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	int userId = (Integer) session.getAttribute("userId");
	ArrayList<Item> items = new ItemManager().getAllItems(userId);
	ArrayList<Unit> units = new UnitManager().getUnits();
	ArrayList<GroceryStore> stores = new GroceryStoreManager().getStores(userId);
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Enter Price Information</title>
	</head>
	<body>
		<h1>Enter Price Information</h1>
		<h2><a href="welcome.jsp">Home</a></h2>
		<form action="addprice">
			Item:
			<select name="item_id">
			<% for(Item i: items) { %>
				<option value="<%= i.getId() %>"><%= i.getFullName() %></option>	
			<% } %>
			</select>
			Price: <input type="number" min=".01" step=".01" name="price">
			Per Unit:
			<select name="unit_id">
			<% for(Unit u: units) { %>
				<option value="<%= u.getId() %>"><%= u.getName() %></option>	
			<% } %>
			</select>
			Date: <input type="date" name="date" value="<%= dateFormat.format(date) %>">
			Store: 
			<select name="store_id">
			<% for(GroceryStore s: stores) { %>
				<option value="<%= s.getStoreID() %>"><%= s.getName() %></option>	
			<% } %>
			</select>

			<input type="submit" name="submit" value="Submit">
		</form>
	</body>
</html>
