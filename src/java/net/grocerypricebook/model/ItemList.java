/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

/**
 *
 * @author dmoney1323
 */
public class ItemList {
    private String name;
    private double price;
    private String listName;
    
    public ItemList(String listName,String name, double price){
        this.listName = listName;
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public String getListName(){
        return listName;
    }
    public double getPrice(){
        return price;
    }
}
