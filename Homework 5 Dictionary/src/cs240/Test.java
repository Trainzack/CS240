package cs240;

import java.util.Iterator;

public class Test {

	private final static int TEST_SIZE = 10;

	public static void main(String args[]) {

		DictionaryInterface<Integer, String> dict = new SortedDictionaryStaticArray<>(TEST_SIZE);
		dictionaryTest(dict, "Static Array Dict");
		dict = new SortedDictionaryLinkedData<>();
		dictionaryTest(dict, "Linked Data Dict");

	}

	public static void dictionaryTest(DictionaryInterface<Integer, String> dict, String listName) {

		System.out.println("==================");
		System.out.println("Testing " + listName);
		System.out.println("==================");

		Integer[] keyData = new Integer[TEST_SIZE];
		String[] valueDataA = new String[TEST_SIZE];
		String[] valueDataB = new String[TEST_SIZE];

		for (int i = 0; i < TEST_SIZE; i++) {
			keyData[i] = new Integer(i);
			valueDataA[i] = "A:" + i;
			valueDataB[i] = "B:" + i;
		}

		// Add items to the dictionary

		boolean valueCheck = true;
		boolean containmentCheck = true;
		boolean sizeCheck = true;
		boolean replacementCheck = true;
		boolean removalCheck = true;
		boolean emptyCheck = true;

		boolean iteratorHasNext = true;
		boolean iteratorNext = true;
		boolean iteratorRemove = true;

		// The dictionary should be empty
		emptyCheck = booleanLatch(emptyCheck, dict.isEmpty());
		// Add the even numbers to the dictionary
		for (int i = 0; i < TEST_SIZE; i+= 2) {

			// System.out.println("Adding " + i);
			String v = dict.add(keyData[i], valueDataA[i]);

			// We are replacing nothing, so this should always return null.
			replacementCheck = booleanLatch(replacementCheck, v == null);

			// Check to make sure the size of the dictionary is correct
			sizeCheck = booleanLatch(sizeCheck, dict.getSize() == (i / 2) + 1);

			// The dictionary should not be empty
			emptyCheck = booleanLatch(emptyCheck, !dict.isEmpty());

			for (int j = 0; j <= i; j += 2) {
				// Ensure that the dictionary contains the things we put in
				containmentCheck = booleanLatch(containmentCheck, dict.contains(keyData[j]));
				// Ensure that the value is correct
				valueCheck = booleanLatch(valueCheck, dict.getValue(keyData[j]) == valueDataA[j]);
			}

			// Ensure that the dictionary does not contain the things we didn't put in
			for (int j = i + 2; j < TEST_SIZE; j += 2) {
				if (dict.contains(keyData[j])) {
					containmentCheck = false;
				}
			}

		}

		// Add all of the numbers to the dictionary, but with the B values.
		for (int i = 0; i < TEST_SIZE; i++) {
			// System.out.println("======================");
			// System.out.println("Adding " + i);
			String v = dict.add(keyData[i], valueDataB[i]);

			// dict.getSize(); // only do this because of testing side-effect

			if (i % 2 == 0) {
				// We are replacing the values we entered earlier
				replacementCheck = booleanLatch(replacementCheck, v == valueDataA[i]);
			} else {
				// We are replacing nothing, so this should always return null.
				replacementCheck = booleanLatch(replacementCheck, v == null);	
			}


			for (int j = 1; j <= i; j += 2) {
				// Ensure that the dictionary contains the odd numbers we put in
				containmentCheck = booleanLatch(containmentCheck, dict.contains(keyData[j]));
				// Ensure that the value is correct
				valueCheck = booleanLatch(valueCheck, dict.getValue(keyData[j]) == valueDataB[j]);
			}

			// Ensure that the dictionary does not contain the things we didn't put in
			for (int j = i + 2; j < TEST_SIZE; j += 2) {
				booleanLatch(containmentCheck, !dict.contains(keyData[j]));
			}
		}


		Iterator<Integer> iK = dict.getKeyIterator();
		Iterator<String> iV = dict.getValueIterator();
		if (iK == null || iV == null) {
			iteratorHasNext = false;
			iteratorNext = false;
			iteratorRemove = false;
		} else {
			for (int i = 0; i < TEST_SIZE; i++) {
				iteratorHasNext = booleanLatch(iteratorHasNext, iK.hasNext() && iV.hasNext());
				Integer k = iK.next();
				String v = iV.next();
				iteratorNext = booleanLatch(iteratorNext, k == keyData[k] && v == valueDataB[i]);
			}
			iteratorHasNext = booleanLatch(iteratorHasNext, !iK.hasNext() && !iV.hasNext());
		}

		for (int i = 0; i < TEST_SIZE; i++) {
			String v = dict.remove(keyData[i]);
			removalCheck = booleanLatch(removalCheck, v == valueDataB[i] && dict.getSize() == (TEST_SIZE - i-1));
		}

		// The dictionary should be empty
		emptyCheck = booleanLatch(emptyCheck, dict.isEmpty());

		// Fill up the dictionary again.
		for (int i = 0; i < TEST_SIZE; i++) {
			dict.add(keyData[i], valueDataA[i]);
		}

		iK = dict.getKeyIterator();

		for (int i = TEST_SIZE; i > 0; i--) {
			iK.next();
			iK.remove();
			iteratorRemove = booleanLatch(iteratorRemove, dict.getSize() == i - 1);
		}
		// Fill up the dictionary again.
		for (int i = 0; i < TEST_SIZE; i++) {
			dict.add(keyData[i], valueDataB[i]);
		}

		iV = dict.getValueIterator();

		for (int i = TEST_SIZE; i > 0; i--) {
			iV.next();
			iV.remove();
			iteratorRemove = booleanLatch(iteratorRemove, dict.getSize() == i - 1);
		}


		printTestResult(removalCheck, listName, "Removal Check");
		printTestResult(emptyCheck, listName, "Empty Check");
		printTestResult(sizeCheck, listName, "Size Check");
		printTestResult(valueCheck, listName, "Value Check");
		printTestResult(containmentCheck, listName, "Containment Check");
		printTestResult(iteratorHasNext, listName, "Iterator Check (Has Next)");
		printTestResult(iteratorNext, listName, "Iterator Check (Next)");
		printTestResult(iteratorRemove, listName, "Iterator Check (Remove)");
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

	/**
	 * Returns the value of a flag such that the value of the flag is set to false if the new data is set to false,
	 * but is never returned to true.
	 * @param flag The flag whose value we are returning
	 * @param newData The new data we are getting
	 * @return The new value of the flag
	 */
	private static boolean booleanLatch(boolean flag, boolean newData) {
		if (flag && newData) {
			return true;
		}
		return false;
	}

	/**
	 * Take an array of objects, and print them out nicely.
	 * @param l The array to print
	 */
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



}
