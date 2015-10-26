Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
Proyecto: CompiladorCTDS



------------------COMPILACION MANUAL-------------

-ubicarse en la carpeta CTDS y correr el siguiente código para compilar (Si falla, crear una carpeta class en CTDS): 

:~CTDS$ javac -d class -cp src:libs/java-cup-11a.jar src/ctds.java     

luego ejecutar el programa : 

:~CTDS$ java -cp libs/java-cup-11a.jar:class/ main /RutaDelArchivo

	ejemplo: java -cp libs/java-cup-11a.jar:class/ main /test/test1.ctds
			 java -cp libs/java-cup-11a.jar:class/ main /testSemantica/test20.ctds


------------------COMPILACION CON SCRIPTS-------------

-ubicarse en la carpeta CTDS
-compilar el proyecto con ./scriptCompilar.sh
-correr el compilador con ./ctds.sh NOMBRE_ARCHIVO.ctds

-------------------------------------------------------

En src/parser se encuentra los archivos jflex y cup, para compilar el cup ejecutar CompilarCup.java.
En src/ir se encuentra todo lo que respecta al arbol ast.
	ir/ast contiene todos las clases definidas para los nodos del arbol.
	ir/semcheck contiene los visitantes que chequean tipo e imprimen el arbol.
	ir/TablaDeSimbolos contiene las clases creadas para generar la tabla de simbolos.
en /test se encuentran los test del interprete.
en /testSintaxis se encuentran los test de la primer entrega.
en /testSemantica se encuentran los test de la segunda entrega.


Los test positivos pueden fallar porque:
	Siempre debe existir una clase Main (con M mayuscula).
	El metodo main de la clase main retorna void. (void main(){}).
	La segunda expresion del for debe ser booleana (for i = 4, i == 10).
