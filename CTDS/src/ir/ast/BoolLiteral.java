/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion de un boolean
 */
package ir.ast;

import ir.ASTVisitor;

public class BoolLiteral extends Literal {
    
    boolean value;
    
    public BoolLiteral(String val, int line, int col) {
        this.setLineNumber(line+1);
        this.setColumnNumber(col+1);
        value = val.equals("true");
        type = Type.BOOL;
    }
    
    @Override
    public Type getType() {
        return Type.BOOL;
    }
    
    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
    
}
