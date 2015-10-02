/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.TablaDeSimbolos;

import ir.ast.Parameter;
import ir.ast.Type;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author nico
 */
public class Metodo {
    private String nombre;
    private Type tipoReturn;
    private List<Parameter> parametros;

    public Metodo(String nombre, Type tipoReturn, List<Parameter> parametros) {
        this.nombre = nombre;
        this.tipoReturn = tipoReturn;
        this.parametros = parametros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Type getTipoReturn() {
        return tipoReturn;
    }

    public void setTipoReturn(Type tipoReturn) {
        this.tipoReturn = tipoReturn;
    }

    public List<Parameter> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parameter> parametros) {
        this.parametros = parametros;
    }
    
    
    
}
