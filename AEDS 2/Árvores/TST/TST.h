#define MAX 50
#define TRUE 1
#define FALSE 0

typedef short boolean;
typedef char TChave;
typedef struct No* ApontadorNo;
typedef struct No{
    TChave dado;
    ApontadorNo Esq, Meio, Dir;
    boolean ehFinalDaPalavra;
}No;

void Inicializa(ApontadorNo* raiz);
ApontadorNo novoNo(TChave x);
void Insere(ApontadorNo* no, TChave* chave);
void Exibir(ApontadorNo raiz);
void ExibirUtil(ApontadorNo no, char* recebePalavra, int indice);
int Busca(ApontadorNo no, TChave* x);
