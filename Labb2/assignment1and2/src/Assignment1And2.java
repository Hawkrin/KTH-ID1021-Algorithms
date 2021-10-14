import java.util.Scanner;

/**
 * A class consisting of insertion sort and a method that counts inversions.
 * 
 * @author Malcolm Liljedahl
 * Date: 21-10-01
 */
public class Assignment1And2 {

    /**
     * A simple test method for the class
     * 
     * @param args --
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of array: ");
        int size = sc.nextInt();
        int[] array = new int[size];

        System.out.println("Enter " + size + " elements");
        for(int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println("\nNumber of inversions are " + inversionCounter(array) + "\n");

        insertionSort(array);
        printArray(array);
    }

    /**
     * Takes in an array and sorts it by creating a reference called current and then compare it
     * with its predecessor element. If the current element is smaller than the predecessor then
     * it compares it with the element before the predecessor. Then the greater elements are moved
     * one position so the lesser element get space.
     * 
     * In-Place, Stable
     * Time complexity -> O(n^2)
     * Memory complexity -> O(1)
     * 
     * @param array  the array to be sorted
     */
    public static void insertionSort(int[] array) {
        int count = 0;
        int current, j;
        for(int i = 1; i < array.length; i++) {
            current = array[i];
            j = i - 1;
            while(j >= 0 && array[j] > current) {
                array[j+1] = array[j];
                count++;
                j--;
            }
            array[j+1] = current;
            printArray(array);
        }
        System.out.println("\nNumber of swaps: " + count);
    }


    /**
     * Method that prints an array
     * 
     * @param array the array to be printed
     */
    public static void printArray(int[] array) {
        int size = array.length;
        for (int i = 0; i < size; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Iterates through the array and for every index it counts all the "upcoming" elements which 
     * are smaller than the current index.
     * 
     * Time complexity: O(n^2) -> two nested loops are used.
     * 
     * @param array the array that is being counted
     * @return the numbers of inversions
     */
    public static int inversionCounter(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    System.out.println("[" + "[" + i + "," + array[i] + "]"+ "[" + j + "," + array[j] + "]" + "]");
                    count++;
                }
            }        
        }
        return count;
    }  
}
