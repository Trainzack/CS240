import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * @author eli
 *
 *
 * @param <T> What type of objects the iterator's iterators are iterating over
 */
public class IteratorOfIterators<T> implements Iterator<T> {

	private Iterator<T>[] its;
	
	private int nextIndex;
	
	/**
	 * Creates a new Iterator of Iterators
	 * @param its The array of iterators that we should iterate over.
	 */
	public IteratorOfIterators(Iterator<T>[] its) {
		this.its = its;
		nextIndex = 0;
	}
	
	
	@Override
	public boolean hasNext() {
		
		// Return false only if the next iterator is empty.
		// Note: assumes all iterators have the same number of items.
		if (!its[nextIndex].hasNext()) {
			return false;
		}
		
		return true;
	}

	@Override
	public T next() {
		
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		// This is the object that we are returning.
		T next = its[nextIndex].next();
		
		// Go forward, or wrap around if we've reached the end.
		nextIndex = (nextIndex + 1) % its.length;
		
		return next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
