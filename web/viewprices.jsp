<%-- 
    Document   : viewprices
    Created on : Mar 24, 2013, 9:12:50 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.ItemPriceData"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemPriceDataManager"%>
<%@page import="net.grocerypricebook.model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	ItemManager itemMan = new ItemManager();
	ItemPriceDataManager priceMan = new ItemPriceDataManager();
	int userId = (Integer)session.getAttribute("userId");
	ArrayList<Item> items = itemMan.getAllItems(userId);


%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>View Prices</title>
	</head>
	<body>
		<h1>View Prices</h1>
		<h2><a href="welcome.jsp">Back</a></h2>

		<table cellpadding="3" border="1">
			<tr>
				<td>Item name</td>
				<td>Average Price</td>
				<td>Low Price</td>
				<td>High Price</td>
				<td>Number of Prices</td>
			</tr>
		<% for(Item i: items) { 
			ItemPriceData priceData = priceMan.getItemPriceData(i.getId());
			priceData.processData();
		%>
			<tr>
				<td>
					<%= i.getFullName() %>
				</td>
				<td>
					<% if(priceData.getAveragePrice() != 0) out.print(priceData.getAveragePrice()); %>
				</td>
				<td>
					<% if(priceData.getLowPrice() != 0) out.print(priceData.getLowPrice()); %>
				</td>
				<td>
					<% if(priceData.getHighPrice() != 0) out.print(priceData.getHighPrice()); %>
				</td>
				<td>
					<%= priceData.getNumPricePoints() %>
				</td>
			</tr>	
		<% } %>
		</table>

	</body>
</html>
