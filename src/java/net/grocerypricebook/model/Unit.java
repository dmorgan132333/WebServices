package net.grocerypricebook.model;

/**
 * This class represents an entry in the table "units"
 * @author mike
 */
public class Unit {
	private int id;
	private String name;
	private int baseUnitId;
	private float multiplier;

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
	 * @return the baseUnitId
	 */
	public int getBaseUnitId() {
		return baseUnitId;
	}

	/**
	 * @param baseUnitId the baseUnitId to set
	 */
	public void setBaseUnitId(int baseUnitId) {
		this.baseUnitId = baseUnitId;
	}

	/**
	 * @return the multiplier
	 */
	public float getMultiplier() {
		return multiplier;
	}

	/**
	 * @param multiplier the multiplier to set
	 */
	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}

}
