
/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Visitor que implementa el interprete
 */
package ir.semcheck;

import ir.ASTVisitor;
import ir.TablaDeSimbolos.Atributo;
import ir.TablaDeSimbolos.Bloque;
import ir.TablaDeSimbolos.Clase;
import ir.TablaDeSimbolos.Metodo;
import ir.TablaDeSimbolos.TablaDeSimbolos;
import ir.ast.AssignStmt;
import ir.ast.BinOpExpr;
import ir.ast.Block;
import ir.ast.Body;
import ir.ast.BoolLiteral;
import ir.ast.BreakStmt;
import ir.ast.ClassDeclaration;
import ir.ast.ContinueStmt;
import ir.ast.Declaration;
import ir.ast.Expression;
import ir.ast.ExternStmt;
import ir.ast.FieldDeclaration;
import ir.ast.FloatLiteral;
import ir.ast.ForStmt;
import ir.ast.IfStmt;
import ir.ast.IntLiteral;
import ir.ast.LocationDeclaration;
import ir.ast.Method;
import ir.ast.MethodCall;
import ir.ast.MethodCallStmt;
import ir.ast.Parameter;
import ir.ast.Program;
import ir.ast.ReturnStmt;
import ir.ast.Statement;
import ir.ast.UnaryOpExpr;
import ir.ast.VarLocation;
import ir.ast.WhileStmt;
import java.util.LinkedList;

public class InterpreterVisitor implements ASTVisitor<Object> {

    private TablaDeSimbolos tablaSimbolos = new TablaDeSimbolos();
    LinkedList<Method> metodos;

    @Override
    public Object visit(Parameter p) {
        return null;
    }

    @Override
    public Object visit(Method m) {
        Bloque bloque = new Bloque();
        tablaSimbolos.pushBloque(bloque);
        if (m.getParameters() != null) {
            for (Parameter p : m.getParameters()) {
                if (tablaSimbolos.getAtributo(p.getId()) != null) {
                    Atributo atributo = new Atributo(null, p.getType(), p.getId());
                    tablaSimbolos.setVariableBloque(atributo);
                }
            }
        }
        Object o = null;
        if (m.getBody() != null) {
            o = m.getBody().accept(this);
        }
        tablaSimbolos.popBloque();
        if (!m.getType().isVoid()) {
            return o;
        } else {
            return null;
        }
    }

    @Override
    public Object visit(LocationDeclaration ld) {
        return null;
    }

    @Override
    public Object visit(FieldDeclaration fd) {
        return null;
    }

    @Override
    public Object visit(Declaration d) {
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
        metodos = d.getMethodDecl();
        //recorro la definicion de metodos
        for (Method m : metodos) {
            Metodo metodo = new Metodo(m.getId(), m.getType(), m.getParameters());
            tablaSimbolos.insertMetClase(tablaSimbolos.getUltimaClase(), metodo);
        }
        boolean findMain = false;
        int i = 0;
        while (!findMain && i < metodos.size()) {
            Method m = metodos.get(i);
            if (m.getId().equals("main")) {
                findMain = true;
                m.accept(this);
                //esto borrar despues
                for (Atributo a : tablaSimbolos.getBloque().getAtributos()) {
                    if (a.getNombre() != null && a.getValor() != null) {
                        System.out.println("Atributo" + a.getNombre() + " Valor: " + a.getValor().toString());
                    }
                }
            }
            i++;
        }
        return null;
    }

    @Override
    public Object visit(ClassDeclaration cd) {
        tablaSimbolos.pushBloque(new Bloque());
        if (cd.getDeclarations() != null) {
            cd.getDeclarations().accept(this);
        }
        tablaSimbolos.popBloque();
        return null;
    }

    @Override
    public Object visit(Program p) {
        boolean findMain = false;
        int i = 0;
        //ignoro las demas'clases por ahora porque no se pueden crear objetos
        while (!findMain && i < p.getClassDeclarations().size()) {
            ClassDeclaration c = p.getClassDeclarations().get(i);
            if (c.getId().equals("Main")) {
                Clase cl = new Clase();
                tablaSimbolos.pushClase("Main", cl);
                c.accept(this);
                findMain = true;
            }
            i++;
        }
        return null;
    }

    @Override
    public Object visit(AssignStmt stmt) {
        Atributo loc = tablaSimbolos.getAtributo(stmt.getLocation().getId());
        switch (stmt.getOperator()) {
            case INCREMENT:
                if (stmt.getExpression().accept(this).getClass().equals(Integer.class)) {
                    loc.setValor((int) loc.getValor() + (int) stmt.getExpression().accept(this));
                } else {
                    loc.setValor((float) loc.getValor() + (float) stmt.getExpression().accept(this));
                }
                break;
            case DECREMENT:
                if (stmt.getExpression().accept(this).getClass().equals(Integer.class)) {
                    loc.setValor((int) loc.getValor() - (int) stmt.getExpression().accept(this));
                } else {
                    loc.setValor((float) loc.getValor() - (float) stmt.getExpression().accept(this));
                }
                break;
            case ASSIGN:
                loc.setValor(stmt.getExpression().accept(this));
                break;
        }
        return null;
    }

    @Override
    public Object visit(ReturnStmt stmt) {
        if (stmt.getExpression() != null) {
            return stmt.getExpression().accept(this);
        } else {
            return null;
        }
    }

    @Override
    public Object visit(IfStmt stmt) {
        if ((boolean) stmt.getCondition().accept(this)) {
            if (stmt.getIfBlock() != null) {
                return stmt.getIfBlock().accept(this);
            }
        } else {
            if (stmt.getElseBlock() != null) {
                return stmt.getElseBlock().accept(this);
            }
        }
        return null;
    }

    @Override
    public Object visit(BreakStmt stmt) {
        return "break";
    }

    @Override
    public Object visit(ContinueStmt stmt) {
        return null;
    }

    @Override
    public Object visit(WhileStmt stmt) {
        LinkedList<Object> list0 = new LinkedList<Object>();
        while ((boolean) stmt.getExpr().accept(this)) {
            if (stmt.getBlock() != null) {
                Object o = stmt.getBlock().accept(this);
                list0.add(o);
                if (o != null && o.equals("break")) {
                    for (Object ob : list0) {
                        if (ob != null) {
                            return ob;
                        }
                    }
                    return null;
                }
            }
        }
        for (Object ob : list0) {
            if (ob != null) {
                return ob;
            }
        }
        return null;
    }

    @Override
    public Object visit(MethodCall stmt) {
        LinkedList<Object> listO = new LinkedList<Object>();
        Bloque bloque = new Bloque();
        tablaSimbolos.pushBloque(bloque);
        if (stmt.getExpressions() != null) {
            for (Expression e : stmt.getExpressions()) {
                listO.add(e.accept(this));
            }
            Metodo metodo = tablaSimbolos.getMetodo("Main", stmt.getId());
            for (Parameter p : metodo.getParametros()) {
                Atributo a = new Atributo(listO.getFirst(), p.getType(), p.getId());
                tablaSimbolos.setVariableBloque(a);
                listO.removeFirst();
            }
        }
        Object o = null;
        for (Method m : metodos) {
            if (m.getId().equals(stmt.getId())) {
                o = visit2(m);
                tablaSimbolos.popBloque();
                return o;
            }
        }
        return null;
    }

    public Object visit2(Method m) {
        Object o = null;
        if (m.getBody() != null) {
            o = m.getBody().accept(this);
        }
        if (!m.getType().isVoid()) {
            return o;
        } else {
            return null;
        }
    }

    @Override
    public Object visit(ExternStmt stmt) {
        return null;
    }

    @Override
    public Object visit(ForStmt stmt) {
        LinkedList<Object> listO = new LinkedList<Object>();
        Atributo loc = tablaSimbolos.getAtributo(stmt.getId());
        loc.setValor(stmt.getExpr().accept(this));
        while ((int) loc.getValor() == (int) stmt.getExpr().accept(this)) {
            if (stmt.getStatement() != null) {
                Object o = stmt.getStatement().accept(this);
                listO.add(o);
                if (o != null && o.equals("break")) {
                    for (Object ob : listO) {
                        if (ob != null) {
                            return ob;
                        }
                    }
                    return null;
                }
            }
            loc.setValor((int) loc.getValor() + 1);
        }
        for (Object ob : listO) {
            if (ob != null) {
                return ob;
            }
        }
        return null;
    }

    @Override
    public Object visit(MethodCallStmt stmt) {
        return stmt.getM().accept(this);
    }

    @Override
    public Object visit(BinOpExpr expr) {
        switch (expr.getOperator()) {
            case PLUS:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) + (int) expr.getRightOperand().accept(this);
                } else {
                    return (float) expr.getLeftOperand().accept(this) + (float) expr.getRightOperand().accept(this);
                }
            case MINUS:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) - (int) expr.getRightOperand().accept(this);
                } else {
                    return (float) expr.getLeftOperand().accept(this) - (float) expr.getRightOperand().accept(this);
                }
            case TIMES:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) * (int) expr.getRightOperand().accept(this);
                } else {
                    return (float) expr.getLeftOperand().accept(this) * (float) expr.getRightOperand().accept(this);
                }
            case DIVIDE:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) / (int) expr.getRightOperand().accept(this);
                } else {
                    return (float) expr.getLeftOperand().accept(this) / (float) expr.getRightOperand().accept(this);
                }
            case MOD:
                return (int) expr.getLeftOperand().accept(this) / (int) expr.getRightOperand().accept(this);
            case LT:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) < (int) expr.getRightOperand().accept(this);
                } else {
                    return (float) expr.getLeftOperand().accept(this) < (float) expr.getRightOperand().accept(this);
                }
            case LTEQ:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) <= (int) expr.getRightOperand().accept(this);
                } else {
                    return (float) expr.getLeftOperand().accept(this) <= (float) expr.getRightOperand().accept(this);
                }
            case GT:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) > (int) expr.getRightOperand().accept(this);
                } else {
                    return (float) expr.getLeftOperand().accept(this) > (float) expr.getRightOperand().accept(this);
                }
            case GTEQ:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) >= (int) expr.getRightOperand().accept(this);
                } else {
                    return (float) expr.getLeftOperand().accept(this) >= (float) expr.getRightOperand().accept(this);
                }
            case EQEQ:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) == (int) expr.getRightOperand().accept(this);
                } else {
                    if (expr.getLeftOperand().accept(this).getClass().equals(Boolean.class)) {
                        return (boolean) expr.getLeftOperand().accept(this) == (boolean) expr.getRightOperand().accept(this);
                    } else {
                        return (float) expr.getLeftOperand().accept(this) == (float) expr.getRightOperand().accept(this);
                    }
                }
            case NOTEQ:
                if (expr.getLeftOperand().accept(this).getClass().equals(Integer.class)) {
                    return (int) expr.getLeftOperand().accept(this) != (int) expr.getRightOperand().accept(this);
                } else {
                    if (expr.getLeftOperand().accept(this).getClass().equals(Boolean.class)) {
                        return (boolean) expr.getLeftOperand().accept(this) != (boolean) expr.getRightOperand().accept(this);
                    } else {
                        return (float) expr.getLeftOperand().accept(this) != (float) expr.getRightOperand().accept(this);
                    }
                }
            case AND:
                return (boolean) expr.getLeftOperand().accept(this) && (boolean) expr.getRightOperand().accept(this);
            case OR:
                return (boolean) expr.getLeftOperand().accept(this) || (boolean) expr.getRightOperand().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(UnaryOpExpr expr) {
        switch (expr.getOperator()) {
            case NOT:
                return !((boolean) expr.getOperand().accept(this));
            case MINUS:
                if (expr.getOperand().accept(this).getClass().equals(Integer.class)) {
                    return (-(int) expr.getOperand().accept(this));
                } else {
                    return (-(float) expr.getOperand().accept(this));
                }
        }
        return null;
    }

    @Override
    public Object visit(IntLiteral lit) {
        return lit.getValue();
    }

    @Override
    public Object visit(BoolLiteral lit) {
        return lit.getValue();
    }

    @Override
    public Object visit(FloatLiteral lit) {
        return lit.getValue();
    }

    @Override
    public Object visit(VarLocation loc) {
        Object t = tablaSimbolos.getAtributo(loc.getId()).getValor();
        return tablaSimbolos.getAtributo(loc.getId()).getValor();
    }

    @Override
    public Object visit(Block bl) {
        tablaSimbolos.pushBloque(new Bloque());
        for (FieldDeclaration fd : bl.getFd()) {
            for (LocationDeclaration ld : fd.getL()) {
                //creo un atributo
                Atributo atributo;
                if (ld.getSize() == null) {
                    atributo = new Atributo(null, fd.getType(), ld.getId());
                } else {
                    atributo = new Atributo(null, fd.getType(), ld.getId(), ld.getSize().getValue());
                }
                tablaSimbolos.setVariableBloque(atributo);
            }
        }
        Object ob = null;
        for (Statement s : bl.getStatements()) {
            Object o = s.accept(this);
            if (o != null) {
                ob = o;
            }

        }
        tablaSimbolos.popBloque();
        return ob;
    }

    @Override
    public Object visit(Body bl) {
        return bl.getBlock().accept(this);
    }

}
