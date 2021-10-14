#include <stdio.h>
#include <stdlib.h>

/**
 * This program uses the partition part of the quicksort algorithm to move all negative numbers
 * in an array to the left of a chosen pivot number.
 * 
 * @author Malcolm Liljedahl
 * Date: 21-10-01
 * 
 **/

void order(int* array, int size);

/**
 *
 * Test method 
 */
void main() {

    int size;
    printf("How many elements do we want? ");
    scanf("%d", &size);

    int *arrayPointer = malloc(size*sizeof(int));
    size_t length = (sizeof(arrayPointer)*size)/sizeof(int);
    printf("Enter elements please: ");
    for(int i = 0; i < length; i++) {
        scanf("%d", &arrayPointer[i]);
    }

    order(arrayPointer, length);
    for(int i = 0; i < length; i++) {
        printf("%d", arrayPointer[i]);
    }
    free(arrayPointer);
}

/**
 * This is the partition part of quicksort.
 * 
 * Time complexity O(n)
 * 
 * If the array index is less than 0 then we will push the element towards the left(start of 
 * the array) by swaping it with the next element. Only elements that are less than 0 are being
 * pushed. 
 * 
*/
void order(int* array, int size) {
    if(size == 1) { return; }
    int tmp;
    for(int i = 0, j = 0; i < size; i++) {
        if(array[i] < 0) {
            tmp = array[i];
            array[i] = array[j];
            array[j++] = tmp;
        }
    }
}

