<%-- 
    Document   : edititemsubtypes
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.dbmanagers.ItemTypesManager"%>
<%@page import="net.grocerypricebook.model.ItemType"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemSubTypesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   ItemSubTypesManager subTypesManager = new ItemSubTypesManager();
   ItemTypesManager itemTypesManager = new ItemTypesManager();
   int userId = (Integer) session.getAttribute("userId");
   int itemTypeId = (Integer) request.getAttribute("item_type_id");
   String itemTypeName = itemTypesManager.getItemTypeName(itemTypeId);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Subtypes</title>
	</head>
	<body>
		<h1>Edit Subtypes for Item Type: <%= itemTypeName %></h1>
		<h2><a href="welcome.jsp">Home</a></h2>
		<table>
		</table>
	</body>
</html>
