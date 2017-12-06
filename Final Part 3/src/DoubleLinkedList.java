import java.util.Iterator;
import java.util.NoSuchElementException;


public class DoubleLinkedList<T> {

	// Point to the front and back of the list, respectively
	DoubleNode<T> front;
	DoubleNode<T> back;

	// Keep track of how long the list is for time's sake.
	int size = 0;

	public DoubleLinkedList() {

		// The single header node 
		front = null;
		back = null;

	}


	
	public void add(T item) {
		
		DoubleNode<T> newNode = new DoubleNode<>(item);

		//special case: the list is empty
		if (front == null) {
			front = newNode;
			back = newNode;
			size = 1;
			return;
		}
		// Link in the new node to the list
		back.setNextNode(newNode);
		newNode.setPreviousNode(back);
		back = newNode;
		
		// Increment the size counter
		size++;

	}

	
	public void add(T item, int index) throws IndexOutOfBoundsException {
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		DoubleNode<T> newNode = new DoubleNode<T>(item);
		
		if (isEmpty()) {
			// special case; add to start and end of list;
			front = newNode;
			back = newNode;
			
			
		} else if (index == 0) {
			// special case; add to start of list
			newNode.setNextNode(front);
			front.setPreviousNode(newNode);
			front = newNode;
			
		} else if (index == size) {
			// special case; add to end of list
			newNode.setPreviousNode(back);
			back.setNextNode(newNode);
			back = newNode;
			
		} else {
			
			// This is the node at the index where we will be adding.
			// its index will increase by one.
			DoubleNode<T> postNode = getNodeAtIndex(index);
			
			DoubleNode<T> preNode = postNode.getPreviousNode();
			
			// Link the new node into the list.
			preNode.setNextNode(newNode);
			newNode.setPreviousNode(preNode);
			
			postNode.setPreviousNode(newNode);
			newNode.setNextNode(postNode);
			
		}

		size++;
	}

	
	public T remove(int index) {
		ensureNotEmpty();
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		T data = null;
		
		if (size() == 1) {
			// special case: remove from front and back
			data = front.getData();
			front = null;
			back = null;
			
		} else if (index == 0) {
			// special case: remove from front
			data = front.getData();
			DoubleNode<T> newFront = front.getNextNode();
			
			newFront.setPreviousNode(null);
			front = newFront;
			
		} else if (index == size - 1) {
			// special case: remove from back
			data = back.getData();
			DoubleNode<T> newBack = back.getPreviousNode();
			
			newBack.setNextNode(null);
			back = newBack;
		} else {
			
			// This is the node at the index where we will be removing.
			DoubleNode<T> exNode = getNodeAtIndex(index);
			
			data = exNode.getData();
			
			// These are the nodes around the node we will be removing.
			DoubleNode<T> postNode = exNode.getNextNode();
			DoubleNode<T> preNode = exNode.getPreviousNode();
			
			// Test.printArray(toArray());			
			
			// Link the new node into the list.
			preNode.setNextNode(postNode);
			postNode.setPreviousNode(preNode);
			
		}

		size--;
		return data;
	}

	
	public T remove() {
		// TODO: replace with more efficient implementation
		return remove(size()-1);
	}

	
	public void clear() {

		DoubleNode<T> curNode = front;

		// Go through the list, and remove one of each node's connections
		while (curNode != null) {
			curNode.setPreviousNode(null);
			curNode = curNode.getNextNode();
		}

		// Now, every node is only connected one way, so when the ends are
		// de-referenced, the rest will be too.
		front = null;
		back = null;

		size = 0;
	}

	
	public T view(int index) {

		return getNodeAtIndex(index).getData();
	}

	
	public boolean contains(T item) {

		DoubleNode<T> curNode = front;

		// Go through each node. If we see the right data, return true.
		// If we get to the end, return false.
		while (curNode != null) {
			if (curNode.getData() == item) {
				return true;
			}
			curNode = curNode.getNextNode();
		}
		
		
		return false;
	}

	
	public int size() {
		return size;
	}

	
	public boolean isEmpty() {
		return (size == 0);
	}

	
	public T[] toArray() {

		// If the array is empty, just return null.
		if (isEmpty()) return null;

		@SuppressWarnings("unchecked")
		T[] outArray = (T[])new Object[size]; // Unchecked cast

		DoubleNode<T> curNode = front;

		// Go through the list and copy the contents of each node to the array
		for(int i = 0; i < size; i++) {
			outArray[i] = curNode.getData();
			curNode = curNode.getNextNode();
		}

		if (curNode != null) {
			throw new IllegalStateException("Size of list wasn't correctly maintained!");
		}

		return outArray;
	}

	
	public T replace(int index, T newItem) {
		
		// Find the node that contains the data we are replacing
		DoubleNode<T> replacedNode = getNodeAtIndex(index);
		
		// Grab the data, so that we can return it later
		T data = replacedNode.getData();
		
		// Replace the data in the node and return the data
		replacedNode.setData(newItem);
		return data;
	}

	/**
	 * Returns the node at the given index
	 * @param index The index to find the node at
	 * @return The given index
	 * @throws IndexOutOfBoundsException if the index is not in the list.
	 */
	private DoubleNode<T> getNodeAtIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		DoubleNode<T> curNode = front;
		// Go through the array until we get to the node at the given index
		for(int i = 0; i < index; i++) {
			if (curNode == null) {
				// The index was too large!
				throw new IndexOutOfBoundsException();
			}
			curNode = curNode.getNextNode();
		}
		return curNode;
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
	

	/**
	 * Returns an iterator that iterates over the stack's values. Removal is not supported.
	 * (This functionality was added during the final!)
	 * @return An iterator of type T that iterates over the stack's values
	 */
	public Iterator<T> getIterator() {
		return new thisIt();
	}

	
	private class thisIt implements Iterator<T> {

		// The node we just gave
		DoubleNode<T> prevNode = null;
		// The node we are about to give
		DoubleNode<T> curNode = front;


		@Override
		public boolean hasNext() {
			return curNode != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prevNode = curNode;
			curNode = curNode.getNextNode();
			return prevNode.getData();

		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}	
	}


}
