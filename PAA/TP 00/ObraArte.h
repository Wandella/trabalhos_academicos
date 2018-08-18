#ifndef OBRAARTE_H_INCLUDED
#define OBRAARTE_H_INCLUDED

#define ALTURA 20
#define LARGURA 80

#define ASTERISCO 1
#define SOMA 2
#define LX 3
#define LT 4

void criarQuadroVazio(char quadro[][LARGURA]);
void mostrarQuadro(char quadro[][LARGURA]);
void insereAsterisco(char quadro[][LARGURA], int qtd);
void insereSoma(char quadro[][LARGURA], int qtd);
void insereX(char quadro[][LARGURA], int qtd);
void insereAleatorio(char quadro[][LARGURA], int qtd);
void insereOutra(char quadro[][LARGURA], int qtd);
int verificaVazio(char quadro[][LARGURA], int x, int y);
int geraNumero(int max);
void contaFiguraADD(int totalf);


#endif // OBRAARTE_H_INCLUDED
