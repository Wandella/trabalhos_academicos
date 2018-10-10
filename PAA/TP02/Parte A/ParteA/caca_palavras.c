#include <string.h>

#include "caca_palavras.h"


//https://programacaodescomplicada.wordpress.com/2012/11/09/aula-64-alocacao-dinamica-pt-6-alocacao-de-matrizes/
//Adaptado

char** alocarMatriz(int Linhas, int Colunas) { //Recebe a quantidade de Linhas e Colunas como Parâmetro

    char i, j; //Variáveis Auxiliares

    char **m = (char**) malloc(Linhas * sizeof (char*)); //Aloca um Vetor de Ponteiros

    for (i = 0; i < Linhas; i++) { //Percorre as linhas do Vetor de Ponteiros
        m[i] = (char*) malloc(Colunas * sizeof (char)); //Aloca um Vetor de Inteiros para cada posição do Vetor de Ponteiros.
        for (j = 0; j < Colunas; j++) { //Percorre o Vetor de Inteiros atual.
            m[i][j] = 0; //Inicializa com 0.
        }
    }
    return m; //Retorna o Ponteiro para a Matriz Alocada
}

int stringToInt(char *num) {
    int i, n = 0;
    for (i = 0; i < strlen(num); i++) {
        n = (n * 10) + num[i] - 48;
    }
    return n;
}

int carregaArquivo(char *nomeArq, Tabuleiro *retorno) {
    FILE *arq;
    arq = fopen(nomeArq, "r");
    char ch;
    char numLinhas[5] = {}, numColunas[5] = {};
    int i = 0, cont = 0;
    int l = 0, c = 0;
    int atual = 0;

    if (arq == NULL) { //erro ao carregar arquivo
        printf("ERRO AO LER ARQUIVO");
        return 0;
    } else {
        while ((ch = fgetc(arq)) != EOF) { //enquanto o arquivo não termina
            //tamanhos de linha e coluna
            if (i == 0) { //olhando a qtd de linhas e colunas
                if (ch == '\n') {//termina de olhar qtd de linhas e colunas
                    i = 1;
                    numColunas[cont] = '\0';
                    retorno->qtdLinhas = stringToInt(numLinhas);
                    retorno->qtdColunas = stringToInt(numColunas);
                    //printf("\n%d %d\n",*qtdLinhas, *qtdColunas);
                } else {
                    if (ch != ' ') {
                        if (atual == 0) {
                            numLinhas[cont] = ch;
                            cont++;
                        } else {
                            numColunas[cont] = ch;
                            cont++;
                        }
                    } else {
                        atual = 1;
                        numLinhas[cont] = '\0';
                        cont = 0;
                    }
                }
            } else { //depois de pegar qtd de linhas e colunas
                if (i == 1) { //aloca a matriz uma vez
                    retorno->tabuleiro = alocarMatriz(retorno->qtdLinhas, retorno->qtdColunas);
                    //printf("\nalocado\n");
                    i = 2;
                }

                if (ch == '\n') {//quebra de linha
                    l++;
                    c = 0;
                } else { //pegando os caracteres
                    //printf("\n[%d][%d] = %c", l, c, ch);

                    retorno->tabuleiro[l][c] = ch;
                    c++;
                }
            }
        }
    }

    fclose(arq);
    return 1;
}

void copiaTabuleiro(Tabuleiro *original, Tabuleiro *copia) {
    int i, j;
    printf("\n%d %d\n", original->qtdLinhas, original->qtdColunas);
    copia->tabuleiro = alocarMatriz(original->qtdLinhas, original->qtdColunas);
    copia->qtdLinhas = original->qtdLinhas;
    copia->qtdColunas = original->qtdColunas;
    for (i = 0; i < original->qtdLinhas; i++) {
        for (j = 0; j < original->qtdColunas; j++) {
            copia->tabuleiro[i][j] = original->tabuleiro[i][j];
        }
    }
}

void tabuleiroZerado(Tabuleiro *tabuleiro, int qtdLinhas, int qtdColunas) {
    int i, j;
    //printf("\n%d %d\n", original->qtdLinhas, original->qtdColunas);
    tabuleiro->tabuleiro = alocarMatriz(qtdLinhas, qtdColunas);
    tabuleiro->qtdLinhas = qtdLinhas;
    tabuleiro->qtdColunas = qtdColunas;
    for (i = 0; i < qtdLinhas; i++) {
        for (j = 0; j < qtdColunas; j++) {
            tabuleiro->tabuleiro[i][j] = '*';
        }
    }
}

void mostraTabuleiro(Tabuleiro *tabuleiro) {
    int i, j;
    printf("\n%dx%d\n", tabuleiro->qtdLinhas, tabuleiro->qtdColunas);
    for (i = 0; i <= tabuleiro->qtdColunas; i++) {
        printf("- ");
    }

    printf("\n");
    for (i = 0; i < tabuleiro->qtdLinhas; i++) {


        for (j = 0; j < tabuleiro->qtdColunas; j++) {
            if (j == 0) {
                printf("|");
            }
            printf("%c ", tabuleiro->tabuleiro[i][j]);
        }
        printf("|\n");
    }

    for (i = 0; i <= tabuleiro->qtdColunas; i++) {
        printf("- ");
    }
}

void posiciona(int local, Posicao *posicao) {
    switch (local) {
        case ABAIXO:
            posicao->linha = posicao->linha + 1;
            return;
        case ESQUERDA:
            posicao->coluna = posicao->coluna - 1;
            return;
        case DIREITA:
            posicao->coluna = posicao->coluna + 1;
            return;
    }
}

TipoItem montaItem(Posicao posicaoAtual, int tipoMovimento) {
    TipoItem tItem;
    tItem.movimento = tipoMovimento;
    tItem.posicao.linha = posicaoAtual.linha;
    tItem.posicao.coluna = posicaoAtual.coluna;
    return tItem;
}

void cacaPalavra(Tabuleiro *tabuleiro, char *palavra) {

    Tabuleiro resolvido;
    TipoPilha pilha;
    TipoItem tItem;
    Posicao posicaoAtual; //marca posição atual
    int letraAtual = 0; //marca a letra atual q está sendo procurada
    int cont = 0, testa = 0;
    copiaTabuleiro(tabuleiro, &resolvido);
    mostraTabuleiro(tabuleiro);

    FPVazia(&pilha);
    posicaoAtual.linha = 0;
    posicaoAtual.coluna = 0;
    while (cont < 10 && posicaoAtual.linha < resolvido.qtdLinhas && posicaoAtual.coluna < resolvido.qtdColunas) { //percorre todo o tabuleiro
        printf("\n--- olhando %c\n", resolvido.tabuleiro[posicaoAtual.linha][posicaoAtual.coluna]);

        if (letraAtual == 0) {//anterior errado ou primeiro
            //confere a letra atual
            if (resolvido.tabuleiro[posicaoAtual.linha][posicaoAtual.coluna] == palavra[letraAtual]) {// se confere
                printf("\nconfere");
                tItem = montaItem(posicaoAtual, ABAIXO);
                Empilha(tItem, &pilha);
                letraAtual++;
                posiciona(ABAIXO, &posicaoAtual);


            } else { //errado
                printf("\nerrado\n");
                //resolvido.tabuleiro[posicaoAtual.linha][posicaoAtual.coluna] = '*';
                if (posicaoAtual.coluna < resolvido.qtdColunas) { //proxima coluna - se houver
                    printf("proxima coluna\n");
                    (posicaoAtual.coluna)++;
                } else if (posicaoAtual.linha < resolvido.qtdLinhas) { //inicio da proxima linha - se houver
                    (posicaoAtual.linha)++;
                    posicaoAtual.coluna = 0;
                    printf("proxima linha\n");
                } else { //volta pra ultima configuração certa

                }

            }
        } else {//anterior certo
            printf("\n" + tItem.movimento);

            while (letraAtual < strlen(palavra)) {
                printf("\n--- olhando %c\n", resolvido.tabuleiro[posicaoAtual.linha][posicaoAtual.coluna]);
                //confere a letra atual
                if (resolvido.tabuleiro[posicaoAtual.linha][posicaoAtual.coluna] == palavra[letraAtual]) {// se confere
                    switch (tItem.movimento) {
                        case ABAIXO:
                            printf("\nanterior = abaixo");
                            tItem = montaItem(posicaoAtual, ESQUERDA);
                            Empilha(tItem, &pilha);
                            letraAtual++;
                            posiciona(ESQUERDA, &posicaoAtual);
                            break;
                        case ESQUERDA:
                            printf("\nanterior = esquerda");
                            tItem = montaItem(posicaoAtual, DIREITA);
                            Empilha(tItem, &pilha);
                            letraAtual++;
                            posiciona(DIREITA, &posicaoAtual);
                            break;
                        case DIREITA:
                            printf("\nanterior = direita");
                            tItem = montaItem(posicaoAtual, ABAIXO);
                            Empilha(tItem, &pilha);
                            letraAtual++;
                            posiciona(ABAIXO, &posicaoAtual);
                            break;
                    }
                    testa = 0;
                    printf("\nanterior = nenhum");

                } else {
                    if (testa == 0 || testa >= 3) {
                        Desempilha(&pilha, &tItem);
                        posicaoAtual.linha = tItem.posicao.linha;
                        posicaoAtual.coluna = tItem.posicao.coluna;
                    }
                    switch (tItem.movimento) {
                        case ABAIXO:
                            tItem.movimento = ESQUERDA;
                            testa++;
                            break;
                        case ESQUERDA:
                            tItem.movimento = DIREITA;
                            testa++;
                            break;
                        case DIREITA:
                            tItem.movimento = ABAIXO;
                            testa++;
                            break;
                    }
                    letraAtual--;
                }
            }


            switch (tItem.movimento) {
                case ABAIXO:
                    printf("\nanterior = abaixo");
                    tItem = montaItem(posicaoAtual, ESQUERDA);
                    Empilha(tItem, &pilha);
                    letraAtual++;
                    posiciona(ESQUERDA, &posicaoAtual);
                    break;
                case ESQUERDA:
                    printf("\nanterior = esquerda");
                    break;
                case DIREITA:
                    printf("\nanterior = direita");
                    break;
            }
            printf("\nanterior = nenhum");


        }

        if (letraAtual == strlen(palavra)) {
            printf("\n Palavra ENCONTRADA!");
            Imprime(pilha);

            tabuleiroZerado(&resolvido, tabuleiro->qtdLinhas, tabuleiro->qtdColunas);
            while (Tamanho(pilha) > 0) {
                Desempilha(&pilha, &tItem);

                resolvido.tabuleiro[tItem.posicao.linha][tItem.posicao.coluna] = tabuleiro->tabuleiro[tItem.posicao.linha][tItem.posicao.coluna];
            }

            break;
        }
        cont++;
    }


    mostraTabuleiro(&resolvido);
}