/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

/**
 *
 * @author mike
 */
public class ItemSubType {
	private int id;
	private int parentTypeId; //The ID of the parent item_type
	private String name;
	private int userId; 

	public ItemSubType(int id, int parentTypeId, int userId, String name){
		this.id = id;
		this.parentTypeId = parentTypeId;
		this.userId = userId;
		this.name = name;
	}

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
	 * @return the parentTypeId
	 */
	public int getParentTypeId() {
		return parentTypeId;
	}

	/**
	 * @param parentTypeId the parentTypeId to set
	 */
	public void setParentTypeId(int parentTypeId) {
		this.parentTypeId = parentTypeId;
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

}
