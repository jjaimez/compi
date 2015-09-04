import java.io.FileReader;

import java.io.*;
public class main {

  public static void main(String[] args) throws IOException, Exception, RuntimeException {
    BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/ejemplo.ctds"));
    try{
       Lexer lex = new Lexer(br);
        parser p = new parser(lex);
        p.parse();
    }
    catch (RuntimeException e) {
      System.err.print(e.toString());
    }        
  }
}