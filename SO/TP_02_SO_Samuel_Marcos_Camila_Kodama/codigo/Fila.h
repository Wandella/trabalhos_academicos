#ifndef FILA_H_INCLUDED
#define FILA_H_INCLUDED


#include <stdio.h>
#include <stdlib.h>



typedef int TipoChave;

typedef struct {
    TipoChave indiceProcesso;
    int prioridade;
    /* outros componentes */
} TItem;

typedef struct Celula* Apontador;

typedef struct Celula {
    TItem Item;
    struct Celula* pProx; /* Apontador pProx; */
} TCelula;

typedef struct {
    Apontador pPrimeiro;
    Apontador pUltimo;
    int Contador;
} TLista;


void FLVazia(TLista* pLista);
int LEhVazia(TLista* pLista);
void LInsere(TLista *pLista,TItem* pItem);
//int LRetira(TLista* pLista, TItem* pItem);
int LRetira (TLista* pLista, int index);
void LImprime(TLista* pLista);
void LBuscaPrimeiro (TLista* pLista, int *indiceProcesso, int *prioridade);



#endif // FILA_H_INCLUDED
