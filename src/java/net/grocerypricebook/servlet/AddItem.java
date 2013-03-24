/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.grocerypricebook.model.dbmanagers.ItemManager;
import net.grocerypricebook.model.exceptions.ItemNotFoundException;

/**
 *
 * @author mike
 */
@WebServlet(name = "AddItem", urlPatterns = {"/additem"})
public class AddItem extends HttpServlet {

	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and
	 * <code>POST</code> methods.
	 * 
	 * Incoming parameters: name - name of item 
	 * 			parent_id - id of parent item 
	 * 			category - id of categories. Possibly multiple. Zero for no selection. 
	 * 			add_cat - Submit button, pressed if the user wants ability to add additional category.
	 * 			submit - Submit button, pressed when user wants to add the data.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 * TODO: check for valid parameters and handle error if not valid.
	 * TODO: create some kinda WIZARD so that when selecting a parent type, categories are handled correctly
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		ItemManager itemMan = new ItemManager();
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/additem.jsp"); //Default dispatch.
		if(request.getParameter("add_cat") != null){
			dispatcher.forward(request, response);
		} else if(request.getParameter("submit") != null) {
			String name = request.getParameter("name");
			int parentId = Integer.parseInt(request.getParameter("parent_id"));
			int userId = (Integer)session.getAttribute("userId");
			String[] catsStr = request.getParameterValues("category");
			ArrayList<String> catStrArr = new ArrayList<String>(Arrays.asList(catsStr));
			ArrayList<Integer> cats = new ArrayList<Integer>();

			for(String str: catStrArr){
				int val = Integer.parseInt(str);
				if(val != 0){
					cats.add(val);
				}
			}

			try {
				itemMan.addItem(userId, parentId, name, cats);
			} catch (SQLException ex) {
				Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ItemNotFoundException ex) {
				Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
			}
			dispatcher = request.getRequestDispatcher("/edititems.jsp");
			dispatcher.forward(request, response);
		} else {
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
