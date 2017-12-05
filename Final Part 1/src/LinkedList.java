
public class LinkedList<T> implements ListInterface<T> {

	// Points to the front of the list
	protected Node<T> front;

	// Keep track of how big the list is to save time
	private int size;

	public LinkedList() {
		front = null;
		size = 0;
	}

	@Override
	public void add(T item) {

		Node<T> newNode = new Node<>(item);

		//special case: the list is empty
		if (front == null) {
			front = newNode;
			size = 1;
			return;
		}

		Node<T> curNode = front;

		// Go through each node, until we get to the end.
		while (curNode.getNextNode() != null) {
			curNode = curNode.getNextNode();
		}

		// Link in the new node to the list
		curNode.setNextNode(newNode);

		// Increment the size counter
		size++;

	}

	@Override
	public void add(T item, int index) throws IndexOutOfBoundsException {
		
		if (index < 0) throw new IndexOutOfBoundsException();
		
		Node<T> newNode = new Node<T>(item);
		
		// Special case: The index is 0
		if (index == 0) {
			// set the new node's next node to front, and front to newNode.
			newNode.setNextNode(front);
			front = newNode;
			
		} else {
			Node<T> prevNode = null;
			Node<T> curNode = front;
			
			// Find the node at that index, and set curNode to it
			for(int i = 0; i < index; i++) {
				if (curNode == null) {
					// The index was too large!
					throw new IndexOutOfBoundsException();
				}
				prevNode = curNode;
				curNode = curNode.getNextNode();
			}	
			// Link newNode in-between prevNode and newNode
			prevNode.setNextNode(newNode);
			if (curNode != null) {
				newNode.setNextNode(curNode);	
			}
		}
		
		
		size++;
	}

	@Override
	public T remove(int index) {
		ensureNotEmpty();
		if (index < 0) throw new IndexOutOfBoundsException();

		T data = null;
		
		// Special case: The index is 0
		if (index == 0) {
			// Remove the front node, and keep its data
			data = front.getData();
			front = front.getNextNode();
			
		} else {
			Node<T> prevNode = null;
			Node<T> curNode = front;
			
			// Find the node at that index, and set curNode to it
			for(int i = 0; i < index; i++) {
				if (curNode == null) {
					// The index was too large!
					throw new IndexOutOfBoundsException();
				}
				prevNode = curNode;
				curNode = curNode.getNextNode();
			}	

			// We will remove curNode, so keep its data
			data = curNode.getData();
			
			// Set (the node before curNode)'s nextNode to curNode's NextNode.
			prevNode.setNextNode(curNode.getNextNode());
		}
		
		// Adjust the known size of the list
		size--;
		return data;
	}

	@Override
	public T remove() {
		// TODO: replace with more efficient implementation;
		return remove(size()-1);
	}

	@Override
	public void clear() {
		// De-reference the entire list
		front = null;
		size = 0;
	}

	@Override
	public T view(int index) {
		if (index < 0) throw new IndexOutOfBoundsException();
		// Find the node at that index, and set curNode to it
		Node<T> curNode = front;
		
		for(int i = 0; i < index; i++) {
			if (curNode == null) {
				// The index was too large!
				throw new IndexOutOfBoundsException();
			}
			curNode = curNode.getNextNode();
		}	
		return curNode.getData();
	}

	@Override
	public boolean contains(T item) {

		Node<T> curNode = front;

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

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {

		return front == null;
	}

	@Override
	public T[] toArray() {

		// If the list is empty, just return null.
		if (isEmpty()) return null;

		@SuppressWarnings("unchecked")
		T[] outArray = (T[])new Object[size]; // Unchecked cast

		Node<T> curNode = front;

		for(int i = 0; i < size; i++) {
			outArray[i] = curNode.getData();
			curNode = curNode.getNextNode();
		}

		if (curNode != null) {
			throw new IllegalStateException("Size of list wasn't correctly maintained!");
		}

		return outArray;
	}

	@Override
	public T replace(int index, T newItem) {

		if (index < 0) throw new IndexOutOfBoundsException();

		Node<T> curNode = front;

		for(int i = 0; i < index; i++) {
			if (curNode == null) {
				// The index was too large!
				throw new IndexOutOfBoundsException();
			}
			curNode = curNode.getNextNode();
		}

		// Swap the data
		T data = curNode.getData();
		curNode.setData(newItem);

		return data;
	}

	/**
	 * Test to see if the queue is not empty. 
	 * If it is empty, throw an EmptyQueueException
	 */
	private void ensureNotEmpty() {
		if (isEmpty()) {
			throw new EmptyListException();
		}
	}

}
