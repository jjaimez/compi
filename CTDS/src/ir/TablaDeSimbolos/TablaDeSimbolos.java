package ir.TablaDeSimbolos;

/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Pila para guardar los ambientes de cada bloque del programa.
 */
import ir.ast.AST;
import java.util.HashMap;
import java.util.LinkedList;

public class TablaDeSimbolos {

    private HashMap<String, Clase> clases;
    private LinkedList<Bloque> pilaBloque;

    public TablaDeSimbolos() {
        clases = new HashMap<>();
        pilaBloque = new LinkedList();
    }

    public void pushClase(String nombre, Clase clase) {
        clases.put(nombre, clase);
    }

    public boolean existeClase(String nombre){
        return clases.containsKey(nombre);
    }

    public HashMap<String, Clase> getClases() {
        return clases;
    }


    public void pushBloque(Bloque ambiente) {
        pilaBloque.addFirst(ambiente);
    }

    public Bloque topBloque() {
        return pilaBloque.getFirst();
    }

    public void popBloque() {
        pilaBloque.removeFirst();
    }

    public Bloque getFirstBloque() {
        return pilaBloque.getLast();
    }

    public boolean isEmptyBloque() {
        return pilaBloque.size() == 0;
    }

}
