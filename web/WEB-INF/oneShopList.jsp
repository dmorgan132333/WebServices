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

<%String listname = (String)session.getAttribute("listName");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Page</title>
    </head>
    <body>
    <center>
        <h2><a href="ShopListServlet">Shopping list's</a></h2> 
        <h1>List: <%=listname%></h1>
        <%for(ItemList il: slm.getItems()){%>
        <table>
            <tr>
                <td width="100">
                    <%=il.getName()%>
                </td>
            </tr>
        </table>
                <%}%>
                <table border="1">
                    <th>id</th>
                    <th>name</th>
                    <%for(ItemList il: slm.getItems()){%>
                    
                    <tr>
                        <td><%=il.getId()%>
                        </td>
                        <td width="100">
                        <%=il.getName()%>
                        </td>
                    </tr>
                    <%}%>
                </table>
                </center>
    </body>
</html>
