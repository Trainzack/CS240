package cs240;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implements the Sorted Dictionary ADT using a fixed size array. Keys in this dictionary are sorted ascendingly.
 * @author Eli Zupke
 *
 * @param <K> The type that will be used as keys in this dictionary
 * @param <V> The type that will be used as values in this dictionary
 */
public class SortedDictionaryStaticArray<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {

	// Used to keep track of where the last element of the dictionary is stored.
	private int end;
	private int capacity;

	// These two arrays hold the keys and the values. The corresponding value of each key will be the entry in the value array with the same index.
	private K[] keyArray;
	private V[] valueArray;

	/**
	 * Creates a new sorted dictionary via static array.
	 * @param capacity The maximum number of key-value pairs in this dictionary.
	 */
	public SortedDictionaryStaticArray(int capacity) {

		this.capacity = capacity;

		// The dictionary starts at zero, so start the end variable pointing at -1 (empty)
		end = -1;

		// Instantiate the arrays for both the keys and values.
		
		@SuppressWarnings("unchecked")
		K[] tempKeyArray = (K[])new Comparable[capacity]; // Unchecked cast
		keyArray = tempKeyArray;

		@SuppressWarnings("unchecked")
		V[] tempValueArray = (V[])new Comparable[capacity]; // Unchecked cast
		valueArray = tempValueArray;

	}

	@Override
	public V add(K key, V value) {
		// These store keys and values in the event that we need to add the key in the middle of the array.
		K curKey = null;
		V curValue = null;

		// Go down the array until we get to a value greater than the one we're adding, then move the rest down
		int i = 0;
		// System.out.println("Inserting " + value.toString());
		//Test.printArray(keyArray); Test.printArray(valueArray);
		
		for (; i < getSize() + 1; i++) {
			if (keyArray[i] == key) {
				// We already have the key, it seems.

				// Hold on to the old value, replace it with the new one, then return it.
				V returnValue = valueArray[i];
				valueArray[i] = value;
				
				return returnValue;
			} else if (keyArray[i] == null) {
				// System.out.println("TEST END");
				// We got to the end of the array, so let's place it at the end!
				valueArray[i] = value;
				keyArray[i] = key;
				end++;
				// We've added the element, so leave.
				return null;
			} else if (keyArray[i].compareTo(key) > 0) {
				// System.out.println("TEST GREATER");
				// We have found where to place our key, so let's do it!
				K tempKey = keyArray[i];
				V tempValue = valueArray[i];

				keyArray[i] = key;
				valueArray[i] = value;

				curKey = tempKey;
				curValue = tempValue;

				// Since we now know that we need to expand the array, but don't know whether we have enough room, let's check  
				ensureCapacity();
				end++;
				break;
			}
		}
		// If we get here, then we know that we went through the last else if,
		// and we still need to move the remaining values over one index.

		for (i += 1; i < getSize(); i++) {
			// Move the next group of values 
			K tempKey = keyArray[i];
			V tempValue = valueArray[i];

			keyArray[i] = curKey;
			valueArray[i] = curValue;

			curKey = tempKey;
			curValue = tempValue;
		}

		return null;
	}

	@Override
	public V remove(K key) {
		
		V value = null;
		
		// Declare the index variable outside the loop, so we can continue where we left off in the next one
		int i = 0;
		
		// Find the key, store its value, and stop the loop.
		// If it gets to the end, then the next loop will not be entered, and we will return null.
		for (; i <= end; i++) {
			if (keyArray[i] == key) {
				value = valueArray[i];
				break;
			}
		}
		
		// If we didn't find the key, then we can stop now
		if (value == null) {
			return null;
		}
		
		// Otherwise, move the rest of the values back.		
		for (; i < end; i++) {
			// Move the next group of values 
			keyArray[i] = keyArray[i+1];
			valueArray[i] = valueArray[i+1];
		}
		keyArray[end] = null;
		valueArray[end] = null;
		// Finally, reduce the end index by one.
		end--;
		
		return value;
	}

	@Override
	public V getValue(K key) {

		// Sequential search the key array for the key we are looking for. 
		for (int i = 0; i < capacity; i++) {
			if (keyArray[i] == key) {
				// We found what we're looking for.
				return valueArray[i];
			}
		}
		// We couldn't find the key we were looking for.
		return null;
	}

	@Override
	public boolean contains(K key) {

		// Sequential search the key array for the key we are looking for. 
		for (int i = 0; i < capacity; i++) {
			if (keyArray[i] == key) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return new StaticArrayIterator<K>(true);
	}

	@Override
	public Iterator<V> getValueIterator() {
		
		return new StaticArrayIterator<V>(false);
	}
	
	private class StaticArrayIterator<I> implements Iterator<I> {

		// Whether this is an iterator of keys (if true) or values (if false)
		boolean key;
		
		// index is the index of the value we just gave.
		private int index = -1;
		
		// whether there is an element we can remove.
		boolean canRemove = false;
		
		StaticArrayIterator(boolean _key) {
			super();
			key = _key;
		}
		
		@Override
		public boolean hasNext() {
			return index < end;
		}

		// Because I will always equal K or V, and we know which one it will equal, we can do this cast.
		@SuppressWarnings("unchecked")
		@Override
		public I next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			index++;
			canRemove = true;
			if (key) {
				return (I)keyArray[index];
			} else {
				return (I)valueArray[index];
			}
			
		}

		@Override
		public void remove() {
			if (!canRemove) {
				throw new IllegalStateException();
			}
			canRemove = false;
			
			SortedDictionaryStaticArray.this.remove(keyArray[index]);
			// Because we removed an element, we need to move our index backwards
			index--;
			
		}	
	}
	

	@Override
	public boolean isEmpty() {
		return getSize() == 0;
	}

	@Override
	public int getSize() {
		// The size of the dictionary is always equal to the position of the end index plus 1.
		return end + 1;
	}

	@Override
	public void clear() {

		// Dereference everything in both arrays
		for (int i = 0; i < capacity; i++) {
			keyArray[i] = null;
			valueArray[i] = null;
		}

		// Move the end index back to before the start of the array.
		end = -1;
	}
	
	/**
	 * Test to see if we have room to add an element.
	 * Throws an IndexOutOfBoundsException if the array is full.
	 */
	private void ensureCapacity() {
		if (end + 1 >= capacity) {
			throw new IndexOutOfBoundsException("Max array size reached!");
		}
	}
}
