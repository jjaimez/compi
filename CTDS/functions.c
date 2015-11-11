 #include <stdio.h>

 int printInt(int x){
 	printf("value %d", x);
	return 1 ;
 }


 void assertEquals(int x, int y){
	if(x == y){
		printf("correct \n");
	}
	else{
		printf("incorrect %d %d ************\n",x,y);
	}
 }

 void printIf(){
	printf("if correct \n");
 }

 void printElse(){
 	printf("else correct \n");
 }




