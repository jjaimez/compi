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
        switch (this) {
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

    public boolean isArithmetic() {
        if (this == BinOpType.PLUS || this == BinOpType.MINUS || this == BinOpType.TIMES || this == BinOpType.DIVIDE || this == BinOpType.MOD) {
            return true;
        }

        return false;
    }

    public boolean isRelational() {
        if (this == BinOpType.LT || this == BinOpType.LTEQ || this == BinOpType.GT || this == BinOpType.GTEQ) {
            return true;
        }

        return false;
    }

    public boolean isEquational() {
        if (this == BinOpType.EQEQ || this == BinOpType.NOTEQ) {
            return true;
        }

        return false;
    }

    public boolean isConditional() {
        if (this == BinOpType.AND || this == BinOpType.OR) {
            return true;
        }

        return false;
    }
}
