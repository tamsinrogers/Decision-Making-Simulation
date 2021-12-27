/**
 * File: PickyCustomer.java
 * Author: Tamsin Rogers
 * Date: 4/5/20
 */
 
import java.util.Iterator;
import java.util.*;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

/* chooses the shortest line */
public class ItemCustomer extends Customer
{
	/* constructor */
	public ItemCustomer( int num_items, int num_lines )
	{
		super(num_items, num_lines);							// call the super class's constructor with the given items and the number of lines as the initial time step value
	}
	
	/* returns the index of the CheckoutAgent that has the current smallest amount of items */
	public int chooseLine(ArrayList<CheckoutAgent> checkouts)
	{
		int shortest = 0;	
		int index = 0;	
		int smallest = 0;						
		
		CheckoutAgent firstA = checkouts.get(0);					// get the agent in the first index;
		Customer firstC = firstA.getFirstCustomer();				// get the first customer in the line
		
		Iterator<Customer> i = firstA.getCustomers().iterator();	// set up the iterator
		
		for(CheckoutAgent a: checkouts)								// for each agent
		{
			while(i.hasNext())										// for each customer in the line
			{
				smallest = firstC.getNumItems();					// get the number of items that the first customer has
				Customer c = i.next();								// set the current customer to the next customer
			
				if(c.getNumItems() < smallest)						// if this customer has fewer items
				{
					smallest = c.getNumItems();						// set smallest to the number of items that this customer has
				}
				if(c.getNumItems() == smallest)						// if this customer has the same amount of items items
				{
					smallest = c.getNumItems();						// set smallest to the number of items that this customer has
				}
				else												// if the current customer has more items than the smallest number
				{
					break;
				}
			}
		}
		return smallest;											// return the index of the line with the fewest total items
	}
}