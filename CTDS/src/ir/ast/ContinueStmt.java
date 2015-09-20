/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion sentencia continue
 */

package ir.ast;

import ir.ASTVisitor;

public class ContinueStmt extends Statement {
    

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
    
}
