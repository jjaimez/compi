/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de declaraciones de variables.
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.List;

/**
 *
 * @author jacinto
 */
public class FieldDeclaration extends AST{
    private Type type;
    private List<LocationDeclaration> l;

    public FieldDeclaration(Type type, List<LocationDeclaration> l) {
        this.type = type;
        this.l = l;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<LocationDeclaration> getL() {
        return l;
    }

    public void setL(List<LocationDeclaration> l) {
        this.l = l;
    }
   
    
    
     @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
    
    
    
    
}
