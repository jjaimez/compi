/*
 * Autores: Jaimez Jacinto, Pereyra Nicolas
 * Proyecto: CompiladorCTDS
 * definicion de los tokens
 */


import java_cup.runtime.*;

%%
%public
%class Lexer
%line
%column
%cup
/*
  Macro Declarations
  
  These declarations are regular expressions that will be used latter
  in the Lexical Rules Section.  
*/

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
InputCharacter = [^\r\n]

/*identificadores*/
Id = [A-Za-z_][A-Za-z_0-9]*

/*literales*/
IntLit = 0 | [1-9][0-9]*
FloatLit = ({FLit1}|{FLit2}|{FLit3})

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 

/* comentarios */
Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?


%%/* ------------------------Lexical Rules Section---------------------- */

<YYINITIAL> {

//espacios en blanco y comentarios
{WhiteSpace}    {/*skip*/}
{Comment}       {/*skip*/}

//literales
{IntLit}            {
                        //System.out.println("INT_LITERAL" +new Integer(yytext())); 
                        return  new Symbol(sym.INT_LITERAL, yyline, yycolumn, new Integer(yytext()));
                    }
{FloatLit}          {
                        //System.out.println("FLOAT_LITERAL" + new Float(yytext().substring(0,yylength()-1))); 
                        return  new Symbol(sym.FLOAT_LITERAL, yyline, yycolumn, new Float(yytext().substring(0,yylength()-1)));
                    }

//operadores
"+"             {
                    //System.out.println("PLUS");
                    return  new Symbol(sym.PLUS, yyline, yycolumn,yytext());
                }
"-"             {
                    //System.out.println("MINUS");
                    return  new Symbol(sym.MINUS, yyline, yycolumn, yytext());
                }
"*"             {
                    //System.out.println("TIMES");
                    return  new Symbol(sym.TIMES, yyline, yycolumn, yytext());
                }
"/"             {
                    //System.out.println("DIVIDE");
                    return  new Symbol(sym.DIVIDE, yyline, yycolumn, yytext());
                }
"%"             {
                    //System.out.println("MOD");
                    return  new Symbol(sym.MOD, yyline, yycolumn, yytext());
                }
"||"            {
                    //System.out.println("OR");
                    return  new Symbol(sym.OR, yyline, yycolumn, yytext());
                }
"&&"            {
                    //System.out.println("AND");
                    return  new Symbol(sym.AND, yyline, yycolumn, yytext());
                }
"!"             {
                    //System.out.println("NOT");
                    return  new Symbol(sym.NOT, yyline, yycolumn, yytext());
                }
"="             {
                    //System.out.println("EQ");
                    return  new Symbol(sym.EQ, yyline, yycolumn, yytext());
                }
"!="            {
                    //System.out.println("NOTEQ");
                    return  new Symbol(sym.NOTEQ, yyline, yycolumn, yytext());
                }
"=="            {
                    //System.out.println("EQEQ");
                    return  new Symbol(sym.EQEQ, yyline, yycolumn, yytext());
                }
"<"             {
                    //System.out.println("LT");
                    return  new Symbol(sym.LT, yyline, yycolumn, yytext());
                }
">"             {
                    //System.out.println("GT");
                    return  new Symbol(sym.GT, yyline, yycolumn, yytext());
                }
"<="            {
                    //System.out.println("LTEQ");
                    return  new Symbol(sym.LTEQ, yyline, yycolumn, yytext());
                }
">="            {
                    //System.out.println("GTEQ");
                    return  new Symbol(sym.GTEQ, yyline, yycolumn, yytext());
                }
"-="            {
                    //System.out.println("MINUSEQ");
                    return  new Symbol(sym.MINUSEQ, yyline, yycolumn, yytext());
                }
"+="            {
                    //System.out.println("MINUSMINUS");
                    return  new Symbol(sym.PLUSEQ, yyline, yycolumn, yytext());
                }
"."            {
                    //System.out.println("DOT");
                    return  new Symbol(sym.DOT, yyline, yycolumn, yytext());
                }

//separadores
";"             {
                    //System.out.println("SEMI");
                    return  new Symbol(sym.SEMI, yyline, yycolumn, yytext());
                }
","             {
                    //System.out.println("COMMA");
                    return  new Symbol(sym.COMMA, yyline, yycolumn, yytext());
                }
"("             {
                    //System.out.println("LPAREN");
                    return  new Symbol(sym.LPAREN, yyline, yycolumn, yytext());
                }
")"             {
                    //System.out.println("RPAREN");
                    return  new Symbol(sym.RPAREN, yyline, yycolumn, yytext());
                }
"{"             {
                    //System.out.println("LKEY");
                    return  new Symbol(sym.LKEY, yyline, yycolumn, yytext());
                }
"}"             {
                    //System.out.println("RKEY");
                    return  new Symbol(sym.RKEY, yyline, yycolumn, yytext());
                }
"["             {
                    //System.out.println("LBRACKET");
                    return  new Symbol(sym.LBRACKET, yyline, yycolumn, yytext());
                }
"]"             {
                    //System.out.println("RBRACKET");
                    return  new Symbol(sym.RBRACKET, yyline, yycolumn, yytext());
                }


//palabras reservadas
"boolean"       {
                    //System.out.println("BOOLEAN");
                    return  new Symbol(sym.TYPE_BOOLEAN, yyline, yycolumn, yytext());
                }
"break"         {
                    //System.out.println("BREAK");
                    return  new Symbol(sym.BREAK, yyline, yycolumn, yytext());
                }
"class"         {
                    //System.out.println("CLASS");
                    return  new Symbol(sym.CLASS, yyline, yycolumn, yytext());
                }
"continue"      {
                    //System.out.println("CONTINUE");
                    return  new Symbol(sym.CONTINUE, yyline, yycolumn, yytext());
                }
"else"          {
                    //System.out.println("ELSE");
                    return  new Symbol(sym.ELSE, yyline, yycolumn, yytext());
                }
"false"         {
                    //System.out.println("FALSE, false");
                    return  new Symbol(sym.FALSE, yyline, yycolumn, yytext());
                }
"float"         {
                    //System.out.println("FLOAT");
                    return  new Symbol(sym.TYPE_FLOAT, yyline, yycolumn, yytext());
                }
"for"           {
                    //System.out.println("FOR");
                    return  new Symbol(sym.FOR, yyline, yycolumn, yytext());
                }
"if"            {
                    //System.out.println("IF");
                    return  new Symbol(sym.IF, yyline, yycolumn, yytext());
                }
"int"           {
                    //System.out.println("INT");
                    return  new Symbol(sym.TYPE_INT, yyline, yycolumn, yytext());
                }
"return"        {
                    //System.out.println("RETURN");
                    return  new Symbol(sym.RETURN, yyline, yycolumn, yytext());
                }
"true"          {
                    //System.out.println("TRUE, true");
                    return  new Symbol(sym.TRUE, yyline, yycolumn, yytext());
                }
"void"          {
                    //System.out.println("VOID");
                    return  new Symbol(sym.VOID, yyline, yycolumn, yytext());
                }
"while"         {
                    //System.out.println("WHILE");
                    return  new Symbol(sym.WHILE, yyline, yycolumn, yytext());
                }
"extern"        {
                    //System.out.println("EXTERN");
                    return  new Symbol(sym.EXTERN, yyline, yycolumn, yytext());
                }


//identificadores
{Id}            {
                    //System.out.println("IDENTIFIER, "+yytext());
                    return  new Symbol(sym.IDENTIFIER, yyline, yycolumn, yytext());
                }        
} 

/* error fallback */
.             { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+" , column "+yycolumn+1); }

