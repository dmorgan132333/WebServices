package net.grocerypricebook.model;


import java.util.ArrayList;
/**
 *
 * @author Doug
 */
public class ShopList {
    private String name;
    private int id;
    private int user_id;
    
    public ShopList(int id, int user_id,String name){
       this.name = name;
       this.id = id;
       this.user_id = user_id;
       
    }
    public String getName(){
        return name;
    }
    public int getID(){
        return id;
    }
    public int getuser_id(){
        return user_id;
    }
}