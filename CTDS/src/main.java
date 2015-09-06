
import java.io.BufferedReader;
import java.io.FileReader;




public class main {

    public static void main(String[] args) throws  Exception, RuntimeException {
        for(int i =1; i< 23; i++){
            System.out.println("test"+i+ ".ctds" );
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/test"+i+".ctds"));
            Lexer lex = new Lexer(br);
            parser p = new parser(lex);
            p.parse();
            System.out.println("--------------------------");

        }
    }
}
