/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.servlet.subtypes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.grocerypricebook.model.dbmanagers.ItemSubTypesManager;
import net.grocerypricebook.model.dbmanagers.ItemTypesManager;

/**
 *
 * @author mike
 */
public class EditSubtypeDispatcher extends HttpServlet {

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
		HttpSession session = request.getSession(false);
		dispatch = request.getRequestDispatcher("/edititemsubtypes.jsp");


		//Initialize database managers
		ItemSubTypesManager subtypeManager = new ItemSubTypesManager();

		int userId = (Integer)session.getAttribute("userId");
		System.out.println("THING: " + request.getParameter("item_type_id"));

		 if(request.getParameter("add") != null){
			int baseTypeId = Integer.parseInt(request.getParameter("item_type_id"));
			String newName = request.getParameter("new_subtype_name");

			try{
				subtypeManager.addSubtype(baseTypeId, userId, newName);
			} catch (SQLException e){
				System.out.println(e);
			}
		} else if(request.getParameter("delete") != null){
			dispatch = request.getRequestDispatcher("/WEB-INF/subtypes/confirmdeletesubtype.jsp");
		} else if(request.getParameter("rename") != null){
			dispatch = request.getRequestDispatcher("/WEB-INF/subtypes/edit.jsp");
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
