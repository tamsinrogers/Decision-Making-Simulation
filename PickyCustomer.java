/**
 * File: PickyCustomer.java
 * Author: Tamsin Rogers
 * Date: 4/3/20
 */
 
import java.util.Iterator;
import java.util.*;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

/* chooses the shortest line */
public class PickyCustomer extends Customer
{
	/* constructor */
	public PickyCustomer( int num_items, int num_lines )
	{
		super(num_items, num_lines);							// call the super class's constructor with the given items and the number of lines as the initial time step value
	}
	
	/* returns the index of the CheckoutAgent that has the current shortest line */
	public int chooseLine(ArrayList<CheckoutAgent> checkouts)
	{
		int shortest = 0;	
		int index = 0;							
		
		CheckoutAgent smallest = checkouts.get(0);				// get the agent in the first index;

		for(CheckoutAgent a: checkouts)							// for each agent in the list of agents
		{
			index = checkouts.indexOf(a);						// set index to the index of the current agent
			
			if(a.getNumInQueue() < smallest.getNumInQueue())	// if the number of customers in the agent's queue is less than the current shortest value
			{
				shortest = index;								// set shortest to the index of that agent
				smallest = checkouts.get(shortest);				// set the smallest agent to the current agent
			}
			if(a.getNumInQueue() == smallest.getNumInQueue())	// this is the case for the first agent
			{
				shortest = index;								// set the shortest index to the current index
			}
			else												// if the number of customers in the agent's queue is greater than the current shortest value
			{
				break;											// this is not the agent with the shortest line, so go check the next agent (back to top of loop)
			}
		}
		return shortest;										// return the index of the agent with the shortest queue
	}
}