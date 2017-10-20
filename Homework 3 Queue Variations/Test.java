
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestDEQueue();

	}
	
	public static void TestDEQueue() {
		DoubleLinkedDEQueue<Integer> q = new DoubleLinkedDEQueue<Integer>();
		
		//Add 0-19 alternating between front and back.
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) { 
				System.out.println("Adding " + i + " to front.");
				q.addToFront(new Integer(i));
			} else {
				System.out.println("Adding " + i + " to back.");
				q.addToBack(new Integer(i));
			}
		}
		// Remove them all from front.
		for (int i = 0; i < 20; i++) {
			Integer next = q.removeBack();
			System.out.println(i + ":\tRemoving " + next);
		}
		
		
		
	}

}
