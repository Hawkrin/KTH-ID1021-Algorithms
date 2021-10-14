package src;

import java.util.Scanner;
import java.util.NoSuchElementException;
import libs.DoubleLinkedCircularList;

/**
 * @author Malcolm Liljedahl
 * Created 2021-10-01
 * 
 * This class consists of a Doubly linked circular FIFO queue that's implemented as a balanced
 * binary search tree. The Binary Search Tree is based upon princetons RedBlackBST.java, 
 * but modified.
 *
 * Time complexity O(logN) <- Insert, search, remove
 * Memory Complexity O(n)
 * 
 */
public class A7RedAndBlackBST <Key extends Comparable<Key>, Value> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private Key key;           
        private Value value;        
        private Node left;
        private Node right;  
        private boolean color;     
        private int size;
               

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }
    /**
     * Initializes an empty symbol table.
     */
    public A7RedAndBlackBST() {}

    private boolean isRed(Node node) {
        if (node == null) { return false; }
        return node.color == RED;
    }

    private int size(Node node) {
        if (node == null) { return 0; }
        return node.size;
    } 

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() { return size(root); }

   /**
     * Checks if table is empty
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() { return root == null; }


    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value of the specified key if it exists in the tree. Otherwise return null
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) { node = node.left; }
            else if (compare > 0) { node = node.right; }
            else { return node.value; }
        }
        return null;
    }

    /**
     * Checks if the tree contains a given key
     * @param key the key
     * @return true if yes, false if no
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) { return get(key) != null; }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param value the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void insert(Key key, Value newValue) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        if (newValue == null) {
            delete(key);
            return;
        }
        root = insert(root, key, newValue);
        root.color = BLACK;
    }

    // insert the key-value pair in the subtree rooted at node
    private Node insert(Node node, Key key, Value newValue) { 
        if (node == null) { return new Node(key, newValue, RED, 1); }
        int compare = key.compareTo(node.key);
        if (compare < 0) { node.left = insert(node.left,  key, newValue); }
        else if (compare > 0) { node.right = insert(node.right, key, newValue); }
        else { node.value   = newValue; }

        // fix-up any right-leaning links
        if (isRed(node.right) && !isRed(node.left)) { node = rotateLeft(node); }
        if (isRed(node.left) && isRed(node.left.left)) { node = rotateRight(node); }
        if (isRed(node.left) && isRed(node.right)) { flipColors(node); }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) { throw new NoSuchElementException("BST underflow"); }
        if (!isRed(root.left) && !isRed(root.right)) { root.color = RED; }
        root = deleteMin(root);
        if (!isEmpty()) { root.color = BLACK; }
    }

    private Node deleteMin(Node newNode) { 
        if (newNode.left == null) { return null; }
        if (!isRed(newNode.left) && !isRed(newNode.left.left)) { newNode = moveRedLeft(newNode); }
        newNode.left = deleteMin(newNode.left);
        return balance(newNode);
    }


    /**
     * Removes the largest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) { throw new NoSuchElementException("BST underflow"); }
        if (!isRed(root.left) && !isRed(root.right)) { root.color = RED; }
        root = deleteMax(root);
        if (!isEmpty()) { root.color = BLACK; }
    }

    private Node deleteMax(Node newNode) { 
        if (isRed(newNode.left)) { newNode = rotateRight(newNode); }
        if (newNode.right == null) { return null; }
        if (!isRed(newNode.right) && !isRed(newNode.right.left)) { newNode = moveRedRight(newNode); }
        newNode.right = deleteMax(newNode.right);
        return balance(newNode);
    }

    /**
     * Removes the specified key-value pair from the symbol table. If it exists  
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) { 
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        if (!contains(key)) return;
        if (!isRed(root.left) && !isRed(root.right)) { root.color = RED; }
        root = delete(root, key);
    }

    private Node delete(Node newNode, Key key) {
        if (key.compareTo(newNode.key) < 0)  {
            if (!isRed(newNode.left) && !isRed(newNode.left.left)) { newNode = moveRedLeft(newNode); }
            newNode.left = delete(newNode.left, key);
        }
        else {
            if (isRed(newNode.left)) { newNode = rotateRight(newNode); }
            if (key.compareTo(newNode.key) == 0 && (newNode.right == null)) { return null; }
            if (!isRed(newNode.right) && !isRed(newNode.right.left)) { newNode = moveRedRight(newNode); }
            if (key.compareTo(newNode.key) == 0) {
                Node otherNode = min(newNode.right);
                newNode.key = otherNode.key;
                newNode.value = otherNode.value;
                newNode.right = deleteMin(newNode.right);
            }
            else { newNode.right = delete(newNode.right, key); }
        }
        return balance(newNode);
    }

   private Node rotateRight(Node newNode) {
        assert (newNode != null) && isRed(newNode.left);
        Node otherNode = newNode.left;
        newNode.left = otherNode.right;
        otherNode.right = newNode;
        otherNode.color = otherNode.right.color;
        otherNode.right.color = RED;
        otherNode.size = newNode.size;
        newNode.size = size(newNode.left) + size(newNode.right) + 1;
        return otherNode;
    }

    private Node rotateLeft(Node newNode) {
        assert (newNode != null) && isRed(newNode.right);
        Node otherNode = newNode.right;
        newNode.right = otherNode.left;
        otherNode.left = newNode;
        otherNode.color = otherNode.left.color;
        otherNode.left.color = RED;
        otherNode.size = newNode.size;
        newNode.size = size(newNode.left) + size(newNode.right) + 1;
        return otherNode;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) { 
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) { 
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left)) { node = rotateLeft(node); }
        if (isRed(node.left) && isRed(node.left.left)) { node = rotateRight(node); }
        if (isRed(node.left) && isRed(node.right)) { flipColors(node); }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Returns the height of the BST (for debugging).
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() { return height(root); }
    private int height(Node node) {
        if (node == null) { return -1; }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Returns the smallest key in the symbol table.
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) { throw new NoSuchElementException("empty symbol table"); }
        return min(root).key;
    } 
    private Node min(Node node) { 
        if (node.left == null) { return node; }
        else { return min(node.left); }
    } 

    /**
     * Returns the largest key in the symbol table.
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) { throw new NoSuchElementException("empty symbol table"); }
        return max(root).key;
    } 
    private Node max(Node node) { 
        if (node.right == null) { return node; }
        else { return max(node.right); }
    } 


    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        if (isEmpty()) { throw new NoSuchElementException("empty symbol table"); }
        Node newNode = floor(root, key);
        if (newNode == null) { throw new NoSuchElementException("node is null"); }
        else { return newNode.key; }
    }    

    private Node floor(Node node, Key key) {
        if (node == null) { return null; }
        int compare = key.compareTo(node.key);
        if (compare == 0) { return node; }
        if (compare < 0) { return floor(node.left, key); }
        Node newNode = floor(node.right, key);
        if (newNode != null) { return newNode; }
        else { return node; }
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        if (isEmpty()) { throw new NoSuchElementException("symbol table is empty"); }
        Node newNode = ceiling(root, key);
        if (newNode == null) { throw new NoSuchElementException("newNode is too small"); }
        else { return newNode.key; }
    }

    private Node ceiling(Node node, Key key) {  
        if (node == null) { return null; }
        int compare = key.compareTo(node.key);
        if (compare == 0) { return node; }
        if (compare > 0) { return ceiling(node.right, key); }
        Node newNode = ceiling(node.left, key);
        if (newNode != null) { return newNode; }
        else { return node; }
    }

    /**
     * Return the key in the symbol table of a given {@code rank}.
     *
     * @param  rank the order statistic
     * @return the key in the symbol table of given {@code rank}
     * @throws IllegalArgumentException
     */
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument is invalid: " + rank);
        }
        return select(root, rank);
    }

    private Key select(Node node, int rank) {
        if (node == null) { return null; }
        int leftSize = size(node.left);
        if (leftSize > rank) { return select(node.left, rank); }
        else if (leftSize < rank) { return select(node.right, rank - leftSize - 1); }
        else { return node.key; }
    }

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        return rank(key, root);
    } 

    private int rank(Key key, Node node) {
        if (node == null) { return 0; }
        int compare = key.compareTo(node.key); 
        if (compare < 0) { return rank(key, node.left); }
        else if (compare > 0) { return 1 + size(node.left) + rank(key, node.right); }
        else { return size(node.left); }
    } 

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * 
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new DoubleLinkedCircularList<Key>();
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range
     *
     * @param  low minimum endpoint
     * @param  high maximum endpoint
     * @return all keys in the symbol table between high and low
     * @throws IllegalArgumentException if high or low are null
     */
    public Iterable<Key> keys(Key low, Key high) {
        if (low == null) { throw new IllegalArgumentException("low is null"); }
        if (high == null) { throw new IllegalArgumentException("high is null"); }
        DoubleLinkedCircularList<Key> doubleLinkedCircularList = new DoubleLinkedCircularList<Key>();
        keys(root, doubleLinkedCircularList, low, high);
        return doubleLinkedCircularList;
    } 
    
    private void keys(Node node, DoubleLinkedCircularList<Key> doubleLinkedCircularList, Key low, Key high) { 
        if (node == null) { return; }
        int compLow = low.compareTo(node.key); 
        int compHigh = high.compareTo(node.key); 
        if (compLow < 0) { keys(node.left, doubleLinkedCircularList, low, high); }
        if (compLow <= 0 && compHigh >= 0) { doubleLinkedCircularList.enqueue(node.key); }
        if (compHigh > 0) { keys(node.right, doubleLinkedCircularList, low, high); }
    } 


    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @param  low minimum endpoint
     * @param  high maximum endpoint
     * @return the number of keys in the symbol table between low and high
     * @throws IllegalArgumentException if either low or high are null
     */
    public int size(Key low, Key high) {
        if (low == null) { throw new IllegalArgumentException("low is null"); }
        if (high == null) { throw new IllegalArgumentException("high is null"); }
        if (low.compareTo(high) > 0) { return 0; }
        if (contains(high)) { return rank(high) - rank(low) + 1; }
        else { return rank(high) - rank(low); }
    }

    /**
     * Prints the elements in the tree in either alphabetical or reverse order. Determined by
     * a switch statement.
     * 
     * @param searchTable
     */
    public static void print(A7RedAndBlackBST<String, Integer> searchTable) {
        Scanner scanInput = new Scanner(System.in);
        System.out.println("Press 1 to print the words alphabetically or press 2 to print the words in reverse order ");
        Integer choice = scanInput.nextInt();
        switch(choice) {
            case 1:
                for(String words : searchTable.keys()) {
                    System.out.println(words);
                }
            break;
            case 2:
                for(int i = searchTable.size(); i > 0; i--) {
                    System.out.println(searchTable.select(i-1));
                }
            break;   
            default: 
        }
        scanInput.close();
    }   

}

  


    





