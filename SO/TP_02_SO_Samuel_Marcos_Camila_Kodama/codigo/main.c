#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "commander.h"

int main()
{
    Commander commander;

    int tipoEscalonamento = -1;
    int tipoPreemptivo = -1;
    int tipoEntrada = -1;

    printf("---------------------- M E N U ----------------------\n");
    printf("Escolha do Escalonamento:\n");
    printf("1- Lista de Prioridade\n");
    printf("2- Relógio\n");

    while (tipoEscalonamento < 1 || tipoEscalonamento > 2) {
        scanf("%d", &tipoEscalonamento);
    }

    printf("Tipo de Escalonamento:\n");
    printf("1- Preemptivo\n");
    printf("2- Não Preemptivo\n\n");

    while (tipoPreemptivo < 1 || tipoPreemptivo > 2) {
        scanf("%d", &tipoPreemptivo);
    }

    printf("Tipo Entrada de Comandos:\n");
    printf("1- Prompt de Comandos\n");
    printf("2- Arquivo\n");

    while (tipoEntrada < 1 || tipoEntrada > 2) {
        scanf("%d", &tipoEntrada);
    }

    inicializaCommander(&commander, tipoEscalonamento, tipoPreemptivo, tipoEntrada);

return 0;
}
