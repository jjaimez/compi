/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Visitor que optimiza las operaciones con constantes.
 */
package ir.optimization;

import ir.ASTVisitor;
import ir.ast.AssignStmt;
import ir.ast.BinOpExpr;
import ir.ast.BinOpType;
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


public class OptimizationVisitor implements ASTVisitor<Expression> {

    @Override
    public Expression visit(Parameter p) {
        return null;
    }

    @Override
    public Expression visit(Method m) {
        m.getBody().accept(this);
        return null;
    }

    @Override
    public Expression visit(LocationDeclaration ld) {
        return null;
    }

    @Override
    public Expression visit(FieldDeclaration fd) {
        return null;
    }

    @Override
    public Expression visit(Declaration d) {
        for (Method m : d.getMethodDecl()) {
            m.accept(this);
        }
        return null;
    }

    @Override
    public Expression visit(ClassDeclaration cd) {
        if (cd.getDeclarations() != null) {
            cd.getDeclarations().accept(this);
        }
        return null;
    }

    @Override
    public Expression visit(Program p) {
        if (p.getClassDeclarations() != null) {
            for (ClassDeclaration cd : p.getClassDeclarations()) {
                cd.accept(this);
            }
        }
        return null;
    }

    @Override
    public Expression visit(AssignStmt stmt) {
        Expression e = stmt.getExpression().accept(this);
        stmt.setExpression(e);
        return null;
    }

    @Override
    public Expression visit(ReturnStmt stmt) {
        if (stmt.getExpression() != null) {
            Expression e = stmt.getExpression().accept(this);
            stmt.setExpression(e);
        }
        return null;
    }

    @Override
    public Expression visit(IfStmt stmt) {
        Expression e = stmt.getCondition().accept(this);
        stmt.setCondition(e);
        if (stmt.getIfBlock() != null) {
            stmt.getIfBlock().accept(this);
        }
        if (stmt.getElseBlock() != null) {
            stmt.getElseBlock().accept(this);
        }
        return null;
    }

    @Override
    public Expression visit(BreakStmt stmt) {
        return null;
    }

    @Override
    public Expression visit(ContinueStmt stmt) {
        return null;
    }

    @Override
    public Expression visit(WhileStmt stmt) {
        Expression e = stmt.getExpr().accept(this);
        stmt.setExpr(e);
        if (stmt.getBlock() != null) {
            stmt.getBlock().accept(this);
        }
        return null;
    }

    @Override
    public Expression visit(MethodCall stmt) {
        for (Expression e : stmt.getExpressions()) {
            e = e.accept(this);
        }
        return stmt;
    }

    @Override
    public Expression visit(ExternStmt stmt) {
        return null;
    }

    @Override
    public Expression visit(ForStmt stmt) {
        Expression e0 = stmt.getExpr().accept(this);
        Expression e1 = stmt.getExpr2().accept(this);
        stmt.setExpr(e0);
        stmt.setExprfin(e1);
        stmt.getStatement().accept(this);
        return null;
    }

    @Override
    public Expression visit(MethodCallStmt stmt) {
        stmt.getM().accept(this);
        return null;
    }

    @Override
    public Expression visit(BinOpExpr expr) {
        Expression e0 = expr.getLeftOperand().accept(this);
        BinOpType op = expr.getOperator();
        Expression e1 = expr.getRightOperand().accept(this);
        if (e0 instanceof BoolLiteral && e1 instanceof BoolLiteral) {
            BoolLiteral b0 = (BoolLiteral) e0;
            BoolLiteral b1 = (BoolLiteral) e1;
            switch (op) {
                case EQEQ:
                    b0.setValue(b0.getValue() == b1.getValue());
                    break;
                case NOTEQ:
                    b0.setValue(b0.getValue() != b1.getValue());
                    break;
                case AND:
                    b0.setValue(b0.getValue() && b1.getValue());
                    break;
                case OR:
                    b0.setValue(b0.getValue() || b1.getValue());
                    break;
            }
            return b0;
        }
        if (e0 instanceof IntLiteral && e1 instanceof IntLiteral) {
            IntLiteral i0 = (IntLiteral) e0;
            IntLiteral i1 = (IntLiteral) e1;
            BoolLiteral b = null;
            switch (op) {
                case EQEQ:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) == 0, 0, 0);
                    break;
                case NOTEQ:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) != 0, 0, 0);
                    break;
                case LT:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) < 0, 0, 0);
                    break;
                case GT:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) > 0, 0, 0);
                    break;
                case LTEQ:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) <= 0, 0, 0);
                    break;
                case GTEQ:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) >= 0, 0, 0);
                    break;
                case PLUS:
                    i0.setValue(i0.getValue() + i1.getValue());
                    break;
                case MINUS:
                    i0.setValue(i0.getValue() - i1.getValue());
                    break;
                case TIMES:
                    i0.setValue(i0.getValue() * i1.getValue());
                    break;
                case DIVIDE:
                    i0.setValue(i0.getValue() / i1.getValue());
                    break;
                case MOD:
                    i0.setValue(i0.getValue() % i1.getValue());
                    break;
            }
            if (b != null) {
                return b;
            } else {
                return i0;
            }
        }
        if (e0 instanceof FloatLiteral && e1 instanceof FloatLiteral) {
            FloatLiteral i0 = (FloatLiteral) e0;
            FloatLiteral i1 = (FloatLiteral) e1;
            BoolLiteral b = null;
            switch (op) {
                case EQEQ:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) == 0, 0, 0);
                    break;
                case NOTEQ:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) != 0, 0, 0);
                    break;
                case LT:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) < 0, 0, 0);
                    break;
                case GT:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) > 0, 0, 0);
                    break;
                case LTEQ:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) <= 0, 0, 0);
                    break;
                case GTEQ:
                    b = new BoolLiteral(i0.getValue().compareTo(i1.getValue()) >= 0, 0, 0);
                    break;
                case PLUS:
                    i0.setValue(i0.getValue() + i1.getValue());
                    break;
                case MINUS:
                    i0.setValue(i0.getValue() - i1.getValue());
                    break;
                case TIMES:
                    i0.setValue(i0.getValue() * i1.getValue());
                    break;
                case DIVIDE:
                    i0.setValue(i0.getValue() / i1.getValue());
                    break;
                case MOD:
                    i0.setValue(i0.getValue() % i1.getValue());
                    break;
            }
            if (b != null) {
                return b;
            } else {
                return i0;
            }
        }
        return expr;
    }

    @Override
    public Expression visit(UnaryOpExpr expr) {
        Expression e = expr.getOperand().accept(this);
        if (e instanceof BoolLiteral) {
            BoolLiteral b = (BoolLiteral) e;
            b.setValue(!b.getValue());
            e = b;
            return b;
        }
        if (e instanceof IntLiteral) {
            IntLiteral i = (IntLiteral) e;
            i.setValue(-i.getValue());
            e = i;
            return i;
        }
        if (e instanceof FloatLiteral) {
            FloatLiteral f = (FloatLiteral) e;
            f.setValue(-f.getValue());
            e = f;
            return f;
        }
        return expr;
    }

    @Override
    public Expression visit(IntLiteral lit) {
        return lit;
    }

    @Override
    public Expression visit(BoolLiteral lit) {
        return lit;
    }

    @Override
    public Expression visit(FloatLiteral lit) {
        return lit;
    }

    @Override
    public Expression visit(VarLocation loc) {
        return loc;
    }

    @Override
    public Expression visit(Block bl) {
        if (bl.getStatements() != null) {
            for (Statement s : bl.getStatements()) {
                s.accept(this);
            }
        }
        return null;
    }

    @Override
    public Expression visit(Body bl) {
        bl.getBlock().accept(this);
        return null;
    }

}
