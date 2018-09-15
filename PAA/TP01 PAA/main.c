#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "logica.h"

int main() {
    int tam, x, result1, result2, result3, result4;
    int *a, *b;
    int tamanhos[] = {10, 100, 1000, 10000, 50000, 100000, 200000};
    unsigned long int contaComparacao1;
    unsigned long int contaComparacao2;
    unsigned long int contaComparacao3;
    unsigned long int contaComparacao4;
    srand(time(NULL)); //semente de números aleatórios setada para o horário atual


    int i;
    for (i = 0; i < 3; i++) {

        tam = tamanhos[i];
        x = rand() % (tam * 2);

        a = geraNumero(tam * 2, tam);

        printf("\nTamanho vetor: %d", tam);
        printf("\nPesquisar por x=%d", x);
        result1 = verifica(a, x, tam, BUBBLE, SEQUENCIAL, &contaComparacao1);
        printf("\nComparacoes (BUBBLE, SEQUENCIAL): %lu", contaComparacao1);
        result2 = verifica(a, x, tam, BUBBLE, BINARIA, &contaComparacao2);
        printf("\nComparacoes (BUBBLE, BINARIA): %lu", contaComparacao2);
        result3 = verifica(a, x, tam, QUICK, SEQUENCIAL, &contaComparacao3);
        printf("\nComparacoes (QUICK, SEQUENCIAL): %lu", contaComparacao3);
        result4 = verifica(a, x, tam, QUICK, BINARIA, &contaComparacao4);
        printf("\nComparacoes (QUICK, BINARIA): %lu", contaComparacao4);
        printf("\n Resposta: ");
        if (result1 == 1 && result2 == 1 && result3 == 1 && result4 == 1) {
            printf("É possível");
        } else if (result1 == 0 && result2 == 0 && result3 == 0 && result4 == 0) {
            printf("Não é possível");
        } else {
            printf("DIVERGENCIA!!! (%d,%d,%d,%d)", result1, result2, result3, result4);
        }
        printf("\n----------------------------------\n");
    }



    return 0;
}

