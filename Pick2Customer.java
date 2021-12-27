/**
 * File: Pick2Customer.java
 * Author: Tamsin Rogers
 * Date: 4/3/20
 */
 
 import java.util.Iterator;
 import java.util.*;

/* chooses the shortest of two lines chosen at random */
public class Pick2Customer extends Customer
{
	public Pick2Customer( int num_items )									
	{
		super(num_items, 2);												// call the super class's constructor with the given items and the 2 as the initial time step value
	}
	
	/* chooses the shortest of two randomly chosen lines */
	public int chooseLine(ArrayList<CheckoutAgent> checkouts)
	{
		Random r = new Random();
		int N = checkouts.size();
		int random1 = r.nextInt(N);											// choose a random int
		int random2 = r.nextInt(N);											// choose a random int
		
		while(random1 == random2)											// if the same int is chosen twice
		{
			random2 = r.nextInt(N);											// pick a new value for the second random int
		}
		
		CheckoutAgent randomAgent1 = checkouts.get(random1);				// get the agent at the first random index;
		CheckoutAgent randomAgent2 = checkouts.get(random2);				// get the agent at the second random index;
	
		int shortest = 0;													// initialize shortest
		
		if(randomAgent1.getNumInQueue() < randomAgent2.getNumInQueue())		// if the first agent's line is shorter than the second agent's line	
		{	
			shortest = random1;
		}
		if(randomAgent2.getNumInQueue() < randomAgent1.getNumInQueue())		// if the second agent's line is shorter than the first agent's line	
		{
			shortest = random2;
		}
		if(randomAgent1.getNumInQueue() == randomAgent2.getNumInQueue())	// if the lines are the same length
		{
			int choose = r.nextInt(2);										// pick a number, either 0 or 1
			if(choose == 0)													// if 0 is picked
			{
				shortest = random1;											// choose the first one
			}
			else															// if 1 is picked
			{
				shortest = random2;											// choose the second one
			}
		}
		return shortest;													// return the index of the shortest line
	}
}