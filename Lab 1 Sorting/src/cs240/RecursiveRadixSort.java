package cs240;

import java.util.LinkedList;

public class RecursiveRadixSort {

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

		// The recursive method works with Integers, not ints, so we need to convert them

		Integer[] objectArray = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			objectArray[i] = new Integer(array[i]);
		}
		// Do the recursive sort
		objectArray  = recursiveSort(objectArray, times);

		// Convert the Integers to ints
		for (int i = 0; i < array.length; i++) {
			array[i] = objectArray[i].intValue();
		}

		return array;

	}

	/**
	 * A recursive method to sort the array via merge sort.
	 * @param array The array to be sorted
	 * @return the sorted array
	 */
	private static Integer[] recursiveSort(Integer[] array, int digit) {

		if (digit < 0) {
			return array;
		} else {
			// Create the array of buckets (Queues)
			@SuppressWarnings("unchecked")
			LinkedList<Integer>[] buckets = new LinkedList[RADIX];

			// Instantiate all of the buckets.
			for (int i = 0; i < RADIX; i++) {
				buckets[i] = new LinkedList<Integer>();
			}

			// Put each value into the bucket that shares its digit.
			for (int i = 0; i < array.length; i++) {
				// This formula gets the digit we are looking at.
				int bucketIndex = (int)(array[i] / Math.pow(RADIX, digit)) % RADIX;
				buckets[bucketIndex].add(array[i]);
			}	

			// Hold on to an index so that we can concatenate the lists right after sorting each one
			int index = 0;

			// Radix sort each of the sublists
			for (int i = 0; i < buckets.length; i++) {

				// Create the array representation of the bucket (Possible cause of slowdowns!)
				Integer[] bucket = (Integer[]) buckets[i].toArray(new Integer[buckets[i].size()]);

				// Sort the bucket
				bucket = recursiveSort(bucket, digit -1);

				// Put the bucket back into the right spot in the list.
				for (int j = 0; j < bucket.length; j++) {
					array[index] = bucket[j];
					index++;
				}

			}
			return array;

		}
	}
}