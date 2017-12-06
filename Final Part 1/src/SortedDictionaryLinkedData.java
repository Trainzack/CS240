import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of the Sorted Dictionary ADT using linked data
 * @author Eli Zupke
 *
 * @param <K> The type that will be used as keys in this dictionary
 * @param <V> The type that will be used as values in this dictionary
 */
public class SortedDictionaryLinkedData<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {

	// This is the first node in the dictionary.
	private Node front;
	
	private class Node {
		
		K key;
		V value;
		Node next;
		
		public Node(K _key, V _value) {
			key = _key;
			value = _value;
			next = null;
		}
	}
	
	@Override
	public V add(K key, V value) {
		
		// Special case. This node simply becomes the front.
		if (front == null) {
			front = new Node(key, value);
			// we replaced nothing, so return null.
			return null;
		}
		
		// Special case. This node becomes the front.
		if (key.compareTo(front.key) < 0) {
			Node newNode = new Node(key, value);
			newNode.next = front;
			front = newNode;
			return null;
		}
		
		Node prevNode = null;
		Node curNode = front;
		
		while (curNode != null) {
			if (curNode.key == key) {
				// The key we're adding already exists in our dictionary, so go ahead and replace the value.
				V oldValue = curNode.value;
				curNode.value = value;
				return oldValue;
			} else if (key.compareTo(curNode.key) < 0) {
				// We need to insert the key here. 
				Node newNode = new Node(key, value);
				newNode.next = curNode;
				prevNode.next = newNode;
				return null;
			}
			
			prevNode = curNode;
			curNode = curNode.next;
		}
		// We have got to the end of the linked data, but we still haven't added the new pair.
		// Therefore, we add it to the end.
		Node newNode = new Node(key, value);
		prevNode.next = newNode;
		return null;
	}

	@Override
	public V remove(K key) {
		
		// Special case: the node we want to remove is the front
		if (front != null && front.key == key) {
			V oldValue = front.value;
			front = front.next;
			return oldValue;
		}

		Node prevNode = null;
		Node curNode = front;
		while (curNode != null) {
			if (curNode.key == key) {
				// We've found the key in our dictionary
				V oldValue = curNode.value;
				prevNode.next = curNode.next;
				return oldValue;
			}
			prevNode = curNode;
			curNode = curNode.next;
		}
		return null;
	}

	@Override
	public V getValue(K key) {
		
		Node curNode = front;
		
		// Sequential search the list until we get to the node that we need.
		while (curNode != null) {
			if (curNode.key == key) {
				return curNode.value;
			}
			curNode = curNode.next;
		}
		
		return null;
	}

	@Override
	public boolean contains(K key) {
		
		Node curNode = front;
		
		while (curNode != null) {
			if (curNode.key == key)
				return true;
			curNode = curNode.next;
		}
		
		return false;
	}


	@Override
	public Iterator<K> getKeyIterator() {
		return new StaticArrayKeyIterator();
	}
	
	private class StaticArrayKeyIterator implements Iterator<K> {

		// The node we just gave
		Node prevNode = null;
		// The node we are about to give
		Node curNode = front;
		
		
		@Override
		public boolean hasNext() {
			return curNode != null;
		}

		@Override
		public K next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prevNode = curNode;
			curNode = curNode.next;
			return prevNode.key;
			
		}

		@Override
		public void remove() {
			if (prevNode == null) {
				throw new IllegalStateException();
			}
			SortedDictionaryLinkedData.this.remove(prevNode.key);
			
		}	
	}
	
	@Override
	public Iterator<V> getValueIterator() {
		
		return new StaticArrayValueIterator();
	}
	
	private class StaticArrayValueIterator implements Iterator<V> {

		// The node we just gave
		Node prevNode = null;
		// The node we are about to give
		Node curNode = front;
		
		
		@Override
		public boolean hasNext() {
			return curNode != null;
		}

		@Override
		public V next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prevNode = curNode;
			curNode = curNode.next;
			return prevNode.value;
			
		}

		@Override
		public void remove() {
			if (prevNode == null) {
				throw new IllegalStateException();
			}
			SortedDictionaryLinkedData.this.remove(prevNode.key);
			
		}	
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public int getSize() {
		int size = 0;
		
		Node curNode = front;

		// System.out.println("==========");
		while (curNode != null) {
			// System.out.println(curNode.value);
			size++;
			curNode = curNode.next;
		}
		
		return size;
	}

	@Override
	public void clear() {
		front = null;

	}
	
	@Override
	public String toString() {
		String out = "{";
		
		Node curNode = front;
		
		while (curNode != null) {
			out += curNode.key + ": " + curNode.value;
			if (curNode.next != null) out += ", ";
			curNode = curNode.next;
		}
		out += "}";
		return out;
	}

}
