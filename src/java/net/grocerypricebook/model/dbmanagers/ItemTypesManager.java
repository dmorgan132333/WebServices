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
import net.grocerypricebook.model.ItemType;
import net.grocerypricebook.model.JDBCUtilities;

/**
 *
 * @author mike
 */
public class ItemTypesManager {
	/**
	     * Returns all available item types. This is a combination of the users item types and the administrator's item types.
	     * Administrator items act as "default" or "standard" items for all other users.
	     * user_id = 1 is always the admin user.
	 */
	public ArrayList<ItemType> getAllItemTypes(int userId) throws Exception{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		ArrayList<ItemType> results = new ArrayList<ItemType>();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT * FROM item_types WHERE user_id=1 OR user_id="+userId;
			System.out.println("Query: " + query);
			rs = stmt.executeQuery(query);
			while(rs.next()){
				results.add(new ItemType(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name"), rs.getInt("base_cat_id")));
			}
			con.close();
			return results;
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}
	}

	public void addItemType(String name, int userId, int categoryId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		ArrayList<ArrayList<String>> outterList = new ArrayList();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "INSERT INTO item_types(name, user_id, base_cat_id) VALUES(\"" + name + "\", "+ userId + ", " + categoryId + ")";
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}
	}

	public void deleteItem(int itemId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "DELETE FROM items WHERE id = " + itemId;
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}
	}

	public String getItemName(int itemId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		String name;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT name FROM items WHERE id = " + itemId;
			rs = stmt.executeQuery(query);
			rs.next();
			name = rs.getString("name");
			con.close();
			return name;
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}

	}

	public int getItemCategory(int itemId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		int catId;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT category_id FROM items WHERE id = " + itemId;
			rs = stmt.executeQuery(query);
			rs.next();
			catId = rs.getInt("category_id");
			con.close();
			return catId;
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}

	}

	public void editItem(int itemId, String newName, int newCatId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "UPDATE items SET name = \"" + newName +"\", category_id = " + newCatId + " WHERE id = " + itemId;
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}

	}
}
