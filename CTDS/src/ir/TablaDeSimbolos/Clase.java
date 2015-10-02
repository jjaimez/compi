/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Definicion de una clase para generar tabla de simbolos
 */
package ir.TablaDeSimbolos;

import java.util.LinkedList;

/**
 *
 * @author nico
 */
public class Clase {
    private LinkedList<Atributo> atributos;
    private LinkedList<Metodo> metodos;

    public Clase(LinkedList<Atributo> atributos, LinkedList<Metodo> metodos) {
        this.atributos = atributos;
        this.metodos = metodos;
    }

    public LinkedList<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(LinkedList<Atributo> atributos) {
        this.atributos = atributos;
    }

    public LinkedList<Metodo> getMetodos() {
        return metodos;
    }

    public void setMetodos(LinkedList<Metodo> metodos) {
        this.metodos = metodos;
    }

    
}
