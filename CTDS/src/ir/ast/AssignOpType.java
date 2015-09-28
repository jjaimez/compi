/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de asignaciones
 */
package ir.ast;

public enum AssignOpType {

    INCREMENT,
    DECREMENT,
    ASSIGN;

    @Override
    public String toString() {
        switch (this) {
            case INCREMENT:
                return "+=";
            case DECREMENT:
                return "-=";
            case ASSIGN:
                return "=";
        }

        return null;
    }

    public boolean isAssign() {
        if (this == AssignOpType.ASSIGN) {
            return true;
        }

        return false;
    }

    public boolean isIncrement() {
        if (this == AssignOpType.INCREMENT) {
            return true;
        }

        return false;
    }

    public boolean isDecrement() {
        if (this == AssignOpType.DECREMENT) {
            return true;
        }

        return false;
    }
}
