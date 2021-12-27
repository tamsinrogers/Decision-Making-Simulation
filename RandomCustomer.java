/**
 * File: RandomCustomer.java
 * Author: Tamsin Rogers
 * Date: 4/3/20
 */
 
import java.util.Iterator;
import java.util.*;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

/* chooses a line at random */
public class RandomCustomer extends Customer
{
	/* constructor */
	public RandomCustomer(int num_items)
	{
		super(num_items, 1);									// call the super class's constructor with the items and 1 as the initial time step value
	}
	
	/* returns a randomly chosen integer */
	public int chooseLine(ArrayList<CheckoutAgent> checkouts)
	{
		Random r = new Random();								// generate a new random number
		int rinteger = r.nextInt(checkouts.size());				// generate a random choice of line
		return rinteger;										// return this choice
	}

}