#include <stdio.h>

#include "logica.h"

int* aloca(int tam) {
    return malloc(sizeof (int) * tam);
}

void desaloca(int *i) {
    free(i);
}

int* geraNumero(int max, int tam) { // gera um número aleatório entre 0 e max
    int *retorno;
    int i;
    retorno = aloca(tam);

    for (i = 0; i < tam; i++) {
        retorno[i] = rand() % (max);
    }

    return retorno;
}

int* copiaVetor(int *a, int tam) {
    int *retorno;
    int i;
    retorno = aloca(tam);

    for (i = 0; i < tam; i++) {
        retorno[i] = a[i];
    }

    return retorno;
}

void mostraVetor(int *a, int tam) {
    int i;
    for (i = 0; i < tam; i++) {
        printf("%d ", a[i]);
    }
}


//https://pt.wikipedia.org/wiki/Pesquisa_binária

int PesquisaBinaria(int vet[], int chave, int Tam) {
    int inf = 0; // limite inferior (o primeiro índice de vetor em C é zero          )
    int sup = Tam - 1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
    int meio;
    
    while (inf <= sup) {
        meio = (inf + sup) / 2;
        if (chave == vet[meio]){
            return meio;
        }
        if (chave < vet[meio])
            sup = meio - 1;
        else
            inf = meio + 1;
    }
    return -1; // não encontrado
}

int pesquisaSequencial(int vet[], int chave, int tam) {
    int i;
    for (i = 0; i < tam; i++) {
        if (chave == vet[i]) {
            return i;
        }
    }
    return -1; //não encontrado
}

Par* valoresSoma(int x) {
    Par *retorno;
    int qtd;
    if (x%2 == 0) {
        qtd = x/2 - 1;
    }else{
        qtd = x/2;
    }

    retorno = malloc(sizeof (Par) * qtd);
    int i, j, cont = 0;
    for (i = 1; i <= qtd; i++) {
        for (j = 2; j < x; j++) {
            if (i + j == x) {
                retorno[cont].p1 = i;
                retorno[cont].p2 = j;
                //printf("\n%d e %d", i, j);
                cont++;
            }
        }

    }

    return retorno;
}

int verifica(int *S, int x, int tam, int metOrden, int metPesq) {
    Par *pares;
    int i;
    //ordena o vetor
    if (metOrden == QUICK) {
        QuickSort(S, tam);
    } else if (metOrden == BUBBLE) {
        bubble(S, tam);
    } else {
        return -1;
    }
    //verifica os valores possíveis
    pares = valoresSoma(x);
    
    //verifica se existem os valores
    if (metPesq == SEQUENCIAL) {
        for (i = 0; i < x / 2; i++) {
            if (pesquisaSequencial(S, pares[i].p1, tam) != -1 && pesquisaSequencial(S, pares[i].p2, tam) != -1) {
                return 1;
            }
        }
    } else if (metPesq == BINARIA) {
        for (i = 0; i < x / 2; i++) {
            if (PesquisaBinaria(S, pares[i].p1, tam) != -1 && PesquisaBinaria(S, pares[i].p2, tam) != -1) {
                return 1;
            }
        }
    } else {
        return -1;
    }


    return 0;

}