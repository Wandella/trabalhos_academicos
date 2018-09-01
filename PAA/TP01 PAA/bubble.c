#include <stdio.h>

#include "bubble.h"

void bubble(int *A, int n){
    int i, j, aux;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n-1; j++) {
            if (A[j] > A[j+1]) {
                aux = A[j];
                A[j] = A[j+1];
                A[j+1] = aux;
            }
        }
    }
}