package cs240;

public class Utilities {

	/**
	 * Merges two sorted arrays that are stored in another array in ascending order.
	 * @param arrays Two sorted int arrays that are stored in one int[] array.
	 * @return The merged and sorted array
	 */
	public static int[] merge(int[][] arrays) {
		int[] array = new int[arrays[0].length + arrays[1].length];


		// Use these numbers to keep track of where we're at for each array
		// (negative means the array is empty!
		int[] arrayIndexes = {0,0};
		for (int i = 0; i < array.length; i++) {

			// Figure out which of the two arrays to pull from, and store it in arrayToPullFrom
			int arrayToPullFrom = -1;

			if (arrayIndexes[0] < 0) {
				// The 0 array is empty, so pull from array 1
				arrayToPullFrom = 1;
			} else if (arrayIndexes[1] < 0) {
				// The 1 array is empty, so pull from array 0
				arrayToPullFrom = 0;
			} else {
				// Neither array is empty, so check which one is smaller and pull from that
				if (arrays[0][arrayIndexes[0]] < arrays[1][arrayIndexes[1]]) {
					arrayToPullFrom = 0;
				} else {
					arrayToPullFrom = 1;
				}
			}
			// Set the next element in the array to the correct element of the array we are pulling from
			array[i] = arrays[arrayToPullFrom][arrayIndexes[arrayToPullFrom]];

			// Move the array index forward one element
			arrayIndexes[arrayToPullFrom] += 1;

			// If we've moved passed this array's end, set its index to -1 so that we know we're done.
			if (arrayIndexes[arrayToPullFrom] >= arrays[arrayToPullFrom].length) {
				arrayIndexes[arrayToPullFrom] = -1;
			}

		}
		return array;
	}

	// Take an array of integers, and print them out nicely.
	public static void printArray(int[] l) {
		
		System.out.print("[");
		for (int i = 0; i < l.length; i++) {
			System.out.print(l[i]);
			if (i+1 < l.length) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}
	
	/** Does a quicksort partition on the provided array, using the end of the array as a pivot.
	*	@return The index of the pivot
	*/
	public static int quicksortPartition(int[] array, int start, int end) {
		// This is the Lomuto partition scheme, which is apparently suboptimal
		// partition the array around a pivot (the last element)
		int pivot = array[end];

		int i = start - 1;

		for (int j = start; j < end; j++) {
			if (array[j] < pivot) {
				i += 1;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		if (array[end] < array[i + 1]) {
			int temp = array[end];
			array[end] = array[i+1];
			array[i+1] = temp;

		}

		return i+1;
	}
	/**
	 * Finds the maximum of an array of integers
	 */
	public static int findMax(int[] a) {
		
		int max = a[0];
		
		for (int i = 1; i < a.length; i++) {
			if (a[i] > max) max = a[i];
		}
		return max;
	}
	
	/**
	 * The sequence used for shell sort A102549.
	 * Supposedly proven by Sedgewick to run in O(N^(4/3)), which is good enough for me.
	 * @param k The entry of the sequence to use
	 * @return The entry of the sequence
	 */
	public static int shellSequence(int k) {
		if (k < 0) {
			throw new IllegalArgumentException();
		} else if (k == 0) {
			// The sequence is prefixed with zero.
			return 1;
		} else {
			// The actual sequence itself
			return (int)(Math.pow(4, k) + 3 * Math.pow(2, k-1) + 1);
		}
	}
}
