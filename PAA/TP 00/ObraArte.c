#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <time.h>

#include "ObraArte.h"
int valor =0, prontas = 0, total = 1;


void criarQuadroVazio(char quadro[][LARGURA]){
    int i,j;

    for(i=0; i<ALTURA; i++){
        for(j=0; j<LARGURA; j++){
            quadro[i][j] = ' ';
            if(j == 0 || j == LARGURA-1){
                quadro[i][j] = '|';
            }
            if(i == 0 || i == ALTURA-1){
                quadro[i][j] = '-';
            }


        }
    }

}

void mostrarQuadro(char quadro[][LARGURA]){
    int i,j;
    printf("\n");
    for(i=0; i<ALTURA; i++){
        for(j=0; j<LARGURA; j++){
            printf("%c",quadro[i][j]);
        }
        printf("\n");
    }
}

/*void insereFigura(char quadro[][LARGURA], int figura, int qtd){
    int x,y;
    do{
        x = geraNumero(ALTURA);
        y = geraNumero(LARGURA);
    }while(x >= ALTURA || y >= LARGURA);
    geraNumero(LARGURA);
    geraNumero(LARGURA);
    geraNumero(ALTURA);
    switch (figura){
        case ASTERISCO:
            if (verificaVazio(quadro,x,y)) {
                quadro[x][y] = '*';
                contaFiguraADD(qtd);
                return;
            }else{
                insereFigura(quadro,figura,qtd);
            }
            break;
        case SOMA:
            if (verificaVazio(quadro,x,y) && verificaVazio(quadro,x,y+1) && 
                verificaVazio(quadro,x-1,y) && verificaVazio(quadro,x+1,y) && verificaVazio(quadro,x,y-1)) {
                quadro[x][y+1] = '*';
                quadro[x-1][y] = '*';
                quadro[x][y] = '*';
                quadro[x+1][y] = '*';
                quadro[x][y-1] = '*';
                contaFiguraADD(qtd);
                return;
            }else{
                insereFigura(quadro,figura,qtd);
            }
            break;
        case LX:
            if (verificaVazio(quadro,x,y) && verificaVazio(quadro,x-1,y-1) && 
                verificaVazio(quadro,x-1,y+1) && verificaVazio(quadro,x+1,y-1) && verificaVazio(quadro,x+1,y+1)) {
                quadro[x][y] = '*';
                quadro[x-1][y-1] = '*';
                quadro[x-1][y+1] = '*';
                quadro[x+1][y-1] = '*';
                quadro[x+1][y+1] = '*';
                contaFiguraADD(qtd);
                return;
            }else{
                insereFigura(quadro,figura,qtd);
            }
            break;
        case LT:
            if (verificaVazio(quadro,x,y) && verificaVazio(quadro,x-1,y+1) && 
                verificaVazio(quadro,x-1,y-1) && verificaVazio(quadro,x+1,y) && verificaVazio(quadro,x-1,y)) {
                quadro[x-1][y+1] = '*';
                quadro[x-1][y-1] = '*';
                quadro[x+1][y] = '*';
                quadro[x][y] = '*';
                quadro[x-1][y] = '*';
                contaFiguraADD(qtd);
                return;
            }else{
                insereFigura(quadro,figura,qtd);
            }
    }
    
    

}*/


void insereFigura(char quadro[][LARGURA], int figura, int qtd){
    int x,y, cont = 0;
    x = geraNumero(ALTURA);
    y = geraNumero(LARGURA);
    switch (figura){
        case ASTERISCO:
            while (insereAsterisco(quadro,x,y) == 0) {
                x = geraNumero(ALTURA);
                y = geraNumero(LARGURA);
            }

            break;
        case SOMA:
            while (insereSoma(quadro,x,y) == 0 && cont < 5000) {
                printf("%d",cont);
                x = geraNumero(ALTURA);
                y = geraNumero(LARGURA);
                cont++;
            }
            break;
        case LX:
            while (insereLX(quadro,x,y) == 0) {
                x = geraNumero(ALTURA);
                y = geraNumero(LARGURA);
            }
            break;
        case LT:
            while (insereLT(quadro,x,y) == 0) {
                x = geraNumero(ALTURA);
                y = geraNumero(LARGURA);
            }
            break;
    }
    
    

    
}

int insereAsterisco(char quadro[][LARGURA], int x, int y) {
    if (verificaVazio(quadro, x, y)) {
        quadro[x][y] = '*';
        return 1;
    } else {
        return 0;
    }
}

int insereSoma(char quadro[][LARGURA], int x, int y) {
    if (verificaVazio(quadro,x,y) && verificaVazio(quadro,x,y+1) && 
                verificaVazio(quadro,x-1,y) && verificaVazio(quadro,x+1,y) && verificaVazio(quadro,x,y-1)) {
                quadro[x][y+1] = '*';
                quadro[x-1][y] = '*';
                quadro[x][y] = '*';
                quadro[x+1][y] = '*';
                quadro[x][y-1] = '*';
                return 1;
            }else{
                return 0;
            }
}

int insereLX(char quadro[][LARGURA], int x, int y) {
    if (verificaVazio(quadro,x,y) && verificaVazio(quadro,x-1,y-1) && 
                verificaVazio(quadro,x-1,y+1) && verificaVazio(quadro,x+1,y-1) && verificaVazio(quadro,x+1,y+1)) {
                quadro[x][y] = '*';
                quadro[x-1][y-1] = '*';
                quadro[x-1][y+1] = '*';
                quadro[x+1][y-1] = '*';
                quadro[x+1][y+1] = '*';
                return 1;
            }else{
                return 0;
            }
}

int insereLT(char quadro[][LARGURA], int x, int y) {
    if (verificaVazio(quadro,x,y) && verificaVazio(quadro,x-1,y+1) && 
                verificaVazio(quadro,x-1,y-1) && verificaVazio(quadro,x+1,y) && verificaVazio(quadro,x-1,y)) {
                quadro[x-1][y+1] = '*';
                quadro[x-1][y-1] = '*';
                quadro[x+1][y] = '*';
                quadro[x][y] = '*';
                quadro[x-1][y] = '*';
                return 1;
            }else{
                return 0;
            }
}

int verificaVazio(char quadro[][LARGURA], int x, int y){
    if (quadro[x][y] == ' ') {
        return 1;
    }
    return 0;

}


int geraNumero(int max){
    srand(time(NULL) + valor);
    
    valor++;
    //printf("%d",valor);
    if(valor % 5 == 0) valor-= 3;
    return rand() % (max);
    

}

void contaFiguraADD(int totalf){
    printf(".");
    prontas++;
    total = totalf;
}
