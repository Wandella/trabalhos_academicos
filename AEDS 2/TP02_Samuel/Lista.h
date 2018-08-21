#ifndef Lista_H_INCLUDED
#define Lista_H_INCLUDED

#include <gtk/gtk.h>
#define UNIVEL 3
#define ULTIMONIVEL 125
/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#define TAMPALAVRA 50

typedef struct {
    char pagina[TAMPALAVRA];
    int filhoNum;
} TItem;

typedef struct Celula* Apontador;

typedef struct Celula {
    TItem Item;
    struct Celula* pProx; /* Apontador pProx; */
} TCelula;

typedef struct {
    Apontador pPrimeiro;
    Apontador pUltimo;
    int Contador, nivel;
} TLista;

TLista* FLVazia(TLista* listanoH, int nivel);
void LInsere(TLista *pLista,TItem pItem);
void LImprime(TLista* pLista, GtkWidget *label[][ULTIMONIVEL]);

#endif // Lista_H_INCLUDED

