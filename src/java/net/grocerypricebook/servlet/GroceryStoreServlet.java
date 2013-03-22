/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.grocerypricebook.model.dbmanagers.GroceryStoreManager;

/**
 *
 * @author dmoney1323
 */
public class GroceryStoreServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        GroceryStoreManager gsm = new GroceryStoreManager();
        
        HttpSession session = request.getSession();
        
        int userId = (Integer)session.getAttribute("userId");
        
        RequestDispatcher dispatcher;
         
         if(request.getParameter("new_store")!= null){
            System.out.println("creating new store");
            try{
                String name = request.getParameter("store_name");
                String state = request.getParameter("state");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                String zip = request.getParameter("zip");
//                int userID, String name,String state,String city,String address,int zip
                gsm.addNewGroceryStore(userId,name,state,city,address,zip);
            }
            catch(SQLException e){
                System.out.println(e);
            }
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/groceryStore.jsp");
            dispatcher.forward(request, response);
        }else if(request.getParameter("delete_store") != null){
            String id = request.getParameter("store_id");
            System.out.println("deleteing store " + id +" " + userId);
            try{
                gsm.deleteGroceryStore(Integer.parseInt(id),userId);
            }catch(SQLException e){
                System.out.println(e);
            }
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/groceryStore.jsp");
         
            dispatcher.forward(request, response);
        }else if(request.getParameter("edit")!= null){
            String name = request.getParameter("store_name");
            session.setAttribute("store_name", name);
            
            String state = request.getParameter("state");
            session.setAttribute("state", state);
            
            String city = request.getParameter("city");
            session.setAttribute("city", city);
            
            String address = request.getParameter("address");
            session.setAttribute("address", address);
            
            String zip = request.getParameter("zip");
            session.setAttribute("zip", zip);
                
            String edit = request.getParameter("edit");
            session.setAttribute("edit",edit);
             
            String id = request.getParameter("store_id");
            
            
            int idd = Integer.parseInt(id);
            System.out.println(idd);
            
            System.out.println("editing store ");
            session.setAttribute("edit",edit);
             System.out.println(name + ""+ state + ""+ city+ ""+address+ ""+zip);
            try{
                gsm.editGroceryStore(idd,userId,name,state,city,address,zip);
            }catch(SQLException e){
                System.out.println(e);
            }
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/groceryStore.jsp");
           
            dispatcher.forward(request, response);

        }else{
            
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/groceryStore.jsp");
         
            dispatcher.forward(request, response);
         }
            
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
