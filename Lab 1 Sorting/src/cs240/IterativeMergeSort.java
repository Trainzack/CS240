package cs240;	

import java.util.LinkedList;


public class IterativeMergeSort {
	
	/**
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort (int[] array) {
		
		// arrayQueue will hold sorted sub-arrays.
		LinkedList<int[]> arrayQueue = new LinkedList<int[]>();
		
		// Fill up the arrayQueue with each element wrapped in an array of size 1.
		for (int i = 0; i < array.length; i++) {
			int[] newArray = {array[i]};
			arrayQueue.add(newArray);
		}
		
		// Keep merging arrays from one end of the queue and putting them on the other, until the queue only has one element
		while(arrayQueue.size() > 1) {
			int[][] arrays = new int[2][];
			arrays[0] = arrayQueue.remove();
			arrays[1] = arrayQueue.remove();
			int[] newArray = Utilities.merge(arrays);
			arrayQueue.add(newArray);
			
		}
		// Now arrayQueue only contains one element, the sorted array.
		return arrayQueue.remove();
		
	}
}