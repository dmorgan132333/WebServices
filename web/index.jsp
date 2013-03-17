<%-- 
    Document   : index
    Created on : Feb 18, 2013, 12:53:10 PM
    Author     : Doug, Mike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Object loginError = request.getAttribute("loginError"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Sign In</h1>
        <%= new java.util.Date() %>
        <form action="LoginServlet" method="POST">
            Username: <input type="text" name="username"><br />
            Password: <input type="password" name="password"><br /><br />
            <input type="submit" value="Submit">
                <% if(loginError != null && (Boolean)loginError){ %>
                <font color="red">Username or password incorrect, please try again.</font>
                <% } %>
        </form>
    </body>
</html>
