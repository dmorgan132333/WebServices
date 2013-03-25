<%-- 
    Document   : groceryStore
    Created on : Mar 21, 2013, 3:40:17 PM
    Author     : dmoney1323
--%>

<%@page import="net.grocerypricebook.model.GroceryStore"%>
<%@page import="net.grocerypricebook.model.dbmanagers.GroceryStoreManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%GroceryStoreManager gsm = new GroceryStoreManager(); 
    int userId = (Integer)request.getSession().getAttribute("userId");
    Object empty_field = request.getAttribute("empty_field");
    boolean empty = true;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grocery Store Page</title>
    </head>
    <body>
        <h1>Grocery Store Data</h1>
        <h2><a href="welcome.jsp">Home</a></h2> 
        
        <% if((empty_field != null) && ((Boolean)empty_field == empty)){%>
            <font color="red">One or more fields are empty</font>
        <%}%>
        <form action="GroceryStoreServlet" method="GET">
            <table>
                <tr>
                    <td>
                        Store Name      <input type="text" name="store_name" size="20">
                    </td>
                  
                </tr>
                <tr>
                    <td>
                        Store State     <input type="text" name="state" size="20">
                    </td>
                  
                </tr>
                <tr>
                    <td>
                        Store Address   <input type="text" name="address" size="20">
                    </td>
                  
                </tr>
                <tr>
                    <td>
                       Store City       <input type="text" name="city" size="20">
                    </td>
                  
                </tr>
                <tr>
                    <td>
                        Store Zip       <input type="text" name="zip" size="20">
                    </td>
                </tr>
               
                <tr align="center">
                    <td>
                        <input type="submit" name="new_store" value="New Store">
                    </td>
                </tr>   
            </table>
        </form>
    <center>
        <%for(GroceryStore gs: gsm.getStores()){%>
        <table border="1">
            <th>Store Name</th>
            <th>State</th>
            <th>City</th>
            <th>Address</th>
            <th>zip</th>
            <th>User ID</th>
            
            <tr>
                <td width="100">
                    <%=gs.getName()%>
                </td>
                <td width="100">
                    <%=gs.getState()%>
                </td>
                <td width="100">
                    <%=gs.getCity()%>
                </td>
                <td width="200">
                    <%=gs.getAddress()%>
                </td>
                <td width="100">
                    <%=gs.getZip()%>
                </td>
                <td width="100">
                    <%=gs.getUserID()%>
                </td>
                <td>
                    <%
                        String id = Integer.toString(gs.getStoreID());
                    %>
                    <form action="GroceryStoreServlet" method="GET">
                        <input type="hidden" name="store_id" value="<%=id%>">
                        <input type="hidden" name="store_name" value="<%=gs.getName()%>">
                        <input type="hidden" name="state" value="<%=gs.getState()%>">
                        <input type="hidden" name="city" value="<%=gs.getCity()%>">
                        <input type="hidden" name="address" value="<%=gs.getAddress()%>">
                        <input type="hidden" name="zip" value="<%=gs.getZip()%>">
                        <input type="hidden" name="user_id" value="<%=gs.getUserID()%>">
                        <input type="submit"  name="edit" value="Edit Store">
                    </form>
                    <form action="GroceryStoreServlet" method="GET">
                        <input type="hidden" name="store_id" value="<%=id%>">
                        
                        <input type="submit" name="delete_store" value="Delete Store">
                    </form>
                </td>
                
            </tr>
        </table>
        
        <%}%>
    </center>
        
    </body>
</html>
