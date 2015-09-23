/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de declaracion de una variable.
 */
package ir.ast;

import ir.ASTVisitor;

/**
 *
 * @author jacinto
 */
public class LocationDeclaration extends AST{
    private String id;
    private int size;

    public LocationDeclaration(String id) {
        this.id = id;
    }

    public LocationDeclaration(String id, int size) {
        this.id = id;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    
     @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
    
    
    
    
}
