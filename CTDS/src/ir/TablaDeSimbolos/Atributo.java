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

    private Object valor[] = new Object[100];
    private Type tipo;
    private String nombre;
    private int tamanio = 0;
    private int offset = 0;

    public Atributo(Object valor, Type tipo, String nombre, Integer tamanio) {
        this.tipo = tipo;
        this.nombre = nombre;
        if (tamanio != null) 
            this.tamanio = tamanio;
        this.valor[0] = valor;
    }

    public Atributo(Object valor, Type tipo, String nombre) {     
        this.valor[0] = valor;
        this.tipo = tipo;
        this.nombre = nombre;
        this.tamanio = 0;
    }

    public Object getValor() {
        return valor[0];
    }
    
    public Object getValorInPos(int pos) {
        if (pos >= 0){
            return valor[pos];
        }
        return null;
    }

    public void setValorInPos(Object valor, int pos) {
        if (pos >= 0) {
            this.valor[pos] = valor;
        }
    }

    public void setValor(Object valor) {
        this.valor[0] = valor;
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

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    

}
