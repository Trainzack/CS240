/**
 * A class of bags implemented by fixed arrays.
 *
 **/

public final class ArrayBag<T> implements BagInterface<T> {
	
	// Member Variables
	private static final int DEFAULT_CAPACITY = 20; // Default size of the bag
	private final T[] bag;	// The array that will store the contents of the bag
	private int numEntries;	// The number of elements that are in this bag
	
	public ArrayBag(){
		
		this(DEFAULT_CAPACITY);
		
	}
	
	public ArrayBag(int desiredSize) {
		
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[])new Object[desiredSize]; // A temporary variable to suppress warning messages
		bag = tempBag;
		numEntries = 0;
		
	}
	
	/** Gets the current number of entries in this bag.
		 @return  The integer number of entries currently in the bag. */
	public int getCurrentSize() {
		
		return numEntries;
		
	}
	
	
	/** Sees whether this bag is empty.
		@return  True if the bag is empty, or false if not. */
	public boolean isEmpty() {
		
		return getCurrentSize() == 0;	// If there are no entries, then the bag is empty.
		
	}
	
	/** Adds a new entry to this bag.
	    @param newEntry  The object to be added as a new entry.
	    @return  True if the addition is successful, or false if not. */
	public boolean add(T newEntry) {
		
		boolean result = true; // What return type we will pass
		
		// Check if we can add anything to the bags
		if(isBagFull()) {
			
			// Bag is full
			
			result = false;
			
		}
		else
		{
			bag[numEntries] = newEntry; // Put the new entry at the end of the entries already in the bag
		
			numEntries++;
			
		}
		
		return result;
		
	}
	
	/**	Checks whether this bag has no empty space
	 *	@return True if this bag is full, or false if there are empty slots. 
	 **/
	private boolean isBagFull() {
		
		return numEntries > bag.length;
		
	}

	/** Removes one unspecified entry from this bag, if possible.
     * @return  Either the removed entry, if the removal.
     *          was successful, or null. */
	public T remove() {
		
		if (isEmpty()) {
			
			// The removal was unsuccessful, so return null
			return null;
			
		}
		else
		{
		
			// Get the last element in the bag so that we can return it to the user
			T lastElement = bag[numEntries - 1];
			
			// Remove the reference to the last item in our array so that we don't clutter it
			bag[numEntries - 1] = null;
			--numEntries;
			return lastElement;
			
		}
		
	}
   
	/** Removes one occurrence of a given entry from this bag.
       @param anEntry  The entry to be removed.
       @return  True if the removal was successful, or false if not. */
    public boolean remove(T anEntry) {
		
		if (!contains(anEntry)) {
			
			// The item we want to remove is not in the bag, so return null.
			return false;
			
		}
		else
		{
			// After the first for loop, this will contain the index of the item we are removing.
			int removed = 0;
			
			// Go through each element in the array, and setting the index of removed to the last one that matches
			for (int i = 0; i < numEntries; i++) {
				
				if (bag[i] == anEntry) {
					removed = i;
				}
	
			}

			// Go through the rest of the bag, moving everything back
			
		}
		
		// Reduce the number of entries
		numEntries--;
	}
	
	/** Removes all entries from this bag. */
	public void clear() {
		
	}
	
	/** Counts the number of times a given entry appears in this bag.
	 *  @param anEntry  The entry to be counted.
	 *  @return  The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry) {
		return -1000;
		
	}
	
	/** Tests whether this bag contains a given entry.
		 @param anEntry  The entry to locate.
		 @return  True if the bag contains anEntry, or false if not. */
	public boolean contains(T anEntry) {
		return false;
		
	}
   
	/** Retrieves all entries that are in this bag.
		 @return  A newly allocated array of all the entries in the bag.
                Note: If the bag is empty, the returned array is empty. */
	public T[] toArray() {
		return null;
		
	}
}
