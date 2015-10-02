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
import ir.TablaDeSimbolos.Atributo;
import ir.TablaDeSimbolos.Clase;
import ir.TablaDeSimbolos.Metodo;
import ir.TablaDeSimbolos.TablaDeSimbolos;
import java.util.HashMap;

// type checker, concrete visitor 
public class TypeEvaluationVisitor implements ASTVisitor<Type> {

    private LinkedList<Error> errors;
    private TablaDeSimbolos tablaSimbolos;

    public TypeEvaluationVisitor() {
        this.errors = new LinkedList<Error>();
        tablaSimbolos = new TablaDeSimbolos();

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
        if (p.getClassDeclarations() == null || p.getClassDeclarations().size() == 0) {
            System.out.println("Error, no hay clases definidas en el programa");
            System.exit(1);
        } else {
            //recorro cada clase del programa y lo apilo en la tabla de simbolos
            for (ClassDeclaration cd : p.getClassDeclarations()) {
                if(tablaSimbolos.existeClase(cd.getId())){
                    System.out.println("Ya existe una clase definida con el nombre '"+cd.getId()+"'");
                    System.exit(1);
                }
                LinkedList<Atributo> atributos = new LinkedList<>();
                LinkedList<Metodo> metodos = new LinkedList<>();
                if (cd.getDeclarations() != null) {
                    for (FieldDeclaration fd : cd.getDeclarations().getFieldDecl()) {
                        for (LocationDeclaration ld : fd.getL()) {
                            //creo un atributo
                            Atributo atributo;
                            if (ld.getSize() == null) {
                                atributo = new Atributo(null, fd.getType(), ld.getId());
                            } else {
                                atributo = new Atributo(null, fd.getType(), ld.getId(), ld.getSize().getValue());
                            }
                            atributos.add(atributo);
                        }
                    }
                    //recorro la definicion de metodos
                    for (Method m : cd.getDeclarations().getMethodDecl()) {
                        Metodo metodo = new Metodo(m.getId(), m.getType(), m.getParameters());
                        metodos.add(metodo);

                    }
                }
                Clase clase = new Clase(atributos, metodos);
                tablaSimbolos.pushClase(cd.getId(), clase);
            }
        }
        return null;
    }

    @Override
    public Type visit(MethodCallStmt stmt) {
        return null;
    }

    @Override
    public Type visit(LocationDeclaration ld) { //CON TABLA DE SIMOBLOS TMB
        return null;
    }

    public TablaDeSimbolos getTablaSimbolos() {
        return tablaSimbolos;
    }

}
