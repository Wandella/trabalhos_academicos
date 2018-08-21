#ifndef MANAGER_H_INCLUDED
#define MANAGER_H_INCLUDED

#include "Fila.h"
#include "cpu.h"
#include "processo.h"

#define I_Q 1
#define I_U 2
#define I_P 3
#define I_T 4
#define QTD_PROCESSOS_MAX 100
#define TAM_BUFFER 4000

//determinar o tipo de entrada dos comandos da commander
#define ENT_TERMINAL 1
#define ENT_ARQUIVO 2

//determinar o tipo de escalonamento
#define PREEMPTIVO 1
#define NPREEMPITIVO 2
#define LISTA_PRIORIDADE 1
#define RELOGIO 2

typedef struct Manager{
    int *pipeEntrada;
    int *pipeSaida;
    int pipeRepEntrada[2];
    int pipeRepSaida[2];
    int time;
    Cpu cpu;
    Processo* pcbTable[QTD_PROCESSOS_MAX]; //vetor de processos
    TLista readyState;
    TLista blockedState;
    int runningState;
    int contadorID;
    int ultimoExecutado;
    int tempoMedio;
    int qtdProcessosEncerrados;
} Manager;

void inicializaManager(Manager *m, int tipoEscalonamento, int tipoPreemptivo);
void recebeComandosManager(Manager *m, int tipoEscalonamento, int tipoPreemptivo);
void inicializaEstruturas(Manager *m);
int criaProcesso(Manager *m, int id, int prioridade, String nomeArq);
void criaProcessoFilho(Manager *m, Processo *pai);
int insereListaPronto(Manager *m, int indice, int prioridade);
int insereListaBloqueado(Manager *m, int indice, int prioridade);
void trocaContexto(Manager *m, int fatiaTempo, int indexProcessoColocar);
int escalonar(Manager *m, int tipoEscalonamento);

#endif // MANAGER_H_INCLUDED
