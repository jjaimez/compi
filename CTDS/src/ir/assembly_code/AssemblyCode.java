/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.assembly_code;

import ir.TablaDeSimbolos.Atributo;
import ir.ast.Expression;
import ir.ast.IntLiteral;
import ir.ast.Literal;
import ir.ast.LocationDeclaration;
import ir.ast.Method;
import ir.ast.MethodCall;
import ir.ast.VarLocation;
import ir.intCodeGeneration.Command;
import java.util.Iterator;
import java.util.LinkedList;
import parser.parser;
import ir.intCodeGeneration.Pair;

/**
 *
 * @author nico
 */
public class AssemblyCode {

    private LinkedList<Command> commands;
    private int labelTrue = 0;
    LinkedList<String> codeAssembly;
    parser pars;

    public AssemblyCode(LinkedList l, parser pars) {
        this.commands = l;
        this.codeAssembly = new LinkedList();
        this.pars = pars;
    // System.out.println("\n\n\n *****GENERANDO CÓDIGO ASSEMBLY*****\n");
        // for (String s : generateAssembly()) {
        //     System.out.println(s);
        // }

    }

    public LinkedList<String> generateAssembly() {
        Iterator<Command> it = commands.iterator();
        //recorro toda la lista de codigo intermedio
        codeAssembly.add(".globl  main"); //con esto hago que reconozca el main globalmente
        codeAssembly.add(".type main, @function");// asi se puede correr despues de compilar
        for (Command c : commands) {
            switch (c.getOp()) {
                case GDEF://variables globales
                    gdef(c);
                    break;
                case LBL://definicion 
                    lbl(c);
                    break;
                case PARAM://apilar todo empezando por el ultimo y llamar con +4, +8
                    break;
                case ADD:
                    add(c);
                    break;
                case SUB:
                    sub(c);
                    break;
                case STR:
                    str(c);
                    break;
                case MUL:
                    mul(c);
                    break;
                case DIV:
                    div(c);
                    break;
                case MOD:
                    mod(c);
                    break;
                case MIN:
                    min(c);
                    break;
                case AND:
                    and(c);
                    break;
                case OR:
                    or(c);
                    break;
                case NOT:
                    not(c);
                    break;
                case LT:
                    lt(c);
                    break;
                case LTEQ:
                    lteq(c);
                    break;
                case GT:
                    gt(c);
                    break;
                case GTEQ:
                    gteq(c);
                    break;
                case NOTEQ:
                    noteq(c);
                    break;
                case EQEQ:
                    eqeq(c);
                    break;
                case RET:
                    ret(c);
                    break;
                case CMP:
                    cmp(c);
                    break;
                case JNE:
                    codeAssembly.add("  jne " + ((Pair) c.getP1()).snd());
                    break;
                case JE:
                    codeAssembly.add("  je " + ((Pair) c.getP1()).snd());
                    break;
                case JMP:
                    codeAssembly.add(" jmp " + ((Pair) c.getP1()).snd());
                    break;
                case INC:
                    inc(c);
                    break;
                case CALL:// guardar el valor en eax 
                    call(c);
                    break;
                case PLG:
                    plg(c); //prologo
                    break;
            }
        }

        return codeAssembly;
    }

    /**
     * agrega las variables globales al codigo assembly
     *
     * @param c
     */
    public void gdef(Command c) {
        Atributo atr = (Atributo) ((LocationDeclaration) c.getP2()).getReference();
        if (atr.getTamanio() == 0) {
            codeAssembly.add(".comm " + atr.getNombre() + ", 4, 4");
        } else {
            codeAssembly.add(".comm " + atr.getNombre() + ", " + atr.getTamanio() * 4 + ", 4");
        }
    }

    /**
     * Agrego la definicion de una etiqueta
     *
     * @param c
     */
    public void lbl(Command c) {
        codeAssembly.add(((Pair) c.getP1()).snd() + ":");

    }

    /**
     * genera codigo para incrementar un operando
     *
     * @param c
     */
    public void inc(Command c) {
        VarLocation atr1 = ((VarLocation) c.getP1());
        codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
        codeAssembly.add("  addl $1, %eax");
        codeAssembly.add("  movl " + " %eax, " + calculateOffset(atr1));

    }

    /**
     * genera codigo para una suma de 2 operandos
     *
     * @param c
     */
    public void add(Command c) {
    //Tengo que revisar los 4 casos para los operandos
        //primer caso variable + Literal ej: x+1
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof Literal)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation result = ((VarLocation) c.getP3());
            if (c.getP2() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  addl $" + c.getP2().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //el segundo parametro es una variable y el primero un int ej: 1+x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            if (c.getP1() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax");
                codeAssembly.add("  addl $" + c.getP1().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //los dos son variables, ej : x+x
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            if (((Atributo) atr1.getReference()).getTipo().isInt()) { //si el primero es int, el sgundo tambien es int
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %edx");
                codeAssembly.add("  addl %edx, %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //los dos son literales, ej: 5+5 o 1.1+2.2
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            if (c.getP1() instanceof IntLiteral) { //si el primero es int, el sgundo tambien es int
                VarLocation result = ((VarLocation) c.getP3());
                codeAssembly.add("  movl $" + c.getP2().toString() + ", %eax");
                codeAssembly.add("  addl $" + c.getP1().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
    }

    /**
     * genera codigo para una resta de 2 operandos
     *
     * @param c
     */
    public void sub(Command c) {
    //Tengo que revisar los 4 casos para los operandos
        //primer caso variable - Literal ej: x-1
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof Literal)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation result = ((VarLocation) c.getP3());
            if (c.getP2() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  subl $" + c.getP2().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //el segundo parametro es una variable y el primero un int ej: 1-x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            if (c.getP1() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax");
                codeAssembly.add("  subl $" + c.getP1().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //los dos son variables, ej : x-x
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            if (((Atributo) atr1.getReference()).getTipo().isInt()) { //si el primero es int, el sgundo tambien es int
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %edx");
                codeAssembly.add("  subl %edx, %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //los dos son literales, ej: 5-5 o 1.1-2.2
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            if (c.getP1() instanceof IntLiteral) { //si el primero es int, el sgundo tambien es int
                VarLocation result = ((VarLocation) c.getP3());
                codeAssembly.add("  movl $" + c.getP2().toString() + ", %eax");
                codeAssembly.add("  subl $" + c.getP1().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
    }

    /**
     * Retorna el offset con el base pointer dada una varlocation por ejemplo
     * -4(%ebp)
     *
     * @param v
     * @return
     */
    private String calculateOffset(VarLocation vl) {
        Atributo atr = (Atributo) vl.getReference();
        if (vl.getExp() != null) {
            Expression e = vl.getExp();
            if (e instanceof VarLocation) {
                codeAssembly.add("  movl " + calculateOffset((VarLocation) e) + ", %edx");
            } else {
                String pos = vl.getExp().toString();
                codeAssembly.add("  movl $" + pos + ", %edx");
            }
            if (atr.esGlobal()) {
                return atr.getNombre() + "(,%edx,4)";
            } else {
                return atr.getOffset() + "(%ebp,%edx,4)";
            }
        } else {
            if (atr.esGlobal()) {
                return atr.getNombre();
            }
            return atr.getOffset() + "(%ebp)";
        }
    }

    /**
     * Asignacion
     *
     * @param c
     */
    public void str(Command c) {
        VarLocation res = (VarLocation) c.getP1();
        String offset = calculateOffset(res);
        if ((c.getP2() instanceof VarLocation)) {
            VarLocation loc = (VarLocation) c.getP2();
            codeAssembly.add("  movl " + calculateOffset(loc) + ", %eax");
            codeAssembly.add("  movl %eax, " + offset);
        } else {
            codeAssembly.add("  movl $" + c.getP2().toString() + "," + offset);
        }
    }

    /**
     * not para un operando
     *
     * @param c
     */
    public void not(Command c) {
        if (c.getP1() instanceof VarLocation) {
            VarLocation loc = (VarLocation) c.getP1();
            codeAssembly.add("  movl " + calculateOffset(loc) + ", %eax");
        } else {
            codeAssembly.add("  movl " + c.getP1().toString() + ", %eax");
        }
        codeAssembly.add("  cmp $1, %eax");
        codeAssembly.add("  je  .true" + labelTrue);
        codeAssembly.add("  movl $1, %eax");
        codeAssembly.add("  jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("  movl $0, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        VarLocation res = (VarLocation) c.getP2();
        codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
    }

    /**
     * minus -
     *
     * @param c
     */
    public void min(Command c) {
        if (c.getP1() instanceof VarLocation) {
            VarLocation loc = (VarLocation) c.getP1();
            codeAssembly.add("  movl " + calculateOffset(loc) + ", %eax");
            codeAssembly.add("  not  %eax");
            VarLocation res = (VarLocation) c.getP2();
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
        } else {
            codeAssembly.add("  movl " + c.getP1().toString() + ", %eax");
            codeAssembly.add("  not  %eax");
            VarLocation res = (VarLocation) c.getP2();
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
        }
    }

    /**
     * compara 2 operando
     *
     * @param c
     */
    public void cmp(Command c) {
        boolean opFloat = false;
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof Literal)) {
            VarLocation loc = (VarLocation) c.getP1();
            codeAssembly.add("  movl " + calculateOffset((loc)) + ", %eax");
            codeAssembly.add("  cmp $" + c.getP2().toString() + ", %eax");
        }
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation loc = (VarLocation) c.getP2();
            codeAssembly.add("  movl $" + c.getP1().toString() + ", %eax");
            codeAssembly.add("  cmp " + calculateOffset(loc) + ", %eax");
        }
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            codeAssembly.add("  movl $" + c.getP1().toString() + ", %eax"); //muevo un literal a un registro
            codeAssembly.add("  cmp $" + c.getP2().toString() + ", %eax");
        }
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            VarLocation loc = (VarLocation) c.getP1();
            VarLocation loc2 = (VarLocation) c.getP2();
            codeAssembly.add("  movl " + calculateOffset(loc) + ", %eax");
            //muevo el primer operando al registro eax
            codeAssembly.add("  cmp " + calculateOffset(loc2) + ", %eax"); //sumo los dos registros
        }
    }

    /**
     * == para dos operandos
     *
     * @param c
     */
    public void eqeq(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("  je  .true" + labelTrue);
        codeAssembly.add("  movl $0, %eax");
        codeAssembly.add("  jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("  movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
    }

    /**
     * != para dos operandos
     *
     * @param c
     */
    private void noteq(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("  jne  .true" + labelTrue);
        codeAssembly.add("  movl $0, %eax");
        codeAssembly.add("  jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("  movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
    }

    /**
     * >= para dos operandos
     *
     * @param c
     */
    private void gteq(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("  jge  .true" + labelTrue);
        codeAssembly.add("  movl $0, %eax");
        codeAssembly.add("  jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("  movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
    }

    /**
     * > para dos operandos
     *
     * @param c
     */
    private void gt(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("  jg  .true" + labelTrue);
        codeAssembly.add("  movl $0, %eax");
        codeAssembly.add("  jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("  movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
    }

    /**
     * <= para dos operandos @param c
     *
     *
     *
     */
    private void lteq(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("  jle  .true" + labelTrue);
        codeAssembly.add("  movl $0, %eax");
        codeAssembly.add("  jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("  movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));

    }

    /**
     * < para dos operandos @param c
     *
     *
     *
     */
    private void lt(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("  jl  .true" + labelTrue);
        codeAssembly.add("  movl $0, %eax");
        codeAssembly.add("  jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("  movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
    }

    /**
     * CASOS DEL CODIGO INTERMEDIO MUL VAR VAR T1 MUL 1 VAR T1 MUL VAR 1 T1 MUL
     * 1 1 T1
     *
     * @param c
     */
    public void mul(Command c) {
        //caso en que el primero es una varariable y la segunda un literal, ej x*1
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof Literal)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation result = ((VarLocation) c.getP3());
            if (c.getP2() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  imull $" + c.getP2().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //caso 1*x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            if (c.getP1() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax");
                codeAssembly.add("  imull $" + c.getP1().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //caso 1*1
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            if (c.getP2() instanceof IntLiteral) {//si el primero es un int, el segundo tambien
                VarLocation result = ((VarLocation) c.getP3());
                codeAssembly.add("  movl $" + c.getP1().toString() + ", %eax");
                codeAssembly.add("  imull $" + c.getP2().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //caso x*x
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            if (((Atributo) atr1.getReference()).getTipo().isInt()) {
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %edx");
                codeAssembly.add("  imull %edx, %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }

    }

    public void div(Command c) {
        //caso en que el primero es una varariable y la segunda un literal, ej x*1
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof Literal)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation result = ((VarLocation) c.getP3());
            if (c.getP2() instanceof IntLiteral) {
                //the DIV BX instruction divides the 32-bit value in DX:AX by BX
                codeAssembly.add("  movl $0, %edx");// muevo un 0 a edx
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax"); //muevo el valor de la variable a eax 
                codeAssembly.add("  movl $" + c.getP2().toString() + ", %ecx"); //muevo el literal a ecx
                codeAssembly.add("  cltd"); //converts signed long to signed double long
                codeAssembly.add("  idivl %ecx"); //divide the contents of EDX:EAX by the contents of ECX
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result)); //el cociente esta en eax
            }
        }
        //caso 1*x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            if (c.getP1() instanceof IntLiteral) {
                codeAssembly.add("  movl $0, %edx");// muevo un 0 a edx
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax"); //muevo el valor de la variable a eax 
                codeAssembly.add("  movl $" + c.getP1().toString() + ", %ecx"); //muevo el literal a ecx
                codeAssembly.add("  cltd"); //converts signed long to signed double long
                codeAssembly.add("  idivl %ecx"); //divide the contents of EDX:EAX by the contents of ECX
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result)); //el cociente esta en eax
            }
        }
        //caso 1*1
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            if (c.getP2() instanceof IntLiteral) {//si el primero es un int, el segundo tambien
                VarLocation result = ((VarLocation) c.getP3());
                codeAssembly.add("  movl $0, %edx");// muevo un 0 a edx
                codeAssembly.add("  movl " + c.getP1().toString() + ", %eax"); //muevo el valor de la variable a eax 
                codeAssembly.add("  movl $" + c.getP2().toString() + ", %ecx"); //muevo el literal a ecx
                codeAssembly.add("  cltd"); //converts signed long to signed double long
                codeAssembly.add("  idivl %ecx"); //divide the contents of EDX:EAX by the contents of ECX
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result)); //el cociente esta en eax
            }
            //caso x*x
            if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
                VarLocation atr1 = ((VarLocation) c.getP1());
                VarLocation atr2 = ((VarLocation) c.getP2());
                VarLocation result = ((VarLocation) c.getP3());
                if (((Atributo) atr1.getReference()).getTipo().isInt()) {
                    codeAssembly.add("  movl $0, %edx");// muevo un 0 a edx
                    codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax"); //muevo el valor de la variable a eax 
                    codeAssembly.add("  movl $" + calculateOffset(atr2) + ", %ecx"); //muevo el literal a ecx
                    codeAssembly.add("  cltd"); //converts signed long to signed double long
                    codeAssembly.add("  idivl %ecx"); //divide the contents of EDX:EAX by the contents of ECX
                    codeAssembly.add("  movl " + " %eax, " + calculateOffset(result)); //el cociente esta en eax
                }
            }
        }
    }

    public void mod(Command c) {
    //es igual que la division pero el resto se guarda en EDX. EDX:EAX = resto:cociente
        //caso en que el primero es una varariable y la segunda un literal, ej x mod 1
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof Literal)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation result = ((VarLocation) c.getP3());
            //the DIV BX instruction divides the 32-bit value in DX:AX by BX
            codeAssembly.add("  movl $0, %edx");// muevo un 0 a edx
            codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax"); //muevo el valor de la variable a eax 
            codeAssembly.add("  movl $" + c.getP2().toString() + ", %ecx"); //muevo el literal a ecx
            codeAssembly.add("  cltd"); //converts signed long to signed double long
            codeAssembly.add("  idivl %ecx"); //divide the contents of EDX:EAX by the contents of ECX
            codeAssembly.add("  movl " + " %edx, " + calculateOffset(result)); //el resto esta en eax

        }
        //caso 1 mod x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl $0, %edx");// muevo un 0 a edx
            codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax"); //muevo el valor de la variable a eax 
            codeAssembly.add("  movl $" + c.getP1().toString() + ", %ecx"); //muevo el literal a ecx
            codeAssembly.add("  cltd"); //converts signed long to signed double long
            codeAssembly.add("  idivl %ecx"); //divide the contents of EDX:EAX by the contents of ECX
            codeAssembly.add("  movl " + " %edx, " + calculateOffset(result)); //el cociente esta en eax
        }
        //caso 1 mod 1
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl $0, %edx");// muevo un 0 a edx
            codeAssembly.add("  movl " + c.getP1().toString() + ", %eax"); //muevo el valor de la variable a eax 
            codeAssembly.add("  movl $" + c.getP2().toString() + ", %ecx"); //muevo el literal a ecx
            codeAssembly.add("  cltd"); //converts signed long to signed double long
            codeAssembly.add("  idivl %ecx"); //divide the contents of EDX:EAX by the contents of ECX
            codeAssembly.add("  movl " + " %edx, " + calculateOffset(result)); //el cociente esta en eax
        }
        //caso x mod x
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl $0, %edx");// muevo un 0 a edx
            codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax"); //muevo el valor de la variable a eax 
            codeAssembly.add("  movl $" + calculateOffset(atr2) + ", %ecx"); //muevo el literal a ecx
            codeAssembly.add("  cltd"); //converts signed long to signed double long
            codeAssembly.add("  idivl %ecx"); //divide the contents of EDX:EAX by the contents of ECX
            codeAssembly.add("  movl " + " %edx, " + calculateOffset(result)); //el cociente esta en eax
        }
    }

    public void and(Command c) {
        //caso en que el primero es una varariable y la segunda un literal, ej x AND 1
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof Literal)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
            codeAssembly.add("  and $" + c.getP2().toString() + ", %eax");
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
        }
        //caso 1 and x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax");
            codeAssembly.add("  and $" + c.getP1().toString() + ", %eax");
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
        }
        //caso 1 and 1
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl " + c.getP1().toString() + ", %eax");
            codeAssembly.add("  and $" + c.getP2().toString() + ", %eax");
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
        }
        //caso x and x
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
            codeAssembly.add("  and $" + calculateOffset(atr2) + ", %eax");
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
        }
    }

    public void or(Command c) {
        //caso en que el primero es una varariable y la segunda un literal, ej x or 1
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof Literal)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
            codeAssembly.add("  or $" + c.getP2().toString() + ", %eax");
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
        }
        //caso 1 or x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax");
            codeAssembly.add("  or $" + c.getP1().toString() + ", %eax");
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
        }
        //caso 1 or 1
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl " + c.getP1().toString() + ", %eax");
            codeAssembly.add("  or $" + c.getP2().toString() + ", %eax");
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
        }
        //caso x or x
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            VarLocation atr1 = ((VarLocation) c.getP1());
            VarLocation atr2 = ((VarLocation) c.getP2());
            VarLocation result = ((VarLocation) c.getP3());
            codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
            codeAssembly.add("  or $" + calculateOffset(atr2) + ", %eax");
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
        }
    }

    public void ret(Command c) {
        if (c.getP1() != null) {
            if (c.getP1() instanceof VarLocation) {
                codeAssembly.add("  movl " + calculateOffset(((VarLocation) c.getP1())) + ", %eax");
            }
            if (c.getP1() instanceof IntLiteral) {
                codeAssembly.add("  movl $" + c.getP1().toString() + ", %eax");
            }
        }
        codeAssembly.add("  leave");
        codeAssembly.add("  ret");
        codeAssembly.add("");
    }

    public void call(Command c) {
        MethodCall e = (MethodCall) c.getP1();
        int i = 0;
        for (Expression ex : e.getExpressions()) {
            if (ex instanceof VarLocation) {
                VarLocation param = (VarLocation) ex;
                codeAssembly.add("  pushl " + calculateOffset(param));
            } else {
                Literal param = (Literal) ex;
                codeAssembly.add("  pushl $" + param.toString());
            }
            i = i + 4;
        }
        codeAssembly.add("  call " + e.getId());
        if (c.getP2() != null) {
            VarLocation res = (VarLocation) c.getP2();
            codeAssembly.add("  movl " + " %eax, " + calculateOffset(res));
        }
    }

    private void plg(Command c) {
        String nombre = ((Method) c.getP1()).getId().toString();
        codeAssembly.add(nombre + ":");
        codeAssembly.add("  pushl %ebp");
        codeAssembly.add("  movl %esp, %ebp");
        codeAssembly.add("  subl $" + -((Method) c.getP1()).getOffset() + ",%esp");
    }

}
