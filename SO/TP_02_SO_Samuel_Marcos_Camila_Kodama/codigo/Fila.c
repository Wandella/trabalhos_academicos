#include "Fila.h"

void FLVazia(TLista* pLista) {
    pLista->pPrimeiro = (Apontador) malloc(sizeof(TCelula));
    pLista->pUltimo = pLista->pPrimeiro;
    pLista->pPrimeiro->pProx = NULL;
    pLista->Contador=0;
}

int LEhVazia(TLista* pLista) {
    return (pLista->pPrimeiro == pLista->pUltimo);
}

void LInsere(TLista *pLista,TItem* pItem) {
    pLista->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula));
    pLista->pUltimo = pLista->pUltimo->pProx;
    pLista->pUltimo->Item = *pItem;
    pLista->pUltimo->pProx = NULL;
    pLista->Contador++;
}


int LRetira (TLista* pLista, int index) {
    Apontador aux, proximo;
    if (LEhVazia(pLista)) //se a lista estiver vazia
    return 0; //retorna 0
    aux = pLista->pPrimeiro;

    while(aux != NULL) {
        if(aux->pProx->Item.indiceProcesso == index) {
            proximo = aux->pProx->pProx;
            if(proximo == NULL) {
                pLista->pUltimo = aux;
            }

            free(aux->pProx);
            aux->pProx = proximo;
            pLista->Contador--;
            return 1;
        }
        aux = aux->pProx;
    }
    return 0;
}

void LImprime(TLista* pLista) {
    Apontador pAux;
    pAux = pLista->pPrimeiro->pProx;
    if (LEhVazia(&pLista)){
        printf("\nVazia\n");
    }else{
        printf("\nQuantidade: %d\n",pLista->Contador);
    }
    while (pAux != NULL)
    {
        printf("%d\n", pAux->Item.indiceProcesso);
        pAux = pAux->pProx; /* próxima célula */
    }
}

void LBuscaPrimeiro (TLista* pLista, int *indiceProcesso, int *prioridade) {
    Apontador aux, proximo, celula;
    if (LEhVazia(pLista)) { //verificar se a lista está vazia
        *indiceProcesso = -1;
        *prioridade = -1;
        return NULL;
    }

    aux = pLista->pPrimeiro;
    celula = aux->pProx;
    proximo = aux->pProx->pProx;

    if(proximo == NULL) {
        pLista->pUltimo = aux;
    }

    *indiceProcesso = celula->Item.indiceProcesso;
    *prioridade = celula->Item.prioridade;

    aux->pProx = proximo;
    pLista->Contador--;
}
