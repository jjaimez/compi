/*
/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Definicion de comando de codigo intermedio
 */
package ir.intCodeGeneration;

import ir.ast.Expression;

public class Command {
    private ICGOpType op;
    private Object p1;
    private Object p2;
    private Object p3;

    public Command(ICGOpType op, Object p1, Object p2, Object p3) {
        this.op = op;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public ICGOpType getOp() {
        return op;
    }

    public Object getP1() {
        return p1;
    }

    public Object getP2() {
        return p2;
    }

    public Object getP3() {
        return p3;
    }

    @Override
    public String toString() {
        String res = op.toString();
        if (p1!=null){
            res = res.concat(" "+p1.toString());
            if (p2!=null){
                res = res.concat(" "+p2.toString());
                if (p3!=null){
                    res = res.concat(" "+p3.toString());
                }
            }
        }
        return res;
    }
    
    
}
