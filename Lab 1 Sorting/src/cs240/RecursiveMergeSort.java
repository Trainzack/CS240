package cs240;

public class RecursiveMergeSort {
	
	/**
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		
		return recursiveSort(array);
		
	}
	
	/**
	 * A recursive method to sort the array via merge sort.
	 * @param array The array to be sorted
	 * @return the sorted array
	 */
	private static int[] recursiveSort(int[] array) {
		// RunTests.printArray(array);
		if (array.length <= 1) {
			return array;
		} else {
			int midpoint = array.length / 2;
			
			int[][] arrays = new int[2][];
			// Create two arrays (0 and 1), one for each part of the array
			arrays[0] = new int[midpoint];
			// (we need to round up on this one for odd-numbered arrays!)
			arrays[1] = new int[(int)((array.length + 1) / 2)];
			
			// fill up the two arrays with the content from the first
			for (int i = 0; i < array.length; i++) {
				if (i >= arrays[0].length) {
					// The 0 array is full, so we need to add to the 1 array
					arrays[1][i - midpoint] = array[i];
				} else {
					// The 0 array is not yet full, so we need to add to the 0 array
					arrays[0][i] = array[i];
				}
			}
			
			// Sort each half
			arrays[0] = recursiveSort(arrays[0]);
			arrays[1] = recursiveSort(arrays[1]);
			
			array = Utilities.merge(arrays);
			
			// Merge the two arrays			
			return array;
		}
		
	}
	


}