package src;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains three different sorting algorithms(Insertion Sort, Merge Sort, Quick Sort). It also
 * contains functions to measure the execution time of each algorithm.
 * 
 * @autor Malcolm Liljedahl
 * Date: 21-10-01
 */
public class Assignment4 {

    public static void main(String[] args) {
        System.out.println("Enter the size of the array: ");
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        int[] array = new int [sc.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt();
        }
        int[] insertionCopy = Arrays.copyOf(array, array.length);
        int[] mergeCopy = Arrays.copyOf(array, array.length);
        int[] quickCopy = Arrays.copyOf(array, array.length);
        
        long insertionTimeStart = System.nanoTime();
        insertionSort(insertionCopy);
        long insertionTimeEnd = System.nanoTime();
        System.out.println("Elapsed Time for insertionSort in nano seconds: "+ (insertionTimeEnd-insertionTimeStart));
        
        long mergeTimeStart = System.nanoTime();
        mergeSort(mergeCopy);
        long mergeTimeEnd = System.nanoTime();
        System.out.println("Elapsed Time for mergeSort in nano seconds: "+ (mergeTimeEnd-mergeTimeStart));
        
        long quickTimeStart = System.nanoTime();
        quickSort(quickCopy, 0, quickCopy.length-1);
        long quickTimeEnd = System.nanoTime();
        System.out.println("Elapsed Time for quickSort in nano seconds: "+ (quickTimeEnd-quickTimeStart));
        
        /*
        int[] test = {5,4,3,2,1,2,5,67,4,4,56,8,9,6,5,2,1,4,5,7,98,7,6,5,4,3};
        insertionSort(test);
        printArray(test);
        mergeSort(test);
        printArray(test);
        quickSort(test, 0, test.length-1);
        printArray(test); 
        */ 
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
        int current, j;
        for(int i = 1; i < array.length; i++) {
            current = array[i];
            j = i - 1;
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
     * Takes in an array and the first and last index of it. If the first index is smaller than
     * the last index then the function partition is called and then the halves are sorted via
     * recursion calls.
     * 
     * Divide and Conquerer, Recursive, !Stable, In-place
     * 
     * O(n^2) - worst time coplexity
     * O(NlogN) - best, average time complexity
     * O(n) - memory complexity
     * 
     * @param array the array to be sorted
     * @param firstIndex the first index of the array
     * @param lastIndex the last index of the array
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
     * @return the partition index
     */
    private static int partition(int[] array, int firstIndex, int lastIndex){
        int pivot = array[lastIndex];
        int partitionIndex = firstIndex - 1;
        for(int i = firstIndex; i <= lastIndex - 1; i++) {
            if(array[i] < pivot) {
                partitionIndex++;
                swap(array, partitionIndex, i);
            }
        }
        swap(array, partitionIndex + 1, lastIndex); 
        return partitionIndex + 1;
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
