#include <stdio.h>

#include "logica.h"

int* geraNumero(int max, int tam) { // gera 'tam' números aleatório entre 0 e 'max'
    int *retorno;
    int i;
    retorno = malloc(sizeof (int) * tam);

    for (i = 0; i < tam; i++) {
        retorno[i] = rand() % (max);
    }

    return retorno;
}

int* copiaVetor(int *a, int tam) { //cria um novo vetor com os mesmos valores do vetor 'a'
    int *retorno;
    int i;
    retorno = malloc(sizeof (int) * tam);

    for (i = 0; i < tam; i++) {
        retorno[i] = a[i];
    }

    return retorno;
}

void mostraVetor(int *a, int tam) { //imprime o vetor na tela
    int i;
    for (i = 0; i < tam; i++) {
        printf("%d ", a[i]);
    }
}


//https://pt.wikipedia.org/wiki/Pesquisa_binária
//retorna a posição em que o número foi encontrado (ou -1 caso contrário)
int PesquisaBinaria(int vet[], int chave, int Tam, int *contaComparacao) {
    int inf = 0; // limite inferior (o primeiro índice de vetor em C é zero          )
    int sup = Tam - 1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
    int meio;

    //percorre o vetor 'metade a metade'
    while (inf <= sup) {
        //comparação do while
        (*contaComparacao)++;

        meio = (inf + sup) / 2;

        //comparação do if
        (*contaComparacao)++;
        if (chave == vet[meio]) {
            return meio;
        }

        //comparação do if
        (*contaComparacao)++;
        if (chave < vet[meio])
            sup = meio - 1;
        else
            inf = meio + 1;
    }
    return -1; // não encontrado
}

//retorna a posição em que o número foi encontrado (ou -1 caso contrário)
int pesquisaSequencial(int vet[], int chave, int tam, int *contaComparacao) {
    int i;
    for (i = 0; i < tam; i++) { //percorre posição a posição, até encontrar a chave
        //comparação do for
        (*contaComparacao)++;

        //comparação do if
        (*contaComparacao)++;
        if (chave == vet[i]) {
            return i;
        }
    }
    return -1; //não encontrado
}

//retorna um par de valores cuja a soma é 'x'
Par* valoresSoma(int x) {
    Par *retorno;
    int qtd;
    
    //calcula a qtd de possibilidades diferentes
    if (x % 2 == 0) { //caso 'x' seja par
        qtd = x / 2 - 1;
    } else { //caso 'x' seja impar
        qtd = x / 2;
    }

    retorno = malloc(sizeof (Par) * qtd);
    int i, j, cont = 0;
    //calcula os pares de valores
    for (i = 1; i <= qtd; i++) {
        for (j = 2; j < x; j++) {
            if (i + j == x) {
                retorno[cont].p1 = i;
                retorno[cont].p2 = j;
                cont++;
            }
        }
    }
    return retorno;
}

//verifica se existem os pares de valores em 'a' cuja soma é 'x'
int verifica(int *a, int x, int tam, int metOrden, int metPesq, int *contaComparacao) {
    Par *pares;
    int *S;
    S = copiaVetor(a, tam); //copia os valores do vetor 'a' em 'S'
    *contaComparacao = 0;
    int i;
    //ordena o vetor
    if (metOrden == QUICK) {
        QuickSort(S, tam, contaComparacao);
    } else if (metOrden == BUBBLE) {
        bubble(S, tam, contaComparacao);
    } else {
        free(S);
        return -1;
    }
    //verifica os pares de valores possíveis
    pares = valoresSoma(x);

    //verifica se existem os pares de valores
    if (metPesq == SEQUENCIAL) {
        for (i = 0; i < x / 2; i++) {
            if (pesquisaSequencial(S, pares[i].p1, tam, contaComparacao) != -1 && pesquisaSequencial(S, pares[i].p2, tam, contaComparacao) != -1) {
                free(S);
                return 1;
            }
        }
    } else if (metPesq == BINARIA) {
        for (i = 0; i < x / 2; i++) {
            if (PesquisaBinaria(S, pares[i].p1, tam, contaComparacao) != -1 && PesquisaBinaria(S, pares[i].p2, tam, contaComparacao) != -1) {
                free(S);
                return 1;
            }
        }
    } else {
        free(S);
        return -1;
    }
    return 0;
}