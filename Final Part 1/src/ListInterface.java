/**
 * An interface that allows for implementation of the ADT List. This list starts at zero.
 * 
 * @author Eli Zupke
 * @param <T> The type of thing the list will contain
 */
public interface ListInterface<T> {

	/**
	 * Adds a new item to the end of the list.
	 * The list size will be increased by 1, and other item positions will be unaffected.
	 * @param item The object to be added
	 */
	public void add(T item);
	
	/**
	 * Adds a new item to the specified position.
	 * The list size will be increased by 1, and all items that are at or after the specified position will also be increased by 1 
	 * @param item The item to add to the list
	 * @param index The position in the list to put the item 
	 * @throws IndexOutOfBoundsException if the specified position is outside of the list.
	 */
	public void add(T item, int index) throws IndexOutOfBoundsException;
	
	
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
	 * Removes the item at the end of the list.
	 * @return The item that was removed
	 * @throws EmptyQueueException if the queue is empty
	 */
	public T remove();
	
	/**
	 * Empties the entire list.
	 * After the operation, the size of the list will be 0.
	 */
	public void clear();
	
	/**
	 * Returns the item that is at the given position.
	 * The list will remain unchanged.
	 * @param index
	 * @return The item at the specified index.
	 * @throws IndexOutOfBoundsException if the specified position is outside of the list.
	 */
	public T view(int index);
	
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
	
	/**
	 * Replaces the item at the specified index with the given item.
	 * The list size remains the same.
	 * @param index	The index to replace at
	 * @param newItem The item to put in the index.
	 * @return The item that was replaced
	 */
	public T replace(int index, T newItem);
	
}
