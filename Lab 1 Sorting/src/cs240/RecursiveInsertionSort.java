package cs240;

public class RecursiveInsertionSort {
	/*
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		
		return recursiveSort(array, array.length);
		
	}
	
	/*
	 * A recursive method to sort the array via insertion sort.
	 * @param array The array to be sorted
	 * @param end The end of the unsorted region.
	 * @return the sorted array
	 */
	private static int[] recursiveSort(int[] array, int firstSorted) {
		
		if (firstSorted <= 0) {
			return array;
		} else {
			// Get the element that needs to be sorted next
			// RunTests.printArray(array);
			int nextSorted = array[firstSorted - 1];
			for (int i = array.length -1; i >= firstSorted; i--) {
				if (nextSorted > array[i]) {
					// Put the element we are holding into the right spot in the array, and grab the one that was there so that we can move the rest down.
					int temp = array[i];
					array[i] = nextSorted;
					nextSorted = temp;
				}
			}
			array[firstSorted-1] = nextSorted;
		}
		return recursiveSort(array, firstSorted -1);
		
	}
}