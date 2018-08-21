#include <string.h>
#include <stdio.h>

#include <math.h>
#include "lista.h"
#include "patricia.h"
#include "QuickSort.h"

void separaPalavras(char *entrada, PALAVRA *vpalavras, int *qtdPalavrasPesquisa){///separa as palavras q vao ser pesquisadas
    int i=0;
    char *salva;

    salva = strtok(entrada, " ");///quebra a string a cada espaço
    printf("\nPesquisando por: ");
    while(salva != NULL){
        printf("\n%s ",salva);
        strcpy(vpalavras[i].palavra,salva);
        salva = strtok(NULL," ,");
        i++;
    }
    printf("\n");
    *qtdPalavrasPesquisa = i;///retorna a qtd de termos a serem pesquisados
}

float calculaPeso(PApontador indice, char palavra[], int texto, int numDocsTotal){///calcula o peso pra cada palavra
    PApontador *auxiliar;
    int ocorrencias=0;
    float peso=0;
    int numDocsAparece=0;

    PBusca(indice, palavra, texto, &numDocsAparece, &ocorrencias); ///pega n° docs q a palavra aparece no texto a sua ocorrencia
    if(ocorrencias == 0){///caso nao tenha a palavra no texto
        return 0;
    }
    peso = ocorrencias*(log2(numDocsTotal)/numDocsAparece);///calcula o peso
    return peso;
}

float calculaRelevancia(PApontador indice, PALAVRA palavras[], int texto, int qtdpalavrasTxt, int numDocsTotal, int totalTermosP){
    int i,nPalavrasDistintas;
    float relevancia=0, peso, resultado, aux;

    nPalavrasDistintas = contaPalavras(indice); ///conta qts palavras ha no indice invertido
    for(i=0; i<totalTermosP; i++){
        peso = calculaPeso(indice, palavras[i].palavra, texto, numDocsTotal);///calcula peso de cada termo
        relevancia = relevancia + peso;///calcula a relevancia do texto
    }
    aux = (float)qtdpalavrasTxt;///transforma qtdpalavrasTxt em float
    resultado = (1 / aux)*relevancia; ///faz a formula
    return resultado;
}

void PesquisaIndice(PApontador indice, char *termo, int qtdpalavrasTxt[], int numDocsTotal, PALAVRA nomeArqs[]){///usada na main
    int i,totalTermosP=0;
    float relevancia[numDocsTotal];
    PALAVRA palavras[50];
    Item textos[numDocsTotal];

    if(numDocsTotal == 0){
        printf("\nNenhum texto indexado!");
        return;
    }
    separaPalavras(termo, &palavras, &totalTermosP);///chama funçao pra separar os termos de pesquisa digitados
    for(i=0; i<numDocsTotal; i++){
        relevancia[i] = calculaRelevancia(indice, palavras, i, qtdpalavrasTxt[i], numDocsTotal, totalTermosP);///calcula relevancia pra cada termo
        ///salva as informações q serao mostradas sobre o texto
        textos[i].Chave = relevancia[i];
        textos[i].numTexto = i;
        strcpy(&textos[i].nomeTexto,&nomeArqs[i]);
    }
    QuickSort1(textos,numDocsTotal);///ordena os textos por relevancia
    printf("\nResultado da pesquisa(por ondem de relevancia):\n");
    for(i=numDocsTotal-1;i>=0;i--){///printa os textos por ordem de relevancia
       printf("\n%s (inserção %d) - relevancia(%.2f)",textos[i].nomeTexto, textos[i].numTexto+1, textos[i].Chave);
    }
}
