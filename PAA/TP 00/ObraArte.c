#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <time.h>

#include "ObraArte.h"
int valor = 0, prontas = 0, total = 1;

void criarQuadroVazio(char quadro[][LARGURA]) {
    int i, j;

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

}

void mostrarQuadro(char quadro[][LARGURA]) {
    int i, j;
    printf("\n");
    for (i = 0; i < ALTURA; i++) {
        for (j = 0; j < LARGURA; j++) {
            printf("%c", quadro[i][j]);
        }
        printf("\n");
    }
}

void insereFigura(char quadro[][LARGURA], int figura, int qtd) {
    int i, erro = 0;
    int *x = geraNumeros(ALTURA, qtd);
    int *y = geraNumeros(LARGURA, qtd);
    int *auxX, *auxY;

    switch (figura) {
        case ASTERISCO:
            for (i = 0; i < qtd; i++) {
                if (verificaVazio(quadro, x[i], y[i])) {
                    printf("%d %d\n", x[i], y[i]);
                    quadro[x[i]][y[i]] = '*';
                    contaFiguraADD(qtd);
                } else {
                    x[i] = geraNumero(ALTURA);
                    y[i] = geraNumero(LARGURA);
                    i--;
                }
            }
            break;
        case SOMA:
            for (i = 0; i < qtd; i++) {
                if (verificaVazio(quadro, x[i], y[i]) && verificaVazio(quadro, x[i], y[i] + 1) &&
                        verificaVazio(quadro, x[i] - 1, y[i]) && verificaVazio(quadro, x[i] + 1, y[i]) && verificaVazio(quadro, x[i], y[i] - 1)) {
                    quadro[x[i]][y[i] + 1] = '*';
                    quadro[x[i] - 1][y[i]] = '*';
                    quadro[x[i]][y[i]] = '*';
                    quadro[x[i] + 1][y[i]] = '*';
                    quadro[x[i]][y[i] - 1] = '*';
                    contaFiguraADD(qtd);
                } else {
                    x[i] = geraNumero(ALTURA);
                    y[i] = geraNumero(LARGURA);
                    i--;
                }
            }
            break;
        case LX:
            printf("qtd = %d", qtd);
            for (i = 0; i < qtd; i++) {
                if (verificaVazio(quadro, x[i], y[i]) && verificaVazio(quadro, x[i] - 1, y[i] - 1) &&
                        verificaVazio(quadro, x[i] - 1, y[i] + 1) && verificaVazio(quadro, x[i] + 1, y[i] - 1) && verificaVazio(quadro, x[i] + 1, y[i] + 1)) {
                    quadro[x[i]][y[i]] = '*';
                    quadro[x[i] - 1][y[i] - 1] = '*';
                    quadro[x[i] - 1][y[i] + 1] = '*';
                    quadro[x[i] + 1][y[i] - 1] = '*';
                    quadro[x[i] + 1][y[i] + 1] = '*';
                    contaFiguraADD(qtd);
                } else {
                    x[i] = geraNumero(ALTURA);
                    y[i] = geraNumero(LARGURA);
                    i--;
                }
            }
            break;
        case LT:
            
            for (i = 0; i < qtd; i++) {
            if (verificaVazio(quadro, x[i], y[i]) && verificaVazio(quadro, x[i] - 1, y[i] + 1) &&
                    verificaVazio(quadro, x[i] - 1, y[i] - 1) && verificaVazio(quadro, x[i] + 1, y[i]) && verificaVazio(quadro, x[i] - 1, y[i])) {
                quadro[x[i] - 1][y[i] + 1] = '*';
                quadro[x[i] - 1][y[i] - 1] = '*';
                quadro[x[i] + 1][y[i]] = '*';
                quadro[x[i]][y[i]] = '*';
                quadro[x[i] - 1][y[i]] = '*';
                contaFiguraADD(qtd);
            } else {
                x[i] = geraNumero(ALTURA);
                y[i] = geraNumero(LARGURA);
                i--;
                erro++;
                auxX = geraNumeros(ALTURA, qtd);
                auxY = geraNumeros(LARGURA, qtd);
                x[i] = auxX[i+1];
                y[i] = auxY[i-1];
                
                /*if (erro % 1000 == 0) {
                    valor = 0;
                }*/
//printf("\n%d %d %d",x[i],y[i],valor);
                if (erro > 100000) {
                    printf("ERRO %d",erro);
                    break;
                }

            }
            }
    }
    printf("Total de figuras: %d",prontas);

}

int verificaVazio(char quadro[][LARGURA], int x, int y) {
    if (quadro[x][y] == ' ') {
        return 1;
    }
    return 0;

}

int geraNumero(int max) {
    srand( (unsigned)time(NULL) + valor);
    valor ++;
    //printf("%d",valor);
    //if(valor % 5 == 0) valor-= 3;
    return rand() % (max);
}

int geraNumeros(int max, int qtd) {
    int *numeros;
    numeros = malloc(sizeof (int)*qtd);
    srand(time(NULL) + valor);
    valor += 3;

    int i;
    for (i = 0; i < qtd; i++) {
        numeros[i] = rand() % (max);
    }


    return numeros;
}

void contaFiguraADD(int totalf) {
    printf(".");
    prontas++;
    total = totalf;
}
