<%-- 
    Document   : shoplist
    Created on : Feb 20, 2013, 2:10:56 PM
    Author     : Doug
--%>

<%@page import="net.grocerypricebook.model.ShopList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ShoppingListManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ShoppingListManager slm = new ShoppingListManager(); 
    int userId = (Integer)request.getSession().getAttribute("userId");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <center>
        <h1>Shopping list</h1>
        <h2><a href="welcome.jsp">Home</a></h2> 
        <%for(ShopList sl: slm.getShopList(userId)){%>
        <table>
            <tr>
                <td width="100">
                    <%=sl.getName()%>
                </td>
                <td>
                    <form action="ShopListServlet" method="GET">
                        <input type="submit" name="list" value="list">
                    </form>
                </td>
            </tr>
        </table>
        
        <%}%>
        <form action="ShopListServlet" method="GET">
            <table>
                <tr colspan="2">
                    <td colspan="2">
                        List Name <input type="text" name="List Name" size="20">
                    </td>
                </tr>
                <tr align="center">
                    <td>
                        <input type="submit" name="new_list" value="new list">
                    </td>
                    <td>
                        <input type="submit" name="delete_list" value="delete list">
                    </td>
                </tr>
            </table>
        </form>
        </center>
    </body>
</html>
