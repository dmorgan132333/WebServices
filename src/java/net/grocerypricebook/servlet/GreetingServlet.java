/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.servlet;

import net.grocerypricebook.model.UsersOld;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "GreetingServlet", urlPatterns = {"/GreetingServlet"})
public class GreetingServlet extends HttpServlet {

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
     public void forward(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
          String firstName = request.getParameter("firstName").toString();
          String surname = request.getParameter("surname").toString();
          
          HttpSession session = request.getSession();
          UsersOld IDCheck = new UsersOld();
//          System.out.println(IDCheck.getUsers().keySet());
//          System.out.println(IDCheck.getUsers().containsKey(firstName));
          if(IDCheck.getUsersID().containsKey(firstName)){
              if(IDCheck.getPassword().containsValue(surname)){
                  session.setAttribute("firstName", firstName);
                  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp"); 
                  ShopList sItems = new ShopList();
                  ArrayList<String> items = sItems.getList();
                  request.setAttribute("items", items);
                  
                  dispatcher.forward(request, response);
              }else{
                request.setAttribute("Wrong", "First name or surname is wrong please try again");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp"); 
                  dispatcher.forward(request, response);
          }
          }else{
              request.setAttribute("Wrong", "First name or surname is wrong please try again");
               RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp"); 
                  dispatcher.forward(request, response);
          }
        
     }
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        
//        String firstName = request.getParameter("firstName").toString();
//        String surname = request.getParameter("surname").toString();
//        
//       
//        
//        ShopList shoplist = new ShopList();
//        ArrayList<String> sList = shoplist.getList();
//                
//        request.setAttribute("shopping list", sList );
//        
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet GreetingServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet GreetingServlet at " + request.getContextPath() + "</h1>");
//            out.println("<p>Welcome, " + firstName + " " + surname + "</p>");
//            out.println("" + request.getAttribute("shopping list"));
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
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
//        response.setContentType("text/html");
        
//        response.getParameter("iD");
//        response.get
//        processRequest(request, response);
        forward(request, response);
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
