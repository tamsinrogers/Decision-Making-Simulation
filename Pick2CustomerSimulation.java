/**
 * File: Pick2CustomerSimulation.java
 * Author: Tamsin Rogers
 * Date: 4/4/20
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner; 

/* customers choose the shortest of two lines chosen at random */
public class Pick2CustomerSimulation
{
    public static void main(String[] args) throws InterruptedException 
    {
    	System.out.println("Enter the number of customers:");
    	Scanner num = new Scanner(System.in);
		int numCustomers = num.nextInt();
    
        Random gen = new Random();
        ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

        for(int i=0;i<5;i++) 
        {
            CheckoutAgent checkout = new CheckoutAgent( i*100+50, 480 );
            checkouts.add( checkout );
        }
        
        Landscape scape = new Landscape(500,500, checkouts);
        LandscapeDisplay display = new LandscapeDisplay(scape);
        
        int step = 100;
        
        for (int j = 0; j < numCustomers+1; j++) 
        {
            Customer cust = new Pick2Customer(1+gen.nextInt(5));
            int choice = cust.chooseLine( checkouts );
            checkouts.get(choice).addCustomerToQueue( cust );
            scape.updateCheckouts();						// update the state of the checkout agents
            display.repaint();
            Thread.sleep( 250 );
            
            if(j%step == 0)									// every 100 times
        	{
        		System.out.println("(" + j + ")");
        		scape.printFinishedCustomerStatistics();
        	}
        }

    }

}