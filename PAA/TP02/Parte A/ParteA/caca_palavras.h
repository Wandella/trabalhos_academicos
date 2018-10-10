#ifndef CACA_PALAVRAS_H
#define CACA_PALAVRAS_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define ABAIXO 1
#define ESQUERDA 2
#define DIREITA 3


typedef struct Tabuleiro{
    char **tabuleiro;
    int qtdLinhas;
    int qtdColunas;
} Tabuleiro;


#endif /* CACA_PALAVRAS_H */

