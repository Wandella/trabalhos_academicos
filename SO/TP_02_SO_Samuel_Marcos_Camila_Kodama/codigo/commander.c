#ifndef COMMANDER_C_INCLUDED
#define COMMANDER_C_INCLUDED

#include "commander.h"
#include <unistd.h>

int carregaComandos(String nomeArq, char *retorno) //usado para iniciar o processo
{
    FILE *arq;
    arq = fopen(nomeArq,"r");
    char ch;
    int i = 0;

    if(arq == NULL){
        return 0;
    }else{
        while( (ch=fgetc(arq))!= EOF ) {
            retorno[i] = ch;
            i++;
        }
        retorno[i] = '/0';
    }
    fclose(arq);
    return 1;
}

void inicializaCommander(Commander *c, int tipoEscalonamento, int tipoPreemptivo, int tipoEntrada){
    //cria o pipe
    (c->manager).pipeEntrada = (c->pipeSaida);
    (c->manager).pipeSaida = (c->pipeEntrada);
    pipe(c->pipeEntrada);
    pipe(c->pipeSaida);

    pid_t pid = fork(); //criação do fork

    if (pid == -1){
        printf("ERRO FORK");
        return;
    }

    if (pid <=0){//filho
        inicializaManager(&(c->manager), tipoEscalonamento, tipoPreemptivo);//inicializa manager
    } else {//pai
        close(c->pipeEntrada[1]); //fechar escrita da entrada
        close(c->pipeSaida[0]); //fechar leitura da saida
        recebeComandos(c, tipoEntrada);
    }
}

void recebeComandos(Commander *c, int tipoEntrada){
    char instrucao;
    char comandos[100];
    char nomeArquivo[30];
    float tempoMedioTotal;
    int tempo;
    char textoReporter[TAM_BUFFER];
    int a, retorno, i=0;

    if(tipoEntrada == ENT_TERMINAL) { // Entrada pelo terminal
        while (1){
            retorno = 0;
            while (retorno == 0){
                printf("\nusuario@computador:~$  ");
                scanf("%s",&instrucao);
                retorno = executaComando(c, instrucao);
                if (retorno == 0 )
                printf("\n--Comando nao reconhecido--\n");
                if (retorno == 2) { // Imprime informacoes do processo
                    read(c->pipeEntrada[0], textoReporter, sizeof(textoReporter));
                    printf("%s\n",textoReporter);
                }
            }

            if (retorno == -1) break;
            read(c->pipeEntrada[0], &a, sizeof(a));
        }
    } else { // entrada pelo Arquivo
        printf("\nEntre nome do arquivo a serem carregados os comandos: " );
        scanf("%s", &nomeArquivo);
        while(carregaComandos(nomeArquivo, comandos) == 0){
            printf("\nERRADO!!! Entre nome do arquivo a serem carregados os comandos: " );
            scanf("%s", &nomeArquivo);
        }

        printf("\nTempo entre os comandos:");
        scanf("%d", &tempo);
        printf("Comandos: %s\n", comandos);

        while (i <= strlen(comandos)){
            retorno = 0;
            while (retorno == 0){
                instrucao = comandos[i];
                i++;
                retorno = executaComando(c, instrucao);
                if (retorno == 0 )
                printf("\n--Comando nao reconhecido--\n");
                if (retorno == 2) { // Imprime informacoesProcesso
                    read(c->pipeEntrada[0], textoReporter, sizeof(textoReporter));
                    printf("%s\n",textoReporter);
                }
            }

            if (retorno == -1) break;
            read(c->pipeEntrada[0], &a, sizeof(a));
            sleep(tempo);
        }
    }

    read(c->pipeEntrada[0], &tempoMedioTotal, sizeof(tempoMedioTotal));
    printf("\nTempo Médio Total da cpu: %f\n", tempoMedioTotal);
}

int executaComando(Commander *c, char comando) {
    int num = 0;
    switch(comando){
        case 'q':
        case 'Q': //finaliza unidade de tempo e executa a próxima instrução do processo simulado
            num = I_Q;
            printf("\nComando Q\n");
            write(c->pipeSaida[1],&num, sizeof(num)); //envia mensagem para o manager
            break;
        case 'u':
        case 'U': //Desbloqueia o primeiro processo simulado na fila bloqueada.
            //move o primeiro processo simulado da fila bloqueada para a fila de estado pronto
            num = I_U;
            printf("\nComando U\n");
            write(c->pipeSaida[1],&num, sizeof(num)); //envia mensagem para o manager
            break;
        case 'p':
        case 'P':   //dispara um novo processo reporter e imprime o estado atual do sistema.
            num = I_P;
            printf("\nComando P\n");
            write(c->pipeSaida[1],&num, sizeof(num));
            return 2;
            break;
        case 't':
        case 'T': //Imprime o tempo médio do ciclo e finaliza o sistema
            num = I_T;
            printf("\nComando T\n");
            write(c->pipeSaida[1],&num, sizeof(num));
            printf("SISTEMA ENCERRADO!!!");
            return -1;
        default: return 0;
    }
    return 1;
}
#endif // COMMANDER_C_INCLUDED
