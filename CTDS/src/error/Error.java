/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Error
 */

package error;


public class Error {
    
    private int ln;
    private int cn;
    private String d;

    public Error(int lineNumber, int columnNumber, String desc) {
        ln=lineNumber;
        cn=columnNumber;
        d=desc;
    }
    
    public void show(){
        System.out.println("Line: "+ln+" Col:"+cn+" "+d);
    }
    
}
