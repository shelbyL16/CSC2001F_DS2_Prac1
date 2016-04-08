package dictionary;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using sequential chaining.
 * 
 * @author Shelby Labuschagne
 * @version 07/4/2016
 */
public class SCHashTable extends AbstractHashTable {

    //Create an SCHashTable with DEFAULT_SIZE table. 
    public SCHashTable() 
    { 
      super(); 
    }//end SCHashTable
    
    //Create an SCHashTable with the given default size table.
    public SCHashTable(int size) 
    { 
      super(size); 
    }//end SCHashTableSize    
    
	protected int findIndex(String word)
   {
   
      //check to see if table full
      if (table.length < 1)
         return -1; 
         
      //create hash address
		int hash = hashFunction(word);
      
      return hash;
      
  	}//end findindex
   
    //overides previous dump in AbstractHashTable//Method provided to facilitate testing and debugging.
    public void dump() 
    {
        Entry[] table = this.table;
        
        for(int i=0; i<table.length; i++)
        {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
        
        System.out.printf("\n#Entries: %d.", this.entries);
    }//end dump
}//end class
