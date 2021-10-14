#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

int size;
int *arrayPointer;

void main() {

    printf("How many elements do we want? ");
    scanf("%d", &size);
    arrayPointer = (int*)malloc(size*sizeof(int));
    assert(size > 0);

    size_t length = (sizeof(arrayPointer)*size)/sizeof(int);

    printf("Enter elements please: ");
    for(int i = 0; i < length; i++) {
        scanf("%d", &arrayPointer[i]);
    }
    
    for(int i = length-1; i >= 0; i--) {
        printf("%d", arrayPointer[i]);
    }
    
   


}