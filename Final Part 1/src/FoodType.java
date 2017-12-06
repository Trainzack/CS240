/**
 * Represents the type of a single food item. Each type has an associated name, and expiry time (in days)
 */


public enum FoodType {
	Bun ("Bun", 5, "Buns"),
	Patty ("Patty", 4, "Patties"),
	Lettuce ("Lettuce", 3, "Lettuce"),
	Tomato ("Tomato", 3, "Tomatoes"),
	Onion ("Onion", 5, "Onions"),
	Cheese ("Cheese", 2, "Cheese Slices")
	;
	private final int expirationTime;
	private final String name;
	private final String plural;

	private FoodType(String name, int expirationTime, String plural) {
		this.name = name;
		this.expirationTime = expirationTime;
		this.plural = plural;
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

	public String getPlural() {
		return plural;
	}
}
