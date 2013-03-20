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
import net.grocerypricebook.model.ItemSubType;
import net.grocerypricebook.model.JDBCUtilities;
import net.grocerypricebook.model.exceptions.ItemTypeNotFoundException;

/**
 *
 * @author mike
 */
public class ItemSubTypesManager {
	/**
	 * Returns all the item subtypes for the given parent type ID.
	 * This is a combination of "standard" subtypes (where user_id = 1)
	 * and the user's subtypes.
	 * @param parentTypeId
	 * @return 
	 */
	public ArrayList<ItemSubType> getItemSubTypes(int parentTypeId, int userId) throws SQLException{
		Connection con;
		ArrayList<ItemSubType> subTypes = new ArrayList<ItemSubType>();
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT id,name,user_id FROM item_subtypes WHERE (user_id = 1 OR user_id ="+userId+") AND item_type_id = "+ parentTypeId + " ORDER BY name";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				subTypes.add(new ItemSubType(rs.getInt("id"), parentTypeId, rs.getInt("user_id"), rs.getString("name")));
			}
			con.close();
			return subTypes;

		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}

	/**
	 * Create new entry in the item_subtypes table and items table
	 * @param parentTypeId
	 * @param userId
	 * @param name
	 * @throws SQLException 
	 */
	public void addSubtype(int parentTypeId, int userId, String name) throws SQLException{
		Connection con;
		Statement stmt;
		String query, query2;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "INSERT INTO item_subtypes(item_type_id, user_id, name) VALUES("+parentTypeId+", "+userId+", \""+name+"\")";
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			rs.next();
			int generatedId = rs.getInt(1);
			query2 = "INSERT INTO items(item_type_id, item_subtype_id, user_id) VALUES("+parentTypeId+","+generatedId+","+userId+")";
			stmt.executeUpdate(query2);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}

	/**
	 * Delete entry from item_subtypes table and items table.
	 * @param subtypeId
	 * @param userId
	 * @throws SQLException 
	 */
	public void deleteSubtype(int subtypeId, int userId) throws SQLException{
		Connection con;
		Statement stmt;
		String query, query2;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "DELETE from items WHERE (item_subtype_id = "+subtypeId+" AND user_id="+userId+")";
			stmt.executeUpdate(query);
			query2 = "DELETE FROM item_subtypes WHERE (id = "+subtypeId+" AND user_id = "+userId+")";
			stmt.executeUpdate(query2);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}

	}

	public String getSubtypeName(int subtypeId) throws ItemTypeNotFoundException, SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT name FROM item_subtypes WHERE id = "+subtypeId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				String name = rs.getString("name");
				con.close();
				return name;
			} else {
				throw new ItemTypeNotFoundException("Could not find item subtype with id: " + subtypeId);
			}
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}

	/**
	 * Returns the parent type ID for the given subtype ID.
	 * @param subtypeId
	 * @return 
	 */
	public int getParentId(int subtypeId) throws ItemTypeNotFoundException, SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT item_type_id FROM item_subtypes WHERE id = "+subtypeId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				int parentId = rs.getInt("item_type_id");
				con.close();
				return parentId;
			} else {
				throw new ItemTypeNotFoundException("Could not find item subtype with id: " + subtypeId);
			}
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}

	public void renameSubtype(int subtypeId, int userId, String newName) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "UPDATE item_subtypes SET name = \"" + newName +"\" WHERE ( id = "+subtypeId+" AND user_id = " + userId +")";
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}

	}

	public ItemSubType getSubType(int subtypeId) throws ItemTypeNotFoundException, SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT item_type_id,name,user_id FROM item_subtypes WHERE id = "+subtypeId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				int parentId = rs.getInt("item_type_id");
				String name = rs.getString("name");
				int userId = rs.getInt("user_id");	
				ItemSubType subtype = new ItemSubType(subtypeId, parentId, userId, name);
				con.close();
				return subtype;
			} else {
				throw new ItemTypeNotFoundException("Could not find item subtype with id: " + subtypeId);
			}
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
}
