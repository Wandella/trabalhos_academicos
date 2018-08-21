#include "SBB.h"

int main(int argc, char *argv[]){
    TipoApontador  Dicionario;
  TipoRegistro x;
  int  vetor[MAX];
  int i, j, k, n;
  struct timeval semente;
  Inicializa(&Dicionario);
  /* Gera uma permutacao aleatoria de chaves entre 1 e MAX */

  /* utilizar o tempo como semente para a funcao srand()   */
  gettimeofday(&semente,NULL);
  srand((int)(semente.tv_sec + 1000000*semente.tv_usec));

  for (i = 1; i <= MAX; i++)
    vetor[i-1] = i;
  Permut(vetor, MAX);

  /* Insere cada chave na arvore e testa sua integridade apos cada insercao */
  for (i = 1; i <= MAX; i++)
  { x.Chave = vetor[i-1];
    Insere(x, &Dicionario);
    printf("Inseriu chave: %d\n", x.Chave);
    Testa(Dicionario);
  }

  /* Retira uma chave aleatoriamente e realiza varias pesquisas */
  for (i = 1; i <= MAX; i++)
    { k = 1 + (int)((float)MAX * rand() / (RAND_MAX + 1.0));
      n = vetor[k - 1];
      x.Chave = n;
      Retira(x, &Dicionario);
      Testa(Dicionario);
      printf("Retirou chave: %d\n", x.Chave);
      for (j = 1; j <= MAX; j++)
      { x.Chave = vetor[(int)((float)MAX * rand()/(RAND_MAX + 1.0))];
        if (x.Chave != n)
        { printf("Pesquisando chave: %d\n", x.Chave);
          Pesquisa(&x, &Dicionario);
        }
      }
      x.Chave = n;
      Insere(x, &Dicionario);
      printf("Inseriu chave: %d\n", x.Chave);
      Testa(Dicionario);
    }
  /* Retira a raiz da arvore ate que ela fique vazia */
  for (i = 1; i <= MAX; i++)
    { x.Chave = Dicionario->Reg.Chave;
      Retira(x, &Dicionario);
      Testa(Dicionario);
      printf("Retirou chave: %d\n", x.Chave);
    }
  return 0;
}
