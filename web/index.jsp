<%-- 
    Document   : index
    Created on : Feb 18, 2013, 12:53:10 PM
    Author     : Doug
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <CENTER>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sign In </h1>
        <%= new java.util.Date() %>
        <form action="GreetingServlet" method="POST">
            First Name: <input type="text" name="firstName"size="20"><br />
            Surname: <input type="text" name="surname" size="20"><br /><br />
            <input type="submit" value="Submit">
        </form>
        <% 
            String wrong = "";
            if(request.getAttribute("Wrong" )!= null){
                wrong = (String)request.getAttribute("Wrong");
             }
            
        %>
        <%= wrong %>
        
    </body>
    </CENTER>
</html>
