<%-- 
    Document   : edititemsubtypes
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.ItemSubType"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemTypesManager"%>
<%@page import="net.grocerypricebook.model.ItemType"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemSubTypesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   ItemSubTypesManager subTypesManager = new ItemSubTypesManager();
   ItemTypesManager itemTypesManager = new ItemTypesManager();
   int userId = (Integer) session.getAttribute("userId");
   int itemTypeId = Integer.parseInt(request.getParameter("item_type_id"));
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
		<h2><a href="edititemtypes.jsp">Back</a></h2>
		<table>
			<tr>
				<td><b><%= itemTypeName %></b></td>
				<td></td>
			</tr>
			<% for(ItemSubType subType : subTypesManager.getItemSubTypes(itemTypeId, userId)) { %>
			<tr>
				<td><%= subType.getName() %></td>
				<td>
					<form action="EditSubtypeDispatcher">
						<input type="hidden" name="subtype_id" value="<%= subType.getId() %>" />
						<input type="submit" name="rename" value="Rename" />
						<input type="submit" name="delete" value="Delete" />
					</form>
				</td>
			</tr>
			<% } %>

			<tr>
				<td>Create new subtype: </td>
				<td>
					<form action="EditSubtypeDispatcher">
						<input type="hidden" name="item_type_id" value="<%=itemTypeId%>" />
						<input type="text" name="new_subtype_name" />
						<input type="submit" name="add" value="Create" />
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>
