import java.util.Iterator;

/**
 * LET"S WORK IN GROUPS!!!!!!!!!!!!!!!!!!!
 */
public class DictionaryOpenAddressingLinearProbing<K, V> implements DictionaryInterface<K, V>  {

	private class Node {
		
		// The key stored in this node
		K key;
		
		// The value associated with that key
		V value;
		
		// Whether the 
		boolean flag;
		
		public Node(K _key, V _value) {
			key = _key;
			value = _value;
		}
		
	}
	
	
	@Override
	public V add(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	private static rehash() {
		// Get the next prime number after (curcap*2)
		int newsize = 0;
		
		// Create a new array
		
		// Add everything to it
		
		
	}
	
	
}
