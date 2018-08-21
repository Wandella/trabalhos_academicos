#ifndef CPU_H_INCLUDED
#define CPU_H_INCLUDED

#include "processo.h"

#define CPU_SEM 0
#define CPU_BLOQ 1
#define CPU_TERM 2
#define CPU_FORK 3
#define CPU_MUDA 4

typedef struct Cpu{
    int time;
    int variavel;
    int pc;
    int fatiaTempo; //qtd de tempo máxima para o processo executar
    int tempoUsado; //tempo ja usado na execuçao
}Cpu;

typedef struct retornoCPU{
    int comando;
    int n;
    char arquivo[30];

  }retornoCPU;
void executaProxInst(Processo *p, Cpu *cpu, retornoCPU* retorno);
void executaInstrucao(Processo *p, String instrucao, Cpu *cpu, retornoCPU* retorno);

#endif // CPU_H_INCLUDED
