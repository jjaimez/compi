/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.semcheck;

import ir.ASTVisitor;
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
public class BreakContinueVisitor implements ASTVisitor<String> {

    @Override
    public String visit(Parameter p) {
        return "";
    }

    @Override
    public String visit(Method m) {
        return m.getBody().accept(this);
    }

    @Override
    public String visit(LocationDeclaration ld) {
        return "";
    }

    @Override
    public String visit(FieldDeclaration fd) {
        return "";
    }

    @Override
    public String visit(Declaration d) {
        for (Method m : d.getMethodDecl()) {
            String g = m.accept(this);
            if (g.equals("BREAK") || g.equals("CONTINUE")) {
            System.err.println("Errore en el metodo "+ m.getId()+": La sentencia " + g + " debe estar dentro de un ciclo");
            System.exit(1);
        }
        }
        return "";
    }

    @Override
    public String visit(ClassDeclaration cd) {
        if (cd.getDeclarations() != null) {
            cd.getDeclarations().accept(this);
        }
        return "";
    }

    @Override
    public String visit(Program p) {
        for (ClassDeclaration c : p.getClassDeclarations()) {
            c.accept(this);
        }
        return "";
    }

    @Override
    public String visit(AssignStmt stmt) {
        return "";
    }

    @Override
    public String visit(ReturnStmt stmt) {
        return "";
    }

    @Override
    public String visit(IfStmt stmt) {
        String g = stmt.getIfBlock().accept(this);
        if (!g.equals("")) {
            return g;
        }
        String g2 = stmt.getElseBlock().accept(this);
        if (!g2.equals("")) {
            return g2;
        }
        return "";
    }

    @Override
    public String visit(BreakStmt stmt) {
        return "BREAK";
    }

    @Override
    public String visit(ContinueStmt stmt) {
        return "CONTINUE";
    }

    @Override
    public String visit(WhileStmt stmt) {
        stmt.getBlock().accept(this);
        return "";
    }

    @Override
    public String visit(MethodCall stmt) {
        return "";
    }

    @Override
    public String visit(ExternStmt stmt) {
        return "";
    }

    @Override
    public String visit(ForStmt stmt) {
        stmt.getStatement().accept(this);
        return "";
    }

    @Override
    public String visit(MethodCallStmt stmt) {
        return "";
    }

    @Override
    public String visit(BinOpExpr expr) {
        return "";
    }

    @Override
    public String visit(UnaryOpExpr expr) {
        return "";
    }

    @Override
    public String visit(IntLiteral lit) {
        return "";
    }

    @Override
    public String visit(BoolLiteral lit) {
        return "";
    }

    @Override
    public String visit(FloatLiteral lit) {
        return "";
    }

    @Override
    public String visit(VarLocation loc) {
        return "";
    }

    @Override
    public String visit(Block bl) {
        for (Statement s : bl.getStatements()) {
            String g = s.accept(this);
            if (g.equals("BREAK") || g.equals("CONTINUE")) {
                return g;
            }
        }
        return "";
    }

    @Override
    public String visit(Body bl) {
        return bl.getBlock().accept(this);
    }

}
