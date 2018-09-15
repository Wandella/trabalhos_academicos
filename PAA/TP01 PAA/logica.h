#ifndef LOGICA_H
#define LOGICA_H
#include "quick_sort.h"
#include "bubble.h"

#define BUBBLE 1
#define QUICK 2

#define SEQUENCIAL 1
#define BINARIA 2

typedef struct Par {
    int p1;
    int p2;
} Par;

int* geraNumero(int max, int tam);
int* copiaVetor(int *a, int tam);
void mostraVetor(int *a, int tam);
int PesquisaBinaria(int vet[], int chave, int Tam, int *contaComparacao);
int pesquisaSequencial(int vet[], int chave, int tam, int *contaComparacao);
Par* valoresSoma(int x);
int verifica(int *a, int x, int tam, int metOrden, int metPesq, int *contaComparacao);


#endif /* LOGICA_H */

