/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Visitante que imprime el arbol ast.
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
import ir.ast.UnaryOpExpr;
import ir.ast.VarLocation;
import ir.ast.WhileStmt;

/**
 *
 * @author jacinto
 */
public class PrintVisitor implements ASTVisitor<String> {

    @Override
    public String visit(Parameter p) {

        return p.toString();
    }

    @Override
    public String visit(Method m) {
        return m.toString();
    }

    @Override
    public String visit(LocationDeclaration ld) {
        return ld.toString();
    }

    @Override
    public String visit(FieldDeclaration fd) {
        return fd.toString();
    }

    @Override
    public String visit(Declaration d) {
        return d.toString();
    }

    @Override
    public String visit(ClassDeclaration cd) {
        return cd.toString();
    }

    @Override
    public String visit(Program p) {
        System.out.print(p.toString());
        return "";
    }

    @Override
    public String visit(AssignStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(ReturnStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(IfStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(BreakStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(ContinueStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(WhileStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(MethodCall stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(ExternStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(ForStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(MethodCallStmt stmt) {
        return stmt.toString();
    }

    @Override
    public String visit(BinOpExpr expr) {
        return expr.toString();
    }

    @Override
    public String visit(UnaryOpExpr expr) {
        return expr.toString();
    }

    @Override
    public String visit(IntLiteral lit) {
        return lit.toString();
    }

    @Override
    public String visit(BoolLiteral lit) {
        return lit.toString();
    }

    @Override
    public String visit(FloatLiteral lit) {
        return lit.toString();
    }

    @Override
    public String visit(VarLocation loc) {
        System.out.println("1231231");
        return loc.toString();
    }

    @Override
    public String visit(Block bl) {
        return bl.toString();
    }

    @Override
    public String visit(Body bl) {
        return bl.toString();
    }
}
