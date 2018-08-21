#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "lista.h"

 void Crialistapropria(TLista *a){
    a->pprimeiro = (TCelula*)malloc(sizeof(TCelula));
    a->pultimo = a->pprimeiro;
    a->pultimo->pprox = NULL;
    a->tamanho=0;
}
void Preencherlista(TLista *a,char *b,int *linha){
    a->pultimo->pprox=(TCelula*)malloc(sizeof(TCelula));
    a->pultimo = a->pultimo->pprox;
    a->pultimo->pprox=NULL;
    strcpy(strlwr(a->pultimo->elemento.palavra),strlwr(b)); //*************************************************************EDITADO
    a->pultimo->elemento.linha=*linha;
    a->tamanho++;
}

char* Removep(TLista *a,int *p){
    int i=0;
    char x[50];
    if(*p==0){
        return 0;
    }
    if(a->pprimeiro==NULL && a->pultimo==NULL){
        printf("Sua lista nao existe\n");
        return 0;
    }
    if(*p>Verificatamanho(a) || *p<0){
        printf("Essa posicao nao existe!\n");
        return 0;
    }
    TCelula *paux,*aux;
    paux=a->pprimeiro;

    for (i=1;i<*p;i++){
        paux=paux->pprox;
    }
    aux = paux->pprox;
    strcpy(x,aux->elemento.palavra);
    paux->pprox=aux->pprox;
    if(paux->pprox==NULL){
        a->pultimo=paux;
    }
    free(aux);
    a->tamanho--;
    return x;
}


void Imprimelista(TLista *a){ // Função que imprime os elementos da lista, um a um, percorrendo a lista,
    int i=0;
    TCelula *paux;
    if(a->pprimeiro==NULL){ // Verifica se a lista existe.
        printf("A lista nao existe!\n");
        return 0;
    }
    /*if(a->pprimeiro==a->pultimo){ //  Verifica se a lista é vazia.
        printf("A lista esta vazia!\n");
        return 0;
    }*/
    printf("\n");
    paux=a->pprimeiro->pprox;
    for(i=1;i<=Verificatamanho(a);i++){ // Percorre a lista, imprimindo elemento por elemento.
        printf("%d. %s - Linha: %d\n",i,paux->elemento.palavra,paux->elemento.linha);
        paux=paux->pprox;
    }

}
int Verificatamanho(TLista *a){ //Função que percorre a lista contando a quantidade de células que existem.
    int i = 0;
    TCelula *paux;
    paux=a->pprimeiro->pprox;
    while (paux!=NULL){ // Comando de repetição que percorre a lista contando quantas células existem.
        i=i+1;
        paux=paux->pprox;
    }
    return i; //Retorna a quantidade de células.
}

void QuickSort(TItem *vetor, int n)
{
    Ordena(0, n-1, vetor);
}

void Particao(int Esq, int Dir,int *i, int *j, TItem *vetor)
{
    TItem pivo, aux;
    *i = Esq;
    *j = Dir;
    pivo = vetor[(*i + *j)/2]; /* obtem o pivo x */

    do
    {
        while (strcmp(pivo.palavra,vetor[*i].palavra) > 0)
        {
            (*i)++;
        }
        while (strcmp(pivo.palavra,vetor[*j].palavra) < 0)
        {
            (*j)--;
        }
        if (*i <= *j)
        {
            aux = vetor[*i];
            vetor[*i] = vetor[*j];
            vetor[*j] = aux;
            (*i)++;
            (*j)--;
        }
    }
    while (*i <= *j);
}

void Ordena(int Esq, int Dir, TItem *vetor)
{
    int i,j;
    Particao(Esq, Dir, &i, &j, vetor);
    if (Esq < j)
    {
        Ordena(Esq, j, vetor);
    }
    if (i < Dir)
    {
        Ordena(i, Dir, vetor);
    }
}

int PesquisaSequencial(TItem *vetor, char palavra[], int qtdpalavra, int *qtpesq){
    int i,cont=0, vezes=0;
    *qtpesq=0;
    for (i=0; i<qtdpalavra; i++){
        if(strcmp(strlwr(&vetor[i].palavra), strlwr(palavra)) == 0){
            cont++;
            break;
        }else{
            vezes++;
        }
        *qtpesq= (*qtpesq)+1;
    }

    if (cont > 0){
        printf("\nPalavra encontrada: %s\n",vetor[vezes].palavra );
        printf("Encontrada na linha: %d\n", vetor[vezes].linha );
        printf("Numero de itens pesquisados: %d\n\n\n", *qtpesq);
        return 1;
    }else{
        printf("Numero de itens pesquisados: %d\n", *qtpesq);
        printf("Palavra nao encontrada!!!\n\n");
        return 0;
    }
}

int PesquisaBinaria(TItem *vetor, char palavra[], int qtdpalavra, int *qtpesq){
    int i=0,posicao=-1, vezes=0;
    *qtpesq=1;
    //
    int esquerda, meio, direita;
    esquerda = 0; direita = qtdpalavra-1;
    while (esquerda <= direita) {
        meio = (esquerda + direita)/2;
        //printf("\nMeio: %d\nDireita: %d\nEsquerda: %d\n",meio,direita,esquerda);
        if ((strcmp(strlwr(&vetor[meio].palavra), strlwr(palavra)) == 0)){
            posicao = meio;
            break;
        }
        if ((strcmp(strlwr(&vetor[meio].palavra), strlwr(palavra)) < 0)){
            esquerda = meio + 1;
        }else{
            direita = meio - 1;
        }
        if (posicao <0){
        i++;
        }
        //*qtpesq = *qtpesq+1;
   }
    *qtpesq = i+1;
    //

    //printf("\n\nCont: %d\nVezes: %d\n\n", cont,vezes);
    if (posicao >= 0){
        printf("\n\nPalavra encontrada: %s\n",vetor[posicao].palavra );
        printf("Encontrada na linha: %d\n", vetor[posicao].linha );
        printf("Numero de itens pesquisados: %d\n\n", *qtpesq);
        printf("*******************************\n\n");
        return 1;
    }else{
        printf("\nNumero de itens pesquisados: %d", *qtpesq);
        printf("\nPalavra nao encontrada!!!\n\n");
        return 0;
    }
}
