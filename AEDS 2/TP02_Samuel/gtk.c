/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#include "GTK.h"
#include "info.c"

/*escreve no terminal quando a janela é fechada*/
static void sair(GtkWidget *widget, gpointer data)
{
    g_print("A janela foi fechada\n");
    dSobre();
    gtk_main_quit();
}

/*mostra um dialog com a mensagem desejada */
static void Mensagem (char *mensagem, char *titulo)
{
    GtkWidget *dialog = NULL, *win;

    win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    dialog = gtk_message_dialog_new (GTK_WINDOW (win), GTK_DIALOG_MODAL, GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE, mensagem);
    gtk_window_set_position (GTK_WINDOW (dialog), GTK_WIN_POS_CENTER);
    gtk_window_set_title(GTK_WINDOW (dialog), titulo);
    gtk_dialog_run (GTK_DIALOG (dialog));
    gtk_widget_destroy (dialog);
}

/*Cria a janela principal*/
static void CriaJanela(GtkWidget *janela)
{
    gtk_signal_connect(GTK_OBJECT (janela), "destroy", GTK_SIGNAL_FUNC (sair), NULL);
    gtk_window_set_title(GTK_WINDOW (janela), "Bem vindo a nossa Floresta");
    gtk_widget_set_usize (janela, 1200, 700);//define o tamanho da janela
    gtk_window_set_position (GTK_WINDOW (janela), GTK_WIN_POS_CENTER);//centraliza a janela na tela
}

/*cria as labels que mostrarão os níveis da árvore*/
static void CriaArvore(GtkWidget *labelArvore[][ULTIMONIVEL], GtkWidget *caixa)
{
    GtkWidget *nivel[5];
    int i,j;

    for(j=0; j<UNIVEL; j++)
    {
        /*cria cada nivel da arvore*/
        nivel[j] = gtk_hbox_new(0, 0);
        gtk_box_pack_start(GTK_BOX (caixa), nivel[j], 1, 1, 0);
        gtk_widget_show(nivel[j]);
        gtk_widget_show(nivel[j]);

        for(i=0; i<((pow((2*M+1),j))); i++)//cria a quantidade máxima de páginas pra cada nvel
        {
            /*cria cada label que irá representar as páginas*/
            labelArvore[j][i] = gtk_label_new("");
            gtk_label_set_justify(labelArvore[j][i], GTK_JUSTIFY_CENTER );
            gtk_box_pack_start(GTK_BOX (nivel[j]), labelArvore[j][i], 1, 1, 0);
            gtk_widget_show(labelArvore[j][i]);
        }
    }

}

/*chama a função para atualizar a árvore na tela*/
static void mostra(GtkWidget *widget, GtkWidget *label[][ULTIMONIVEL])
{
    MostraTela(arvore, label);
}

/*insere na arvore a chave entrada no entry inserir*/
static void Inserir(GtkWidget *widget, gpointer data, TipoApontador *pagina)
{
    TipoRegistro a;
    int i;
    char texto[30];

    /*atualiza labels de informação*/
    a.Chave = atoi(gtk_entry_get_text(GTK_ENTRY(data)));
    snprintf(texto,sizeof(texto),"Inserindo %d ",a);
    gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);

    Insere(a,&arvore);//insere na arvore

    /*atualiza labels de informação*/
    gtk_editable_copy_clipboard(GTK_EDITABLE(data));
    gtk_entry_set_text(GTK_ENTRY(data),"");
    gtk_editable_select_region(GTK_EDITABLE(data), 0,-1);
}

/*retira da arvore a chave entrada no entry retira*/
static void Retirar(GtkWidget *widget, gpointer data, TipoApontador *pagina)
{
    int a;
    char texto[30];

    /*atualiza labels de informação*/
    a = atoi(gtk_entry_get_text(GTK_ENTRY(data)));
    snprintf(texto,sizeof(texto),"Retirando %d ",a);
    gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);

    Retira(a,&arvore);

    /*atualiza labels de informação*/
    gtk_editable_copy_clipboard(GTK_EDITABLE(data));
    gtk_entry_set_text(GTK_ENTRY(data),"");
    gtk_editable_select_region(GTK_EDITABLE(data), 0,-1);
}

/*pesquina na arvore a chave entrada no entry pesquisa*/
static void Pesquisar(GtkWidget *widget, gpointer data, TipoApontador *pagina)
{
    int a;
    char texto[30];

    /*atualiza labels de informação*/
    a = atoi(gtk_entry_get_text(GTK_ENTRY(data)));
    snprintf(texto,sizeof(texto),"Pesquisando por %d ",a);
    gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);

    Pesquisa(&a,arvore);

    /*atualiza labels de informação*/
    gtk_editable_copy_clipboard(GTK_EDITABLE(data));
    gtk_entry_set_text(GTK_ENTRY(data),"");
    gtk_editable_select_region(GTK_EDITABLE(data), 0,-1);
}

/*cria label, entry e botao inserir*/
static void botaoInserir(GtkWidget *caixa, GtkWidget *labelA)
{
    GtkWidget *botao,*txt, *label;

    /*cria label*/
    label = gtk_label_new("Entre um número para Inserir");
    gtk_label_set_justify(label, GTK_JUSTIFY_LEFT );
    gtk_box_pack_start(GTK_BOX (caixa), label, FALSE, TRUE, 0);
    gtk_widget_show(label);

    /*cria entry*/
    txt = gtk_entry_new();
    g_signal_connect(txt, "activate", G_CALLBACK(Inserir), txt);
    g_signal_connect(txt, "activate", G_CALLBACK(mostra), labelA);
    gtk_box_pack_start(GTK_BOX (caixa), txt, FALSE, TRUE, 0);
    gtk_widget_show(txt);

    /*cria o botao*/
    botao = gtk_button_new_with_mnemonic("Inserir");
    g_signal_connect(botao, "clicked", G_CALLBACK(Inserir), txt);
    g_signal_connect(botao, "clicked", G_CALLBACK(mostra), labelA);
    gtk_box_pack_start(GTK_BOX (caixa), botao, FALSE, TRUE, 0);
    gtk_widget_show(botao);
}

/*cria label, entry e botao remover*/
static void botaoRemover(GtkWidget *caixa, GtkWidget *labelA)
{
    GtkWidget *botao, *label, *txt;

    /*cria a label*/
    label = gtk_label_new("Entre um número para Remover");
    gtk_label_set_justify(label, GTK_JUSTIFY_LEFT );
    gtk_box_pack_start(GTK_BOX (caixa), label, FALSE, TRUE, 0);
    gtk_widget_show(label);

    /*cria o entry*/
    txt = gtk_entry_new();
    g_signal_connect(txt, "activate", G_CALLBACK(Retirar), txt);
    g_signal_connect(txt, "activate", G_CALLBACK(mostra), labelA);
    gtk_box_pack_start(GTK_BOX (caixa), txt, FALSE, TRUE, 0);
    gtk_widget_show(txt);

    /*cria o botao*/
    botao = gtk_button_new_with_mnemonic("Remover");
    g_signal_connect(botao, "clicked", G_CALLBACK(Retirar), txt);
    g_signal_connect(botao, "clicked", G_CALLBACK(mostra), labelA);
    gtk_box_pack_start(GTK_BOX (caixa), botao, FALSE, TRUE, 0);
    gtk_widget_show(botao);
}

/*cria label, entry e botao pesquisar*/
static void botaoPesquisar(GtkWidget *caixa, GtkWidget *labelA)
{
    GtkWidget *botao, *label, *txt;

    /*cria a label*/
    label = gtk_label_new("Entre um número para Pesquisar");
    gtk_label_set_justify(label, GTK_JUSTIFY_LEFT );
    gtk_box_pack_start(GTK_BOX (caixa), label, FALSE, TRUE, 0);
    gtk_widget_show(label);

    /*cria o entry*/
    txt = gtk_entry_new();
    g_signal_connect(txt, "activate", G_CALLBACK(Pesquisar), txt);
    gtk_box_pack_start(GTK_BOX (caixa), txt, FALSE, TRUE, 0);
    gtk_widget_show(txt);

    /*cria o botao*/
    botao = gtk_button_new_with_mnemonic("Pesquisar");
    g_signal_connect(botao, "clicked", G_CALLBACK(Pesquisar), txt);
    gtk_box_pack_start(GTK_BOX (caixa), botao, FALSE, TRUE, 0);
    gtk_widget_show(botao);
}

/*cria label, entry e botao pesquisar*/
static void botoes(GtkWidget *caixa)
{
    GtkWidget *botaoInfo,*botaoPesquisa, *botaoInsercao, *botaoRemocao, *botaoSobre, *label, *txt;

    /*cria o botao Informações*/
    botaoInfo = gtk_button_new_with_mnemonic("Informações");
    g_signal_connect(botaoInfo, "clicked", G_CALLBACK(dInfo), NULL);
    gtk_box_pack_start(GTK_BOX (caixa), botaoInfo, FALSE, TRUE, 0);
    gtk_widget_show(botaoInfo);

    /*cria o botao Pesquisa*/
    botaoPesquisa = gtk_button_new_with_mnemonic("Pesquisa");
    g_signal_connect(botaoPesquisa, "clicked", G_CALLBACK(dPesquisa), NULL);
    gtk_box_pack_start(GTK_BOX (caixa), botaoPesquisa, FALSE, TRUE, 0);
    gtk_widget_show(botaoPesquisa);

    /*cria o botao Inserção*/
    botaoInsercao = gtk_button_new_with_mnemonic("Inserção");
    g_signal_connect(botaoInsercao, "clicked", G_CALLBACK(dInsercao), NULL);
    gtk_box_pack_start(GTK_BOX (caixa), botaoInsercao, FALSE, TRUE, 0);
    gtk_widget_show(botaoInsercao);

    /*cria o botao*/
    botaoSobre = gtk_button_new_with_mnemonic("Sobre");
    g_signal_connect(botaoSobre, "clicked", G_CALLBACK(dSobre), NULL);
    gtk_box_pack_start(GTK_BOX (caixa), botaoSobre, FALSE, TRUE, 0);
    gtk_widget_show(botaoSobre);
}
