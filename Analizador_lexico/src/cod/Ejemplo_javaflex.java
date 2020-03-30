package cod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Ejemplo_javaflex {


    public static void main(String[] args) throws Exception {
      
    //new Ventana();
    String ruta1="../Analizador_lexico/src/cod/Analizador_Lexico.flex";
    String ruta2="../Analizador_lexico/src/cod/LexicoCup.flex";
    String [] rutas={"-parser","sintax","../Analizador_lexico/src/cod/Analizador_Sintactico.cup"};
       
    generar(ruta1,ruta2,rutas);
        
    }
    public static void generar(String ruta1,String ruta2,String []  rutas) throws IOException, Exception{
        File archivo ;
        archivo=new File(ruta1);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutas);
        Path rutaSym= Paths.get("..\\Analizador_lexico\\src\\cod\\sym.java");
        if(Files.exists(rutaSym)){
            Files.delete(rutaSym);
        }
        Path rutaSin= Paths.get("..\\Analizador_lexico\\src\\cod\\sintax.java");
        if(Files.exists(rutaSin)){
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("..\\Analizador_lexico\\sym.java"),
                Paths.get("..\\Analizador_lexico\\src\\cod/sym.java"));
        Files.move(
                Paths.get("..\\Analizador_lexico\\sintax.java"),
                Paths.get("..\\Analizador_lexico\\src\\cod\\sintax.java"));
    }
}
