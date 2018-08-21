#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "TST.h"

int main()
{
    int i,qt;
    char palavra[10];
    ApontadorNo raiz;

    Inicializa(&raiz);
/*    Insere(&raiz, "Abre");
    Insere(&raiz, "Aba");
    Insere(&raiz, "Abacate");*/
    printf("Quantidade de palavras: ");
    scanf("%d",&qt);
    for(i = 0; i < qt; i++){
        printf("\nEntre com a palavra: ");
        fflush(stdin);
        gets(palavra);
        Insere(&raiz, strupr(palavra));
    }

    Exibir(raiz);
    //Busca(&raiz, "Aba")? printf("Encontrado\n"): printf("Nao Encontrado\n");
    return 0;
}
