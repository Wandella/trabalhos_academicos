#include <string.h>
#include "processo.h"


int carregaInstrucoes(Processo *p, String nomeArq) //carrega código do arquivo
{
    p->qtdInst = 0;
    FILE *arq;
    arq = fopen(nomeArq,"r");
    char ch[20];
    int i = 0;

    if(arq == NULL){
        printf("Erro, nao foi possivel abrir /Nome arq: %s / \n", nomeArq);
        return 0;
    }else{
        while( (fgets(ch, sizeof(ch), arq))!=NULL ){
            for(i=0;i<10;i++){ //tira a quebra de linha
                if(ch[i] == '\n'){
                    ch[i] = '\0';
                    break;
                }
            }
            insereIntrucao(p,ch);
        }
    }
    fclose(arq);
    return 1;
}

void informacoesProcesso(Processo *p) //printa o estado, cod e instruçoes com PC
{
    int i;
    String est;
    printf("\n---------------- PROCESSO %d ----------------\n",p->id);
    printf("Valor da variável:%d ",p->variavel);
    switch (p->estadoAtual){
        case FIM_EXECUCAO:
        est = "TERMINOU A EXECUÇÃO";
        break;
        case PRONTO:
        est = "PRONTO";
        break;
        case BLOQUEADO:
        est = "BLOQUEADO";
        break;
        case EXECUTANDO:
        est = "EM EXECUÇÃO";
        break;
    }
    printf("\nEstado: %s", est);

    printf("\nCódigo do programa:\n");
    for(i=0; i<p->qtdInst; i++)
    {
        if(p->pc == i) printf("->");
        printf("%d- %s\n",i+1, p->texto[i]);
    }
    printf("-------------------------------------------\n");

}

void insereIntrucao(Processo *p, String instrucao)
{
    strcpy(p->texto[p->qtdInst],instrucao);
    p->qtdInst++;
}
