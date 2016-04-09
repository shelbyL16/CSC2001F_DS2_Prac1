package dictionary;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using sequential chaining.
 * 
 * @author Shelby Labuschagne
 * @version 07/4/2016
 */
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
      this.entries = 0; 
    }//end SCHashTableSize    
    

    protected int findIndex(String word)
    {
	//create hash address
	int hash = hashFunction(word);
	int i =0;
	
	//create a chainedentry	
	ChainedEntry entry = table[hash];

	while (entry != null)
	{
		if (!entry.getWord().equals(word))
		{
			entry.getNext();
			i++;
			incProbeCount();
		}//end if 
		else 
		    return i;	
	}//end null
	
	//next free slot
   	return i+1;
    }//end findindex
   



//overriding methods
//----------------------------------------------------------------------------------------------------------------------------------
	
    //override previous ones
    public boolean containsWord(String word) 
    {
	//get hash
	int hash = hashFunction(word);
	//chained entry  with this hash at the space
	ChainedEntry entry = table[hash];
	boolean answer = false;
	
	//find index
	int position = this.findIndex(word);

	if (entry != null)
	{
		//need to loop through the chain entries
		while (entry.getNext() != null)
		{
			if (entry.getWord().equals(word))//if at pos is the same as the current word
			{
				answer= true;
			}//end if
			else 
			{
				answer= false; 
			}//end else
			entry=entry.getNext();
		}//end while
	}//end if 
	return answer;
    }//end containsWord
    
    public List<Definition> getDefinitions(String word) 
    {
	//get hash
	int hash = hashFunction(word);
	//chained entry  with this hash at the space
	ChainedEntry entry = table[hash];
	
	//find position
        int position = findIndex(word);
      
	//need to loop through the chain entries
	for (int i = 0; i<position; i++)
	{
		if (entry.getWord().equals(word))//word same as word therefore get definition
		{
			return entry.getDefinitions();
		}//end if
		else if (entry.getNext() != null)//word in there but not the word looking for
		{
			entry=entry.getNext();
		}//end elseif
		else 
		{
			return null;
		}//end else
	}//end for loop
	return null;
    }//end getDefinitions
    
    public void insert(String word, Definition definition) 
    {   
        //get hash
	int hash = hashFunction(word);
	//chained entry  with this hash at the space
	ChainedEntry entry = table[hash];
	ChainedEntry newEntry = new ChainedEntry(word);
	
	//find position
        int position = findIndex(word);
      
	//need to loop through the chain entries
	for (int i = 0; i<position; i++)
	{
		if (entry.getWord().equals(word))//word same as word therefore add definition
		{
			entry.addDefinition(definition);
		}//end if
		else if (entry.getNext() == null)//no word in there
		{
			entry = newEntry;
			newEntry.addDefinition(definition);
		}//end elseif
	}//end for loop
    }//end insert

    //overides previous dump in AbstractHashTable-Method provided to facilitate testing and debugging.
    public void dump() 
    {
        ChainedEntry[] table = this.table;
	ChainedEntry e = table[0].getNext();
        
        for(int i=0; i<table.length; i++)
        {
            while(e.getNext() != null)
		{
			System.out.printf("\n4%d : %s", i , e);
			e =e.getNext();
		}//end while loop
        }//end for loop
        System.out.printf("\n#Entries: %d.", this.entries);
    }//end dump
}//end class
