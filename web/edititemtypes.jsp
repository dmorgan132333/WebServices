<%-- 
    Document   : edititemtypes
    Created on : Mar 10, 2013, 7:56:32 PM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.ItemSubType"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemSubTypesManager"%>
<%@page import="net.grocerypricebook.model.Category"%>
<%@page import="net.grocerypricebook.model.ItemType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.CategoriesManager"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemTypesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ItemTypesManager typesManager = new ItemTypesManager(); 
   ItemSubTypesManager subTypesManager = new ItemSubTypesManager();
   CategoriesManager categoriesManager = new CategoriesManager();
   int userId = (Integer)session.getAttribute("userId");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Item Types</title>
	</head>
	<body>
		<h1>Edit Item Types</h1>
		<h2><a href="welcome.jsp">Home</a></h2>
		<style type="text/css">
			td.subtype{
				padding-left: 20px;
				column-span: 2;
			}
		</style>
		<table>
			<tr>
				<td>Name</td>
				<td>Basic Category</td>
				</td>
			</tr>
			<% for(ItemType itemType : typesManager.getAllItemTypes(userId) ) { %>
			<form action="EditItemTypesDispatcher" method="GET">
				<input type="hidden" name="item_type_id" value="<%= itemType.getId() %>" />
				<tr><td><%= itemType.getName() %></td>
					<td>
						<%= categoriesManager.getCategoryName(itemType.getBaseCategoryId()) %>
					</td>
					<td><input type="submit" name="edit" value="Edit" /><input type="submit" name="delete" value="Delete"<td>
					<td>&nbsp;&nbsp;<input type="submit" name="edit_subtypes" value="Manage Subtypes"/></td>
				</tr>
				<% for(ItemSubType subType: subTypesManager.getItemSubTypes(itemType.getId(), userId)) { 	%>
				<tr>
					<td class="subtype"><%= subType.getName() %></td>
				</tr>
				<% } %>
			</form>
			<% } %>
			<tr><td></br></td></tr>

			<form action="EditItemTypesDispatcher" method="POST">
			<tr>
				<td>Add new item type:</td>
				<td><input type="text" name="addNewName" />
					<select name="cat_id">
						<option>Select basic category</option>
						<% for(Category category : categoriesManager.getBasicCategories((Integer)session.getAttribute("userId"))){
							out.println("<option value="+ category.getCatId() +">"+ category.getName() +"</option>");
						   }
						%>
					</select>
					<input type="submit" name="add" value="Add"/>
				</td>
			</tr>
			</form>
		</table>
	</body>
</html>
