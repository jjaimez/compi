/* --------------------------Usercode Section------------------------ */
package parser;
import java_cup.runtime.*;

      
%%

/* -----------------Options and Declarations Section----------------- */
%public
%class CTDSLexer

%line
%column
%cup

/*
  Declarations
*/
%{ 
	StringBuilder string = new StringBuilder();  
    /* To create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

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

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

OctDigit          = [0-7]

%state STRING


%%
/* ------------------------Lexical Rules Section---------------------- */

<YYINITIAL> {

//espacios en blanco y comentarios
{WhiteSpace}	{/*skip*/}
{Comment}		{/*skip*/}

//literales
"-2147483648"           { return symbol(sym.INT_LITERAL, new Integer(Integer.MIN_VALUE)); }
{IntLit}		  		{return symbol(sym.INT_LITERAL, new Integer(yytext()));}
{FloatLit}		  		{return symbol(sym.FLOAT_LITERAL, new Float(yytext().substring(0,yylength()-1)));}

//operadores
"+"				{return symbol(sym.PLUS);}
"-"				{return symbol(sym.MINUS);}
"*"                             {return symbol(sym.TIMES);}
"/"                             {return symbol(sym.DIVIDE);}
"%"				{return symbol(sym.MOD);}
"||"				{return symbol(sym.OR);}
"&&"				{return symbol(sym.AND);}
"!"				{return symbol(sym.NOT);}
"="				{return symbol(sym.EQ);}
"!="			{return symbol(sym.NOTEQ);}
"=="			{return symbol(sym.EQEQ);}
"<"				{return symbol(sym.LT);}
">"				{return symbol(sym.GT);}
"<="			{return symbol(sym.LTEQ);}
">="			{return symbol(sym.GTEQ);}
"-="			{return symbol(sym.MINUSEQ);}
"+="			{return symbol(sym.PLUSEQ);}
"++"                           { return symbol(sym.PLUSPLUS); }
"--"                           { return symbol(sym.MINUSMINUS); }

//separadores
";"				{return symbol(sym.SEMI);}
","				{return symbol(sym.COMMA);}
"("             {return symbol(sym.LPAREN);}
")"             {return symbol(sym.RPAREN);}
"{"				{return symbol(sym.LKEY);}
"}"				{return symbol(sym.RKEY);}
"["				{return symbol(sym.LBRACKET);}
"]"				{return symbol(sym.RBRACKET);}

//palabras reservadas
"boolean"		{return symbol(sym.BOOLEAN);}
"break"			{return symbol(sym.BREAK);}
"class"			{return symbol(sym.CLASS);}
"continue"		{return symbol(sym.CONTINUE);}
"else"			{return symbol(sym.ELSE);}
"false"			{return symbol(sym.FALSE, false);}
"float"			{return symbol(sym.FLOAT);}
"for"			{return symbol(sym.FOR);}
"if"			{return symbol(sym.IF);}
"int"			{return symbol(sym.INT);}
"return"		{return symbol(sym.RETURN);}
"true"			{return symbol(sym.TRUE, true);}
"void"			{return symbol(sym.VOID);}
"while"			{return symbol(sym.WHILE);}
"extern"                {return symbol(sym.EXTERN);}

/* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }




//identificadores
{Id}	{return symbol(sym.IDENTIFIER, yytext());}        
} 

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, new String(string)); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }
  
  /* error cases /*
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+1+", column "+yycolumn+1); }

<<EOF>>                          { return symbol(sym.EOF); }
