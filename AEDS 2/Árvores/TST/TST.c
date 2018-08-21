#include<stdio.h>
#include<stdlib.h>
#include "TST.h"

void Inicializa(ApontadorNo* raiz){
    (*raiz)=NULL;
}

//Cria um novo nó
ApontadorNo novoNo(TChave x){
    ApontadorNo aux;

    aux = (ApontadorNo) malloc(sizeof(No));
    aux->dado = x;
    aux->Esq = NULL;
    aux->Dir = NULL;
    aux->Meio = NULL;
    aux->ehFinalDaPalavra = FALSE;
    return aux;
}

void Insere(ApontadorNo* no, TChave* chave){
    if((*no)== NULL){
        (*no)= novoNo(*chave);
    }

    if((*chave) < (*no)->dado){
        Insere(&(*no)->Esq, chave);
        return;
    }
    else if((*chave) > (*no)->dado){
        Insere(&(*no)->Dir, chave);
        return;
    }
    else if((*chave) == (*no)->dado){
        if(*(chave+1) != '\0'){
            Insere(&(*no)->Meio, chave+1);
        }
        else{
            (*no)->ehFinalDaPalavra = TRUE;
        }

        return;
    }
}

void Exibir(ApontadorNo raiz){
    char recebePalavra[MAX];
    ExibirUtil(raiz, recebePalavra, 0);
}

void ExibirUtil(ApontadorNo no, char* recebePalavra, int indice){
    if(no!=NULL){
        ExibirUtil(no->Esq, recebePalavra, indice);

        recebePalavra[indice] = no->dado;

        if(no->ehFinalDaPalavra){
            recebePalavra[indice+1] = '\0';
            printf("%s\n", recebePalavra);
        }

        ExibirUtil(no->Meio, recebePalavra, indice+1);
        ExibirUtil(no->Dir, recebePalavra, indice);
        return;
    }
}

int Busca(ApontadorNo no, TChave* x){
    if(no==NULL){
        return 0;
    }
    else if((*x) < (no)->dado){
        return Busca((no->Esq), x);
    }
    else if((*x) > (no)->dado){
        return Busca((no->Dir), x);
    }
    else if((*x)== (no->dado)){
        if((*x)+1 == '\0'){
            return no->ehFinalDaPalavra;
        }
        else{
            return Busca(no->Meio, x+1);
        }
    }
    else{
        return 0;
    }
}
