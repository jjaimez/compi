/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Definicion 
 */
package ir.ast;

import ir.ASTVisitor;

public class VarLocation extends Location {

    private int blockId;
    private Expression exp;
    private VarLocation location;

    public VarLocation(String id, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        this.id = id;
        this.blockId = -1;
    }

    public VarLocation(String id, Expression exp, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        this.id = id;
        this.blockId = -1;
        this.exp = exp;
    }

    public VarLocation(String id, VarLocation loc, int line, int col) {
        this.setLineNumber(line + 1);
        this.setColumnNumber(col + 1);
        this.id = id;
        this.blockId = -1;
        location = loc;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    @Override
    public String toString() {
        if (exp != null) {
            return id + " " + exp.toString();

        }
        if (location != null) {
            return id + " " + location.toString();
        }
        return id;
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
