/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Expresion de llamada a un metodo.
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.LinkedList;
import java.util.List;

public class MethodCall extends Expression {

    private String id;
    private List<Expression> expressions = new LinkedList<Expression>();
    private MethodCall mc;

    public MethodCall(String id, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        this.id = id;

    }

    public MethodCall(String id, List<Expression> expressions, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        this.id = id;
        if (expressions!=null)
            this.expressions = expressions;
    }

    public MethodCall(String id, MethodCall mc, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        this.id = id;
        this.mc = mc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
    
     public List<Expression> getExpressions() {
        return expressions;
    }

    public String getId() {
        return id;
    }
    

    @Override
    public String toString() {
        String s = id + " ";
        if (expressions != null) {
            s += expressions.toString();
        }
        if (mc != null) {
            s += mc.toString();
        }

        return s;

    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
