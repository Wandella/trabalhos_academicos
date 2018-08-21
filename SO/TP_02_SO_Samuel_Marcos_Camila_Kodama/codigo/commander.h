#ifndef COMMANDER_H_INCLUDED
#define COMMANDER_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#include "manager.h"

typedef struct Commander{
    int pipeEntrada[2];
    int pipeSaida[2];
    Manager manager;
}Commander;

int carregaComandos(String nomeArq, char *retorno);
void inicializaCommander(Commander *c, int tipoEscalonamento, int tipoPreemptivo, int tipoEntrada);
void recebeComandos(Commander *c, int tipoEntrada);
int executaComando(Commander *c, char comando);

#endif // COMMANDER_H_INCLUDED
