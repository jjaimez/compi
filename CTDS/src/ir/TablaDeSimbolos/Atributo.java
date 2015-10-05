/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Definicion de un atributo para generar tabla de simbolos
 */
package ir.TablaDeSimbolos;

import ir.ast.Type;

/**
 *
 * @author nico
 */
public class Atributo {
    private Object valor;
    private Type tipo;
    private String nombre;
    private Integer tamanio = 0;

    public Atributo(Object valor, Type tipo, String nombre,Integer tamanio) {
        this.valor = valor;
        this.tipo = tipo;
        this.nombre = nombre;
        if (tamanio != null)
            this.tamanio = tamanio;
    }

    public Atributo(Object valor, Type tipo, String nombre) {
        this.valor = valor;
        this.tipo = tipo;
        this.nombre = nombre;
        this.tamanio=0;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTamanio() {
        return tamanio;
    }
 
   
    
}
