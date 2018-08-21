#include<stdio.h>
#include<stdlib.h>

typedef int TipoChave;

typedef struct {
    No chaveLista;
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
int LRetira(TLista* pLista, TItem* pItem);
void LImprime(TLista* pLista);

