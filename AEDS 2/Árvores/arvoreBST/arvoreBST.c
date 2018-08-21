#include "arvoreBST.h"

void Inicializa(ApontadorNo* no){
    *no = NULL;
}

void Insere(int x, ApontadorNo* no){
    if(*no == NULL){
        *no = (ApontadorNo) malloc (sizeof(No));
        (*no)->chave = x;
        (*no)->esq = NULL;
        (*no)->dir = NULL;
        return;
    }
    if(x <(*no)->chave){
        Insere(x, &(*no)->esq);
    }
    if(x>(*no)->chave){
        Insere(x, &(*no)->dir);
    }
}

void Antecessor(ApontadorNo q, ApontadorNo* r){
    ApontadorNo aux;

    if((*r)->dir !=NULL){
        Antecessor(q, &(*r)->dir);
        return;
    }

    q->chave = (*r)->chave;
    aux = *r;
    *r = (*r)->esq;
    free(aux);
}
void Retira(int x, ApontadorNo* no){
    ApontadorNo aux;

    if((*no)==NULL){
        printf("NAO ENCONTRADO!!!\n");
        return;
    }
    else{
        if(x < (*no)->chave){
            Retira(x, &(*no)->esq);
            return;
        }
        if(x > (*no)->chave){
            Retira(x, &(*no)->dir);
            return;
        }
        if((*no)->dir == NULL){
            aux = (*no);
            (*no) = (*no)->esq;
            free(aux);
            return;
        }
        if((*no)->esq != NULL){
            Antecessor((*no), &(*no)->esq);
            return;
        }
        aux = *no;
        *no = (*no)->dir;
        free(aux);
    }
}

void Ordem(ApontadorNo* no){
    if((*no) == NULL){
        return;
    }
    else{
        Ordem(&(*no)->esq);
        printf("%d ", (*no)->chave);
        Ordem(&(*no)->dir);
    }
}

void PreOrdem(ApontadorNo* no){
    if((*no) == NULL){
        return;
    }
    else{
        printf("%d ", (*no)->chave);
        PreOrdem(&(*no)->esq);
        PreOrdem(&(*no)->dir);
    }
}

void PosOrdem(ApontadorNo* no){
    if((*no) == NULL){
        return;
    }
    else{
        PosOrdem(&(*no)->esq);
        PosOrdem(&(*no)->dir);
        printf("%d ", (*no)->chave);
    }
}

int Pesquisa(int x, ApontadorNo* no){
    if(*no == NULL){
        printf("A arvore esta vazia ou o elemento nao se encontra na arvore\n");
        return;
    }

    if(x < (*no)->chave ){
        Pesquisa(x , &(*no)->esq);
    }
    else if(x > (*no)->chave){
        Pesquisa(x, &(*no)->dir);
    }
    else if(x == (*no)->chave){
        printf("O elemento foi encontrado na arvore.\n");
    }
}

void buscaLargura(ApontadorNo *no){
    TLista lista;
    No auxiliar;
    FLVazia(&lista);
    if((*no) != NULL){
        LInsere(&lista, (*no));
        while(LEhVazia(&lista) != 1){
            LRetira(&lista, &auxiliar);
            printf("%d ",auxiliar.chave);
            if(auxiliar.esq != NULL){
                LInsere(&lista, (auxiliar.esq));
            }
            if(auxiliar.dir != NULL){
                LInsere(&lista, (auxiliar.dir));
            }
        }
    }

}

int retornaMaior(ApontadorNo *No){
    if(*No == NULL){
        printf("Arvore Vazia");
        return 0;
    }
    if ((*No)->dir == NULL){
        return (*No)->chave;
    }
    retornaMaior(&(*No)->dir);
}

int retornaMenor(ApontadorNo *No){
    if(*No == NULL){
        printf("Arvore Vazia");
        return 0;
    }
    if ((*No)->esq == NULL){
        return (*No)->chave;
    }
    retornaMenor(&(*No)->esq);
}

int totalNos(ApontadorNo *No){
    if(*No == NULL){
        return 0;
    }else{
        return 1 + totalNos(&(*No)->esq) + totalNos(&(*No)->dir);
    }
}

int primo(int valor){
    int i, cont=0;
    for(i=1; i<=valor; i++){
        if(i%2 == 0){
            cont++;
        }
        //if(cont > 2) return 0;
    }
    if(cont == 2){
        return 1;
    }else{
        return 0;
    }
}
void imprimePrimos(ApontadorNo *No){
    if(*No == NULL){
        return;
    }else{
        imprimePrimos(&(*No)->esq);
        if(primo((*No)->chave) == 1){
            printf("%d ",(*No)->chave);
        }
        imprimePrimos(&(*No)->dir);
    }
}


//LISTA
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
