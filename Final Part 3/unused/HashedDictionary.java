

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashedDictionary<K extends Comparable<K>, V> implements DictionaryInterface<K, V> {

	// TODO: Fix iterators
	
	private static final int DEFAULT_SIZE = 10;

	private DictNode<K, V>[] hashTable;
	/**
	 * This variable represents the amount of items being store in the HashTable
	 * NOT the length of the hashTable; rather, the number of elements currently being stored
	 */
	private int size;

	public HashedDictionary() {
		this(DEFAULT_SIZE);
	}

	public HashedDictionary(int size) {

		@SuppressWarnings("unchecked")
		DictNode<K, V>[] temp = (DictNode<K, V>[]) new DictNode[size];

		hashTable = temp;
		this.size = 0;
	}

	@Override
	public V add (K key, V value) {
		// Test.printArray(hashTable);
		int index = key.hashCode() % hashTable.length;
		index = probe(index, key);

		if(index == -1) // If hashtable is full, expand size
		{
			rehash();
		}

		if ((key == null) || (value == null))
		{
			throw new IllegalArgumentException();
		}
		else
		{
			V oldValue = null;

			if(index != -1) // If hashTable is not full
			{
				// If key is not found
				if( (hashTable[index] == null) || (hashTable[index].flag == true) )
				{
					hashTable[index] = new DictNode<K, V>(key, value);
					size++;
				}
				else // If key is found
				{
					oldValue = hashTable[index].value;
					hashTable[index].value = value;
				}
			} // end if
			// Returns null if new entry was added or no entry was added (hashtable was full)
			// Returns the old value of key that was found
			return oldValue;
		} // end if
	} // end add

	@Override
	public V remove (K key) {
		V result = null;

		// Get hash index of key
		int index = key.hashCode() % hashTable.length;
		// Probes for the specific location of key, then sets index.
		index = locate(index, key); // -1 if key is not found. Positive # if key is located.

		if(index != -1) // If key is found
		{
			result = hashTable[index].value; // Set the value in result
			hashTable[index].key = null;
			hashTable[index].flag = true; // Set flag to show that entry is deleted
			size--;
		}
		// Returns null if key wasn't found
		// Returns the value of the key if it was found
		return result;
	}

	@Override
	public V getValue (K key) {
		V result = null;

		// Get hash index of key
		int index = key.hashCode() % hashTable.length;
		// Probes for the specific location of key, then sets index.
		index = locate(index, key); // -1 if key is not found. Positive # if key is located.

		if(index != -1) {
			return hashTable[index].value; // If key is found, get value
		}
		// If key is found, the key's value is returned.
		// If key is not found, null is returned
		return result;
	}

	@Override
	public boolean contains (K key) {
		boolean result = false;

		// Get hash index of key
		int index = key.hashCode() % hashTable.length;
		// Probes for the specific location of key, then sets index.
		index = locate(index, key); // -1 if key is not found. Positive # if key is located.

		if(index != -1) {
			result = true; // If key is found, set result as true
		}
		// If key is found, return true
		// If key is not found, return false
		return result;
	}

	@Override
	public Iterator<K> getKeyIterator () {

		// We are iterating over keys, so the parameter should be true.
		return new DictIterator<>(true);
	}

	@Override
	public Iterator<V> getValueIterator () {

		// We are iterating over values, so the parameter should be false.
		return new DictIterator<>(false);
	}

	/**
	 * Iterates over either keys or values. It will go straight from the lowest index to the largest index, regardless of the keys it goes through.
	 * @author Eli Zupke
	 *
	 * @param <I> The type of thing that we are iterating over (must equal K if key is true, or V if key is false!)
	 */
	private class DictIterator<I> implements Iterator<I> {

		// The location in the array of the item we will return next
		//	Or -1 if there is no next element.
		private int curIndex;

		// Whether we are iterating over keys (true) or values (false).
		private boolean iteratorTypeIsKey;

		/**
		 * @param key Whether we are iterating over keys (true) or values (false).
		 */
		public DictIterator(boolean key) {

			iteratorTypeIsKey = key;
			
			// Set curIndex to -1, so that findNextNode() doesn't skip over anything in hashTable[0].
			curIndex = -1;
			findNextNode();

		}

		@Override
		public boolean hasNext() {
			
			// If we don't have a next element, then findNextNode() will have set curNode to -1.
			return curIndex > 0;
		}

		@Override
		public I next() {

			// If we don't have a next element, we can't return it!
			if (!hasNext()) {
				throw new NoSuchElementException();
			} else {

				// Depending on what type of iterator this is, either return the key or value of the node.
				if (iteratorTypeIsKey) {
					// This cast is fine because I will always equal K (the type of key) if iteratorTypeIsKey is true.	
					@SuppressWarnings("unchecked")
					I output = (I) hashTable[curIndex].key;
					
					// Now that we have this node's key, find the next.
					findNextNode();
					return output;
				} else {
					// This cast is fine because I will always equal V (the type of val) if iteratorTypeIsKey is false.
					@SuppressWarnings("unchecked")
					I output = (I) hashTable[curIndex].value;

					// Now that we have this node's value, find the next.
					findNextNode();
					return output;
				}
			}
		}

		@Override
		public void remove() {
			// Remove is not implemented.
			throw new UnsupportedOperationException();
		}

		/**
		 * Finds the next empty node 
		 */
		private void findNextNode() {
			curIndex++;

			// Loop through the hash table until we reach the end, or reach a non-empty node.
			while (curIndex < hashTable.length && 
					(hashTable[curIndex] == null || !hashTable[curIndex].flag)) {
				curIndex++;
			}

			// If we have reached the end, then set curIndex to -1 to help us notice that.
			if (curIndex >= hashTable.length) {
				curIndex = -1;
			}
		}

	}

	@Override
	public boolean isEmpty () {
		return size == 0;
	}

	@Override
	public int getSize () {
		return size;
	}

	@Override
	public void clear () {
		@SuppressWarnings("unchecked")
		DictNode<K, V>[] temp = (DictNode<K, V>[]) new Object[size];

		hashTable = temp;
		this.size = 0;
	}

	/**
	 * Creates a new array hash-table with more than double the capacity,
	 * Rehashes all of the old nodes into the new array,
	 * Then replaces the old array with the new.
	 */
	private void rehash() {

		System.out.println(size);
		// Find the new size of the table (smallest prime > tableLength * 2)
		int newSize = getNextPrime(hashTable.length * 2);

		// Allocate the memory for the new table
		@SuppressWarnings("unchecked") // This is fine because the new array is full of nulls;
		DictNode<K, V>[] newTable = (DictNode<K, V>[]) new DictNode[newSize];

		// Make hashTable equal to the new table, but first store the old array somewhere
		DictNode<K, V>[] oldTable = hashTable;
		hashTable = newTable;
		size = 0;

		for (int i = 0; i < oldTable.length; i++) {

			// If there is a node here and it's not empty
			if (oldTable[i] != null && !oldTable[i].flag) {
				// Add the node's key-value pair to the new table
				DictNode<K, V> oldNode = oldTable[i];
				add(oldNode.key, oldNode.value);
				// (This would be better if it used the same node)
			}

		}
		System.out.println(size);


	}

	/**
	 * Returns the smallest prime number that is greater or equal to the provided number.
	 * @param min Specifies the smallest acceptable prime number
	 * @return The smallest prime number that is larger than or equal to min.
	 */
	private static int getNextPrime(int min) {

		// Take care of a few smaller prime numbers first, to avoid edge-cases.
		//	(I doubt we will ever need primes this small, but w/e)
		if (min < 2) {
			return 2;
		} else if (min < 5) {
			return 5;
		}

		// Store the potential prime numbers here.
		int primeCandidate = min;

		// If this number is even, make it odd.
		if (primeCandidate % 2 == 0) primeCandidate++;

		// Loop through values for primeCandidate until we find one that is actually prime
		while (true) {

			boolean isPrime = true;

			// Loop through all potential factors (they will all be odd)
			for (int f = 3; f < primeCandidate; f+=2) {
				if (primeCandidate % f == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				return primeCandidate;
			} else {
				// Increment p to the next odd integer.
				primeCandidate += 2;
			}
		}

	}

	private int locate(int index, K key) {
		boolean found = false; // False if key is not there, true if key is found

		// Checks if key is in first hash index.
		int firstIndex = index;

		if(hashTable[index] != null && key.equals(hashTable[index].key)) {
			found = true; // Key is found
		}
		// If key is not in first hash index, search through other indexes
		else {
			index = (index + 1) % hashTable.length; // Go to second index

			// Look for key starting from second index.
			// Loop stops when key is found.
			// Also stops loop when key is not found and index returns to first Index
			while( !found && (hashTable[index] != null) && (index != firstIndex) )
			{
				if(key.equals(hashTable[index].key)) {
					found = true; // Key is found
				}
				else {
					index = (index + 1) % hashTable.length; // Next index
				}
			} // end while
		} // end else

		// Either key or null is found at hashTable[index]
		int result = -1;
		if(found) {
			result = index; // If key was found, result is set as the index
		}
		// If key isn't found, -1 is returned
		//If key is found, the index where the key's index is returned.
		return result;
	} // end locate

	private int probe(int index, K key)
	{
		boolean found = false;
		int freeSpace = -1;

		// Precondition: Exisiting entries have flag set to false. Deleted entries have flag set to true.

		// Checks if first hash index is empty, flagged, or key is same.
		int firstIndex = index;
		if( (hashTable[index] == null) || (hashTable[index].flag == true) || (key.equals(hashTable[index].key)) )
		{
			freeSpace = index; // Get location of flagged index.
			found = true;
		}
		// If first hash index is not empty, search through other indexes
		else
		{
			index = (index + 1) % hashTable.length; // Go to second index
		}

		// Search starting from second index.
		// Loop stops when flag is found, reaches end of sequence, or hashtable is full
		while( !found && (hashTable[index] != null) && (index != firstIndex) )
		{
			// Checks if index has a flag or if key is a same.
			if( (key.equals(hashTable[index].key)) || (hashTable[index].flag == true) )
			{
				freeSpace = index;
				found = true;
			}
			else
			{
				index = (index + 1) % hashTable.length; // Next index
			}
		} // end while

		if(index == firstIndex) // Check if hashTable is full
		{
			index = -1;
		}

		if(found && (freeSpace != -1) ) // Return flagged index
		{
			return freeSpace;
		}
		else // Return last index of sequence if there is space, or -1 if hashtable is full.
		{
			return index;
		}
	} // end probe
}
