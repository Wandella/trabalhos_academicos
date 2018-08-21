#include<stdio.h>
#include<stdlib.h>
#include "lista.h"
#include "patricia.h"
#include"QuickSort.h"
void particao1(int Esq, int Dir, int *i, int *j, Item *A){
    Item pivo, aux;
    *i = Esq; *j = Dir;
    pivo = A[(*i + *j)/2]; /* obtem o pivo x */
    do
    {
    while (pivo.Chave > A[*i].Chave) (*i)++;
    while (pivo.Chave < A[*j].Chave) (*j)--;
    if (*i <= *j)
    {
    aux = A[*i]; A[*i] = A[*j]; A[*j] = aux;
    (*i)++; (*j)--;
    }
    } while (*i <= *j);
}
void ordena1(int Esq, int Dir, Item *A){
    int i,j;
    particao1(Esq, Dir, &i, &j, A);
    if (Esq < j) ordena1(Esq, j, A);
    if (i < Dir) ordena1(i, Dir, A);
}
void QuickSort1(Item *A, int n){
    ordena1(0, n-1, A);
}
