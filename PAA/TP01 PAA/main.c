#include <stdio.h>
#include <stdlib.h>
#include "logica.h"



int main() {
    int tam, x, metOrden, metPesq, result;
    int *a, *b, numBusca;
    srand(time(NULL)); //semente de números aleatórios setada para o horário atual
    
    tam = 5;
    x = 6;
    metOrden = QUICK;
    metPesq = METODO_1;
    
    a = geraNumero(100, tam);
    b = copiaVetor(a,tam);
    mostraVetor(a, tam);
    
    result = verifica(a,x,tam,metOrden,metPesq);
    if (result == 1) {
        printf("\nÉ possível");
    }else{
        printf("Não é possível");
    }

    
    

    return 0;
}

