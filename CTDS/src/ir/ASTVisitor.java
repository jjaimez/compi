/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * interface ast visitor
 */
package ir;

import ir.ast.*;

// Abstract visitor
public interface ASTVisitor<T> {
    
    //declarations
        T visit(Parameter p);
        T visit(Method m);
        T visit(LocationDeclaration ld);
        T visit(FieldDeclaration fd);
        T visit(Declaration d);
        T visit(ClassDeclaration cd);
        T visit(Program p);
// visit statements
	T visit(AssignStmt stmt);
	T visit(ReturnStmt stmt);
	T visit(IfStmt stmt);
        T visit(BreakStmt stmt);
        T visit(ContinueStmt stmt);
        T visit(WhileStmt stmt);
        T visit(MethodCall stmt);
        T visit(ExternStmt stmt);
        T visit(ForStmt stmt);
        T visit(MethodCallStmt stmt);
// visit expressions
	T visit(BinOpExpr expr);
        T visit(UnaryOpExpr expr);
// visit literals	
	T visit(IntLiteral lit);       
        T visit(BoolLiteral lit);
        T visit(FloatLiteral lit);

// visit locations	
	T visit(VarLocation loc);
        
// visit blocks
        T visit(Block bl);
        T visit(Body bl);
}
