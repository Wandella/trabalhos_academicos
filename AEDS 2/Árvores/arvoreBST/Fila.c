#include "Fila.h"
void FLVazia(TLista* pLista)
{
    pLista->pPrimeiro = (Apontador) malloc(sizeof(TCelula));
    pLista->pUltimo = pLista->pPrimeiro;
    pLista->pPrimeiro->pProx = NULL;
    pLista->Contador=0;
}
int LEhVazia(TLista* pLista)
{
    return (pLista->pPrimeiro == pLista->pUltimo);
}
void LInsere(TLista *pLista,TItem* pItem)
{
    pLista->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula));
    pLista->pUltimo = pLista->pUltimo->pProx;
    pLista->pUltimo->Item = *pItem;
    pLista->pUltimo->pProx = NULL;
    pLista->Contador++;

}
int LRetira(TLista* pLista, TItem* pItem)
{
    int item_retorna;
    TCelula* pAux;
    if (LEhVazia(pLista))
        return 0;
    *pItem = pLista->pPrimeiro->pProx->Item;
    item_retorna = pItem;
    pAux = pLista->pPrimeiro;
    pLista->pPrimeiro = pLista->pPrimeiro->pProx;
    free(pAux);
    pLista->Contador--;
    return item_retorna;
}
void LImprime(TLista* pLista)
{
    Apontador pAux;
    pAux = pLista->pPrimeiro->pProx;
    if ((LEhVazia(&pLista)==1)){
        printf("\nQuantidade: %d\n",pLista->Contador);
    }else{
        //printf("\nVazia\n");
    }
    while (pAux != NULL)
    {
        printf("(Lista%d)\n", pAux->Item.chaveLista);
        pAux = pAux->pProx; /* próxima célula */
    }
}

