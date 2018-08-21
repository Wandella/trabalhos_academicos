/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#ifndef arvore_H_INCLUDED
#define arvore_H_INCLUDED

#include "hash.h"
#define M 2
#define MM  (M * 2)
#define FALSE 0
#define TRUE  1
#define UNIVEL 3
#define ULTIMONIVEL 125

typedef int TipoChave;
typedef struct TipoRegistro
{
    TipoChave Chave;
} TipoRegistro;
typedef struct TipoPagina* TipoApontador;
typedef struct TipoPagina
{
    short n;
    TipoRegistro r[MM];
    TipoApontador p[MM + 1];
} TipoPagina;

char *escrevePagina(TipoApontador Ap);
void Inicializa(TipoApontador *arvore);
void Pesquisa(TipoRegistro *x, TipoApontador Ap);
void InsereNaPagina(TipoApontador Ap, TipoRegistro Reg, TipoApontador ApDir);
void Ins(TipoRegistro Reg, TipoApontador Ap, short *Cresceu, TipoRegistro *RegRetorno,  TipoApontador *ApRetorno);
void Insere(TipoRegistro Reg, TipoApontador *Ap);
void Reconstitui(TipoApontador ApPag, TipoApontador ApPai, int PosPai, short *Diminuiu);
void Antecessor(TipoApontador Ap, int Ind, TipoApontador ApPai, short *Diminuiu);
void Ret(TipoChave Ch, TipoApontador *Ap, short *Diminuiu);
void Retira(TipoChave Ch, TipoApontador *Ap);
void AtualizaA(TipoApontador p, int nivel, Tabela *tabela, int filhoNum, GtkWidget *label[][ULTIMONIVEL]);
void MostraTela(TipoApontador p, GtkWidget *label[][ULTIMONIVEL]);
#endif
