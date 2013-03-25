<%-- 
    Document   : additem
    Created on : Mar 24, 2013, 12:23:34 AM
    Author     : mike
--%>

<%@page import="java.io.IOException"%>
<%@page import="net.grocerypricebook.model.dbmanagers.CategoriesManager"%>
<%@page import="net.grocerypricebook.model.Category"%>
<%@page import="net.grocerypricebook.model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ItemManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ItemManager itemManager = new ItemManager();
   CategoriesManager catMan = new CategoriesManager();
   int userId = (Integer) session.getAttribute("userId");
   ArrayList<Item> items = itemManager.getAllItems(userId);
   ArrayList<Category> cats = catMan.getCategories(userId);

   String name = request.getParameter("name");

   String[] catsStr = request.getParameterValues("category");
   int numCats = 0;
   if(catsStr != null){
	   numCats = catsStr.length;
   }

   String addCatStr = request.getParameter("add_cat");
   boolean addCat = false;
   if(addCatStr != null){
	addCat = true;
   }
%>

<%! 
	private void printSelectCats(ArrayList<Category> cats, int selected, JspWriter out) throws IOException {
		out.println("<select name=\"category\">");
		out.println("<option value=\"0\">None</option>");
		for(Category cat: cats){
			String option;
			if(cat.getCatId() == selected){
				option = String.format("<option value=\"%d\" selected>%s</option>", cat.getCatId(), cat.getName());
			} else {
				option = String.format("<option value=\"%d\">%s</option>", cat.getCatId(), cat.getName());
			}
			out.println(option);
		}
		out.println("</select>");
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add Item</title>
	</head>
	<body>
		<h1>Create new item!</h1>
		<h2><a href="edititems.jsp">Back</a></h2>

		<form action="additem">
			Name: <input type="text" name="name" <% if(name != null) out.print("value=\""+name+"\""); %> />
		Parent: 
		<select name="parent_id">
			<option value="0">No parent</option>
			<% for(Item i: items) { %>
				<option value="<%= i.getId() %>"><%= i.getFullName() %></option>
			<% } %>
	        </select>

		<% if(numCats == 0){
			out.print("Category 1: ");
			printSelectCats(cats, -1, out);
		   } else {
			for(int i = 0; i < numCats; i++){
				out.print("Category " + (i + 1) + ": ");
				printSelectCats(cats, Integer.parseInt(catsStr[i]), out);
			}
		   }
		   
		   if(addCat){ 
			   out.print("Category " + (numCats + 1) + ": ");
			   printSelectCats(cats, -1, out);
		   }
		%>
		<input type="submit" name="add_cat" value="Add Additional Category" />

		</br></br><input type="submit" name="submit" value="Submit" />
		</form>
	</body>
</html>