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
import net.grocerypricebook.model.ItemList;
import net.grocerypricebook.model.JDBCUtilities;
import net.grocerypricebook.model.ShopList;

/**
 *
 * @author dmoney1323
 */
public class ShoppingListManager {
     
     public ShoppingListManager(){
         
     }
    public void addToList(int userID, String name){
        
    }
    public void addNewList(int userID, String name)throws SQLException{
        Connection con;
        Statement stmt;
        String query;
        try{
            con = JDBCUtilities.getConnection();
            stmt = con.createStatement(); 
//            query = "INSERT INTO users(login, password) VALUES(\"" + name + "\", \""+password+"\")";
            query = "INSERT INTO shopping_lists(user_id, name) VALUES(\"" + userID + "\", \"" + name + "\")";
           
            stmt.executeUpdate(query);
            con.close();
        } catch (SQLException e){
            System.out.println(e); 
            throw e;
        }
    }
    public void deleteList(int userID, String name)throws SQLException{
        Connection con;
        Statement stmt;
        String query;
        try{
            con = JDBCUtilities.getConnection();
            stmt = con.createStatement(); 
//            query = "INSERT INTO users(login, password) VALUES(\"" + name + "\", \""+password+"\")";
            query = "DELETE FROM shopping_lists WHERE name=\"" +name + "\" AND user_id = \"" + userID + "\""; // + name;
            stmt.executeUpdate(query);
            con.close();
        } catch (SQLException e){
            System.out.println(e); 
            throw e;
        }
    }
    public ArrayList<ShopList> getShopList(int userID)throws SQLException{
        Connection con;
        Statement stmt;
        String query;
        ResultSet rs;
        ArrayList<ShopList> shopList = new ArrayList<ShopList>();
        
        try{
            con = JDBCUtilities.getConnection();
            stmt = con.createStatement(); 
//            query = "INSERT INTO users(login, password) VALUES(\"" + name + "\", \""+password+"\")";
//            query = "INSERT INTO shopping_lists VALUES(\"" + userID + "\")";
            query = "SELECT id,user_id,name FROM shopping_lists WHERE user_Id = \"" +userID + "\"";
            
            rs = stmt.executeQuery(query);
            while(rs.next()){
                shopList.add(new ShopList(rs.getInt("id"),rs.getInt("user_id"),rs.getString("name")));
            }
            con.close();
        } catch (SQLException e){
            System.out.println(e); 
            throw e;
        }
        return shopList;
    }
    public ArrayList<ItemList> getItems(String name){
        ArrayList<ItemList> itemL = new ArrayList<ItemList>();
        
        itemL.add(new ItemList("monday","milk",3.50));
        return itemL;
    }
}
