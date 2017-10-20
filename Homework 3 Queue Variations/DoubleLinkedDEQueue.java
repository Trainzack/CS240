/**
* A class implementing an ADT Queue using doubly linked nodes
* @author Eli Zupke
* @version 1.0
**/

	public class DoubleLinkedDEQueue<T> implements DequeInterface<T> {

	private DLNode<T> head;
	private DLNode<T> tail;

	public DoubleLinkedDEQueue()
	{
		head = null;
		tail = null;
	}
	

	   /** Adds a new entry to the front/back of this dequeue.
	   @param newEntry  An object to be added. */
	public void addToFront(T newEntry) {
		
		DLNode<T> newNode = new DLNode<T>(newEntry);

		
		if (!isEmpty()) {
			// If the DEQueue is not empty, then there is a node at head, which means we need to update its and the new node's pointers.
			head.setNextNode(newNode);
			newNode.setPreviousNode(head);
		} else {
			// If we ARE empty, then this node will be the head and the tail.
			tail = newNode;
		}
		head = newNode;
	}

	public void addToBack(T newEntry) {
		
		DLNode<T> newNode = new DLNode<T>(newEntry);
	   
	   
		if (!isEmpty()) {
			// If the DEQueue is not empty, then there is a node at tail, which means we need to update its and the new node's pointers.
			tail.setPreviousNode(newNode);
			newNode.setNextNode(tail);
		} else {
			// If we ARE empty, then this node will be the head and the tail.
			head = newNode;
		}
		tail = newNode;
	}

	/** Removes and returns the front/back entry of this dequeue.
	   @return  The object at the front/back of the dequeue.
	   @throws  EmptyQueueException if the dequeue is empty before the operation. */
	public T removeFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			// Get the data at the front
			T outData = head.getData();
			
			// Delete data in front node (security reasons)
			head.setData(null);
			
			// Move the front pointer to the correct position
			head = head.getPreviousNode();
			
			// Remove the new front node's connection (if it exists)
			if (head != null) {
				head.setNextNode(null);
			}
			
			// Return the data
			return outData;
		}
	}
	public T removeBack() {
		
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			
			// Get the data at the tail
			T outData = tail.getData();
			
			// Delete data in tail node (security reasons)
			tail.setData(null);
			
			// Move the tail pointer to the correct position
			tail = tail.getNextNode();
			
			// Remove the new tail node's connection (if it exists)
			if (tail != null) {
				tail.setPreviousNode(null);
			}
			
			// Return the data
			return outData;
		}
	}

	/** Retrieves the front/back entry of this dequeue.
	   @return  The object at the front/back of the dequeue.
	   @throws  EmptyQueueException if the dequeue is empty before the operation. */
	public T getFront() {
		
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return head.getData();
		}
	}
	
	public T getBack() {
		
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return tail.getData();
		}
	}

	/*  Detects whether this dequeue is empty.
	   @return  True if the queue is empty, or false otherwise. */
	public boolean isEmpty() {
		return head == null;
	}

	/*  Removes all entries from this dequeue. */
	public void clear() {
		// Loop through each node and kill it (we can't just dereference it)
		if (!isEmpty()) {
			DLNode<T> curNode = tail;
			tail = null;
			while (curNode != null) {
				curNode.setPreviousNode(null);
				curNode.setData(null);
				curNode = curNode.getNextNode();
			}
			head = null;
		}
	}

}

