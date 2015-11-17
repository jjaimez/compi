/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Definicion de un metodo para generar tabla de simbolos
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
    private List<Parameter> parametros = new LinkedList<>();
   
    private boolean isExtern;

    public Metodo(String nombre, Type tipoReturn, List<Parameter> parametros) {
        this.nombre = nombre;
        this.tipoReturn = tipoReturn;
        this.parametros = parametros;
         isExtern = false;
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
    public boolean isExtern() {
        return isExtern;
    }

    public void setIsExtern(boolean isExtern) {
        this.isExtern = isExtern;
    }
    
   
    
    
}
