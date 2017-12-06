/*import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;*/

import java.io.PrintStream;
import java.util.Random;

/**
 * Simulates a simplified model of an In-N-Out.
 * @author eli
 *
 */

public class Inventory {

	// This is the PrintStream we will be writing to. This is to ease exporting the output to a file.
	private static final PrintStream output = System.out;

	//private static PrintWriter output;

	private static Random randomNumberGenerator = new Random();

	// What day of the month it is. Starts at DEC 1st, or 301.
	private static int day = 301;

	// What is the last day to simulate (DEC 31st)
	private static final int last_date = 331; 

	// How many customers we can hold in line
	private static final int CUSTOMER_QUEUE_CAPACITY = 50;

	// Represents how many days away the next shipment is.
	private static int daysUntilNextShipment = 0;

	// Represents which customer we're serving
	private static int customerNumber = 1;

	// This array contains the six menu items that customers can order.
	@SuppressWarnings("unchecked") // This is okay because the new array is empty
	private static ListInterface<FoodType>[] menuItems = new ListInterface[6];

	// This dictionary contains the stacks of food items. 
	private static DictionaryInterface<FoodType, StackInterface<FoodItem>> stock = new SortedDictionaryLinkedData<>();

	public static void main(String[] args) {


		/*try {
			output = new PrintWriter("output.txt", "ASCII");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/

		init();
		/*
		for (int i = 0; i < menuItems.length; i++) {
			output.println(i + ": " + menuItems[i].toString());
		}*/


		for (; day <= last_date; day++) {
			runDay();
		}

		output.println("=========================================");
		output.println("=== January 01 Log: =====================");
		output.println("=========================================");
		output.println();
		output.println("This In-N-Out is shut down for having consistently low sales and customer satisfaction, despite high customer throughput. If only the higher-ups at In-N-Out waited until this data got back, then maybe they would have noticed that it was their own fault for not supplying the store with enough food.");
		output.close();
	}

	/**
	 * Initializes all of the important variables, like menu items
	 */
	private static void init() {

		// Add the correct food types to each menu item by creating arrays containing the food items first, then making the lists based off of those arrays.

		// (1) Burger - Bun, Patty, Lettuce, Tomato, Onion
		FoodType[] burger = {FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Tomato, FoodType.Onion};
		menuItems[0] = new FixedArrayList<>(burger);

		// (2) Cheese Burger - Cheese, Bun, Patty, Lettuce, Tomato, Onion
		FoodType[] cheeseBurger = {FoodType.Cheese, FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Tomato, FoodType.Onion};
		menuItems[1] = new FixedArrayList<>(cheeseBurger);

		// (3) Vegan lettuce wrap Burger - (NO Bun, No Patty),2 order of Lettuce, Tomato, Onion
		FoodType[] veganBurger = {FoodType.Lettuce, FoodType.Lettuce, FoodType.Tomato, FoodType.Onion};
		menuItems[2] = new FixedArrayList<>(veganBurger);

		// (4) Burger No Onion - (No Onion), Bun, Patty, Lettuce, Tomato,
		FoodType[] burgerNoOnion = {FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Tomato};
		menuItems[3] = new FixedArrayList<>(burgerNoOnion);

		// (5) Cheese Burger No Onion - (No Onion), Cheese, Bun, Patty, Lettuce, Tomato
		FoodType[] cheeseBurgerNoOnion = {FoodType.Cheese, FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Tomato};
		menuItems[4] = new FixedArrayList<>(cheeseBurgerNoOnion);

		// (6) Burger No Tomato - (No Tomato), Bun, Patty, Lettuce, Onion
		FoodType[] burgerNoTomato = {FoodType.Bun, FoodType.Patty, FoodType.Lettuce, FoodType.Onion};
		menuItems[5] = new FixedArrayList<>(burgerNoTomato);

		// Setup the stacks of food items
		for (FoodType f : FoodType.values()) {
			stock.add(f, new LinkedDataStack<FoodItem>());
		}

	}

	/**
	 * Adds 700-1000 new food items of random type to the correct stocks.
	 */
	private static void receiveShipment() {
		output.println("A new shipment arrived!");

		// Create a random number of each type
		// Put all new food items into their stacks
		// (the morning shift wants to do as little work as possible, and doesn't care about FIFO, so they put the new food on top of the old food. If the management paid attention, they'd surely be reprimanded.)

		// This is the number of food items we are receiving this shipment. We want 700-1000 items total
		int goal = randomNumberGenerator.nextInt(301) + 700;

		for (int i = 0; i < goal; i++) {

			// Select a random food type
			FoodType newType = FoodType.values()[randomNumberGenerator.nextInt(FoodType.values().length)];

			// Create the new food item
			FoodItem newItem = new FoodItem(day, newType);

			// Add it to the correct stock
			stock.getValue(newType).push(newItem);
		}

		// Randomly select the next shipment date
		daysUntilNextShipment = randomNumberGenerator.nextInt(3) + 3;
	}

	/**
	 * Fills up a given line with customers
	 * @param customers The line to put customers in
	 * @param hour What hour it currently is
	 * @return How many customers were lost this hour
	 */
	private static int createCustomers(QueueInterface<Customer> customers, int hour) {

		// How many customers decide to show up this hour
		int customersThisHour = randomNumberGenerator.nextInt(99) + 1;

		// How many customers couldn't fit into the queue
		int lostCustomersHour = 0;

		for (int i = 0; i < customersThisHour; i++) {
			// Create the new customer
			Customer newCustomer = new Customer(customerNumber);
			customerNumber++;

			// Check if the queue is full
			if (i >= CUSTOMER_QUEUE_CAPACITY) {
				lostCustomersHour++;
			} else {
				customers.enqueue(newCustomer);
			}
		}

		// Print the results
		output.print(hour + ":00 --- " + customersThisHour + " customers arrived");
		if (lostCustomersHour > 0) { 
			output.print(" (" + lostCustomersHour + " were turned away)");
		}
		output.println(".");

		return lostCustomersHour;
	}

	/**
	 * 
	 * @param customerOrder
	 * @return
	 */
	private static boolean enoughIngredients(ListInterface<FoodType> customerOrder) {

		// First, check to see if we are out of any of the food types we need

		Object[] orderArray = customerOrder.toArray(); 

		for (Object f : orderArray) {

			// Check to see if we have at least one of each of the food items in the customer's order
			if (stock.getValue((FoodType)f).size() <= 0) {
				return false;
			}
		}

		// An ugly hack to check for the one item that has double lettuce
		if (customerOrder == menuItems[2] && stock.getValue(FoodType.Lettuce).size() <= 1) {
			return false;
		}

		return true;
	}

	/**
	 * Simulates a single day.
	 */
	private static void runDay() {

		output.println("=========================================");
		output.println("=== December " + String.format("%02d",(day - 300)) + " Log: ====================");
		output.println("=========================================");
		output.println();

		// Represents how many customers we lost today
		int lostCustomerDay = 0;

		// Represents how many of each menu item was ordered (i.e., the item in menuItems[0] will have its count in itemsOrdered[0])
		int[] itemsOrdered = {0, 0, 0, 0, 0, 0};

		// Represents how many of each type of food item has been wasted via spoiling (today)
		DictionaryInterface<FoodType, Integer> foodWasted = new SortedDictionaryLinkedData<>();
		for (FoodType f : FoodType.values()) {
			foodWasted.add(f, new Integer(0));
		}

		customerNumber = 1;

		// Represents customer orders. (Key is customer, value is menu item ordered)
		DictionaryInterface<Customer, Integer> orders = new SortedDictionaryLinkedData<Customer, Integer>();

		// Maybe receive shipment
		if (daysUntilNextShipment <= 0) {
			receiveShipment();
		}

		// Print out how much stocks we have

		for (FoodType f : FoodType.values()) {

			output.println("\t"+ f.getPlural() + " in stock: " + stock.getValue(f).size());
		}

		// Go through every hour of the day (military time)
		for (int hour = 10; hour <= 19; hour++) {


			QueueInterface<Customer> customers = new FixedArrayQueue<>(CUSTOMER_QUEUE_CAPACITY);

			// Generate customers
			lostCustomerDay += createCustomers(customers, hour);

			// Go through customer queue
			while (!customers.isEmpty()) {
				Customer currentCustomer = customers.dequeue();
				// Select one of the menu items. This is the customer's order
				int orderNumber = randomNumberGenerator.nextInt(6);
				ListInterface<FoodType> curCustomerOrder = menuItems[orderNumber];

				// Check to see if we have enough ingredients
				if (enoughIngredients(curCustomerOrder)) {

					// Empty off the ingredients we will need
					for (int i = 0; i < curCustomerOrder.size(); i++) {
						stock.getValue(curCustomerOrder.view(i)).pop();
					}

					// Increment the amount of times this was ordered
					itemsOrdered[orderNumber] += 1;

					// Log order to dictionary
					orders.add(currentCustomer, new Integer(orderNumber + 1));

				} else {
					// We don't have enough ingredients, so we can't do it!
					lostCustomerDay++;
				}

			}


		}

		// Check inventory
		//	(The evening shift has to sort out morning shift's mess)
		//	Sort it by copying stack to array, sorting, then copying array to stack
		for (FoodType f : FoodType.values()) {

			StackInterface<FoodItem> stack = stock.getValue(f);

			// For some reason we have to do it this way to avoid an invalid cast
			Object[] temp = stack.toArray();
			// Check to make sure the stack isn't empty
			if (temp != null) {
				FoodItem[] items = new FoodItem[temp.length];

				for (int i = 0; i < temp.length; i++) {
					items[i] = (FoodItem)temp[i];
				}

				stack.clear();
				items = IterativeInsertionSort.sort(items);

				// Put the items back in the stack
				for (int i = 0; i < items.length; i++) {
					FoodItem curItem = (FoodItem)items[i];

					// Check to see if it is expired
					if (!curItem.isExpired(day)) {
						// It's still good, so put it on the shelf
						stack.push(curItem);

					} else {
						// It's gone bad, toss it and record the waste
						foodWasted.add(f, foodWasted.getValue(f) + 1);
					}

				}
			}

		}

		daysUntilNextShipment--;

		// Now we print the end of day information log!

		output.println();

		output.println("---End of day inventory count---");

		output.println("Customers lost today: " + lostCustomerDay);
		output.println("Food expired today:");
		for (FoodType f : FoodType.values()) {
			output.println("\t" + f.getPlural() + ": " + foodWasted.getValue(f));
		}
		output.println("Orders served today:");
		for (int i = 0; i < itemsOrdered.length; i++) {
			output.println("\t#" + (i + 1) + " was ordered " + itemsOrdered[i] + " time(s).");
		}

		output.println("Successful customer order log:");
		output.println(orders);

		output.println("--------------------------------");
		output.println();
	}


}
