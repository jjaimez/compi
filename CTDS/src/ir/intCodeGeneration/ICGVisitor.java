/*
 /*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Visitor generador de codigo intermedio
 */
package ir.intCodeGeneration;

import ir.ASTVisitor;
import ir.TablaDeSimbolos.Atributo;
import ir.ast.AssignOpType;
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
import ir.ast.Type;
import ir.ast.UnaryOpExpr;
import ir.ast.VarLocation;
import ir.ast.WhileStmt;
import java.util.LinkedList;
import java.util.Stack;

public class ICGVisitor implements ASTVisitor<Expression> {

    private LinkedList<Command> code;
    private int commId;
    private int labelId;
    private Stack<Pair<Integer, Integer>> iterLabels;
    private int offsetStack;

    public ICGVisitor() {
        code = new LinkedList<Command>();
        iterLabels = new Stack<Pair<Integer, Integer>>();
        offsetStack = 0;
        commId = 0;
        labelId = 0;
    }

    public LinkedList<Command> getCode() {
        return code;
    }

    @Override
    public Expression visit(Parameter p) {
        code.add(new Command(ICGOpType.PARAM, p, null, null));
        return null;
    }

    @Override
    public Expression visit(Method m) {
        ++labelId;
        offsetStack = 0;
        code.add(new Command(ICGOpType.LBL, new Pair(labelId, m.getId()), null, null));
        if (m.getParameters() != null) {
            for (Parameter p : m.getParameters()) {
                p.accept(this);
            }
        }
        m.getBody().accept(this);
        if (!code.getLast().getOp().equals(ICGOpType.RET)) {
            code.add(new Command(ICGOpType.RET, null, null, null));
        }
        return null;
    }

    @Override
    public Expression visit(LocationDeclaration ld) {
        return null;
    }

    @Override
    public Expression visit(FieldDeclaration fd) {
        for (LocationDeclaration ld : fd.getL()) {
            Integer tam = ((Atributo) ld.getReference()).getTamanio();
            if (tam != null && tam > 0) {
                offsetStack -= 4 * tam;
                ((Atributo) ld.getReference()).setOffset(offsetStack);
            }
            offsetStack -= 4;
            ((Atributo) ld.getReference()).setOffset(offsetStack);
            code.add(new Command(ICGOpType.DEF, fd.getType(), ld, null));
        }
        return null;
    }

    @Override
    public Expression visit(Declaration d) {
        for (FieldDeclaration fd : d.getFieldDecl()) {
            for (LocationDeclaration ld : fd.getL()) {
                code.add(new Command(ICGOpType.GDEF, fd.getType(), ld, null));
            }
        }
        for (Method m : d.getMethodDecl()) {
            m.accept(this);
        }
        return null;
    }

    @Override
    public Expression visit(ClassDeclaration cd) {
        cd.getDeclarations().accept(this);
        return null;
    }

    @Override
    public Expression visit(Program p) {
        for (ClassDeclaration cd : p.getClassDeclarations()) {
            cd.accept(this);
        }
        return null;
    }

    @Override
    public Expression visit(AssignStmt stmt) {
        Expression loc = stmt.getLocation().accept(this);
        AssignOpType op = stmt.getOperator();
        Expression expr = stmt.getExpression().accept(this);
        switch (stmt.getOperator()) {
            case INCREMENT:
                code.add(new Command(ICGOpType.ADD, loc, expr, loc));
                break;
            case DECREMENT:
                code.add(new Command(ICGOpType.SUB, loc, expr, loc));
                break;
            case ASSIGN:
                code.add(new Command(ICGOpType.STR, loc, expr, null));
                break;
        }
        return null;
    }

    @Override
    public Expression visit(ReturnStmt stmt) {
        if (stmt.getExpression() != null) {
            code.add(new Command(ICGOpType.RET, stmt.getExpression().accept(this), null, null));
        } else {
            code.add(new Command(ICGOpType.RET, null, null, null));
        }
        return null;
    }

    @Override
    public Expression visit(IfStmt stmt) {
        Expression cond = stmt.getCondition().accept(this);
        int lbl = ++labelId;
        int lbl2 = ++labelId;
        code.add(new Command(ICGOpType.CMP, "1", cond, null));
        code.add(new Command(ICGOpType.JNE, new Pair(lbl, ".LIF"), null, null));
        if (stmt.getIfBlock() != null) {
            stmt.getIfBlock().accept(this);
            code.add(new Command(ICGOpType.JMP, new Pair(lbl2, ".LEIF"), null, null));
        }
        code.add(new Command(ICGOpType.LBL, new Pair(lbl, ".LIF"), null, null));
        if (stmt.getElseBlock() != null) {
            stmt.getElseBlock().accept(this);
        }
        code.add(new Command(ICGOpType.LBL, new Pair(lbl2, ".LEIF"), null, null));
        return null;
    }

    @Override
    public Expression visit(BreakStmt stmt) {
        code.add(new Command(ICGOpType.JMP, new Pair(iterLabels.peek().snd(), ".EI"), null, null));
        return null;
    }

    @Override
    public Expression visit(ContinueStmt stmt) {
        code.add(new Command(ICGOpType.JMP, new Pair(iterLabels.peek().fst(), ".BI"), null, null));
        return null;
    }

    @Override
    public Expression visit(WhileStmt stmt) {
        int lbl = ++labelId;
        iterLabels.push(new Pair<>(lbl, ++labelId));
        code.add(new Command(ICGOpType.LBL, new Pair(iterLabels.peek().fst(), ".BI"), null, null));
        Expression cond = stmt.getExpr().accept(this);
        code.add(new Command(ICGOpType.CMP, "1", cond, null));
        code.add(new Command(ICGOpType.JNE, new Pair(iterLabels.peek().snd(), ".EI"), null, null));
        stmt.getBlock().accept(this);
        code.add(new Command(ICGOpType.JMP, new Pair(iterLabels.peek().fst(), ".BI"), null, null));
        code.add(new Command(ICGOpType.LBL, new Pair(iterLabels.peek().snd(), ".EI"), null, null));
        iterLabels.pop();
        return null;
    }

    @Override
    public Expression visit(MethodCall stmt) {
        LinkedList<Expression> list = (LinkedList) stmt.getExpressions();
        for (int i = 0; i < list.size(); i++) {
            Expression e = list.get(i).accept(this);
            list.set(i, e);
        }
        stmt.setExpressions(list);
        ++commId;
        int id = commId;
        VarLocation var = new VarLocation("t" + id);
        Atributo a = new Atributo(null, stmt.getType(), "t" + id);
        offsetStack -= 4;
        a.setOffset(offsetStack);
        code.add(new Command(ICGOpType.CALL, stmt, var, null));
        return var;
    }

    @Override
    public Expression visit(ExternStmt stmt) {
        return null;
    }

    @Override
    public Expression visit(ForStmt stmt) {
        int lbl = ++labelId;
        iterLabels.push(new Pair<>(lbl, ++labelId));
        code.add(new Command(ICGOpType.STR, stmt.getId(), stmt.getExpr().accept(this), null));
        code.add(new Command(ICGOpType.LBL, new Pair(iterLabels.peek().fst(), ".BI"), null, null));
        Expression to = stmt.getExpr2().accept(this);
        code.add(new Command(ICGOpType.CMP, "1", to, null));
        code.add(new Command(ICGOpType.JNE, new Pair(iterLabels.peek().snd(), ".EI"), null, null));
        if (stmt.getStatement() != null) {
            stmt.getStatement().accept(this);
        }
        code.add(new Command(ICGOpType.INC, stmt.getId(), null, null));
        code.add(new Command(ICGOpType.JMP, new Pair(iterLabels.peek().fst(), ".BI"), null, null));
        code.add(new Command(ICGOpType.LBL, new Pair(iterLabels.peek().snd(), ".EI"), null, null));
        iterLabels.pop();
        return null;
    }

    @Override
    public Expression visit(MethodCallStmt stmt) {
        stmt.getM().accept(this);
        return null;
    }

    @Override
    public Expression visit(BinOpExpr expr) {
        BinOpType op = expr.getOperator();
        Expression left = expr.getLeftOperand().accept(this); // codigo de primer operando
        Expression right = expr.getRightOperand().accept(this); // codigo de segundo operando
        ++commId;
        int id = commId;
        VarLocation var = new VarLocation("t" + id);
        Atributo a = new Atributo(null, expr.getType(), "t" + id);
        var.setReference(a);
        offsetStack -= 4;
        a.setOffset(offsetStack);
        switch (op) {
            case MINUS:
                code.add(new Command(ICGOpType.SUB, left, right, var));
                break;
            case PLUS:
                code.add(new Command(ICGOpType.ADD, left, right, var));
                break;
            case TIMES:
                code.add(new Command(ICGOpType.MUL, left, right, var));
                break;
            case DIVIDE:
                code.add(new Command(ICGOpType.DIV, left, right, var));
                break;
            case MOD:
                code.add(new Command(ICGOpType.MOD, left, right, var));
                break;
            case AND:
                code.add(new Command(ICGOpType.AND, left, right, var));
                break;
            case OR:
                code.add(new Command(ICGOpType.OR, left, right, var));
                break;
            case LT:
                code.add(new Command(ICGOpType.LT, left, right, var));
                break;
            case LTEQ:
                code.add(new Command(ICGOpType.LTEQ, left, right, var));
                break;
            case GT:
                code.add(new Command(ICGOpType.GT, left, right, var));
                break;
            case GTEQ:
                code.add(new Command(ICGOpType.GTEQ, left, right, var));
                break;
            case EQEQ:
                code.add(new Command(ICGOpType.EQEQ, left, right, var));
                break;
            case NOTEQ:
                code.add(new Command(ICGOpType.NOTEQ, left, right, var));
                break;
        }
        return var;
    }

    @Override
    public Expression visit(UnaryOpExpr expr) {
        ++commId;
        int id = commId;
        Expression e = expr.getOperand().accept(this);
        VarLocation var = new VarLocation("t" + id);
        Atributo a = new Atributo(null, expr.getType(), "t" + id);
        offsetStack -= 4;
        a.setOffset(offsetStack);
        var.setReference(a);
        switch (expr.getOperator()) {
            case NOT:
                code.add(new Command(ICGOpType.NOT, e, var, null));
            case MINUS:
                code.add(new Command(ICGOpType.MIN, e, var, null));
                break;
        }
        return var;
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
        if (loc.getExp() != null) {
            loc.setExp(loc.getExp().accept(this));
        }
        return loc;
    }

    @Override
    public Expression visit(Block bl) {
        for (FieldDeclaration fd : bl.getFd()) {
            fd.accept(this);
        }
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
