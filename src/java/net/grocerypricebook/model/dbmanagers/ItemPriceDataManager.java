/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model.dbmanagers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import net.grocerypricebook.model.ItemPriceData;
import net.grocerypricebook.model.ItemPriceDataEntry;
import net.grocerypricebook.model.JDBCUtilities;
import net.grocerypricebook.model.exceptions.ItemNotFoundException;

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
		con.close();
	}

	/**
	 * Returns an ItemPriceData object which represents all available price data
	 * for the given item.
	 * @param itemId
	 * @return
	 * @throws SQLException
	 * @throws ItemNotFoundException 
	 */
	public ItemPriceData getItemPriceData(int itemId) throws SQLException, ItemNotFoundException{
		Connection con = JDBCUtilities.getConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT price, unit, date, store_id, user_id FROM item_price_data WHERE item_id = " + itemId;
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<ItemPriceDataEntry> data = new ArrayList<ItemPriceDataEntry>();
		while(rs.next()){
			ItemPriceDataEntry dataEntry = new ItemPriceDataEntry();
			dataEntry.setItemId(itemId);
			dataEntry.setPrice(rs.getFloat("price"));
			dataEntry.setUnitId(rs.getInt("unit"));
			dataEntry.setDate(rs.getDate("date"));
			dataEntry.setStoreId(rs.getInt("store_id"));
			dataEntry.setUserId(rs.getInt("user_id"));
			data.add(dataEntry);
		}
		con.close();
		return new ItemPriceData(data, new ItemManager().getItem(itemId));
	}
	
}