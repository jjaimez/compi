/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion literal
 */

package ir.ast;

public abstract class Literal extends Expression {
	/*
	 * @return: returns Type of Literal instance
	 */
	public abstract Type getType();
}
