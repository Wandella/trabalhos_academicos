/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#include "hash.h"
#include "arvore.h"

Tabela* CriarHash()
{
    int i;
    Tabela *h;
    h = (Tabela*) malloc(sizeof(Tabela));
    for(i=0; i<TAM; i++)
    {
        h->tabela[i]= FLVazia(&(h->tabela[i]),i);
    }
    return h;
}
void InsereH(Tabela *Hash, TItem item, int endereco)
{
    LInsere(Hash->tabela[endereco], item);
}

void imprimeHash(Tabela *Hash, GtkWidget *label[][ULTIMONIVEL])
{
    int i,j;

    /*limpa todas as labels da arvore*/
    for(i=0; i<UNIVEL; i++)
    {
        for(j=0; j<((pow((MM+1),i))); j++) gtk_label_set_text(GTK_LABEL(label[i][j]), "");
    }

    /*imprime cada nível da árvore nas labels correspondentes*/
    for(i=0; i<TAM; i++)
    {
        LImprime(Hash->tabela[i],label);
    }
}
