/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Visitor que setea las referencias a los atributos en los nodos AST
 */
package ir.semcheck;

import ir.ASTVisitor;
import ir.TablaDeSimbolos.Atributo;
import ir.TablaDeSimbolos.Bloque;
import ir.TablaDeSimbolos.Clase;
import ir.TablaDeSimbolos.Metodo;
import ir.TablaDeSimbolos.TablaDeSimbolos;
import ir.ast.AssignStmt;
import ir.ast.BinOpExpr;
import ir.ast.Block;
import ir.ast.Body;
import ir.ast.BoolLiteral;
import ir.ast.BreakStmt;
import ir.ast.ClassDeclaration;
import ir.ast.ContinueStmt;
import ir.ast.Declaration;
import ir.ast.ExternStmt;
import ir.ast.FieldDeclaration;
import ir.ast.FloatLiteral;
import ir.ast.ForStmt;
import ir.ast.IfStmt;
import ir.ast.IntLiteral;
import ir.ast.LocationDeclaration;
import ir.ast.Method;
import ir.ast.MethodCall;
import ir.ast.MethodCallStmt;
import ir.ast.Parameter;
import ir.ast.Program;
import ir.ast.ReturnStmt;
import ir.ast.Statement;
import ir.ast.UnaryOpExpr;
import ir.ast.VarLocation;
import ir.ast.WhileStmt;

/**
 *
 * @author jacinto
 */
public class SetReferencesVisitor implements ASTVisitor<Object> {

    private TablaDeSimbolos tablaSimbolos;

    public SetReferencesVisitor() {
        this.tablaSimbolos = new TablaDeSimbolos();
    }
    
    
    
    @Override
    public Object visit(Parameter p) {
        Atributo atributo;
        atributo = new Atributo(null, p.getType(), p.getId());
        tablaSimbolos.setVariableBloque(atributo);
        p.setReference(atributo);
        return null;
    }

    @Override
    public Object visit(Method m) {
        Bloque bloque = new Bloque();
        tablaSimbolos.pushBloque(bloque);
        for (Parameter p : m.getParameters()) {
            p.accept(this);
        }
        m.getBody().accept(this);
        tablaSimbolos.popBloque();
        return null;
    }

    @Override
    public Object visit(LocationDeclaration ld) {
        return null;
    }

    @Override
    public Object visit(FieldDeclaration fd) {
        return null;
    }

    @Override
    public Object visit(Declaration d) {
        for (FieldDeclaration fd : d.getFieldDecl()) {
            for (LocationDeclaration ld : fd.getL()) {
                //creo un atributo
                Atributo atributo;
                if (ld.getSize() == null) {
                    atributo = new Atributo(null, fd.getType(), ld.getId());
                } else {
                    atributo = new Atributo(null, fd.getType(), ld.getId(), ld.getSize().getValue());
                }
                ld.setReference(atributo);
                tablaSimbolos.setVariableBloque(atributo);
                tablaSimbolos.insertAtrClase(tablaSimbolos.getUltimaClase(), atributo);
            }
        }
        //recorro la definicion de metodos
        for (Method m : d.getMethodDecl()) {
            m.accept(this);
            Metodo metodo = new Metodo(m.getId(), m.getType(), m.getParameters());
            m.setReference(metodo);
            tablaSimbolos.insertMetClase(tablaSimbolos.getUltimaClase(), metodo);
        }
        return null;
    }

    @Override
    public Object visit(ClassDeclaration cd) {
        Clase c = new Clase();

        cd.setReference(c);
        tablaSimbolos.pushClase(cd.getId(), c);
        tablaSimbolos.pushBloque(new Bloque());
        if (cd.getDeclarations() != null) {
            cd.getDeclarations().accept(this);
        }
        tablaSimbolos.popBloque();
        return null;
    }

    @Override
    public Object visit(Program p) {
        if (p.getClassDeclarations() != null) {
            for (ClassDeclaration cd : p.getClassDeclarations()) {
                cd.accept(this);
            }
        }
        return null;
    }

    @Override
    public Object visit(AssignStmt stmt) {
        if (stmt.getLocation().getExpr() != null) {
            stmt.getLocation().getExpr().accept(this);
        }
        stmt.getLocation().accept(this);
        return null;
    }

    @Override
    public Object visit(ReturnStmt stmt) {
        stmt.getExpression().accept(this);
        return null;
    }

    @Override
    public Object visit(IfStmt stmt) {
        stmt.getCondition().accept(this);
        if (stmt.getIfBlock() != null) {
            stmt.getIfBlock().accept(this);
        }
        if (stmt.getElseBlock() != null) {
            stmt.getElseBlock().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(BreakStmt stmt) {
        return null;
    }

    @Override
    public Object visit(ContinueStmt stmt) {
        return null;
    }

    @Override
    public Object visit(WhileStmt stmt) {
        stmt.getExpr().accept(this);
        if (stmt.getBlock() != null) {
            stmt.getBlock().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(MethodCall stmt) {
        Metodo met = tablaSimbolos.getMetodo(tablaSimbolos.getUltimaClase(), stmt.getId());
        stmt.setReference(met);
        return null;
    }

    @Override
    public Object visit(ExternStmt stmt) {
        return null;
    }

    @Override
    public Object visit(ForStmt stmt) {
        stmt.getExpr().accept(this);
        stmt.getExpr2().accept(this);
        if (stmt.getStatement() != null) {
            stmt.getStatement().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(MethodCallStmt stmt) {
        stmt.getM().accept(this);
        return null;
    }

    @Override
    public Object visit(BinOpExpr expr) {
        expr.getLeftOperand().accept(this);
        expr.getRightOperand().accept(this);
        return null;
    }

    @Override
    public Object visit(UnaryOpExpr expr) {
        expr.getOperand().accept(this);
        return null;
    }

    @Override
    public Object visit(IntLiteral lit) {
        return null;
    }

    @Override
    public Object visit(BoolLiteral lit) {
        return null;
    }

    @Override
    public Object visit(FloatLiteral lit) {
        return null;
    }

    @Override
    public Object visit(VarLocation loc) {
        Atributo var = tablaSimbolos.getAtributo(loc.getId());
        loc.setReference(var);
        return null;
    }

    @Override
    public Object visit(Block bl) {
        tablaSimbolos.pushBloque(new Bloque());
        for (FieldDeclaration fd : bl.getFd()) {
            for (LocationDeclaration ld : fd.getL()) {
                //creo un atributo
                Atributo atributo;
                if (tablaSimbolos.getAtributoSameBlock(ld.getId()) == null) {
                    if (ld.getSize() == null) {
                        atributo = new Atributo(null, fd.getType(), ld.getId());
                    } else {
                        atributo = new Atributo(null, fd.getType(), ld.getId(), ld.getSize().getValue());
                    }
                    tablaSimbolos.setVariableBloque(atributo);
                    ld.setReference(atributo);             
                    
                }
            }
        }
        for (Statement s : bl.getStatements()) {
            s.accept(this);
        }
        tablaSimbolos.popBloque();
        return null;

    }

    @Override
    public Object visit(Body bl) {
        bl.getBlock().accept(this);
        return null;
    }

}
