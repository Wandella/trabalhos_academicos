/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#include "gtk.c"
#include "arvore.h"

int main (int argc, char *argv[])
{
    int i,j,k;
    /*GTK*/
    GtkWidget *janela, *win, *txt, *table;
    GtkWidget *labelArvore[3][ULTIMONIVEL], *label;
    GtkWidget *caixaPrincipal, *caixab, *caixac, *caixa1, *caixa2, *caixa3, *caixa4, *caixa5;
    gtk_init(&argc, &argv);

    /*Cria a Janela*/
    janela = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    CriaJanela(janela);

    /*Cria a Caixa Principal*/
    caixaPrincipal = gtk_vbox_new(1, 0);
    gtk_container_add(GTK_CONTAINER (janela), caixaPrincipal);
    gtk_widget_show(caixaPrincipal);

    /*Cria a caixa onde ficará a arvore*/
    caixa1 = gtk_vbox_new(1, 1);
    gtk_box_pack_start(GTK_BOX (caixaPrincipal), caixa1, FALSE, TRUE, 0);
    gtk_widget_show(caixa1);

    label_mostra[0] = gtk_label_new("Bem Vindo!");
    gtk_box_pack_start(GTK_BOX (caixa1), label_mostra[0], FALSE, 0, 0);
    gtk_widget_show(label_mostra[0]);

    label_mostra[1] = gtk_label_new("Arvore B");
    gtk_box_pack_start(GTK_BOX (caixa1), label_mostra[1], FALSE, 0, 0);
    gtk_widget_show(label_mostra[1]);

    CriaArvore(labelArvore, caixaPrincipal);//Cria as labels onde a arvore sera desenhada

    caixab = gtk_vbox_new(TRUE, 0);
    gtk_box_pack_start(GTK_BOX (caixaPrincipal), caixab, FALSE, TRUE, 0);
    gtk_widget_show(caixab);

    label = gtk_label_new("Escolha a operação que deseja que seja realizada");
    gtk_label_set_justify(label, GTK_JUSTIFY_LEFT );
    gtk_box_pack_start(GTK_BOX (caixab), label, FALSE, TRUE, 0);
    gtk_widget_show(label);

    caixa2 = gtk_hbox_new(TRUE, 0);
    //gtk_widget_set_usize (caixa2, 0, 100);
    gtk_box_pack_start(GTK_BOX (caixab), caixa2, FALSE, FALSE, 0);
    gtk_widget_show(caixa2);

    botaoInserir(caixa2,labelArvore);//Faz o botao para inserir

    caixa3 = gtk_hbox_new(TRUE, 0);
    gtk_box_pack_start(GTK_BOX (caixab), caixa3, FALSE, FALSE, 0);
    gtk_widget_show(caixa3);

    botaoRemover(caixa3,labelArvore);//Faz o botao para remover

    caixa4 = gtk_hbox_new(TRUE, 0);
    //gtk_widget_set_usize (caixa4, 0, 20);
    gtk_box_pack_start(GTK_BOX (caixab), caixa4, FALSE, FALSE, 0);
    gtk_widget_show(caixa4);

    botaoPesquisar(caixa4,labelArvore);//Faz o botao para pesquisar

    caixac = gtk_vbox_new(TRUE, 0);
    //gtk_widget_set_size_request (caixab, 50, 80);
    gtk_box_pack_start(GTK_BOX (caixaPrincipal), caixac, FALSE, TRUE, 0);
    gtk_widget_show(caixac);

    label = gtk_label_new("");
    gtk_label_set_justify(label, GTK_JUSTIFY_LEFT );
    gtk_box_pack_start(GTK_BOX (caixac), label, FALSE, TRUE, 0);
    gtk_widget_show(label);

    label = gtk_label_new("Algumas informações sobre o funcionamento da árvore");
    gtk_label_set_justify(label, GTK_JUSTIFY_LEFT );
    gtk_box_pack_start(GTK_BOX (caixac), label, FALSE, TRUE, 0);
    gtk_widget_show(label);

    caixa5 = gtk_hbox_new(TRUE, 0);
    gtk_box_pack_start(GTK_BOX (caixac), caixa5, FALSE, FALSE, 0);
    gtk_widget_show(caixa5);

    botoes(caixa5);

    label = gtk_label_new("SJST - 2016");
    gtk_label_set_justify(label, GTK_JUSTIFY_LEFT );
    gtk_box_pack_start(GTK_BOX (caixac), label, FALSE, TRUE, 0);
    gtk_widget_show(label);

    gtk_widget_show(janela);

    gtk_main();
    return 0;
}
