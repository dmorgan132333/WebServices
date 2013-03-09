/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Doug
 */



public class Users {
    HashMap<String,String> userID = new HashMap<String,String>();
    ArrayList<String> sList;
    HashMap<String,ArrayList<String>> sMap = new HashMap<String,ArrayList<String>>();
    
    public Users(){
        addUsers();
    }
    
    public void addUsers(){
        userID.put("Doug","Morgan");
        sList = new ArrayList<String>();
        sList.add("Milk");
        sList.add("Eggs");
        sList.add("Water");
        sMap.put("Doug", sList);
        
        userID.put("Mike","Hayes");
        sList = new ArrayList<String>();
        sList.add("Tofu");
        sList.add("Eggs");
        sList.add("Water");
        sMap.put("Mike", sList);
    }
    public void addToShopList(String user, String item){
        ArrayList<String> s = sMap.get(user);
        s.add(item);
       System.out.println(item + ", " + user + ", " +s);
        sMap.put(user, s);
    }

    public HashMap getUsersID() {
       // System.out.println(userID.entrySet());
        return userID;
    }
    public HashMap getPassword(){
        return userID;
    }
    public ArrayList<String> getShopList(String user){
        
        ArrayList<String> s =sMap.get(user);
        System.out.println(s);
        return s;
    }
}
