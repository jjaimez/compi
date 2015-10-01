
import ir.semcheck.PrintVisitor;
import ir.semcheck.TypeEvaluationVisitor;
import parser.parser;
import java.io.BufferedReader;
import java.io.FileReader;

public class main {

    public static void main(String[] args) throws Exception, RuntimeException {
        for (int i = 1; i < 22; i++) {
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
        }
        /*  System.out.println("<<<<<<<<<< Comienzan los test negativos >>>>>>>>>>");
         for(int i =1; i< 14; i++){
         System.out.println("test"+i+ ".ctds" );
         BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/negativos/test"+i+".ctds"));
         Lexer lex = new Lexer(br);
         parser p = new parser(lex);
         p.parse();
         System.out.println("--------------------------");

         }*/

        /*   BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/test15.ctds"));
         Lexer lex = new Lexer(br);
         parser p = new parser(lex);
         p.parse();
         PrintVisitor pv = new PrintVisitor();
         pv.visit(p.getAST());*/
    }
}
