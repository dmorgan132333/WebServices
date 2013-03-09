<%-- 
    Document   : welcome
    Created on : Feb 20, 2013, 1:08:47 PM
    Author     : Doug
--%>

<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
            String iD = (String)session.getAttribute("firstName");
            request.setAttribute("id", iD);
            
        %>
        <h1>Welcome, <%=iD%></h1>  
        
        <ul>
            <li></li>
        </ul>
    <center>
        <%= request.getAttribute("items") %>
    </center>
            <form action="ShopListServlet" method="POST">
                <input type="submit" value="Personal list">
            </form>
    <center>
    <form action="ShopListServlet" method="POST">
        <input type="checkbox" name="items" value="Milk">Milk
        <input type="checkbox" name="items" value="Cheese">Cheese
        <input type="checkbox" name="items" value="Eggs">Eggs
        <input type="checkbox" name="items" value="Chocolate Milk">Chocolate Milk
        <input type="checkbox" name="items" value="Peanut Butter">Peanut Butter
        <input type="checkbox" name="items" value="Jelly">Jelly
        <input type="submit" value="add to list">
    </form>
        
    </center>
    </body>
</html>
