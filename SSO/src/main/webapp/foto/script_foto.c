#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(){
  char url[500];
  char nome_foto[30];
  strcpy(nome_foto,"foto");
  strcpy(url,"");

  char inizio[300];
  char inizio2[300];
  char fine[300];
/*
  strcpy(inizio, "wget http://graph.facebook.com/v2.5/");
  strcpy(fine,"/picture?height=200&height=200");
*/
  char numero[30];

  int i = 10;
  int n = 4000;
  int retry = 0;

  while(i<=n){

    //DOWNLOAD
    strcpy(inizio, "wget http://graph.facebook.com/v2.5/");
    strcpy(fine,"/picture");
    /* ?height=200&height=200 */

    sprintf(numero, "%d", i);
    //printf("%s \n",str);
    strcat(inizio,numero);
    strcat(inizio,fine);
    printf("%s \n ",inizio);
    system(inizio);

    //CHECK

    FILE *tmp;
    tmp = fopen("picture","rb");
    if(tmp==NULL && retry<=5){
      /*i=i-1;
      retry = retry + 1;
      if(retry>5){
        i = i+1;
        n=n+1;
        retry = 0;
      }*/
      n=n+1;
    }
/*
    if(tmp!=NULL || retry>5 ){
      retry=0;
    }
*/


    //RENAME
    strcpy(nome_foto,"'banana");
    strcat(nome_foto,numero);
    strcat(nome_foto,"'");
    strcpy(inizio2, "mmv 'picture' ");
    strcat(inizio2,nome_foto);
    printf("%s \n",inizio2);
    system(inizio2);
  //  mmv 'picture?height=200' 'foto1'


    //strcat("wget http://graph.facebook.com/v2.5/",str);
  //  strcat(url,"/picture?height=200&height=200");
  //  print("wget http://graph.facebook.com/v2.5/" +  + "/picture?height=200&height=200 ");
    i = i+1;
  }

  return(0);
}
