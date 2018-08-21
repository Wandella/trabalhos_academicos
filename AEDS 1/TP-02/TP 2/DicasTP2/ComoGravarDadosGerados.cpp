#include <stdio.h>

int main ()
{
FILE *arq;
int result;
char Str[50];
char buffer [50];
int n,tam = 10, contTroc=5, contMov=3, Tempo = 10;

arq = fopen("ArqGrav.txt", "wt");

if (arq == NULL){
    printf("Problemas na CRIACAO do arquivo\n");
    return 0;
} 	

n=sprintf (buffer, "quicsort;%d;%d;%d;%d", tam, contTroc, contMov,Tempo);
result = fprintf(arq,buffer);

if (result == EOF)
   	printf("Erro na Gravacao\n");

printf("\nOs dados foram salvos no arquivo ArqGrav.txt \n\n");
fclose(arq);  
  
return 0;
}

