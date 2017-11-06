
public class DoubleNode<T> extends Node<T> {

	private Node<T> previousNode;
	
	public DoubleNode(T data) {
		super(data);
		
	}

	public Node<T> getPreviousNode() {
		return previousNode;
	}

	public void setPreviousNode(Node<T> previousNode) {
		this.previousNode = previousNode;
	}
	
}
