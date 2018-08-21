#include <stdio.h>
#include <stdlib.h>
#include "Lista.h"

void FLVazia(TLista* pLista){
    pLista->pPrimeiro = (Apontador) malloc(sizeof(TCelula));
    pLista->pUltimo = pLista->pPrimeiro;
    pLista->pPrimeiro->pProx = NULL;
    pLista->Contador=0;
}

int LEhVazia(TLista* pLista){
    return (pLista->pPrimeiro == pLista->pUltimo);
}

void LInsere(TLista *pLista,int texto){
    pLista->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula));
    pLista->pUltimo = pLista->pUltimo->pProx;
    pLista->pUltimo->Item.texto = texto;
    pLista->pUltimo->Item.qtd = 1;
    pLista->pUltimo->pProx = NULL;
    pLista->Contador++;
}

void LImprime(TLista* pLista){
    Apontador pAux;

    pAux = pLista->pPrimeiro->pProx;
    if(LEhVazia(pLista)){
        printf("\nLista Vazia\n");
        return;
    }else{
        while (pAux != NULL){
            printf("<%d,%d>", pAux->Item.qtd, pAux->Item.texto+1);
            pAux = pAux->pProx; /* próxima célula */
        }
    }
}

void InsereLista(TLista* pLista, int numTexto, int *contador){
    Apontador pAux;
    pAux = pLista->pPrimeiro->pProx;

    if(!LEhVazia(pLista)){
        while (pAux != NULL){
            if(pAux->Item.texto == numTexto){
                pAux->Item.qtd++;
                return;
            }
            pAux = pAux->pProx; /* próxima célula */
        }
    }
    LInsere(pLista, numTexto);
    (*contador)=(*contador)+1;
}

int VerificatamanhoLista(TLista *a){ ///Função que percorre a lista contando a quantidade de células que existem.
    int i = 0;
    TCelula *paux;

    paux=a->pPrimeiro->pProx;
    while (paux!=NULL){ /// Comando de repetição que percorre a lista contando quantas células existem.
        i=i+1;
        paux=paux->pProx;
    }
    return i; ///Retorna a quantidade de células.
}

int qtdApareceuT(TLista* pLista, int texto){///retorna quantas vezes a palavra apareceu no texto
    Apontador pAux;

    pAux = pLista->pPrimeiro->pProx;
    if(LEhVazia(pLista)){
        return 0;
    }else{
        while (pAux != NULL)
        {
            if(pAux->Item.texto == texto){
                return pAux->Item.qtd;
            }
            pAux = pAux->pProx; /* próxima célula */
        }
        return 0;
    }
}

