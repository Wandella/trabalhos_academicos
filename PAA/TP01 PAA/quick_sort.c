#include "quick_sort.h"

//http://www2.dcc.ufmg.br/livros/algoritmos-edicao2/cap4/codigo/c/4.1a4.7e4.14-ordenacao.c
//ordena de forma crescente
void Particao(int Esq, int Dir, int *i, int *j, int *A, int *contaComparacao) {
    int pivo, aux;
    *i = Esq;
    *j = Dir;
    pivo = A[(*i + *j) / 2]; /* obtem o pivo x */
    do {
        //comparação do while
        while (pivo > A[*i]) {
            (*contaComparacao)++;
            (*i)++;
        }

        //comparação do while
        while (pivo < A[*j]) {
            (*contaComparacao)++;
            (*j)--;
        }

        (*contaComparacao)++; //comparação do if
        if (*i <= *j) {
            aux = A[*i];
            A[*i] = A[*j];
            A[*j] = aux;
            (*i)++;
            (*j)--;
        }

        (*contaComparacao)++; //comparação do do_while
    } while (*i <= *j);
}

void Ordena(int Esq, int Dir, int *A, int *contaComparacao) {
    int i, j;
    Particao(Esq, Dir, &i, &j, A, contaComparacao);

    (*contaComparacao)++; //comparação do if
    if (Esq < j) Ordena(Esq, j, A, contaComparacao);

    //comparação do if
    (*contaComparacao)++;
    if (i < Dir) Ordena(i, Dir, A, contaComparacao);
}

void QuickSort(int *A, int n, int *contaComparacao) {
    Ordena(0, n - 1, A, contaComparacao);
}
