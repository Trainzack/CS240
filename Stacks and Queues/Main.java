import java.util.EmptyStackException;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FixedArrayStack<Integer> fAS = new FixedArrayStack<Integer>();
		VectorStack<Integer> vS = new VectorStack<Integer>();
		LinkedDataStack<Integer> lDS = new LinkedDataStack<Integer>();
		System.out.println("Fixed Array Stack works: " + stackTest(fAS));
		System.out.println("Vector Stack works: " + stackTest(vS));
		System.out.println("Linked Data Stack works: " + stackTest(lDS));
	}
	
	public static boolean stackTest(StackInterface<Integer> s) {
		
		// Whether this stack passes the test.
		boolean result = true;
		// Fill the stack 4 entries deep
		for (int i = 0; i < 4; i++) {
			s.push(new Integer(i));
			System.out.println("Pushing " + i);
		}
		
		// Empty the stack back 4 entries
		for (int i = 0; i < 4; i++) {
			System.out.println("Peek:\t" + s.peek());
			System.out.println("Pop:\t" + s.pop());
		}
		
		System.out.println("The stack should be empty now. True = " + s.isEmpty());
		if (!s.isEmpty()) {
			result = false;
		}
		// The stack should be empty now, so we should get exceptions when we try these things
		try {
			System.out.println(s.peek());
			System.out.println("Uh Oh! The stack should be empty now!");
			result = false;
		} catch (EmptyStackException e) {
			System.out.println("Expected Empty Stack Exception successfully thrown!");
		}
		
		try {
			System.out.println(s.pop());
			System.out.println("Uh Oh! The stack should be empty now!");
			result = false;
		} catch (EmptyStackException e) {
			System.out.println("Expected Empty Stack Exception successfully thrown!");
		}
		
		//Test to make sure that clearing the stack really does empty it.
		for (int i = 0; i < 4; i++) {
			s.push(new Integer(i));
		}
		
		s.clear();
		
		System.out.println("The stack should be empty now. True = " + s.isEmpty());

		if (!s.isEmpty()) {
			result = false;
		}
		
		return result;
	}

}
