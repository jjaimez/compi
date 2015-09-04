/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.IOException;

/**
 *
 * @author nico
 */
public class CompilarCup {

    public static void main(String[] args) throws IOException, Exception, RuntimeException {

        //compilo desde aca cup y jflex
        String opciones[] = new String[5];
        //Seleccionamos la opción de dirección de destino
        opciones[0] = "-destdir";
        //Le damos la dirección
        opciones[1] = System.getProperty("user.dir")+ "/src/parser/";
        //Seleccionamos la opciónde nombre de archivo
        opciones[2] = "-parser";
        //Le damos el nombre que queremos que tenga
        opciones[3] = "parser";
        //Le decimos donde se encuentra el archivo .cup
        opciones[4] =System.getProperty("user.dir")+ "/src/parser/CTDS.cup";
        try {
            java_cup.Main.main(opciones);
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
