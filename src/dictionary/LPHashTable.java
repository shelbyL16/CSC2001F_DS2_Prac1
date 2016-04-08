package dictionary;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using linear probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class LPHashTable extends AbstractHashTable {

    //Create an LPHashTable with DEFAULT_SIZE table. 
    public LPHashTable() 
    { 
      super(); 
    }//end LPHashTable
    
    //Create an LPHashTable with the given default size table.
    public LPHashTable(int size) 
    { 
      super(size); 
    }//end LPHashTableSize    
    
	protected int findIndex(String word)
   {
      //create hash address
		int hash = hashFunction(word);
      int end = table.length;
      
      //check to see if table full
      if (this.loadFactor() == 1)
         return -1;
      
      //check to see if it is already full,if full continue
      while (!table[hash].getWord().equals(word) && table[hash] != null)
      {
         hash = hash++;
         hash = hash%end;
      }//end while loop
      
      //checks to see if it equals word
      if (table[hash].getWord().equals(word))
      {
         return hash;
      }//end if
      
      //if null (ie clustering over) or end
      else if (table[hash] == null)
      {
         return -1;
      }//end else if 
      return 0;
	}//end findindex
}//end class
