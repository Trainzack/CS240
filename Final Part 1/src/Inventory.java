import java.io.PrintStream;

/**
 * Simulates a simplified model of an In-N-Out.
 * @author eli
 *
 */

public class Inventory {

	// This is the PrintStream we will be writing to. This is to ease exporting the output to a file.
	private static final PrintStream output = System.out;
	
	// What day of the month it is. Starts at DEC 1st, or 301.
	private static int day = 301;
	
	// What is the last day to simulate (DEC 31st)
	private static final int last_date = 331; 
	
	// Represents how many days away the next shipment is.
	private static int daysUntilNextShipment = 0;
	
	// This array contains the six menu items that customers can order.
	
	@SuppressWarnings("unchecked") // This is okay because the new array is empty
	private static ListInterface<FoodType>[] menuItems = new ListInterface[6];
	
	public static void main(String[] args) {
		
		init();
		
		for (int i = 0; i < menuItems.length; i++) {
			output.println(i + ": " + menuItems[i].toString());
		}
		
		for (; day <= last_date; day++) {
			runDay();
		}
		
	}
	
	/**
	 * Initializes all of the important variables, like menu items
	 */
	private static void init() {
		
		// Add the correct food types to each menu item by creating arrays containing the food items first, then making the lists based off of those arrays.
		
		// (1) Burger – Bun, Patty, Lettuce, Tomato, Onion
		FoodType[] burger = {FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Tomato, FoodType.Onion};
		menuItems[0] = new FixedArrayList<>(burger);
		
		// (2) Cheese Burger – Cheese, Bun, Patty, Lettuce, Tomato, Onion
		FoodType[] cheeseBurger = {FoodType.Cheese, FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Tomato, FoodType.Onion};
		menuItems[1] = new FixedArrayList<>(cheeseBurger);
		
		// (3) Vegan lettuce wrap Burger – (NO Bun, No Patty),2 order of Lettuce, Tomato, Onion
		FoodType[] veganBurger = {FoodType.Lettuce, FoodType.Lettuce, FoodType.Tomato, FoodType.Onion};
		menuItems[2] = new FixedArrayList<>(veganBurger);
		
		// (4) Burger No Onion - (No Onion), Bun, Patty, Lettuce, Tomato,
		FoodType[] burgerNoOnion = {FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Tomato};
		menuItems[3] = new FixedArrayList<>(burgerNoOnion);
		
		// (5) Cheese Burger No Onion – (No Onion), Cheese, Bun, Patty, Lettuce, Tomato
		FoodType[] cheeseBurgerNoOnion = {FoodType.Cheese, FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Tomato};
		menuItems[4] = new FixedArrayList<>(cheeseBurgerNoOnion);
		
		// (6) Burger No Tomato - (No Tomato), Bun, Patty, Lettuce, Onion
		FoodType[] burgerNoTomato = {FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Onion};
		menuItems[5] = new FixedArrayList<>(burgerNoTomato);
		
	}
	
	/**
	 * Simulates a single day.
	 */
	private static void runDay() {
		
		output.println("December " + (day - 300) + " Log:");
		
		// Represents how many customers we lost today
		int lostCustomerDay = 0;
		
		// Represents how many of each menu item was ordered (i.e., the item in menuItems[0] will have its count in itemsOrdered[0])
		int[] itemsOrdered = {0, 0, 0, 0, 0, 0};
		
		// Represents how many of each type of food item has been wasted via spoiling.
		int[] foodWasted = {0, 0, 0, 0, 0, 0};
		
		// Represents which customer we're serving
		int customerNumber = 1;
		
		// Represents customer orders. (Key is customer number, value is menu item ordered)
		DictionaryInterface<Integer, Integer> orders = new SortedDictionaryLinkedData<>();
		
		// Maybe receive shipment		
		// 	Put all food items into temp stack
		//	Generate new food, put at bottom of original stack
		//	Return all food items
		
		// Each Hour
		//	Create Customers
		//		Reject overfull customers, inc lostCustomerDay
		//	Go through customer queue
		//		Select item 1-6
		//		Check to see if enough ingredients
		//			If not: inc lostCustomerDay
		//			If so: 
		//				remove ingredients, inc itemsOrdered[item]
		//				Add customer w/ order to dict.
		
		// Check inventory
		//	Expired food should always be on top of the stack
		
		// Print
		//	lostCustomerDay
		//	foodWasted
		//	orders
		
	}
	
	
}
