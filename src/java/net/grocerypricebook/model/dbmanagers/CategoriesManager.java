/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model.dbmanagers;

import java.sql.*;
import java.util.ArrayList;
import net.grocerypricebook.model.Category;
import net.grocerypricebook.model.Category.CategoryType;
import net.grocerypricebook.model.Item;
import net.grocerypricebook.model.JDBCUtilities;

import net.grocerypricebook.model.exceptions.CategoryNotFoundException;

/**
 * This class has methods that manage three different database tables.
 * 1. categories 2. category_combos 3. category_combo_entries
 * @author mike
 */
public class CategoriesManager {
	
	public CategoriesManager(){
	}
	
	/**
	 * Get all available categories. This is a combination of the "admin" categories and the
	 * user's custom categories.
	 * @param userId the user's id
	 */
	public ArrayList<Category> getCategories(int userId) throws Exception{
		Connection con;
		ArrayList<Category> categories = new ArrayList<Category>();
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT id,user_id,name FROM categories WHERE user_id = 1 OR user_id ="+userId+" ORDER BY name";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				categories.add(new Category(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name")));
			}
			con.close();
			return categories;
			
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
	
	public ArrayList<Category> getBasicCategories(int userId) throws Exception{
		Connection con;
		ArrayList<Category> categories = new ArrayList<Category>();
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT id,user_id,name FROM categories WHERE type = \"basic\" AND (user_id = 1 OR user_id ="+userId+") ORDER BY name";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				categories.add(new Category(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name"), CategoryType.BASIC));
			}
			con.close();
			return categories;
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
	
	public ArrayList<Category> getOtherCategories(int userId) throws Exception{
		Connection con;
		ArrayList<Category> categories = new ArrayList<Category>();
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT id,user_id,name FROM categories WHERE type = \"other\" AND (user_id = 1 OR user_id ="+userId+") ORDER BY name";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				categories.add(new Category(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name"), CategoryType.OTHER));
			}
			con.close();
			return categories;
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
	
	public Category getCategory(int catId) throws SQLException, CategoryNotFoundException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT user_id,name FROM categories WHERE id ="+catId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				Category cat = new Category(catId, rs.getInt("user_id"), rs.getString("name"));
				con.close();
				return cat;
			}
			else{
				con.close();
				throw new CategoryNotFoundException("Category with id " + catId + " was not found.");
			}
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
	
	public String getCategoryName(int id) throws SQLException, CategoryNotFoundException{
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		String name;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT name FROM categories WHERE id = " + id;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				name = rs.getString("name");
				con.close();
				return name;
			} else
				throw new CategoryNotFoundException("Category with id: " + id + " not found.");
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		}
	}
	
	public void addBasicCategory(String name, int userId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		
		con = JDBCUtilities.getConnection();
		stmt = con.createStatement();
		query = "INSERT INTO categories (name,user_id,type) VALUES(\"" + name + "\", "+userId+", \"basic\")";
		stmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		int id = rs.getInt(1);
		con.close();
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(id);
		addCategoryCombo(list);
		
	}
	
	public void addOtherCategory(String name, int userId) throws SQLException{
		Connection con;
		Statement stmt;
		String query;
		con = JDBCUtilities.getConnection();
		stmt = con.createStatement();
		query = "INSERT INTO categories (name,user_id,type) VALUES(\"" + name + "\", "+userId+", \"other\")";
		stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		int id = rs.getInt(1);
		con.close();
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(id);
		addCategoryCombo(list);
	}
	
	
	public void editCategory(int id, String newName){
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "UPDATE categories SET name = \""+newName+"\" WHERE id = "+id;
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
		}
	}
	
	/**
	 * This deletes from categories and category_combo_entries through delete cascade.
	 * This can leave category_combos who's num_categories does not equal the number of category_combo_entries for that combo_id.
	 * @param id 
	 */
	public void deleteCategory(int id){
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "DELETE FROM categories WHERE id = "+id;
			stmt.executeUpdate(query);

			//Select the combo ID for the combo which has only one entry, the category_id
			/*query =	"SELECT DISTINCT c.id FROM (category_combos c, category_combo_entries e) "+ 
				"INNER JOIN category_combo_entries ON c.id = e.combo_id "+ 
				"WHERE c.num_categories = 1 AND e.category_id = "+id;
			
			System.out.println("QUERY: " + query);

			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			int comboId = rs.getInt("id");
			//Delete the combo_id from both category_combos and category_combo_entries
			//Achieved through foreign key cascade on delete.
			query = "DELETE FROM category_combos WHERE id = "+comboId;
			stmt.executeUpdate(query);
			*/
			con.close();
		} catch (SQLException e){
			System.out.println(e);
		}
	}
	
	/**
	 * Given a list of categories IDs, return the corresponding category_combo_id
	 * or 0 if the combination does not exist.
	 * @param categories List of Integers representing category IDs
	 * @return int which is the category_combos.id unique to the provided combination of categories or 0 if that combination was not found.
	 */
	public int getCategoryComboId(ArrayList<Integer> categories) throws SQLException{
		Connection con = JDBCUtilities.getConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT DISTINCT c.id " +
			       "FROM category_combos c " +
			       "INNER JOIN (";
		for(int i = 0; i < categories.size(); i++){
			if(i == 0)
			       query += String.format("category_combo_entries e%d", i+1);
		        else
			       query += String.format(", category_combo_entries e%d", i+1);
		}

		query += ") ON ";

		for(int i = 0; i < categories.size(); i++){
			if(i == 0)
			       query += String.format("c.id = e%d.combo_id", i+1);
		        else
			       query += String.format(" AND c.id = e%d.combo_id", i+1);
		}

		query += " WHERE c.num_categories = " + categories.size();

		for(int i = 0; i < categories.size(); i++){
			       query += String.format(" AND e%d.category_id = %d", i+1, categories.get(i));
		}

		System.out.println("getCategoryComboId( QUERY: " + query);

		ResultSet rs = stmt.executeQuery(query);

		if(rs.next()){
			int result = rs.getInt(1);
			con.close();
			return result;
		} else {
			con.close();
			return 0;
		}
	}
	
	/**
	 * Create a new category_combo ID and add the corresponding categories
	 * to category_combo_entries. This does not check that an equal category
	 * combination does not already exist, so there is the potential to
	 * create duplicates if this method is not used properly.
	 *
	 * TODO: This should be a transaction so if any statement fails, they all fail.
	 */
	public int addCategoryCombo(ArrayList<Integer> categoryIds) throws SQLException {
		Connection con = JDBCUtilities.getConnection();
		Statement stmt = con.createStatement();
		String query = "INSERT INTO category_combos(num_categories) VALUES("+categoryIds.size()+")";
		stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		int combo_id = rs.getInt(1);
		query = "INSERT INTO category_combo_entries(combo_id, category_id) ";
		for(int i = 0; i < categoryIds.size(); i++){
			if(i==0){
				query += String.format("VALUE(%d,%d)", combo_id, categoryIds.get(i));
			}
			else{
				query += String.format(", (%d,%d)", combo_id, categoryIds.get(i));
			}
		}
		System.out.println("addCategoryCombo(...) QUERY: " + query);
		stmt.executeUpdate(query);
		con.close();
		return combo_id;
	}

	/*
	public ArrayList<Item> getItemsInCategory(int categoryId){
		Connection con = JDBCUtilities.getConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT  "
	}*/

}
