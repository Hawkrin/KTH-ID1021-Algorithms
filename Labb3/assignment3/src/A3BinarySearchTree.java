package src;

import java.util.NoSuchElementException;
import libs.DoubleLinkedCircularList;

/**
 * @author Malcolm Liljedahl
 * Created 2021-10-01
 * 
 * This class consists of a Doubly linked circular FIFO queue that's implemented as a binary
 * search tree. The Binary Search Tree is based upon princetons BST.java, but modified.
 * 
 */
public class A3BinarySearchTree <Key extends Comparable<Key>, Value> {
    private Node root;             

    private class Node {
        private Key key;          
        private Value value;         
        private Node left;
        private Node right;  
        private int size;       

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    /**
     * Init the tree
     */
    public A3BinarySearchTree() {}

    private int size(Node node) {
        if (node == null) { return 0; }
        else { return node.size; }
    }

    public boolean isEmpty() { return size(root) == 0;} 

    /**
     * Checks if the tree already contains the given key.
     * 
     * @param key The key to be searched for.
     * @return true if the tree contains the key, false otherwise.
     * @throws IllegalArgumentException if the key is <code>null</code>.
     */
    public boolean contains(Key key) {
        if (key == null) { throw new IllegalArgumentException("Key is null."); }
        return get(key) != null;
    }
    
    /**
     * Searches for the given key in the tree and returns its value if found.
     * 
     * @param key The key we search for
     * @return the value of the key. Null if empty
     * @throws IllegalArgumentException if the key is <code>null</code>.
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("key is null");
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) { return null; }
        int compare = key.compareTo(node.key);
        if (compare < 0) { return get(node.left, key); }
        else if (compare > 0) { return get(node.right, key); }
        else { return node.value; }
    }

    /**
     * Puts a specified key in its correct position in the binary search tree. If the key
     * already exists in the tree, that keys value will be updated with the new one.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if <code>null</code> is null.
     */
    public void put(Key key, Value value) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) { return new Node(key, value, 1); }
        int compare = key.compareTo(node.key);
        if (compare < 0) {node.left  = put(node.left,  key, value); }
        else if (compare > 0) node.right = put(node.right, key, value);
        else { node.value = value; }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    /**
     * Get the maximum key -> rightmost key
     * 
     * @return the maximum key.
     * @throws NoSuchElementException if the tree is empty.
     */
    public Key max() {
        if (isEmpty()) { throw new NoSuchElementException("The BST is empty."); }
        return max(root);
    }

    private Key max(Node node) {
        if (node.right == null) { return node.key; }
        else { return max(node.right); }       
    }

    /**
     * Gets the minimum key -> leftmost
     * 
     * @return the minimum key.
     * @throws NoSuchElementException is the tree is empty.
     */
    public Key min() {
        if (isEmpty()) { throw new NoSuchElementException("The BST is emtpy."); }
        return min(root);
    }

    private Key min(Node node) {
        if (node.left == null) { return node.key; }
        else { return min(node.left); }
    }

    /**
     * @return an iterable queue holding all of the keys.
     */
    public Iterable<Key> keys() {
        if (isEmpty()) { return new DoubleLinkedCircularList<Key>(); }
        return keys(min(), max());
    }

    /**
     * Inserts all keys in the given key interval into an iterable queue.
     * 
     * @param low  Low limit.
     * @param high High limit.
     * @return an iterable queue holding the keys in the given range.
     * @throws IllegalArgumentException if one of the keys is <code>null</code>.
     */
    public Iterable<Key> keys(Key low, Key high) {
        if (low == null) { throw new IllegalArgumentException("Low key is null."); }
        if (high == null) { throw new IllegalArgumentException("High key is null."); }
        DoubleLinkedCircularList<Key> queue = new DoubleLinkedCircularList<Key>();
        keys(root, queue, low, high);
        return queue;
    }

    private void keys(Node node, DoubleLinkedCircularList<Key> queue, Key low, Key high) {
        if (node == null) { return; }
        int compareLow = low.compareTo(node.key);
        int compareHigh = high.compareTo(node.key);
        if (compareLow < 0) { keys(node.left, queue, low, high); }
        if ((compareLow <= 0) && (compareHigh >= 0)) { queue.enqueue(node.key); }
        if (compareHigh > 0) { keys(node.right, queue, low, high); }
    } 
}
