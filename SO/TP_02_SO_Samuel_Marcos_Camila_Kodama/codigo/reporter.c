#include "reporter.h"

void imprimeEstado(Manager *m, char* retorno){ //imprime o estado atual do sistema
    char b[150];
    int i, prioridade, j;
    Apontador aux;

    strcpy(retorno,"\n\n------------------------INFORMAÇÕES DO SISTEMA------------------------\n");
    sprintf(b,"\nTempo do Sistema: %d", m->time);
    strcat(retorno, b);
    strcat(retorno,"\nProcessos:");
    for(i=0; i<QTD_PROCESSOS_MAX; i++){
        if (m->pcbTable[i] != NULL){
            sprintf(b,"\n\t%d - ID:%d; ID Pai: %d; Prioridade: %d; PC: %d; Tempo Início: %d; Tempo CPU: %d; Variavel: %d ",i, (m->pcbTable[i])->id, (m->pcbTable[i])->idPai, (m->pcbTable[i])->prioridade, (m->pcbTable[i])->pc,
                                                                                                                              (m->pcbTable[i])->tempoInicio, (m->pcbTable[i])->tempoCPU, (m->pcbTable[i])->variavel);
            strcat(retorno,b);
        }
    }

    strcpy(b,"\nPROCESSO EM EXECUÇÃO:");
    strcat(retorno,b);
    i = m->runningState;
    if(i!=-1){
        sprintf(b,"\n\t%d - ID:%d; ID Pai: %d; Prioridade: %d; Variavel: %d ",i, (m->pcbTable[i])->id, (m->pcbTable[i])->idPai, (m->pcbTable[i])->prioridade, (m->pcbTable[i])->variavel);
        strcat(retorno,b);
    } else {
        strcat(retorno,"\n\tNenhum processo em execução");
    }

    strcat(retorno,"\nPROCESSOS PRONTOS:"); //percorre a lista de prontos
    for(j=0; j<4; j++){
        aux = m->readyState.pPrimeiro->pProx;
        sprintf(b, "\n\tProcessos com prioridade %d", j);
        strcat(retorno, b);
        while(aux != NULL) {
            prioridade = -1;
            i = aux->Item.indiceProcesso;
            prioridade = aux->Item.prioridade;
            if(prioridade == j){
                sprintf(b,"\n\t\t%d - ID:%d; ID Pai: %d; Prioridade: %d; Variavel: %d ",i, (m->pcbTable[i])->id, (m->pcbTable[i])->idPai, (m->pcbTable[i])->prioridade, (m->pcbTable[i])->variavel);
                strcat(retorno,b);
            }
            aux = aux->pProx;
        }
    }

    strcat(retorno,"\nPROCESSOS BLOQUEADOS:"); //percorre a lista de bloqueados
    for(j=0; j<4; j++){
        aux = m->blockedState.pPrimeiro->pProx;
        sprintf(b, "\n\tProcessos com prioridade %d", j);
        strcat(retorno, b);
        while(aux != NULL) {
            i = aux->Item.indiceProcesso;
            prioridade = aux->Item.prioridade;
            if(prioridade == j){
                sprintf(b,"\n\t\t%d - ID:%d; ID Pai: %d; Prioridade: %d; Variavel: %d ",i, (m->pcbTable[i])->id, (m->pcbTable[i])->idPai, (m->pcbTable[i])->prioridade, (m->pcbTable[i])->variavel);
                strcat(retorno,b);
            }
            aux = aux->pProx;
        }
    }

    strcat(retorno,"\nInformações da CPU:");
    sprintf(b, "\n\tPC %d",m->cpu.pc);
    strcat(retorno,b);
    sprintf(b,"\n\tVariável: %d", m->cpu.variavel);
    strcat(retorno, b);
    sprintf(b,"\n\tTempo de Uso: %d", m->cpu.tempoUsado);
    strcat(retorno, b);
    sprintf(b,"\n\tFatia de tempo: %d", m->cpu.fatiaTempo);
    strcat(retorno, b);
    strcat(retorno,"\n-------------------------------------------------------------------\n");
    retorno[strlen(retorno)] = '\0';
}
