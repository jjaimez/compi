/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de un programa
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.LinkedList;

/**
 *
 * @author jacinto
 */
public class Program extends AST {

    private LinkedList<ClassDeclaration> classDeclarations = new LinkedList<ClassDeclaration>();

    public Program(LinkedList<ClassDeclaration> classDeclarations) {
        if (classDeclarations!=null)
            this.classDeclarations = classDeclarations;
    }

    public LinkedList<ClassDeclaration> getClassDeclarations() {
        return classDeclarations;
    }

    public void setClassDeclarations(LinkedList<ClassDeclaration> classDeclarations) {
        this.classDeclarations = classDeclarations;
    }

    @Override
    public String toString() {
        String s = "";
        if (classDeclarations != null) {
            for (ClassDeclaration c : classDeclarations) {
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
