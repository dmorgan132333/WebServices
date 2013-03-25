package net.grocerypricebook.model;

import java.sql.SQLException;
import java.util.ArrayList;
import net.grocerypricebook.model.dbmanagers.CategoriesManager;
import net.grocerypricebook.model.dbmanagers.ItemManager;
import net.grocerypricebook.model.exceptions.CategoryNotFoundException;
import net.grocerypricebook.model.exceptions.ItemNotFoundException;

/**
 * Represents an entry in the items table.
 * @author mike
 */
public class Item {
	private int id;
	private int userId;
	private int parentId;
	private String name;
	private int categoryComboId;
	private ArrayList<Integer> categories;

	private ItemManager itemMan = new ItemManager();
	private CategoriesManager catMan = new CategoriesManager();

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the categories
	 */
	public ArrayList<Integer> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(ArrayList<Integer> categories) {
		this.categories = categories;
	}

	/**
	 * @return the category_combo_id
	 */
	public int getCategoryComboId() {
		return categoryComboId;
	}

	/**
	 * @param category_combo_id the category_combo_id to set
	 */
	public void setCategoryComboId(int categoryComboId) {
		this.categoryComboId = categoryComboId;
	}

	public String getFullName() throws SQLException, ItemNotFoundException, CategoryNotFoundException{
		Item parent;
		if(parentId != 0){
			parent = itemMan.getItem(this.parentId);
		} else {
			parent = null;
		}
		ArrayList<Category> categories = catMan.getCategoriesInCombo(categoryComboId);
		String catString = "";

		for(int i = 0; i < categories.size(); i++){
			if(i == categories.size() - 1){
				catString += categories.get(i).getName();
			}
			else{
				catString += categories.get(i).getName() + ", ";
			}
		}

		if(parent == null){
			return name + ": " + catString;
		}
		else{
			return parent.getName() + ", " + name + ": " + catString;
		}
	}

}
