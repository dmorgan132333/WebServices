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
import net.grocerypricebook.model.Category;
import net.grocerypricebook.model.Item;
import net.grocerypricebook.model.JDBCUtilities;
import net.grocerypricebook.model.exceptions.CategoryNotFoundException;
import net.grocerypricebook.model.exceptions.ItemTypeNotFoundException;

/**
 *
 * @author mike
 */
public class ItemManager {
	
	/**
	 * Returns all items available to user with userId. This is a
	 * combination of the items the user has create and that the
	 * administrator has created.
	 * @param userId
	 * @return
	 */
	public ArrayList<Item> getAllItems(int userId) throws SQLException, ItemTypeNotFoundException, CategoryNotFoundException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT id FROM items WHERE user_id = 1 OR user_id ="+userId;
			rs = stmt.executeQuery(query);
			ArrayList<Item> items = new ArrayList<Item>();
			while(rs.next()){
				items.add(getItem(rs.getInt("id")));
			}
			con.close();
			return items;
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
	
	/**
	 * Returns an Item corresponding to itemId.
	 * @param itemId
	 * @return
	 */
	public Item getItem(int itemId) throws SQLException, ItemTypeNotFoundException, CategoryNotFoundException{
		System.out.println("Called getItem(" + itemId + ")");
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		ItemTypesManager typesManager = new ItemTypesManager();
		ItemSubTypesManager subtypesManager = new ItemSubTypesManager();
		CategoriesManager categoryManager = new CategoriesManager();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT user_id, item_type_id, item_subtype_id FROM items WHERE id="+itemId;
			rs = stmt.executeQuery(query);
			rs.next();
			Item item = new Item();
			item.setId(itemId);
			item.setUserId(rs.getInt("user_id"));
			item.setType(typesManager.getItemType(rs.getInt("item_type_id")));
			
			if(rs.getInt("item_subtype_id") != 0){
				item.setSubtype(subtypesManager.getSubType(rs.getInt("item_subtype_id")));
			} else {
				item.setSubtype(null);
			}
			
			ArrayList<Integer> categories = getItemCategories(itemId);
			for(Integer catId: categories){
				Category cat = categoryManager.getCategory(catId);
				item.addOtherCategory(cat);
			}
			
			con.close();
			return item;
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		} catch (ItemTypeNotFoundException e){
			System.out.println(e);
			throw e;
		}
	}
	
	/**
	 * Returns all the category entries from the item_categories table
	 * which have corresponding item_id.
	 * @param itemId
	 * @return
	 */
	public ArrayList<Integer> getItemCategories(int itemId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT category_id FROM item_categories WHERE item_id ="+itemId;
			rs = stmt.executeQuery(query);
			ArrayList<Integer> categories = new ArrayList<Integer>();
			while(rs.next()){
				categories.add(rs.getInt("category_id"));
			}
			con.close();
			return categories;
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
	
	/**
	 * Adds an item to the item table. Also inserts the base_type's category
	 * into item_categories along with items in additionalCategories.
	 * @param userId
	 * @param itemTypeId
	 * @param itemSubtypeId
	 * @param additionalCategories these are category IDs which are in addition to the item type's base category.
	 * These additional categories are essentially what makes an item unique.
	 * I do not think this uniqueness can be enforced by SQL tables but needs
	 * to be enforced programmatically. Perhaps a LEFT JOIN will do the trick
	 * of identifying unique items?
	 */
	public void addItem(int userId, int itemTypeId, int itemSubtypeId, ArrayList<Integer> additionalCategories) throws SQLException, ItemTypeNotFoundException{
		System.out.println("addItem: " + userId + " " + itemTypeId + " " + itemSubtypeId + " " + additionalCategories);
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		ItemTypesManager typesManager = new ItemTypesManager();
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			//items table query
			
			Integer subtypeId;
			if(itemSubtypeId == 0)
				subtypeId = null;
			else
				subtypeId = itemSubtypeId;
			
			query = "INSERT INTO items(item_type_id, item_subtype_id, user_id) VALUES("+itemTypeId+", "+subtypeId+", "+userId+")";
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			rs.next();
			int key = rs.getInt(1);
			
			//Add the base type's category to item_categories.
			int baseCategoryId = typesManager.getBaseCategory(itemTypeId);
			query = "INSERT INTO item_categories(item_id, category_id) VALUES("+key+", "+baseCategoryId+")";
			stmt.executeUpdate(query);
			
			//additional categories insertion
			if(additionalCategories != null){
				query = "INSERT INTO item_categories(item_id, category_id) ";
				for(int i = 0; i < additionalCategories.size(); i++){
					if(i == 0){
						query += "VALUES ("+key+", "+additionalCategories.get(i)+")";
					} else {
						query += ", ("+key+", "+additionalCategories.get(i);
					}
				}
				stmt.executeUpdate(query);
			}
			
			con.close();
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
	
	/**
	 * Delete the item id from items. This delete
	 * will cascade into item_categories based on item's id.
	 * @param itemId
	 * @param userId
	 */
	public void deleteItem(int itemId, int userId){
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			
			//Delete from items table only if userId and itemId match.
			query = "DELETE FROM items WHERE id="+itemId+" AND user_id = "+userId;
			stmt.executeUpdate(query);
			
			con.close();
		} catch (SQLException e){
			
		}
	}
	
	/**
	 * Returns all Items which have the given category.
	 * @param userId
	 * @param categoryId
	 * @return
	 */
	public ArrayList<Item> getItemsInCategory(int userId, int categoryId) throws SQLException, ItemTypeNotFoundException, CategoryNotFoundException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT item_id FROM item_categories "+
				"INNER JOIN items ON (items.user_id = "+userId+" OR items.user_id = 1) "+ 
				"AND item_categories.category_id = "+categoryId+" "+
				"AND items.id = item_categories.item_id";
			rs = stmt.executeQuery(query);
			ArrayList<Item> items = new ArrayList<Item>();
			while(rs.next()){
				items.add(getItem(rs.getInt("item_id")));
			}
			con.close();
			return items;
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
}
