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
import net.grocerypricebook.model.exceptions.ItemNotFoundException;

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
	public ArrayList<Item> getAllItems(int userId) throws SQLException, ItemNotFoundException, CategoryNotFoundException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
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
	}
	
	/**
	 * Returns an Item corresponding to itemId.
	 * @param itemId
	 * @return
	 */
	public Item getItem(int itemId) throws SQLException, ItemNotFoundException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		con = JDBCUtilities.getConnection();
		stmt = con.createStatement();
		query = "SELECT id, name, parent_id, category_combo_id, user_id FROM items WHERE id = " + itemId;
		rs = stmt.executeQuery(query);
		Item item;
		if(rs.next()){
			item = new Item();
			item.setId(itemId);
			item.setName(rs.getString("name"));
			item.setParentId(rs.getInt("parent_id"));
			item.setCategoryComboId(rs.getInt("category_combo_id"));
			item.setUserId(rs.getInt("user_id"));
			con.close();
			return item;
		} else {
			con.close();
			throw new ItemNotFoundException("Item with id: " + itemId + " was not found in the database.");
		}
	}
	
	public void addItem(int userId, int parentId, String name, ArrayList<Integer> additionalCategories) throws SQLException, ItemNotFoundException{
		CategoriesManager catMan = new CategoriesManager();
		Connection con = JDBCUtilities.getConnection();
		Statement stmt = con.createStatement();
		int catComboId = catMan.getCategoryComboId(additionalCategories);
		if(catComboId == 0){
			catComboId = catMan.addCategoryCombo(additionalCategories);
		}
		String query;
		if(parentId == 0){
			query = "INSERT INTO items(name, category_combo_id, user_id) VALUES(\"%s\", %d, %d)";
			query = String.format(query, name, catComboId, userId);
		} else {
			query = "INSERT INTO items(name, parent_id, category_combo_id, user_id) VALUES(\"%s\", %d, %d, %d)";
			query = String.format(query, name, parentId, catComboId, userId);
		}
		stmt.executeUpdate(query);
		con.close();
	}
	
	/**
	 * Delete the item id from items. This delete
	 * will cascade into item_categories based on item's id.
	 * @param itemId
	 * @param userId
	 */
	public void deleteItem(int itemId, int userId){
	}
	
}
