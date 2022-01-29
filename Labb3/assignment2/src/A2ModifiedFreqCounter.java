package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** 
 * @author Malcolm Liljedahl
 * Created 2021-10-01
 * 
 * Princetons FrequencyCounter, modified to read files from java scanner
*/
public class A2ModifiedFreqCounter {


    private A2ModifiedFreqCounter() {}

    /**
     * Reads in a textfile and an integer from the user. The integer stands for the minimum 
     * length of a word in the textfile. The program that searches through the file and prints
     * the amount of words which have a minimum of x letters as well as which word occurs the most.
     *
     * @param args the command-line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanInput = new Scanner(System.in);
        int distinct = 0;
        int words = 0;
        A2BinarySearchST<String, Integer> searchTable = new A2BinarySearchST<String, Integer>();
        System.out.println("What's the minium word length? ");
        int minLength = scanInput.nextInt();
        Scanner sc = new Scanner(new File("textfile.txt"));
        int count = 0;
        
        long startTime = System.nanoTime();
        while(count <= 1000) {
            String word = sc.next().toLowerCase();
            if (word.length() < minLength) { continue; }
            words++;
            if (searchTable.contains(word)) { searchTable.put(word, searchTable.get(word) + 1); }
            else {
                searchTable.put(word, 1);
                distinct++;
            }
            count++;     
        }
    
        String max = "";
        searchTable.put(max, 0);
        for (String word : searchTable.keys()) {
            if (searchTable.get(word) > searchTable.get(max)) { max = word; }
        }
        long endTime = System.nanoTime();
        System.out.println("\nThe most frequent word was: " + max.toUpperCase() + " which was printed "
        + searchTable.get(max) + " times");
        System.out.println("Distinct words that exceeds " + minLength + " characters = " + distinct);
        System.out.println("Words that exceeds " + minLength + " characters = " + words);
        System.out.println("Time elapsed for finding the most frequent word of size " + minLength + 
        " was " + ((endTime-startTime) + "nS"));
        scanInput.close();
    }
    
}
