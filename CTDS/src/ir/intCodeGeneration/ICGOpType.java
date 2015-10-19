/*
/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Definicion de instrucciones de codigo intermedio
 */
package ir.intCodeGeneration;

public enum ICGOpType {
    STR, //ASSIGN
    ADD,
    SUB,
    MUL,
    DIV,
    MOD,   
    MIN, //minus -
    AND,
    OR,   
    NOT,   
    LT, 
    LTEQ,
    GT,
    GTEQ,
    NOTEQ,
    EQEQ,
    RET;
}
