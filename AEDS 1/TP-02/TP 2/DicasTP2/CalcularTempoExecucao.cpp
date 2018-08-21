#include <windows.h>
#include <stdio.h>

int main() {
        LARGE_INTEGER tempoInicial, tempoFinal, freq;
        float tempoTotal;
        int i;
        QueryPerformanceCounter(&tempoInicial);

for(i=0;i<10;i++)
  printf("teste");

        QueryPerformanceCounter(&tempoFinal);
        QueryPerformanceFrequency(&freq);
        tempoTotal = (float)(tempoFinal.QuadPart - tempoInicial.QuadPart)/freq.QuadPart;
        printf("\n\nTempo total: %f",tempoTotal);
        return 0;
}
