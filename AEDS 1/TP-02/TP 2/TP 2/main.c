#include <stdio.h>
#include <stdlib.h>
#include "vetor.h"
#include <windows.h>

int main()
{
    int i,j,k;
    int valores[13]={10,100,500,1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};
    int vetor[10000];
    int contc=0,contm=0;
    FILE *arquivo;
    long seed=0;

    LARGE_INTEGER tempoInicial, tempoFinal, freq;
    float tempo;

    arquivo = fopen("Selecao.txt","at");
    printf("Metodo Selecao\n\n");
    for(i=0;i<13;i++){
        for(k=0;k<30;k++){
            Criar(vetor,valores[i]);
            QueryPerformanceCounter(&tempoInicial);
            Selecao(vetor, valores[i], &contc, &contm);
            QueryPerformanceCounter(&tempoFinal);
            QueryPerformanceFrequency(&freq);
            tempo = (float)(tempoFinal.QuadPart - tempoInicial.QuadPart)/freq.QuadPart;
            fprintf(arquivo,"Tamanho:  %d Comparacoes: %d Movimentos: %d Tempo: %.8f\n",valores[i],contc,contm,tempo);
        }
    }
    printf("Fim metodo Selecao\n\n\n");
    fclose(arquivo);

    arquivo = fopen("Insercao.txt","at");
    printf("Metodo Insercao\n\n");
    for(i=0;i<13;i++){
        for(k=0;k<30;k++){
            Criar(vetor,valores[i]);
            QueryPerformanceCounter(&tempoInicial);
            Insercao(vetor, valores[i], &contc, &contm);
            QueryPerformanceCounter(&tempoFinal);
            QueryPerformanceFrequency(&freq);
            tempo = (float)(tempoFinal.QuadPart - tempoInicial.QuadPart)/freq.QuadPart;
            fprintf(arquivo,"Tamanho:  %d Comparacoes: %d Movimentos: %d Tempo: %.8f\n",valores[i],contc,contm,tempo);
        }
    }
    printf("\nFim metodo Insercao\n\n\n");
    fclose(arquivo);


    arquivo = fopen("QuickSort.txt","at");
    printf("Metodo QuickSort\n\n");
    for(i=0;i<13;i++){
        for(k=0;k<30;k++){
            Criar(vetor,valores[i]);
            QueryPerformanceCounter(&tempoInicial) ;
            QuickSort(vetor, valores[i], &contc, &contm);
            QueryPerformanceCounter(&tempoFinal);
            QueryPerformanceFrequency(&freq);
            tempo = (float)(tempoFinal.QuadPart - tempoInicial.QuadPart)/freq.QuadPart;
            fprintf(arquivo,"Tamanho:  %d Comparacoes: %d Movimentos: %d Tempo: %.8f\n",valores[i],contc,contm,tempo);

        }
    }
    printf("Fim metodo QuickSort\n\n\n");
    fclose(arquivo);


    arquivo = fopen("HeapSort.txt","at");
    printf("Metodo HeapSort\n\n");
    for(i=0;i<13;i++){
        for(k=0;k<30;k++){
            Criar(vetor,valores[i]);
            QueryPerformanceCounter(&tempoInicial);
            HeapSort(vetor, valores[i],&contc,&contm);
            QueryPerformanceCounter(&tempoFinal);
            QueryPerformanceFrequency(&freq);
            tempo = (float)(tempoFinal.QuadPart - tempoInicial.QuadPart)/freq.QuadPart;
            fprintf(arquivo,"Tamanho:  %d Comparacoes: %d Movimentos: %d Tempo: %.8f\n",valores[i],contc,contm,tempo);
        }
    }
    printf("\nFim metodo HeapSort\n\n\n");
    fclose(arquivo);


    arquivo = fopen("ShellSort.txt","at");
    printf("Metodo ShellSort\n\n");
    for(i=0;i<13;i++){
        for(k=0;k<30;k++){
            Criar(vetor,valores[i]);
            QueryPerformanceCounter(&tempoInicial);
            ShellSort(vetor, valores[i], &contc, &contm);
            QueryPerformanceCounter(&tempoFinal);
            QueryPerformanceFrequency(&freq);
            tempo = (float)(tempoFinal.QuadPart - tempoInicial.QuadPart)/freq.QuadPart;
            fprintf(arquivo,"Tamanho:  %d Comparacoes: %d Movimentos: %d Tempo: %.8f\n",valores[i],contc,contm,tempo);
        }
    }
    printf("Fim metodo ShellSort\n\n\n");
    fclose(arquivo);

    return 0;
}
