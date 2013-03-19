/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.grocerypricebook.model.dbmanagers.ItemTypesManager;

/**
 *
 * @author mike
 */
public class EditItemType extends HttpServlet {
	private HttpSession HttpSession;

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
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatch;
		HttpSession session = request.getSession(false);
		ItemTypesManager itemTypeManager = new ItemTypesManager();
		dispatch = request.getRequestDispatcher("/edititemtypes.jsp");
		//Get the action value
		String action = request.getParameter("action");
		int userId = (Integer)session.getAttribute("userId");
		int itemId = Integer.parseInt(request.getParameter("item_type_id"));
		if(action.equals("Submit")){
			String newName = request.getParameter("new_name");
			int newBaseCatId = Integer.parseInt(request.getParameter("new_base_cat_id"));
			try{
				itemTypeManager.editItemType(itemId, userId, newName, newBaseCatId);
			} catch (SQLException e){
				System.out.println(e);
			}
		} else if(action.equals("Delete")){
			try{
				itemTypeManager.deleteItemType(itemId, userId);
			} catch (SQLException e){
				System.out.println(e);
			}
		}
		dispatch.forward(request, response);
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
