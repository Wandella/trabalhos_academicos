#include <stdio.h>
#include <stdlib.h>
#include "Lista.h"

int main(){
    TLista vetorLista[TAM];
    TItem item;
    TCelula *pCelula;
    Apontador pAux;
    int opcao, opcao1, num=0, i=0,tamanho,posicao,numero,qtlista,contadorqtlista,listas_atuais=0,opcao_lista,elemento;
    int contador, escolha1, escolha2;

    do{
       do {
            printf("\t\t\n\n\n------------------MENU------------------\n\n");
            printf("\n\t1) Criar Lista MANUAL\n");
            printf("\t2) Gerar Lista ALEATORIA\n");
            printf("\t0) SAIR");
            printf("\n\nEntre com a opcao desejada:  ");
            scanf("%d", &opcao);

            switch(opcao){
                case 1:
                    printf("\nQuantidade de listas a serem criadas: ");
                    scanf("%d",&qtlista);
                    for (contadorqtlista=0; contadorqtlista<qtlista; contadorqtlista++){
                        CriarLista(&vetorLista[contadorqtlista]);
                        listas_atuais++;
                    }
                    system("cls");
                    printf("Listas criadas com sucesso!");
                    opcao= -99;
                    break;
                case 2:
                    printf("\nQuantidade de listas a serem criadas: ");
                    scanf("%d",&qtlista);
                    for (contadorqtlista=0; contadorqtlista<qtlista; contadorqtlista++){
                        printf("\nTamanho lista %d: ",contadorqtlista);
                        scanf("%d",&tamanho);
                        GerarLista(&vetorLista[contadorqtlista],tamanho);
                        printf("Lista gerada com sucesso!");
                        listas_atuais++;
                    }
                    system("cls");

                    opcao= -99;
                    break;
                case 0:
                    system("cls");
                    printf("Obrigado!!!");
                    return 0;
                    break;
                default:
                    system("cls");
                    printf("Opcao invalida");
                    break;
            }
        }while (opcao !=0 && opcao != -99);


        do{
            printf("\t\t\n\n\n------------------MENU------------------\n\n");
            printf("\n\t1) Localizar elemento na Lista\n");
            printf("\t2) Concatenar 2 listas\n");
            printf("\t3) Intercalar 2 Listas\n");
            printf("\t4) Dividir Lista\n");
            printf("\t5) Copiar Lista\n");
            printf("\t6) Inserir elemento\n");
            printf("\t7) Remover elemento\n");
            printf("\t8) Trocar posicoes\n");
            printf("\t9) Inverter Lista\n");
            printf("\t10) Mover menor\n");
            printf("\t11) Imprimir Lista\n");
            printf("\t12) Imprimir todas as Listas\n");
            printf("\t13) Criar Lista MANUAL\n");
            printf("\t14) Gerar Lista ALEATORIA\n");
            printf("\t0) SAIR");
            printf("\n\nEntre com a opcao desejada:  ");
            scanf("%d", &opcao1);
            switch(opcao1){
                case 0: //sair
                    system("cls");
                    printf("Obrigado!!!");
                    opcao = 0;
                    break;

                case 1: //pesquisar
                    system("cls");
                    printf("**Pesquisar Elemento**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    printf("Lista a ser pesquisada: ");
                    scanf("%d",&opcao_lista);
                    if (opcao_lista > listas_atuais) {
                            printf("Lista invalida");
                            break;
                    }
                    printf("\nElemento a ser pesquisado: ");
                    scanf("%d",&elemento);
                    Pesquisa(&vetorLista[opcao_lista],elemento);
                    system("pause");
                    system("cls");
                    break;

                case 2://concatenar
                    system("cls");
                    printf("**Concatenar 2 Listas**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    if (listas_atuais <2 ){
                        printf("\nListas insuficientes, crie mais listas!!!\n");
                        system("pause");
                        system("cls");
                        break;
                    }
                    do {
                        printf("\nEscolha primeira lista: ");
                        scanf("%d",&escolha1);
                    }while (escolha1 > listas_atuais-1);

                    do {
                        printf("\nEscolha segunda lista: ");
                        scanf("%d",&escolha2);
                    }while (escolha2 > listas_atuais-1);

                    Concatenar(&vetorLista[escolha1],&vetorLista[escolha2]);
                    printf("\nConcatenado com sucesso!!!\n");
                    system("pause");
                    system("cls");
                    break;

                case 3://intercalar
                    system("cls");
                    printf("**Intercalar 2 Listas**\n");
                    if (listas_atuais <2 ){
                        printf("\nListas insuficientes, crie mais listas!!!\n");
                        system("pause");
                        break;
                    }
                    do {
                        printf("\nEscolha primeira lista: ");
                        scanf("%d",&escolha1);
                    }while (escolha1 > listas_atuais);

                    do {
                        printf("\nEscolha segunda lista: ");
                        scanf("%d",&escolha2);
                    }while (escolha2 > listas_atuais);
                    //CriarLista(&vetorLista[listas_atuais+1]);
                    Intercalar(&vetorLista[escolha1],&vetorLista[escolha2],&vetorLista[listas_atuais]);
                    listas_atuais++;
                    printf("\nConcatenado com sucesso!!!\n Lista concatenada salva em: Lista %d\n\n",listas_atuais);
                    system("pause");
                    system("cls");
                    break;

                case 4://dividir
                    system("cls");
                    printf("**Dividir Lista**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    do {
                        printf("\nEscolha a Lista a ser dividida: ");
                        scanf("%d",&escolha1);
                    }while (escolha1 > listas_atuais-1);

                        printf("Dividir em quantas outras listas: ");
                        scanf("%d",&escolha2);

                    listas_atuais = Divisao(&vetorLista,&vetorLista[escolha1],escolha2,listas_atuais);
                    system("pause");
                    system("cls");
                    break;

                case 5://copiar
                    system("cls");
                    printf("**Copiar Lista**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    do {
                        printf("\nEscolha a Lista a ser copiada: ");
                        scanf("%d",&escolha1);
                    }while (escolha1 > listas_atuais-1);
                    CriarLista(&vetorLista[listas_atuais]);
                    listas_atuais++;
                    Copiar (&vetorLista[escolha1], &vetorLista[listas_atuais-1]);
                    printf("\nCopiada com sucesso!!!\n Lista copiada salva em: Lista %d\n\n",listas_atuais-1);
                    system("pause");
                    system("cls");;
                    break;

                case 6://inserir
                    system("cls");
                    printf("**Inserir**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    do {
                        printf("\nEscolha Lista: ");
                        scanf("%d",&escolha1);
                    }while (escolha1 > listas_atuais-1);

                    printf("Item a ser inserido: ");
                    scanf("%d",&numero);
                    item.numero = numero;
                    printf("Posicao a ser inserida: ");
                    scanf("%d",&posicao);
                    Inserir(&vetorLista[escolha1],&item, posicao);
                    printf("\nInserido com sucesso!!!\n \nLista %d:\n",escolha1);
                    LImprime(&vetorLista[escolha1]);
                    system("pause");
                    system("cls");
                    break;

                case 7://remover
                    system("cls");
                    printf("**Remover**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    do {
                        printf("\nEscolha Lista: ");
                        scanf("%d",&escolha1);
                    }while (escolha1 > listas_atuais-1);

                    printf("Posicao a ser retirada: ");
                    scanf("%d",&posicao);
                    Remover(&vetorLista[escolha1],&item, posicao);
                    printf("\nRetirado com sucesso!!!\n \nLista %d:\n",escolha1);
                    LImprime(&vetorLista[escolha1]);
                    system("pause");
                    system("cls");
                    break;

                case 8://trocar
                    system("cls");
                    printf("**Trocar Elemento**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    printf("Escolha a lista: ");
                    scanf("%d",&opcao_lista);
                    printf("\nEscolha o elemento: ");
                    scanf("%d",&escolha1);

                    if (Pesquisa(&vetorLista[opcao_lista],escolha1) != NULL){
                        pCelula = Pesquisa(&vetorLista[opcao_lista],escolha1);
                        system("cls");
                        Troca(&vetorLista[opcao_lista],pCelula);
                        printf("\nTrocado com sucesso!!!\n");
                        system("pause");
                        system("cls");
                    }else{
                        system("pause");
                        system("cls");
                    }

                    break;

                case 9: //inverter
                    system("cls");
                    printf("**Inverte**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    do {
                        printf("\nEscolha Lista: ");
                        scanf("%d",&escolha1);
                    }while (escolha1 > listas_atuais-1);

                    Inverte(&vetorLista[escolha1]);
                    printf("\nInvertida com sucesso!!!\n \nLista %d:\n",escolha1);
                    LImprime(&vetorLista[escolha1]);
                    system("pause");
                    system("cls");
                    break;

                case 10://mover menor
                    system("cls");
                    printf("**Mover Menor**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    do {
                        printf("\nEscolha Lista: ");
                        scanf("%d",&escolha1);
                    }while (escolha1 > listas_atuais-1);

                    MoveMenor(&vetorLista[escolha1]);
                    printf("\nMenor movido com sucesso!!!\n \nLista %d:\n",escolha1);
                    LImprime(&vetorLista[escolha1]);
                    system("pause");
                    system("cls");
                    break;

                case 11: //imprimir lista
                    system("cls");
                    printf("**Imprimir Lista**\n");
                    printf("Quantidade de listas criadas: %d\n",listas_atuais);
                    do{
                        printf("\nLista a ser impressa: ");
                        scanf("%d",&opcao_lista);
                    }while(opcao_lista > listas_atuais-1);
                    if (opcao_lista > listas_atuais) {
                            printf("Lista invalida");
                            break;
                    }
                    printf("\nLista %d :\n",opcao_lista);
                    LImprime(&vetorLista[opcao_lista]);
                    system("pause");
                    system("cls");
                    break;

                case 12: //imprimir tudo
                    system("cls");
                    printf("**Todas as Listas**\n");
                    for (contador=0; contador<listas_atuais; contador++){
                        printf("\nLista %d :\n",contador);
                        LImprime(&vetorLista[contador]);
                    }
                    system("pause");
                    system("cls");
                    break;

                case 13: //criar lista
                    system("cls");
                    printf("**Criar Lista**\n");
                    CriarLista(&vetorLista[listas_atuais]);
                    listas_atuais++;
                    system("cls");
                    printf("Lista criada com sucesso! Salva em Lista %d",listas_atuais);
                    system("pause");
                    system("cls");
                    break;

                case 14: //gerar lista
                    system("cls");
                    printf("**Gerar Lista**\n");
                    printf("\nTamanho lista: ");
                    scanf("%d",&tamanho);
                    GerarLista(&vetorLista[listas_atuais],tamanho);
                    listas_atuais++;
                    printf("\nLista gerada com sucesso! Salva em Lista %d: \n",listas_atuais);
                    LImprime(&vetorLista[listas_atuais-1]);
                    system("pause");
                    system("cls");

                    break;
                default:
                    system("cls");
                    printf("OPCAO INVALIDA!");
                    break;
            }
                }while(opcao1 != 0);
    }while (opcao !=0 && opcao != -99);
}
