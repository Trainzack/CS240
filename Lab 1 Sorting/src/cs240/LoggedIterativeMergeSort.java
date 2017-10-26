package cs240;	

import java.util.LinkedList;


public class LoggedIterativeMergeSort {
	
	
	
	/**
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort (int[] array) {
		int countMove = 0;
		int countCompare = 0;
		
		// arrayQueue will hold sorted sub-arrays.
		LinkedList<int[]> arrayQueue = new LinkedList<int[]>();
		
		// Fill up the arrayQueue with each element wrapped in an array of size 1.
		for (int i = 0; i < array.length; i++) {
			countMove++;
			int[] newArray = {array[i]};
			arrayQueue.add(newArray);
		}
		
		// Keep merging arrays from one end of the queue and putting them on the other, until the queue only has one element
		while(arrayQueue.size() > 1) {
			int[][] arrays = new int[2][];
			arrays[0] = arrayQueue.remove();
			arrays[1] = arrayQueue.remove();
			countMove+=2;
			
			int[] newArray = new int[arrays[0].length + arrays[1].length];
			


			// Use these numbers to keep track of where we're at for each array
			// (negative means the array is empty!
			int[] arrayIndexes = {0,0};
			for (int i = 0; i < newArray.length; i++) {

				// Figure out which of the two arrays to pull from, and store it in arrayToPullFrom
				int arrayToPullFrom = -1;
				
				countCompare++;
				if (arrayIndexes[0] < 0) {
					// The 0 array is empty, so pull from array 1
					arrayToPullFrom = 1;
				} else if (arrayIndexes[1] < 0) {
					countCompare++;
					// The 1 array is empty, so pull from array 0
					arrayToPullFrom = 0;
				} else {
					countCompare++;
					// Neither array is empty, so check which one is smaller and pull from that
					if (arrays[0][arrayIndexes[0]] < arrays[1][arrayIndexes[1]]) {
						arrayToPullFrom = 0;
					} else {
						arrayToPullFrom = 1;
					}
				}
				// Set the next element in the array to the correct element of the array we are pulling from
				countMove++;
				array[i] = arrays[arrayToPullFrom][arrayIndexes[arrayToPullFrom]];

				// Move the array index forward one element
				arrayIndexes[arrayToPullFrom] += 1;

				// If we've moved passed this array's end, set its index to -1 so that we know we're done.
				if (arrayIndexes[arrayToPullFrom] >= arrays[arrayToPullFrom].length) {
					arrayIndexes[arrayToPullFrom] = -1;
				}

			}
			arrayQueue.add(newArray);
			
		}
		int[] out = {countMove, countCompare}; 
		return out;
		
	}
}