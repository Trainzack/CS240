import java.util.Random;


public class Test {

	private static final int TEST_COUNT = 20;

	public static void main(String[] args) {

		// Note: This test is unbearably slow above 5000 entries or so.
		LinkedPriorityQueue<Integer> queue = new LinkedPriorityQueue<>();
		Random r = new Random();

		boolean sizeCorrect = true;

		for (int i = 0; i < TEST_COUNT; i++) {

			if (queue.getSize() != i) sizeCorrect = false;

			queue.add(new Integer(r.nextInt(TEST_COUNT)));

		}

		if (!sizeCorrect) {
			System.out.println("LinkedPriorityQueue returned incorrect size!");
		} else {
			System.out.println("LinkedPriorityQueue returned correct size!");
		}

		int errors = 0;

		// Now we need to make sure that the list is sorted.
		int min = queue.remove();
		while (!queue.isEmpty()) {
			int next = queue.remove();
			if (next > min) {
				errors++;
			} else {
				min = next;
			}
		}

		System.out.println("LinkedPriorityQueue Sorting errors found: " + errors);

		try {
			queue.remove();
			System.out.println("LinkedPriorityQueue isEmpty, but remove returns values!");
		} catch (EmptyQueueException e) {
			System.out.println("LinkedPriorityQueue threw expected EmptyQueueException.");
		}

		// Test the lists.

		ListInterface<Integer> list = new FixedArrayList<>(TEST_COUNT);
		testList(list, "FixedArrayList");
		list = new LinkedList<Integer>();
		testList(list, "LinkedList");
		list = new DoubleLinkedList<Integer>();
		testList(list, "DoubleLinkedList");

	}

	/**
	 * Run a list of integers through a pretty good test.
	 * @param list The list we are testing
	 * @param name What the list is called
	 */
	public static void testList(ListInterface<Integer> list, String name) {
		
		System.out.println("");
		System.out.println("==============================");
		System.out.println("Testing " + name);
		
		// Fill the list with increasing elements, then
		// remove everything from every index.
		boolean sequential = true;
		boolean size = true;
		boolean containment = true;

		// An array that contains all of the numbers, so that we can test for equality better.
		Integer[] numbers = new Integer[TEST_COUNT];

		for (int i = 0; i < TEST_COUNT; i++) {
			numbers[i] = new Integer(i);
		}

		for (int rIndex = 0; rIndex < TEST_COUNT; rIndex ++) {
			// System.out.println("Testing " + rIndex);
			// Fill the array with increasing numbers
			for (int i = 0; i < TEST_COUNT; i++) {
				list.add(numbers[i]);
				if (list.size() != i + 1) {
					size = false;
				}
			}

			// All of the numbers should still be in sequence
			for (int i = 0; i < TEST_COUNT - rIndex; i++) {
				if (list.remove(rIndex) != numbers[i + rIndex]) {
					sequential = false;
				}
			}

			// It should contain all of the numbers, up to what we removed
			for (int i = 0; i < rIndex; i++) {
				if (!list.contains(numbers[i])) {
					containment = false;
				}
			}

			// It should not contain any of the numbers after what we removed.
			for (int i = rIndex; i < TEST_COUNT; i++) {
				if (list.contains(numbers[i])) {
					containment = false;
				}
			}


			list.clear();
		}
		printTestResult(sequential, name, "removal test");
		printTestResult(containment, name, "containment test");
		printTestResult(size, name, "size test");

		list.clear();

		boolean indexAdd = true;
		boolean indexView = true;
		
		// Add everything to the start
		for (int i = 0; i < TEST_COUNT; i++) {
			list.add(numbers[i], 0);
			
			// Check to make sure the size is right
			if (list.size() != i + 1) {
				indexAdd = false;
				// System.out.println(list.size());
			}
			
			// Check to make sure that the first index is the element we're adding, and the lest is 0
			if (list.view(i) == null || list.view(0) != numbers[i] || list.view(i) != numbers[0]) {
				indexView = false; 
			}
		}
		// printArray(list.toArray());
		
		printTestResult(indexAdd, name, "add(0) test (size)");
		printTestResult(indexView, name, "add(0) test (view)");
		indexAdd = true;
		// All of the numbers should still be in sequence
		for (int i = TEST_COUNT -1; i >= 0; i--) {
			if (list.remove(0) != numbers[i]) {
				indexAdd = false;
			}
		}
		printTestResult(indexAdd, name, "add(0) test (sequence)");

		list.clear();
		
		for (int i = 0; i < TEST_COUNT; i++) {
			list.add(numbers[TEST_COUNT - i - 1]);
		}
		
		boolean replaceReturn = true;
		boolean replaceSet = true;
		
		for (int i = 0; i < TEST_COUNT; i++) {
			Integer c = list.replace(i, numbers[i]);
			if (c != numbers[TEST_COUNT - i - 1]) {
				replaceReturn = false;
			}
			
			if (list.view(i) != numbers[i]) {
				replaceSet = false;
			}
			
		}
		
		printTestResult(replaceReturn, name, "replace return value test");
		printTestResult(replaceSet, name, "replace value set test");
		
		list.clear();
		try {
			list.remove(0);
			System.err.println("Uh Oh! " + name + " did not throw expected empty stack exception!");
		} catch (EmptyQueueException e) {
			System.out.println(name + " threw expected EmptyQueueException!");
		}
		
	}

	// Take an array of integers, and print them out nicely.
	public static void printArray(Object[] l) {

		System.out.print("[");
		for (int i = 0; i < l.length; i++) {
			System.out.print(l[i]);
			if (i+1 < l.length) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

	/**
	 * Print the results of a test
	 * @param result Whether the test passed
	 * @param name The name of the test
	 */
	public static void printTestResult(boolean result, String listName, String name) {
		if (result) System.out.println(listName + " passed " + name +".");
		else System.err.println(listName + " failed " + name + "!");
	}

}
