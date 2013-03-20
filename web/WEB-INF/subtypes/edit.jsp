<%-- 
    Document   : edit
    Created on : Mar 20, 2013, 2:06:59 PM
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
		<title>Rename Subtype</title>
	</head>
	<body>
		<h1>Rename subtype: <%=name%></h1>
		<h1>Parent type: <%=parentName%></h2>
		<form action="RenameSubtype">
			<input type="hidden" name="item_type_id" value="<%= parentTypeId %>" />
			<input type="hidden" name="subtype_id" value="<%= subtypeId %>" />
			New Name: <input type="text" name="new_name" />
			<input type="submit" name="rename" value="Rename" />
			<input type="submit" name="cancel" value="Cancel" />
		</form>
	</body>
</html>
