/**
 * This program contains of two functions. Both function do the same task but in different ways.
 * The functions reads characters from (stdin) until they reach a newline "\n" and the they
 * output it to the standard output in reverse.
 * 
 * reverse_iterative() saves all elements onto an array while streaming through them and then it 
 * outputs the arrays content in the opposit order.
 * 
 * reverse_recursive() reads the chars of the stream, it then compares each char with the newline
 * sign "\n" and if it's not equal it will continue and read the next char. If the function would
 * read a "\n" it stops, as it's the basecase of the recursive function. It can be seen as a safety
 * so it won't run endelessly. The function calls itself until a read char is equal to newline. All
 * elements/chars are saved to the stack and because the stack is LIFO it will empty itself in the
 * reverse order.
 * 
 * @autor Malcolm Liljedahl
 * Date: 21-10-01
 */

#include <stdio.h>
#include <stdlib.h>

#define LENGTH_OF_ARRAY 10

void reverse_iterative();
void reverse_recursive();

void main() {
    printf("Enter some elements for the iterative function: ");
    reverse_iterative();
    printf("\n");
    printf("Enter some elemens for the recursive function: ");
    reverse_recursive();
}

/**
 * Allocates the memory needed for the LENGTH_OF_ARRAY specified and continously reads the chars
 * from (stdin) and stores them in an array until a newline is read. Then iterates in
 * reverse through the array and prints the last character first etc.
*/
void reverse_iterative(){
    int counter = 0;
    char character;
    char *arrayPointer = malloc(LENGTH_OF_ARRAY*sizeof(char));

    while((character = getchar()) != '\n') {
        arrayPointer[counter++] = character;
    }

    for(counter = counter-1; counter >= 0; counter--) {
        putchar(arrayPointer[counter]);
    }
    free(arrayPointer);   
}

/**
 * Reads and compares each character with newline "\n" and if it's not the same it will continue by calling
 * itself over and over again. This function continues until the "base case" is met. All elements/characters are
 * stored in a stack and when the base case is met the stack will be emptied and printed out in the opposit order,
 * due to LIFO.
 * 
*/
void reverse_recursive() {
    char character;
    if((character = getchar()) != '\n') {
        reverse_recursive();
    }
    putchar(character);
}




