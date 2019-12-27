#include <stdio.h>
#include <string.h>
#include <stdlib.h>
//using namespace std;

#define NUM_CARTELLE 1000
#define FOTO_PER_CARTELLA 3

int main(){
  char query[500];
  char statement[500];
  char data[500];
  //char paziente[500];
  char id[500];
  int paziente = 0;

  strcpy(query,"INSERT INTO FOTO VALUES");
  strcpy(statement,"");

  FILE *fp;
  fp = fopen("insert_foto.sql","w");


//id,caricamento,paziente
/*
INSERT INTO PROVINCIA VALUES
('TA', 'Taranto'),
('MN', 'Mantova'),
('VR', 'Verona'),
('VI', 'Vicenza'),
('TN', 'Trento'),
('RE', 'Reggio Emilia');*/
  fprintf(fp,query);
fprintf(fp,"\n");

  for( int i=0 ; i<NUM_CARTELLE ;i++){
    for(int j=0 ; j<FOTO_PER_CARTELLA ; j++){
      strcpy(statement,"");
      strcpy(data,"");
      sprintf(id, "%d", i+1);

      char anno[50];
      strcpy(anno,"2019");

      int mese = rand()%12 +1 ;
      char mese_str[30];
      sprintf(mese_str, "%d", mese);

      int giorno = rand()%28 +1 ;
      char giorno_str[30];
      sprintf(giorno_str, "%d", giorno);

      strcat(data,anno);
      strcat(data,"-");
      strcat(data,mese_str);
      strcat(data,"-");
      strcat(data,giorno_str);

      paziente = paziente + 1 ;
      char paziente_str[30];
      sprintf(paziente_str, "%d", paziente);

      strcat(statement,"('");
      strcat(statement,paziente_str);
      strcat(statement,"','");
      strcat(statement,data);
      strcat(statement,"','");
      strcat(statement,id);
      strcat(statement,"'), \n");
      fprintf(fp,statement);
    }
//    paziente = paziente + 1;
  }


  return(0);
}
