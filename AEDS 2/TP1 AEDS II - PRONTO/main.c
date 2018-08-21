// Alunos: Carlos Carvalho - 2283
//         Samuel Jhonata - 2282

#include <stdio.h>
#include <stdlib.h>
#include "lista.h"
#include "patricia.h"
#include "funcoes.h"
#include "arquivo.h"
#include "QuickSort.h"
#include <locale.h>
#define QTDMAXTEXTOS 50
#define QTDMAXPALAVRASNOTEXTO 500
int main()
{
    PApontador indice;
    PALAVRA nomeArqs[QTDMAXTEXTOS];
    int i, j, h=0, k, qtdTextos=0, opcao, cont=0;
    int qtdpalavrasTxt[QTDMAXTEXTOS]; // qtdpalavrasTxt = armazenar quantas palavras tem cada texto
    char nomearq[TAMPALAVRA],pesquisa[100];
    FILE *f;
    Inicializa(&indice);
    f=NULL;

    for(k=0;k<QTDMAXTEXTOS;k++){
        qtdpalavrasTxt[k]=0;
    }
    do
    {
        system("cls || clear");
        printf("\n\n");
        printf("\t\t * * * * * * * * * * * * * * * * *\n");
        printf("\t\t *            M E N U            *\n");
        printf("\t\t * 1. Indexar um arquivo         *\n");
        printf("\t\t * 2. Fazer uma busca            *\n");
        printf("\t\t * 3. Imprimir Indice Invertido  *\n");
        printf("\t\t * 0. Sair                       *\n");
        printf("\t\t * * * * * * * * * * * * * * * * *\n");
        scanf("%d", &opcao);
        system("cls || clear");

        switch(opcao)
        {
            case 1:
                printf("Qual arquivo deseja indexar?\n");
                fflush(stdin);
                scanf("%s",nomearq);
                f = (fopen(&nomearq, "r+"));
                if(Indexa(f,&indice,qtdTextos,&qtdpalavrasTxt[qtdTextos])==0){
                    system("pause");
                    break;
                }
                fclose(f);
                strcpy(&nomeArqs[qtdTextos].palavra,nomearq);
                for(k=0;k<=qtdTextos;k++){
                    printf("\nIndexado com sucesso!!!\nHa %d palavras distintas em '%s' (inserção %d)\n",qtdpalavrasTxt[k],nomeArqs[k].palavra,k+1);
                }
                qtdTextos++;
                system("PAUSE");
            break;

            case 2:
                printf("\nEntre os termos de pesquisa:\n");
                fflush(stdin);
                gets(&pesquisa);
                PesquisaIndice(indice, &pesquisa,qtdpalavrasTxt, qtdTextos,nomeArqs);
                printf("\n\n");
                system("PAUSE");
            break;

            case 3:
                printf("->Impressao na Patricia:\n");
                PImprime(indice);
                printf("\n\n->Impressao Ordenada:");
                ImprimePatriciaOrdenada(indice);
                VerificaTamanhoP(indice,&cont);
                printf("\n\nQuantidade Palavras distintas no indice: %d\n\n",cont);
                system("PAUSE");
        }
    }while(opcao!=0);

    return 0;
}
