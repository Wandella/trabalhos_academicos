#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "caca_palavras.h"

int main() {
    Tabuleiro c;
    if (carregaArquivo("casa.txt", &c)) {
        cacaPalavra(&c, "casa");

        //printf("%c",c[0][0]);

    }

}

