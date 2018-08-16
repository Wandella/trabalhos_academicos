#include <stdio.h>
#include <stdlib.h>
#include "ObraArte.h"

int main()
{

    /*int *numeros = geraNumeros(100,50);
    
    int i;
    for (i = 0; i < 50; i++) {
        printf("%d ", numeros[i]);

    }*/

    
    
    char quadro[ALTURA][LARGURA];
    criarQuadroVazio(quadro);
    insereFigura(quadro,LT,100);
    mostrarQuadro(quadro);

    return 0;
}
