#include <stdio.h>
#include <stdlib.h>
#include "logica.h"



int main() {
    int tam, x, result1, result2, result3, result4;
    int *a, *b;
    srand(time(NULL)); //semente de números aleatórios setada para o horário atual
    
    tam = 5;
    x = 5;
    
    a = geraNumero(10, tam);
    b = copiaVetor(a,tam);
    mostraVetor(a, tam);
    
    result1 = verifica(a,x,tam,BUBBLE,SEQUENCIAL);
    result2 = verifica(a,x,tam,BUBBLE,BINARIA);
    result3 = verifica(a,x,tam,QUICK,SEQUENCIAL);
    result4 = verifica(a,x,tam,QUICK,BINARIA);
    
    if (result1 == 1 && result2 == 1  && result3 == 1 && result4 == 1) {
        printf("\nÉ possível");
    }else if (result1 == 0 && result2 == 0  && result3 == 0 && result4 == 0){
        printf("\nNão é possível");
    }else{
        printf("\nDIVERGENCIA!!! (%d,%d,%d,%d)",result1,result2,result3,result4);
    }

    
    

    return 0;
}

