/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion de un boolean
 */


package ir.ast;

import ir.ASTVisitor;


public class BoolLiteral extends Literal {
    boolean value;
    
    public BoolLiteral(Boolean val){
		value = val; 
	}

	@Override
	public Type getType() {
		return Type.BOOL;
	}

    public void setValue(boolean value) {
        this.value = value;
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
