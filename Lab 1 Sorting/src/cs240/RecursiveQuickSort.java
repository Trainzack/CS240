package cs240;

public class RecursiveQuickSort {
	
	/*
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		
		recursiveSort(array, 0, array.length-1);
		return array;
		
	}
	
	/*
	 * Performs quicksort on the array, modifying it instead of returning anything.
	 */
	private static void recursiveSort(int[] array, int start, int end) {
		
		if (start >= end) {
			return;
		} else {
			
			int pivot = Utilities.quicksortPartition(array, start, end);
			// Quick sort each half.
			recursiveSort(array, start, pivot-1);
			recursiveSort(array, pivot + 1, end);
		}
		
	}
	
}