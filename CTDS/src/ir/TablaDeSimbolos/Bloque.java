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
        private LinkedList<Atributo> parametros;

    public Bloque() {
    }

    public Bloque(LinkedList<Atributo> parametros) {
        this.parametros = parametros;
    }

    public LinkedList<Atributo> getParametros() {
        return parametros;
    }

    public void setParametros(LinkedList<Atributo> parametros) {
        this.parametros = parametros;
    }
        
        

}
