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

    private LinkedList<HashMap<String, AST>> pila;
    private int cantidad;

    public TablaDeSimbolos() {
        pila = new LinkedList();
        cantidad = 0;
    }

    public void push(HashMap<String, AST> ambiente) {
        pila.addFirst(ambiente);
        cantidad++;
    }

    public HashMap<String, AST> top() {
        return pila.getFirst();
    }

    public void pop() {
        pila.removeFirst();
        cantidad--;
    }

    public HashMap<String, AST> getFirst() {
        return pila.getLast(); 
    }

    public boolean isEmpty() {
        return cantidad == 0;
    }

    public AST search(String id) {
        for (int i = 0; i < pila.size(); i++) {
            HashMap<String, AST> a = pila.get(i);
            AST d = a.get(id);
            if (d != null) {
                return d;
            }
        }
        return null;

    }

    public LinkedList<HashMap<String, AST>> getPila() {
        return pila;
    }

    public void setPila(LinkedList<HashMap<String, AST>> pila) {
        this.pila = pila;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public TablaDeSimbolos clone() {
        TablaDeSimbolos t = new TablaDeSimbolos();
        t.cantidad = cantidad;
        LinkedList<HashMap<String, AST>> l = new LinkedList();
        for (HashMap<String, AST> a : pila) {
            HashMap<String, AST> am = (HashMap<String, AST>) a.clone();
            l.add(am);
        }
        t.setPila(l);
        return t;
    }
}
