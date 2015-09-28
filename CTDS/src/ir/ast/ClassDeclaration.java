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
public class ClassDeclaration extends AST {

    private String id;
    private Declaration declarations;

    public ClassDeclaration(String id, Declaration declarations) {
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

    public Declaration getDeclarations() {
        return declarations;
    }

    public void setDeclarations(Declaration declarations) {
        this.declarations = declarations;
    }

    @Override
    public String toString() {

        if (declarations != null) {
            return "class " + id + " " + declarations.toString();
        } else {
            return "class " + id;
        }
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
