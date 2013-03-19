/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

/**
 *
 * @author mike
 */
public class ItemType {
	private int id;
	private int userId;
	private String name;
	private int baseCategoryId;


	public ItemType(int id, int userId, String name, int baseCategoryId){
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.baseCategoryId = baseCategoryId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the baseCategoryId
	 */
	public int getBaseCategoryId() {
		return baseCategoryId;
	}

	/**
	 * @param baseCategoryId the baseCategoryId to set
	 */
	public void setBaseCategoryId(int baseCategoryId) {
		this.baseCategoryId = baseCategoryId;
	}

	
}
