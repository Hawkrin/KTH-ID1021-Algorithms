import java.util.Scanner;

public class Assignment4TestClass {

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
     * A test method that add and remove elements depending on input from the user
     * 
     */
    public static void queueTester() {
        CircularLinkedList <Integer> queueTest = new CircularLinkedList <Integer>();
        boolean input = false;
        do {
            System.out.println("Enter 1 to add from the back:\nEnter 2 to add from the front:\n" 
                                +"Enter 3 to remove from the front:\nEnter 4 to remove from the back: ");
            Scanner sc = new Scanner(System.in);
            Integer switcher = sc.nextInt();
            switch(switcher) {
                case 1:
                    System.out.println("Element to add: ");
                    queueTest.addFromBack(sc.nextInt());
                    System.out.println(queueTest.toString() + "\n");
                break; 
                case 2:
                    System.out.println("Element to add: ");
                    queueTest.addFromFront(sc.nextInt());
                    System.out.println(queueTest.toString() + "\n");
                break;  
                case 3:
                    queueTest.removeFromFront();
                    System.out.println(queueTest.toString() + "\n"); 
                break;
                case 4:
                    queueTest.removeFromBack();
                    System.out.println(queueTest.toString() + "\n"); 
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
        CircularLinkedList <Integer> queueTest = new CircularLinkedList <Integer>();
    
        System.out.println("Current List: ");
        queueTest.addFromBack(1);
        queueTest.addFromBack(2);
        queueTest.addFromBack(3);
        queueTest.addFromBack(4);
        queueTest.addFromBack(5);
        System.out.println(queueTest.toString() + "\n");

       queueTest.forEach( item -> {System.out.println(item);});

    }
    
}
