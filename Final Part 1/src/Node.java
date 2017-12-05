/**
 * 
 * @author Eli Zupke
 *
 * @param <T> The type of data that is to be stored in this node.
 */
public class Node<T> {

	/**
	 * The node that this node links to.
	 */
	private Node<T> nextNode;
	
	private T data;
	
	public Node(T data) {
		this.data = data;
	}

	public Node<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
