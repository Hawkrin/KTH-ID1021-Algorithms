import java.util.Scanner;

/**
 * Class that tests the DoubleLinkedCircularList class.
 */
public class Assignment3TestClass {
    

    /**
     * Test method for the DoubleLinkedCircularList class.
     * 
     * @param args
     */
    public static void main(String[] args){
    
        queueTester();
        //iteratorTester();
       
    }

    /**
     * A test method that queues and dequeues string elements depending on input from the use
     * 
     */
    public static void queueTester() {
        DoubleLinkedCircularList <Integer> queueTest = new DoubleLinkedCircularList <Integer>();
        boolean input = false;
        do {
            System.out.println("Enter 1 to enqueue or 2 to dequeue: ");
            Scanner sc = new Scanner(System.in);
            int switcher = sc.nextInt();
                switch(switcher) {
                    case 1:
                        System.out.println("Enqueue: ");
                        queueTest.enqueue(sc.nextInt());
                        System.out.println(queueTest.toString());
                    break;   
                    case 2:
                        queueTest.dequeue();
                        System.out.println(queueTest.toString()); 
                    break;
                    default:
                        System.out.println("Done");
                        input = true;
                    break;
                }
            } while (!input);
    }

    /**
     * A test method that tests iterating through a list.
     * 
     */
    public static void iteratorTester() {
        DoubleLinkedCircularList <Integer> queueTest = new DoubleLinkedCircularList <Integer>();
    
        System.out.println("Current List: ");
        queueTest.enqueue(1);
        queueTest.enqueue(2);
        queueTest.enqueue(3);
        queueTest.enqueue(4);
        queueTest.enqueue(5);
        System.out.println(queueTest.toString() + "\n");

       queueTest.forEach( (item) -> { System.out.println(item); });

    }

   
   
    
}
