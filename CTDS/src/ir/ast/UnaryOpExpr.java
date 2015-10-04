/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion exprecione con operador unario
 */
package ir.ast;

import ir.ASTVisitor;

public class UnaryOpExpr extends Expression {

    private UnaryOpType operator; //operator in the expr = operator expr
    private Expression operand; // expression

    public UnaryOpExpr(UnaryOpType operator, Expression operand, int line, int col) {
        this.setLineNumber(line+1);
        this.setColumnNumber(col+1);
        this.operator = operator;
        this.operand = operand;
    }

    public UnaryOpType getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "("+operator.toString() + " " + operand.toString()+")";
    }

    public void setOperator(UnaryOpType operator) {
        this.operator = operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
