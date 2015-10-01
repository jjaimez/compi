package ir.semcheck;

/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Visitor que busca errorres semanticos
 */
import java.util.List;

import ir.ASTVisitor;
import ir.ast.*;
import java.util.LinkedList;
import error.Error;

// type checker, concrete visitor 
public class TypeEvaluationVisitor implements ASTVisitor<Type> {

    private LinkedList<Error> errors;

    public TypeEvaluationVisitor() {
        this.errors = new LinkedList<Error>();
    }

    private void addError(AST a, String desc) {
        errors.add(new Error(a.getLineNumber(), a.getColumnNumber(), desc));
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void showErrors() {
        for (Error e : errors) {
            e.show();
        }
    }

    public void setErrors(LinkedList<Error> errors) {
        this.errors = errors;
    }

    @Override
    public Type visit(ReturnStmt stmt) {
        return null;

    }

    @Override
    public Type visit(IfStmt stmt) {
        return null;

    }

    @Override
    public Type visit(IntLiteral lit) {
        return null;
    }

    @Override
    public Type visit(VarLocation loc) { //ACA NECESITAMOS LA TABLA DE SIMBOLOS
        return null;
     
        
    }

    @Override
    public Type visit(AssignStmt stmt) {
        return null;
      
    }

    @Override
    public Type visit(BinOpExpr expr) {
        return null;

    }

    @Override
    public Type visit(BoolLiteral lit) {
        return null;

    }

    @Override
    public Type visit(FloatLiteral lit) {
        return null;

    }

    @Override
    public Type visit(Block bl) {
        return null;
     
    }

    @Override
    public Type visit(UnaryOpExpr expr) {
        return null;

    }

    @Override
    public Type visit(BreakStmt stmt) {
        return null;
        
    }

    @Override
    public Type visit(ContinueStmt stmt) {
        return null;
     
    }

    @Override
    public Type visit(WhileStmt stmt) {
        return null;

        
    }

    @Override
    public Type visit(MethodCall stmt) {
        return null;

    }

    @Override
    public Type visit(Body bl) {
        return null;
    }

    @Override
    public Type visit(ExternStmt stmt) {
        return null;
       
    }

    @Override
    public Type visit(ForStmt stmt) {
        return null;

    }

    @Override
    public Type visit(Parameter p) { 
        return null;

    }

    @Override
    public Type visit(Method m) {
        return null;

    }

    @Override
    public Type visit(FieldDeclaration fd) { 
        return null;

    }

    @Override
    public Type visit(Declaration d) {
        return null;
      
    }

    @Override
    public Type visit(ClassDeclaration cd) {
        return null;
    }

    @Override
    public Type visit(Program p) {
        return null;

    }

    @Override
    public Type visit(MethodCallStmt stmt) {
        return null;
    }

    @Override
    public Type visit(LocationDeclaration ld) { //CON TABLA DE SIMOBLOS TMB
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
