import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Assignment7Test {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("200words.txt"));
        SET<String> set = new SET<String>();
        for(int i = 0; sc.hasNext(); i++) {
            String words = sc.next();
            set.add(words);
        }

        Scanner scanInput = new Scanner(System.in);
        System.out.println("Press 1 to print the words alphabeticly or press 2 to print the words in reverse order ");
        Integer choice = scanInput.nextInt();
            
        switch(choice) {
            case 1:
                for(String words : set) {
                    System.out.println(words);
                }
            break;
            case 2:
                for(String words : set) {
                    System.out.println(words);
                }
            break;
            default: 
        }
    }
    
}
