<%-- 
    Document   : editcategories
    Created on : Mar 9, 2013, 10:40:38 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Category"%>
<%@page import="java.util.Map"%>
<%@page import="net.grocerypricebook.model.CategoriesManager"%>
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
		<table>
			<tr><td>Category Name</td></td></td></tr>
			<% for(Category category: manager.getCategories(userId) ) { %>
			<form action="EditCategoriesDispatcher" method="GET">
                <input type="hidden" name="category_id" value="<%= category.getCatId() %>" />
				<tr><td><%= category.getName() %></td>
                    <td><input type="submit" name="action" value="Edit" /><input type="submit" name="action" value="Delete"><td>
                </tr>
			</form>
			<% } %>
			<tr><td></br></td></tr>

			<form action="EditCategoriesDispatcher" method="GET">
			<tr><td>Add new category:</td><td><input type="text" name="addNewName" /><input type="submit" name="action" value="Add"/></td></tr>
			</form>
		</table>
	</body>
</html>
