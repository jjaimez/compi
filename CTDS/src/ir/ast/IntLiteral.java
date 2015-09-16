/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion numero entero
 */

package ir.ast;

import ir.ASTVisitor;

public class IntLiteral extends Literal {
	private Integer value;
	
	/*
	 * Constructor for int literal that takes a string as an input
	 * @param: String integer
	 */
	public IntLiteral(Integer val){
		value = val;
	}

	@Override
	public Type getType() {
		return Type.INT;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
}
