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
    private int id;
    public ItemList(int id, String name){
        this.id = id;
        this.name = name;
    }
    public ItemList(String listName,String name, double price){
        this.listName = listName;
        this.name = name;
        this.price = price;
    }
    public int getId(){
        return id;
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
