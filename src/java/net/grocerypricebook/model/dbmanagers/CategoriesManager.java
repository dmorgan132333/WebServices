/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model.dbmanagers;

import java.sql.*;
import java.util.ArrayList;
import net.grocerypricebook.model.Category;
import net.grocerypricebook.model.Category.CategoryType;
import net.grocerypricebook.model.JDBCUtilities;

import net.grocerypricebook.model.exceptions.CategoryNotFoundException;

/**
 *
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

	public void addCategory(String name, int userId){
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "INSERT INTO categories (name,user_id) VALUES(\"" + name + "\", "+userId+")";
			stmt.executeUpdate(query);
			con.close();

		} catch (SQLException e){
			System.out.println(e);
		} 
	}

	public void addBasicCategory(String name, int userId){
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "INSERT INTO categories (name,user_id,type) VALUES(\"" + name + "\", "+userId+", \"basic\")";
			stmt.executeUpdate(query);
			con.close();

		} catch (SQLException e){
			System.out.println(e);
		} 
	}
	public void addOtherCategory(String name, int userId){
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "INSERT INTO categories (name,user_id,type) VALUES(\"" + name + "\", "+userId+", \"other\")";
			stmt.executeUpdate(query);
			con.close();

		} catch (SQLException e){
			System.out.println(e);
		} 
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

	public void deleteCategory(int id){
		Connection con;
		Statement stmt;
		String query;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "DELETE FROM categories WHERE id = "+id;
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
		}	
	}

}
