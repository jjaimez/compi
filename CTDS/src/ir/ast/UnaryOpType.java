/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion operadores unarios
 */
package ir.ast;

public enum UnaryOpType {

    MINUS,
    NOT;

    @Override
    public String toString() {
        switch (this) {
            case NOT:
                return "!";
            case MINUS:
                return "-";
        }
        return null;
    }

}
