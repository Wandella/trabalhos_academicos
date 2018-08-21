/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#ifndef hash_H_INCLUDED
#define hash_H_INCLUDED

#include "Lista.h"
#define M 2
#define MM  (M * 2)
#define UNIVEL 3
#define ULTIMONIVEL 125
#define TAM 3

typedef struct Tabela{
    TLista *tabela[TAM];
}Tabela;

Tabela* CriarHash();
void InsereH(Tabela *Hash, TItem item, int endereco);
void imprimeHash(Tabela *Hash, GtkWidget *label[][ULTIMONIVEL]);

#endif //hash_H_INCLUDED
