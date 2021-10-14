package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** 
 * @author Malcolm Liljedahl
 * Created 2021-10-01
 * 
 * Reads input from a file and prints the amount of time a word of given minimum length is being
 * read. Base idea -> Princeton FrequencyCounter.
*/
public class A6ModifiedFreqCounter {

    private A6ModifiedFreqCounter() {}

    /**
     * Reads in a textfile and an integer from the user. The integer stands for the minimum 
     * length of a word in the textfile. The program that searches through the file and prints
     * the amount of words which have a minimum of x letters as well as which word occurs the most.
     *
     * @param args the command-line arguments
     * @throws FileNotFoundException if the file isn't found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        int minLength = 0;
        A6HashTable<String, Integer> hashTable = new A6HashTable<String, Integer>();
        Scanner sc = new Scanner(new File("textfile.txt"));
        Scanner scanInput = new Scanner(System.in);
        while (sc.hasNext()) {
            String word = sc.next().toLowerCase();
            if (hashTable.contains(word)) { hashTable.put(word, hashTable.get(word) + 1); } 
            else { hashTable.put(word, 1); }
        }
        do {
            System.out.println("What's the minium word length? ");
            minLength = scanInput.nextInt();
            String max = "";
            hashTable.put(max, 0);
            for (String word : hashTable.keys()) {
                if (word.length() < minLength) { continue; }
                if (hashTable.get(word) > hashTable.get(max)) { max = word; }
            }
            System.out.println("\nThe most frequent word was: " + max.toUpperCase() + " which was printed "
            + hashTable.get(max) + " times");
            System.out.println("\n");
        } while(minLength > 0);
        System.out.println("DONE");
        scanInput.close();
    }
}


