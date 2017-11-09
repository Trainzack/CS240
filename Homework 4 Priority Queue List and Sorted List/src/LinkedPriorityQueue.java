/**
 * An implementation of the ADT Priority Queue using singly linked data.
 *  
 * @author Eli Zupke
 * @param <T> The type of data to store in this queue. T must extend Comparable. 
 */
public class LinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {

	// This variable always points at the back of the queue
	private ComparableNode<T> back;

	@Override
	/** Adds a new entry to the back of this queue.
    @param newEntry  An object to be added. */
	public void add(T newEntry) {//TODO FIX THIS

		// Create the new node that we will add
		ComparableNode<T> addedNode = new ComparableNode<T>(newEntry);

		ComparableNode<T> prevNode = null;
		ComparableNode<T> nextNode = back;
		// We need to loop through the queue to find the first node that is better than this one.
		while(nextNode != null
				&& nextNode.compareTo(addedNode) < 0) {
			// We are still in the middle of the queue; we must go further
			prevNode = nextNode;
			nextNode = nextNode.getNextNode();
		}

		// We have found the front of the queue
		// We remove this node from the chain by setting the previous node's next node to null
		if (prevNode == null) {
			// This is a special case; we must set top to null
			addedNode.setNextNode(back);
			back = addedNode;
		} else {
			addedNode.setNextNode(nextNode);
			prevNode.setNextNode(addedNode);
		}

	}

	@Override
	/** Removes and returns the entry at the front of this queue.
    @return  The object at the front of the queue. 
    @throws  EmptyQueueException if the queue is empty before the operation. */
	public T remove() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			ComparableNode<T> prevNode = null;
			ComparableNode<T> thisNode = back;

			// We need to loop through the entire queue to find the front.
			while(thisNode.getNextNode() != null) {
				// We are still in the middle of the queue; we must go further
				prevNode = thisNode;
				thisNode = thisNode.getNextNode();
			}

			// We have found the front of the queue
			// We remove this node from the chain by setting the previous node's next node to null
			if (prevNode == null) {
				// This is a special case; we must set top to null
				back = null;
			} else {
				prevNode.setNextNode(null);
			}

			return thisNode.getData();
		}
	}

	@Override
	/**  Retrieves the entry at the front of this queue.
    @return  The object at the front of the queue.
    @throws  EmptyQueueException if the queue is empty. */
	public T peek() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			ComparableNode<T> thisNode = back;

			// Loop through the entire queue
			while(true) {
				if (thisNode.getNextNode() == null) {
					// We have found the front of the queue
					return thisNode.getData();
				} else {
					// We must continue to the next node
					thisNode = thisNode.getNextNode();
				}
			}
		}
	}
	@Override
	/** Detects whether this queue is empty.
    @return  True if the queue is empty, or false otherwise. */
	public boolean isEmpty() {

		return (back == null);

	}

	@Override
	/** Gets the size of this priority queue.
    @return  The number of entries currently in the priority queue. */
	public int getSize() {
		int size = 0;
		ComparableNode<T> curNode = back;

		// We traverse the linked data until we come across null, which means we reached the front. 
		while (curNode != null) {
			size++;
			curNode = curNode.getNextNode();
		}
		return size;
	}

	@Override
	/** Removes all entries from this queue. */
	public void clear() {
		// We can clear the queue by dereferencing the top node.
		back = null;
	}

}
