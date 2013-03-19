package net.grocerypricebook.model;

public class Category{
    private int catId;
    private int userId;
    private String name;
    private CategoryType type;

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(int catId) {
		this.catId = catId;
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
	 * @return the type
	 */
	public CategoryType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(CategoryType type) {
		this.type = type;
	}

	public enum CategoryType { 
		BASIC("basic"),
		OTHER("other"); 

		private CategoryType(final String text){
			this.text = text;
		}

		private final String text;

		@Override
		public String toString(){
			return text;
		}
	}

    public Category(int catId, int userId, String name){
        this.catId = catId; 
        this.userId = userId;
        this.name = name;
    }

    public Category(int catId, int userId, String name, CategoryType type){
        this.catId = catId;
        this.userId = userId;
        this.name = name;
	this.type = type;
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
