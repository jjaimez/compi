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
import ir.TablaDeSimbolos.Bloque;
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
        return stmt.getExpression().getType();

    }

    @Override
    public Type visit(IfStmt stmt) {
        if(!stmt.getCondition().accept(this).isBool()){
            System.err.println("Condicion del if no es booleana");
            System.exit(1);
        }else{
            stmt.getIfBlock().accept(this);
            if(stmt.getElseBlock()!=null)
                stmt.getElseBlock().accept(this);
        }
        return Type.VOID;

    }

    @Override
    public Type visit(IntLiteral lit) {
        return Type.INT;
    }

    @Override
    public Type visit(VarLocation loc) {
        Atributo var= tablaSimbolos.getAtributo(loc.getId());
        if(var==null){
            System.err.println("variable '"+loc.getId()+"' no definida");
            System.exit(1);
        }else
            return var.getTipo();
        return Type.VOID;

    }

    @Override
    public Type visit(AssignStmt stmt) {
        Atributo loc = tablaSimbolos.getAtributo(stmt.getLocation().getId());
        if (loc != null) {
            Type typeStmt = stmt.getExpression().accept(this);
            if (loc.getTipo() != typeStmt) {
                System.err.println("error de tipos");
                System.exit(1);
            }
        } else {
            System.err.println("variable no definida");
            System.exit(1);
        }
        return Type.VOID;

    }

    @Override
    public Type visit(BinOpExpr expr) {
        Type typeLeft = expr.getLeftOperand().accept(this);
        Type typeRight= expr.getRightOperand().accept(this);

        if(typeLeft != typeRight){
            System.err.println("error de tipos, no se puede hacer "+typeLeft.toString()+expr.getOperator().toString()+typeRight.toString());
            System.exit(1);
        }
        BinOpType op= expr.getOperator();
        //CORROBORO QUE SI SON OPERACIONES RELACIONALES O LOGICAS LOS OPERANDOS SEAN LOGICOS Y QUE RETORNE ALGO LOGICO
        if(op.isConditional() || op.isEquational() || op.isRelational() ){
            expr.setType(Type.BOOL);
            return Type.BOOL;
        }
        else{// si es aritmetico retorno el tipo de algun operando
            expr.setType(typeLeft);
            return typeLeft;
        }

    }

    @Override
    public Type visit(BoolLiteral lit) {
        return Type.BOOL;

    }

    @Override
    public Type visit(FloatLiteral lit) {
        return Type.FLOAT;

    }

    @Override
    public Type visit(Block bl) {
        tablaSimbolos.pushBloque(new Bloque());
        for (FieldDeclaration fd : bl.getFd()) {
            for (LocationDeclaration ld : fd.getL()) {
                //creo un atributo
                Atributo atributo;
                if (tablaSimbolos.getVariableBloque(ld.getId()) == null) {
                    if (ld.getSize() == null) {
                        atributo = new Atributo(null, fd.getType(), ld.getId());
                    } else {
                        atributo = new Atributo(null, fd.getType(), ld.getId(), ld.getSize().getValue());
                    }
                    tablaSimbolos.setVariableBloque(atributo);
                } else {
                    System.out.println("Error, ya existe una variable en el bloque corriente'"+ld.getId()+"'");
                    System.exit(1);
                }
            }
        }
        Type lastType = Type.VOID; //utizo esto para retornar el tipo, en caso de no ser de retorno es void
        for (Statement s : bl.getStatements()) {
            if(lastType!=Type.VOID){
                    System.out.println("No se puede tener sentencias despues de un return");
                    System.exit(1);
            }
            lastType = s.accept(this);
        }
        tablaSimbolos.popBloque();
        return lastType;

    }

    @Override
    public Type visit(UnaryOpExpr expr) {
        Type typeExpr = expr.getOperand().accept(this);
        if(expr.getOperator()== UnaryOpType.MINUS){
            //el tipo de la expresión debe ser si o si float o int
            if(!typeExpr.isFloat() && !typeExpr.isInt() ){
                    System.out.println("no se puede aplicar '-' a una expresión de tipo "+typeExpr.toString());
                    System.exit(1);
            }
            else
                return typeExpr;
        }else{
            //el tipo debe ser booleano
            if(!typeExpr.isBool()){
                    System.out.println("no se puede aplicar '!' a una expresión de tipo "+typeExpr.toString());
                    System.exit(1);
            }else
                return Type.BOOL;
        }
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
        Metodo met= tablaSimbolos.getMetodo(tablaSimbolos.getUltimaClase(), stmt.getId());
        if(met.getParametros().size()!= stmt.getExpressions().size()){
            System.err.println("Error en la cantidad de parametros en la llamada del metodo "+stmt.getId());
            System.exit(1);
        }
        for(int i=0;i<stmt.getExpressions().size();i++){
            if(stmt.getExpressions().get(i).accept(this)!=met.getParametros().get(i).getType()){
            System.err.println("Error de tipo de parametros en la llamada del metodo "+stmt.getId());
            System.exit(1);
            }
        }

        return met.getTipoReturn();

    }

    @Override
    public Type visit(Body bl) {
        return bl.getBlock().accept(this);
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
        Bloque bloque = new Bloque();
        tablaSimbolos.pushBloque(bloque);
        Type ret= m.getBody().accept(this);
        if(m.getType()!= ret){
            System.err.println("Error de tipo, el tipo de retorno del metodo es "+m.getType() +" y el tipo retornado es "+ret);
            System.exit(1);
        }
        tablaSimbolos.popBloque();
        return null;

    }

    @Override
    public Type visit(FieldDeclaration fd) {
        return null;

    }

    @Override
    public Type visit(Declaration d) {
        for (FieldDeclaration fd : d.getFieldDecl()) {
            for (LocationDeclaration ld : fd.getL()) {
                //creo un atributo
                Atributo atributo;
                if (ld.getSize() == null) {
                    atributo = new Atributo(null, fd.getType(), ld.getId());
                } else {
                    atributo = new Atributo(null, fd.getType(), ld.getId(), ld.getSize().getValue());
                }
                tablaSimbolos.insertAtrClase(tablaSimbolos.getUltimaClase(), atributo);
            }
        }
        //recorro la definicion de metodos
        for (Method m : d.getMethodDecl()) {
            m.accept(this);
            Metodo metodo = new Metodo(m.getId(), m.getType(), m.getParameters());
            tablaSimbolos.insertMetClase(tablaSimbolos.getUltimaClase(), metodo);
        }
        return null;
    }

    @Override
    public Type visit(ClassDeclaration cd) {
        Clase c = new Clase();
        tablaSimbolos.pushClase(cd.getId(), c);
        if (cd.getDeclarations() != null) {
            cd.getDeclarations().accept(this);
        }
        return null;
    }

    @Override
    public Type visit(Program p) {
        if (p.getClassDeclarations() == null || p.getClassDeclarations().size() == 0) {
            System.out.println("Error, no hay clases definidas en el programa");
            System.exit(1);
        } else {
            for (ClassDeclaration cd : p.getClassDeclarations()) {
                cd.accept(this);
            }

        }
        return null;
    }

    @Override
    public Type visit(MethodCallStmt stmt) {
        return null;
    }

    @Override
    public Type visit(LocationDeclaration ld) { 
        return null;
    }

    public TablaDeSimbolos getTablaSimbolos() {
        return tablaSimbolos;
    }

}
