/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion sentencia while
 */

package ir.ast;

import ir.ASTVisitor;


public class WhileStmt extends Statement {

    private Statement block;
    private Expression expr;

    public WhileStmt(Statement block, Expression expr) {
        this.block = block;
        this.expr = expr;
    }

    public Statement getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "While" + expr.toString() + " " + block.toString();
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> v) {
       return v.visit(this);
    }
    
}
