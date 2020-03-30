/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cod;


import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author jairo
 */
public class Ventana extends JFrame implements ActionListener{
    JButton abrir=new JButton("Abrir archivo");
    JTextArea codigo=new JTextArea();
    JScrollPane panel1=new JScrollPane(codigo);
    
    JButton analisis_lex=new JButton("Analisis lexico");
    JButton borrar_lex=new JButton("Borrar");
    JTextArea lexico=new JTextArea();
    JScrollPane panel2=new JScrollPane(lexico);
    
    JButton analisis_sin=new JButton("Analisis sintactico");
    JButton borrar_sin=new JButton("Borrar");
    JTextArea sintaxis=new JTextArea();
    JScrollPane panel3=new JScrollPane(sintaxis);
    
    public Ventana(){
        Container c=getContentPane();
        c.setLayout(null);
        
       c.add(abrir);
       abrir.setBounds(10,10,150,20);
       c.add(panel1);
       panel1.setBounds(10,35,450,300);
       
       c.add(analisis_lex);
       analisis_lex.setBounds(465,10,150,20);
       c.add(borrar_lex);
       borrar_lex.setBounds(615,10,100,20);
       c.add(panel2);
       panel2.setBounds(465,35,450,620);
       
       c.add(analisis_sin);
       analisis_sin.setBounds(10,340,150,20);
       c.add(borrar_sin);
       borrar_sin.setBounds(165,340,100,20);
       c.add(panel3);
       panel3.setBounds(10,365,450,320);
       
       
       this.abrir.addActionListener(this);
       this.abrir.setActionCommand("abrir");
       this.analisis_lex.addActionListener(this);
       this.analisis_lex.setActionCommand("analisis lex");
       this.borrar_lex.addActionListener(this);
       this.borrar_lex.setActionCommand("borrar_lex"); 
       this.analisis_sin.addActionListener(this);
       this.analisis_sin.setActionCommand("analisis sin");
       this.borrar_sin.addActionListener(this);
       this.borrar_sin.setActionCommand("borrrar_sin");
       
       this.setSize(1000,1000);
       this.setLocationRelativeTo(null);
       this.setVisible(true);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   
    public void actionPerformed(ActionEvent ae) {
      //Boto para abrir archivo  
        if(ae.getActionCommand().equals("abrir")){
            int cont =1;
            JFileChooser chooser=new JFileChooser();
            chooser.showOpenDialog(null);
            File arc=new File(chooser.getSelectedFile().getAbsolutePath());
            
            try {
                String st=new String(Files.readAllBytes(arc.toPath()));
                codigo.setText(st);
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    //Boton para borrar analisis lexico y sintactico

        if(ae.getActionCommand().equals("borrar_lex")){
            lexico.setText("");
        }
         if(ae.getActionCommand().equals("borrar_sin")){
            sintaxis.setText("");
        }
    //Boton para el analisis lexico
    
         if(ae.getActionCommand().equals("analisis lex")){
            try {
                analizar_lexico();
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    //Boton para el analisis sintactico
        
        if(ae.getActionCommand().equals("analisis sin")){
            if(!codigo.getText().equals("")){
                String st =codigo.getText();
                sintax s=new sintax(new cod.Lexer_cup(new StringReader(st)));
                try {
                    s.parse();
                    sintaxis.setText("Analisis realizado correctamente");
                    sintaxis.setForeground(new Color(25, 111, 61));
                } catch (Exception ex) {
                    Symbol sym = s.getS();
                    sintaxis.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
                    sintaxis.setForeground(Color.red);
                }
            }
            
        }
    }

    
    
///metodo para el analisis lexico
    private void analizar_lexico() throws IOException{
        String expr=(String)codigo.getText();
            Lexer lexer=new Lexer(new StringReader(expr));
            String resultado="";
            while(true){
               
                 Tokens token=lexer.yylex();
                    if(token==null){
                        lexico.setText(resultado);
                        return;
                    }
                switch (token){
                    case Main:
                        resultado+=lexer.lexeme+" metodo main\n";
                        break;
                    case Parentesis_a:
                        resultado+=lexer.lexeme+" abre parentesis\n";
                        break;
                    case Parentesis_c:
                        resultado+=lexer.lexeme+" cierra parentesis\n"; 
                        
                    case Llave_a:
                        resultado+=lexer.lexeme+" abre llave\n";
                        break;
                    case Llave_c:
                        resultado+=lexer.lexeme+" cierra llave \n";
                        break;
                    case Corchete_a:
                        resultado+=lexer.lexeme+" abre corchete\n";
                        break;
                    case Corchete_c:
                        resultado+=lexer.lexeme+" cierra corchete\n";
                        break; 
                        
                    case Coma:
                        resultado+=lexer.lexeme+" coma\n";
                        break;
                    case Punto:
                        resultado+=lexer.lexeme+" punto\n";
                        break;  
                    case Dos_puntos:
                        resultado+=lexer.lexeme+" dos puntos\n";
                        break;    
                    case Punto_coma:
                        resultado+=lexer.lexeme+" punto y coma\n";
                        break;    
                    case Numeral:
                        resultado+=lexer.lexeme+" numeral\n";
                        break;
                    case Int:
                        resultado+=lexer.lexeme+" int\n";
                        break; 
                    case Double:
                        resultado+=lexer.lexeme+" double\n";
                        break;     
                    case Float:
                        resultado+=lexer.lexeme+" float\n";
                        break;     
                    case Long:
                        resultado+=lexer.lexeme+" long\n";
                        break; 
                    case Short:
                        resultado+=lexer.lexeme+" short\n";
                        break;     
                    case Void:
                        resultado+=lexer.lexeme+" void\n";
                        break; 
                    case Enum:
                        resultado+=lexer.lexeme+" enum\n";
                        break; 
                    case Unsi:
                        resultado+=lexer.lexeme+" unsigned\n";
                        break; 
                     case Struct:
                        resultado+=lexer.lexeme+" struct\n";
                        break;      
                    case If:
                        resultado+=lexer.lexeme+" If\n";
                        break; 
                    case Else:
                        resultado+=lexer.lexeme+" Else\n";
                        break; 
                    case Do:
                        resultado+=lexer.lexeme+" Do\n";
                        break;     
                    case While:
                        resultado+=lexer.lexeme+" while\n";
                        break; 
                    case Switch:
                        resultado+=lexer.lexeme+" switch\n";
                        break; 
                    case Case:
                        resultado+=lexer.lexeme+" case\n";
                        break;     
                    case For:
                        resultado+=lexer.lexeme+" for\n";
                        break; 
                    case Break:
                        resultado+=lexer.lexeme+" break\n";
                        break;     
                   case Goto:
                        resultado+=lexer.lexeme+" goto\n";
                        break;
                    case Igual:
                        resultado+=lexer.lexeme+" igual\n";
                        break; 
                    case Suma:
                        resultado+=lexer.lexeme+" suma\n";
                        break; 
                    case Resta:
                        resultado+=lexer.lexeme+" resta\n";
                        break;    
                    case Multiplicacion:
                        resultado+=lexer.lexeme+" producto\n";
                        break; 
                    case Division:
                        resultado+=lexer.lexeme+" division\n";
                        break; 
                    case Modulo:
                        resultado+=lexer.lexeme+" modulo\n";
                        break;   
                    case Op_y:
                        resultado+=lexer.lexeme+" y\n";
                        break; 
                    case Op_o:
                        resultado+=lexer.lexeme+" o\n";
                        break; 
                    case Op_negacion:
                        resultado+=lexer.lexeme+" negacion\n";
                        break;     
                     case Op_xor:
                        resultado+=lexer.lexeme+" xor\n";
                        break; 
                    case Mayor_que:
                        resultado+=lexer.lexeme+" mayor que\n";
                        break;     
                    case Menor_que:
                        resultado+=lexer.lexeme+" menor que\n";
                        break;     
                    
                    case Igual_que:
                        resultado+=lexer.lexeme+" igual que\n";
                        break; 
                    case Mayor_o_igual:
                        resultado+=lexer.lexeme+" mayor o igual que\n";
                        break; 
                    case Menor_o_igual:
                        resultado+=lexer.lexeme+" menor o igual que\n";
                        break; 
                    case Diferente_que:
                        resultado+=lexer.lexeme+" diferente que\n";
                        break;
                    case Suma_igual:
                        resultado+=lexer.lexeme+" asignacion suma\n";
                        break; 
                    case Resta_igual:
                        resultado+=lexer.lexeme+" asignacion resta\n";
                        break; 
                    case Mul_igual:
                        resultado+=lexer.lexeme+" asignacion multiplicacion\n";
                        break; 
                    case Div_igual:
                        resultado+=lexer.lexeme+" asignacion division\n";
                        break; 
                    case Mod_igual:
                        resultado+=lexer.lexeme+" asignacion modulo\n";
                        break;
                   case Incremento:
                        resultado+=lexer.lexeme+" incrementa\n";
                        break;
                    case Decremento:
                        resultado+=lexer.lexeme+" decrece\n";
                        break;   
                   case True:
                        resultado+=lexer.lexeme+" true\n";
                        break;   
                    case False:
                        resultado+=lexer.lexeme+" false\n";
                        break;   
                    case Include:
                        resultado+=lexer.lexeme+" include\n";
                        break;    
                    case Y_bit:
                        resultado+=lexer.lexeme+" y bit a bit\n";
                        break;    
                    case O_bit:
                        resultado+=lexer.lexeme+" o bit a bit\n";
                        break;
                    case Nul:
                        resultado+=lexer.lexeme+" valor null\n";
                        break;    
                    case Asi_apu:
                        resultado+=lexer.lexeme+" tomar valor de un apuntador de struct\n";
                        break;    
                    case Identificador:
                        resultado+=lexer.lexeme+" identificador\n";
                        break;    
                    case Libreria:
                        resultado+=lexer.lexeme+" libreria\n";
                        break;        
                    case Numero:
                        resultado+=lexer.lexeme+" numero entero\n";
                        break;    
                   case Real:
                        resultado+=lexer.lexeme+" numero real\n";
                        break;    
                    case string:
                        resultado+=lexer.lexeme+" string\n";
                        break;    
                    case Ch:
                        resultado+=lexer.lexeme+" caracter\n";
                        break;    
                    case Str:
                        resultado+=lexer.lexeme+" cadena de caracteres\n";
                        break;    
                    case Error:
                        resultado+=lexer.lexeme+" Error,palabra no permitida\n";
                        break;        
                    default:
                        
                        break;
                }   
                
            }
    }
    
}
