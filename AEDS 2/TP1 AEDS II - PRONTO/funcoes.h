#ifndef funcoes_H_INCLUDED
#define funcoes_H_INCLUDED

void separaPalavras(char *entrada, PALAVRA *vpalavras, int *qtdPalavrasPesquisa);
float calculaPeso(PApontador indice, char palavra[], int texto, int numDocsTotal);
float calculaRelevancia(PApontador indice, PALAVRA palavras[], int texto, int qtdpalavrasTxt, int numDocsTotal, int totalTermosP);
void PesquisaIndice(PApontador indice, char *termo, int qtdpalavrasTxt[], int numDocsTotal, PALAVRA nomeArqs[]);///usada na main

#endif
