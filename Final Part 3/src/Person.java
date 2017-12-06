import java.util.Iterator;

/*************************************************************************
  Winter 2017 CS 240 Programming Exam : Person

 Author: Eli    

 Dependencies: Stack, Queue, Dictionary

 Description:  Models a person, a list of messages that they can
               read, and a list of their friends, so that when you
               post a message, all your friends can read it too.

**************************************************************************/

public class Person { 


	public SingleLinkedDataQueue<Person> friends;
	
	public LinkedDataStack<String> messages;
	
	public String name;
	
    // Create a new Person with this name.
    public Person(String name) {
    	friends = new SingleLinkedDataQueue<>();
    	
    	messages = new LinkedDataStack<>();
    	
    	this.name = name;
    }

    // Make these two people become friends with each other.
    // Throw an exception if you try to meet yourself.
    // We are allowed to assume we didn't meet this person yet.
    public void meet(Person otherPerson) {
        
    	if (otherPerson == this) {
    		throw new RuntimeException("Stop meeting yourself!");
    	}
    	
    	friends.enqueue(otherPerson);
    	otherPerson.friends.enqueue(this);
    	
    }

    // Are these two people friends?
    // Throw an exception if you ask about knowing yourself.
    public boolean knows(Person otherPerson) {
    	
    	if (otherPerson == this) {
    		throw new RuntimeException("Stop meeting yourself!");
    	}
    	
    	Iterator<Person> freindIterator = friends.getIterator();
    	
    	while (freindIterator.hasNext()) {
    		if (freindIterator.next() == otherPerson) return true;
    	}
    	
    	return false;
       
    }

    // Post a message to my list and the lists of all my friends
    public void post(String message) {
        
    	messages.push(message);
    	
    	Iterator<Person> freindIterator = friends.getIterator();
    	
    	while (freindIterator.hasNext()) {
    		freindIterator.next().messages.push(message);
    	}
    	
    	
    }

    // Print a header, then all messages this Person can read, newest first
    public void listMessages() {
       System.out.println("== The wall of " + name + " ==");
       
       while (!messages.isEmpty()) {
    	   System.out.println(messages.pop());
       }
       
    }
}