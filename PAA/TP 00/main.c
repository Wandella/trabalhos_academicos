/*
 * PROJETO E ANÁLISE DE ALGORITMOS - PAA
 * Trabalho Prático 0
 * ALUNO: Samuel Jhonata S. Tavares - 2282
 * PROF.: Daniel Mendes
 * Florestal, 2018
 * Desenvolvido no Windows 10
 */

#include <stdio.h>
#include <stdlib.h>
#include "ObraArte.h"

int main() {
    char quadro[ALTURA][LARGURA];
    int opcao = 0, qtd;

    while (opcao != 6) {
        printf("             * * * * * * * * * * * M E N U * * * * * * * * * * *");
        printf("\n             *       PROGRAMA GERADOR DE OBRAS DE ARTE         *");
        printf("\n             * Tipo de figura a ser gerada:                    *");
        printf("\n             * 1- Asterisco Simples                            *");
        printf("\n             * 2- Símbolo de Soma com Asteriscos               *");
        printf("\n             * 3- Letra X com Asteriscos                       *");
        printf("\n             * 4- Figuras Aleatórias                           *");
        printf("\n             * 5- Letra T com Asteriscos                       *");
        printf("\n             * 6- SAIR                                         *");
        printf("\n             * * * * * * * * * * * * * * * * * * * * * * * * * *");


        do {
            printf("\n               Escolha uma opção:");
            fflush(stdout);
            scanf("%d", &opcao);
            fflush(stdout);
        } while (opcao < 1 || opcao > 6);

        if (opcao == 6) {
            printf("Programa encerrado!");
            break;
        }

        printf("\n               Quantidade de figuras:");
        fflush(stdout);
        scanf("%d", &qtd);
        fflush(stdout);

        if (qtd <= 0) {
            qtd = geraNumero(99) + 1;
            printf("\n                - Serão geradas %d figuras", qtd);
        } else if (qtd > 100) {
            qtd = 100;
        }

        criarQuadroVazio(quadro);
        switch (opcao) {
            case 1:
                insereAsterisco(quadro, qtd);
                break;
            case 2:
                insereSoma(quadro, qtd);
                break;
            case 3:
                insereX(quadro, qtd);
                break;
            case 4:
                insereAleatorio(quadro, qtd);
                break;
            case 5:
                insereOutra(quadro, qtd);
                break;
        }
        mostrarQuadro(quadro);
    }

    return 0;
}
