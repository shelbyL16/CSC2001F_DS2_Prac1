package dictionary;
import java.util.ArrayList;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using sequential chaining.
 * 
 * @author Shelby Labuschagne
 * @version 07/4/2016
 */
//make file
public class SCHashTable extends AbstractHashTable {
	
    protected ChainedEntry[] table;
    
    //Create an SCHashTable with DEFAULT_SIZE table. 
    public SCHashTable() 
    { 
      this(DEFAULT_SIZE);
    }//end SCHashTable
    

    //Create an SCHashTable with the given default size table.
    public SCHashTable(int size) 
    { 
      this.table = new ChainedEntry[size];
      super.entries = 0; 
	//incProbeCount();
    }//end SCHashTableSize    
    
    public int tableSize()
    {
	return table.length;
    }//end overriding table size

    protected int findIndex(String word)
    {	
	int hash = -1;
	if (word != null)
	{	
		//create hash address
		hash = hashFunction(word);
	}//end word null if 
   	return hash;
    }//end findindex
   
//overriding methods
//----------------------------------------------------------------------------------------------------------------------------------
	
    //override previous ones
    public boolean containsWord(String word) 
    {
	//get hash
	int hash = hashFunction(word);
	
	boolean answer = false;
	
	//find index
	int position = findIndex(word);
	//chained entry  with this hash at the space
	ChainedEntry entry = table[position];

	if (entry != null)
	{
		//one entry equals the current word
		if (entry.getWord().equals(word))
		{
			answer = true;
		}//end if equals
		
		//need to loop through the chain entries
		while (entry.getNext() != null)
		{
            incProbeCount();			
            if (entry.getWord().equals(word))//if at pos is the same as the current word
			{
				answer= true;
				break;
			}//end if
			entry=entry.getNext();
		}//end while

		//last  entry equals the current word
		if (entry.getWord().equals(word))
		{
			answer = true;
		}//end if equals
	}//end if 
	return answer;
    }//end containsWord
    
    public List<Definition> getDefinitions(String word) 
    {
	//get hash
	int hash = hashFunction(word);
	//create a list
	List<Definition> defs = new ArrayList<Definition>();
	//find position
        int position = findIndex(word);
	//chained entry  with this position at the space
	ChainedEntry entry = table[position];
      
	//checks to see if word in the actual table
	if(containsWord(word))
	{
		while (entry != null)
		{
		    incProbeCount();	
            if (entry.getWord().equals(word))//word same as word therefore get definition
			{
				defs = entry.getDefinitions();
				return defs;
			}//end if
			entry = entry.getNext();

            if (entry == null)
			{
				defs = entry.getDefinitions();
                return defs;
				//break;
			}//end if
		}//end while loop
            
	}//end contains word
	return null;
    }//end getDefinitions
    
    public void insert(String word, Definition definition) 
    {   
        //create hash to show where it stores to
	    int hash = hashFunction(word);

	//chained entry  with this 
	ChainedEntry newEntry = new ChainedEntry(word);
	
	//find position
    int position = findIndex(word);
	ChainedEntry entry = table[position];

	if (containsWord(word))
	{
		//see if word is in dictionary already, add it
		if (entry.getWord().equals(word))
		{
			entry.addDefinition(definition);
		}//end if in 	

		//while there are words in the chained entry just not the right ones
		while(entry.getNext() != null)
		{
            incProbeCount();
			if (entry.getWord().equals(word))
			{
				entry.addDefinition(definition);
				break;
			}//end if
			entry = entry.getNext();
		}//end while loop
        
	}//end 
	//if the word is not in the dictionary at all
		if (containsWord(word) == false)
		{
			if (table[position] == null)//nuthing there, then add it
			{
				table[position] = newEntry;
				newEntry.addDefinition(definition);
				entries = entries +1;
			}//end if 
			else//sumtin there add it
			{
				entry = table[position];
				entry = new ChainedEntry(word,entry);
				table[position] = entry;
				entry.addDefinition(definition);
				entries = entries +1;
				
			}//end else
		}//end else if
    }//end insert


//these methods are fine
//_________________________________________________________________________________________________________________________________
    //overides previous dump in AbstractHashTable-Method provided to facilitate testing and debugging.
    @Override
	public void dump() 
	{
		ChainedEntry[] table = this.table;
		for(int i=0; i<tableSize(); i++) 
		{
			if (table[i]==null) 
			{
				System.out.printf("\n%6d : %s", i, table[i]);
			}//end if 
			else 
			{
				ChainedEntry entry = table[i];
				while (entry!=null) 
				{
					System.out.printf("\n%6d : %s", i, entry);
					entry = entry.getNext();
				}//end while
			}//end else
		}//end for 
		System.out.printf("\n#Entries: %d.", this.entries);
	}//end dump

    //Generate a hash code for the given key using algorithm in Weiss. (For use by sub classes.)
    protected int hashFunction(String key) 
    {
         int hashVal = 0;
    
         for(int i = 0; i<key.length(); i++) 
             hashVal = 37 * hashVal+key.charAt(i);
         
         hashVal %= table.length;
    
         if (hashVal < 0 ) 
         {
            hashVal+=table.length;
         }//end if 
    return hashVal; 
    }
	
}//end class
