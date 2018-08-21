#ifndef Lista_H_INCLUDED
#define Lista_H_INCLUDED

typedef int TipoChave;

typedef struct {
    TipoChave texto;
    int qtd;
} TItem;

typedef struct Celula* Apontador;

typedef struct Celula {
    TItem Item;
    struct Celula* pProx; /* Apontador pProx; */
} TCelula;

typedef struct {
    Apontador pPrimeiro;
    Apontador pUltimo;
    int Contador;
} TLista;

void FLVazia(TLista* pLista);
int LEhVazia(TLista* pLista);
void LInsere(TLista *pLista,int texto);
void LImprime(TLista* pLista);
void InsereLista(TLista* pLista, int numTexto, int *contador);
int VerificatamanhoLista(TLista *a);
int qtdApareceuT(TLista* pLista, int texto);

#endif
