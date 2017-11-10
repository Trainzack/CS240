package cs240;	

public class IterativeShellSort {
	
	/**
	 * Sort the elements in the given array in ascendinge order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		
		// Figure out how many times to do shell sort
		int iterations = 0;
		
		while (true) {
			if (Utilities.shellSequence(iterations) > array.length) {
				iterations--;
				break;
			} else {
				iterations++;
			}
		}
		
		// Now we must actually do the sort.
		for (int i = iterations; i >= 0; i--) {
			int currentGap = Utilities.shellSequence(i);
			currentGap = 1;
			
			// Do an insertion sort with a gap of size currentGap
			for (int j = currentGap; j < array.length; j++) {
				
				int temp = array[j];
				
				//keep moving back currentGap elements until we find one that is smaller than temp, moving everything else forward at the same time
				int k = j;
				while(k >= currentGap && array[k - currentGap] > temp) {
					array[k] = array[k-currentGap];
					k -= currentGap;
				}
				array[k] = temp;
				
			}
		}
		
		return array;
		
	}
	
}