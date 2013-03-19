<%-- 
    Document   : shoplist
    Created on : Feb 20, 2013, 2:10:56 PM
    Author     : Doug
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shopping list</h1>
        <h2><a href="welcome.jsp">Home</a></h2> 
        <h2>just making an update</h2>
        
        <form action="ShopListServlet" method="GET">
            List Name <input type="text" name="List Name" size="20">
            <input type="submit" name="delete_list" value="delete list">
        </form>
        <form action="ShopListServlet" method="GET">
            List Name <input type="text" name="List Name" size="20">
            <input type="submit" name="new_list" value="new list">
        </form>
    </body>
</html>
