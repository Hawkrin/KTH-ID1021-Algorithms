#include <stdio.h>
#include <ctype.h>

/**
 * The program reads single characters from the stdin and the sends it to the stdout. The characters
 * have been filtered so only alphabetical characters and newline are being printed from stdout.
 * All other characters are used shown as a blank space.
 * 
 * Can both do this to input from the user but also from txt files:
 * assignment1.exe < textfile.txt
 * 
 * @author Malcolm Liljedahl
 * Date: 21-10-01
*/

void input();

/**
 * Test method
*/
void main() {
    input();
}

/**
 * The function reads single characters from stdin and prints them if they are in the alphabet
 * or if the are the newline. Else it prints an empty space.
 * 
*/
void input() {
    printf("\n" );
    int count = 0;
    char c;
    while(((c = fgetc(stdin))!=EOF) && (count <= 2000)) {
        if(isalpha(c) || c == '\n') { printf("%c", c); }
        else { printf("%c", ' '); }
        count++;
    }
}
