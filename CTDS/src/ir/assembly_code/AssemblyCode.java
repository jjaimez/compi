/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.assembly_code;

import ir.TablaDeSimbolos.Atributo;
import ir.ast.IntLiteral;
import ir.ast.Literal;
import ir.ast.LocationDeclaration;
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
        System.out.println("\n\n\n *****GENERANDO CÓDIGO ASSEMBLY*****\n");
        for (String s : generateAssembly()) {
            System.out.println(s);
        }

    }

    public LinkedList<String> generateAssembly() {
        Iterator<Command> it = commands.iterator();
        //recorro toda la lista de codigo intermedio
        for (Command c : commands) {
            switch (c.getOp()) {
                case GDEF://variables globales
                    gdef(c);
                    break;
                case LBL://definicion 
                    lbl(c);
                    break;
                case PARAM:
                    //VER QUE HACEMOS ACAAAA
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
                    //mul(c);
                    break;
                case DIV:
                    //div(c);
                    break;
                case MOD:
                    //mod(c);
                    break;
                case MIN:
                    min(c);
                    break;
                case AND:
                    //and(c);
                    break;
                case OR:
                    //or(c);
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
                    //ret(c);
                    break;
                case CMP:
                    cmp(c);
                    break;
                case JNE:
                    codeAssembly.add("      jne " + c.getP1().toString());
                    break;
                case JE:
                    codeAssembly.add("      je " + c.getP1().toString());
                    break;
                case JMP:
                    codeAssembly.add(" jmp " + c.getP1().toString());
                    break;
                case INC:
                    inc(c);
                    break;
                case CALL:
                    //call(c);
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
        Atributo atr1 = (Atributo) ((VarLocation) c.getP1()).getReference();
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
            Atributo atr1 = (Atributo) ((VarLocation) c.getP1()).getReference();
            Atributo result = (Atributo) ((VarLocation) c.getP3()).getReference();
            if (c.getP2() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  addl $" + c.getP2().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //el segundo parametro es una variable y el primero un int ej: 1+x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            Atributo atr2 = (Atributo) ((VarLocation) c.getP2()).getReference();
            Atributo result = (Atributo) ((VarLocation) c.getP3()).getReference();
            if (c.getP1() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax");
                codeAssembly.add("  addl $" + c.getP1().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //los dos son variables, ej : x+x
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            Atributo atr1 = (Atributo) ((VarLocation) c.getP1()).getReference();
            Atributo atr2 = (Atributo) ((VarLocation) c.getP1()).getReference();
            Atributo result = (Atributo) ((VarLocation) c.getP3()).getReference();
            if (c.getP1() instanceof IntLiteral) { //si el primero es int, el sgundo tambien es int
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %edx");
                codeAssembly.add("  addl %edx, %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //los dos son literales, ej: 5+5 o 1.1+2.2
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            if (c.getP1() instanceof IntLiteral) { //si el primero es int, el sgundo tambien es int
                Atributo result = (Atributo) ((VarLocation) c.getP3()).getReference();
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
            Atributo atr1 = (Atributo) ((VarLocation) c.getP1()).getReference();
            Atributo result = (Atributo) ((VarLocation) c.getP3()).getReference();
            if (c.getP2() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  subl $" + c.getP2().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //el segundo parametro es una variable y el primero un int ej: 1-x
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            Atributo atr2 = (Atributo) ((VarLocation) c.getP2()).getReference();
            Atributo result = (Atributo) ((VarLocation) c.getP3()).getReference();
            if (c.getP1() instanceof IntLiteral) {
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %eax");
                codeAssembly.add("  subl $" + c.getP1().toString() + ", %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //los dos son variables, ej : x-x
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            Atributo atr1 = (Atributo) ((VarLocation) c.getP1()).getReference();
            Atributo atr2 = (Atributo) ((VarLocation) c.getP1()).getReference();
            Atributo result = (Atributo) ((VarLocation) c.getP3()).getReference();
            if (c.getP1() instanceof IntLiteral) { //si el primero es int, el sgundo tambien es int
                codeAssembly.add("  movl " + calculateOffset(atr1) + ", %eax");
                codeAssembly.add("  movl " + calculateOffset(atr2) + ", %edx");
                codeAssembly.add("  subl %edx, %eax");
                codeAssembly.add("  movl " + " %eax, " + calculateOffset(result));
            }
        }
        //los dos son literales, ej: 5-5 o 1.1-2.2
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            if (c.getP1() instanceof IntLiteral) { //si el primero es int, el sgundo tambien es int
                Atributo result = (Atributo) ((VarLocation) c.getP3()).getReference();
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
    private String calculateOffset(Atributo atr) {
        if (atr.getOffset() == 0) {//tenemos que ver si lo hacemos con 0 o como para ver las globales
            return atr.getNombre();
        }
        if (atr.getTamanio() == 0) {// NO es un arreglo
            return atr.getOffset() + "(%ebp)";
        }

        return "";
    }

    /**
     * Asignacion
     *
     * @param c
     */
    public void str(Command c) {
        VarLocation res = (VarLocation) c.getP1();
        String offset = calculateOffset((Atributo) res.getReference());
        if ((c.getP2() instanceof VarLocation)) {
            VarLocation loc = (VarLocation) c.getP2();
            codeAssembly.add("      movl " + calculateOffset((Atributo) loc.getReference()) + ", %eax");
            codeAssembly.add("      movl %eax, " + offset);
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
            codeAssembly.add("      movl " + calculateOffset((Atributo) loc.getReference()) + ", %eax");
        } else {
            codeAssembly.add("      movl " + c.getP1().toString() + ", %eax");
        }
        codeAssembly.add("      cmp $1, %eax");
        codeAssembly.add("      je  .true" + labelTrue);
        codeAssembly.add("      movl $1, %eax");
        codeAssembly.add("      jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("      movl $0, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        VarLocation res = (VarLocation) c.getP2();
        codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));
    }

    /**
     * minus -
     *
     * @param c
     */
    public void min(Command c) {
        if (c.getP1() instanceof VarLocation) {
            VarLocation loc = (VarLocation) c.getP1();
            codeAssembly.add("      movl " + calculateOffset((Atributo) loc.getReference()) + ", %eax");
            codeAssembly.add("      not  %eax");
            VarLocation res = (VarLocation) c.getP2();
            codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));
        } else {
            codeAssembly.add("      movl " + c.getP1().toString() + ", %eax");
            codeAssembly.add("      not  %eax");
            VarLocation res = (VarLocation) c.getP2();
            codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));
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
            codeAssembly.add("      movl " + calculateOffset((Atributo) loc.getReference()) + ", %eax");
            codeAssembly.add("      cmp $" + c.getP2().toString() + ", %eax");
        }
        if ((c.getP2() instanceof VarLocation) && (c.getP1() instanceof Literal)) {
            VarLocation loc = (VarLocation) c.getP2();
            codeAssembly.add("      movl $" + c.getP1().toString() + ", %eax");
            codeAssembly.add("      cmp " + calculateOffset((Atributo) loc.getReference()) + ", %eax");
        }
        if ((c.getP1() instanceof Literal) && (c.getP2() instanceof Literal)) {
            codeAssembly.add("      movl $" + c.getP1().toString() + ", %eax"); //muevo un literal a un registro
            codeAssembly.add("      cmp $" + c.getP2().toString() + ", %eax");
        }
        if ((c.getP1() instanceof VarLocation) && (c.getP2() instanceof VarLocation)) {
            VarLocation loc = (VarLocation) c.getP1();
            VarLocation loc2 = (VarLocation) c.getP2();
            codeAssembly.add("      movl " + calculateOffset((Atributo) loc.getReference()) + ", %eax");
            //muevo el primer operando al registro eax
            codeAssembly.add("      cmp " + calculateOffset((Atributo) loc2.getReference()) + ", %eax"); //sumo los dos registros
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
        codeAssembly.add("      je  .true" + labelTrue);
        codeAssembly.add("      movl $0, %eax");
        codeAssembly.add("      jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("      movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));
    }

    /**
     * != para dos operandos
     *
     * @param c
     */
    private void noteq(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("      jne  .true" + labelTrue);
        codeAssembly.add("      movl $0, %eax");
        codeAssembly.add("      jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("      movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));
    }

    /**
     * >= para dos operandos
     *
     * @param c
     */
    private void gteq(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("      jge  .true" + labelTrue);
        codeAssembly.add("      movl $0, %eax");
        codeAssembly.add("      jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("      movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));
    }

    /**
     * > para dos operandos
     *
     * @param c
     */
    private void gt(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("      jg  .true" + labelTrue);
        codeAssembly.add("      movl $0, %eax");
        codeAssembly.add("      jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("      movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));
    }

    /**
     * <= para dos operandos 
     * @param c
     *
     *
     */
    private void lteq(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("      jle  .true" + labelTrue);
        codeAssembly.add("      movl $0, %eax");
        codeAssembly.add("      jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("      movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));

    }

    /**
     * < para dos operandos 
     * @param c
     *
     *
     */
    private void lt(Command c) {
        cmp(c);
        VarLocation res = (VarLocation) c.getP3();
        codeAssembly.add("      jl  .true" + labelTrue);
        codeAssembly.add("      movl $0, %eax");
        codeAssembly.add("      jmp  .endtrue" + labelTrue);
        codeAssembly.add(".true" + labelTrue + ":");
        codeAssembly.add("      movl $1, %eax");
        codeAssembly.add(".endtrue" + labelTrue + ":");
        labelTrue++;
        codeAssembly.add("      movl " + " %eax, " + calculateOffset((Atributo) res.getReference()));
    }

}
