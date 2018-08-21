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

/*salva todos os registros de uma página em uma única string*/
char *escrevePagina(TipoApontador Ap)
{
    int i=0;
    char aux[20], texto[50];

    strcpy(texto, "[");
    while (i < Ap->n)
    {
        snprintf(aux,sizeof(aux),"%d ",Ap->r[i].Chave);
        strcat(texto, aux);
        i++;
    }
    strcat(texto, "]");
    return texto;
}

/*Inicializa a árvore*/
void Inicializa(TipoApontador *arvore)
{
    *arvore = NULL;
}

/*Pesquisa por um registro na árvore*/
void Pesquisa(TipoRegistro *x, TipoApontador Ap)
{
    long i = 1;
    char texto[100], aux[50];

    /*atualiza labels de informação*/
    snprintf(texto, sizeof(texto), "Pesquisando por %d", x->Chave);
    gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);

    /*Caso o registro não esteja na árvore*/
    if (Ap == NULL)
    {
        snprintf(texto, sizeof(texto), "%d não está na árvore!", x->Chave);
        Mensagem(texto, "Pesquisa");
        /*atualiza labels de informação*/
        gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);
        gtk_label_set_text(GTK_LABEL(label_mostra[1]), "");
        return;
    }

    /*atualiza labels de informação*/
    snprintf(texto, sizeof(texto), "Olhando a página %s", escrevePagina(Ap));
    gtk_label_set_text(GTK_LABEL(label_mostra[1]), texto);
    Mensagem(texto, "Pesquisa");

    /*Olha e compara cada registro da página*/
    snprintf(texto, sizeof(texto),"Compara com o %dº registro da pagina (%d)",i, Ap->r[i-1].Chave);
    Mensagem(texto, "Pesquisa");
    while (i < Ap->n && x->Chave > Ap->r[i-1].Chave)
    {
        snprintf(aux,sizeof(aux),"%d > %d. Deve continuar olhando", x->Chave, Ap->r[i-1].Chave);
        Mensagem(aux, "Pesquisa");
        i++;
        snprintf(texto, sizeof(texto),"Compara com o %dº registro da pagina (%d)",i, Ap->r[i-1].Chave);
        Mensagem(texto,"Pesquisa");
    }

    /*A chave seja igual, ela foi encontrada*/
    if (x->Chave == Ap->r[i-1].Chave)
    {
        *x = Ap->r[i-1];

        /*atualiza labels de informação*/
        snprintf(texto, sizeof(texto), "%d foi encontrado na página %s", x->Chave, escrevePagina(Ap));
        Mensagem(texto,"Pesquisa");
        gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);
        gtk_label_set_text(GTK_LABEL(label_mostra[1]), "");
        return;
    }

    /*A chave é maior*/
    if (x->Chave < Ap->r[i-1].Chave)
    {
        /*atualiza labels de informação*/
        snprintf(texto, sizeof(texto), "Deve olhar o filho à esquerda de %d", Ap->r[i-1].Chave);
        Mensagem(texto,"Pesquisa");
        Pesquisa(x, Ap->p[i-1]);
    }
    else/*A chave é menor*/
    {
        /*atualiza labels de informação*/
        snprintf(texto, sizeof(texto), "Deve olhar o filho à direita de %d", Ap->r[i-1].Chave);
        Mensagem(texto,"Pesquisa");

        Pesquisa(x, Ap->p[i]);
    }
}

/*Auxiliar da Insere e Retira*/
void InsereNaPagina(TipoApontador Ap, TipoRegistro Reg, TipoApontador ApDir)
{
    short NaoAchouPosicao;
    int k, filho, cont=0;
    char texto[50], pagina[50];

    strcpy(pagina, escrevePagina(Ap));
    k = Ap->n;
    NaoAchouPosicao = (k > 0);

    /*atualiza labels de informação*/
    strcpy(texto,"Olhando página ");
    strcat(texto, escrevePagina(Ap));
    gtk_label_set_text(GTK_LABEL(label_mostra[1]), texto);

    while (NaoAchouPosicao)
    {
        if (Reg.Chave >= Ap->r[k-1].Chave)
        {
            NaoAchouPosicao = FALSE;
            break;
        }

        /*atualiza labels de informação*/
        strcpy(texto,"Olhando página ");
        strcat(texto, escrevePagina(Ap));
        gtk_label_set_text(GTK_LABEL(label_mostra[1]), texto);

        /*Mensagem*/
        snprintf(texto, sizeof(texto), "Desloca %d uma posição para a direita", Ap->r[k-1]);
        Mensagem(texto,"Insere na Página");

        Ap->r[k] = Ap->r[k-1];
        Ap->p[k+1] = Ap->p[k];

        k--;
        cont++;
        if (k < 1)
            NaoAchouPosicao = FALSE;
    }
    if(cont>0)
    {
        /*Mensagem*/
        snprintf(texto, sizeof(texto), "Salva %d onde estava %d", Reg.Chave, Ap->r[k]);
        Mensagem(texto,"Insere na Página");
    }
    Ap->r[k] = Reg;
    Ap->p[k+1] = ApDir;
    Ap->n++;
    if(cont==0)
    {
        /*Mensagem*/
        snprintf(texto, sizeof(texto), "Salva %d na pagina %s", Reg.Chave, pagina);
        Mensagem(texto,"Insere na Página");
    }

}

/*Auxiliar da Insere*/
void Ins(TipoRegistro Reg, TipoApontador Ap, short *Cresceu, TipoRegistro *RegRetorno,  TipoApontador *ApRetorno)
{
    long i = 1;
    long j;
    char aux[50],texto[100];
    TipoApontador ApTemp;

    /*Não existe página*/
    if (Ap == NULL)
    {
        *Cresceu = TRUE;
        (*RegRetorno) = Reg;
        (*ApRetorno) = NULL;

        Mensagem("Não existe filho","Ins");
        return;
    }

    /*atualiza labels de informação*/
    strcpy(texto, "Olhando a página ");
    strcat(texto, escrevePagina(Ap));
    gtk_label_set_text(GTK_LABEL(label_mostra[1]), texto);
    Mensagem(texto,"Ins");

    /*percorre e compara cada registro da página*/
    snprintf(texto, sizeof(texto),"Compara com o %dº registro da pagina (%d)",i, Ap->r[i-1].Chave);
    Mensagem(texto,"Ins");
    while (i < Ap->n && Reg.Chave > Ap->r[i-1].Chave)
    {
        snprintf(aux,sizeof(aux),"%d > %d. Deve continuar olhando", Reg.Chave, Ap->r[i-1].Chave);
        Mensagem(aux,"Ins");
        i++;
        snprintf(texto, sizeof(texto),"Compara com o %dº registro da pagina (%d)",i, Ap->r[i-1].Chave);
        Mensagem(texto,"Ins");
    }

    /*Registro é igual*/
    if (Reg.Chave == Ap->r[i-1].Chave)
    {
        *Cresceu = FALSE;

        /*Mensagem*/
        snprintf(texto, sizeof(texto),"%d já está na árvore!", Reg.Chave);
        gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);
        Mensagem(texto,"Ins");
        return;
    }

    /*Registro  é menor*/
    if (Reg.Chave < Ap->r[i-1].Chave)
    {
        /*Mensagem*/
        snprintf(aux,sizeof(aux),"%d < %d. Deve olhar o filho a esquerda de [%d]", Reg.Chave, Ap->r[i-1].Chave, Ap->r[i-1].Chave);
        Mensagem(aux,"Ins");
        i--;
    }
    else /*Registro é maior*/
    {
        /*Mensagem*/
        snprintf(aux,sizeof(aux),"%d > %d. Deve olhar o filho a direita de [%d]", Reg.Chave, Ap->r[i-1].Chave, Ap->r[i-1].Chave);
        Mensagem(aux,"Ins");
    }

    Ins(Reg, Ap->p[i], Cresceu, RegRetorno, ApRetorno);

    if (!*Cresceu)
    {
        return;
    }
    if (Ap->n < MM)   /* Pagina tem espaco */
    {
        /*Mensagem*/
        snprintf(texto, sizeof(texto), "Há espaço na página %s. Registro será salvo nela", escrevePagina(Ap));
        Mensagem(texto,"Ins");

        InsereNaPagina(Ap, *RegRetorno, *ApRetorno);
        *Cresceu = FALSE;

        /*atualiza labels de informação*/
        Mensagem("Inserido com Sucesso!","Ins");
        gtk_label_set_text(GTK_LABEL(label_mostra[0]), "Inserido com Sucesso!!!");
        return;
    }
    /* Overflow: Pagina tem que ser dividida */

    /*Mensagem*/
    snprintf(texto, sizeof(texto), "A página %s ficou cheia e deve ser dividida", escrevePagina(Ap));
    Mensagem(texto,"Ins");

    /*Cria uma nova página*/
    ApTemp = (TipoApontador)malloc(sizeof(TipoPagina));
    ApTemp->n = 0;
    ApTemp->p[0] = NULL;
    Mensagem("Um página vazia foi criada","Ins");

    if (i < M + 1)
    {
        InsereNaPagina(ApTemp, Ap->r[MM-1], Ap->p[MM]);
        Ap->n--;
        InsereNaPagina(Ap, *RegRetorno, *ApRetorno);
    }
    else
    {
        InsereNaPagina(ApTemp, *RegRetorno, *ApRetorno);
    }
    for (j = M + 2; j <= MM; j++)
    {
        InsereNaPagina(ApTemp, Ap->r[j-1], Ap->p[j]);
    }

    Ap->n = M;
    ApTemp->p[0] = Ap->p[M+1];
    *RegRetorno = Ap->r[M];
    *ApRetorno = ApTemp;

    /*Mensagem*/
    snprintf(texto, sizeof(texto), "A página antiga fica: %s ", escrevePagina(Ap));
    Mensagem(texto,"Ins");
}

/*Insere um registro na página*/
void Insere(TipoRegistro Reg, TipoApontador *Ap)
{
    short Cresceu;
    char texto[50];
    TipoRegistro RegRetorno;
    TipoPagina *ApRetorno=NULL, *ApTemp=NULL;

    Ins(Reg, *Ap, &Cresceu, &RegRetorno, &ApRetorno);

    if (Cresceu)  /* Arvore cresce na altura pela raiz */
    {
        /*Mensagem*/
        snprintf(texto, sizeof(texto), "Uma nova raiz foi criada: [%d]", RegRetorno);
        Mensagem(texto,"Insere");

        /*cria uma nova página raiz*/
        ApTemp = (TipoPagina *)malloc(sizeof(TipoPagina));
        ApTemp->n = 1;
        ApTemp->r[0] = RegRetorno;
        ApTemp->p[1] = ApRetorno;
        ApTemp->p[0] = *Ap;
        *Ap = ApTemp;

        gtk_label_set_text(GTK_LABEL(label_mostra[0]), "Inserido com Sucesso!!!"); /*atualiza labels de informação*/
    }
    gtk_label_set_text(GTK_LABEL(label_mostra[1]), "");/*atualiza labels de informação*/

}

/*Auxiliar da Retira*/
void Reconstitui(TipoApontador ApPag, TipoApontador ApPai, int PosPai, short *Diminuiu)
{
    TipoPagina *Aux;
    long DispAux, j;
    char texto[100],sAux1[50],sAux2[30];

    if (PosPai < ApPai->n)  /* Aux = TipoPagina a direita de ApPag */
    {
        /*Mensagem*/
        snprintf(texto, sizeof(texto), "Salva o pai %d na página %s", ApPai->r[PosPai], escrevePagina(ApPag));
        Mensagem(texto,"Reconstitui");

        Aux = ApPai->p[PosPai+1];
        DispAux = (Aux->n - M + 1) / 2;
        ApPag->r[ApPag->n] = ApPai->r[PosPai];
        ApPag->p[ApPag->n + 1] = Aux->p[0];
        ApPag->n++;

        if (DispAux > 0)  /* Existe folga: transfere de Aux para ApPag */
        {
            for (j = 1; j < DispAux; j++)
                InsereNaPagina(ApPag, Aux->r[j-1], Aux->p[j]);

            /*Mensagem*/
            snprintf(texto, sizeof(texto), "%d é salvo na página pai %s, no lugar de %d", Aux->r[DispAux-1], escrevePagina(ApPai), ApPai->r[PosPai]);
            Mensagem(texto,"Reconstitui");

            ApPai->r[PosPai] = Aux->r[DispAux-1];
            Aux->n -= DispAux;

            for (j = 0; j < Aux->n; j++)
                Aux->r[j] = Aux->r[j + DispAux];
            for (j = 0; j <= Aux->n; j++)
                Aux->p[j] = Aux->p[j + DispAux];
            *Diminuiu = FALSE;
        }
        else /* Fusao: intercala Aux em ApPag e libera Aux */
        {
            /*salvas os registros da página em uma unica string*/
            strcpy(sAux1,"[");
            for(j=0; j<Aux->n; j++)
            {

                snprintf(sAux2, sizeof(sAux2), "%d ", Aux->r[j]);
                strcat(sAux1,sAux2);
            }
            strcat(sAux1,"]");
            /*Mensagem*/
            snprintf(texto, sizeof(texto), "As páginas %s e %s serão concatenadas na página %s", sAux1,escrevePagina(ApPag),escrevePagina(ApPag));
            Mensagem(texto,"Reconstitui");

            for (j = 1; j <= M; j++)
                InsereNaPagina(ApPag, Aux->r[j-1], Aux->p[j]);

            /*salvas os registros da página em uma unica string*/
            strcpy(sAux1,"[");
            for(j=0; j<Aux->n; j++)
            {

                snprintf(sAux2, sizeof(sAux2), "%d ", Aux->r[j]);
                strcat(sAux1,sAux2);
            }
            strcat(sAux1,"]");
            /*Mensagem*/
            snprintf(texto, sizeof(texto), "Exclui a página %s", sAux1);
            Mensagem(texto,"Reconstitui");

            free(Aux);

            /*reajusta vetor*/
            for (j = PosPai + 1; j < ApPai->n; j++)
            {
                snprintf(texto, sizeof(texto), "Anda com %d para a esquerda e atualiza o ponteiro",ApPai->r[j]);
                Mensagem(texto,"Reconstitui");
                ApPai->r[j-1] = ApPai->r[j];
                ApPai->p[j] = ApPai->p[j+1];
            }
            ApPai->n--;

            if (ApPai->n >= M) *Diminuiu = FALSE;
        }
    }
    else /* Aux = TipoPagina a esquerda de ApPag */
    {
        Aux = ApPai->p[PosPai-1];
        DispAux = (Aux->n - M + 1) / 2;
        for (j = ApPag->n; j >= 1; j--)
            ApPag->r[j] = ApPag->r[j-1];

        /*Mensagem*/
        snprintf(texto, sizeof(texto), "Transfere %d para a pagina %s", ApPai->r[PosPai-1], escrevePagina(ApPag));
        Mensagem(texto,"Reconstitui");

        ApPag->r[0] = ApPai->r[PosPai-1];

        for (j = ApPag->n; j >= 0; j--)
            ApPag->p[j+1] = ApPag->p[j];

        ApPag->n++;

        if (DispAux > 0) /* Existe folga: transf. de Aux para ApPag */
        {
            /*salvas os registros da página em uma unica string*/
            strcpy(sAux1,"[");
            for(j=0; j<Aux->n; j++)
            {

                snprintf(sAux2, sizeof(sAux2), "%d ", Aux->r[j]);
                strcat(sAux1,sAux2);
            }
            strcat(sAux1,"]");
            /*Mensagem*/
            snprintf(texto, sizeof(texto) ,"Existe folga: transf. %s para %s", sAux1, escrevePagina(ApPag));
            Mensagem(texto,"Reconstitui");

            for (j = 1; j < DispAux; j++)
                InsereNaPagina(ApPag, Aux->r[Aux->n - j], Aux->p[Aux->n - j + 1]);
            ApPag->p[0] = Aux->p[Aux->n - DispAux + 1];
            ApPai->r[PosPai-1] = Aux->r[Aux->n - DispAux];
            Aux->n -= DispAux;
            *Diminuiu = FALSE;
        }
        else /* Fusao: intercala ApPag em Aux e libera ApPag */
        {
            /*salvas os registros da página em uma unica string*/
            strcpy(sAux1,"[");
            for(j=0; j<Aux->n; j++)
            {

                snprintf(sAux2, sizeof(sAux2), "%d ", Aux->r[j]);
                strcat(sAux1,sAux2);
            }
            strcat(sAux1,"]");
            /*Mensagem*/
            snprintf(texto, sizeof(texto) ,"A página %s será concatenada na página %s", escrevePagina(ApPag), sAux1);
            Mensagem(texto,"Reconstitui");

            for (j = 1; j <= M; j++)
                InsereNaPagina(Aux, ApPag->r[j-1], ApPag->p[j]);

            free(ApPag);
            ApPai->n--;
            if (ApPai->n >= M)
                *Diminuiu = FALSE;
        }
    }
}

/*Auxiliar da Retira*/
void Antecessor(TipoApontador Ap, int Ind, TipoApontador ApPai, short *Diminuiu)
{
    char texto[100];

    if (ApPai->p[ApPai->n] != NULL)
    {
        Antecessor(Ap, Ind, ApPai->p[ApPai->n], Diminuiu);
        if (*Diminuiu)
        {
            Reconstitui(ApPai->p[ApPai->n], ApPai, (long)ApPai->n, Diminuiu);
        }
        return;
    }

    /*Mensagem*/
    snprintf(texto, sizeof(texto),"Transfere %d para a pagina %s", ApPai->r[ApPai->n - 1], escrevePagina(Ap));
    Mensagem(texto,"Antecessor");

    Ap->r[Ind-1] = ApPai->r[ApPai->n - 1];
    ApPai->n--;
    *Diminuiu = (ApPai->n < M);
}

/*auxiliar da retira*/
void Ret(TipoChave Ch, TipoApontador *Ap, short *Diminuiu)
{
    long j, Ind = 1;
    TipoApontador Pag;
    char texto[100];

    /*Não existe a página*/
    if (*Ap == NULL)
    {
        /*Mensagem*/
        Mensagem("Não existe filho","Ret");
        Mensagem("O registro NÃO está na árvore!","Ret");
        /*Atualiza as labels de informações*/
        gtk_label_set_text(GTK_LABEL(label_mostra[0]), "Não está na árvore!");
        *Diminuiu = FALSE;
        return;
    }

    /*Atualiza as labels de informações*/
    snprintf(texto, sizeof(texto), "Olhando a página %s", escrevePagina(*Ap));
    gtk_label_set_text(GTK_LABEL(label_mostra[1]), texto);
    Mensagem(texto,"Ret");

    /*Percorre todos os registros da página ate o fim ou achar um registro menor*/
    Pag = *Ap;
    snprintf(texto, sizeof(texto), "Compara %d com %d", Ch, Pag->r[Ind-1].Chave);
    Mensagem(texto,"Ret");
    while (Ind < Pag->n && Ch > Pag->r[Ind-1].Chave)
    {
        snprintf(texto,sizeof(texto),"%d > %d. Deve continuar olhando", Ch, Pag->r[Ind-1].Chave);
        Mensagem(texto,"Ret");

        snprintf(texto, sizeof(texto), "Compara %d com %d", Ch, Pag->r[Ind].Chave);
        Mensagem(texto,"Ret");

        Ind++;
    }
    /*Encontrado*/
    if (Ch == Pag->r[Ind-1].Chave)
    {
        snprintf(texto, sizeof(texto), "%d foi encontrado", Ch);
        Mensagem(texto,"Ret");

        /*se nao tiver filho*/
        if (Pag->p[Ind-1] == NULL)   /* TipoPagina folha */
        {
            Mensagem("É uma página folha","Ret");
            Pag->n--;
            *Diminuiu = (Pag->n < M);
            for (j = Ind; j <= Pag->n; j++)
            {
                Pag->r[j-1] = Pag->r[j];
                Pag->p[j] = Pag->p[j+1];
            }

            snprintf(texto, sizeof(texto), "%d foi removido", Ch);
            gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);
            Mensagem(texto,"Ret");
            return;
        }

        /* TipoPagina nao e folha: trocar com antecessor */
        Mensagem("Não é uma página folha, 'antecessor()' será chamada","Ret");
        Antecessor(*Ap, Ind, Pag->p[Ind-1], Diminuiu);
        if (*Diminuiu)
            Reconstitui(Pag->p[Ind-1], *Ap, Ind - 1, Diminuiu);
        return;
    }

    /*registro é maior*/
    if (Ch > Pag->r[Ind-1].Chave)
    {
        snprintf(texto, sizeof(texto), "%d > %d. Será olhado o filho à direita de (%d)", Ch, Pag->r[Ind-1], Pag->r[Ind-1]);
        Mensagem(texto,"Ret");
        Ind++;
    }
    else /*registro é menor*/
    {
        snprintf(texto, sizeof(texto), "%d < %d. Será olhado o filho à esquerda de (%d)", Ch, Pag->r[Ind-1], Pag->r[Ind-1]);
        Mensagem(texto,"Ret");
    }

    Ret(Ch, &Pag->p[Ind-1], Diminuiu);
    if (*Diminuiu)
        Reconstitui(Pag->p[Ind-1], *Ap, Ind - 1, Diminuiu);
}

/*retira um registro da arvore*/
void Retira(TipoChave Ch, TipoApontador *Ap)
{
    char texto[100];
    short Diminuiu;
    TipoApontador Aux;

    /*atualiza labels de informações*/
    snprintf(texto, sizeof(texto), "Removendo %d", Ch);
    gtk_label_set_text(GTK_LABEL(label_mostra[0]), texto);

    Ret(Ch, Ap, &Diminuiu);

    if (Diminuiu && (*Ap)->n == 0)  /* Arvore diminui na altura */
    {
        Aux = *Ap;
        *Ap = Aux->p[0];
        snprintf(texto, sizeof(texto), "A página %s foi excluída", escrevePagina(Aux));
        Mensagem(texto,"Retira");
        free(Aux);
    }
    gtk_label_set_text(GTK_LABEL(label_mostra[1]), "");
}

/*atualiza o hash com as novas páginas*/
void AtualizaA(TipoApontador p, int nivel, Tabela *tabela, int filhoNum, GtkWidget *label[][ULTIMONIVEL])
{
    TItem item;
    char aux[20], pagina[20];
    long i;

    /*se nao existe a página*/
    if (p == NULL)
        return;

    /*salva todas as chaves da página*/
    strcpy(item.pagina, "");
    strcpy(aux, "[");
    printf("Nivel %d : ", nivel);
    for (i = 0; i < p->n; i++)
    {
        printf("%ld ",(long)p->r[i].Chave);
        snprintf(pagina,sizeof(pagina)," %d ",p->r[i].Chave);
        strcat(aux, pagina);
    }
    strcat(aux, "");
    strcpy(item.pagina, aux);
    strcat(item.pagina, "]");

    item.filhoNum = filhoNum;
    InsereH(tabela, item, nivel);// Insere a página no hash, no endereço correspondente ao nivel

    /*salva no hash todos os filhos daquele nó*/
    nivel++;
    for (i = 0; i <= p->n; i++)
    {
        AtualizaA(p->p[i], nivel, tabela, i, label);
    }
}

/*mostra a árvore atualizada na tela*/
void MostraTela(TipoApontador p, GtkWidget *label[][ULTIMONIVEL])
{
    int i, n=0;
    Tabela *tabela;
    tabela = CriarHash();
    AtualizaA(p, n, tabela, -1, label);
    imprimeHash(tabela,label);
}
