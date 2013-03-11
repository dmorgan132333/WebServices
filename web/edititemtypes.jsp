<%-- 
    Document   : edititemtypes
    Created on : Mar 10, 2013, 7:56:32 PM
    Author     : mike
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.grocerypricebook.model.Categories"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.grocerypricebook.model.Items"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Items items = new Items(); %>
<% HashMap<Integer, String> categories = (HashMap<Integer, String>) new Categories().getCategories(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Item Types</title>
	</head>
	<body>
		<h1>Edit Item Types</h1>
		<table>
			<tr><td>Name</td><td>Category</td></td></tr>
			<% for(ArrayList<String> row:  items.getItems() ) { %>
			<form action="EditItemsDispatcher" method="GET">
				<input type="hidden" name="item_id" value="<%= row.get(0) %>" />
				<tr><td><%= row.get(1) %></td>
					<td>
						<% for(Map.Entry<Integer, String> catMap : categories.entrySet()){ 
							if(catMap.getKey() == Integer.parseInt(row.get(2)))
								out.println(categories.get(Integer.parseInt(row.get(2))));
						   }
						%>
					</td>
					<td><input type="submit" name="action" value="Edit" /><input type="submit" name="action" value="Delete"<td></tr>
			</form>
			<% } %>
			<tr><td></br></td></tr>

			<form action="EditItemsDispatcher" method="GET">
			<tr>
				<td>Add new item type:</td>
				<td><input type="text" name="addNewName" />
					<select name="cat_id">
						<% for(Map.Entry<Integer, String> catMap : categories.entrySet()){
							out.println("<option value="+ catMap.getKey() +">"+ catMap.getValue() +"</option>");
						   }
						%>
					</select>
					<input type="submit" name="action" value="Add"/>
				</td>
			</tr>
			</form>
		</table>
	</body>
</html>
