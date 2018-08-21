#ifndef patricia_H_INCLUDED
#define patricia_H_INCLUDED
#include "Lista.h"
#define TAMPALAVRA 30
typedef struct PALAVRA{
    char palavra[TAMPALAVRA];
} PALAVRA;

typedef enum {
    Interno, Externo
} TipoNo;
typedef struct PItem{
    char Chave[TAMPALAVRA];
    TLista lista;
} PItem;
typedef struct TipoPatNo* PApontador;
typedef struct TipoPatNo {
    TipoNo tipoNo;
    union {
        struct {
            int bit;
            char caracter;
            PApontador Esq, Dir;
        } NInterno ;
        PItem item;
  }NO;
} TipoPatNo;

void Inicializa(PApontador* raiz);
short EExterno(PApontador p);
PApontador CriaNoInt(int bit, PApontador *Esq,  PApontador *Dir, char caracter);
PApontador CriaNoExt(char* palavra, int texto, int *contador);
PApontador InsereEntre(char* k, PApontador* no, int i, char  caracter, int texto, int *contador);
PApontador Insere(char* k, PApontador* no, int texto, int *contador);
void PImprime(PApontador no);
void PBusca(PApontador no, char palavra[TAMPALAVRA], int texto, int *numDocsAparece, int *ocorrencias);
int contaPalavras(PApontador no);
int ContaNos(PApontador no, int *qtd);

#endif
