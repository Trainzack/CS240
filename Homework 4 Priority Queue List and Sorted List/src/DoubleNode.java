/**
 * 
 * @author Eli Zupke
 *
 * @param <T> The type of data we want to store in this node
 */
public class DoubleNode<T> {

	/**
	 * The node that this node links to.
	 */
	private DoubleNode<T> nextNode;
	
	// The node that links to this one.
	private DoubleNode<T> previousNode;

	private T data;
	
	public DoubleNode(T data) {
		this.data = data;
	}

	public DoubleNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(DoubleNode<T> nextNode) {
		this.nextNode = nextNode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DoubleNode<T> getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(DoubleNode<T> previousNode) {
		this.previousNode = previousNode;
	}
	
}
