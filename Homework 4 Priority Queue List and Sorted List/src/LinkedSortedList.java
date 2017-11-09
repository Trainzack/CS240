
public class LinkedSortedList<T extends Comparable<? super T>> extends LinkedList<T> implements SortedListInterface<T>{
	
	public LinkedSortedList() {
		super();
	}
	
	/**
	 * Adds the item into the proper, sorted place in the list
	 */
	@Override
	public void add(T item) {
		
		// Get the position of this element
		int position = getPosition(item);
		if (position < 0) position *= -1;
		
		// Add the item at the position that it belongs in.
		add(item, position);
		
	}

	@Override
	public boolean remove(T item) {

		int position = getPosition(item);

		// if the position of the item is > 0, then it is in the list, and therefore
		// can be removed.
		if (position > 0) {
			remove(position);
			return true;
		}
		
		return false;
	}

	@Override
	public int getPosition(T entry) {
		
		Node<T> curNode = front;
		int index = 0;
		
		// Go through the list, and increment index.
		while (curNode != null) {
			if (curNode.getData() == entry) {
				// If we find the item we are looking for, return this index
				return index;
			} else if (curNode.getData().compareTo(entry) > 0) {
				// If we find that we have passed the item we are looking for, return this index, but negative.
				return -1 * index;
			}
			curNode = curNode.getNextNode();
			index++;
		}

		return -1 * index;
	}
	
	
}
