/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de declaracion de clase.
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.LinkedList;

/**
 *
 * @author jacinto
 */
public class ClassDeclaration extends AST{
    private String id;
    private LinkedList<Declaration> declarations;

    public ClassDeclaration(String id, LinkedList<Declaration> declarations) {
        this.id = id;
        this.declarations = declarations;
    }

    public ClassDeclaration(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(LinkedList<Declaration> declarations) {
        this.declarations = declarations;
    }
       
    
      @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
    
    
}
