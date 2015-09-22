/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Sentencia de asignación
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.List;

public class MethodCall extends Expression {

    private String id;
    private List<Expression> expressions;

    public MethodCall(String id) {
        this.id = id;

    }

    public MethodCall(String id, List<Expression> expressions) {
        this.id = id;
        this.expressions = expressions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
