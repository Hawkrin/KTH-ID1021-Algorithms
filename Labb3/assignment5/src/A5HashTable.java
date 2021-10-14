

package src;

import edu.princeton.cs.algs4.SequentialSearchST;
import libs.DoubleLinkedCircularList;

/**
 * @author Malcolm Liljedahl
 * Created 2021-10-01
 * 
 * This class consists of a Doubly linked circular FIFO queue that's implemented as a Hashtable.
 * The Hashtable is based upon princetons A5HashTable.java, but modified.
 * 
 */
public class A5HashTable <Key, Value> {
    private static final int INIT_CAPACITY = 997;
    private int numberOfPairs;                            
    private int tableSize;                                
    private SequentialSearchST<Key, Value>[] array;          


    /**
     * Initializes an empty symbol table.
     */
    public A5HashTable() { this(INIT_CAPACITY); } 

    /**
     * Initializes an empty symbol table with {@code tableSize} chains.
     * @param tableSize the initial number of chains
     * 
     */
    @SuppressWarnings("unchecked")
    public A5HashTable(int tableSize) {
        this.tableSize = tableSize;
        array = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[tableSize];
        for (int i = 0; i < tableSize; i++) {
            array[i] = new SequentialSearchST<Key, Value>();
        }
    } 

    private void resize(int chains) {
        A5HashTable<Key, Value> temp = new A5HashTable<Key, Value>(chains);
        for (int i = 0; i < tableSize; i++) {
            for (Key key : array[i].keys()) {
                temp.put(key, array[i].get(key));
            }
        }
        this.tableSize  = temp.tableSize;
        this.numberOfPairs  = temp.numberOfPairs;
        this.array = temp.array;
    }

    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (tableSize-1);
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() { return numberOfPairs; } 

    /**
     * Returns true if this symbol table is empty.
     *
     * @return true if empty otherwise false
     */
    public boolean isEmpty() { return size() == 0; }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return true if contains key, otherwise false.
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) { throw new IllegalArgumentException("the key is null"); }
        return get(key) != null;
    } 

    /**
     * Returns the value associated with the specified key in this symbol table.
     *
     * @param  key the key
     * @return the key value, if no key returns null
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) { throw new IllegalArgumentException("the key is null"); }
        int i = hash(key);
        return array[i].get(key);
    } 

    /**
     * The key-value pair is being put in the hash table as long as it doesn't exist. If the 
     * key exist, the value is being updated with the new value. If the the number of key-value
     * pairs are 10times the table size, the table size is doubled.
     *
     * @param  key the key
     * @param  value the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value value) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        if (value == null) {
            delete(key);
            return;
        }
        if (numberOfPairs >= 10*tableSize) resize(2*tableSize);
        int i = hash(key);
        if (!array[i].contains(key)) { numberOfPairs++; }
        array[i].put(key, value);
    } 

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) { throw new IllegalArgumentException("the key is null"); }
        int i = hash(key);
        if (array[i].contains(key)) numberOfPairs--;
        array[i].delete(key);
        // halve table size if average length of list <= 2
        if (tableSize > INIT_CAPACITY && numberOfPairs <= 2*tableSize) resize(tableSize/2);
    } 

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        DoubleLinkedCircularList<Key> queue = new DoubleLinkedCircularList<Key>();
        for (int i = 0; i < tableSize; i++) {
            for (Key key : array[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    } 

    /**
     * Function that prints out the keys in the hashtable together with their hash values.
     * 
     * @param hashTable the current hashtable
     */
    public static void print(A5HashTable<String, Integer> hashTable){
        for(String words : hashTable.keys()) {
            System.out.println(words + " " + hashTable.get(words));
        }
    }
}





