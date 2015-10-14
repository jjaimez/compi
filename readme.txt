Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
Proyecto: CompiladorCTDS


1) Abrir el proyecto CTDS con neatbeans
2) Correr main.java para ejecutar los test.


En src/parser se encuentra los archivos jflex y cup, para compilar el cup ejecutar CompilarCup.java.
En src/ir se encuentra todo lo que respecta al arbol ast.
	ir/ast contiene todos las clases definidas para los nodos del arbol.
	ir/semcheck contiene los visitantes que chequean tipo e imprimen el arbol.
	ir/TablaDeSimbolos contiene las clases creadas para generar la tabla de simbolos.
en test/ se encuentran los test.
en testSintaxis se encuentran los test de la primer entrega.
en testSemantica se encuentran los test de la segunda entrega.


Los test positivos pueden fallar porque:
	Siempre debe existir una clase Main (con M mayuscula).
	El metodo main de la clase main retorna void. (void main(){}).
	La segunda exptresion del for debe ser booleana (for i = 4, i == 10).
