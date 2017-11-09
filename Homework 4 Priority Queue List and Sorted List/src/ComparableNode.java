/**
 * 
 * @author Eli Zupke
 *
 * @param <T> The object that is to be stored (Must implement Comparable)
 */
public class ComparableNode<T extends Comparable<? super T>> implements Comparable<ComparableNode<T>> {

	/**
	 * The node that this node links to.
	 */
	private ComparableNode<T> nextNode;
	
	private T data;
	
	public ComparableNode(T data) {
		this.data = data;
	}

	public ComparableNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(ComparableNode<T> nextNode) {
		this.nextNode = nextNode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	/**
	 * When we compare one node with another, we really want to compare their contents, so do that.
	 */
	public int compareTo(ComparableNode<T> n) {
		return this.getData().compareTo(n.getData());
	}}
