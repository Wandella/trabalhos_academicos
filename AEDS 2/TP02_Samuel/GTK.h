/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#ifndef GTK_H_INCLUDED
#define GTK_H_INCLUDED

#include "arvore.h"
#include <gtk/gtk.h>
#include  <unistd.h>
#define M 2
#define MM  (M * 2)
#define UNIVEL 3
#define ULTIMONIVEL 125

/*Declara a arvore e os labels que irão mostrar as informações*/
TipoApontador arvore;
GtkWidget *label_mostra[2];

static void sair(GtkWidget *widget, gpointer data);
static void Mensagem (char *mensagem, char *titulo);
static void CriaJanela(GtkWidget *janela);
static void CriaArvore(GtkWidget *labelArvore[][ULTIMONIVEL], GtkWidget *caixa);
static void mostra(GtkWidget *widget, GtkWidget *label[][ULTIMONIVEL]);
static void Inserir(GtkWidget *widget, gpointer data, TipoApontador *pagina);
static void Retirar(GtkWidget *widget, gpointer data, TipoApontador *pagina);
static void Pesquisar(GtkWidget *widget, gpointer data, TipoApontador *pagina);
static void botaoInserir(GtkWidget *caixa, GtkWidget *labelA);
static void botaoRemover(GtkWidget *caixa, GtkWidget *labelA);
static void botaoPesquisar(GtkWidget *caixa, GtkWidget *labelA);
#endif
