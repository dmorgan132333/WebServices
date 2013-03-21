/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

/**
 *
 * @author dmoney1323
 */
public class GroceryStore {
    private String name;
    private String state;
    private String city;
    private String address;
    private int zip;
    private int store_id;
    private int user_id;
    
    public GroceryStore(int store_id,String name,String state,String city,String address,int zip,int user_id){
        this.name =name;
        this.state = state;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.store_id = store_id;
        this.user_id = user_id;
    }
    public String getName(){
        return name;
    }
    public String getState(){
        return state;
    }
    public String getCity(){
        return city;
    }
    public String getAddress(){
        return address;
    }
    public int getZip(){
        return zip;
    }
    public int getStoreID(){
        return store_id;
    }
    public int getUserID(){
        return user_id;
    }
}
