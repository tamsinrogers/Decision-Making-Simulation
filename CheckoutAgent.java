/**
 * File: CheckoutAgent.java
 * Author: Tamsin Rogers
 * Date: 4/3/20
 */
 
import java.util.Iterator;
import java.util.*;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

/* each CheckoutAgent has a queue of customers */
public class CheckoutAgent
{
	private int x;														// the agent's x position
	private int y;														// the agent's y position
	private MyQueue<Customer> queue;									// the agent's list of customers

	/* constructor */
	public CheckoutAgent(int x, int y)
	{	
		this.x = x;														// initialize the agent's x position
		this.y = y;														// initialize the agent's y position
		this.queue = new MyQueue<Customer>();							// initialize the agent's list of customers
	}
	
	/* return the agent's x position */
	public int getX()
	{
		return this.x;						
	}
	
	/* return the agent's y position */
	public int getY()
	{
		return this.y;
	}
	
	/* return the first customer in the agent's queue */
	public Customer getFirstCustomer()
	{
		return queue.peek();
	}
	
	/* return the agent's queue of customers */
	public MyQueue<Customer> getCustomers()
	{
		return this.queue;
	}
	
	/* adds a customer to its queue */
	public void addCustomerToQueue( Customer c )
	{
		queue.offer(c);
	}
	
	/* returns the number of customers in its queue */
	public int getNumInQueue()
	{
		return queue.size();
	}
	
	/* draws the Checkout Agent */
	public void draw(Graphics g)
	{
		int N = getNumInQueue();											// set N to the number of customers in the agent's queue
		int height = 10*N;											
		int width = 10;
		int yValue = this.y - height;										// calculate the appropriate y value based on the agent's height
		g.setColor(new Color((int)(Math.random() * 0x1000000)));			// fill the agent rectangle with a random color										
		g.fillRect(this.x, yValue, width, height);																
		g.drawRect(this.x, yValue, width, height);		
	}
	
	/* for every time step, update the state of the CheckoutAgent  */
	public void updateState(Landscape scape)
	{
		Iterator<Customer> i = queue.iterator();							// set up the iterator
		
		while(i.hasNext())													// while there is a next customer
		{
			Customer next = i.next();										
			next.incrementTime();											// increment the customer's time step
		}
		
		if(queue.peek() != null)											// if the queue has a customer
		{
			Customer first = queue.peek();									// save the first customer
			first.giveUpItem();												// give up one of the customers items
			
			if(first.getNumItems() == 0)									// if the customer has 0 items
			{
				scape.addFinishedCustomer(queue.poll());					// add that customer to the finished customers list and remove them from the queue
			}	
		}
	}

	
	/* tests all the methods */
	public static void main(String[] args) 
	{
		CheckoutAgent test = new CheckoutAgent(20,20);
		System.out.println("number of customers: " + test.getNumInQueue());
		
		System.out.println("adding a customer");
		Random gen = new Random();
		Customer cust = new RandomCustomer(1+gen.nextInt(10));
		test.addCustomerToQueue(cust);
		
		System.out.println("number of customers: " + test.getNumInQueue());
	}
}