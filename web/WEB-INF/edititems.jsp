<%-- 
    Document   : editcat
    Created on : Mar 10, 2013, 12:51:38 AM
    Author     : mike
--%>

<%@page import="java.util.Map"%>
<%@page import="net.grocerypricebook.model.Items"%>
<%@page import="net.grocerypricebook.model.Categories"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% Categories cat = new Categories(); 
   Items items = new Items();

   Map<Integer, String> categories = cat.getCategories();
   int itemId = Integer.parseInt(request.getParameter("item_id"));
   int catId = items.getItemCategory(itemId);
   String catName = cat.getCategoryName(catId);
   String name = items.getItemName(itemId);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Item</title>
	</head>
	<body>
		<h1>Edit Item: <%= name %></h1>
		<form action="EditItem">
			<table>
				<tr>
					<td>New Name: </td>
					<td><input type="text" name="new_name" /></td>
					<td>
					<select name="new_cat_id">
						<% for(Map.Entry<Integer, String> catMap : categories.entrySet()){
							out.println("<option value="+ catMap.getKey() +">"+ catMap.getValue() +"</option>");
						   }
						%>
					</select>
					</td>

				</tr>
			</table>
			<input type="hidden" name="item_id" value="<%= itemId %>" />
			<input type="submit" name="action" value="Submit" />
			<input type="submit" name="action" value="Cancel" />
		</form>
	</body>
</html>
