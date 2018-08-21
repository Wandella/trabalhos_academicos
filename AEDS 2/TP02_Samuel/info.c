/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#include <gtk/gtk.h>

/*faz uma label*/
static void FLabel(int posicionamento,GtkWidget *dialog, char *texto)
{
    GtkWidget *caixa, *label;

    caixa = gtk_hbox_new(0, 0);
    gtk_box_pack_start(GTK_BOX( GTK_DIALOG( dialog )->vbox), caixa, 0, 0, 0);
    gtk_widget_show(caixa);
    label = gtk_label_new(texto);
    gtk_label_set_justify(label, GTK_JUSTIFY_LEFT);
    gtk_box_pack_start( GTK_BOX(caixa), label, posicionamento, 0, 1 );
    gtk_widget_show(label);
}

/*mostra informações sobre definição da arvore B em um dialog*/
static void dInfo()
{
    GtkWidget *dialog = NULL, *win, *caixa, *label;

    win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    dialog = gtk_message_dialog_new (GTK_WINDOW (win), GTK_DIALOG_MODAL, GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE, "Uma árvore B possui as seguintes propriedades:");
    gtk_window_set_position (GTK_WINDOW (dialog), GTK_WIN_POS_CENTER);
    gtk_window_set_title(GTK_WINDOW (dialog), "Definição:");

    FLabel(0,dialog, "As árvores B são árvores balanceadas projetadas para trabalhar com dispositivos de armazenamento secundário como discos magnéticos.");
    FLabel(0,dialog, "Elas visam otimizar as operações de entrada e saída nos dispositivos. O tempo de acesso às informações em um disco é prejudicado principalmente pelo tempo de posicionamento do");
    FLabel(0,dialog, "braço de leitura. Uma vez que o braço esteja posicionado no local correto, a leitura pode ser feita de forma bastante rápida. Desta forma, devemos minimizar o número de acessos ao disco.");
    FLabel(0,dialog, "Diferente das árvores binárias, cada nó em uma árvore B pode ter muitos filhos, isto é, o grau de um nó pode ser muito grande.");
    FLabel(0,dialog, "1. Todo o nó X possui os seguintes campos:");
    FLabel(0,dialog, "a. n, o número de chaves armazenadas em X;");
    FLabel(0,dialog, "b. as n chaves k1, k2...kn são armazenadas em ordem crescente;");
    FLabel(0,dialog, "c. folha, que indica se X é uma folha ou um nó interno.");
    FLabel(0,dialog, "");
    FLabel(0,dialog, "2. Se X é um nó interno então ele possui n+1 ponteiros f1, f2...fn+1 para seus filhos (podendo alguns serem nulos)");
    FLabel(0,dialog, "");
    FLabel(0,dialog, "3. Todas as folhas da árvore estão na mesma altura (que é a altura da árvore).");
    FLabel(0,dialog, "");
    FLabel(0,dialog, "4. Existe um número máximo e mínimo de filhos em um nó. Este número pode ser descrito em termos de um inteiro fixo t maior ou igual a 2 chamado grau mínimo.");
    FLabel(0,dialog, "a. Todo o nó diferente da raiz deve possuir pelo menos t-1 chaves. Todo o nó interno diferente da raiz deve possuir pelo menos t filhos. ");
    FLabel(0,dialog, "Se a árvore não é vazia, então a raiz possui pelo menos uma chave.");
    FLabel(0,dialog, "b. Todo o nó pode conter no máximo 2t - 1 chaves. Logo um nó interno pode ter no máximo 2t filhos. Dizemos que um nó é cheio se ele contém 2t - 1 chaves.");

    FLabel(1,dialog ,"Retirado de: http://www.ft.unicamp.br/liag/siteEd/definicao/arvore-b.php");

    gtk_dialog_run (GTK_DIALOG (dialog));
    gtk_widget_destroy (dialog);
}

/*mostra informações sobre pesquisa em arvore B em um dialog*/
static void dPesquisa()
{
    GtkWidget *dialog = NULL, *win, *caixa, *label;

    win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    dialog = gtk_message_dialog_new (GTK_WINDOW (win), GTK_DIALOG_MODAL, GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE, "Como funciona a pesquisa em uma árvore B:");
    gtk_window_set_position (GTK_WINDOW (dialog), GTK_WIN_POS_CENTER);
    gtk_window_set_title(GTK_WINDOW (dialog), "Pesquisa:");

    FLabel(0,dialog, "A busca em uma árvore B é uma função parecida com a de busca em uma árvore de busca binária, exceto o fato de que se deve decidir entre vários caminhos.");
    FLabel(0,dialog, "Como as chaves estão ordenadas, basta realizar uma busca binária nos elementos de cada nó.");
    FLabel(0,dialog, "Isso levará tempo O(lg(t)). Se a chave não for encontrada no nó em questão, continua-se a busca nos filhos deste nó, realizando-se novamente a busca binária.");
    FLabel(0,dialog, "Caso o nó não esteja contido na árvore a busca terminará ao encontrar um ponteiro igual a NULL, ou de forma equivalente, verificando-se que o nó é uma folha.");
    FLabel(0,dialog, "A busca completa pode ser realizada em tempo O(lg(t)logt(n)).");

    FLabel(1,dialog ,"Retirado de: http://www.ft.unicamp.br/liag/siteEd/definicao/arvore-b.php");

    gtk_dialog_run (GTK_DIALOG (dialog));
    gtk_widget_destroy (dialog);
}

/*mostra informações sobre inserção em arvore B em um dialog*/
static void dInsercao()
{
    GtkWidget *dialog = NULL, *win, *caixa, *label;

    win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    dialog = gtk_message_dialog_new (GTK_WINDOW (win), GTK_DIALOG_MODAL, GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE, "Como funciona a inserção em uma árvore B:");
    gtk_window_set_position (GTK_WINDOW (dialog), GTK_WIN_POS_CENTER);
    gtk_window_set_title(GTK_WINDOW (dialog), "Inserção:");

    FLabel(0,dialog, "Para inserir um novo elemento em uma árvore B, basta localizar o nó folha X onde o novo elemento deva ser inserido.");
    FLabel(0,dialog, "Se o nó X estiver cheio, será necessário realizar uma subdivisão de nós que consiste em passar o elemento mediano");
    FLabel(0,dialog, "de X para seu pai e subdividir X em dois novos nós com t - 1 elementos e depois inserir a nova chave.");

    FLabel(1,dialog ,"Retirado de: http://www.ft.unicamp.br/liag/siteEd/definicao/arvore-b.php");

    gtk_dialog_run (GTK_DIALOG (dialog));
    gtk_widget_destroy (dialog);
}

/*mostra informações sobre remoção em arvore B em um dialog*/
static void dRemocao()
{
    GtkWidget *dialog = NULL, *win, *caixa, *label;

    win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    dialog = gtk_message_dialog_new (GTK_WINDOW (win), GTK_DIALOG_MODAL, GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE, "Como funciona a remoção em uma árvore B:");
    gtk_window_set_position (GTK_WINDOW (dialog), GTK_WIN_POS_CENTER);
    gtk_window_set_title(GTK_WINDOW (dialog), "Remoção:");

    FLabel(0,dialog, "A remoção de um elemento de uma árvore B pode ser dividida em dois casos: ");
    FLabel(0,dialog, "1.  O elemento que será removido está em uma folha.");
    FLabel(0,dialog, "2. O elemento que será removido está em um nó interno.");
    FLabel(0,dialog, "Se o elemento estiver sendo removido de um nó não folha, seu sucessor, que deve estar em uma folha, será movido para a posição eliminada e o processo");
    FLabel(0,dialog, "de eliminação procede como se o elemento sucessor fosse removido do nó folha.");
    FLabel(0,dialog, "Quando um elemento for removido de uma folha X e o número de elementos no nó folha diminui para menos que t - 1, deve-se reorganizar a árvore B.");
    FLabel(0,dialog, "A solução mais simples é analisarmos os irmãos da direita ou esquerda de X.");
    FLabel(0,dialog, "Se um dos irmãos (da direita ou esquerda) de X possui mais de t - 1 elementos, a chave k do pai que separa os irmãos pode ser incluída no nó X e a última");
    FLabel(0,dialog, "ou primeira chave do irmão (última se o irmão for da esquerda e primeira se o irmão for da direita) pode ser inserida no pai no lugar de k. ");
    FLabel(0,dialog, "");

    FLabel(1,dialog ,"Retirado de: http://www.ft.unicamp.br/liag/siteEd/definicao/arvore-b.php");

    gtk_dialog_run (GTK_DIALOG (dialog));
    gtk_widget_destroy (dialog);
}

/*mostra informações sobre o programa em um dialog*/
static void dSobre()
{
    GtkWidget *dialog = NULL, *win, *caixa, *label;

    win = gtk_window_new(GTK_WINDOW_TOPLEVEL);
    dialog = gtk_message_dialog_new (GTK_WINDOW (win), GTK_DIALOG_MODAL, GTK_MESSAGE_INFO, GTK_BUTTONS_CLOSE, "Informações sobre o trabalho");
    gtk_window_set_position (GTK_WINDOW (dialog), GTK_WIN_POS_CENTER);
    gtk_window_set_title(GTK_WINDOW (dialog), "Sobre");

    FLabel(1,dialog, "Trabalho Prático II - Algoritmo e Estruturas de Dados II (2016/1)");
    FLabel(1,dialog, "SIMULADOR GRÁFICO PARA ÁRVORE B");
    FLabel(0,dialog, "");
    FLabel(0,dialog, "Desenvolvido no Ubuntu 14.04 ");
    FLabel(0,dialog, "Desenvolvido por Samuel Jhonata S. Tavares, 2282");
    FLabel(0,dialog, "Professora Gláucia Braga e Silva");
    FLabel(0,dialog, "");
    FLabel(1,dialog, "UFV - Universidade Federal de Viçosa");
    FLabel(1,dialog, "Florestal - MG");
    FLabel(1,dialog, "2 0 1 6");

    gtk_dialog_run (GTK_DIALOG (dialog));
    gtk_widget_destroy (dialog);
}
