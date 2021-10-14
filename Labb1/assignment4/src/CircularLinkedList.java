import java.util.Iterator;

/**
 * This class implements a generic double iterable circular linked list which includes functions
 * to remove and insert elements both from the front and from the back. It's also circular which
 * means that the next reference of the tail node points towards the head node.
 * 
 * An inner class called Node is created and it's where the elements of the list are made. An
 * additional inner class called QueueIterator is created and provides functions for iterating 
 * through the list.
 * 
 * @autor Malcolm Liljedahl
 * Date: 21-10-01
 * 
 */
public class CircularLinkedList <Item> implements Iterable <Item> {
    private Node head = null;
    private Node tail = null;
    private int length;

    private class Node {
        Item item;
        Node next;  
        Node previous;

        public Node(Item item) {
            this.item = item;
        }
    }

    /**
     * Check if the queue is empty
     * 
     * @return true if empty
     */
    public boolean isEmpty() { return head == null; }

    /**
     * Returns the size of the list
     * 
     * @return the size
     */
    public int getSize(){ return length; }


    /**
     * Creates a new node and checks if the head node is null. 
     * If the head is null, the new node becomes the first node and all references are going
     * from and to this node.
     * Else the new node is added to the back of the queue and all references to and from that
     * node are updated.
     * 
     * @param item the specified type to queue
     */
    public void addFromBack(Item item) {
        Node newNode = new Node(item);
        if(head == null) { 
            head = newNode;
            head.next = head;
            head.previous = head;
            tail = head;
        }
        else {
            newNode.previous = tail;
            newNode.next = head; 

            head.previous = newNode;
            tail.next = newNode;

            tail = newNode;
        }
        length++;
    }

    /**
     * Creates a new node and checks if the head node is null. 
     * If the head is null, the new node becomes the first node and all references are going 
     * from and to this node.
     * Else the new node is added to the front of the queue and all references to and from that
     * node are updated.
     * 
     * @param item the specified type to queue
     */
    public void addFromFront(Item item) {
        Node newNode = new Node(item);
        if(head == null) { 
            head = newNode;
            head.next = head;
            head.previous = head;
            tail = head;
        }
        else {
            newNode.next = head;
            newNode.previous = tail;

            tail.next = newNode;
            head.previous = newNode;

            head = newNode;
        }
        length++;   
    }

    /**
     * Creates a new item which references to the tail item.
     * If the head == tail is true, the head and tail reference are set to null
     * Else the head reference are dereferenced to the next node in line and the tail node
     * are referenced to the new head. This removes the object from the front.
     * 
     * @return the removed item
     */
    public Item removeFromFront() {
        if(isEmpty()) {
            throw new IndexOutOfBoundsException("NO NODES");
        }
        Item item = head.item;
        if(head == tail) { 
            head = null;
            tail = null; 
        }
        else {
            head = head.next;
            tail.next = head;
            head.previous = tail;
        }
        length--;
        return item;
    }

    /**
     * Creates a new item which references to the tail item.
     * If the head == tail is true, the head and tail reference are set to null
     * Else the tail reference are dereferenced to the previous node in the list and the new 
     * tail node are dereferenced to the head. This removes the object from the back.
     * 
     * @return the removed item
     */
    public Item removeFromBack() {
        if(isEmpty()) {
            throw new IndexOutOfBoundsException("NO NODES");
        }
        Item item = tail.item;
        if(head == tail) { 
            tail = null;
            head = null;
        }
        else {
            tail = tail.previous;
            tail.next = head;
            head.previous = tail;
        }
        length--;
        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node currentNode = head;
        if (this.isEmpty()) { return sb.append("[]").toString(); }
        while(currentNode.next != head) {
            sb.append("[" + currentNode.item + "],");
            currentNode = currentNode.next;
        }
        sb.append("[" + currentNode.item + "],");
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }   

    private class QueueIterator implements Iterator <Item> {
        private Node current = head;
        private boolean completedCircle = false;

        @Override
        public boolean hasNext() {
            if(length == 0) { return false; }
            return current != head || !completedCircle;   
        }

        @Override
        public Item next() {
            if (current.next == head) { completedCircle = true; }
            Item item = current.item; //get data
			current = current.next; //move to next node
			return item;
        }
    }
}
