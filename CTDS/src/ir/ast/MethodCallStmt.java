/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * sentencia de llamada a un metodo.
 */

package ir.ast;

import ir.ASTVisitor;

public class MethodCallStmt extends Statement {

    private MethodCall m;

    public MethodCall getM() {
        return m;
    }

    public void setM(MethodCall m) {
        this.m = m;
    }

    public MethodCallStmt(MethodCall m) {
        this.m = m;
    }
    
    @Override
    public String toString(){
        return m.toString();
    }
    
    
    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
    
}
