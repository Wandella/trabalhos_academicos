#include <unistd.h>
#include "manager.h"


void inicializaManager(Manager *m, int tipoEscalonamento, int tipoPreemptivo) //incializa as estruturas e cria o primeiro processo
{
    int indice, prioridade = 3;
    inicializaEstruturas(m);

    //criar o primeiro processo
    indice = criaProcesso(m,m->contadorID,prioridade,"programas/programa1.txt");
    insereListaPronto(m, indice, prioridade);
    m->ultimoExecutado = indice;
    trocaContexto(m,8,indice);
    m->cpu.pc = 0;
    m->tempoMedio = 0;
    m->qtdProcessosEncerrados = 0;

    recebeComandosManager(m, tipoEscalonamento, tipoPreemptivo);
}

void recebeComandosManager(Manager *m, int tipoEscalonamento, int tipoPreemptivo) {
    int codigo, a, num;
    retornoCPU cpuRetorno;
    pid_t pid;
    int *pipeEntrada;
    int *pipeSaida;
    int prioridade = -1, indiceProcesso = -1;
    float tempoMedioTotal;
    char textoReporter[TAM_BUFFER];

    while(1){
        read(m->pipeEntrada[0], &codigo , sizeof(codigo));
        a=3;
        if(num==I_Q) {
            a=1;
        }else if(num==I_U){
            a=2;
        } else if(num==I_T) {
            printf("\nTerminou na manager\n" );
        }
        switch (codigo) {
            case I_Q: // Fim de uma unidade de tempo
                if(m->runningState != -1) {
                    executaProxInst(m->pcbTable[m->runningState],&(m->cpu), &(cpuRetorno));
                    if(cpuRetorno.comando == CPU_FORK) { //caso seja uma funçao FORK simulada (F)
                        criaProcessoFilho(m,m->pcbTable[m->runningState]);
                        m->cpu.pc = m->cpu.pc + cpuRetorno.n; //pula N instruçoes no processo pai
                    }
                    if(((m->cpu.fatiaTempo == m->cpu.tempoUsado) && (tipoPreemptivo == PREEMPTIVO)) || m->pcbTable[m->runningState]->estadoAtual == BLOQUEADO || m->pcbTable[m->runningState]->estadoAtual == FIM_EXECUCAO) {
                        escalonar(m, tipoEscalonamento);
                        printf("\nEscalonando rodando agora %d \n", m->runningState );
                    }
                    m->time = m->time + 1;
                } else {
                    // CPU OCIOSA
                    escalonar(m, tipoEscalonamento);
                }
                break;
            case I_U: // Coloca o primeiro processo da lista de bloqueados na lista de prontos
                LBuscaPrimeiro(&(m->blockedState), &indiceProcesso, &prioridade);
                if (indiceProcesso != -1) {
                    insereListaPronto(m, indiceProcesso, prioridade);
                    m->pcbTable[indiceProcesso]->estadoAtual = PRONTO;
                } else {
                    printf("\nLista de processos bloqueados vazia, impossível desbloquear processo\n");
                }
                break;
            case I_P: // imprime o estado atual do sistema
                strcpy(textoReporter, "");
                imprimeEstado(m,textoReporter);
                write(m->pipeSaida[1], textoReporter, sizeof(textoReporter));
                break;
            case I_T: // Imprime Tempo médio do ciclo e finaliza sistema
                if(m->qtdProcessosEncerrados != 0) {
                    tempoMedioTotal = (float) (m->tempoMedio)/(m->qtdProcessosEncerrados);
                } else {
                    tempoMedioTotal = 0;
                }
                write(m->pipeSaida[1], &tempoMedioTotal, sizeof(tempoMedioTotal));
                printf("Terminando Manager\n");
                break;
        }
        if(codigo == I_T) break; // Terminando o Loop da manager

        write(m->pipeSaida[1], &a, sizeof(a));
    }
}

void inicializaEstruturas(Manager *m) {
    int i;
    m->cpu.time = 0;
    m->time = 0;
    m->contadorID = 0;
    for (i=0;i<QTD_PROCESSOS_MAX;i++){
        m->pcbTable[i] = NULL;
    }
    m->runningState = -1;

    //inicializar listas
    FLVazia(&(m->readyState));
    FLVazia(&(m->blockedState));

    //fecha pipes Commander
    close(m->pipeEntrada[1]); //fechar escrita da entrada
    close(m->pipeSaida[0]); //fechar leitura da saida


}

int criaProcesso(Manager *m, int id, int prioridade, String nomeArq){
    int i;
    Processo *p;
    p = (Processo*) malloc(sizeof(Processo));

    //seta os valores do Processo
    p->qtdInst = 0;
    p->idPai = -1;
    p->pc = 0;
    p->id = id;
    p->prioridade = prioridade;
    p->estadoAtual = BLOQUEADO;
    p->tempoInicio = m->time;
    p->tempoCPU = 0;

    carregaInstrucoes(p, nomeArq);
    //insere na lista de Processos
    for (i=0;i<QTD_PROCESSOS_MAX;i++){
        if(m->pcbTable[i] == NULL){
            m->pcbTable[i] = p;
            break;
        }
    }

    p->estadoAtual = PRONTO; //ainda precisa carregar o código
    m->contadorID = m->contadorID + 1;
    return i;

}
void criaProcessoFilho(Manager *m, Processo *pai){
    Processo *p;
    int i;
    p = (Processo*) malloc(sizeof(Processo));

    //seta os valores do Processo com os valores do pai
    p->qtdInst = pai->qtdInst;
    p->pc = m->cpu.pc;
    p->id = m->contadorID;
    p->prioridade = pai->prioridade;
    p->estadoAtual = PRONTO;
    p->idPai = pai->id;
    p->variavel = m->cpu.variavel;
    p->tempoInicio = m->time;
    p->tempoCPU = 0;

    for(i=0; i< pai->qtdInst; i++ ) { //copia as instruções do pai para o filho
        strcpy(p->texto[i],pai->texto[i]);
    }

    for (i=0;i<QTD_PROCESSOS_MAX;i++){ //insere o processo em pcbTable
        if(m->pcbTable[i] == NULL){
            m->pcbTable[i] = p;
            break;
        }
    }

    p->estadoAtual = PRONTO; //ainda precisa carregar o código
    insereListaPronto(m,i,p->prioridade);
    m->contadorID = m->contadorID + 1; //aumenta contador de ids
}

int insereListaPronto(Manager *m, int indice, int prioridade){
    TItem *pItem;
    pItem = (TItem *) malloc(sizeof(pItem));
    pItem->indiceProcesso = indice;
    pItem->prioridade = prioridade;
    LInsere(&(m->readyState),pItem);
}

int insereListaBloqueado(Manager *m, int indice, int prioridade){
    TItem *pItem;
    pItem = (TItem *) malloc(sizeof(pItem));
    pItem->indiceProcesso = indice;
    pItem->prioridade = prioridade;
    LInsere(&(m->blockedState),pItem);
}

//salva o processo que estava em execução em pcbTable e coloca o novo processo na CPU
void trocaContexto(Manager *m, int fatiaTempo, int indexProcessoColocar){
    Processo *p;

    //retira o processo da CPU caso ja tivesse algum
    if(m->runningState != -1){
        // Atualiza dados processo a partir da CPU
        m->pcbTable[m->runningState]->variavel = m->cpu.variavel;
        m->pcbTable[m->runningState]->pc = m->cpu.pc;
        m->pcbTable[m->runningState]->tempoCPU += m->cpu.tempoUsado;
        m->ultimoExecutado = m->runningState;
    }

    //coloca o novo processo na CPU
    if(indexProcessoColocar != -1){
        m->runningState = indexProcessoColocar; //receba a posicao do processo que vai ser executado
        p = m->pcbTable[m->runningState]; // p é o processo
        p->estadoAtual = EXECUTANDO;
        m->cpu.variavel = p->variavel;
        m->cpu.pc = p->pc;
        m->cpu.fatiaTempo = fatiaTempo; //fatiaTempo é de acordo com a prioridade do programa
        m->cpu.tempoUsado = 0;
        LRetira(&(m->readyState), indexProcessoColocar);//retira processo da lista de EM EXECUÇÃO
    } else {
        m->runningState = -1; //tira de execução
    }
}

int escalonar(Manager *m, int tipoEscalonamento){ //escalonamento por prioridade
    int processoEscolhido =-1;
    Apontador aux;
    int i,j, prioridade, fatiaTempo, primeiro, ultimo;
    primeiro = -1;
    ultimo = -1;
    char b[30];
    if(m->runningState != -1){
        switch (m->pcbTable[m->runningState]->estadoAtual) {
            case FIM_EXECUCAO: //se terminou execuçao
                m->tempoMedio += m->time;
                m->qtdProcessosEncerrados++;
                free(m->pcbTable[m->runningState]);
                m->pcbTable[m->runningState] = NULL;
                m->ultimoExecutado = m->runningState;
                m->runningState = -1;
                break;
            case BLOQUEADO: //se foi bloqueado
                m->ultimoExecutado = m->runningState;
                if(m->cpu.fatiaTempo != m->cpu.tempoUsado) { //se foi bloqueado antes de sua fatia de tempo acabar
                    if (m->pcbTable[m->runningState]->prioridade > 0) { //se ainda é possível diminuir a prioridade
                        m->pcbTable[m->runningState]->prioridade--; //diminui a prioridade
                    }
                }
                insereListaBloqueado(m, m->runningState, m->pcbTable[m->runningState]->prioridade);
                break;
            case EXECUTANDO: //se acabou o tempo de execução mandar pra lista de pronto
                m->ultimoExecutado = m->runningState;
                if (m->pcbTable[m->runningState]->prioridade < 3) {
                    m->pcbTable[m->runningState]->prioridade++;
                }
                insereListaPronto(m, m->runningState, m->pcbTable[m->runningState]->prioridade );
                break;
        }
    }

    if(tipoEscalonamento == 1) {
        //pesquisa na lista de PRONTO o primeiro processo com a prioridade mais alta
        for(j=3; j>=0; j--){ //percorre a lista até 4 vezes, até encontrar a maior prioridade
            aux = m->readyState.pPrimeiro->pProx;

            /*percorre a lista até encontrar a prioridade atual ou acabar a lista
            se acabar a lista, volta olhando a prioridade mais baixa que a anterior*/
            while(aux != NULL) {
                i = aux->Item.indiceProcesso;
                prioridade = aux->Item.prioridade;
                if(prioridade == j){
                    processoEscolhido = i;
                    break;
                }
                aux = aux->pProx;
            }
            if(processoEscolhido != -1) { //se encontrou um processo, não precisa procurar mais
                break;
            }
        }
        switch (prioridade) { //se encontrou um processo
            case 0:
                fatiaTempo = 1;
                break;
            case 1:
                fatiaTempo = 2;
                break;
            case 2:
                fatiaTempo = 4;
                break;
            case 3:
                fatiaTempo = 8;
                break;
        }

        trocaContexto(m, fatiaTempo, processoEscolhido);//se nao encontrou processo, processoEscolhido = -1
        return 1;
    } else { // Escalonamento por relógio
        for(i=0; i< QTD_PROCESSOS_MAX; i++) { // Busca qual o primeiro processo da lista
            if((primeiro == -1) && (m->pcbTable[i] != NULL)){
                if(m->pcbTable[i]->estadoAtual != BLOQUEADO){
                    primeiro = i;
                    break;
                }
            }
        }
        if(primeiro != -1) { // existe processo rodando
            for(i=primeiro+1; i < QTD_PROCESSOS_MAX; i++) { // Busca qual o último processo da lista
                if(m->pcbTable[i] != NULL){
                    if(m->pcbTable[i]->estadoAtual != BLOQUEADO){
                        ultimo = i;
                    }
                }
            }

            if(ultimo != -1) { // tem mais de 1 na lista
                if(m->ultimoExecutado>=ultimo) {
                    //m->ultimoExecutado = m->ultimoExecutado % ultimo;
                    processoEscolhido = primeiro;
                } else {
                    for(i=m->ultimoExecutado+1; i <= ultimo; i++) {
                        if(m->pcbTable[i] != NULL){
                            if(m->pcbTable[i]->estadoAtual != BLOQUEADO){
                                processoEscolhido = i;
                                break;
                            }
                        }
                    }
                }

            } else {// caso de só ter 1 na lista
                processoEscolhido = primeiro;
            }
            prioridade = m->pcbTable[processoEscolhido]->prioridade;
            switch (prioridade) { //se encontrou um processo
                case 0:
                    fatiaTempo = 1;
                    break;
                case 1:
                    fatiaTempo = 2;
                    break;
                case 2:
                    fatiaTempo = 4;
                    break;
                case 3:
                    fatiaTempo = 8;
                    break;
            }
        } else { // caso não ter nada na lista
            processoEscolhido = -1;
        }
        trocaContexto(m, fatiaTempo, processoEscolhido);
        return 0;

    }

}
