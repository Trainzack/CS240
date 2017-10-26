package cs240;	

import java.util.LinkedList;

public class IterativeRadixSort {
	
	// This can be any integer. It probably depends on the dataset
	final static int RADIX = 4;
	
	
	/**
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		
		// Find out how many times we need to run the algorithm.
		int max = Utilities.findMax(array);
		
		// The number of times we need to go is equal to the "length" of the maximum value in the
		// given radix, which is equal to the log base radix, rounded down, plus 1.
		int times = ((int)(Math.log(max) / Math.log(RADIX))) + 1;
	
		// Create the list of buckets (Queues)
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] buckets = new LinkedList[RADIX];
		
		// Instantiate all of the buckets.
		for (int i = 0; i < RADIX; i++) {
			buckets[i] = new LinkedList<Integer>();
		}
		
		
		for (int digit = 0; digit < times; digit++) {

			// Put each value into the bucket that shares its digit.
			for (int i = 0; i < array.length; i++) {
				// This formula gets the digit we are looking at.
				int bucketIndex = (int)(array[i] / Math.pow(RADIX, digit)) % RADIX;
				buckets[bucketIndex].add(array[i]);
			}
			
			// Take everything out of the buckets, in order
			int j = 0;
			for (int i = 0; i < RADIX; i++) {
				while (!buckets[i].isEmpty()) {
					array[j] = buckets[i].remove();
					j++;
				}
			}
				
		}
		
		return array;
		
	}
	
}