<%-- 
    Document   : editcat
    Created on : Mar 10, 2013, 12:51:38 AM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Categories"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% Categories cat = new Categories(); 
   int catId = Integer.valueOf(request.getParameter("category_id"));
   String name = cat.getCategoryName(catId);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Confirm Delete Category</title>
	</head>
	<body>
		<h1>Delete category: <%= name %> ?</h1>
		<form action="EditCategory">
			<input type="hidden" name="cat_id" value="<%= catId %>" />
			<input type="submit" name="action" value="Delete" />
			<input type="submit" name="action" value="Cancel" />
		</form>
	</body>
</html>
