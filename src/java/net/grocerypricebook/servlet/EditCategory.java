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
import net.grocerypricebook.model.CategoriesManager;
import net.grocerypricebook.model.Category;
import net.grocerypricebook.model.exceptions.CategoryNotFoundException;

/**
 *
 * @author mike
 * TODO: Handle case where userId != category's userId.
 */
public class EditCategory extends HttpServlet {

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
		RequestDispatcher dispatch;
		dispatch = request.getRequestDispatcher("/editcategories.jsp");
		//Get the action value
		String action = request.getParameter("action");
        int catId = Integer.parseInt(request.getParameter("cat_id"));
        int userId = (Integer)request.getSession().getAttribute("userId");
        CategoriesManager manager = new CategoriesManager();
        try{
        Category category = manager.getCategory(catId);
        if(userId == category.getUserId()){
            if(action.equals("Submit")){
                manager.editCategory(Integer.parseInt(request.getParameter("cat_id")), request.getParameter("new_name"));
            } else if(action.equals("Delete")){
                manager.deleteCategory(Integer.parseInt(request.getParameter("cat_id")));
            }
        } else {
            System.out.println("Cannot edit category because it does not belong to user.");
        }
		dispatch.forward(request, response);
        } catch (SQLException e) {
            System.out.println(e); 
        } catch (CategoryNotFoundException e){
            PrintWriter out = response.getWriter();
            out.write(e.toString());
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
