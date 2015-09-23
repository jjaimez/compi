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
public class Program extends AST{
    private LinkedList<ClassDeclaration> classDeclarations;

    public Program(LinkedList<ClassDeclaration> classDeclarations) {
        this.classDeclarations = classDeclarations;
    }

    public LinkedList<ClassDeclaration> getClassDeclarations() {
        return classDeclarations;
    }

    public void setClassDeclarations(LinkedList<ClassDeclaration> classDeclarations) {
        this.classDeclarations = classDeclarations;
    }   
    
    
     @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
