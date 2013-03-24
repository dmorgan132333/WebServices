/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.grocerypricebook.model.ShopList;
import net.grocerypricebook.model.dbmanagers.ShoppingListManager;

/**
 *
 * @author Doug
 */
//@WebServlet(name = "ShopListServlet", urlPatterns = {"/ShopListServlet"})
public class ShopListServlet extends HttpServlet {
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
        
        ShoppingListManager shopL = new ShoppingListManager();
        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute("userId");
        String name = request.getParameter("List Name");
        
        
        System.out.println("forward to shoplist.jsp");
        RequestDispatcher dispatcher;
        if(request.getParameter("new_list")!= null){
            System.out.println("creating new list");
            try{
                shopL.addNewList(userId,name);
            }
            catch(SQLException e){
                System.out.println(e);
            }
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/shoplist.jsp");
            dispatcher.forward(request, response);
        }else if(request.getParameter("delete_list")!= null){
            System.out.println("deleting list");
            try{
                shopL.deleteList(userId, name);
            }
            catch(SQLException e){
                System.out.println(e);
            }
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/shoplist.jsp");
            dispatcher.forward(request, response);
            
        }else if(request.getParameter("list")!= null){
            System.out.println("going to list");
            String listName = request.getParameter("list_name");
            session.setAttribute("listName", listName);
            try{
                shopL.getItems();
                
            }
            catch(SQLException e){
                System.out.println(e);
            }
                
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/oneShopList.jsp");
                dispatcher.forward(request, response);
                
        }else{
            try{
               shopL.getShopList(userId);
            }
            catch(SQLException e){
                System.out.println(e);
            }
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/shoplist.jsp");
            dispatcher.forward(request, response);
        }
        
        
        
//        String[] itemsChecked = request.getParameterValues("items");
//        
//        HttpSession session = request.getSession();
//        String id = (String)session.getAttribute("firstName");
        
//        Users user = new Users();
        
        
        
//        if(itemsChecked != null){
//            for(int x = 0;x<itemsChecked.length;x++){
//                user.addToShopList(id,itemsChecked[x]);
//            }
//        }
        
//        ShopList shoplist = new ShopList();
        
        
//               if(session.getAttribute("")) {
//                   
//               }
//        ArrayList<String> sList = user.getShopList(id);
////        System.out.println(id +", "+sList +"itemschecked " + itemsChecked[0]);
//        request.setAttribute("shopping list", sList );
       
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
