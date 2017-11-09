/**
 * An interface that allows for implementation of the ADT Sorted List.
 * 
 * @author Eli Zupke
 * @param <T> The type of thing the list will contain
 */
public interface SortedListInterface<T> {

	/**
	 * Adds a new item to the right place in the list.
	 * The list size will be increased by 1.
	 * @param item The object to be added
	 */
	public void add(T item);
	
	/**
	 * Removes the first occurrence of the provided item, if it exists in the list
	 * @param item The item to remove
	 * @return Whether it was removed.
	 */
	public boolean remove(T item);
	
	/**
	 * Removes the item that is at the given position.
	 * The size of the list will decrease by one, and all subsequent entries will also have their positions decreased by one.
	 * @param index The index of the item to remove
	 * @return The item that was removed
	 * @throws EmptyQueueException if the queue is empty
	 * @throws IndexOutOfBoundsException if the specified position is outside of the list.
	 */
	public T remove(int index);
	
	/**
	 * Gets the position of the first occurence of the provided entry
	 * @param entry The entry to locate
	 * @return The position of the entry.
	 */
	public int getPosition(T entry);
	
	/**
	 * Empties the entire list.
	 * After the operation, the size of the list will be 0.
	 */
	public void clear();
	
	/**
	 * Checks whether the list contains a specified item.
	 * @param item The item to check for
	 * @return True if the list contains the item, or false if not.
	 */
	public boolean contains(T item);
	
	/**
	 * Returns the number of entries in the list
	 * @return The number of entries in the list
	 */
	public int size();
	
	/**
	 * Checks whether the list has any items
	 * @return True if the list has no items, or false if the list does have items.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns an array representation of the list
	 * @return An array of the same length as the list, with the same items, in the same positions.
	 */
	public T[] toArray();
}
