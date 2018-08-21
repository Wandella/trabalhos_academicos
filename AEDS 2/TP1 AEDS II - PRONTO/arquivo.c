#include <stdio.h>
#include <stdlib.h>
#include "arquivo.h"
#include "patricia.h"
#include "lista.h"
#include <locale.h>

int Indexa(FILE *f,PApontador* indice,int qtdTextos,int *contador){
    if(f==NULL){
        printf("O arquivo nao existe!\n");
        return 0;
    }
    setlocale(LC_ALL,"Portuguese");
    char linha[150];
    char palavra[TAMPALAVRA],palavraaux[TAMPALAVRA],letra;
    char *pedacopalavra;
    int tampalavra,i,j,h;
    while((fgets(linha,sizeof(linha),f)) != NULL){
        pedacopalavra = (char*)strtok(linha," ");
        while(pedacopalavra != NULL){
            strcpy(palavra,pedacopalavra);
            strlwr(palavra);
            tampalavra = strlen(&palavra);
            for(i=0; i<tampalavra; i++){
                if(i==0){
                    if(palavra[i] == '"' || palavra[i] == '(' || palavra[i] == '[' || palavra[i] == '-' || palavra[i] == '|' || palavra[i] == '“' || palavra[i] == '”')
                    {
                        for(j = 0; j<tampalavra; j++){
                            palavra[j] = palavra[j+1];
                        }
                    }
                }
                if(i>0){
                    if(palavra[i] == ',' || palavra[i] == '.' || palavra[i] == '?' || palavra[i] == '!' || palavra[i] == ':' || palavra[i] == ';' || palavra[i] == '"' || palavra[i] == '"' || palavra[i] == ')' || palavra[i] == ']' || palavra[i] == '-' || palavra[i] == '|' || palavra[i] == '\n' || palavra[i] == '”' || palavra[i] == '“' )
                    {
                        palavra[i]='\0';
                    }
                }
            }
            if(strcmp(palavra,"\n") == 0){
                break;
            }
            pedacopalavra = (char*)strtok(NULL, " ");
            strcpy(palavraaux,palavra);
            Insere(&palavraaux,indice,qtdTextos,contador);
        }
    }
    return 1;
}

void QuickSort(PItem *vetor, int n){
    Ordena(0, n-1, vetor);
}

void Particao(int Esq, int Dir,int *i, int *j, PItem *vetor){
    PItem pivo, aux;

    *i = Esq;
    *j = Dir;
    pivo = vetor[(*i + *j)/2]; /* obtem o pivo x */

    do{
        while (strcmp(pivo.Chave,vetor[*i].Chave) > 0){
            (*i)++;
        }
        while (strcmp(pivo.Chave,vetor[*j].Chave) < 0){
            (*j)--;
        }
        if (*i <= *j){
            aux = vetor[*i];
            vetor[*i] = vetor[*j];
            vetor[*j] = aux;
            (*i)++;
            (*j)--;
        }
    }
    while (*i <= *j);
}

void Ordena(int Esq, int Dir, PItem *vetor){
    int i,j;

    Particao(Esq, Dir, &i, &j, vetor);
    if (Esq < j){
        Ordena(Esq, j, vetor);
    }
    if (i < Dir){
        Ordena(i, Dir, vetor);
    }
}

void ImprimePatriciaOrdenada(TipoPatNo* no){
    int A=0;
    int *h;

    h = &A;
    PItem *a;
    VerificaTamanhoP(no,h);
    a = (PItem*)malloc((*h)*(sizeof(PItem)));
    (*h)=0;
    int n = 0;
    printf("\n\n");
    TransferePatriciaParaVetor(a,no,h,&n);
    QuickSort(a,(*h));
    ImprimeVetorOrdenado(a,((&h-1)));
}

void TransferePatriciaParaVetor(PItem *a,TipoPatNo* no, int *h,int *n){
    int i,contador;
    TCelula *paux, *paux2;

    if(no == NULL){
        printf("Seu indice invertido eh vazio!\n");
        return;
    }
    if(EExterno(no)){
        FLVazia(&(a[*h].lista));
        paux = no->NO.item.lista.pPrimeiro;
        paux = paux->pProx;
        paux2 = a[*h].lista.pPrimeiro;
        strcpy(&a[*h].Chave,&no->NO.item.Chave);
        for(i=0; i<VerificatamanhoLista(&no->NO.item.lista); i++){
            InsereLista(&a[*h].lista,paux->Item.texto,&contador);
            paux2 = paux2->pProx;
            paux2->Item.qtd = paux->Item.qtd;
            paux = paux->pProx;
        }
        (*h)=(*h)+1;
        (*n)=(*n)+1;
        return;
    }
    else{
        TransferePatriciaParaVetor(a,no->NO.NInterno.Esq,h,n);
        TransferePatriciaParaVetor(a,no->NO.NInterno.Dir,h,n);
    }
}

void ImprimeVetorOrdenado(PItem *a,int *h)
{
    int i;
    TCelula *paux;

    for(i=0; i<(*h); i++){
        paux= a[i].lista.pPrimeiro;
        paux = paux->pProx;
        //printf("\n");
        printf("\n***Palavra: %s ",a[i].Chave);
        LImprime(&a[i].lista);
    }
    h=0;
}

VerificaTamanhoP(PApontador no,int *h){
    if(no == NULL){
        return;
    }
    if(EExterno(no)){
        (*h)++;
        return;
    }
    else{
        VerificaTamanhoP(no->NO.NInterno.Esq,h);
        VerificaTamanhoP(no->NO.NInterno.Dir,h);
    }
}
