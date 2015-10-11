
import ir.TablaDeSimbolos.Atributo;
import ir.TablaDeSimbolos.Clase;
import ir.TablaDeSimbolos.Metodo;
import ir.semcheck.BreakContinueVisitor;
import ir.semcheck.InterpreterVisitor;
import ir.semcheck.PrintVisitor;
import ir.semcheck.TypeEvaluationVisitor;
import parser.parser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

public class main {

    public static void main(String[] args) throws Exception, RuntimeException {
        /*  for (int i = 1; i < 2; i++) {
         System.out.println("test" + i + ".ctds");
         BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/test" + i + ".ctds"));
         Lexer lex = new Lexer(br);
         parser p = new parser(lex);
         p.parse();
         PrintVisitor pv = new PrintVisitor();
         pv.visit(p.getAST());
         System.out.println("--------------------------");
         TypeEvaluationVisitor tev = new TypeEvaluationVisitor();
         tev.visit(p.getAST());
         HashMap<String, Clase> c = tev.getTablaSimbolos().getClases();
         LinkedList<Atributo> atr = c.get("Main").getAtributos();
         if (atr != null) {
         for (Atributo a : atr) {
         System.out.println(a.getTipo() + " " + a.getNombre());
         }
         }
         LinkedList<Metodo> met=c.get("Main").getMetodos();
         for(Metodo m : met){
         System.out.println(m.getTipoReturn().toString()+" "+m.getNombre());

         }
            
         BreakContinueVisitor bcv = new BreakContinueVisitor();
         bcv.visit(p.getAST());
         }*/

        for (int i = 1; i < 6; i++) {
            System.out.println("test" + i + ".ctds");
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/test" + i + ".ctds"));
            Lexer lex = new Lexer(br);
            parser p = new parser(lex);
            p.parse();
            TypeEvaluationVisitor tev = new TypeEvaluationVisitor();
            tev.visit(p.getAST());
            BreakContinueVisitor bcv = new BreakContinueVisitor();
            bcv.visit(p.getAST());
            InterpreterVisitor iv= new InterpreterVisitor();
            iv.visit(p.getAST());
            System.out.println("--------------------------");
        }

        
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
