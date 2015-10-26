/*
 /*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Definicion de instrucciones de codigo intermedio
 */
package ir.intCodeGeneration;

public enum ICGOpType {

    STR, //ASSIGN
    ADD, //SUMA
    SUB, //RESTA
    MUL, //MULTIPLICACION
    DIV, //DIVISION
    MOD, //%
    MIN, //minus -
    AND, //&&
    OR, //||
    NOT, //!
    LT, //<
    LTEQ, //<=
    GT, //>
    GTEQ, //>=
    NOTEQ, //NOT EQUAL
    EQEQ, //==
    RET, //RETURN
    CMP, //COMPARE
    JNE, //jumpo not equals
    JE, //JUMP EQUAL
    JMP, //JUMP
    LBL, //LABEL
    INC, // INCREMENTAR
    CALL, //CALL FUNCTION
    DEF, // DEFINICION
    GDEF, //DEF VARIABLES GLOBALES
    PARAM; //PARAM
}
