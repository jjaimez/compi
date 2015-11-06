
/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Main del compilador
 */
import ir.TablaDeSimbolos.Atributo;
import ir.TablaDeSimbolos.Clase;
import ir.TablaDeSimbolos.Metodo;
import ir.assembly_code.AssemblyCode;
import ir.ast.AST;
import ir.ast.Program;
import ir.intCodeGeneration.Command;
import ir.intCodeGeneration.ICGVisitor;
import ir.semcheck.BreakContinueVisitor;
import ir.semcheck.InterpreterVisitor;
import ir.semcheck.PrintVisitor;
import ir.semcheck.SetReferencesVisitor;
import ir.semcheck.TypeEvaluationVisitor;
import parser.parser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;

public class ctds {

    String name;
    String extension = ".s";
    boolean rename = false;

    public static void main(String[] args) throws Exception, RuntimeException {
//        ctds c = new ctds();
//        String path = System.getProperty("user.dir") + args[args.length - 1];
//        String[] aux = path.split("/");
//        c.name = aux[aux.length - 1].replace(".ctds", "");
//        int i = 0;
//        while (i < args.length - 1) {
//            c.commandTerminal(args, i);
//            i += 2;
//        }
//
//        BufferedReader br = new BufferedReader(new FileReader(path));
//        Lexer lex = new Lexer(br);
//        parser p = new parser(lex);
//        p.parse();
//        Program prog = p.getAST();
//        c.typeEvaluator(prog);
//        LinkedList<Command> ic = c.compileICG(prog);
//        if (".s".equals(c.extension)) { //si estoy generando codigo assembly llamo al compilador de C
//            System.out.println("***Generando codigo assembly***");
//            LinkedList<String> codeAsm = c.genAseembly(ic, p);
//            c.writeFile(codeAsm);
//            String s = null;
//            Process process = Runtime.getRuntime().exec("gcc -o " + c.name + " -m32 " + c.name + c.extension);
//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
//                    process.getInputStream()));
//
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(
//                    process.getErrorStream()));
//            // Leemos los errores si los hubiera
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
//        } else { // si no genero assembly, entones genero codigo intermedio
//            System.out.println("***Generando codigo intermedio***");
//            LinkedList commandString = new LinkedList();
//            for (Command com : ic) {
//                commandString.add(com.toString());
//            }
//            c.writeFile(commandString);
//        }
         
        //ESTO ES PARA CORRERLO DESDE NETBEANS
        for (int i = 1; i < 2; i++) {
        System.out.println("test2.ctds");
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/testProfe/testAssembly.ctds"));
        Lexer lex = new Lexer(br);
        parser p = new parser(lex);
        p.parse();
        Program prog = p.getAST();
        
        TypeEvaluationVisitor tev = new TypeEvaluationVisitor();
        tev.visit(prog);
        BreakContinueVisitor bcv = new BreakContinueVisitor();
        bcv.visit(prog);

        SetReferencesVisitor srv = new SetReferencesVisitor();
        srv.visit(prog);
        
        ICGVisitor icgv = new ICGVisitor();
        icgv.visit(prog);
        
        for (Command c : icgv.getCode()) {
            System.out.println(c.toString());
        }
        AssemblyCode genCode= new AssemblyCode(icgv.getCode(), p);
       for (String s:  genCode.generateAssembly()){
            System.out.println(s+"\n");
       }
        
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

    /**
     *
     * @param path ruta de archivo original
     * @param name ruta de archivo de salida
     * @throws FileNotFoundException
     * @throws Exception
     */
    private void writeFile(LinkedList<String> code) throws FileNotFoundException, Exception {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/" + name + extension)));
        for (String comm : code) {
            out.write(comm);
            out.write("\n");
        }
        out.close();
        System.out.println("------------- OK -------------");
    }

    private void typeEvaluator(Program prog) {
        TypeEvaluationVisitor tev = new TypeEvaluationVisitor();
        tev.visit(prog);
        BreakContinueVisitor bcv = new BreakContinueVisitor();
        bcv.visit(prog);
    }

    /**
     * genera codigo assembly
     *
     * @param ic
     * @param p
     * @return
     */
    public LinkedList<String> genAseembly(LinkedList<Command> ic, parser p) {
        AssemblyCode genCode = new AssemblyCode(ic, p);
        return genCode.generateAssembly();
    }

    /**
     * genera codigo intermedio y retorna una linkedlst con el codigo
     *
     * @param prog
     * @return
     */
    private LinkedList<Command> compileICG(Program prog) {
        SetReferencesVisitor srv = new SetReferencesVisitor();
        srv.visit(prog);
        ICGVisitor icgv = new ICGVisitor();
        icgv.visit(prog);
        return icgv.getCode();
    }

    private void commandTerminal(String[] args, int pos) throws Exception {
        String comm = args[pos];

        switch (comm) {
            case "-o":
                //aca va para escribir el ejecutable
                name = args[pos + 1];
                break;
            case "-target":
                String target = args[pos + 1];
                if (target.equals("assembly"));
                extension = ".s";
                if (target.equals("codeinter"));
                extension = ".ci";
                break;
            case "-opt":
                break;
            case "-debug":
                break;
            default:
                System.out.println("error en linea de comandos");
                break;
        }
    }
}
