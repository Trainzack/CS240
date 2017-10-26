package cs240;

public class RecursiveSelectionSort  {

	/*
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {

		return recursiveSort(array, array.length);
	}

	/*
	 * A recursive method to sort the array via selection sort.
	 * @param array The array to be sorted
	 * @param end The end of the unsorted region.
	 * @return the sorted array
	 */
	private static int[] recursiveSort(int[] array, int end) {
		if (end <= 1) {
			return array;
		} else {
			// Find the position with the highest value
			int bestPosition = 0;
			for (int i = 1; i < end; i++) {
				if (array[i] > array[bestPosition]) {
					bestPosition = i;
				}
			}

			// now bestPosition is the index of the highest element

			// swap the largest element with the end of the unsorted part of the list (lastGood -1)
			int temp = array[bestPosition];
			array[bestPosition] = array[end-1];
			array[end-1] = temp;

			return recursiveSort(array, end - 1);

		}
	}




}