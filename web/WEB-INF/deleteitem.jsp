<%-- 
    Document   : editcat
    Created on : Mar 10, 2013, 12:51:38 AM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.dbmanagers.ItemTypesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
   ItemTypesManager manager = new ItemTypesManager();
   int itemTypeId = Integer.parseInt(request.getParameter("item_type_id"));
   String name = manager.getItemTypeName(itemTypeId);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Confirm Delete Item</title>
	</head>
	<body>
		<h1>Delete item: <%= name %> ?</h1>
		<form action="EditItemType">
			<input type="hidden" name="item_type_id" value="<%= itemTypeId %>" />
			<input type="submit" name="action" value="Delete" />
			<input type="submit" name="action" value="Cancel" />
		</form>
	</body>
</html>
