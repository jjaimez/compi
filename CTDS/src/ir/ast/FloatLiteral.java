/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion numero flotante
 */
package ir.ast;

import ir.ASTVisitor;

public class FloatLiteral extends Literal {

    private Float value;

    public FloatLiteral(String value, int line, int col) {
        this.setLineNumber(line+1);
        this.setColumnNumber(col+1);
        this.value = Float.valueOf(value);
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
