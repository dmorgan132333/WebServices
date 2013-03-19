package net.grocerypricebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Doug
 */
public class ShopList {
    
    ArrayList<String> slist = new ArrayList<String>();
    double price;
    
    public ShopList(){
       
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
            query = "DELETE FROM shopping_lists WHERE name=\"" +name + "\""; // + name;
            stmt.executeUpdate(query);
           
            con.close();
        } catch (SQLException e){
            System.out.println(e); 
            throw e;
        }
    }
    public ArrayList<String> getList(int userID)throws SQLException{
        Connection con;
        Statement stmt;
        String query;
        ResultSet rs;
        try{
            con = JDBCUtilities.getConnection();
            stmt = con.createStatement(); 
//            query = "INSERT INTO users(login, password) VALUES(\"" + name + "\", \""+password+"\")";
//            query = "INSERT INTO shopping_lists VALUES(\"" + userID + "\")";
            query = "SELECT id FROM shopping_lists WHERE user_Id = \"" +userID + "\"";
            rs = stmt.executeQuery(query);
            System.out.println(rs);
            con.close();
        } catch (SQLException e){
            System.out.println(e); 
            throw e;
        }
        return slist;
    }
}