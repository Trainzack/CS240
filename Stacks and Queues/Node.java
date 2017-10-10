/**
   A single node with one pointer to another node, for use with linked data
   @author Eli Zupke
   @version 1.0
*/
public class Node<T> {

	// This is the data of type T that the node is pointing to
	private T data;
	
	// This represents whether this node is the "Empty" node used in the double linked data queue.
	private boolean isHeader;

	// The one other node that this node points to.
	private Node<T> nextNode = null;

	public Node(T data) {
		super();
		this.data = data;
		this.isHeader = false;
	}
	

	public T getData() {
		return data;
	}

	public Node<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
	}
	

	public boolean isHeader() {
		return isHeader;
	}

	public void setIsHeader(boolean isHeader) {
		this.isHeader = isHeader;
	}
}
