package cs240;	

import java.util.Stack;

public class IterativeQuickSort {
	
	/*
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		
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
			
			// Check to see if we actually need to sort anything
			if (start >= end) {
				continue;
			} else {
				
				// Use the quicksort partition to partition the array
				int pivot = Utilities.quicksortPartition(array, start, end);
				
				// Quicksort each half.
				int[] firstBounds = {start, pivot-1};
				boundsStack.push(firstBounds);
				int[] secondBounds = {pivot + 1, end};
				boundsStack.push(secondBounds);
			}
		}	
		return array;
	}
}