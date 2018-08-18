#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <time.h>

#include "ObraArte.h"
int prontas, total;

void criarQuadroVazio(char quadro[][LARGURA]) { //cria quadro vazio so com bordas
    int i, j;

    srand(time(NULL)); //semente de números aleatórios setada para o horário atual
    for (i = 0; i < ALTURA; i++) {
        for (j = 0; j < LARGURA; j++) {
            quadro[i][j] = ' ';
            if (j == 0 || j == LARGURA - 1) {
                quadro[i][j] = '|';
            }
            if (i == 0 || i == ALTURA - 1) {
                quadro[i][j] = '-';
            }
        }
    }
    prontas = 0;

}

void mostrarQuadro(char quadro[][LARGURA]) {
    int i, j;
    printf("\nTotal de figuras: %d/%d", prontas, total);
    printf("\n");
    for (i = 0; i < ALTURA; i++) {
        for (j = 0; j < LARGURA; j++) {
            printf("%c", quadro[i][j]);
        }
        printf("\n");
    }
}

void insereAsterisco(char quadro[][LARGURA], int qtd) {
    int i;
    int x, y;

    for (i = 0; i < qtd; i++) {
        x = geraNumero(ALTURA);
        y = geraNumero(LARGURA);
        if (verificaVazio(quadro, x, y)) { //se as posições estão vazias
            quadro[x][y] = '*';
            contaFiguraADD(qtd);
        } else { //se as posições não estão vazias
            i--;
        }
    }
}

void insereSoma(char quadro[][LARGURA], int qtd) {
    int i;
    int x, y;

    for (i = 0; i < qtd; i++) {
        x = geraNumero(ALTURA);
        y = geraNumero(LARGURA);
        if (verificaVazio(quadro, x, y) && verificaVazio(quadro, x, y + 1) &&
                verificaVazio(quadro, x - 1, y) && verificaVazio(quadro, x + 1, y) &&
                verificaVazio(quadro, x, y - 1)) { //se as posições estão vazias
            quadro[x][y + 1] = '*';
            quadro[x - 1][y] = '*';
            quadro[x][y] = '*';
            quadro[x + 1][y] = '*';
            quadro[x][y - 1] = '*';
            contaFiguraADD(qtd);
        } else { //se as posições não estão vazias
            i--;
        }
    }
}

void insereX(char quadro[][LARGURA], int qtd) {
    int i;
    int x, y;

    for (i = 0; i < qtd; i++) {
        x = geraNumero(ALTURA);
        y = geraNumero(LARGURA);
        if (verificaVazio(quadro, x, y) && verificaVazio(quadro, x - 1, y - 1) &&
                verificaVazio(quadro, x - 1, y + 1) && verificaVazio(quadro, x + 1, y - 1) &&
                verificaVazio(quadro, x + 1, y + 1)) { //se as posições estão vazias
            quadro[x][y] = '*';
            quadro[x - 1][y - 1] = '*';
            quadro[x - 1][y + 1] = '*';
            quadro[x + 1][y - 1] = '*';
            quadro[x + 1][y + 1] = '*';
            contaFiguraADD(qtd);
        } else { //se as posições não estão vazias
            i--;
        }
    }

}

void insereOutra(char quadro[][LARGURA], int qtd) {
    int i;
    int x, y;

    for (i = 0; i < qtd; i++) {
        x = geraNumero(ALTURA);
        y = geraNumero(LARGURA);
        if (verificaVazio(quadro, x, y) && verificaVazio(quadro, x - 1, y + 1) &&
                verificaVazio(quadro, x - 1, y - 1) && verificaVazio(quadro, x + 1, y) &&
                verificaVazio(quadro, x - 1, y)) { //se as posições estão vazias
            quadro[x - 1][y + 1] = '*';
            quadro[x - 1][y - 1] = '*';
            quadro[x + 1][y] = '*';
            quadro[x][y] = '*';
            quadro[x - 1][y] = '*';
            contaFiguraADD(qtd);
        } else { //se as posições não estão vazias
            i--;
        }
    }

}

void insereAleatorio(char quadro[][LARGURA], int qtd) {
    int i, num;
    for (i = 0; i < qtd; i++) {
        num = geraNumero(3) + 1; //sorteia o tipo de figura
        switch (num) {
            case ASTERISCO:
                insereAsterisco(quadro, 1);
                break;
            case SOMA:
                insereSoma(quadro, 1);
                break;
            case LX:
                insereX(quadro, 1);
                break;
            default:
                i--;
                break;
        }
    }
    total = qtd; //total de figuras geradas
}

int verificaVazio(char quadro[][LARGURA], int x, int y) { //olha se a posição está vazia 
    if (quadro[x][y] == ' ') {
        return 1;
    }
    return 0;

}

int geraNumero(int max) { // gera um número aleatório entre 0 e max
    return rand() % (max);
}

void contaFiguraADD(int totalf) { //adiciona mais uma figura pronta
    prontas++;
    total = totalf;
}
