package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** 
 * @author Malcolm Liljedahl
 * Created 2021-10-01
 * 
 * Princetons FrequencyCounter, modified to read from file and you scanner
*/
public class A3ModifiedFreqCounter {

   
    private A3ModifiedFreqCounter() {}

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
        A3BinarySearchTree<String, Integer> searchTree = new A3BinarySearchTree<String, Integer>();
        System.out.println("What's the minium word length? ");
        int minLength = scanInput.nextInt();
        Scanner sc = new Scanner(new File("textfile.txt"));
        int count = 0;
        
        long startTime = System.nanoTime();
        while(count <= 1000) {
            String word = sc.next().toLowerCase();
            if (word.length() < minLength) { continue; }
            words++;
            if (searchTree.contains(word)) { searchTree.put(word, searchTree.get(word) + 1); }
            else {
                searchTree.put(word, 1);
                distinct++;
            }
            count++;    
        }

        String max = "";
        searchTree.put(max, 0);
        for (String word : searchTree.keys()) {
            if (searchTree.get(word) > searchTree.get(max)) { max = word; }
        }
        long endTime = System.nanoTime();
        System.out.println("\nThe most frequent word was: " + max.toUpperCase() + " which was printed "
        + searchTree.get(max) + " times");
        System.out.println("Distinct words that exceeds " + minLength + " characters = " + distinct);
        System.out.println("Words that exceeds " + minLength + " characters = " + words);
        System.out.println("Time elapsed for finding the most frequent word of size " + minLength + 
        " was " + ((endTime-startTime) + "nS"));
        scanInput.close();
    }
    
}
    

