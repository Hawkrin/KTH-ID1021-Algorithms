/*

package src;

import libs.DoubleLinkedCircularList;

public class A5HashTable<Key, Value> {
    private static final int INIT_CAPACITY = 997;
    private int elements;       // number of key-value elements
    private int tableSize;       // hash table size
    private Node[] hashTable;   // array of linked-list symbol tables

    // a helper linked list data type
    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    // create separate chaining hash table
    public A5HashTable() {
        this(INIT_CAPACITY);
    } 

    // create separate chaining hash table with tableSize lists
    public A5HashTable(int tableSize) {
        this.tableSize = tableSize;
        hashTable = new Node[tableSize];
    } 


    // hash value between 0 and tableSize-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    } 

    // return number of key-value elements in symbol table
    public int size() { return elements; } 

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    } 

    // return value associated with key, null if no such key
    public Value get(Key key) {
        int i = hash(key);
        for (Node x = hashTable[i]; x != null; x = x.next) {
            if (key.equals(x.key)) return (Value) x.val;
        }
        return null;
    }

    // insert key-value pair into the table
    public void put(Key key, Value val) {
        if (val == null) {
            delete(key);
            return;
        }
        int i = hash(key);
        for (Node x = hashTable[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        elements++;
        hashTable[i] = new Node(key, val, hashTable[i]);
    }

    // delete key (and associated value) from the symbol table
    public void delete(Key key) {
        throw new UnsupportedOperationException("delete not currently supported");
    }

    // return all keys as an Iterable
    public Iterable<Key> keys()  {
        DoubleLinkedCircularList<Key> queue = new DoubleLinkedCircularList<Key>();
        for (int i = 0; i < tableSize; i++) {
            for (Node x = hashTable[i]; x != null; x = x.next) {
                queue.enqueue((Key) x.key);
            }
        }
        return queue;
    }

   
    public static void print(A5HashTable<String, Integer> hashTable){
        for(String words : hashTable.keys()) {
            System.out.println(words + " " + hashTable.get(words));
        }
    }
    
}*/

