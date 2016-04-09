import dictionary.AbstractHashTable;
import dictionary.Loader;
import dictionary.Entry;
import test.PrimeSequence;
import java.io.File;
import java.lang.reflect.Constructor;

//Program for testing load performance of a given hash table implementation of Dictionary.
public class SearchTest
{
	//Create an instance of the given class of dictionary with the given table size.
    	private static AbstractHashTable createTable(final String className, final int tableSize) throws Exception { 
        final Class<? extends AbstractHashTable> tableClass = Class.forName(className).asSubclass(AbstractHashTable.class);
        final Constructor<? extends AbstractHashTable> intConstructor = tableClass.getConstructor(int.class);
        return intConstructor.newInstance(tableSize);
    }
    
    public static void main(final String[] args) throws Exception {

	PrimeSequence prim = new PrimeSequence(Integer.parseInt(args[1]));
	int tbl = prim.next();

        // Create a dictionary object from the class name and table size given as command line arguments.
        final AbstractHashTable table = createTable(args[0], Integer.parseInt(args[1]));
        final Loader loader = new Loader(table);

        // Your code here.
	try{
		loader.load(new File("data/" + args[2]));
		//table.dump();
	}catch(NullPointerException e){
		int tot = table.getProbeCount();
		System.out.println("\n" + tot);
		e.printStackTrace();
	}
	table.resetProbeCount();
	
	


	int reps = Integer.parseInt(args[4]);
	for(int k = 1; k <= reps; k++){


	        java.util.ArrayList<Entry> wrdlst = table.getWords();
	
		Randomizer<Entry> newlst = new Randomizer<Entry>(wrdlst);
		
		int real = (Integer.parseInt(args[3]) * 4)/5;
		int not = Integer.parseInt(args[3]) - real;
		

	
	
		for (int i = 1; i <= real; i++){
			table.containsWord((newlst.next()).getWord());
		}
	
		
		Nonsense nonlst = new Nonsense(4, 7);
	
		for (int i = 1; i <= not; i++){
					table.containsWord(nonlst.next());
		}
	}



	int tot = table.getProbeCount();
	int perSearch = tot/ ((Integer.parseInt(args[3])) * (Integer.parseInt(args[4])));
	int perSet = tot / (Integer.parseInt(args[4]));

	System.out.println(args[0]);
	System.out.println("\nTotal Probes: " + tot);
	System.out.println("\nAverage Probes per Search: " + perSearch);
	System.out.println("\nAverage Probes per " + args[3] + " Searches: " + perSet);
    }
    //java -cp bin SearchTest dictionary.LPHashTable 3739 lexicon.txt 100 10000
    
}
