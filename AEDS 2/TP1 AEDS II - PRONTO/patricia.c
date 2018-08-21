#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "patricia.h"

void Inicializa(PApontador* raiz){
    (*raiz)=NULL;
}

short EExterno(PApontador p){ /* Verifica se p^ e nodo externo */
    return (p->tipoNo == Externo);
}

PApontador CriaNoInt(int bit, PApontador *Esq,  PApontador *Dir, char caracter){
    PApontador p;

    p = (PApontador)malloc(sizeof(TipoPatNo));
    p->tipoNo = Interno;
    p->NO.NInterno.Esq = *Esq;
    p->NO.NInterno.Dir = *Dir;
    p->NO.NInterno.bit = bit;
    p->NO.NInterno.caracter  = caracter;
    return p;
}

PApontador CriaNoExt(char* palavra, int texto, int *contador){
    PApontador No;

    No = (PApontador)malloc(sizeof(TipoPatNo));
    No->tipoNo = Externo;
    strcpy(No->NO.item.Chave,palavra);
    FLVazia(&No->NO.item.lista);///cria uma lista onde serao salvos as ocorrencias da palavra em cada texto
    InsereLista(&No->NO.item.lista, texto, contador);///insere o texto e inicializa a qtd de ocorrencias em 0
    return No;
}


PApontador InsereEntre(char* k, PApontador* no, int i, char  caracter, int texto, int *contador){
    PApontador aux;

    if (EExterno(*no) || i < (*no)->NO.NInterno.bit){ /* cria um novo no externo */
        aux = CriaNoExt(k, texto,contador);

        return (CriaNoInt(i,no,&aux,caracter));
    }
    else{
        if (k[(*no)->NO.NInterno.bit] != (*no)->NO.NInterno.caracter)
            (*no)->NO.NInterno.Dir = InsereEntre(k,&(*no)->NO.NInterno.Dir,i,caracter, texto,contador);
        else
            (*no)->NO.NInterno.Esq = InsereEntre(k,&(*no)->NO.NInterno.Esq,i,caracter, texto,contador);

        return (*no);
    }
}

PApontador Insere(char* k, PApontador* no, int texto, int *contador){///insere uma palavra no indice
    PApontador aux;
    int i;

    if (*no == NULL){///caso nao haja registros
        (*no) = CriaNoExt(k, texto,contador);
    }
    else{
        aux = *no;
        while (!EExterno(aux)){
            if(k[aux->NO.NInterno.bit] == (aux->NO.NInterno.caracter))
                aux = aux->NO.NInterno.Esq;
            else
                aux = aux->NO.NInterno.Dir;
        }

        if(strcmp(k, aux->NO.item.Chave)==0){///caso ja tenha a palavra no indice
            InsereLista(&(aux)->NO.item.lista, texto, contador);
            return (*no);
        }
        ///acha o primeiro bit diferente das palavras
        i = 0;
        while (k[i]==(aux->NO.item.Chave[i])) i++;
        (*no) = (InsereEntre(k, no, i,aux->NO.item.Chave[i], texto,contador));///caso ainda nao tenha a palavra no indice
    }
}

void PImprime(PApontador no){///Imprime todos os nos externos
    if(no == NULL){
        printf("Nao ha registros!!!");
        return;
    }

    if(EExterno(no)){
        printf("\n-> %s ", no->NO.item.Chave);
        LImprime(&no->NO.item.lista);
        return;
    }
    else{
        PImprime(no->NO.NInterno.Esq);
        PImprime(no->NO.NInterno.Dir);
    }
}

void PBusca(PApontador no, char palavra[TAMPALAVRA], int texto, int *numDocsAparece, int *ocorrencias){///busca uma palavra na PATRICIA
    if((no)== NULL){
        return;
    }

    if(EExterno((no))){
        if(strcmp((no)->NO.item.Chave, palavra) == 0){
            *numDocsAparece = (no)->NO.item.lista.Contador;
            *ocorrencias = qtdApareceuT(&(no)->NO.item.lista,texto);
            return;
        }else{
            return;
       }
    }
    else{
        if(palavra[(no)->NO.NInterno.bit] == (no)->NO.NInterno.caracter){
            PBusca((no)->NO.NInterno.Esq, palavra, texto, numDocsAparece, ocorrencias);
        }
        if(palavra[(no)->NO.NInterno.bit] != (no)->NO.NInterno.caracter){
            PBusca((no)->NO.NInterno.Dir, palavra, texto, numDocsAparece, ocorrencias);
        }
    }
}
int contaPalavras(PApontador no){///encapsulamento da ContaNos
    int i = 0;

    ContaNos(no, &i);///chama ContaNos para contar qts palavras ha no indice
    return i;
}
int ContaNos(PApontador no, int *qtd){///conta quantas palavras ha no indice invertido
    if(no == NULL){
        return 0;///vazio
    }

    if(EExterno(no)){
        *qtd = *qtd+1;
        return *qtd;
    }
    else{
        ContaNos(no->NO.NInterno.Esq, qtd);
        ContaNos(no->NO.NInterno.Dir, qtd);
    }
}
