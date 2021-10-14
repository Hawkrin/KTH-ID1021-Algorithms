package src;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

/**
 * This class compare the ordinary merge sort with the merge sort with cut off(insertion sort).
 * Values in the range of [0-30] are tested and compared for the merge sort cut off.
 * 
 * @autor Malcolm Liljedahl
 * Date: 21-10-01
 */
public class Assignment5 {

    /**
     * Simple test method for the class
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
        int[] mergeCopy = Arrays.copyOf(array, array.length);
        int[] mergeXcopy = Arrays.copyOf(array, array.length);
        int[] mergeX5copy = Arrays.copyOf(array, array.length);
        int[] mergex10copy = Arrays.copyOf(array, array.length);
        int[] mergex20copy = Arrays.copyOf(array, array.length);
        int[] mergex30copy = Arrays.copyOf(array, array.length);
        

        //Ordinary mergeSort
        long mergeTimeStart = System.nanoTime();
        mergeSort(mergeCopy);
        long mergeTimeEnd = System.nanoTime();
        System.out.println("Elapsed Time for original mergeSort in nano seconds: "+ (mergeTimeEnd-mergeTimeStart));

        //CUTOFF = 0
        long mergeXTimeStart = System.nanoTime();
        mergeSortX(mergeXcopy, 0, mergeXcopy.length-1, 0);
        long mergeXTimeEnd = System.nanoTime();
        System.out.println("Elapsed Time for mergeSort cutoff(0) in nano seconds: "+ (mergeXTimeEnd-mergeXTimeStart));

        //CUTOFF = 5
        long mergeXTime5Start = System.nanoTime();
        mergeSortX(mergeX5copy, 0, mergeX5copy.length-1, 5);
        long mergeXTime5End = System.nanoTime();
        System.out.println("Elapsed Time for mergeSort cutoff(5) in nano seconds: "+ (mergeXTime5End-mergeXTime5Start));

        //CUTOFF = 10
        long mergeXTime10Start = System.nanoTime();
        mergeSortX(mergex10copy, 0, mergex10copy.length-1, 10);
        long mergeXTime10End = System.nanoTime();
        System.out.println("Elapsed Time for mergeSort cutoff(10) in nano seconds: "+ (mergeXTime10End-mergeXTime10Start));

        //CUTOFF = 20
        long mergeXTime20Start = System.nanoTime();
        mergeSortX(mergex20copy, 0, mergex20copy.length-1, 20);
        long mergeXTime20End = System.nanoTime();
        System.out.println("Elapsed Time for mergeSort cutoff(20) in nano seconds: "+ (mergeXTime20End-mergeXTime20Start));

        //CUTOFF = 30
        long mergeXTime30Start = System.nanoTime();
        mergeSortX(mergex30copy, 0, mergex30copy.length-1, 30);
        long mergeXTime30End = System.nanoTime();
        System.out.println("Elapsed Time for mergeSort cutoff(30) in nano seconds: "+ (mergeXTime30End-mergeXTime30Start));

        /*
        int[] test = {1,3,5,7,5,4,3,2,7,8,6,5,4,3,56,7,8,9,7,6,5,4,3,3,65,7,8,9,7,6};
        mergeSortX(test, 0, test.length-1, 0);
        mergeSort(test);
        printArray(test);
        */   
    }

    /**
     * This method takes in an array and splits it up into two subarrays, the function is recursive
     * so it will continue splitting the array into smaller and smaller subarrays until the arrays
     * only consist on 1 element. Then the function merge will be called. We then decide where
     * to "cut off" the array and implement insertion sort instead when there're a x amount of
     * elements left.
     * 
     * Divide and Conquerer, Recursive, Stable, !In-place
     * 
     * O(n) - memory complexity
     * O(NlogN) - Time complexity (split and merge)
     * 
     * @param array the array to be sorted
     */
    public static void mergeSortX(int[] array, int firstIndex, int lastIndex, int CUTOFF) {

        if (lastIndex <= firstIndex + CUTOFF) { 
            insertionSort(array);
            return;
        }
        int size = array.length;
        int mid = size/2;
        if(size < 2) { return; }
        else {
            int[] left = new int[mid];
            int[] right = new int[size-mid]; 

            for(int i = 0; i < mid; i++) {
                left[i] = array[i];
            } 
            for(int i = mid; i < size; i++) {
                right[i-mid] = array[i];
            }
            mergeSortX(left, firstIndex, mid, CUTOFF);
            mergeSortX(right, mid+1, lastIndex, CUTOFF);
            merge(left, right, array);
        }    
    }

    /**
     * Starts at index1 and compares with index0 if index1 is smaller, index0 is being "pushed"
     * to the right and index 1 is being placed at index0. Then index2 compares with index1 and 
     * index0 and the same thing will happen until we have gone through the array.
     * 
     * O(n^2) - average/worst time complexity
     * O(n) - best time complexity
     * 
     * @param array the array to be sorted
     */
    public static void insertionSort(int[] array){
        int current = 0;
        for(int i = 1; i < array.length; i++) {
            current = array[i];
            int j = i - 1;
            while(j >= 0 && array[j] > current) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = current;
        }
    }

    /**
     * This method takes in an array and splits it up into two subarrays, the function is recursive
     * so it will continue splitting the array into smaller and smaller subarrays until the arrays
     * only consist on 1 element. Then the function merge will be called.
     * 
     * Divide and Conquerer, Recursive, Stable, !In-place
     * 
     * O(n) - memory complexity
     * O(NlogN) - Time complexity (split and merge)
     * 
     * @param array the array to be sorted
     */
    public static void mergeSort(int[] array) {
        int size = array.length;
        int mid = size/2;
        if(size < 2) { return; }
        else {
            int[] left = new int[mid];
            int[] right = new int[size-mid]; 

            for(int i = 0; i < mid; i++) {
                left[i] = array[i];
            } 
            for(int i = mid; i < size; i++) {
                right[i-mid] = array[i];
            }
            mergeSort(left);
            mergeSort(right);
            merge(left, right, array);
        }    
    }

    /**
     * This method takes in the three arrays from the method mergeSort and overwrites the elements
     * in the main array with the elements in the subarrays in ascending order.
     * 
     * @param left subarray
     * @param right subarray
     * @param array main array
     */
    private static void merge(int[] left, int[] right, int[] array) {
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0; // leftArray
        int j = 0; // rightArray
        int k = 0; // mainArray
        while(i < leftSize && j < rightSize) {
            if(left[i] < right[j]) { array[k++] = left[i++]; }
            else { array[k++] = right[j++]; }
        }
        while(i < leftSize) { array[k++] = left[i++]; }
        while(j < rightSize) { array[k++] = right[j++]; }
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
