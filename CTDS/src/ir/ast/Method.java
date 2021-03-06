/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * definicion de metodo
 */
package ir.ast;

import ir.ASTVisitor;
import java.util.LinkedList;
import java.util.List;

public class Method extends Expression {

    private Type type;
    private String id;
    private List<Parameter> parameters = new LinkedList<Parameter>();
    private Body body;
    private int offset = 0;
    private boolean extern = false;

    public Method(String id) {
        this.id = id;

    }

    public Method(Type type, String id, List<Parameter> parameters, Body body) {
        this.type = type;
        this.id = id;
        if (parameters!=null)
            this.parameters = parameters;
        this.body = body;
    }

    public Method(Type type, String id, Body body) {
        this.type = type;
        this.id = id;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }
    
    @Override
    public Type getType(){
        return this.type;
    }
    
    @Override
    public String toString() {
        String s = type.toString() + " " + id + "(";
        if (parameters != null) {
            for (Parameter p : parameters) {
                s += p.toString();
            }
        }
        if(body.toString() != "extern"){
            s += "){" + '\n' + body.toString() + "}";
        }
        else{
            s+= ") "+ body.toString()+ ";";
        }
        return s;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isExtern() {
        return extern;
    }

    public void setExtern(boolean extern) {
        this.extern = extern;
    }

    

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
