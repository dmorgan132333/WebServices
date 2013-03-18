package net.grocerypricebook.model;

public class Category{
    int catId;
    int userId;
    String name;

    Category(int catId, int userId, String name){
        this.catId = catId; 
        this.userId = userId;
        this.name = name;
    }

    public int getCatId(){
        return catId; 
    }

    public int getUserId(){
        return userId; 
    }

    public String getName(){
        return name; 
    }
}
