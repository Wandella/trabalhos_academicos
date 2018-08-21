#define TAMPALAVRA 50
typedef struct{
char palavra[TAMPALAVRA];
int linha;
}TItem;

typedef struct{
TItem elemento;
struct TCelula *pprox;
}TCelula;

typedef struct{
TCelula *pultimo;
TCelula *pprimeiro;
int tamanho;
}TLista;

void Crialistapropria(TLista *a);
void Preencherlista(TLista *a,char *b,int *linha);
char* Removep(TLista *a,int *p);
void Imprimelista(TLista *a);
int Verificatamanho(TLista *a);
void QuickSort(TItem *vetor, int n);
void Particao(int Esq, int Dir,int *i, int *j, TItem *vetor);
void Ordena(int Esq, int Dir, TItem *vetor);
int PesquisaSequencial(TItem *vetor, char palavra[], int qtdpalavra, int *qtpesq);
int PesquisaBinaria(TItem *vetor, char palavra[], int qtdpalavra, int *qtpesq);
