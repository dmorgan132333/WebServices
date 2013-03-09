/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.servlet;

import net.grocerypricebook.model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.grocerypricebook.model.ShopList;

/**
 *
 * @author Doug
 */
//@WebServlet(name = "ShopListServlet", urlPatterns = {"/ShopListServlet"})
public class ShopListServlet extends HttpServlet {
    Users user = new Users();
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
        PrintWriter out = response.getWriter();
       String[] itemsChecked = request.getParameterValues("items");
        
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("firstName");
        
//        Users user = new Users();
        
        
        
        if(itemsChecked != null){
            for(int x = 0;x<itemsChecked.length;x++){
                user.addToShopList(id,itemsChecked[x]);
            }
        }
        
//        ShopList shoplist = new ShopList();
        
        
//               if(session.getAttribute("")) {
//                   
//               }
        ArrayList<String> sList = user.getShopList(id);
//        System.out.println(id +", "+sList +"itemschecked " + itemsChecked[0]);
        request.setAttribute("shopping list", sList );
       //request.setAttribute("accountList",accounts);
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShopList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopList at " + request.getContextPath() + "</h1>");
            out.println("" + request.getAttribute("shopping list"));
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
