#include "cpu.h"

//onde o processo em execucao irá rodar
void executaProxInst(Processo *p, Cpu *cpu, retornoCPU* retorno)
{
    if (cpu->pc >= p->qtdInst) {
        printf("\nAcessou instrução lixo\n");
        return;
    }
    String instrucao = p->texto[cpu->pc];
    executaInstrucao(p,instrucao, cpu, retorno);
}

void executaInstrucao(Processo *p, String instrucao, Cpu *cpu, retornoCPU* retorno)
{
    char inst = instrucao[0];
    int n;
    char nomeArq[30] = "";
    printf("\nExecutando instrução: %s\n",instrucao);
    cpu->tempoUsado += 1;
    switch (inst)
    {
        case 'S':   //define valor
            n = quebraNumero(instrucao); //pega o valor de n da instrucao e salva no registrador
            cpu->variavel = n;
            cpu->pc = cpu->pc + 1;
            retorno->comando = CPU_SEM;
            break;
        case 'A':   //soma n
            n = quebraNumero(instrucao);
            cpu->pc = cpu->pc + 1;
            cpu->variavel = cpu->variavel + n;
            retorno->comando = CPU_SEM;
            break;
        case 'D':   //subtrai n
            n = quebraNumero(instrucao);
            cpu->pc = cpu->pc + 1;
            cpu->variavel = cpu->variavel - n;
            retorno->comando = CPU_SEM;
            break;
        case 'B':   //bloqueia
            p->estadoAtual = BLOQUEADO;
            cpu->pc = cpu->pc + 1;
            retorno->comando = CPU_BLOQ;
            break;
        case 'E':   //termina o processo simulado
            p->estadoAtual = FIM_EXECUCAO;
            retorno->comando = CPU_TERM;
            break;
        case 'F':   //cria filho
            n = quebraNumero(instrucao);
            cpu->pc = cpu->pc + 1;
            retorno->comando = CPU_FORK;
            retorno->n = n;
            break;
        case 'R':   //substitui programa
            retorno->comando = CPU_MUDA;
            cpu->pc = 0;
            cpu->variavel = NULL;
            strncpy(retorno->arquivo,instrucao+2, strlen(instrucao)-3); // Copia o nome do arquivo para o retorno excluindo o nome do comando "R "
            retorno->arquivo[strlen(retorno->arquivo)] = '\0'; // Adiciona o final de string ao nome do arquivo
            carregaInstrucoes(p,retorno->arquivo);
            break;
        default:
            printf("COMANDO NÃO RECONHECIDO! %s", instrucao);
    }
}

int quebraNumero(String texto) //pegar valor n da instrução
{
    int i, n=0;

    for (i=2; i<strlen(texto); i++){
        n = (n*10) + texto[i] - 48;
    }
    return n;
}
