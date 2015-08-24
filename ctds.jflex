import java_cup.runtime.*;


%%
/*-*
 * LEXICAL FUNCTIONS:
 */

%cup
%line
%column
%unicode
%class ctdsLexer

%{

/**
 * Return a new Symbol with the given token id, and with the current line and
 * column numbers.
 */
Symbol newSym(int tokenId) {
    return new Symbol(tokenId, yyline, yycolumn);
}

/**
 * Return a new Symbol with the given token id, the current line and column
 * numbers, and the given token value.  The value is used for tokens such as
 * identifiers and numbers.
 */
Symbol newSym(int tokenId, Object value) {
    return new Symbol(tokenId, yyline, yycolumn, value);
}

%}


/*-*
 * PATTERN DEFINITIONS:
 */
letter          = [A-Za-z]
digit           = [0-9]
alphanumeric    = {letter}|{digit}
other_id_char   = [_]
identifier      = {letter}({alphanumeric}|{other_id_char})*
integer         = {digit}*
float            = {integer}\.{integer}
leftbrace       = \{
rightbrace      = \}
nonrightbrace   = [^}]
comment_body    = {nonrightbrace}*
comment         = {leftbrace}{comment_body}{rightbrace}
whitespace      = [ \n\t]
lineTerminator = \r|\n|\r\n
inputCharacter = [^\r\n]
whiteSpace = {lineTerminator} | [ \t\f]
comment = {traditionalComment} | {endOfLineComment}
traditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
endOfLineComment = "//" {inputCharacter}* {lineTerminator}?


%%
/**
 * LEXICAL RULES:
 */

  /* keywords */              
  "boolean"                      { return newSym(sym.BOOLEAN); }
  "break"                        { return newSym(sym.BREAK); }
  "class"                        { return newSym(sym.CLASS); }
  "continue"                     { return newSym(sym.CONTINUE); }
  "else"                         { return newSym(sym.ELSE); }
  "false"                        { return newSym(sym.FALSE); }
  "extern"                       { return newSym(sym.EXTERN); }
  "float"                        { return newSym(sym.FLOAT); }
  "for"                          { return newSym(sym.FOR); }
  "int"                          { return newSym(sym.INT); } 
  "if"                           { return newSym(sym.IF); }
  "true"                         { return newSym(sym.TRUE); }
  "return"                       { return newSym(sym.RETURN); }
  "void"                         { return newSym(sym.VOID); }
  "while"                        { return newSym(sym.WHILE); } 

 
  /* separators */
  "("                            { return newSym(sym.LPAREN); }
  ")"                            { return newSym(sym.RPAREN); }
  "{"                            { return newSym(sym.LBRACE); }
  "}"                            { return newSym(sym.RBRACE); }
  "["                            { return newSym(sym.LBRACK); }
  "]"                            { return newSym(sym.RBRACK); }
  ";"                            { return newSym(sym.SEMICOLON); }
  ","                            { return newSym(sym.COMMA); }
  "."                            { return newSym(sym.DOT); }
  
  /* operators */
  "="                            { return newSym(sym.EQ); }
  ">"                            { return newSym(sym.GT); }
  "<"                            { return newSym(sym.LT); }
  "!"                            { return newSym(sym.NOT); }
  "=="                           { return newSym(sym.EQEQ); }
  "<="                           { return newSym(sym.LTEQ); }
  ">="                           { return newSym(sym.GTEQ); }
  "!="                           { return newSym(sym.NOTEQ); }
  "&&"                           { return newSym(sym.AND); }
  "||"                           { return newSym(sym.OR); }
  "++"                           { return newSym(sym.PLUSPLUS); }
  "--"                           { return newSym(sym.MINUSMINUS); }
  "+"                            { return newSym(sym.PLUS); }
  "-"                            { return newSym(sym.MINUS); }
  "*"                            { return newSym(sym.MULT); }
  "/"                            { return newSym(sym.DIV); }
  "%"                            { return newSym(sym.MOD); }
  "+="                           { return newSym(sym.PLUSEQ); }
  "-="                           { return newSym(sym.MINUSEQ); }

 
  {identifier}    { return newSym(sym.IDENT, yytext()); }
  {integer}       { return newSym(sym.INT, new Integer(yytext())); }
  {float}          { return newSym(sym.FLOAT, new Double(yytext())); }
  {comment}       {  /* ignore */ }
  {whitespace}    { /* ignore */ }
  .               { System.out.println("Illegal char, '" + yytext() +"' line: " + yyline + ", column: " + yychar); }
