<%-- 
    Document   : editcat
    Created on : Mar 10, 2013, 12:51:38 AM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Categories"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% Categories cat = new Categories(); 
   String name = cat.getCategoryName(Integer.valueOf(request.getParameter("category_id")));
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Category</title>
	</head>
	<body>
		<h1>Edit Category: <%= name %></h1>
		<form action="EditCategory">
			<table>
				<tr>
					<td>New Name: </td>
					<td><input type="text" name="new_name" /></td>
				</tr>
			</table>
			<input type="submit" name="action" value="Submit" />
			<input type="submit" name="action" value="Cancel" />
		</form>
	</body>
</html>
