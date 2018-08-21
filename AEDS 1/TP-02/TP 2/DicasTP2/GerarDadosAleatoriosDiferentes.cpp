#include <stdlib.h> 
#include <stdio.h> 
#include <time.h> 

long seed=0;

int getRandom(int min,int max)
{

  int r;

  if(!seed)
  {
    time(&seed);
    srand((unsigned)seed);
  }

  r=min+rand()%(max-min+1);
  
  return r;
}

int main() 
{ 
    int i; 
    for(i=0; i<10; i++) 
    { 
        printf("%d\n", getRandom(1,100)); 
    } 
    printf("\n\n\n\n"); 

    for(i=0; i<10; i++) 
    { 
        printf("%d\n", getRandom(1,100)); 
    } 
    printf("\n\n\n\n"); 

    for(i=0; i<10; i++) 
    { 
        printf("%d\n", getRandom(1,100)); 
    } 


    return 0; 
}
