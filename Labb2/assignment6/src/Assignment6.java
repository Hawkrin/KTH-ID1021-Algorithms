package src;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/**
 *  This class contains of the "original" quicksort algorithm as well as the Median-of-three version.
 *  The test method compares the execution time of these algorithms.
 * 
 * @autor Malcolm Liljedahl
 * Date: 21-10-01
 */
public class Assignment6 {

    /**
     * Test method for the class
     * 
     * @param args --
     */
    public static void main(String[] args) {
        System.out.println("Enter the size of the array: ");
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        int[] array = new int [sc.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt();
        }

        int[] quickCopy = Arrays.copyOf(array, array.length);
        int[] quickCopyMedian = Arrays.copyOf(array, array.length);

        long quickTimeStart = System.nanoTime();
        quickSort(quickCopy, 0, quickCopy.length-1);
        long quickTimeEnd = System.nanoTime();
        System.out.println("Elapsed Time for quickSort in nano seconds: "+ (quickTimeEnd-quickTimeStart));

        long quickTimeMedianStart = System.nanoTime();
        quickSortMedianOfThree(quickCopyMedian, 0, quickCopyMedian.length-1);
        long quickTimeMedianEnd = System.nanoTime();
        System.out.println("Elapsed Time for quickSort with Median-of-Three partioning in nano seconds: "+ (quickTimeMedianEnd-quickTimeMedianStart));
        
        /*
        int[] test = {5,4,3,2,1,2,5,67,4,4,56,8,9,6,5,2,1,4,5,7,98,7,6,5,4,3};
        int[] test2 = {5,4,3,2,1,2,5,67,4,4,56,8,9,6,5,2,1,4,5,7,98,7,6,5,4,3};
        quickSort(test, 0, test.length-1);
        quickSortMedianOfThree(test2, 0, test2.length-1);
        */

    }
    
    /**
     * Takes in an array and the first and last index of it. If the first index is smaller than
     * the last index then the function partition is called and then the halves are sorted via
     * recursion calls.
     * 
     * Divide and Conquerer, Recursive, Stable, In-place
     * 
     * O(n^2) - worst time coplexity
     * O(NlogN) - average time complexity
     * 
     * @param array the array to be sorted
     */
    public static void quickSort(int[] array, int firstIndex, int lastIndex) {
        if(firstIndex < lastIndex) {
            int partition = partition(array, firstIndex, lastIndex);
            quickSort(array, firstIndex, partition - 1); //sort left
            quickSort(array, partition + 1, lastIndex); // sort right
        }
    }

    /**
     * This method chooses a pivot(last element in this case) and places it in it's correct
     * position. Then it places all smaller elements to the left of the pivot and the larger
     * elements to the right.
     * 
     * @param array array to be sorted
     * @param firstIndex first index of array
     * @param lastIndex last index of array
     * @return the partitionIndex
     */
    private static int partition(int[] array, int firstIndex, int lastIndex){
        int pivot = array[firstIndex];
        int partitionIndex = firstIndex + 1;
        for(int i = firstIndex + 1; i <= lastIndex; i++) {
            if(array[i] < pivot) {
                swap(array, partitionIndex, i);
                partitionIndex++;
            }
        }
        swap(array, firstIndex, partitionIndex-1); 
        return partitionIndex-1;
    }

    /**
     * Takes in an array and the first and last index of it. If the first index is smaller than
     * the last index then the function partition is called and then the halves are sorted via
     * recursion calls.
     * 
     * Divide and Conquerer, Recursive, Stable, In-place
     * 
     * O(n^2) - worst time coplexity
     * O(NlogN) - average time complexity
     * 
     * @param array the array to be sorted
     */
    public static void quickSortMedianOfThree(int[] array, int firstIndex, int lastIndex) {
        if(firstIndex < lastIndex) {
            int partition = partitionMedianOfThree(array, firstIndex, lastIndex);
            quickSort(array, firstIndex, partition - 1); //sort left
            quickSort(array, partition + 1, lastIndex); // sort right
        }
    }

      /**
     * This method chooses a pivot via the medianOfThree method and places it in it's correct
     * position. Then it places all smaller elements to the left of the pivot and the larger
     * elements to the right.
     * 
     * @param array array to be sorted
     * @param firstIndex first index of array
     * @param lastIndex last index of array
     * @return
     */
    private static int partitionMedianOfThree(int[] array, int firstIndex, int lastIndex){
        int pivot = array[medianOfThree(array, firstIndex, lastIndex)];
        int partitionIndex = firstIndex + 1;
        for(int i = firstIndex + 1; i <= lastIndex; i++) {
            if(array[i] < pivot) {
                swap(array, partitionIndex, i);
                partitionIndex++;
            }
        }
        swap(array, firstIndex, partitionIndex-1); 
        return partitionIndex-1;
    }

    /**
     * Swaps two elements in an array
     * 
     * @param array array with swapable elements
     * @param i element to be swapped
     * @param j element to be swapped with
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Returns a pivot by calculating the median of the first, the last and the middle index
     * of the array.
     * 
     * @param array array that are being sorted
     * @param firstIndex the first index of the array
     * @param lastIndex the last index of the array
     * @return the median value, aka our new pivot
     */
    private static int medianOfThree(int[] array, int firstIndex, int lastIndex) {
        int midIndex = (firstIndex + lastIndex)/2;
        if(array[lastIndex] < array[firstIndex]) {
            swap(array, firstIndex, lastIndex);
        }
        else if(array[midIndex] < array[firstIndex]) {
            swap(array, midIndex, firstIndex);
        }
        else if(array[lastIndex] < array[midIndex]) {
            swap(array, lastIndex, midIndex);
        }
        return midIndex;
    }

    /**
     * Prints out the elements in an array
     * 
     * @param array the array to be printed
     */
    public static void printArray(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; ++i)
            System.out.print(array[i] + " ");
        System.out.println();
    } 
    
}
