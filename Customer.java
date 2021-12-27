/**
 * File: Customer.java
 * Author: Tamsin Rogers
 * Date: 4/3/20
 */
 
 import java.util.Iterator;
 import java.util.*;

/* each customer has a number of items and time steps */
public abstract class Customer
{
	private int items;
	private int steps;
	
	/* constructor */
	public Customer(int num_items)
	{
		this.items = num_items;
		items = 0;
	}
	
	/* constructor */
	public Customer(int num_items, int time_steps)
	{
		this.items = num_items;
		this.steps = time_steps;
	}
	
	/* increments the number of time steps */
	public void incrementTime()
	{
		steps++;
	}
	
	/* returns the number of time steps */
	public int getTime()
	{
		return this.steps;
	}
	
	/* decrements the number of items (an item has been paid for) */
	public void giveUpItem()
	{
		items--;
	}
	
	/* returns the number of items */
	public int getNumItems()
	{
		return this.items;
	}
	
	public abstract int chooseLine(ArrayList<CheckoutAgent> checkouts);

	/* tests all of the methods */
	public static void main(String[] args) 
	{
		Random gen = new Random();
		Customer test = new RandomCustomer(1+gen.nextInt(10));
		
		System.out.println("items: " + test.getNumItems());
		System.out.println("steps: " + test.getTime());
		
		System.out.println("incrementing time");
		test.incrementTime();
		System.out.println("items: " + test.getNumItems());
		System.out.println("steps: " + test.getTime());
		
		System.out.println("giving up item");
		test.giveUpItem();
		System.out.println("items: " + test.getNumItems());
		System.out.println("steps: " + test.getTime());
	}
	
}