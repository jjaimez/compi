/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de declaracion de una variable.
 */
package ir.ast;

import ir.ASTVisitor;

public class LocationDeclaration extends AST {

    private String id;
    private IntLiteral size;

    public LocationDeclaration(String id, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        this.id = id;
    }

    public LocationDeclaration(String id, IntLiteral size, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        this.id = id;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IntLiteral getSize() {
        return size;
    }

    public void setSize(IntLiteral size) {
        this.size = size;
    }

    @Override
    public String toString() {
        if (size != null) {
            return id + "[" + size.toString() + "]";
        } else {
            return id;
        }
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
