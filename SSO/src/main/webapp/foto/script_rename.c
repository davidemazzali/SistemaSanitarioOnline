#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>



int main(){
  srand(0) ;
  //genera stringa data
  //int anno = 2019;
  for(int i = 10 ; i<4786 ; i++){
        int mese = rand()%10;
        int giorno = rand()%28;

        char anno_str[30];
        strcpy(anno_str,"2019");

        char mese_str[30];
        char giorno_str[30];
        char data[30];
        strcpy(data,"");


        sprintf(mese_str, "%d", mese);
        printf("%s \n",mese_str);

        sprintf(giorno_str, "%d", giorno);
        printf("%s \n",giorno_str);



        strcat(data,anno_str);
        strcat(data,"-");
        strcat(data,mese_str);
        strcat(data,"-");
        strcat(data,giorno_str);


        printf("%s \n",data);

        //controlla se il file esiste

        //rinomina il FILE



        char num[30];
        sprintf(num, "%d", i);

        char systemcall[30];
        strcpy(systemcall , "mv ");
        strcat(systemcall , "banana");
        strcat(systemcall , num);
        strcat(systemcall , " ");
        strcat(systemcall , data);
        printf("%s \n",systemcall);
        system(systemcall);


  }














  return(0);
}
