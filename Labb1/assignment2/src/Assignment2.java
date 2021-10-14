import java.util.Scanner;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

/**
 * This program reads input from the user via System.in and prints it to the console 
 * via System.out. The printout is in reversed order from the input and there are two methods
 * doing this in different ways.
 * 
 * The iterative method turns the string into an array and then pushes each element/character
 * onto a stack until the nextline character "\n". Then it pops the stack and prints out the
 * elements in reversed order.
 * 
 * @autor Malcolm Liljedahl
 * Date: 21-10-01
 * 
 */
public class Assignment2 {
    static Scanner sc = new Scanner(System.in);
    static Stack<Character> stack2 = new Stack<Character>();

    /**
     * Method that tests the stack methods
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Enter a string for the iterative function: ");
        iterativeStacker();
        System.out.println("\n");
        System.out.println("Enter a string for the recursive function: ");
        recursiveStacker();
    }

    /**
     * Reads in a string of various size from the user and turns it into a character array. Then
     * loops through the array and while the current index is no equal to "\n" the element is
     * being pushed to the stack. Then we go through the array backwards and pop all elements
     */
    public static void iterativeStacker(){
        Stack<Character> stack1 = new Stack<Character>();
        String scanner1 = sc.nextLine();
        char[] characters = scanner1.toCharArray();

        for(int i = 0; i < characters.length; i++) {
            if(characters[i] != '\n') {
                stack1.push(characters[i]);
            }    
        }
        for(int i = 0; i < characters.length; i++) {
            if(!stack1.isEmpty()) {
                System.out.println(stack1.pop());
            }
        }
    }

    /**
     * This method reads in characters via stdin using the function readChar and if the input
     * is not equal to '\n' it's push onto the stack. Then the function calls itself until the
     * input is '\n' and then the stack is pop instead. The return is used to avoid underflow.
     */
    public static void recursiveStacker(){
        char character = StdIn.readChar();
        if(character != '\n') {
            stack2.push(character);
            recursiveStacker();
        }
        else {
            return;
        }
        System.out.println(stack2.pop());
    }
}
