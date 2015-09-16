/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion numero flotante
 */

package ir.ast;

import ir.ASTVisitor;


public class FloatLiteral extends Literal {

    private Float value;

    public FloatLiteral(Float value) {
        this.value = value;
    }
    
    @Override
    public Type getType() {
        return Type.FLOAT;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    
    
    
}
