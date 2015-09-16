/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion operadores binarios
 */

package ir.ast;

public enum BinOpType {
	PLUS, // Arithmetic
	MINUS,
	TIMES,
	DIVIDE,
	MOD,
	LT, // Relational
	LTEQ,
	GT,
	GTEQ,
	NOTEQ, // Equal
	EQEQ, 
	AND, // Conditional
	OR;
	
	@Override
	public String toString() {
		switch(this) {
			case PLUS:
				return "+";
			case MINUS:
				return "-";
			case TIMES:
				return "*";
			case DIVIDE:
				return "/";
			case MOD:
				return "%";
			case LT:
				return "<";
			case LTEQ:
				return "<=";
			case GT:
				return ">";
			case GTEQ:
				return ">=";
			case EQEQ:
				return "==";
			case NOTEQ:
				return "!=";
			case AND:
				return "&&";
			case OR:
				return "||";
		}
		
		return null;
	}

}
