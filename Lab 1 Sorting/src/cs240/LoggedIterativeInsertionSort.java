package cs240;

public class LoggedIterativeInsertionSort {
	
	/**
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		
		int countMove = 0;
		int countCompare = 0;
		
		// firstSorted is the first element in the array that is already sorted
		for (int firstSorted = array.length; firstSorted > 0; firstSorted--) {
			// Get the element that needs to be sorted next
			// RunTests.printArray(array);
			int nextSorted = array[firstSorted - 1];
			countMove++;
			for (int i = array.length -1; i >= firstSorted; i--) {
				countCompare++;
				if (nextSorted > array[i]) {
					// Put the element we are holding into the right spot in the array, and grab the one that was there so that we can move the rest down.
					int temp = array[i];
					array[i] = nextSorted;
					nextSorted = temp;
					countMove += 3;
				}
			}
			array[firstSorted-1] = nextSorted;
			countMove++;
		}
		
		int[] out = {countMove, countCompare}; 
		return out;
		
	}
	
}