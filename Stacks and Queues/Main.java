import java.util.EmptyStackException;


public class Main {

	/**
	 * Creates one instance of each data structure implementation and runs it through the test for that ADT
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FixedArrayStack<Integer> fAS = new FixedArrayStack<Integer>();
		VectorStack<Integer> vS = new VectorStack<Integer>();
		LinkedDataStack<Integer> lDS = new LinkedDataStack<Integer>();
		System.out.println("Fixed Array Stack works: " + stackTest(fAS));
		System.out.println("Vector Stack works: " + stackTest(vS));
		System.out.println("Linked Data Stack works: " + stackTest(lDS));
		

		FixedArrayQueue<Integer> fAQ = new FixedArrayQueue<Integer>();
		SingleLinkedDataQueue<Integer> sLDQ = new SingleLinkedDataQueue<Integer>();
		DoubleLinkedDataQueue<Integer> cLDQ = new DoubleLinkedDataQueue<Integer>();
		System.out.println("Fixed Array Queue works: " + queueTest(fAQ));
		System.out.println("Single Linked Data Queue works: " + queueTest(sLDQ));
		System.out.println("Circularly Linked Data Queue works: " + queueTest(cLDQ));
	}
	
	/*
	 * The test for ADTs implementing the StackInterface. 
	 */
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
	
	/*
	 * The test for queues implementing the QueueInterface.
	 */
	public static boolean queueTest(QueueInterface<Integer> s) {
		
		// Whether this stack passes the test.
		boolean result = true;
		// Fill the queue 16 entries, but remove an entry after every second one.
		for (int i = 0; i < 16; i++) {
			s.enqueue(new Integer(i));
			System.out.println("Enqueueing " + i);
			System.out.println("(Front is " + s.getFront() + ")");
			if (i % 2 == 0) {
				System.out.println("Dequeue:\t" + s.dequeue());
			}
		}
		// Empty the remaining 8 entries
		for (int i = 0; i < 8; i++) {
			System.out.println("GetFront:\t" + s.getFront());
			System.out.println("Dequeue:\t" + s.dequeue());
		}
		
		System.out.println("The queue should be empty now. True = " + s.isEmpty());
		if (!s.isEmpty()) {
			result = false;
		}
		// The queue should be empty now, so we should get exceptions when we try these things
		try {
			System.out.println(s.getFront());
			System.out.println("Uh Oh! The queue should be empty now!");
			result = false;
		} catch (EmptyQueueException e) {
			System.out.println("Expected Empty Queue Exception successfully thrown!");
		}
		
		try {
			System.out.println(s.dequeue());
			System.out.println("Uh Oh! The queue should be empty now!");
			result = false;
		} catch (EmptyQueueException e) {
			System.out.println("Expected Empty Queue Exception successfully thrown!");
		}
		
		//Test to make sure that clearing the stack really does empty it.
		for (int i = 0; i < 4; i++) {
			s.enqueue(new Integer(i));
		}
		
		s.clear();
		
		System.out.println("The queue should be empty now. True = " + s.isEmpty());

		if (!s.isEmpty()) {
			result = false;
		}
		
		return result;
	}

}
