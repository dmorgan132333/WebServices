/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Mike Hayes
 */
public class Users{
    public Users(){

    }

    /**
     * Adds a username and password combination to the user database.
     * @author Mike Hayes
     * @param name The username to add to the database.
     * @param password The password to associate with the username.
     */
    public void addUser(String name, String password) throws SQLException{
        Connection con;
        Statement stmt;
        String query;
        try{
            con = JDBCUtilities.getConnection();
            stmt = con.createStatement(); 
            query = "INSERT INTO users(login, password) VALUES(\"" + name + "\", \""+password+"\")";
            stmt.executeUpdate(query);
            con.close();
        } catch (SQLException e){
            System.out.println(e); 
            throw e;
        }
    }

    /**
     * Returns true if the username and password match. Returns false if the username was not found or if they did not match.
     * @author Mike Hayes
     * @param userName the string of the user's login name
     * @param password the string of the user's password
     * @return true if match, false if not matched or if no user with userName was found
     */
    public boolean passwordMatch(String userName, String password) throws SQLException{
        Connection con;
        Statement stmt;
        String query;
        ResultSet rs;
        try{
            con = JDBCUtilities.getConnection();
            stmt = con.createStatement(); 
            query = "SELECT password FROM users WHERE login = \""+ userName +"\"";
            rs = stmt.executeQuery(query);
            //If there are no results (no user with userName found), rs.next() will return false.
            if(rs.next()){
                String pw = rs.getString("password");
                con.close();
                return password.equals(pw);
            } else{
                con.close();
                return false;
            }

        } catch (SQLException e){
            System.out.println(e); 
            throw e;
        }
    }

    public int getUserId(String userName) throws SQLException{
        Connection con;
        Statement stmt;
        String query;
        ResultSet rs;
        try{
            con = JDBCUtilities.getConnection();
            stmt = con.createStatement(); 
            query = "SELECT id FROM users WHERE login = \""+ userName +"\"";
            rs = stmt.executeQuery(query);
            //If there are no results (no user with userName found), rs.next() will return false.
            rs.next();
            int id = rs.getInt("id");
            con.close();
            return id;
        } catch (SQLException e){
            System.out.println(e); 
            throw e;
        }
    }
}
