#include <stdio.h>
#include <stdlib.h>
#include "ObraArte.h"

int main()
{
    
    char quadro[ALTURA][LARGURA];
    criarQuadroVazio(quadro);
    int i;
    for (i = 0; i < 20; i++) {
        insereFigura(quadro,LX,1);

    }
//
    mostrarQuadro(quadro);

    return 0;
}
