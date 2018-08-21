#ifndef QuickSort_H_INCLUDED
#define QuickSort_H_INCLUDED
#include<stdio.h>
#include<stdlib.h>
#include "patricia.h"
typedef struct Item{
    float Chave;
    int numTexto;
    char nomeTexto[TAMPALAVRA];
}Item;

void particao1(int Esq, int Dir,int *i, int *j, Item *A);
void QuickSort1(Item *A, int n);
void ordena1(int Esq, int Dir, Item *A);
#endif
