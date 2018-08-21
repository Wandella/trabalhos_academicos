#ifndef arquivo_H_INCLUDED
#define arquivo_H_INCLUDED
#include "lista.h"
#include "patricia.h"
#define TAMPALAVRA 30
#define QTDMAXTEXTOS 50D


int Indexa(FILE *f,PApontador* indice,int qtdTextos,int *contador);
void QuickSort(PItem *vetor, int n);
void Particao(int Esq, int Dir,int *i, int *j, PItem *vetor);
void QuickSort(PItem *vetor, int n);
void TransferePatriciaParaVetor(PItem *a,TipoPatNo* no, int *h,int*n);
void ImprimePatriciaOrdenada(TipoPatNo* no);
void ImprimeVetorOrdenado(PItem *a,int *h);
void VerificaTamanhoP(PApontador no,int *h);
#endif
