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
				Category cat = categoryManager .getCategory(catId);
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
}