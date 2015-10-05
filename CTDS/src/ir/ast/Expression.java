/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Clase abstracta de una expresion
 */

package ir.ast;

public abstract class Expression extends AST {
	protected Expression expr;
	protected Type type;
	
	public Type getType() {
		return this.type;
	}
	
	public void setType(Type t) {
		this.type = t;
	}

    public Expression getExpr() {
        return expr;
    }
        
}
