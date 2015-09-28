/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de declaraciones.
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.LinkedList;

/**
 *
 * @author jacinto
 */
public class Declaration extends AST {

    private LinkedList<FieldDeclaration> fieldDecl;
    private LinkedList<Method> methodDecl;

    public Declaration(LinkedList<FieldDeclaration> fieldDecl, LinkedList<Method> methodDecl) {
        this.fieldDecl = fieldDecl;
        this.methodDecl = methodDecl;
    }

    public LinkedList<FieldDeclaration> getFieldDecl() {
        return fieldDecl;
    }

    public void setFieldDecl(LinkedList<FieldDeclaration> fieldDecl) {
        this.fieldDecl = fieldDecl;
    }

    public LinkedList<Method> getMethodDecl() {
        return methodDecl;
    }

    public void setMethodDecl(LinkedList<Method> methodDecl) {
        this.methodDecl = methodDecl;
    }
    
    

    @Override
    public String toString() {
        String s = "";
        if (fieldDecl != null) {
            for (FieldDeclaration c : fieldDecl) {
                s += c.toString() + '\n';
            }
        }
        if (methodDecl != null) {
            for (Method c : methodDecl) {
                s += c.toString() + '\n';
            }
        }
        return s;

    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
