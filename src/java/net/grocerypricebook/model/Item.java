/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

import java.util.ArrayList;

/**
 *
 * @author mike
 */
public class Item {
	private int id;
	private int userId;
	private ItemType type;
	private ItemSubType subtype;
	private ArrayList<Category> otherCategories;

	public Item() {}

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
	 * @return the typeId
	 */
	public int getTypeId() {
		return type.getId();
	}

	/**
	 * @return the subtypeId
	 */
	public int getSubtypeId() {
		return subtype.getId();
	}

	/**
	 * @return the name of the Item, a combination of the type name and subtype name or just type name if there is no subtype
	 */
	public String getName() {
		if(subtype != null){
			return subtype.getName() + " " + type.getName();
		} else {
			return type.getName();
		}
	}


	/**
	 * @return the baseCategory
	 */
	public Category getBaseCategory() {
		return type.getBaseCategory();
	}

	/**
	 * @return the otherCategories
	 */
	public ArrayList<Category> getOtherCategories() {
		return otherCategories;
	}

	public void addOtherCategory(Category other){
		otherCategories.add(other);
	}

	public ArrayList<Category> getAllCategories(){
		ArrayList<Category> all = (ArrayList<Category>) otherCategories.clone();
		all.add(type.getBaseCategory());
		return all;
	}

	/**
	 * @return the type
	 */
	public ItemType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ItemType type) {
		this.type = type;
	}

	/**
	 * @return the subtype
	 */
	public ItemSubType getSubtype() {
		return subtype;
	}

	/**
	 * @param subtype the subtype to set
	 */
	public void setSubtype(ItemSubType subtype) {
		this.subtype = subtype;
	}
}
