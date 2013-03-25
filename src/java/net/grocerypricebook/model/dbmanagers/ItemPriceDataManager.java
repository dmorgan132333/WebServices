/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model.dbmanagers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import net.grocerypricebook.model.JDBCUtilities;


/**
 * Manages queries on table item_price_data.
 * @author mike
 */
public class ItemPriceDataManager {

	public void addData(int userId, int itemId, int storeId, Date date, double price, int unitId) throws SQLException{
		Connection con = JDBCUtilities.getConnection();
		Statement stmt = con.createStatement();
		//MySQL DATE format is YYYY-MM-DD
		String query = "INSERT INTO item_price_data(user_id, item_id, store_id, date, price, unit) VALUES(%d, %d, %d, '%tY-%tm-%td', %f, %d)";
		System.out.println("DATE: " + date);
		query = String.format(query, userId, itemId, storeId, date, date, date, price, unitId);

		stmt.executeUpdate(query);
	}
	
}
