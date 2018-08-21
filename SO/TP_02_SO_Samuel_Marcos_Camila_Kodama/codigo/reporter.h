#ifndef REPORTER_H_INCLUDED
#define REPORTER_H_INCLUDED

#include "manager.h"


typedef struct Reporter{
  int *pipeEntrada;
  int *pipeSaida;

}Reporter;

void imprimeEstado(Manager *m, char* retorno);

#endif // REPORTER_H_INCLUDED
