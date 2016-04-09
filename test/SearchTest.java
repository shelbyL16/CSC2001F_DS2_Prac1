//regular imports
import java.io.File;
import java.lang.reflect.Constructor;

//special imports
import dictionary.AbstractHashTable;
import dictionary.Loader;
import dictionary.Entry;
import test.PrimeSequence;


//used to perform load performance testing on a hash table implementation of Dictionary
public class SearchTest 
{
	//Create an instance of the given class of dictionary with the given table size
   	private static AbstractHashTable createTable(final String className, final int tableSize) 
	
	throws Exception 
	{
        	final Class<? extends AbstractHashTable> tableClass = Class.forName(className).asSubclass(AbstractHashTable.class);
        	final Constructor<? extends AbstractHashTable> intConstructor = tableClass.getConstructor(int.class);
        	return intConstructor.newInstance(tableSize);
   	 }//end exception catch
   
	public static void main(final String[] args) throws Exception 
	{
		// Create a AbstractHashTable object from size given from the command line
        	final AbstractHashTable table = createTable(args[0], Integer.parseInt(args[1]));
        	final Loader LOAD = new Loader(table);

		//create a prime sequence object, and a int from that
		PrimeSequence prime = new PrimeSequence(Integer.parseInt(args[1]));
    		int tableInteger = prime.next();

    		try
		{
        		LOAD.load(new File("data/" + args[2]));
        
    		}	
		catch(NullPointerException exception)
		{
        		int probes = table.getProbeCount();
        		System.out.println("\n" + probes);
        		exception.printStackTrace();
    	}
	//need to reset probe count everytime
    	table.resetProbeCount();
   	
	//need reps for for loop
    	int repititions = Integer.parseInt(args[4]);
	
	//for loop
    	for(int i = 1; i <= repititions; i++)
	{
		//list declarations
            	java.util.ArrayList<Entry> wordlist = table.getWords();
   		Randomizer<Entry> list = new Randomizer<Entry>(wordlost);
       		
		//int declarations
        	int j = (Integer.parseInt(args[3]) * 4)/5;
        	int k = Integer.parseInt(args[3]) - j;
       
		//for loop
       		for (int l = 1; l <= real; l++)
		{
            		table.containsWord((list.next()).getWord());
        	}//end for loop l
   
       		//nonsense declaration
        	Nonsense nonsense = new Nonsense(4, 7);
   
        	for (int m = 1; m <= not; m++)
		{
                    table.containsWord(nonsense.next());
        	}//end for loop m
    	}//end for loop i


    int total = table.getProbeCount();
    int pSearch = total/ ((Integer.parseInt(args[3])) * (Integer.parseInt(args[4])));
    int pSet = total / (Integer.parseInt(args[4]));

    System.out.println("Total Probes for the diciotnary: " + total);
    System.out.println("Probes per Search: " + pSearch);
    System.out.println("Probes per " + args[3] + " searches: " + pSet);
    }
}//end class
