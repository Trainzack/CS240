/**
 * Represents a single customer.
 * @author eli
 *
 */
public class Customer implements Comparable<Customer> {

	private int number;
	
	public Customer(int number) {
		super();
		this.number = number;
	}

	@Override
	public String toString() {
		return "Customer " + number;
	}

	@Override
	public int compareTo(Customer arg0) {
		// TODO Auto-generated method stub
		return number - arg0.number;
	}
	
}
