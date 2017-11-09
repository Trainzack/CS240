
public class FixedArrayList<T> implements ListInterface<T> {

	private T[] array;
	
	// This number is always the index of the element with the highest index.
	int top = -1;
	
	
	public FixedArrayList(int capacity) {
		
        @SuppressWarnings("unchecked")
        T[] tempArray = (T[])new Object[capacity]; // Unchecked cast
		array = tempArray;
	}
	
	
	@Override
	public void add(T item) {
		// Check to see if we have space.
		ensureCapacity();
		top++;
		array[top] = item;
		
	}

	@Override
	public void add(T item, int index) throws IndexOutOfBoundsException {

		ensureCapacity();
		ensureIndexInAddingBounds(index);
		
		//Add the item at the right index, and move everything after it down.
		// The element we are currently moving
		T moved = item;
		for (int i = index; i <= top + 1; i++) {
			T temp = array[i];
			array[i] = moved;
			moved = temp;
			// Test.printArray(this.toArray());
		}
		// Now that we've added an item, the top counter should move up!
		top++;
		// Test.printArray(this.toArray());
	}

	@Override
	public T remove(int index) {
		ensureNotEmpty();
		ensureIndexInBounds(index);
		// Move everything from the top down one space. By the end, we've removed an item.
		// The element we are currently moving
		T moved = null;
		for (int i = top; i >= index; i--) {
			T temp = array[i];
			array[i] = moved;
			moved = temp;
		}
		// We've removed something, so the top index is lower now.
		top--;
		// By the end of this, the item we want should be in moved.
		return moved;
	}
	
	@Override
	public T remove() {
		return remove(top);
	}

	@Override
	public void clear() {
		for (int i = 0; i <= top; i++) {
			array[i] = null;
		}
		top = -1;
		
	}

	@Override
	public T view(int index) {
		ensureNotEmpty();
		ensureIndexInBounds(index);
		return array[index];
	}

	@Override
	public boolean contains(T item) {
		// Search through the array to see if item is in the array.
		for (int i = 0; i <= top; i++) {
			if (array[i] == item) return true;
		}
		return false;
	}

	@Override
	public int size() {
		return top + 1;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public T[] toArray() {
		if (isEmpty()) {
			return null;
		} else {
			// Create an array of the right size
	        @SuppressWarnings("unchecked")
	        T[] outArray = (T[])new Object[top + 1]; // Unchecked cast
	        
	        // Copy the contents of the array into the new array
	        for (int i = 0; i < outArray.length; i++) {
	        	outArray[i] = array[i];
	        }
	        return outArray;
		}
	}

	@Override
	public T replace(int index, T newItem) {
		
		// Just swap the two things. Easy.
		T output = array[index];
		array[index] = newItem;
		return output;
	}
	
	/**
	 * Test to see if we have room to add an element.
	 * Throws an IndexOutOfBoundsException if the array is full.
	 */
	private void ensureCapacity() {
		if (top + 1 >= array.length) {
			throw new IndexOutOfBoundsException("Max array size reached!");
		}
	}
	
	/**
	 * Test to see if an index is in the bounds of the array,
	 * and that there is an element at that index.
	 * Throws an IndexOutOfBoundsException if not.
	 * @param index The index to test
	 */
	private void ensureIndexInBounds(int index) {
		if (index > top || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	
	/**
	 * Test to see if an index is in the bounds of the array,
	 * and that there is an element at that index, or one before it.
	 * This is used to test if we can add an element at a given index.
	 * Throws an IndexOutOfBoundsException if not.
	 * @param index The index to test
	 */
	private void ensureIndexInAddingBounds(int index) {
		if (index > top + 1 || index >= array.length || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Test to see if the queue is not empty. 
	 * If it is empty, throw an EmptyQueueException
	 */
	private void ensureNotEmpty() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
	}

}
