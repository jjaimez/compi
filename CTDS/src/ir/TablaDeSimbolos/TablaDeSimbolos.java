package ir.TablaDeSimbolos;

/*
 * Autores: Jaimez Jacinto, Pereyra Orcasitas Nicolás
 * Proyecto: CompiladorCTDS
 * Pila para guardar los ambientes de cada bloque del programa.
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class TablaDeSimbolos {

    private HashMap<String, Clase> clases;
    private LinkedList<Bloque> pilaBloque;
    private String ultimaClase;

    public TablaDeSimbolos() {
        clases = new HashMap<>();
        pilaBloque = new LinkedList();
        ultimaClase = "";
    }

    public void pushClase(String nombre, Clase clase) {
        if (clases.containsKey(nombre)) {
            System.err.println("ya existe la clase '" + nombre + "'");
            System.exit(1);
        }
        clases.put(nombre, clase);
        ultimaClase = nombre;
    }

    public boolean existeClase(String nombre) {
        return clases.containsKey(nombre);
    }

    public HashMap<String, Clase> getClases() {
        return clases;
    }

    /**
     * Inserta un atributo a una clase
     *
     * @param atr
     */
    public void insertAtrClase(String nombreClase, Atributo atr) {
        if (existeClase(nombreClase)) {
            Clase c = clases.get(nombreClase);
            c.setAtributo(atr);
            clases.replace(nombreClase, c);
        } else {
            System.out.println("error, no existe la clase '" + nombreClase + "'");
            System.exit(1);
        }
    }

    /**
     * Inserta un atributo a una clase
     *
     * @param atr
     */
    public void insertMetClase(String nombreClase, Metodo met) {
        if (existeClase(nombreClase)) {
            Clase c = clases.get(nombreClase);
            c.setMetodo(met);
            clases.replace(nombreClase, c);
        } else {
            System.out.println("error, no existe la clase '" + nombreClase + "'");
            System.exit(1);
        }
    }

    public Atributo getAtributo(String id) {
        for (int i = pilaBloque.size()-1; i >=0; i--) {
            Atributo atr = pilaBloque.get(i).getAtributo(id);
            if (atr != null) {
                return atr;
            }
        }
        return null;
    }

    public Atributo getAtributoSameBlock(String id) {
        return pilaBloque.getLast().getAtributo(id);
    }

    public Metodo getMetodo(String nombreClase, String nombreMetodo) {
        Clase c = clases.get(nombreClase);
        return c.getMetodo(nombreMetodo);
    }
    
     public Metodo getMetodoHard(String nombreMetodo) {
        Iterator it = clases.entrySet().iterator();
        while (it.hasNext()){
            Clase c = (Clase) ((Entry) it.next()).getValue();
            Metodo m = c.getMetodo(nombreMetodo);
            if (m != null){
                return m;
            }
        }
        return null;
    }


    public void pushBloque(Bloque ambiente) {
        pilaBloque.add(ambiente);
    }

    public Bloque getBloque() {
        return pilaBloque.getLast();
    }

    public void popBloque() {
        pilaBloque.removeLast();
    }

    public boolean isEmptyBloque() {
        return pilaBloque.size() == 0;
    }

    public String getUltimaClase() {
        return ultimaClase;
    }

    public Atributo getVariableBloque(String id) {
        for (int i = pilaBloque.size()-1; i>0; i--) {//arranco de de 1 para evitar rev
            Atributo atr = pilaBloque.get(i).getAtributo(id);
            if (atr != null) {
                return atr;
            }
        }
        return null;
    }

    /**
     * agrega una variable al bloque corriente
     *
     * @param id
     * @return
     */
    public Atributo setVariableBloque(Atributo atr) {
        Bloque b = pilaBloque.getLast();
        b.setAtributo(atr);
        pilaBloque.set(pilaBloque.size() - 1, b);
        return null;
    }

    /**
     * agrega una variable al bloque corriente
     *
     * @param id
     * @return
     */
    public Atributo setVariablesBloque(LinkedList<Atributo> atr) {
        Bloque b = pilaBloque.getLast();
        b.setAtributos(atr);
        pilaBloque.set(pilaBloque.size() - 1, b);
        return null;
    }

}
