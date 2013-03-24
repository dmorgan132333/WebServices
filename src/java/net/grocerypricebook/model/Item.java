package net.grocerypricebook.model;

import java.util.ArrayList;

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
	public int getCategoryComboCd() {
		return categoryComboId;
	}

	/**
	 * @param category_combo_id the category_combo_id to set
	 */
	public void setCategoryComboId(int categoryComboId) {
		this.categoryComboId = categoryComboId;
	}

}
