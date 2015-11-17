 #include <stdio.h>

 int printInt(int x){
 	printf("value %d", x);
	return 1 ;
 }

 void printFloat(float x){
 	printf("value %f", x);
 }



 void assertEquals(int x, int y){
	if(x == y){
		printf("correct \n");
	}
	else{
		printf("incorrect %d %d ************\n",x,y);
	}
 }

 void assertEqualsFloat(float x, float y){
	if(x == y){
		printf("correct  %f %f ************\n",x,y);
	}
	else{
		printf("incorrect %f %f ************\n",x,y);
	}
 }

 void printIf(){
	printf("if correct \n");
 }

 void printElse(){
 	printf("else correct \n");
 }

 void printSeparador(){
 	printf("-------------\n");
 }


FILE *fp;  
char buffer[100];

// abre el archivo con los datos de entrada
int init_input(int f){
	if (f==1) fp = fopen ( "input", "r" );
	else      fp = fopen ( "input1", "r" );
}

// cierra el archivo
int close_input(){
	fclose(fp);
}

// lee un dato entero del archivo de entrada
int get_int(){
	int x;	
   	fscanf(fp, "%s" ,buffer); 
    sscanf(buffer, "%d", &x);
    return x;
}

// imprime un entero en la salida
void print_int(int x){
       printf("%d\n",x);	
	}

// lee un dato real del archivo de entrada
float get_float(){
	float x;	
   	fscanf(fp, "%s" ,buffer);
    sscanf(buffer, "%f", &x);
    return x;
}

// imprime un real en la salida
void print_float(float x){
       printf("%f\n",x);	
	}
	
// imprime un string en la salida	
void print_string(int op){
	    
        if (op==1) 	printf("%s\n","---------------------------------------------------------");

        if (op==2) 	printf("%s\n","Factorial Enteros----------------------------------");
        
        if (op==3) 	printf("%s\n","Factorial Reales----------------------------------");
        	        
        if (op==4) 	printf("%s\n","Factorial Array Enteros----------------------------------");
	
        if (op==5) 	printf("%s\n","Nthprime Enteros----------------------------------");

        if (op==6) 	printf("%s\n","Nthprime Array Enteros----------------------------------");        	
	
        if (op==7) 	printf("%s\n","Int2Bin Enteros----------------------------------");
	
        if (op==8) 	printf("%s\n","GCD Enteros----------------------------------");

        if (op==9) 	printf("%s\n","test----------------------------------");        	
	
        if (op==10) 	printf("%s\n","test1----------------------------------");
			
	}
