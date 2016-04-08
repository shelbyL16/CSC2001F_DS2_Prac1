package dictionary;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using quadratic probing.
 * 
 * @author Shelby Labuschagne
 * @version 07/4/2016
 */
public class QPHashTable extends AbstractHashTable {

    //Create an QPHashTable with DEFAULT_SIZE table. 
    public QPHashTable() 
    { 
      super(); 
    }//end QPHashTable
    
    //Create an QPHashTable with the given default size table.
    public QPHashTable(int size) 
    { 
      super(size); 
    }//end QPHashTableSize    
    
	protected int findIndex(String word)
   {
      //create hash address
		int hash = hashFunction(word);
      int end = table.length;
      int i = 0;
      
      //find length of table to see if full
      int len = table.length;
          
      //check to see if table full
      if (this.loadFactor() == 1)
         return -1;
      
      //check to see if it is already full,if full continue (actual entry)
      while (table[hash].getWord() != word && table[hash] != null && hash != end  )
      {
         hash = hash+(i*i);
         i++;
         hash = hash%len;
      }//end while loop
      
      //checks to see if it equals word
      if (table[hash].getWord().equals(word))
      {
         return hash;
      }//end if
      
      //if null (ie clustering over) or end
      else if (table[hash] == null || hash == end)
      {
         return -1;
      }//end else if 
      return 0;
	}//end findindex
   
}//end class
