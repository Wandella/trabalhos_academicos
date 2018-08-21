#ifndef PROCESSO_H_INCLUDED
#define PROCESSO_H_INCLUDED


#include <stdio.h>
#include <stdlib.h>

#define TAM_TEXTO 100
#define FIM_EXECUCAO  -1
#define BLOQUEADO 1
#define PRONTO 2
#define EXECUTANDO 3

typedef char* String;
typedef int Estado;

typedef struct Processo{
    int id; //id do processo
    int idPai;
    int pc; //contador de programa
    int variavel; //variavel a ser manipulada
    int prioridade;
    Estado estadoAtual; //estado atual do processo
    int tempoInicio;
    int tempoCPU;
    char texto[TAM_TEXTO][20]; //codigo do programa
    int qtdInst; //conta total de comandos

}Processo;

int carregaInstrucoes(Processo *p, String nomeArq);

#endif // PROCESSO_H_INCLUDED
