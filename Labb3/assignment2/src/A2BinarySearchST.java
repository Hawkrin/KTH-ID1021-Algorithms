package src;

import java.util.NoSuchElementException;
import libs.DoubleLinkedCircularList;

/**
 * @author Malcolm Liljedahl
 * Created 2021-10-01
 * 
 * This class consists of a Doubly linked circular FIFO queue that's implemented as a binary
 * search table. The Binary Search table is based upon princetons BST.java, but modified.
 * 
 */
public class A2BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int size = 0;

    /**
     * Initializes an empty symbol table.
     */
    public A2BinarySearchST() { this(INIT_CAPACITY); }

    /**
     * Initializes an empty symbol table with the specified initial capacity.
     * @param capacity the maximum capacity
     */
    @SuppressWarnings("unchecked")
    public A2BinarySearchST(int capacity) { 
        keys = (Key[]) new Comparable[capacity]; 
        vals = (Value[]) new Object[capacity]; 
    }   

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= size;
        Key[] temporaryKey = (Key[]) new Comparable[capacity];
        Value[] temporaryValue = (Value[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temporaryKey[i] = keys[i];
            temporaryValue[i] = vals[i];
        }
        vals = temporaryValue;
        keys = temporaryKey;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() { return size; }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return true if empty, false if not       
     */
    public boolean isEmpty() { return size() == 0; }


    /**
     * Checks if the table contains the specified key
     *
     * @param  key the key
     * @return true if containing, otherwise false   
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        return get(key) != null;
    }

    /**
     * Returns a keys value from the table
     *
     * @param  key the key
     * @return the value associated with the key, if the key doesn't exist null is returned      
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        if (isEmpty()) { return null; }
        int i = rank(key); 
        if (i < size && keys[i].compareTo(key) == 0) { return vals[i]; }
        return null;
    } 

    /**
     * Returns the number of keys in this symbol table strictly less than the key. So this gives
     * us the keys position.
     *
     * @param  key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) { throw new IllegalArgumentException("argument to rank() is null"); }
        int low = 0;
        int high = size-1; 
        while (low <= high) { 
            int mid = low + (high - low) / 2; 
            int compare = key.compareTo(keys[mid]);
            if (compare < 0) { high = mid - 1; }
            else if (compare > 0) { low = mid + 1; }
            else { return mid; }
        } 
        return low;
    } 

    /**
     * Inserts a specified key-value par into the table. If the key already exists, the old value
     * is overwritten by the new value. The pair is deleted if the value is null.
     *
     * @param  key the key
     * @param  value the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value value)  {
        if (key == null) { throw new IllegalArgumentException("the key is null"); }
        if (value == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }
        if (size == keys.length) { resize(2*keys.length); }
        for (int j = size; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = value;
        size++;
    } 

    /**
     * Removes a key-value pair if it exists in the table
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null, so cant be deleted"); }
        if (isEmpty()) { return; }

        int i = rank(key); // compute rank
        if (i == size || keys[i].compareTo(key) != 0) { return; } // key not in table
        for (int j = i; j < size-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        size--;
        keys[size] = null;  // to avoid loitering
        vals[size] = null;

        if (size > 0 && size == keys.length/4) { resize(keys.length/2); } // resize if 1/4 full
    } 

    /**
     * Removes the smallest key-value pair.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) { throw new NoSuchElementException("Symbol table is empty: UNDERFLOW"); }
        delete(min());
    }

    /**
     * Removes the largest key-value pair
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) { throw new NoSuchElementException("Symbol table is empty: UNDERFLOW"); }
        delete(max());
    }

   /**
     * Returns the smallest key in this symbol table.
     *
     * @return the smallest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key min() {
        if (isEmpty()) { throw new NoSuchElementException("Symbol table is empty: UNDERFLOW"); }
        return keys[0]; 
    }

    /**
     * Returns the largest key in this symbol table.
     *
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key max() {
        if (isEmpty()) { throw new NoSuchElementException("Symbol table is empty: UNDERFLOW"); }
        return keys[size-1];
    }

    /**
     * Return the kth smallest key in this symbol table.
     *
     * @param  k the order statistic
     * @return the kth smallest key in this symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *        <em>size</em>–1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    /**
     * Returns the largest key in this symbol table less than or equal to the specified key.
     *
     * @param  key the key
     * @return the largest key in this symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); } 
        int i = rank(key);
        if (i < size && key.compareTo(keys[i]) == 0) { return keys[i]; }
        if (i == 0) { throw new NoSuchElementException("argument to floor() is too small"); }
        else { return keys[i-1]; }
    }

    /**
     * Returns the smallest key in this symbol table greater than or equal to the specified key.
     *
     * @param  key the key
     * @return the smallest key in this symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); } 
        int i = rank(key);
        if (i == size) { throw new NoSuchElementException("argument to ceiling() is too large"); }
        else { return keys[i]; }
    }

    /**
     * Returns the number of keys in this symbol table in the specified range.
     *
     * @param low minimum endpoint
     * @param high maximum endpoint
     * @return the number of keys in this symbol table between and inclusive low and high.
     * @throws IllegalArgumentException if either {@code low} or {@code high} is null
     */
    public int sizeOfRange(Key low, Key high) {
        if (low == null) { throw new IllegalArgumentException("first argument to size() is null"); } 
        if (high == null) { throw new IllegalArgumentException("second argument to size() is null"); }
        if (low.compareTo(high) > 0) { return 0; }
        if (contains(high)) { return rank(high) - rank(low) + 1; }
        else { return rank(high) - rank(low); }
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * Used to iterate over the keys via for each.
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() { return keysInRange(min(), max()); }

    /**
     * Returns all keys in this symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param low minimum endpoint
     * @param high maximum endpoint
     * @return all keys in this symbol table between high and low
     * @throws IllegalArgumentException if either {@code low} or {@code high} is null
     */
    public Iterable<Key> keysInRange(Key low, Key high) {
        if (low == null) { throw new IllegalArgumentException("first argument to keys() is null"); }
        if (high == null) { throw new IllegalArgumentException("second argument to keys() is null"); } 
        DoubleLinkedCircularList<Key> queue = new DoubleLinkedCircularList<Key>(); 
        if (low.compareTo(high) > 0) { return queue; }
        for (int i = rank(low); i < rank(high); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(high)) { queue.enqueue(keys[rank(high)]); }
        return queue; 
    }    
}
    