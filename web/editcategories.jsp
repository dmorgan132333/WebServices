<%-- 
    Document   : editcategories
    Created on : Mar 9, 2013, 10:40:38 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Category"%>
<%@page import="java.util.Map"%>
<%@page import="net.grocerypricebook.model.dbmanagers.CategoriesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% CategoriesManager manager  = new CategoriesManager(); 
int userId = (Integer)request.getSession().getAttribute("userId");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Categories</title>
	</head>
	<body>

		<h1>View and Edit Item Categories</h1>
		<h2><a href="welcome.jsp">Home</a></h2> 
		<table>
			<tr>
				<td><b>Basic Categories</b></td>
				</td></td>
			</tr>
			<% for(Category category: manager.getBasicCategories(userId) ) { %>
			<form action="EditCategoriesDispatcher" method="POST">
			<input type="hidden" name="category_id" value="<%= category.getCatId() %>" />
				<tr>
					<td><%= category.getName() %></td>
                    			<td><input type="submit" name="edit" value="Edit" /><input type="submit" name="delete" value="Delete"><td>
				</tr>
			</form>
			<% } %>

			<form action="EditCategoriesDispatcher" method="POST">
			<tr>
				<td>Add basic category:</td><td><input type="text" name="addNewName" /><input type="submit" name="add_basic" value="Add"/></td>
			</tr>

			<tr>
				<td></br></td>
			</tr>

			<tr>
				<td><b>Other Categories</b></td>
				</td></td>
			</tr>

			<% for(Category category: manager.getOtherCategories(userId) ) { %>
			<form action="EditCategoriesDispatcher" method="POST">
			<input type="hidden" name="category_id" value="<%= category.getCatId() %>" />
				<tr>
					<td><%= category.getName() %></td>
                    			<td><input type="submit" name="edit" value="Edit" /><input type="submit" name="delete" value="Delete"><td>
				</tr>
			</form>
			<% } %>

			<form action="EditCategoriesDispatcher" method="POST">
			<tr>
				<td>Add other category:</td><td><input type="text" name="addNewName" /><input type="submit" name="add_other" value="Add"/></td>
			</tr>
			</form>
		</table>
	</body>
</html>