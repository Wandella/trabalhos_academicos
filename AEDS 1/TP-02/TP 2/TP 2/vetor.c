#include <stdio.h>
#include <stdlib.h>
#include "vetor.h"

void Selecao (int *vetor, int n,int *contc,int *contm)
{
    int i, j, Min;
    int aux;
    (*contc)=0;
    (*contm)=0;
    for (i = 0; i < n-1; i++)
    {
        Min = i;
        for (j = i + 1 ; j < n; j++)
        {
            *contc =(*contc)+1;
            if ( vetor[j] < vetor[Min])
            {
                Min = j;
            }
        }
        aux = vetor[Min];
        vetor[Min] = vetor[i];
        vetor[i] = aux;
        *contm=(*contm)+3;
    }
}

void Insercao(int *vetor, int n,int *contc,int *contm)
{
    int i,j,cont;
    int aux;
    (*contc)=0;
    (*contm)=0;
    for (i = 1; i < n; i++)
    {
        cont=0;
        aux = vetor[i];
        j = i - 1;
        *contc =(*contc)+1;
        while (( j >= 0 ) && ( aux < vetor[j] ))
        {
            cont = cont+1;
            vetor[j + 1] = vetor[j];
            *contm=(*contm)+1;
            j--;
        }
        vetor[j + 1] = aux;
        if(cont>0){
            (*contc)=(*contc)+(cont-1);
        }
        *contm=(*contm)+2;

    }
}

void QuickSort(int *vetor, int n,int *contc,int *contm)
{
    (*contc)=0;
    (*contm)=0;
    Ordena(0, n-1, vetor, contc, contm);
}

void Particao(int Esq, int Dir,int *i, int *j, int *vetor, int *contc, int *contm)
{
    int pivo, aux,cont;
    *i = Esq;
    *j = Dir;
    pivo = vetor[(*i + *j)/2]; /* obtem o pivo x */

    do
    {
        cont=0;
        (*contc)=(*contc)+1;
        while (pivo > vetor[*i])
        {
            cont = cont+1;
            (*i)++;
        }
        (*contc)=(*contc)+1;
        while (pivo < vetor[*j])
        {
            cont = cont+1;
            (*j)--;
        }
        if(cont>0){
            (*contc)=(*contc)+(cont-2);
        }
        if (*i <= *j)
        {
            (*contm)=(*contm)+3;
            aux = vetor[*i];
            vetor[*i] = vetor[*j];
            vetor[*j] = aux;
            (*i)++;
            (*j)--;
        }
    }
    while (*i <= *j);
}

void Ordena(int Esq, int Dir, int *vetor,int *contc,int *contm)
{
    int i,j;
    Particao(Esq, Dir, &i, &j, vetor, contc, contm);
    if (Esq < j)
    {
        Ordena(Esq, j, vetor, contc, contm);
    }
    if (i < Dir)
    {
        Ordena(i, Dir, vetor, contc, contm);
    }
}

void HeapSort(int *vetor, int n,int *contc,int *contm)
{
    (*contc)=0;
    (*contm)=0;
    int Esq, Dir;
    int aux;
    Constroi(vetor,n-1,contc,contm);
    Esq = 0;
    Dir = n-1;
    while (Dir > 0){
        aux = vetor[0];
        vetor[0] = vetor[Dir];
        vetor[Dir] = aux;
        (*contm)=(*contm)+3;
        Dir--;
        Refaz(Esq, Dir, vetor,contc,contm);
    }
}

void Constroi(int *a, int n,int *contc,int *contm)
{
    int Esq;
    Esq = (n-1) / 2;
    while (Esq >= 0)
    {
        Refaz(Esq, n-1, a,contc,contm);
        Esq--;
    }
}

void  Refaz(int Esq, int Dir, int *a,int *contc,int *contm)
{
    int i = Esq;
    int j;
    int aux;

    j = i * 2+1;
    (*contm)=(*contm)+1;
    aux = a[i];
    while (j <= Dir)
    {
        if (j < Dir){
            (*contc)=(*contc)+1;
            if (a[j] < a[j+1]){
                j++;
            }
        }
        (*contc)=(*contc)+1;
        if (aux >= a[j]){
            break;
        }
         a[i] = a[j];
         (*contm) = (*contm)+1;
         i = j;
         j = i * 2+1;
    }
    a[i] = aux;
    (*contm) = (*contm)+1;
}
void ShellSort(int *vetor, int n,int *contc,int *contm)
{
    int i, j,cont=0;
    int h = 1;
    int aux;
    (*contc)=0;
    (*contm)=0;

    do h = h * 3 + 1;
    while (h < n);
    do
    {
        h = h/3;
        for( i = h ; i < n ; i++ ){
             cont = 0;
             aux = vetor[i];
             j = i;
             (*contc)++;
             (*contm)++;
             while (vetor[j - h] > aux) {

                 cont=cont+1;
                 vetor[j] = vetor[j - h];
                 j = j - h;
                 (*contm)++;

                 if (j < h){
                    break;
                 }
             }
             if(cont > 0){
                (*contc)=(*contc)+(cont-1);
             }
             (*contm) = (*contm)+ 1;
             vetor[j] = aux;
        }
    }while(h!=1);
}

void Criar(int *vetor,int n)
{
    time_t seed;

    int i;

    if(!seed)
    {
        time(&seed);
        srand((unsigned)seed);
    }

    for(i=0;i<n;i++){
        vetor[i] = rand()%(10000);
    }
}
