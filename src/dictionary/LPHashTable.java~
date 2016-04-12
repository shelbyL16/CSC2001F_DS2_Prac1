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
    
      //create general variable declarations
      int answerInt = -1;
      boolean answerBool = false;
      int count = 0; 

      //create hash address
      int hash = hashFunction(word);
      
       if (word != null)
       {
	      for (Entry entry : table)
	      {
			if (entry != null)
			{
				if (entry.getWord().equals(word))	
				{
					answerInt = count;
					answerBool = true;
                    
					//break;
				}//end if equals
			}//end if 
			count = count +1;
	      }//end for loop

		if (answerBool == false)
		{
		while (table[hash] != null)
		{
			hash = hash+1;
			hash = hash % table.length;
			incProbeCount();
            //breaking the stuck while loop
            if(hash == hashFunction(word))
            {
                break;
            }//end while
		}//end while loop
		answerInt = hash;
		}//end if
      }//end word null
	return answerInt;
   }//end findindex

}//end class
