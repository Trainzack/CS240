package cs240;	

public class LoggedIterativeShellSort {
	
	/**
	 * Sort the elements in the given array in ascending order.
	 * The method may change the existing array.
	 * @return A new array, containing the sorted elements
	 */
	public static int[] sort(int[] array) {
		int countMove = 0;
		int countCompare = 0;
		
		// Figure out how many times to do shell sort
		int iterations = 0;
		
		while (true) {
			countCompare++;
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
				
				countMove++;
				int temp = array[j];
				
				//keep moving back currentGap elements until we find one that is smaller than temp, moving everything else forward at the same time
				int k = j;
				while(k >= currentGap && array[k - currentGap] > temp) {
					countCompare += 2;
					countMove++;
					array[k] = array[k-currentGap];
					k -= currentGap;
				}
				countCompare += 2;
				countMove++;
				array[k] = temp;
				
			}
		}

		int[] out = {countMove, countCompare}; 
		return out;
		
	}
	
}