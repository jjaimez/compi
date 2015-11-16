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
import ir.TablaDeSimbolos.Atributo;
import ir.TablaDeSimbolos.Bloque;
import ir.TablaDeSimbolos.Clase;
import ir.TablaDeSimbolos.Metodo;
import ir.TablaDeSimbolos.TablaDeSimbolos;

// type checker, concrete visitor 
public class TypeEvaluationVisitor implements ASTVisitor<Type> {

    private LinkedList<Error> errors;
    private TablaDeSimbolos tablaSimbolos;

    public TypeEvaluationVisitor() {
        this.errors = new LinkedList<Error>();
        tablaSimbolos = new TablaDeSimbolos();

    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(LinkedList<Error> errors) {
        this.errors = errors;
    }

    @Override
    public Type visit(ReturnStmt stmt) {
        if (stmt.getExpression() != null) {
            return stmt.getExpression().accept(this);
        }
        return Type.VOID;

    }

    @Override
    public Type visit(IfStmt stmt) {
        if (!stmt.getCondition().accept(this).isBool()) {
            System.err.println("Condicion del if no es booleana, linea: " + stmt.getCondition().getLineNumber() + " columna: " + stmt.getCondition().getColumnNumber());
            System.exit(1);
        } else {
            stmt.getIfBlock().accept(this);
            if (stmt.getElseBlock() != null) {
                stmt.getElseBlock().accept(this);
            }
        }
        return Type.VOID;

    }

    @Override
    public Type visit(IntLiteral lit) {
        return Type.INT;
    }

    @Override
    public Type visit(VarLocation loc) {
        Atributo var = tablaSimbolos.getAtributo(loc.getId());
        if (var == null) {
            System.err.println("variable '" + loc.getId() + "' no definida, linea: " + loc.getLineNumber() + " columna: " + loc.getColumnNumber());
            System.exit(1);
        }
        if (var.getTamanio() > 0) {
            if (loc.getExp() != null) {
                Type t = loc.getExp().accept(this);
                if (!t.isInt()) {
                    System.err.println("el tipo de expr debe ser int." + loc.getExp().getLineNumber() + " columna: " + loc.getExp().getColumnNumber());
                    System.exit(1);
                }
            } else {
                System.err.println("el arreglo se esta usando como una variable " + loc.getLineNumber() + " columna: " + loc.getColumnNumber());
                System.exit(1);
            }
        }
        return var.getTipo();
    }

    @Override
    public Type visit(AssignStmt stmt) {
        Atributo loc = tablaSimbolos.getAtributo(stmt.getLocation().getId());
        stmt.getLocation().accept(this);
        if (loc != null) {
            Type typeStmt = stmt.getExpression().accept(this);
            if (loc.getTipo() != typeStmt) {
                System.err.println("error de tipos, linea: " + stmt.getExpression().getLineNumber() + " columna: " + stmt.getExpression().getColumnNumber());
                System.exit(1);
            }
            //si es += o -= y la variable no es float o int se rompe
            if (!loc.getTipo().isFloat() && !loc.getTipo().isInt() && (stmt.getOperator().isDecrement() || stmt.getOperator().isIncrement())) {
                System.err.println("No se puede aplicar " + stmt.getOperator() + " a una variable de tipo " + loc.getTipo() + ". linea: " + stmt.getExpression().getLineNumber() + " columna: " + stmt.getExpression().getColumnNumber());
                System.exit(1);
            }
            //si la variable definida NO es un arreglo pero en la location si lo usa como arreglo se rompe
            if (loc.getTamanio() == 0 && stmt.getLocation().getExpr() != null) {
                System.err.println("La variable " + loc.getNombre() + " No es un arreglo. linea: " + stmt.getLocation().getLineNumber() + " columna: " + stmt.getLocation().getColumnNumber());
                System.exit(1);
            }
        } else {
            System.err.println("variable no definida, linea: " + stmt.getLocation().getLineNumber() + " columna: " + stmt.getLocation().getColumnNumber());
            System.exit(1);
        }
        return Type.VOID;

    }

    @Override
    public Type visit(BinOpExpr expr) {
        Type typeLeft = expr.getLeftOperand().accept(this);
        Type typeRight = expr.getRightOperand().accept(this);

        if (typeLeft != typeRight) {
            System.err.println("error de tipos, no se puede hacer " + typeLeft.toString() + expr.getOperator().toString() + typeRight.toString() + ", linea: " + expr.getLineNumber() + " columna: " + expr.getColumnNumber());
            System.exit(1);
        }
        BinOpType op = expr.getOperator();
        //CORROBORO QUE SI SON OPERACIONES RELACIONALES O LOGICAS LOS OPERANDOS SEAN LOGICOS Y QUE RETORNE ALGO LOGICO
        if (op.isConditional() || op.isEquational() || op.isRelational()) {
            expr.setType(Type.BOOL);
            return Type.BOOL;
        } else {// si es aritmetico retorno el tipo de algun operando
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
                if (tablaSimbolos.getAtributoSameBlock(ld.getId()) == null) {
                    if (ld.getSize() == null) {
                        atributo = new Atributo(null, fd.getType(), ld.getId());
                    } else {
                        atributo = new Atributo(null, fd.getType(), ld.getId(), ld.getSize().getValue());
                    }
                    tablaSimbolos.setVariableBloque(atributo);
                } else {
                    System.err.println("Error, ya existe una variable en el bloque corriente'" + ld.getId() + "'" + ", linea: " + ld.getLineNumber() + " columna: " + ld.getColumnNumber());
                    System.exit(1);
                }
            }
        }
        Type lastType = Type.VOID; //utizo esto para retornar el tipo, en caso de no ser de retorno es void
        for (Statement s : bl.getStatements()) {
            if (lastType != Type.VOID) {
                System.err.println("No se puede tener sentencias despues de un return");
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
        if (expr.getOperator() == UnaryOpType.MINUS) {
            //el tipo de la expresión debe ser si o si float o int
            if (!typeExpr.isFloat() && !typeExpr.isInt()) {
                System.err.println("no se puede aplicar '-' a una expresión de tipo " + typeExpr.toString() + ", linea: " + expr.getLineNumber() + " columna: " + expr.getColumnNumber());
                System.exit(1);
            } else {
                return typeExpr;
            }
        } else {
            //el tipo debe ser booleano
            if (!typeExpr.isBool()) {
                System.err.println("no se puede aplicar '!' a una expresión de tipo " + typeExpr.toString() + ", linea: " + expr.getLineNumber() + " columna: " + expr.getColumnNumber());
                System.exit(1);
            } else {
                return Type.BOOL;
            }
        }
        return null;
    }

    @Override
    public Type visit(BreakStmt stmt) {
        return Type.VOID;

    }

    @Override
    public Type visit(ContinueStmt stmt) {
        return Type.VOID;

    }

    @Override
    public Type visit(WhileStmt stmt) {
        Type t = stmt.getExpr().accept(this);
        if (!t.isBool()) {
            System.err.println("El tipo de la expresion debe ser bool. linea: " + stmt.getExpr().getLineNumber() + " columna: " + stmt.getExpr().getColumnNumber());
            System.exit(1);
        } else {
            stmt.getBlock().accept(this);
        }
        return Type.VOID;

    }

    @Override
    public Type visit(MethodCall stmt) {
        Metodo met = tablaSimbolos.getMetodo(tablaSimbolos.getUltimaClase(), stmt.getId());
        if (met.getParametros().size() != stmt.getExpressions().size()) {
            System.err.println("Error en la cantidad de parametros en la llamada del metodo " + stmt.getId() + ", linea: " + stmt.getLineNumber());
            System.exit(1);
        }

        for (int i = 0; i < stmt.getExpressions().size(); i++) {
            if (stmt.getExpressions().get(i).accept(this) != met.getParametros().get(i).getType()) {
                System.err.println("Error de tipo de parametros en la llamada del metodo " + stmt.getId() + ", linea: " + stmt.getLineNumber());
                System.exit(1);
            }
        }

            return met.getTipoReturn();



    }

    @Override
    public Type visit(Body bl) {//
        return bl.getBlock().accept(this);
    }

    @Override
    public Type visit(ExternStmt stmt) {
        return null;

    }

    @Override
    public Type visit(ForStmt stmt) {
        Type typeExpInit = stmt.getExpr().accept(this);
        Type t = stmt.getExpr2().accept(this);
        //las expresion de inicio debe ser entero unicamente
        if (!typeExpInit.isInt()) {
            System.err.println("El tipo de la expresion de inicio debe ser int linea: " + stmt.getExpr().getLineNumber() + " columna: " + stmt.getExpr().getColumnNumber());
            System.exit(1);
        }
        if (!t.isBool()) {
            System.err.println("El tipo de la expresion debe ser bool. linea: " + stmt.getExpr2().getLineNumber() + " columna: " + stmt.getExpr2().getColumnNumber());
            System.exit(1);
        } else {
            stmt.getStatement().accept(this);
        }
        return Type.VOID;

    }

    @Override
    public Type visit(Parameter p) {
        Atributo atributo;
        if (tablaSimbolos.getVariableBloque(p.getId()) == null) {
            atributo = new Atributo(null, p.getType(), p.getId());
            tablaSimbolos.setVariableBloque(atributo);
        } else {
            System.err.println("Error, ya existe una parametro en el metodo '" + p.getId() + "'");
            System.exit(1);
        }
        return null;
    }

    @Override
    public Type visit(Method m) {
        Bloque bloque = new Bloque();
        tablaSimbolos.pushBloque(bloque);
        for (Parameter p : m.getParameters()) {
            p.accept(this);
        }
        Type ret = m.getBody().accept(this);
        if (ret != null)// si es null es porque es un extern, de esta forma ignoro el analisis de retorno
        {
            if (m.getType() != ret) {
                System.err.println("Error de tipo, el tipo de retorno del metodo es " + m.getType() + " y el tipo retornado es " + ret);
                System.exit(1);
            }
        }
        tablaSimbolos.popBloque();
        return null;
    }

    @Override
    public Type visit(FieldDeclaration fd) {
        return Type.VOID;

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
                tablaSimbolos.setVariableBloque(atributo);
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
        tablaSimbolos.pushBloque(new Bloque());
        if (cd.getDeclarations() != null) {
            cd.getDeclarations().accept(this);
        }
        tablaSimbolos.popBloque();
        return null;
    }

    @Override
    public Type visit(Program p) {
        if (p.getClassDeclarations() == null) {
            System.err.println("Error, no hay clases definidas en el programa");
            System.exit(1);
        } else {
            if (p.getClassDeclarations().size() == 0) {

            } else {
                boolean containsMetMain = false;
                for (ClassDeclaration cd : p.getClassDeclarations()) {
                    cd.accept(this);
                }
            }
        }
        //analizo que exista una clase "Main" y que tenga un metodo main
        if (tablaSimbolos.existeClase("Main")) {
            Metodo met = tablaSimbolos.getMetodo("Main", "main");
            if (met == null) {
                System.err.println("error. La clase debe contener un metodo main");
                System.exit(1);
            }
            if (!met.getParametros().isEmpty()) {
                System.err.println("error. El metodo main no debe contener parametros");
                System.exit(1);
            } else {
                if (!met.getTipoReturn().isVoid()) {
                    System.err.println("error. el metodo main debe retornar void");
                    System.exit(1);
                }
            }
        } else {
            System.err.println("El programa debe contener una clase main");
            System.exit(1);
        }
        return null;
    }

    @Override
    public Type visit(MethodCallStmt stmt) {
        return Type.VOID;
    }

    @Override
    public Type visit(LocationDeclaration ld) {
        return Type.VOID;
    }

    public TablaDeSimbolos getTablaSimbolos() {
        return tablaSimbolos;
    }

}
