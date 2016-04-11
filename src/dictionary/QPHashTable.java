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
	      //create general variable declarations
	      int answerInt = -1;
	      boolean answerBool = false;
	      int count = 0; 
	      int fibonacci = 1;

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
						break;
					}//end if equals
				}//end if 
				count = count +1;
		      }//end for loop

			if (answerBool == false)
			{
			while (table[hash] != null)
			{
				hash = hash - ((fibonacci-1)*(fibonacci-1));
				hash = hash + (fibonacci*fibonacci);
				hash = hash % table.length;
				incProbeCount();
				fibonacci = fibonacci+1;
            
                 //breaking the stuck while loop
                if(fibonacci <= table.length)
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
