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
}
