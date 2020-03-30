package cod;
import static cod.Tokens.*;
%%
%class Lexer
%type Tokens
%unicode

L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
point="."
colom=\,
%{
    public String lexeme;
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Comentarios multilinea */
( ("/*")(.|"\n"|espacio)*("*/")) {/*Ignore*/}



/* Salto de linea */
( "\n" ) { lexeme=yytext(); return Linea;}

("main") {lexeme=yytext(); return Main;}

("(") {lexeme=yytext(); return Parentesis_a;}
(")") {lexeme=yytext(); return Parentesis_c;}

("{") {lexeme=yytext(); return Llave_a;}
("}") {lexeme=yytext(); return Llave_c;}

("[") {lexeme=yytext(); return Corchete_a;}
("]") {lexeme=yytext(); return Corchete_c;}
{colom} {lexeme=yytext(); return Coma;}
{point} {lexeme=yytext(); return Punto;}
(":") {lexeme=yytext(); return Dos_puntos;}
(";") {lexeme=yytext(); return Punto_coma;}
("#") {lexeme=yytext(); return Numeral;}

("int") {lexeme=yytext(); return Int;}

("double") {lexeme=yytext(); return Double;}
("float") {lexeme=yytext(); return Float;}
("long") {lexeme=yytext(); return Long;}
("short") {lexeme=yytext(); return Short;}
("void") {lexeme=yytext(); return Void;}
("unsigned") {lexeme=yytext(); return Unsi;}
("signed") {lexeme=yytext(); return Signed;}
("enum") {lexeme=yytext(); return Enum;}
("boolean") {lexeme=yytext(); return Boolean;}
("char") {lexeme=yytext(); return Char;}
("String") {lexeme=yytext(); return string;}
("union") {lexeme=yytext(); return Union;}
("struct") {lexeme=yytext(); return Struct;}

("if") {lexeme=yytext(); return If;}
("else") {lexeme=yytext(); return Else;}
("do") {lexeme=yytext(); return Do;}
("while") {lexeme=yytext(); return While;}
("switch") {lexeme=yytext(); return Switch;}
("case") {lexeme=yytext(); return Case;}
("default") {lexeme=yytext(); return Default;}
("for") {lexeme=yytext(); return For;}
("break") {lexeme=yytext(); return Break;}
("goto") {lexeme=yytext(); return Goto;}


( "=" ) {lexeme=yytext(); return Igual;}
( "+" ) {lexeme=yytext(); return Suma;}
( "-" ) {lexeme=yytext(); return Resta;}
( "*" ) {lexeme=yytext(); return Multiplicacion;}
( "/" ) {lexeme=yytext(); return Division;}
( "%" ) {lexeme=yytext(); return Modulo;}

( "&&" ) {lexeme=yytext(); return Op_y;}
( "||" ) {lexeme=yytext(); return Op_o;}
( "!"  ) {lexeme=yytext(); return Op_negacion;}
(  "^" ) {lexeme=yytext(); return Op_xor;}

(">") {lexeme = yytext(); return Mayor_que;}
("<") {lexeme = yytext(); return Menor_que;}
("==") {lexeme = yytext(); return Igual_que;}
("!=") {lexeme = yytext(); return Diferente_que;}
(">=") {lexeme = yytext(); return Mayor_o_igual;}
( "<=") {lexeme = yytext(); return Menor_o_igual;}


( "&"   ) {lexeme=yytext(); return Y_bit;}
( "|"  ) {lexeme=yytext(); return O_bit;}

( "+="  ) {lexeme = yytext(); return Suma_igual;}
(  "-=" ) {lexeme = yytext(); return Resta_igual;}
(  "*="  ) {lexeme = yytext(); return Mul_igual;}
(  "/="  ) {lexeme = yytext(); return Div_igual;}
(  "%=" ) {lexeme = yytext(); return Mod_igual;}

( "++") {lexeme = yytext(); return Incremento;}
( "--" ) {lexeme = yytext(); return Decremento;}

(true )      {lexeme = yytext(); return True;}
(false)      {lexeme = yytext(); return False;}

( "include" ) {lexeme=yytext(); return Include;}
( "define" ) {lexeme=yytext(); return Define;}
( "NULL" ) {lexeme=yytext(); return Nul;}
("->") {lexeme=yytext(); return Asi_apu;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
{L}(({L}|{D}|("/"))*(".h"){1})+ {lexeme=yytext(); return Libreria;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
("(-"{D}+("."){D}+")")|{D}+("."){D}+ {lexeme=yytext(); return Real;}

( "\"" )(.| "(.")*( "\"" ) {lexeme=yytext(); return Str;}
( '(.|\n)')  {lexeme=yytext(); return Ch;}
 ("\'" )(.| "(."){1,2}( "\'" ) {lexeme=yytext(); return Chr;}
.  {lexeme=yytext(); return Error;}