#include <stdio.h>

#include "bubble.h"

//ordena de forma crescente
void bubble(int *A, int n, int *contaComparacao) {
    int i, j, aux;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n - 1; j++) {
            (*contaComparacao)++; //comparação do for

            (*contaComparacao)++; //comparação do if
            if (A[j] > A[j + 1]) {
                aux = A[j];
                A[j] = A[j + 1];
                A[j + 1] = aux;
            }
        }
    }
}