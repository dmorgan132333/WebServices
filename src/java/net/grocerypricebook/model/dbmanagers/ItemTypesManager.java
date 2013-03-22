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

	/**
	 * Creates the item_type and also adds the basic item to items.
	 * @param name
	 * @param userId
	 * @param categoryId
	 * @throws SQLException
	 * @throws ItemTypeNotFoundException 
	 */
	public void addItemType(String name, int userId, int categoryId) throws SQLException, ItemTypeNotFoundException{
		Connection con;
		Statement stmt;
		String query, query2;
		ResultSet rs;
		ItemManager itemManager = new ItemManager();
		ArrayList<ArrayList<String>> outterList = new ArrayList();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "INSERT INTO item_types(name, user_id, base_cat_id) VALUES(\"" + name + "\", "+ userId + ", " + categoryId + ")";
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			rs.next();
			int generatedId = rs.getInt(1);
			itemManager.addItem(userId, generatedId, 0, null);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}
	}

	/**
	 * CAUTION: If you delete an admin item_type, it will also delete all
	 * items based on that item type for ALL USERS without them knowing.
	 * This seems catastrophic, perhaps there is a better way to handle it...
	 * @param itemTypeId
	 * @param userId
	 * @throws SQLException 
	 */
	public void deleteItemType(int itemTypeId, int userId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		ItemManager itemManager = new ItemManager();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT id FROM items WHERE item_type_id = "+itemTypeId+" AND user_id ="+userId;
			rs = stmt.executeQuery(query);
			while(rs.next()){
				itemManager.deleteItem(rs.getInt("id"), userId);	
			}

			query = "DELETE FROM item_types WHERE id = " + itemTypeId + " AND user_id = " + userId;
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

	public ItemType getItemType(int typeId) throws ItemTypeNotFoundException, SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT id, name, user_id, base_cat_id FROM item_types WHERE id = "+typeId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				ItemType itemType = new ItemType();
				itemType.setId(typeId);
				itemType.setName(rs.getString("name"));
				itemType.setUserId(rs.getInt("user_id"));
				itemType.setBaseCategoryId(rs.getInt("base_cat_id"));
				con.close();
				return itemType;
			} else {
				throw new ItemTypeNotFoundException("Could not find item type with id: " + typeId);
			}
		} catch (SQLException e){
			System.out.println(e);
			throw e;	
		}
	}
}
