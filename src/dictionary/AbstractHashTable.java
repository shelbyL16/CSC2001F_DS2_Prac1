package dictionary;
import java.util.List;
/**
 * Abstract implementation of dictionary using hash table.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
	//try
public abstract class AbstractHashTable  extends Monitorable implements Dictionary {
    public final static int DEFAULT_SIZE = 50;
 
    protected Entry[] table;
    protected int entries;
 
    //Create a table with DEFAULT_SIZE. (For use by sub classes.)
    protected AbstractHashTable() { this(DEFAULT_SIZE); }
    
    //Create a table with the given default size. (For use by sub classes.) 
    protected AbstractHashTable(final int size) 
    { 
        this.table = new Entry[size];
        this.entries = 0;
    }//end
   
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
       
    public boolean containsWord(String word) 
    {
      int find = findIndex(word);
      
      if (find == -1)
         return false;
      else if (find != -1)
      {
         if (table[find] == null)
         {
            return false;
         }//end
         else
            return true;
      }//end else 
        return false;
    }//end containsWord
    
    public List<Definition> getDefinitions(String word) 
    {
      int find = findIndex(word);
      
      if (find == -1)
         return null;
      else if (find != -1)
      {
         if (table[find] == null)
         {
            return null;
         }//end
         else if ((table[find] != null))
         {
            return table[find].getDefinitions();
         }//end
      }//end else 
        return null;
    }//end getDefinitions
    
    public void insert(String word, Definition definition) 
    {   
        //calls hash function     
        int index = findIndex(word);
        //check full table
        if (index == -1)
        {
            //rebuild();
        }//end check table full
        
        else if (table[index] == null)
        {
            Entry e = new Entry(word);
            table[index] = e;
            table[index].addDefinition(definition);
	    entries++;
        }//end check table full
        
        else if (table[index].getWord().equals(word))
        {
            table[index].addDefinition(definition);
        }//end check table full
    }//end insert
    

    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new Entry[this.table.length];this.entries = 0;  }
    
    public int size() { return this.entries; }
    
    

    
    /* Hash Table Functions */
    
    public double loadFactor() { return entries/(double)table.length; }

    /**
     * Method called by <code>insert()</code> when the table needs enlarging.
     * <p>
     * Sub classes should override as required.
     */
    protected void rebuild() {
          throw new IllegalStateException("Hashtable:insert(): table is full.");
    }
    
    
    /**
     * Find the index for entry: if entry is in the table, then returns its position; 
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     * 
     */
    protected abstract int findIndex(String word);
        
        
    
    /**
     * Prints contents of table to screen. (Method provided to facilitate testing and debugging.) 
     */
    public void dump() {
        Entry[] table = this.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
        System.out.printf("\n#Entries: %d.", this.entries);
    }
    
    /**
     * Obtain a list of the entries in the dictionary. (Method to facilitate testing and debugging.) 
     */
    public java.util.ArrayList<Entry> getWords() {
        java.util.ArrayList<Entry> entries = new java.util.ArrayList<Entry>();
        for (int i=0; i<this.table.length; i++) {
            if (this.table[i]!=null) {
                entries.add(table[i]);
            }
        }
        return entries;
    }
        
}
