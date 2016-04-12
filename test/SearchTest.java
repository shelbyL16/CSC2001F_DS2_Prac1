//normal imports 
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.ArrayList;

//file imports
import dictionary.*;
import test.PrimeSequence;

//Program for search test to write report 
public class SearchTest 
{
	//create a AbstractHashTable
    private static AbstractHashTable createTable(final String className, final int tableSize) throws Exception 
	{
        final Class<? extends AbstractHashTable> tableClass = Class.forName(className).asSubclass(AbstractHashTable.class);
        final Constructor<? extends AbstractHashTable> intConstructor = tableClass.getConstructor(int.class);
        return intConstructor.newInstance(tableSize);
    	}//end 
   
    public static void main(final String[] args) throws Exception 
	{
	        // Create a table
       		final AbstractHashTable table = createTable(args[0], Integer.parseInt(args[1]));
        	final Loader load = new Loader(table);
            int total = 0; 
	
	 	    //create a primesequence and then a number
   		    PrimeSequence prime = new PrimeSequence(Integer.parseInt(args[1]));
    	 	int tableInteger = prime.next();

        	load.load(new File("data/" + args[2]));
		
		    //need to reset probe count 
    		table.resetProbeCount();
   	
		     //int declarartion with loop
    		int loop = Integer.parseInt(args[4]);
	        int w = 1;
    		
		//while loop
		while(w <= loop)
		{
			//list declarations
			ArrayList<Entry> arrayList = new ArrayList<Entry>();            		
			arrayList = table.getWords();
        	Randomizer<Entry> list = new Randomizer<Entry>(arrayList);
            
       
			//int declarations - arguments into command line, test words number
        	int k = (Integer.parseInt(args[3]) * 4)/5;//80% actual words
        	int l = Integer.parseInt(args[3]) - k;//20% nonsense

            //trying to get sc to work
            if ((args[0]).equals("dictionary.SCHashTable"))
            {
                 String ans = "You haven't gotten this to work yet";
                 System.out.println(ans);
                 break;
            }//end 
  
        	for (int i = 1; i <= k; i++)
			{
            		table.containsWord((list.next()).getWord());
        	}//end for loop
   
       		//declare nonsense
        	Nonsense nonsense = new Nonsense(4, 7);
  
  			for (int i = 1; i <= l; i++)
			{
               		table.containsWord(nonsense.next());
    		  }//end for loop
                w++;
    		}//end while loop

    	    total = table.getProbeCount();
	    //int set = total / (Integer.parseInt(args[4]));
    	//int search = total/ ((Integer.parseInt(args[3])) * (Integer.parseInt(args[4])));
    	

    	System.out.println("This is your test for: " + args[0]);
    	System.out.println("Total Probes per sample given: " + total);
    	System.out.println("Average Probes " + (total)/(((Integer.parseInt(args[4])))));
    }//end main
}//end class
