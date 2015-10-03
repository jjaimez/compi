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

    public Clase() {
        this.atributos = new LinkedList<>();
        this.metodos = new LinkedList<>();
    }

    
    
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

    public void setAtributo(Atributo atr) {
        if (existeAtributo(atr)) {
            System.out.println("error, ya existe el atributo '" + atr.getNombre() + "'");
            System.exit(1);
        }
        this.atributos.add(atr);
    }

    public boolean existeAtributo(Atributo atr) {
        boolean existe = false;
        int i = 0;
        while (!existe && i < atributos.size()) {
            if (atributos.get(i).getNombre() == atr.getNombre()) {
                existe = true;
            }
            i++;
        }
        return existe;
    }

    public boolean existeMetodo(Metodo met) {
        boolean existe = false;
        int i = 0;
        while (!existe && i < metodos.size()) {
            if (metodos.get(i).getNombre() == met.getNombre()) {
                existe = true;
            }
            i++;
        }
        return existe;
    }
    
        public Metodo getMetodo(String nombre) {
        int i = 0;
        while (i < metodos.size()) {
            if (metodos.get(i).getNombre().equals(nombre)) {
                return metodos.get(i);
            }
            i++;
        }
        return null;
    }
    

    public void setMetodo(Metodo met) {
        if (existeMetodo(met)) {
            System.out.println("error, ya existe el metodo '" + met.getNombre() + "'");
            System.exit(1);
        }
        this.metodos.add(met);
    }
}
