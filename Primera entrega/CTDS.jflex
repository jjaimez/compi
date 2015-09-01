%%
%public
%class Lexer
%line
%column
%standalone
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
{WhiteSpace}	{/*skip*/}
{Comment}		{/*skip*/}

//literales
{IntLit}		  		{System.out.println("INT_LITERAL" +new Integer(yytext()));}
{FloatLit}		  	{System.out.println("FLOAT_LITERAL" + new Float(yytext().substring(0,yylength()-1)));}

//operadores
"+"				{System.out.println("PLUS");}
"-"				{System.out.println("MINUS");}
"*"       {System.out.println("TIMES");}
"/"       {System.out.println("DIVIDE");}
"%"				{System.out.println("MOD");}
"||"			{System.out.println("OR");}
"&&"			{System.out.println("AND");}
"!"				{System.out.println("NOT");}
"="				{System.out.println("EQ");}
"!="			{System.out.println("NOTEQ");}
"=="			{System.out.println("EQEQ");}
"<"				{System.out.println("LT");}
">"				{System.out.println("GT");}
"<="			{System.out.println("LTEQ");}
">="			{System.out.println("GTEQ");}
"++"     	{ System.out.println("PLUSPLUS"); }
"--"      { System.out.println("MINUSMINUS"); }

//separadores
";"				{System.out.println("SEMI");}
","				{System.out.println("COMMA");}
"("       {System.out.println("LPAREN");}
")"       {System.out.println("RPAREN");}
"{"				{System.out.println("LKEY");}
"}"				{System.out.println("RKEY");}
"["				{System.out.println("LBRACKET");}
"]"				{System.out.println("RBRACKET");}


//palabras reservadas
"boolean"		{System.out.println("BOOLEAN");}
"break"			{System.out.println("BREAK");}
"class"			{System.out.println("CLASS");}
"continue"	{System.out.println("CONTINUE");}
"else"			{System.out.println("ELSE");}
"false"			{System.out.println("FALSE, false");}
"float"			{System.out.println("FLOAT");}
"for"				{System.out.println("FOR");}
"if"				{System.out.println("IF");}
"int"				{System.out.println("INT");}
"return"		{System.out.println("RETURN");}
"true"			{System.out.println("TRUE, true");}
"void"			{System.out.println("VOID");}
"while"			{System.out.println("WHILE");}
"extern"    {System.out.println("EXTERN");}


//identificadores
{Id}	{System.out.println("IDENTIFIER, "+yytext());}        
} 

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+1+", column "+yycolumn+1); }

