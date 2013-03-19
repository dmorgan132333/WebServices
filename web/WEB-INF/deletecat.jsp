<%-- 
    Document   : editcat
    Created on : Mar 10, 2013, 12:51:38 AM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.dbmanagers.CategoriesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% CategoriesManager manager = new CategoriesManager(); 
   int catId = Integer.valueOf(request.getParameter("category_id"));
   String name = manager.getCategoryName(catId);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Confirm Delete Category</title>
	</head>
	<body>
		<h1>Delete category: <%= name %> ?</h1>
		<form action="EditCategory" method="POST">
			<input type="hidden" name="cat_id" value="<%= catId %>" />
			<input type="submit" name="action" value="Delete" />
			<input type="submit" name="action" value="Cancel" />
		</form>
	</body>
</html>
