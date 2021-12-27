/** File: Pick2CustomerSimulation.java
 * Author: Tamsin Rogers
 * Date: 4/4/20
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner; 

/* user chooses the simulation to be run and number of customers/agents, prints simulation statistics for every 100 runs */
public class Simulation
{
    public static void main(String[] args) throws InterruptedException 
    {
    	System.out.println("Enter the simulation you would like to run:");
    	System.out.println("1: Random Customer, 2: Picky Customer, 3: Pick2Customer, 4: ItemCustomer");
    	Scanner scan1 = new Scanner(System.in);
		int sim = scan1.nextInt();														// set the user's choice to the simulation to be run
		
		System.out.println("Enter the number of customers:");
    	Scanner scan2 = new Scanner(System.in);
		int numCustomers = scan2.nextInt();												// set the user's choice to the number of customers to be used in the simulation
		
		System.out.println("Enter the number of checkout agents:");
    	Scanner scan3 = new Scanner(System.in);
		int numAgents = scan3.nextInt();												// set the user's choice to the number of agents to be used in the simulation
    
        Random gen = new Random();
        ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

        for(int i=0;i<numAgents;i++) 
        {
            CheckoutAgent checkout = new CheckoutAgent( i*100+(numAgents*10), 480 );	// place the agents to they are evenly spaced in the window
            checkouts.add( checkout );
        }
        
        Landscape scape = new Landscape((numAgents*100),500, checkouts);				// size the landscape according to the number of agents chosen
        LandscapeDisplay display = new LandscapeDisplay(scape);
        
        Customer cust;																	// initialize the current customer in line
        
        if(sim == 1)																	// if the user chooses RandomCustomerSimulation
		{
			System.out.println("RUNNING RANDOM CUSTOMER SIMULATION");
			System.out.println("Customers will join lines chosen at random");
		}
        if(sim == 2)																	// if the user chooses PickyCustomerSimulation
		{
			System.out.println("RUNNING PICKY CUSTOMER SIMULATION");
			System.out.println("Customers will join the current shortest line");
		}
        if(sim == 3)																	// if the user chooses Pick2CustomerSimulation
		{
			System.out.println("RUNNING PICK2 CUSTOMER SIMULATION");
			System.out.println("Customers will join the shortest of two lines chosen at random");
		}
		if(sim == 4)																	// if the user chooses ItemCustomerSimulation
		{
			System.out.println("RUNNING ITEM CUSTOMER SIMULATION");
			System.out.println("Customers will join the line with the smallest number of total items");
		}
        
        for (int j = 0; j < numCustomers+1; j++) 										// repeat for the number of customers chosen
        {
        	if(sim == 1)																// if the user chooses RandomCustomerSimulation
        	{
        		cust = new RandomCustomer(1+gen.nextInt(5));
        		int choice = cust.chooseLine( checkouts );
				checkouts.get(choice).addCustomerToQueue( cust );
				scape.updateCheckouts();												// update the state of the checkout agents
				display.repaint();
				Thread.sleep( 250 );
        	}
        	if(sim == 2)																// if the user chooses PickyCustomerSimulation
        	{
        		cust = new PickyCustomer(1+gen.nextInt(5), checkouts.size());
        		int choice = cust.chooseLine( checkouts );
				checkouts.get(choice).addCustomerToQueue( cust );
				scape.updateCheckouts();												// update the state of the checkout agents
				display.repaint();
				Thread.sleep( 250 );
        	}
        	if(sim == 3)																// if the user chooses Pick2CustomerSimulation
        	{
        		cust = new Pick2Customer(1+gen.nextInt(5));
        		int choice = cust.chooseLine( checkouts );
				checkouts.get(choice).addCustomerToQueue( cust );
				scape.updateCheckouts();												// update the state of the checkout agents
				display.repaint();
				Thread.sleep( 250 );
        	}
        	if(sim == 4)																// if the user chooses ItemCustomerSimulation
        	{
        		cust = new ItemCustomer(1+gen.nextInt(5), checkouts.size());
        		int choice = cust.chooseLine( checkouts );
				checkouts.get(choice).addCustomerToQueue( cust );
				scape.updateCheckouts();												// update the state of the checkout agents
				display.repaint();
				Thread.sleep( 250 );
        	}
        	
        	int step = 100;																// the statistics will be printed every x times

            if(j%step == 0)																// every 100 times
        	{
        		System.out.println("(" + j + ")");
        		scape.printFinishedCustomerStatistics();
        	}
        }
    }
}