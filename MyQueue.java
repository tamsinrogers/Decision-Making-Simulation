/**
 * File: MyQueue.java
 * Author: Tamsin Rogers
 * Date: 3/30/20
 */
 
 import java.util.Iterator;
 import java.util.*;

public class MyQueue<T> implements Iterable<T> 
{
    int size;
	private Node head;
	
	/* node objects have a value and a next pointer */						
	private class Node
	{
		T value;						// the value of type T of the node
		Node next;						// the next pointer of the node
		
		/* constructor, initializes next to null and the container field to item */
		public Node(T item)	
		{
			next = null;						// initialize next to null
			value = item;						// initialize the container field to item
		}	
		
		/* returns the value of the container field */
		public T getThing()
		{
			return value;						// return the value of the container field
		}

		/* sets next to the given node */
		public void setNext(Node n)
		{
			next = n;							// set next to the given node
		}

		/* returns the next field */
		public Node getNext()
		{
			return next;						// return the next node
		}
	}
	
	/* constructor method */
	public MyQueue()
	{
		this.head = null;						// initialize the head node to null
		size = 0;								// initialize the size of the queue to 0
	}	
	
	public boolean isEmpty()
	{
		return size == 0;						// returns true if size is 0
	}	
	
	/* resets the fields so the list is empty */
	public void clear()
	{
		size = 0;								// initialize size to 0 (so the queue is empty)
		head = null;							// set the head node to null (there is no head node)
	}

	/* returns the size of the queue */
	public int size()
	{
		return size;
	}

	/* inserts the item at the beginning of the queue */
	private void addFirst(T thing)
	{
		Node firstNode = new Node(thing);		// create a node that has the given thing value
		firstNode.next = head;					// set the next node to the current head node
		head = firstNode;						// set the new head node to the newly created node
		size++;									// increment the size of the queue
	}

	/* appends the item at the end of the queue */
	private void addLast(T thing)
	{
		if(size == 0)							// if the queue is empty
		{
			head = new Node(thing);				// the head node is the newly added node
		}
		else									// if the list already has elements
		{
			Node lastNode = new Node(thing);	// create a new node that has the given thing value
			Node node = head;					// point to the head node
			Node next = node.next;				// point to the node next to the head node
			for(int i=0; i<this.size-1; i++)	// loop through the queue
			{
				node = next;					// set the head node to the next node
				next = node.next;				// set the current node to the next node
			}
			node.next = lastNode;				// set the final pointed next node to the last node
		}
		size++;									// increment the size of the queue
	}

	/* inserts the item at the specified position in the queue */
	public void add(int index, T item)
	{
		if(size == 0)							// if the queue is empty
		{
			this.addFirst(item);				// add the new item to the beginning of the queue
			return;
		}
		if(index == 0)							// if the given index is 0
		{
			this.addFirst(item);				// add the new item to the beginning of the queue
			return;
		}
		Node addNode = new Node(item);			// create a new node
		Node node = head;						// point to the head node
		for(int i=0; i<index-1; i++)			// loop through the queue
		{
			node = node.getNext();				// set node to the node next to it
		}
		Node adder = node.getNext();			// set the next node to the adder node
		node.next = addNode;					// set the node next to node to addNode
		addNode.setNext(adder);					// set adder to be the node next to addNode
		size++;									// increment the size of the queue
	}
	
	/* removes the item at the specified position in the list */
	private T remove(int index)
	{
		Node node = head;						// point to the head node
		Node nxt = head.next;					// set next to the node next to the head node
		if(index == 0)							// if the given index is 0
		{
			T headValue = head.value;			// set headValue to the value of the head node
			head = head.next;					// set the head node to the node next to it
			size--;								// decrement the size of the queue
			return headValue;					// return the value of the head node
		}
		for(int i=0; i<index-1; i++)			// loop through the queue
		{
			node = nxt;							// set the head node pointer to the node next to the head node
			nxt = node.next;					// set the nxt node to the node next to it (move them down one)
		}
		node.next = nxt.next;					//set the node next to node to the node next to next (move them down one)
		size--;									// decrement the size of the queue
		return nxt.value;						// return the T value of the nxt node
	}
	
	/* inserts the item into the queue if possible - returns true if successful */
	public boolean offer(T item) 
	{
		this.addLast(item);					// insert the item at the end of the queue
    	return true;						// insertion is successful
	}

	/* returns, but does not remove, the head of this queue, or returns null if this queue is empty */
	public T peek() 
	{
		if(this.size() == 0)					// if the queue is empty
		{
			return null;
		}
		else									// if the queue is not empty
		{
			return this.head.value;				// returns the head of the queue
		}
	}

	/* returns and removes the head of this queue, or returns null if this queue is empty */
	public T poll() 
	{
		if(this.size() == 0)					// if the queue is empty
		{
			return null;
		}
		else									// if the queue is not empty
		{
			Node headNode = this.head;			// save the head node
			this.remove(0);						// remove the node at index 0 (the head node)
			return headNode.value;				// return the saved head of the queue
		}
	}

	/* implements an iterator for the Q class, loops over the linked list from head to tail */
	private class QIterator implements Iterator<T>
	{
		private Node current;					// initialize the current node
		
		/* constructor for the QIterator given the head of a list */
		public QIterator(Node head)
		{		
			current = head;						// set the current node to the head node
		}
		
		/* returns true if there are still values to traverse (if the current node reference is not null) */
		public boolean hasNext()
		{
			if(current != null)					// if the current node exists
			{
				return true;
			}
			else								// if the current node does not exist
			{
				return false;
			}
		}
		
		/* returns the next item in the list, which is the item contained in the current node
			and moves the traversal along to the next node in the list */
		public T next()
		{
			Node n = current;					// set the node n to the current node
			current = current.next;				// set the current node to the node next to it
			return n.value;						// return the value of the current node
		}
		
		/* removes the current element in the list */
		public void remove()
		{
			Node node = head;						// point to the head node
			Node nxt = head.next;					// set next to the node next to the head node
			for(int i=0; i<size; i++)				// loop through the linked list
			{
				node = nxt;							// set the head node pointer to the node next to the head node
				nxt = node.next;					// set the nxt node to the node next to it (move them down one)
			}
			node.next = nxt.next;					//set the node next to node to the node next to next (move them down one)
			size--;									// decrement the size of the linked list
		}
	}
	
	/* returns a new QIterator pointing to the head of the list */
	public Iterator<T> iterator() 
	{
    	return new QIterator(this.head);		
	}
	
	/* returns a string representation of the queue */
	public String toString()
	{
		if (this.size() == 0) 
		{
			return "<>";
		}
		else 
		{
			String result = "<";
			for (T item : this) 
			{
				result += item + ", ";
			}
			return result.substring(0,result.length()-2) + ">";
		}
	}
	
	/* compares the two queues, returns true if their contents are equal, false otherwise */
	public boolean equals(MyQueue<T> other) 
	{
		if (this.size != other.size) 
		{
			return false;
		}
		else 
		{
			Iterator<T> thisIt = this.iterator();
			Iterator<T> otherIt = other.iterator();
			while (thisIt.hasNext()) 
			{
				T thisItem = thisIt.next();
				T otherItem = otherIt.next();
				if (! thisItem.equals(otherItem)) 
				{
					return false;
				}
			}
		return true;
		}
	}
	
	/* tests all of the methods */
	public static void main(String[] args) 
	{
		MyQueue<Integer> test = new MyQueue<Integer>();
		
		// EMPTY queue tests
		System.out.println("QUEUE: EMPTY");
		System.out.println(test.toString());
		System.out.println("test isEmpty: (should return true)");
		System.out.println(test.isEmpty());
		System.out.println("test size: (should return 0)");
		System.out.println(test.size());
		System.out.println("test peak: (should return null)");
		System.out.println(test.peek());
		System.out.println("test poll: (should return null)");
		System.out.println(test.poll());
		System.out.println("test offer: (should return true)");
		System.out.println(test.offer(5));
		
		// FILLED queue tests
		System.out.println("QUEUE: NOT EMPTY");
		System.out.println(test.toString());
		System.out.println("test addLast(7)");
		test.addLast(7);													
		System.out.println(test.toString());
		System.out.println("test offer (8): (should return true)");									
		System.out.println(test.offer(8));									
		System.out.println(test.toString());
		System.out.println("test peek: (should return head value, which is 5)");					
		System.out.println(test.peek());
		
		System.out.println("test addFirst(2)");
		test.addFirst(2);														
		System.out.println(test.toString());
		test.addFirst(1);											
		System.out.println(test.toString());
		
		System.out.println("test poll: (should return and remove head value, which is 1)");					
		System.out.println(test.poll());										
		System.out.println(test.toString());
		System.out.println("test peek: (should return head value, which is 2)");					
		System.out.println(test.peek());
		System.out.println(test.toString());
		System.out.println("test poll: (should return and remove head value, which is 2)");					
		System.out.println(test.poll());									
		System.out.println(test.toString());
		
		System.out.println("poll the rest of the queue");
		test.poll();
		test.poll();
		test.poll();
		
		System.out.println(test.toString());
		
		System.out.println("the queue is now empty");
		
		System.out.println("test isEmpty: (should return true)");
		System.out.println(test.isEmpty());
		System.out.println("test size: (should return 0)");
		System.out.println(test.size());
		System.out.println("test peak: (should return null)");
		System.out.println(test.peek());
		System.out.println("test poll: (should return null)");
		System.out.println(test.poll());
		System.out.println("test offer: (should return true)");
		System.out.println(test.offer(5));
	}

}