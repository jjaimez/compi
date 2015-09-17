/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion de los tipos existentes en el lenguaje
 */
package ir.ast;

public enum Type {

    INT,
    INTARRAY,
    VOID,
    UNDEFINED,
    FLOAT,
    BOOL;

    @Override
    public String toString() {
        switch (this) {
            case INT:
                return "int";
            case VOID:
                return "void";
            case UNDEFINED:
                return "undefined";
            case INTARRAY:
                return "int[]";
            case BOOL:
                return "boolean";
            case FLOAT:
                return "float";
        }
        return null;
    }

    public boolean isArray() {
        if (this == Type.INTARRAY) {
            return true;
        }
        return false;
    }

    public boolean isVoid() {
        if (this == Type.VOID) {
            return true;
        }
        return false;
    }

    public boolean isUndefined() {
        if (this == Type.UNDEFINED) {
            return true;
        }
        return false;
    }
    
    
    public boolean isInt() {
        if (this == Type.INT) {
            return true;
        }
        return false;
    }

    public boolean isFloat() {
        if (this == Type.FLOAT) {
            return true;
        }
        return false;
    }

    public boolean isBool() {
        if (this == Type.BOOL) {
            return true;
        }
        return false;
    }
}
