/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * Representacion bloque del programa
 */
package ir.ast;

import java.util.List;
import ir.ASTVisitor;
import java.util.LinkedList;

public class Block extends Statement {

    private List<Statement> statements = new LinkedList<Statement>();
    private int blockId;
    private LinkedList<FieldDeclaration> fd = new LinkedList<FieldDeclaration>();

    public Block() {
    }

    public Block(int bId) {
        blockId = bId;
    }

    public Block(List<Statement> statements) {
        this.statements = statements;
    }

    public Block(int bId, List<Statement> s) {
        blockId = bId;
        statements = s;
    }

    public Block(List<Statement> statements, LinkedList<FieldDeclaration> fd) {
        this.statements = statements;
        if (fd != null)
            this.fd = fd;
    }

    public Block(LinkedList<FieldDeclaration> fd) {
        this.fd = fd;
    }

    public void addStatement(Statement s) {
        this.statements.add(s);
    }

    public List<Statement> getStatements() {
        return this.statements;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    @Override
    public String toString() {
        String rtn = "";
        if (fd != null) {
            for (FieldDeclaration s1 : fd) {
                rtn += s1.toString() + '\n';
            }
        }
        if (statements != null) {
            for (Statement s : statements) {
                if (s != null) {
                    rtn += s.toString() + '\n';
                }
            }
        }

        return rtn;
    }

    @Override
    public <T> T accept(ASTVisitor<T> v) {
        return v.visit(this);
    }
}
