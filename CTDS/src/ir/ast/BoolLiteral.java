/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion de un boolean
 */
package ir.ast;

import ir.ASTVisitor;

public class BoolLiteral extends Literal {
    
    boolean value;
    
    public BoolLiteral(String val, int line, int col) {
        this.setLineNumber(line+1);
        this.setColumnNumber(col+1);
        value = val.equals("true");
        type = Type.BOOL;
    }
    
       public BoolLiteral(boolean val, int line, int col) {
        this.setLineNumber(line+1);
        this.setColumnNumber(col+1);
        value = val;
        type = Type.BOOL;
    }
    
    @Override
    public Type getType() {
        return Type.BOOL;
    }
    
    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
    
    @Override
    public String toString() {
                if(value)
            return "1";
        else 
            return "0";
    }
    
    public int getIntValue(){
        if(value)
            return 1;
        else 
            return 0;
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
    
}
