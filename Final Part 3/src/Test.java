import java.util.Iterator;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final int LENGTH = 3;
		
		System.out.println("Person Test 1");
		System.out.println();
		test1();
		System.out.println();
		System.out.println("Person Test 2");
		System.out.println();
		try {
			test2();
			System.out.println("Uh-Oh! We were expecting a runtime exception!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.err.println("(We were expecting that!)");
		}
		System.out.println();
		System.out.println();
		System.out.println("Iterators of Iterators:");

		VectorStack<String> stack = new VectorStack<>();
		DoubleLinkedList<String> list = new DoubleLinkedList<>();
		SortedDictionaryStaticArray<Integer, String> dict = new SortedDictionaryStaticArray<>(LENGTH);

		for (int i = 0; i < LENGTH; i++) {
			stack.push("S" + i);
			list.add("L" + i);
			dict.add(new Integer(i), "D" + i);
		}
		
		Iterator[] its = {stack.getIterator(), list.getIterator(), dict.getValueIterator()};

		IteratorOfIterators<String> t = new IteratorOfIterators<>(its);

		while (t.hasNext()) {
			System.out.print(t.next() + " ");
		}

	}

	/**This is a sample test main() for Person. It should output:

		== The wall of Kim ==
		I agree
		Friends are awesome
		Only Kim can read this
		== The wall of Pat ==
		I agree
		Friends are awesome

	 *************************************************/

	public static void test1() {

		Person first = new Person("Kim");
		Person second = new Person("Pat");
		first.post("Only Kim can read this");

		first.meet(second);
		second.post("Friends are awesome");
		first.post("I agree");

		first.listMessages();
		second.listMessages();

	}

	/**********************************************

		This is a sample test main() for Person. It should output:

		false
		true
		true

		and then throw a RuntimeException (see the comments).

	 *************************************************/

	public static void test2() {

		Person first = new Person("Kim");
		Person second = new Person("Pat");

		System.out.println(first.knows(second));   // should print "false"

		first.meet(second);

		System.out.println(first.knows(second));   // should print "true"
		System.out.println(second.knows(first));   // should print "true"

		first.knows(first);                    // should throw a RuntimeException
	}
}
