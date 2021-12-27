/**
 * File: Landscape.java
 * Author: Tamsin Rogers
 * Date: 4/3/20
 */
 
import java.util.Iterator;
import java.util.*;
import java.util.Collections;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Landscape
{
	private int width;
	private int height;
	private ArrayList<CheckoutAgent> agents;
	private LinkedList<Customer> customers;

	/* constructor */
	public Landscape(int w, int h, ArrayList<CheckoutAgent> checkouts )
	{
		this.width = w;
		this.height = h;
		this.agents = checkouts;								// the list of checkout agents
		this.customers = new LinkedList<Customer>();			// the list of finished customers
	}
	
	/* returns the height of the landscape */
	public int getHeight()
	{
		return this.height;
	}
	
	/* returns the height of the landscape */
	public int getWidth()
	{
		return this.width;
	}
	
	/* returns a string with the number of checkouts and finished customers in the landscape */
	public String toString()
	{
		String s = "number of checkouts: " + agents.size() + "\n" + "number of finished customers: " + customers.size();
		return s;
	}
	
	/* adds the customer to the list of finished customers */
	public void addFinishedCustomer(Customer c )
	{
		this.customers.addLast(c);
	}
	
	/* draws each of the CheckoutAgents */
	public void draw( Graphics g )
	{
		int number = 0;											// initialize number
		for(CheckoutAgent a: agents)							// for each agent
		{			
			a.draw(g);											// draw the agent
			number = a.getNumInQueue();							// set number to the number of customers in the agent's queue
			String s = Integer.toString(number);				// cast number to a string
			g.setFont(new Font("Arial", Font.PLAIN, 20)); 		// set the font and size of the text
			g.drawString(s, (a.getX()), ((a.getY()) - 150));	// draw the number of customers above the agent				
		}
	}
	
	public void updateCheckouts()
	{
		for(CheckoutAgent a: agents)							// loop through all of the checkout agents
		{
			a.updateState(this);								// update the state of the checkout agent
		}
	}
	
	/* compute and print the average and standard deviation of the time it takes all of the customers to be finished */
	public void printFinishedCustomerStatistics()
	{
		ArrayList<Integer> times = new ArrayList<Integer>();	// initialize a list to store the times

		for(Customer c : customers)								// for each customer in the agent's list
		{
			times.add(c.getTime());								// add the customer's time step to the times list
		}

		double sum = 0;
		double N = times.size();
		double sdev = 0;
		double avg = 0;

		if(!times.isEmpty())									
		{
			for(int time : times)								// for each time in the list of times
			{
				sum += time;									// add the current time to the current sum of times in the list
				avg = (sum/N);									// calculate the customer's average time
				sdev = Math.pow(time - avg, 2);					// calculate the customer's standard deviation
			}
		}
	
		System.out.println("average customer time: " + avg + " ms");
		System.out.println("standard deviation customer time: " + sdev + " ms");
	}
	
	/* tests the methods */
	public static void main(String[] args) 
	{
		ArrayList<CheckoutAgent> ags = new ArrayList<CheckoutAgent>();
		Landscape scape = new Landscape(400,300, ags);
		
		System.out.println("landscape width: " + scape.getWidth());
		System.out.println("landscape height: " + scape.getHeight());
		
		System.out.print(scape.toString());
	}
}