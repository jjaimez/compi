
import java.io.BufferedReader;
import java.io.FileReader;




public class main {

    public static void main(String[] args) throws  Exception, RuntimeException {

        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/ejemplo.ctds"));
        Lexer lex = new Lexer(br);
        parser p = new parser(lex);
        p.parse();

    }
}
