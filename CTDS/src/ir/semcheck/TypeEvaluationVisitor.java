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
        Expression e = stmt.getExpression();
        if (e != null) {
            if (!e.accept(this).isUndefined()) {
                return Type.VOID;
            } else {
                addError(e, "Retorno invalido");
                return Type.UNDEFINED;
            }
        } else {
            return Type.VOID;
        }

    }

    @Override
    public Type visit(IfStmt stmt) {
        if (stmt == null) {
            System.out.println("stmt es null");
        }
        if (stmt.getIfBlock() == null) {
            System.out.println("block es null");
        }
        Type expr = stmt.getCondition().accept(this);
        Type ifBlock = stmt.getIfBlock().accept(this);
        if (stmt.getElseBlock() != null) {
            Type elseBlock = stmt.getElseBlock().accept(this);
            if (expr.isBool()) {
                if (!ifBlock.isUndefined() && !elseBlock.isUndefined()) {
                    return Type.VOID;
                }
                return Type.UNDEFINED;
            } else {
                addError(stmt.getCondition(), "La condicion no es logica");
            }
            return Type.UNDEFINED;
        } else {
            if (expr.isBool()) {
                if (!ifBlock.isUndefined()) {
                    return Type.VOID;
                }

                return Type.UNDEFINED;
            } else {
                addError(stmt.getCondition(), "La condicion no es logica");
            }
            return Type.UNDEFINED;
        }
    }

    @Override
    public Type visit(IntLiteral lit) {
        if (lit.getType().isInt()) {
                        lit.setType(Type.INT);
            return Type.INT;
        } else {
            addError(lit, "Tipo invalido, se esperaba un entero");
        }
        return Type.UNDEFINED;
    }

    @Override
    public Type visit(VarLocation loc) { //ACA NECESITAMOS LA TABLA DE SIMBOLOS
        return Type.INT;
    }

    @Override
    public Type visit(AssignStmt stmt) {
        Type left = stmt.getLocation().accept(this);
        AssignOpType op = stmt.getOperator();
        Type right = stmt.getExpression().accept(this);
        if (left.equals(right)) {
            if (!left.isArray()) {
                if (left.isBool()) {
                    if (!op.isAssign()) {
                        addError(stmt, "Operacion invalida");
                    } else {
                        return Type.VOID;
                    }
                }
                if (left.isInt() || left.isFloat()) {
                    return Type.VOID;
                }
            } else {
                addError(stmt, "Operacion invalida");
            }
        } else {
            addError(stmt, "Al destino le queres asignar algo que no es del mismo tipo");
        }
        return Type.UNDEFINED;
    }

    @Override
    public Type visit(BinOpExpr expr) {
        Type left = expr.getLeftOperand().accept(this);
        BinOpType op = expr.getOperator();
        Type right = expr.getRightOperand().accept(this);
        if (left.equals(right)) {
            if (left.isBool()) {
                if (op.isConditional() || op.isEquational()) {
                    expr.setType(Type.BOOL);
                    return Type.BOOL;
                } else {
                    addError(expr, "Los operandos son logicos sin embargo el operador no lo es");
                }
            } else if (left.isFloat() || left.isInt()) {
                if (op.isArithmetic()) {
                    return left;
                } else if (op.isEquational() || op.isRelational()) {
                    expr.setType(Type.BOOL);
                    return Type.BOOL;
                } else {
                    addError(expr, "Los operandos son aritmeticos sin embargo el operador es logico");
                }
            } else {
                addError(expr, "Operacion invalida");
            }
        }
        addError(expr, "Los operandos son de tipos distintos");
        return Type.UNDEFINED;
    }

    @Override
    public Type visit(BoolLiteral lit) {
        if (lit.getType().isBool()) {
            lit.setType(Type.BOOL);
            return Type.BOOL;
        } else {
            addError(lit, "Tipo invalido, se esperaba un valor logico");
        }
        return Type.UNDEFINED;
    }

    @Override
    public Type visit(FloatLiteral lit) {
        if (lit.getType().isFloat()) {
            lit.setType(Type.FLOAT);
            return Type.FLOAT;
        } else {
            addError(lit, "Tipo invalido, se esperaba un real");
        }
        return Type.UNDEFINED;
    }

    @Override
    public Type visit(Block bl) { // CREO QUE NO HACE FALTA FIJARSE EN LOS fd
        List<Statement> ls = bl.getStatements();
        if (ls != null) {
            for (Statement elem : ls) {
                if (elem.accept(this).isUndefined()) {
                    return Type.UNDEFINED;
                }
            }
        }
        return Type.VOID;
    }

    @Override
    public Type visit(UnaryOpExpr expr) {
        Type operand = expr.getOperand().accept(this);
        UnaryOpType operator = expr.getOperator();
        if (operator.isMinus() && (operand.isFloat() || operand.isInt())) {
            return operand;
        } else if (operator.isNot() && operand.isBool()) {
            expr.setType(Type.BOOL);
            return Type.BOOL;
        }
        addError(expr, "Error de tipos");
        return Type.UNDEFINED;
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
        Type block = stmt.getBlock().accept(this);
        Type expr = stmt.getExpr().accept(this);
        if (!block.isUndefined()) {
            if (expr.isBool()) {
                return Type.VOID;
            } else {
                addError(stmt.getExpr(), "La condicion no es de tipo logico");
                return Type.UNDEFINED;
            }
        } else {
            return Type.UNDEFINED;
        }
    }

    @Override
    public Type visit(MethodCall stmt) {
        List<Expression> ls = stmt.getExpressions();
        if (ls != null) {
            for (Expression elem : ls) {
                if (elem.accept(this).isUndefined()) {
                    addError(stmt, "hay parametros con tipos inapropiados");
                    return Type.UNDEFINED;
                }
            }
        }
        return stmt.getType();
    }

    @Override
    public Type visit(Body bl) {
        bl.getBlock().accept(this);
        return Type.VOID;
    }

    @Override
    public Type visit(ExternStmt stmt) {
        return Type.VOID;
    }

    @Override
    public Type visit(ForStmt stmt) {
        Type block = stmt.getStatement().accept(this);
        Type expr = stmt.getExpr().accept(this);
        Type exprfin = stmt.getExpr2().accept(this);
        if (expr.isInt()) {
            if (exprfin.isInt()) {
                if (!block.isUndefined()) {
                    return Type.VOID;
                } else {
                    return Type.UNDEFINED;
                }
            } else {
                addError(stmt.getExpr2(), "else expresion no es de tipo logico");
                return Type.UNDEFINED;
            }
        } else {
            addError(stmt, "Error de asignacion en la variable de control");
            return Type.UNDEFINED;
        }
    }

    @Override
    public Type visit(Parameter p) { // FIJARSE SI EL ID ES DEL MISMO TIPO?
        if (p.getType().isUndefined() || p.getType().isVoid()) {
            addError(p, "el parametro no tiene tipo");
            return Type.UNDEFINED;
        }
        return Type.VOID;
    }

    @Override
    public Type visit(Method m) {
        if (m.getType().isUndefined()) {
            addError(m, "la declaracion del metodo no tiene tipo");
            return Type.UNDEFINED;
        }
        if (m.getParameters() != null) {
            for (Parameter p : m.getParameters()) {
                m.accept(this);
            }
        }
        m.getBody().accept(this);
        return Type.VOID;

    }

    @Override
    public Type visit(FieldDeclaration fd) { // Tambien hay q revisar los de adentro!!
        if (fd.getType().isUndefined() || fd.getType().isVoid()) {
            addError(fd, "la declaracion no tiene tipo");
        }
        return Type.VOID;
    }

    @Override
    public Type visit(Declaration d) {
        if (d.getFieldDecl() != null) {
            for (FieldDeclaration f : d.getFieldDecl()) {
                f.accept(this);
            }
        }
        if (d.getMethodDecl() != null) {
            for (Method f : d.getMethodDecl()) {
                f.accept(this);
            }
        }
        return Type.VOID;
    }

    @Override
    public Type visit(ClassDeclaration cd) {
        cd.getDeclarations().accept(this);
        return Type.VOID;
    }

    @Override
    public Type visit(Program p) {
        for (ClassDeclaration c : p.getClassDeclarations()) {
            c.accept(this);
        }
        return Type.VOID;
    }

    @Override
    public Type visit(MethodCallStmt stmt) {
        return Type.VOID;
    }

    @Override
    public Type visit(LocationDeclaration ld) { //CON TABLA DE SIMOBLOS TMB
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
