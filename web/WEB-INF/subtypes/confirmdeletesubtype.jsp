<%-- 
    Document   : confirmdeletesubtype
    Created on : Mar 20, 2013, 12:30:54 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.dbmanagers.ItemTypesManager"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemTypesManager"%>
<%@page import="net.grocerypricebook.model.ItemSubType"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemSubTypesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	ItemSubTypesManager subtypeManager = new ItemSubTypesManager();
	ItemTypesManager typesManager = new ItemTypesManager();
	int subtypeId = Integer.parseInt(request.getParameter("subtype_id"));
	int parentTypeId = subtypeManager.getParentId(subtypeId);
	String parentName = typesManager.getItemTypeName(parentTypeId);
	String name = subtypeManager.getSubtypeName(subtypeId);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Confirm subtype deletion</title>
	</head>
	<body>
		<h1>Are you sure you want to delete subtype: <%= name %>, of parent type: <%=parentName%>?</h1>
		<form action="DeleteSubtype">
			<input type="hidden" name="item_type_id" value="<%= parentTypeId %>" />
			<input type="hidden" name="subtype_id" value="<%= subtypeId %>" />
			<input type="submit" name="delete" value="Delete" />
			<input type="submit" name="cancel" value="Cancel" />
		</form>
	</body>
</html>
