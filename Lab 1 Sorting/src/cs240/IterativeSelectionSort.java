package cs240;

public class IterativeSelectionSort  {
	/**
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		
		// lastGood is the last element in the array that is guaranteed to be in the right place.
		for (int lastGood = array.length; lastGood > 0; lastGood--) {
			// Find the position with the highest value
			int bestPosition = 0;
			for (int i = 1; i < lastGood; i++) {
				if (array[i] > array[bestPosition]) {
					bestPosition = i;
				}
			}
			// now bestPosition is the index of the highest element
			
			// swap the largest element with the end of the unsorted part of the list (lastGood -1)
			int temp = array[bestPosition];
			array[bestPosition] = array[lastGood-1];
			array[lastGood-1] = temp;
		}
		
		return array;
	}
	
}