package net.grocerypricebook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.grocerypricebook.model.dbmanagers.ItemPriceDataManager;

/**
 *
 * @author mike
 */
@WebServlet(name = "addprice", urlPatterns = {"/addprice"})
public class AddPrice extends HttpServlet {

	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and
	 * <code>POST</code> methods.
	 * 
	 * Incoming request parameters: item_id 
	 * 				price
	 * 				unit_id
	 * 				date: YYYY-MM-DD
	 * 				store_id
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");

		int itemId = Integer.parseInt(request.getParameter("item_id"));
		float price = Float.parseFloat(request.getParameter("price"));
		int unitId = Integer.parseInt(request.getParameter("unit_id"));
		String dateStr = request.getParameter("date");
		int storeId = Integer.parseInt(request.getParameter("store_id"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date date = format.parse(dateStr);
			new ItemPriceDataManager().addData(unitId, itemId, storeId, date, price, unitId);
			dispatcher.forward(request, response);
		} catch (SQLException e){
			System.out.println(e);
		} catch (ParseException e){
			System.out.println(e);
		} finally {
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
