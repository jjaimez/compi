/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion expresion con operadores binarios
 */
package ir.ast;

import ir.ASTVisitor;

public class BinOpExpr extends Expression {

    private BinOpType operator; //operator in the expr = expr operator expr
    private Expression lOperand; //left expression
    private Expression rOperand; //right expression

    public BinOpExpr(Expression l, BinOpType op, Expression r, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        operator = op;
        lOperand = l;
        rOperand = r;
    }

    public BinOpType getOperator() {
        return operator;
    }

    public void setOperator(BinOpType operator) {
        this.operator = operator;
    }

    public Expression getLeftOperand() {
        return lOperand;
    }

    public void setLeftOperand(Expression lOperand) {
        this.lOperand = lOperand;
    }

    public Expression getRightOperand() {
        return rOperand;
    }

    public void setRightOperand(Expression rOperand) {
        this.rOperand = rOperand;
    }

    @Override
    public String toString() {
        return "(" + lOperand.toString() + " " + operator.toString() + " " + rOperand.toString() + ")";
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
