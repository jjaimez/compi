/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion del cerpo
 */

package ir.ast;


import ir.ASTVisitor;

public class Body extends Statement {
	private Block block;
	
	public Body(Block block) {
            this.block = block;
	}
	
	
	@Override
	public <T> T accept(ASTVisitor<T> v) {
		return v.visit(this);
	}
	
}
