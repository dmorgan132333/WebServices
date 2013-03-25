<%-- 
    Document   : editGroceryStore
    Created on : Mar 24, 2013, 3:15:57 PM
    Author     : dmoney1323
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Store Page</title>
    </head>
    <body>
        <h1>Edit Store</h1>
        <h2><a href="GroceryStoreServlet">Store List</a></h2> 
         <form action="GroceryStoreServlet" method="GET">
        <table>
                <tr>
                    <td>
                        Store Name      <input type="text" name="store_name" value="<%=session.getAttribute("store_name")%>"size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                        Store Address   <input type="text" name="address" value="<%=session.getAttribute("state")%>" size="20">
                    </td>
                  
                </tr>
                <tr>
                    <td>
                        Store State     <input type="text" name="state" value="<%=session.getAttribute("city")%>" size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                       Store City       <input type="text" name="city" value="<%=session.getAttribute("address")%>" size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                        Store Zip       <input type="text" name="zip" value="<%=session.getAttribute("zip")%>" size="20">
                    </td>
                </tr>
                <tr align="center">
                    <td>
                       
                            <input type="submit" name="edit_store" value="Edit Store">
                        </form>
                    </td>
                </tr>   
            </table>
    </body>
</html>
