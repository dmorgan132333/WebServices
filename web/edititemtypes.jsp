<%-- 
    Document   : edititemtypes
    Created on : Mar 10, 2013, 7:56:32 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Category"%>
<%@page import="net.grocerypricebook.model.ItemType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.CategoriesManager"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemTypesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ItemTypesManager typesManager = new ItemTypesManager(); 
   CategoriesManager categoriesManager = new CategoriesManager();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Item Types</title>
	</head>
	<body>
		<h1>Edit Item Types</h1>
		<h2><a href="welcome.jsp">Home</a></h2>
		<table>
			<tr>
				<td>Name</td>
				<td>Basic Category</td>
				</td>
			</tr>
			<% for(ItemType itemType : typesManager.getAllItemTypes((Integer)session.getAttribute("userId")) ) { %>
			<form action="EditItemsDispatcher" method="GET">
				<input type="hidden" name="item_type_id" value="<%= itemType.getId() %>" />
				<tr><td><%= itemType.getName() %></td>
					<td>
						<%= categoriesManager.getCategoryName(itemType.getBaseCategoryId()) %>
					</td>
					<td><input type="submit" name="action" value="Edit" /><input type="submit" name="action" value="Delete"<td></tr>
			</form>
			<% } %>
			<tr><td></br></td></tr>

			<form action="EditItemsDispatcher" method="POST">
			<tr>
				<td>Add new item type:</td>
				<td><input type="text" name="addNewName" />
					<select name="cat_id">
						<option>Select basic category</option>
						<% for(Category category : categoriesManager.getBasicCategories((Integer)session.getAttribute("userId"))){
							out.println("<option value="+ category.getCatId() +">"+ category.getName() +"</option>");
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
