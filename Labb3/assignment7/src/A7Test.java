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
public class A7Test {

    /**
     * Test method, where you can choose to print out word list alphabetically or in reverse.
     * The alphabetically print is done without any hustle because of the ordered binary search
     * tree. To print in reverse all words are pushed and poped upon a stack.
     * 
     * @param args -- 
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("textfile.txt"));
        A7RedAndBlackBST<String, Integer> searchTree = new A7RedAndBlackBST<String, Integer>();
        int count = 0;
        while(count <= 200) {
            String words = sc.next().toLowerCase();
            searchTree.insert(words, count);
            count++;  
        }
        A7RedAndBlackBST.print(searchTree);
    }
}

    

