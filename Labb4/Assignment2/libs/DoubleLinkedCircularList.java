import java.util.Iterator;

/**
 * This class implements a generic iterable FIFO doubly linked circular queue. All nodes have
 * references to the next and the previous node. The last node called tail has it's next node
 * referenced to the first node called head. The first node called head has it's previous node
 * referenced to the last node.
 * 
 * The nodes are created via a inner class called Node. To be able to iterate through this list
 * another inner class called Queueiterator is also implemented.
 * 
 * @autor Malcolm Liljedahl
 * Date: 21-10-01
 * 
 */
public class DoubleLinkedCircularList <Item> implements Iterable <Item> {

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
     * Returns the size of the linkedlist
     * 
     * @return the size
     */
    public int getSize() { return length; }

    /**
     * Creates a new node and checks if the current head node exist or not. If not the new node
     * becomes both the head the the tail node with all the references pointing from and towards
     * it. If the head node exists the new node is being referenced to the next of the tail. More
     * references are implemented to make it "double circular"
     * 
     * @param item the specified type to queue
     */
    public void enqueue(Item item) {
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
     * If the list is empty an exception is thrown, because you can't dequeue an empty list.
     * A new reference called item are created and points towards tail.item. If the head and tail
     * is the same, aka there're only one node then the head and tail references are set to null.
     * Else the head is being referenced to the next node in line and the tail is being referenced
     * to the new head.
     * 
     * @return the dequeued item
     */
    public Item dequeue() {
        if(isEmpty()) {
            throw new IndexOutOfBoundsException("NO NODES");
        }
        Item item = head.item;
        if(head == tail) {
            head = tail = null;
        }
        else {
            head = head.next;
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
