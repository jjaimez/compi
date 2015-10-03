/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.TablaDeSimbolos;

import java.util.LinkedList;

/**
 *
 * @author nico
 */
public class Bloque {

    private LinkedList<Atributo> atributos;

    public Bloque() {
        this.atributos = new LinkedList<>();
    }

    public Bloque(LinkedList<Atributo> atributos) {
        this.atributos = atributos;
    }

    public LinkedList<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(LinkedList<Atributo> atributos) {
        this.atributos = atributos;
    }
    
    public Atributo getAtributo(String id){
        int i = 0;
        while (i < atributos.size()) {
            if (atributos.get(i).getNombre().equals(id)) {
                return atributos.get(i);
            }
            i++;
        }
        return null;
    }

    public void setAtributo(Atributo atr) {
        if (existeAtributo(atr)) {
            System.out.println("error, ya existe la variable '" + atr.getNombre() + "'");
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
}
