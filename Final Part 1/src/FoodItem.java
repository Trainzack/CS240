/**
 * Represents a single, actual ingredient that exists. It has an expiry date and everything!
 * @author eli
 *
 */
public class FoodItem implements Comparable<FoodItem> {

	public int getExpiryDate() {
		return expiryDate;
	}

	// The day that this item will expire.
	private int expiryDate;
	
	// What type of food item this
	private FoodType type;

	/**
	 * Creates a new food item of the given type, and sets the expiration date accordingly
	 * @param curDate The current day, as an int.
	 * @param type What type of food item this is.
	 */
	public FoodItem(int curDate, FoodType type) {
		super();
		this.type = type;
		this.expiryDate = curDate + type.getExpirationTime();
	}

	/**
	 * Checks whether this food item is expired.
	 * @param curDate The day that it currently is, as an int
	 * @return True if expired, false if still good.
	 */
	public boolean isExpired(int curDate) {
		return curDate >= expiryDate;
	}

	public FoodType getType() {
		return type;
	}

	@Override
	public int compareTo(FoodItem arg0) {
		return expiryDate - arg0.expiryDate;
	}
	
}
