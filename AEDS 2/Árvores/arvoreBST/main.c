#include "arvoreBST.c"

int main(){
    int i, valor;
    No raiz;
    ApontadorNo pRaiz;
    pRaiz = &raiz;
    Inicializa(&pRaiz);
    do{
        printf("\n* * * M E N U * * *\n");
        printf(" 1 - Inicializar\n");
        printf(" 2 - Inserir\n");
        printf(" 3 - Ordem Crescente\n");
        printf(" 4 - Pre-Ordem\n");
        printf(" 5 - Pos-Ordem\n");
        printf(" 6 - Pesquisar\n");
        printf(" 7 - Em Largura\n");
        printf(" 8 - Retirar\n");
        printf(" 9 - Maior\n");
        printf("10 - Menor\n");
        printf("11 - Quantidade de Nos\n");
        printf("12 - Imprimir Primos\n");
        printf("99 - Inserir TESTES\n");
        printf("0 - S A I R \n");
        scanf("%d", &i);
        switch (i){
            case 1:
                Inicializa(&pRaiz);
                system("cls");
                printf("Inicializado com Sucesso!!!\n");
                break;
            case 2:
                system("cls");
                printf("Entre o numero a ser inserido: ");
                scanf("%d", &valor);
                system("cls");
                printf("Inserido com Sucesso!!!");
                Insere(valor,&pRaiz);
                printf("%d", pRaiz->chave);
                break;
            case 3:
                system("cls");
                printf("Ordem: "); Ordem(&pRaiz); printf("\n");
                break;
            case 4:
                system("cls");
                printf("PreOrdem: "); PreOrdem(&pRaiz); printf("\n");
                break;
            case 5:
                system("cls");
                printf("PosOrdem: "); PosOrdem(&pRaiz); printf("\n");
                break;
            case 6:
                system("cls");
                printf("Qual numero pesquisar? ");
                scanf("%d",&valor);
                Pesquisa(valor, &pRaiz);

            case 7:
                system("cls");
                printf("Em Largura\n");
                buscaLargura(&pRaiz);
                break;

            case 8:
                system("cls");
                printf("Entre o numero a ser removido\n");
                scanf("%d",&valor);
                Retira(valor,&pRaiz);
                break;

            case 9:
                system("cls");
                valor = retornaMaior(&pRaiz);
                printf("Maior Encontrado: %d",valor);
                break;

            case 10:
                system("cls");
                valor = retornaMenor(&pRaiz);
                printf("Menor Encontrado: %d",valor);
                break;

            case 11:
                system("cls");
                valor = totalNos(&pRaiz);
                printf("Menor Encontrado: %d",valor);
                break;

            case 12:
                system("cls");
                printf("Primos Encontrado:\n");
                imprimePrimos(&pRaiz);
                break;

            case 99: system("cls");Insere(6, &pRaiz);Insere(4, &pRaiz);Insere(7, &pRaiz);Insere(3, &pRaiz);Insere(8, &pRaiz);Insere(5, &pRaiz);break;
        }
    }while(i != 0);
    return 0;
}
