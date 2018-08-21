#include "Lista.h"


int CriarLista (TLista *pLista){
    pLista->pPrimeiro = (Apontador) malloc(sizeof(TCelula)); //cria celula cabeça
    pLista->pUltimo = pLista->pPrimeiro; //faz o ultimo apontar pra cabeça
    pLista->pPrimeiro->pProx = NULL; //faz cabeça apontar pra NULL
    pLista->Contador=0;
}

int GerarLista (TLista *pLista, int N){
    int i;
    CriarLista(pLista);//faz a lista
    srand( (unsigned)time(NULL) ); //gerar numeros
    for (i=0; i<N; i++){
        pLista->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula));//cria celula
        pLista->pUltimo = pLista->pUltimo->pProx; // faz ultimo apontar para novo ultimo
        pLista->pUltimo->Item.numero = rand()%100; //gera numeros entre 0 e 100
        pLista->pUltimo->pProx = NULL; // faz ultima celula apontar para NULL
        pLista->Contador++;
    }
}

int LEhVazia(TLista* pLista){
    return (pLista->pPrimeiro == pLista->pUltimo);
}

void LImprime(TLista* pLista){
    Apontador pAux;
    pAux = pLista->pPrimeiro->pProx;
    while (pAux != NULL) //percorre toda lista
    {
        printf("%d\n", pAux->Item.numero); //imprime o numero
        pAux = pAux->pProx; /* próxima célula */
    }
    free(pAux);
}

int Concatenar (TLista *Lista1, TLista *Lista2){
    Apontador pAux;
    pAux = Lista2->pPrimeiro->pProx;
    // faz com que se crie nova celula e copie o item da lista 2
    while (pAux != NULL){//percorre toda lista
        Lista1->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula)); //cria celula
        Lista1->pUltimo = Lista1->pUltimo->pProx; // ultimo aponta para novo ultimo
        Lista1->pUltimo->Item =  pAux->Item; //salva o item na nova celula
        Lista1->pUltimo->pProx = NULL;//faz ultimo apontar para NULL
        Lista1->Contador++;
        pAux = pAux->pProx;
    }
    free(pAux);
}
////////////////////////////////////////
void LInsereFinal(TLista *pLista,TItem* pItem)
{
    pLista->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula)); //cria celula
    pLista->pUltimo = pLista->pUltimo->pProx;// ultimo aponta para novo ultimo
    pLista->pUltimo->Item = *pItem; //salva o item na nova celula
    pLista->pUltimo->pProx = NULL; //faz ultimo apontar para NULL
    pLista->Contador++;

}

Apontador Pesquisa (TLista *pLista, int item){
   Apontador pAux,pPosicao;
   pAux = pLista->pPrimeiro;
   int nposicao=1;
   while(pAux->pProx != NULL){

       if(pAux->pProx->Item.numero == item){
            pPosicao = pAux->pProx;
            printf("Item encontrado na posicao: %d \n", nposicao);//printf("%d \n", nposicao);
            return pPosicao;
       }
       else{
           pAux = pAux->pProx;
       }
        nposicao++;
   }
        printf("\nNao encontrado\n");
        return NULL;
    free(pAux);
    free(pPosicao);
}

int Intercalar (TLista *Lista1, TLista *Lista2, TLista *ListaInter){
    Apontador pAux1, pAux2;
    int total, i, listamenor, j=0;
    TItem item, item1, item2;
    pAux1 = Lista1->pPrimeiro->pProx; //aponta primeira celula lista 1
    pAux2 = Lista2->pPrimeiro->pProx; //aponta primeira celula lista 2
    CriarLista(ListaInter);
    total = Lista1->Contador + Lista2->Contador;
    for (i=0; i<=total+1; i++){
        if (i%2 == 0 && (pAux1 != NULL)){ //faz com que se for iteraçao par, insira o item da lista 1
            item1 = pAux1->Item;
            LInsereFinal(ListaInter, &item1);
            pAux1 = pAux1->pProx;
        }
        if (pAux1 == NULL){ //se lista 1 acabar
            j=1;
            if (pAux2 != NULL){
            item2 = pAux2->Item;
            LInsereFinal(ListaInter, &item2);
            pAux2 = pAux2->pProx;
            }
        }
        if(i%2 == 1 && (pAux2 != NULL) && j==0){ //faz com que se for iteraçao impar, insira o item da lista 2
            item2 = pAux2->Item;
            LInsereFinal(ListaInter, &item2);
            pAux2 = pAux2->pProx;
        }
    }
    free(pAux1);
    free(pAux2);
}

int Copiar (TLista *Loriginal, TLista *Lcopia){
    Apontador pAux;
    //colar
    pAux = Loriginal->pPrimeiro->pProx;
    while (pAux != NULL){//percorre toda lista
        Lcopia->pUltimo->pProx = (Apontador) malloc(sizeof(TCelula)); //cria celula
        Lcopia->pUltimo = Lcopia->pUltimo->pProx; // ultimo aponta pra novo ultimo
        Lcopia->pUltimo->Item =  pAux->Item; //item da nova lista recebe item da outra lista
        Lcopia->pUltimo->pProx = NULL; // ultimo recebe NULL
        Lcopia->Contador++;
        pAux = pAux->pProx;
    }
    free(pAux);
}

void MoveMenor(TLista *pLista){
    Apontador pAux, pAnterior, pMenor;
    int menor,i=1;
    if (LEhVazia(pLista)) return 0;
    menor = pLista->pPrimeiro->pProx->Item.numero;
    pMenor = pLista->pPrimeiro->pProx;
    pAux = pLista->pPrimeiro->pProx;
    pAnterior = pAux;
    while (pAux->pProx != NULL){//percorre toda lista
        if ((pAux->pProx->Item.numero) < menor){
            pMenor = pAux->pProx;
            menor = pAux->pProx->Item.numero;
            pAnterior = pAux;
        }
        pAux = pAux->pProx;
    }

    if (pMenor == pLista->pPrimeiro->pProx){ //Se for o primeiro
        return 1;
    }
    if(pMenor == pLista->pUltimo){//se for o ultimo
        pAnterior->pProx = NULL;
        pMenor->pProx = pLista->pPrimeiro->pProx;
        pLista->pPrimeiro->pProx = pMenor;
        return 1;
    }
    if(pMenor != pLista->pUltimo && pMenor != pLista->pPrimeiro) { //se estiver no meio da lista
        pAnterior->pProx = pMenor->pProx;
        pMenor->pProx = pLista->pPrimeiro->pProx;
        pLista->pPrimeiro->pProx = pMenor;
        return 1;
    }
    free(pAux);
    free(pMenor);
    free(pAnterior);
}
////////////////////////////
int Remover (TLista *pLista, TItem *item, int posicao){
    Apontador pAux, pAux2;
    int i=0;
    if (LEhVazia(pLista)){ //se a lista for vazia
        system("cls");
        printf("Lista Vazia!!!");
        return 0;
    }
    if (posicao > pLista->Contador || posicao < 1){//se nao existir a posicao
       system("cls");
       printf("Posicao Invalida!!!");
       return 0;
    }
    //se for possivel remover
    pAux = pLista->pPrimeiro;
    pAux2 = pLista->pPrimeiro;
    for (i=0; i<posicao-1;i++){
        pAux = pAux->pProx;
    }
    pAux2 = pAux->pProx;
    pAux->pProx = pAux2->pProx;
    free(pAux2);
    if (pAux->pProx == NULL){
        pLista->pUltimo = pAux;
    }
    pLista->Contador--;

}

int Inserir (TLista *pLista, TItem *item, int posicao){
    Apontador pAnterior, pAtual, pAux;
    int contador=0;
    pAnterior = pLista->pPrimeiro;
    pAtual = pLista->pPrimeiro;
    if (posicao-1 >= pLista->Contador){
        LInsereFinal(pLista, item);
        pLista->Contador++;
        return 0;
    }
    if (posicao == 1){
        pAux = (Apontador) malloc(sizeof(TCelula));
        pAux->Item = *item;
        pAux->pProx = pLista->pPrimeiro->pProx;
        pLista->pPrimeiro->pProx = pAux;
        pLista->Contador++;
        return 0;
    }
    while (contador != posicao-1){
        pAtual = pAtual->pProx;
        contador++;
        //pAnterior = pAnterior->pProx;
    }
    pAux = (Apontador) malloc(sizeof(TCelula));
    pAux->Item = *item;
    pAux->pProx = pAtual->pProx;
    pAtual->pProx = pAux;
    pLista->Contador++;
}

int Troca(TLista *pLista, TCelula *p){
    Apontador pAux,pAux2;
    if(p->pProx==NULL) // testa se existe celula
        printf("Nao existe celula P!\n");
    else{
        pAux = pLista->pPrimeiro; // auxiliar recebe a lista
        pAux2 = p->pProx; //auxiliar recebe a celula
        while(pAux->pProx != p){
            pAux=pAux->pProx;
        }
        p->pProx = pAux2->pProx;
        pAux2->pProx = p;
        pAux->pProx = pAux2;
        if(p->pProx==NULL) // se for no final
            pLista->pUltimo=p;
    }
}

int Inverte(TLista *pLista){
    if(LEhVazia(pLista))
       return 0;
    Apontador pAux,pPosterior,pAnterior;
    pAux = pLista->pPrimeiro->pProx;
    pAnterior = pPosterior = NULL;
    while (pAux != NULL){//percorre toda lista
        pAnterior = pAux->pProx;
        pAux->pProx = pPosterior;
        pPosterior = pAux;
        pAux = pAnterior;
    }
    pLista->pUltimo = pLista->pPrimeiro->pProx;
    pLista->pPrimeiro->pProx = pPosterior;
    return 1;

}

int Divisao (TLista *vetor, TLista *pLista, int k, int listas_atuais){
    int i,j,celporlista, resto,contador=0;
    Apontador pAux;
    TItem item;
    if (LEhVazia(pLista)) return 0;
    if (k > pLista->Contador){
        printf("Celulas insuficientes");
        return 0;
    }
    celporlista = (pLista->Contador) / k;
    resto = (pLista->Contador)%k;
    pAux = pLista->pPrimeiro->pProx;
    if (resto == 0){
        for (i=0; i<k; i++){
            CriarLista(&vetor[listas_atuais]);

            for (j=0; j<celporlista; j++){
                item = pAux->Item;
                LInsereFinal(&vetor[listas_atuais],&item);

                pAux = pAux->pProx;
            }
            listas_atuais++;
        }
    }else{
        for (i=0; i<k-1; i++){
            CriarLista(&vetor[listas_atuais]);

            for (j=0; j<celporlista; j++){
                item = pAux->Item;
                LInsereFinal(&vetor[listas_atuais],&item);
                contador++;
                pAux = pAux->pProx;
            }
            listas_atuais++;
        }
        CriarLista(&vetor[listas_atuais]);
        for (i=contador; i<pLista->Contador; i++){
            item = pAux->Item;
            LInsereFinal(&vetor[listas_atuais],&item);
            pAux = pAux->pProx;
        }
        listas_atuais++;
    }
    printf("\nLista:\n");
    LImprime(pLista);
    printf("\n");
    for (i=0;i<=k;i++){
        printf("\nLista:%d\n",i);
        LImprime(&vetor[listas_atuais-k+i-1]);
    }
    return listas_atuais;
}
