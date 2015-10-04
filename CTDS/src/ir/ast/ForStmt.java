/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion del statement for
 */
package ir.ast;

import ir.ASTVisitor;

public class ForStmt extends Statement {

    private String id;
    private Expression expr;
    private Expression expr2;
    private Statement statement;

    public ForStmt(String id, Expression expr, Expression expr2, Statement statement) {
        this.id = id;
        this.expr = expr;
        this.expr2 = expr2;
        this.statement = statement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr2() {
        return expr2;
    }

    public void setExprfin(Expression expr2) {
        this.expr2 = expr2;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "for " + id + "=" + expr.toString() + "," + expr2.toString() + statement.toString();
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }

}
