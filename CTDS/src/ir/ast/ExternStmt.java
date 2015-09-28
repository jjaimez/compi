/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion extern
 */

package ir.ast;

import ir.ASTVisitor;

public class ExternStmt extends Body {

    public ExternStmt() {
    }
    
    @Override
    public String toString(){
        return "extern";
    }
    

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
    
}