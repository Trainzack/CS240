/**
   A single node with one pointer to another node, for use with linked data
   @author Eli Zupke
   @version 1.0
*/
public class DictNode<K, V> {

	// This is the data of type T that the node is pointing to
	K key;
	
	V value;
	
	boolean flag = false;
	
	
	
	// The one other node that this node points to.
	private DictNode<K, V> nextNode = null;

	public DictNode(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	

	public DictNode<K, V> getNextNode() {
		return nextNode;
	}

	public void setNextNode(DictNode<K, V> nextNode) {
		this.nextNode = nextNode;
	}


}
