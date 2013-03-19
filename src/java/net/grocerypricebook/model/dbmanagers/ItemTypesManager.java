package net.grocerypricebook.model.dbmanagers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import net.grocerypricebook.model.ItemType;
import net.grocerypricebook.model.JDBCUtilities;
import net.grocerypricebook.model.exceptions.ItemTypeNotFoundException;

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
			query = "SELECT * FROM item_types WHERE (user_id=1 OR user_id="+userId+") ORDER BY name";
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

	public void deleteItemType(int itemId, int userId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "DELETE FROM item_types WHERE id = " + itemId + " AND user_id = " + userId;
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}
	}

	public String getItemTypeName(int itemTypeId) throws SQLException, ItemTypeNotFoundException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		String name;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT name FROM item_types WHERE id = " + itemTypeId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				name = rs.getString("name");
				con.close();
				return name;
			} else {
				throw new ItemTypeNotFoundException("ItemType with ID: " + itemTypeId + " was not found.");
			}
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}

	}

	/**
	 * Returns the base category ID of the item type.
	 * @param itemTypeId the ID of the item type
	 * @return the category ID representing the base category for the item type
	 * @throws SQLException 
	 */
	public int getBaseCategory(int itemTypeId) throws SQLException, ItemTypeNotFoundException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		int catId;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT base_cat_id FROM item_types WHERE id = " + itemTypeId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				catId = rs.getInt("base_cat_id");
				con.close();
				return catId;
			} else {
				throw new ItemTypeNotFoundException("ItemType with ID: " + itemTypeId + " was not found.");
			}
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}

	}

	/**
	 * Updates the item type where id = itemId, user_id = userId, with new name and new basic category id.
	 * @param itemId
	 * @param userId
	 * @param newName
	 * @param newCatId
	 * @throws SQLException 
	 */
	public void editItemType(int itemId, int userId, String newName, int newCatId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "UPDATE item_types SET name = \"" + newName +"\", base_cat_id = " + newCatId + " WHERE id = " + itemId + " AND user_id = " + userId;
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}
	}
}
