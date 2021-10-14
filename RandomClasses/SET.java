import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * This set class represents an ordered set of comparable keys. This implementation is based
 * on a balanced search tree.
 */
public class SET<Key extends Comparable<Key>> implements Iterable<Key> {
    private TreeSet<Key> set;

    /**
     * Initializes an empty set.
     */
    public SET() { set = new TreeSet<Key>(); }

    /**
     * Initializes a new set that is an independent copy of the specified set.
     *
     * @param x the set to copy
     */
    public SET(SET<Key> x) { set = new TreeSet<Key>(x.set); }

    /**
     * Adds the key to this set (if it is not already present).
     *
     * @param  key the key to add
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void add(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        set.add(key);
    }

    /**
     * Returns true if this set contains the given key.
     *
     * @param  key the key
     * @return true if contains key, otherwise false
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        return set.contains(key);
    }

    /**
     * Removes the specified key from this set (if the set contains the specified key).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        set.remove(key);
    }

    /**
     * Returns the number of keys in this set.
     *
     * @return the number of keys in this set
     */
    public int size() { return set.size(); }

    /**
     * Returns true if this set is empty.
     *
     * @return true if empty, otherwise false
     */
    public boolean isEmpty() { return size() == 0; }
 
    /**
     * Returns all of the keys in this set, as an iterator.
     *
     * @return an iterator to all of the keys in this set
     */
    public Iterator<Key> iterator() { return set.iterator(); }

    /**
     * Returns all of the keys in this set, as an iterator in descending order.
     *
     * @return an iterator to all of the keys in this set
     */
    public Iterator<Key> descendingIterator() { return set.descendingIterator(); }

    /**
     * Returns the largest key in this set.
     *
     * @return the largest key in this set
     * @throws NoSuchElementException if this set is empty
     */
    public Key max() {
        if (isEmpty()) { throw new NoSuchElementException("set is empty"); }
        return set.last();
    }

    /**
     * Returns the smallest key in this set.
     *
     * @return the smallest key in this set
     * @throws NoSuchElementException if this set is empty
     */
    public Key min() {
        if (isEmpty()) { throw new NoSuchElementException("called min() with empty set"); }
        return set.first();
    }

    /**
     * Returns the smallest key in this set greater than or equal to {@code key}.
     *
     * @param  key the key
     * @return the smallest key in this set greater than or equal to {@code key}
     * @throws IllegalArgumentException if the key is null
     * @throws NoSuchElementException if there is no such key
     */
    public Key ceiling(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        Key newKey = set.ceiling(key);
        if (newKey == null) { throw new NoSuchElementException("all keys are less than " + key);}
        return newKey;
    }

    /**
     * Returns the largest key in this set less than or equal to {@code key}.
     *
     * @param  key the key
     * @return the largest key in this set table less than or equal to {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     * @throws NoSuchElementException if there is no such key
     */
    public Key floor(Key key) {
        if (key == null) { throw new IllegalArgumentException("key is null"); }
        Key newKey = set.floor(key);
        if (newKey == null) { throw new NoSuchElementException("all keys are greater than " + key); }
        return newKey;
    }

    /**
     * Returns the union of this set and that set.
     *
     * @param  that the other set
     * @return the union of this set and that set
     * @throws IllegalArgumentException if {@code that} is {@code null}
     */
    public SET<Key> union(SET<Key> that) {
        if (that == null) { throw new IllegalArgumentException("key is null"); }
        SET<Key> newKey = new SET<Key>();
        for (Key otherKey : this) { newKey.add(otherKey); }
        for (Key otherKey : that) { newKey.add(otherKey); }
        return newKey;
    }

    /**
     * Returns the intersection of this set and that set.
     *
     * @param  that the other set
     * @return the intersection of this set and that set
     * @throws IllegalArgumentException if {@code that} is {@code null}
     */
    public SET<Key> intersects(SET<Key> that) {
        if (that == null) { throw new IllegalArgumentException("key is null"); }
        SET<Key> newKey = new SET<Key>();
        if (this.size() < that.size()) {
            for (Key x : this) {
                if (that.contains(x)) { newKey.add(x); }
            }
        }
        else {
            for (Key x : that) {
                if (this.contains(x)) { newKey.add(x); }
            }
        }
        return newKey;
    }

    /**       
     * Compares this set to the specified set.
     *       
     * @param  other the other set
     * @return true if equal otherwise false
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        if (other == this) { return true; }
        if (other == null) { return false; }
        if (other.getClass() != this.getClass()) { return false; }
        SET<Key> that = (SET<Key>) other;
        return this.set.equals(that.set);
    }

    /**
     * Returns a string representation of this set.
     *
     * @return a string representation of this set
     */
    @Override
    public String toString() {
        String string = set.toString();
        return "{ " + string.substring(1, string.length() - 1) + " }";
    }
}
