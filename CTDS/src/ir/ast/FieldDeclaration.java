/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de declaraciones de variables.
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.LinkedList;
import java.util.List;

public class FieldDeclaration extends AST {

    private Type type;
    private List<LocationDeclaration> l = new LinkedList<LocationDeclaration>();

    public FieldDeclaration(Type type, List<LocationDeclaration> l) {
        this.type = type;
        if (l!=null)
            this.l= l;
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
    public String toString() {
        String s = type.toString() + " ";
        if (l != null) {
            for (LocationDeclaration c : l) {
                s += c.toString();
            }
        }
        return s;
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
