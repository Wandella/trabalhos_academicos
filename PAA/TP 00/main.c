#include <stdio.h>
#include <stdlib.h>
#include "ObraArte.h"

int main()
{

    char quadro[ALTURA][LARGURA];
    criarQuadroVazio(quadro);
    //insereFigura(quadro,LX,100);
    insereAleatorio(quadro,100);
    mostrarQuadro(quadro);

    return 0;
}
