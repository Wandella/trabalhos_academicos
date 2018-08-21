#include <time.h>
#define RAND_MAX 100
#define TAM 100

typedef int TipoChave;

typedef struct {
    TipoChave Chave;
    int numero;
} TItem;

typedef struct Celula* Apontador;

typedef struct Celula {
    TItem Item;
    Apontador pProx;
} TCelula;

typedef struct {
    Apontador pPrimeiro;
    Apontador pUltimo;
    int Contador;
} TLista;

//Operacoes
int CriarLista (TLista *pLista); //criar lista com entradas do usuario
int GerarLista (TLista *pLista, int N); //criar lista com valores aleatorios
int LEhVazia(TLista* pLista);
void LImprime(TLista* pLista);
void LInsereFinal(TLista *pLista,TItem* pItem);
Apontador Pesquisa (TLista *pLista, int item);
int Concatenar (TLista *Lista1, TLista *Lista2);
int Intercalar (TLista *Lista1, TLista *Lista2, TLista *ListaInter);
int Divisao (TLista *vetor, TLista *pLista, int k, int listas_atuais);
int Copiar (TLista *Loriginal, TLista *Lcopia);
int Inserir (TLista *pLista, TItem *item, int posicao);
int Remover (TLista *pLista, TItem *item, int posicao);
int Troca(TLista *pLista, TCelula *p);
int Inverte(TLista *pLista);
void MoveMenor(TLista *pLista);
