/**
 * Represents the type of a single food item. Each type has an associated name, and expiry time (in days)
 */


public enum FoodType {
	Bun ("Bun", 5),
	Patty ("Patty", 4),
	Lettuce ("Lettuce", 3),
	Tomato ("Tomato", 3),
	Onion ("Onion", 5),
	Cheese ("Cheese", 2)
	;
	private final int expirationTime;
	private final String name;

	private FoodType(String name, int expirationTime) {
		this.name = name;
		this.expirationTime = expirationTime;
	}
	
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Returns the amount of days that this type of food item lasts for
	 * @return An int represnting the number of days that this food item lasts for
	 */
	public int getExpirationTime() {
		return expirationTime;
	}
}
