
/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Main del compilador
 */
import ir.TablaDeSimbolos.Atributo;
import ir.TablaDeSimbolos.Clase;
import ir.TablaDeSimbolos.Metodo;
import ir.intCodeGeneration.Command;
import ir.intCodeGeneration.ICGVisitor;
import ir.semcheck.BreakContinueVisitor;
import ir.semcheck.InterpreterVisitor;
import ir.semcheck.PrintVisitor;
import ir.semcheck.SetReferencesVisitor;
import ir.semcheck.TypeEvaluationVisitor;
import parser.parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

public class main {

    public static void main(String[] args) throws Exception, RuntimeException {
        // for (int i = 1; i < 2; i++) {
        System.out.println("test2.ctds");
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/test2.ctds"));
        Lexer lex = new Lexer(br);
        parser p = new parser(lex);
        p.parse();
          //  TypeEvaluationVisitor tev = new TypeEvaluationVisitor();
        //tev.visit(p.getAST());
        //BreakContinueVisitor bcv = new BreakContinueVisitor();
        //bcv.visit(p.getAST());

        SetReferencesVisitor srv = new SetReferencesVisitor();
        srv.visit(p.getAST());
        ICGVisitor icgv = new ICGVisitor();
        icgv.visit(p.getAST());
        for (Command c : icgv.getCode()) {
            System.out.println(c.toString());
        }
        // }

        /*
         BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + args[0]));
         Lexer lex = new Lexer(br);
         parser p = new parser(lex);
         p.parse();
         TypeEvaluationVisitor tev = new TypeEvaluationVisitor();
         tev.visit(p.getAST());
         BreakContinueVisitor bcv = new BreakContinueVisitor();
         bcv.visit(p.getAST());
         ICGVisitor icgv = new ICGVisitor();
         icgv.visit(p.getAST());
         System.out.println("------------- OK -------------");
         */
//         System.out.println("<<<<<<<<<< Comienzan los test negativos >>>>>>>>>>");
//         for (int i = 35; i < 36; i++) {
//         System.out.println("test" + i + ".ctds");
//         BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/negativos/test" + i + ".ctds"));
//         Lexer lex = new Lexer(br);
//         parser p = new parser(lex);
//         p.parse();
//         TypeEvaluationVisitor tev = new TypeEvaluationVisitor();
//         tev.visit(p.getAST());
//         BreakContinueVisitor bcv = new BreakContinueVisitor();
//         bcv.visit(p.getAST());
//         System.out.println("--------------------------");
//         }

        /*   BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/test15.ctds"));
         Lexer lex = new Lexer(br);
         parser p = new parser(lex);
         p.parse();
         PrintVisitor pv = new PrintVisitor();
         pv.visit(p.getAST());*/
    }
}
