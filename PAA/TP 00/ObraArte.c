#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <time.h>

#include "ObraArte.h"
int valor = 0, prontas = 0, total = 1;

void criarQuadroVazio(char quadro[][LARGURA]) {
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

void insereAsterisco(char quadro[][LARGURA], int qtd) {
    int i;
    int x, y;

    for (i = 0; i < qtd; i++) {
        x = geraNumero(ALTURA);
        y = geraNumero(LARGURA);
        if (verificaVazio(quadro, x, y)) {
            quadro[x][y] = '*';
            contaFiguraADD(qtd);
        } else {
            //x = geraNumero(ALTURA);
            //y = geraNumero(LARGURA);
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
                verificaVazio(quadro, x - 1, y) && verificaVazio(quadro, x + 1, y) && verificaVazio(quadro, x, y - 1)) {
            quadro[x][y + 1] = '*';
            quadro[x - 1][y] = '*';
            quadro[x][y] = '*';
            quadro[x + 1][y] = '*';
            quadro[x][y - 1] = '*';
            contaFiguraADD(qtd);
        } else {
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
                verificaVazio(quadro, x + 1, y + 1)) {
            quadro[x][y] = '*';
            quadro[x - 1][y - 1] = '*';
            quadro[x - 1][y + 1] = '*';
            quadro[x + 1][y - 1] = '*';
            quadro[x + 1][y + 1] = '*';
            contaFiguraADD(qtd);
        } else {
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
                verificaVazio(quadro, x - 1, y)) {
            quadro[x - 1][y + 1] = '*';
            quadro[x - 1][y - 1] = '*';
            quadro[x + 1][y] = '*';
            quadro[x][y] = '*';
            quadro[x - 1][y] = '*';
            contaFiguraADD(qtd);
        } else {
            i--;
        }
    }

}

void insereAleatorio(char quadro[][LARGURA], int qtd){
    int i, num;
    for (i = 0; i < qtd; i++) {
        num = geraNumero(4);
        //printf("\n%d",num);
        switch(num){
            case ASTERISCO:
                insereAsterisco(quadro,1);
                break;
            case SOMA:
                insereSoma(quadro,1);
                break;
            case LX:
                insereX(quadro,1);
                break;
            case LT:
                insereOutra(quadro,1);
                break;
            default:
                i--;
                break;
        }
    }

}

void insereFigura(char quadro[][LARGURA], int figura, int qtd) {
    int i;
    int *x = geraNumeros(ALTURA, qtd);
    int *y = geraNumeros(LARGURA, qtd);

    switch (figura) {
        case ASTERISCO:
            for (i = 0; i < qtd; i++) {
                if (verificaVazio(quadro, x[i], y[i])) {
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
                }
            }
    }
    printf("\nTotal de figuras: %d",total);
}

int verificaVazio(char quadro[][LARGURA], int x, int y) {
    if (quadro[x][y] == ' ') {
        return 1;
    }
    return 0;

}

int geraNumero(int max) {
    //srand( (unsigned)time(NULL) + valor);
    //valor++;
    //printf("%d",valor);
    //if(valor % 5 == 0) valor-= 3;
    return rand() % (max);
}

int geraNumeros(int max, int qtd) {
    int *numeros;
    numeros = malloc(sizeof (int)*qtd);
    //srand(time(NULL) + valor);
    //valor += 3;

    int i;
    for (i = 0; i < qtd; i++) {
        numeros[i] = rand() % (max);
    }


    return numeros;
}

void contaFiguraADD(int totalf) {
    //printf(".");
    prontas++;
    total = totalf;
}
