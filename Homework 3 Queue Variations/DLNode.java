/**
* A class implementing an ADT Queue using doubly linked nodes
* @author Eli Zupke
* @version 1.0
**/

public class DLNode<T> {
	
	// The next node in the chain
	private DLNode<T> nextNode = null;
	
	// The previous node in the chain
	private DLNode<T> previousNode = null;
	
	// The data we are holding
	private T data = null;

	public DLNode(T data) {
		super();
		this.data = data;
	}
	
	/*
	 * @return the data in this node
	 **/
	public T getData() {
		return data;
	}
	
	/*
	 * Sets the data in this node
	 */
	public void setData(T data) {
		this.data = data;
	}

	/*
	 * @return the next node
	 **/
	public DLNode<T> getNextNode() {
		return nextNode;
	}


	/*
	 * Set the next node to the provided node
	 **/
	public void setNextNode(DLNode<T> nextNode) {
		this.nextNode = nextNode;
	}

	
	/*
	 * @return the previous node
	 **/
	public DLNode<T> getPreviousNode() {
		return previousNode;
	}

	/*
	 * Set the previous node to the provided node.
	 **/
	public void setPreviousNode(DLNode<T> previousNode) {
		this.previousNode = previousNode;
	}
	
}
