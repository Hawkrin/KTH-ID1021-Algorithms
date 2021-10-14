package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Malcolm Liljedahl
 * Date: 21-10-01
 * 
 * Reads in a file and places all words in a Red&Black binary search tree which implements a 
 * Doubly circular FIFO linked queue.
 */
public class A5Test {
    /**
     * Test method, which prints out the input from a textfile. The method calls the function
     * print which prints both the key and it's hashvalue from the hash table.
     * 
     * @param args -- 
     */
    public static void main(String[] args) throws FileNotFoundException {
        int words = 0;
        int distinct = 0;
        int count = 0;
        Scanner sc = new Scanner(new File("textfile.txt"));
        A5HashTable<String, Integer> hashTable = new A5HashTable<String, Integer>(500);
        while(count <= 500) {
            String word = sc.next().toLowerCase();
            words++;
            if (hashTable.contains(word)) { hashTable.put(word, hashTable.get(word) + 1); }
            else {
                hashTable.put(word, 1);
                distinct++;
            }
            count++;    
        }
        for(String word : hashTable.keys()) {
            System.out.println(word + " " + hashTable.get(word));
        }
        System.out.println("\nTotal words: " + words + "\nDistinct words: " + distinct);
    }
    
}
    

