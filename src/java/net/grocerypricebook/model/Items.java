/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mike
 */
public class Items {
	/* TODO: Implement this method.
	public Map<Integer, String> getItemsInCategory(int categoryId){
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "INSERT INTO categories (name) VALUES(\"" + name + "\")";
			rs = stmt.executeQuery(query);
			con.close();

		} catch (SQLException e){
			System.out.println(e);
		} 
	}*/

	/**
     * Returns all available items. This is a combination of the users items and the administrator's items.
     * Administrator items act as "default" or "standard" items for all other users.
     * user_id = 1 is always the admin user.
     * The first column returned is the item_id.
     * Any remaining columns are the category_ids of the item.
	 */
	public ArrayList<ArrayList<String>> getAllItems(int userId) throws Exception{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		ArrayList<ArrayList<String>> results = new ArrayList();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT * FROM items WHERE user_id=0 OR user_id="+userId;
			rs = stmt.executeQuery(query);
			while(rs.next()){
				ArrayList<String> row = new ArrayList();
				row.add(rs.getString("id"));
				row.add(rs.getString("name"));
				row.add(rs.getString("category_id"));
				results.add(row);
			}
			con.close();
			return results;
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}
	}

	public void addItem(String name, int categoryId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		ArrayList<ArrayList<String>> outterList = new ArrayList();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "INSERT INTO items(name, category_id) VALUES(\"" + name + "\", " + categoryId + ")";
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
