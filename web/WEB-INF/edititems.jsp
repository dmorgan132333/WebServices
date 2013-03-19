<%-- 
    Document   : editcat
    Created on : Mar 10, 2013, 12:51:38 AM
    Author     : mike
--%>

<%@page import="net.grocerypricebook.model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemTypesManager"%>
<%@page import="net.grocerypricebook.model.dbmanagers.CategoriesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% CategoriesManager categoriesManager = new CategoriesManager(); 
   ItemTypesManager itemTypesManager = new ItemTypesManager();

   int itemTypeId = Integer.parseInt(request.getParameter("item_type_id"));
   int catId = itemTypesManager.getBaseCategory(itemTypeId);
   String catName = categoriesManager.getCategoryName(catId);
   String name = itemTypesManager.getItemTypeName(itemTypeId);
   ArrayList<Category> basicCategories = categoriesManager.getBasicCategories((Integer)session.getAttribute("userId"));
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Item</title>
	</head>
	<body>
		<h1>Edit Item: <%= name %></h1>
		<form action="EditItemType">
			<table>
				<tr>
					<td>New Name: </td>
					<td><input type="text" name="new_name" /></td>
					<td>
					<select name="new_base_cat_id">
						<option>Select Base Category</option>
						<% for(Category category : basicCategories){
							out.println("<option value="+ category.getCatId() +">"+ category.getName() +"</option>");
						   }
						%>
					</select>
					</td>

				</tr>
			</table>
			<input type="hidden" name="item_type_id" value="<%= itemTypeId %>" />
			<input type="submit" name="action" value="Submit" />
			<input type="submit" name="action" value="Cancel" />
		</form>
	</body>
</html>
