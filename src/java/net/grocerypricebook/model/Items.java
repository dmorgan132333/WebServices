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

	/*Returns all available items.
	 * The order of the returned rows is <item ID, item name, category ID>
	 */
	public ArrayList<ArrayList<String>> getItems() throws Exception{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		ArrayList<ArrayList<String>> results = new ArrayList();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT * FROM items";
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
