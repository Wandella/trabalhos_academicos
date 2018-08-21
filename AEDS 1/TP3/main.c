#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lista.h"
#include <locale.h>

int main()
{
    int tampalavra,contador=0;
    int qtdpalavra,i,j,k,posicao=1,continuar,tamanholista,cont=0;
    int buscar=0,indice,linhas=1;
    int qtdpesquisado;
    char nomearq[TAMPALAVRA];
    char palavra[TAMPALAVRA],letra;
    char *pedacofrase,*pedacopalavra;
    char frase[150];
    char buscarpalavra[TAMPALAVRA];
    TItem *vetor,*aux;
    FILE *f;
    TLista lista;
    TCelula *paux,*paux2;
    Crialistapropria(&lista);
    setlocale(LC_ALL,"portuguese");

    do
    {
        lugar:
        printf("\t\t _____________________________\n");
        printf("\t\t| Ola, o que deseja fazer?    |\n");
        printf("\t\t|1. Abrir um arquivo          |\n");
        printf("\t\t|2. Procurar uma palavra      |\n");
        printf("\t\t|0. Sair                      |\n");
        printf("\t\t|_____________________________|\n");
        scanf("%d", &continuar);
        system("cls || clear");

        switch(continuar)
        {
            case 1:
                printf("Qual o nome do arquivo de texto que deseja abrir?\n");
                fflush(stdin);
                gets(&nomearq);

                if((fopen(&nomearq, "r+"))== NULL){
                    printf("O arquivo nao existe!\n");
                    break;
                }
                f = (fopen(&nomearq, "r+"));

                while((fgets(frase,sizeof(frase),f)) != NULL){
                    pedacopalavra = (char*)strtok(frase," ");
                    while(pedacopalavra != NULL){
                        strcpy(palavra,pedacopalavra);
                        tampalavra = strlen(&palavra);

                        for(i=0;i<tampalavra;i++){
                            if(i==0){
                                if(palavra[i] == '"' || palavra[i] == '(' || palavra[i] == '[' || palavra[i] == '-' || palavra[i] == '|'){
                                    for(j = 0;j<tampalavra;j++){
                                        palavra[j] = palavra[j+1];
                                    }
                                }
                            }
                            if(i>0){
                                if(palavra[i] == ',' || palavra[i] == '.' || palavra[i] == '?' || palavra[i] == '!' || palavra[i] == ':' || palavra[i] == ';' || palavra[i] == '"' || palavra[i] == '"' || palavra[i] == ')' || palavra[i] == ']' || palavra[i] == '-' || palavra[i] == '|'){
                                    palavra[i]='\0';
                                }
                            }
                        }
                        if(strcmp(palavra,"\n") == 0){
                            break;
                        }
                        tamanholista = Verificatamanho(&lista);
                        paux2=lista.pprimeiro;

                        for(k=0;k<tamanholista;k++){
                            paux2 = paux2->pprox;
                            if(strcmp(palavra,paux2->elemento.palavra) == 0){
                                cont++;
                            }
                        }
                        if(cont==0){
                            Preencherlista(&lista,&palavra,&linhas);
                        }
                        cont=0;
                        pedacopalavra = (char*)strtok(NULL, " ");
                    }
                linhas++;
                }


                qtdpalavra=lista.tamanho;

                vetor = (TItem*) malloc(sizeof(TItem) * qtdpalavra);

                for(i=0;i<qtdpalavra;i++){
                    paux=lista.pprimeiro->pprox;
                    vetor[i].linha = paux->elemento.linha;
                    strcpy(&vetor[i].palavra,paux->elemento.palavra);
                    vetor[i].linha=paux->elemento.linha;
                    Removep(&lista,&posicao);
                }
                QuickSort(vetor, qtdpalavra);
                for(i=0;i<qtdpalavra;i++){
                    printf("%d. %s - Linha: %d\n",i,vetor[i].palavra,vetor[i].linha);
                }
                linhas=1;
                buscar++;
                goto lugar;
            case 2:
                if(buscar==0){
                    printf("Voce ainda nao abriu nenhum arquivo!\n");
                    break;
                }
                printf("Qual palavra deseja buscar?\n");
                fflush(stdin);
                gets(&buscarpalavra);

                printf("***** Pesquisa Sequencial *****\n");
                PesquisaSequencial(vetor,buscarpalavra,qtdpalavra, &qtdpesquisado);
                printf("****** Pesquisa Binaria *******\n");
                PesquisaBinaria(vetor,buscarpalavra,qtdpalavra, &qtdpesquisado);

                goto lugar;
            case 0:
                return 0;
            default:
                printf("Digite uma opcao valida\n");
        }
    }while(continuar!=1 && continuar!=2 && continuar!=0);

    return 0;
}
