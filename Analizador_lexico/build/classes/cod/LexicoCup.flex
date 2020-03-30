package cod;
import java_cup.runtime.Symbol;
%%
%class Lexer_cup
%type java_cup.runtime.Symbol
%unicode
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
point="."
colom=\,

%{
    private Symbol symbol(int type,Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }
    private Symbol symbol(int type){
        return new Symbol(type,yyline,yycolumn);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Comentarios multilinea */
( ("/*")(.|"\n"|espacio)*("*/")) {/*Ignore*/}



/* Salto de linea */
( "\n" ) { return new Symbol(sym.Linea,yychar,yyline,yytext());}


/* main*/
( "main" ) { return new Symbol(sym.Main,yychar,yyline,yytext());}
("\(") { return new Symbol(sym.Parentesis_a,yychar,yyline,yytext());}
("\)") { return new Symbol(sym.Parentesis_c,yychar,yyline,yytext());}

("\{") { return new Symbol(sym.Llave_a,yychar,yyline,yytext());}
("\}") { return new Symbol(sym.Llave_c,yychar,yyline,yytext());}
("\[") { return new Symbol(sym.Corchete_a,yychar,yyline,yytext());}
("\]") { return new Symbol(sym.Corchete_c,yychar,yyline,yytext());}
{colom} { return new Symbol(sym.Coma,yychar,yyline,yytext());}
{point} { return new Symbol(sym.Punto,yychar,yyline,yytext());}
(":") { return new Symbol(sym.Dos_puntos,yychar,yyline,yytext());}
(";") { return new Symbol(sym.Punto_coma,yychar,yyline,yytext());}
("#") { return new Symbol(sym.Numeral,yychar,yyline,yytext());}

("int") { return new Symbol(sym.Int,yychar,yyline,yytext());}
("double") { return new Symbol(sym.Double,yychar,yyline,yytext());}
("float") { return new Symbol(sym.Float,yychar,yyline,yytext());}
("long") { return new Symbol(sym.Long,yychar,yyline,yytext());}
("short") { return new Symbol(sym.Short,yychar,yyline,yytext());}
("void") { return new Symbol(sym.Void,yychar,yyline,yytext());}
("unsigned") { return new Symbol(sym.Unsi,yychar,yyline,yytext());}
("signed") { return new Symbol(sym.Signed,yychar,yyline,yytext());}
("enum") { return new Symbol(sym.Enum,yychar,yyline,yytext());}
("boolean") { return new Symbol(sym.Boolean,yychar,yyline,yytext());}
("char")  { return new Symbol(sym.Char,yychar,yyline,yytext());}
("String") { return new Symbol(sym.string,yychar,yyline,yytext());}
("union") { return new Symbol(sym.Union,yychar,yyline,yytext());}
("struct") { return new Symbol(sym.Struct,yychar,yyline,yytext());}

("if") { return new Symbol(sym.If,yychar,yyline,yytext());}
("else") { return new Symbol(sym.Else,yychar,yyline,yytext());}
("do") { return new Symbol(sym.Do,yychar,yyline,yytext());}
("while") { return new Symbol(sym.While,yychar,yyline,yytext());}
("switch") { return new Symbol(sym.Switch,yychar,yyline,yytext());}
("case") { return new Symbol(sym.Case,yychar,yyline,yytext());}
("default") { return new Symbol(sym.Default,yychar,yyline,yytext());}
("for") { return new Symbol(sym.For,yychar,yyline,yytext());}
("break") { return new Symbol(sym.Break,yychar,yyline,yytext());}
("goto") { return new Symbol(sym.Goto,yychar,yyline,yytext());}

( "=" ) { return new Symbol(sym.Igual,yychar,yyline,yytext());}
( "+" ) { return new Symbol(sym.Suma,yychar,yyline,yytext());}
( "-" ) { return new Symbol(sym.Resta,yychar,yyline,yytext());}
( "*" ) { return new Symbol(sym.Multiplicacion,yychar,yyline,yytext());}
( "/" ) { return new Symbol(sym.Division,yychar,yyline,yytext());}
( "%" ) { return new Symbol(sym.Modulo,yychar,yyline,yytext());}

( "&&" ) { return new Symbol(sym.Op_y,yychar,yyline,yytext());}
( "||" ) { return new Symbol(sym.Op_o,yychar,yyline,yytext());}
( "!"  ) { return new Symbol(sym.Op_negacion,yychar,yyline,yytext());}
(  "^" ) { return new Symbol(sym.Op_xor,yychar,yyline,yytext());}

(">") { return new Symbol(sym.Mayor_que,yychar,yyline,yytext());}
("<") { return new Symbol(sym.Menor_que,yychar,yyline,yytext());}
("==") { return new Symbol(sym.Igual_que,yychar,yyline,yytext());}
("!=") { return new Symbol(sym.Diferente_que,yychar,yyline,yytext());}
(">=") { return new Symbol(sym.Mayor_o_igual,yychar,yyline,yytext());}
( "<=") { return new Symbol(sym.Menor_o_igual,yychar,yyline,yytext());}

( "&"   ) { return new Symbol(sym.Y_bit,yychar,yyline,yytext());}
( "|"  ) { return new Symbol(sym.O_bit,yychar,yyline,yytext());}

( "+="  ) { return new Symbol(sym.Suma_igual,yychar,yyline,yytext());}
(  "-=" ) { return new Symbol(sym.Resta_igual,yychar,yyline,yytext());}
(  "*="  ) { return new Symbol(sym.Mul_igual,yychar,yyline,yytext());}
(  "/="  ) { return new Symbol(sym.Div_igual,yychar,yyline,yytext());}
(  "%=" ) { return new Symbol(sym.Mod_igual,yychar,yyline,yytext());}

( "++") { return new Symbol(sym.Incremento,yychar,yyline,yytext());}
( "--" ) { return new Symbol(sym.Decremento,yychar,yyline,yytext());}

(true ) { return new Symbol(sym.True,yychar,yyline,yytext());}
(false)  { return new Symbol(sym.False,yychar,yyline,yytext());}


( "include" ) { return new Symbol(sym.Include,yychar,yyline,yytext());}
( "define" ) { return new Symbol(sym.Define,yychar,yyline,yytext());}
("NULL")    { return new Symbol(sym.Nul,yychar,yyline,yytext());}
("->")    { return new Symbol(sym.Asi_apu,yychar,yyline,yytext());}
{L}({L}|{D})* { return new Symbol(sym.Identificador,yychar,yyline,yytext());}
{L}(({L}|{D}|("/"))*(".h"){1})+ { return new Symbol(sym.Libreria,yychar,yyline,yytext());}
(-{D}+)|{D}+ { return new Symbol(sym.Numero,yychar,yyline,yytext());}
(-{D}+("."){D}+")")|{D}+("."){D}+ { return new Symbol(sym.Real,yychar,yyline,yytext());}

( "\"" )(.| "(.")*( "\"" ) { return new Symbol(sym.Str,yychar,yyline,yytext());}
( "'"(.|\n)"'")  { return new Symbol(sym.Ch,yychar,yyline,yytext());}
 