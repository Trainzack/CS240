/**   An ADT DEQueue implemented with singly linked data   @author Eli Zupke   @version 1.0 */public class SingleLinkedDEQueue<T> implements DequeInterface<T>{	// This is the node at the front, which points at the node behind it, et. cetera.	private Node<T> head;		public SingleLinkedDEQueue() {		head = null;	}		/** Adds a new entry to the front/back of this dequeue.	   @param newEntry  An object to be added. */	public void addToFront(T newEntry) {		Node<T> newNode = new Node<T>(newEntry);				newNode.setNextNode(head);		head = newNode;			}	public void addToBack(T newEntry) {		Node<T> newNode = new Node<T>(newEntry);				if (head == null) {			head = newNode; 		} else {			Node<T> currentNode = head;						while(currentNode.getNextNode() != null) {				currentNode = currentNode.getNextNode();			}						// now currentNode is pointing to the back of the dequeue			currentNode.setNextNode(newNode); 		}	}	/** Removes and returns the front/back entry of this dequeue.	   @return  The object at the front/back of the dequeue.	   @throws  EmptyQueueException if the dequeue is empty before the operation. */	public T removeFront() {		if (isEmpty()) {			throw new EmptyQueueException();		} else {			T returnedData = head.getData();			head = head.getNextNode();			return returnedData;		}			}	public T removeBack() {		if (isEmpty()) {			throw new EmptyQueueException();		} else {						Node<T> previousNode = null;			Node<T> currentNode = head;						while(currentNode.getNextNode() != null) {				previousNode = currentNode;				currentNode = currentNode.getNextNode();			}			T returnedData = currentNode.getData();						// Set the pointer pointing at the back to null, either the second to last node or the 'head' pointer.			if (previousNode != null) {				previousNode.setNextNode(null);			} else {				head = null;			}						return returnedData;		}			}	/** Retrieves the front/back entry of this dequeue.	   @return  The object at the front/back of the dequeue.	   @throws  EmptyQueueException if the dequeue is empty before the operation. */	public T getFront() {		if (isEmpty()) {			throw new EmptyQueueException();		} else {			return head.getData();		}	}	public T getBack() {		if (isEmpty()) {			throw new EmptyQueueException();		} else {						Node<T> currentNode = head;						// Go to the end of the queue			while(currentNode.getNextNode() != null) {				currentNode = currentNode.getNextNode();			}						return currentNode.getData();		}			}	/*  Detects whether this dequeue is empty.	   @return  True if the queue is empty, or false otherwise. */	public boolean isEmpty() {		return head == null;	}	/*  Removes all entries from this dequeue. */	public void clear() {		head = null;	}}