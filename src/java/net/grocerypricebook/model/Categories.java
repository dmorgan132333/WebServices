/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mike
 */
public class Categories {

	public Categories(){
	}

	public Map<Integer, String> getCategories() throws Exception{
		Map<Integer, String> categories = new HashMap<Integer, String>();
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "SELECT name,id FROM categories";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				int id = rs.getInt("id");
				categories.put(id, name);
			}
			con.close();
			return categories;

		} catch (SQLException e){
			System.out.println(e);
			throw e;
		} 
	}

	public String getCategoryName(int id) throws Exception{
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
			rs.next();
			name = rs.getString("name");
			con.close();
			return name;
		} catch (SQLException e){
			System.out.println(e);
			throw e;
		} 
	}

	public void addCategory(String name){
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
	}

	public void editCategory(int id, String newName){
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "UPDATE categories SET name = \""+newName+"\" WHERE id = "+id;
			rs = stmt.executeQuery(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
		}	
	}

	public void deleteCategory(int id){
		Connection con;
		Statement stmt;
		String query;
		ResultSet rs;
		try{
			con = JDBCUtilities.getConnection();
			stmt = con.createStatement();
			query = "DELETE FROM categories WHERE id = "+id;
			rs = stmt.executeQuery(query);
			con.close();
		} catch (SQLException e){
			System.out.println(e);
		}	
	}

}