package cs240;	

import java.util.Stack;

public class LoggedIterativeQuickSort {
	
	/**
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		int countMove = 0;
		int countCompare = 0;
		
		// A stack of int pairs, where the first element is the start sub-array to be quicksorted,
		// and the last element is the end of the subarray
		Stack<int[]> boundsStack = new Stack<>();
		
		// We will need to sort the entire array, so we push that instruction.
		int[] firstInstruction = {0, array.length-1};
		boundsStack.push(firstInstruction);
		
		while (!boundsStack.isEmpty()) {
			
			// Get the bounds of the array we need to sort
			int[] currentBounds = boundsStack.pop();
			int start = currentBounds[0];
			int end = currentBounds[1];
			countMove++;
			
			// Check to see if we actually need to sort anything
			countCompare++;
			if (start >= end) {
				continue;
			} else {
				
				// Use the quicksort partition to partition the array
				// This is the Lomuto partition scheme, which is apparently suboptimal
				// partition the array around a pivot (the last element)
				int pivot = array[end];

				int i = start - 1;

				for (int j = start; j < end; j++) {
					countCompare++;
					if (array[j] < pivot) {
						i += 1;
						int temp = array[i];
						array[i] = array[j];
						array[j] = temp;
						countMove+=3;
					}
				}
				countCompare++;
				if (array[end] < array[i + 1]) {
					int temp = array[end];
					array[end] = array[i+1];
					array[i+1] = temp;
					countMove+=3;

				}
				pivot = i+1;
				
				// Quicksort each half.
				int[] firstBounds = {start, pivot-1};
				boundsStack.push(firstBounds);
				int[] secondBounds = {pivot + 1, end};
				boundsStack.push(secondBounds);
			}
		}	
		int[] out = {countMove, countCompare}; 
		return out;
	}
}