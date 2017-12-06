import java.util.Iterator;import java.util.NoSuchElementException;/**   A queue implemented with a circular array! (This would be much better with doubly linked data)   @author Eli Zupke   @version 1.0 */public class DoubleLinkedDataQueue<T> implements QueueInterface<T>{		Node<T> front;		Node<T> back;			public DoubleLinkedDataQueue() {				// The single header node 		Node<T> header = new Node<T>(null);		header.setIsHeader(true);		front = header;		back = header;			}		/** Adds a new entry to the back of this queue.      @param newEntry  An object to be added. */	public void enqueue(T newEntry) {				// Create the node that will hold the new entry		Node<T> newNode = new Node<>(newEntry);				// Link this into the list, by pointing this node's pointer at the last element of the list.		newNode.setNextNode(back);				// Set the back of the list to the newNode		back = newNode;				// Re-make the circle by setting the header node's pointer to point at the back node.		front.setNextNode(back);			}	/** Removes and returns the entry at the front of this queue.      @return  The object at the front of the queue.       @throws  EmptyQueueException if the queue is empty before the operation. */	public T dequeue() {				if (isEmpty()){			throw new EmptyQueueException();		} else {						// First, we need to get the node that points at the header node			Node<T> first = back;			Node<T> second = front;						// Loop until the node we are pointing at is the header			while(!first.getNextNode().isHeader()) {				second = first;				first = first.getNextNode();			}						// Now first points at the header, and second points at first						// Get the data we will be returning			T returnedData = first.getData();						// Now we cut the first node out of the chain, because using linked data without dereferencing anything is a waste of resources.			second.setNextNode(front);						// If this was the last element in the queue, we need to reset the 'back' pointer to point at the header.			if (front.getNextNode() == front) {				back = front;			}						return returnedData;		}			}	/**  Retrieves the entry at the front of this queue.      @return  The object at the front of the queue.      @throws  EmptyQueueException if the queue is empty. */	public T getFront() {		if (isEmpty()){			throw new EmptyQueueException();		} else {			return getFrontNode().getData();		}			}	/**	 * Returns the node that is at the front of the queue.	 * @return The node at the front of the queue (that points at the header)	 */	private Node<T> getFrontNode() {			// First, we need to get the node that points at the header node		Node<T> first = back;				// Loop until the node we are pointing at is the header		while(!first.getNextNode().isHeader()) {			first = first.getNextNode();		}				// Return the data from that first node.		return first;			}	/** Detects whether this queue is empty.      @return  True if the queue is empty, or false otherwise. */	public boolean isEmpty() {				// If the front node is the empty node, then this queue is empty.		return (getFrontNode().isHeader());	}	/** Removes all entries from this queue. */	public void clear() {				// Set the header node to point at itself, dereferencing the rest of the queue		front.setNextNode(front);		back = front;	}		/**	 * Returns an iterator that iterates over the stack's values. Removal is not supported.	 * (This functionality was added during the final!)	 * @return An iterator of type T that iterates over the stack's values	 */	public Iterator<T> getIterator() {		return new DoubleLinkedDataQueueIterator();	}		private class DoubleLinkedDataQueueIterator implements Iterator<T> {		// The node we just gave		Node<T> nextNode = null;		// The node we are about to give		Node<T> curNode = back;				@Override		public boolean hasNext() {			return curNode != null;		}		@Override		public T next() {			if (!hasNext()) {				throw new NoSuchElementException();			}			nextNode = curNode;			curNode = curNode.getNextNode();			return nextNode.getData();		}		@Override		public void remove() {			throw new UnsupportedOperationException();		}		}}