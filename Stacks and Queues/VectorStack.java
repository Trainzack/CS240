import java.util.EmptyStackException;import java.util.Vector;/**   A stack data structure implemented via Vectors.   @author Eli Zupke   @version 1.0 */public class VectorStack<T> implements StackInterface<T>{	private Vector<T> stack;		public VectorStack() {		stack = new Vector<T>();	}		/** Adds a new entry to the top of this stack.	   @param newEntry  An object to be added to the stack. */	public void push(T newEntry) {		stack.add(newEntry);	}	/** Removes and returns this stack's top entry.       @return  The object at the top of the stack.        @throws  EmptyStackException if the stack is empty before the operation. */	public T pop() {				if (isEmpty()) {			throw new EmptyStackException();		}		else {			// Grab the last element of the stack, remove it from the stack, and return it			return stack.remove(stack.size() - 1);		}			}	/** Retrieves this stack's top entry.       @return  The object at the top of the stack.       @throws  EmptyStackException if the stack is empty. */	public T peek() {				if (isEmpty()) {			throw new EmptyStackException();		}		else {			// Grab the last element of the stack, and return it			return stack.get(stack.size() - 1);		}	}	/** Detects whether this stack is empty.       @return  True if the stack is empty. */	public boolean isEmpty() {		return stack.isEmpty();			}	/** Removes all entries from this stack. */	public void clear() {				stack.clear();			}}