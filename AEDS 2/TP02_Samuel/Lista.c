/*******************************************************
    UNIVERSIDADE FEDERAL DE VIÇOSA - CAMPUS FLORESTAL
    CIÊNCIA DA COMPUTAÇÃO - 2016/1

    TRABALHO PRÁTICO II
    Algoritmos e Estrutura de Dados II - AEDS 2
    PROFESSORA: Glácia Braga e Silva

    DESENVOLVIDO POR: Samuel Jhonata S. Tavares, 2282

    DESENVOLVIDO NO UBUNTU
********************************************************/

#include "Lista.h"
#define UNIVEL 3
#define ULTIMONIVEL 125

/*cria lista vazia*/
TLista* FLVazia(TLista* listanoH, int nivel)
{
    listanoH = (TLista*) malloc(sizeof(TLista));
    listanoH->pPrimeiro = (Apontador) malloc(sizeof(TCelula));
    listanoH->pUltimo = listanoH->pPrimeiro;
    listanoH->nivel = nivel;
    listanoH->pPrimeiro->pProx = NULL;
    return listanoH;
}

/*insere na lista*/
void LInsere(TLista *pLista,TItem pItem)
{
    pLista->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula));
    pLista->pUltimo = pLista->pUltimo->pProx;
    pLista->pUltimo->Item = pItem;
    pLista->pUltimo->pProx = NULL;
    pLista->Contador++;
}

/*atualiza e imprime os níveis da árvore*/
void LImprime(TLista* pLista, GtkWidget *label[][ULTIMONIVEL])
{
    int i, j, anterior, cont=0, numlabel=0;
    Apontador pAux;

    /*ve em qual nível está*/
    if(pLista->nivel == 0)
    {
        anterior =-99;
    }
    else
    {
        anterior = -1;
    }

    /*caso nao existam paginas no nivel*/
    if (pLista->pPrimeiro == pLista->pUltimo)
    {
        return;
    }

    /*pescorre todas as páginas do nível*/
    pAux = pLista->pPrimeiro->pProx;
    while (pAux != NULL)
    {
        if(anterior == -99)
        {
            /*Caso esteja no primeiro nível*/
            gtk_label_set_text(GTK_LABEL(label[0][0]), pAux->Item.pagina);
            anterior = 0;
        }
        else
        {
            /*olha se pai tem o máximo de filhos possível*/
            if(anterior > pAux->Item.filhoNum)
            {
                while(anterior<4)
                {
                    /*caso não tenha o máximo, pula as labels correspondentes*/
                    anterior++;
                }
                cont++;
            }
            numlabel = pAux->Item.filhoNum + (cont*5);

            /*atualiza a label correspondente à página*/
            gtk_label_set_text(GTK_LABEL(label[pLista->nivel][numlabel]), pAux->Item.pagina);
            anterior = pAux->Item.filhoNum;
        }
        pAux = pAux->pProx; /* próxima célula */
    }
}
