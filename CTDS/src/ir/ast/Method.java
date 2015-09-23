/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de metodo
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.List;

public class Method extends Expression {
    
    private Type type;
    private String id;
    private List<Parameter> parameters;
    private Body body;
    
    
    public Method(String id) {
        this.id = id;

    }

    public Method(Type type, String id, List<Parameter> parameters, Body body) {
        this.type = type;
        this.id = id;
        this.parameters = parameters;
        this.body = body;
    }

    public Method(Type type, String id, Body body) {
        this.type = type;
        this.id = id;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
