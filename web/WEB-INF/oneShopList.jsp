<%-- 
    Document   : oneShopList
    Created on : Mar 20, 2013, 9:58:36 AM
    Author     : dmoney1323
--%>

<%@page import="net.grocerypricebook.model.ItemList"%>
<%@page import="net.grocerypricebook.model.dbmanagers.ShoppingListManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ShoppingListManager slm = new ShoppingListManager(); 
    int userId = (Integer)request.getSession().getAttribute("userId");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Page</title>
    </head>
    <body>
    <center>
        <h1>Monday</h1>
        <%for(ItemList il: slm.getItems("monday")){%>
        <table>
            <tr>
                <td width="100">
                    <%=il.getName()%>
                </td>
            </tr>
        </table>
                <%}%>
    </center>
    </body>
</html>
