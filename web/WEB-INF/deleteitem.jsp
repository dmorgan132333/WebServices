<%-- 
    Document   : editcat
    Created on : Mar 10, 2013, 12:51:38 AM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Items"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
   Items items = new Items();
   int itemId = Integer.parseInt(request.getParameter("item_id"));
   String name = items.getItemName(itemId);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Confirm Delete Item</title>
	</head>
	<body>
		<h1>Delete item: <%= name %> ?</h1>
		<form action="EditItem">
			<input type="hidden" name="item_id" value="<%= itemId %>" />
			<input type="submit" name="action" value="Delete" />
			<input type="submit" name="action" value="Cancel" />
		</form>
	</body>
</html>
