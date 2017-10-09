/**
   A single node with one pointer to another node, for use with linked data
   @author Eli Zupke
   @version 1.0
*/
public class Node<T> {

	// This is the data of type T that the node is pointing to
	private T data;
	
	// The one other node that this node points to.
	private Node<T> nextNode = null;

	public T getData() {
		return data;
	}

	public Node<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
	}

	public Node(T data) {
		super();
		this.data = data;
	}
}
