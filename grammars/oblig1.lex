package oblig1parser;
import java_cup.runtime.*;
%%

%class Lexer
%unicode
%cup
%line
%column
%public
%{
 StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }

%}
LineTerminator = \r|\n|\r\n
WhiteSpace		= {LineTerminator} | [ \t\f]
Identifier = [:jletter:] [:jletterdigit:]*
IntLiteral = 0 | [1-9][0-9]*
FloatLiteral = {IntLiteral}\.[0-9]*
Comment = \/\/.*
%state STRING
%%
<YYINITIAL>{
        {WhiteSpace}                    {}
        {Comment}                       {}
        "class"                         { return symbol(sym.CLASS); }
        "proc"                          { return symbol(sym.PROC); }
        "var"                           { return symbol(sym.VAR); }
        "float"                         { return symbol(sym.FLOAT); }
        "int"                           { return symbol(sym.INT); }
        "bool"                          { return symbol(sym.BOOL); }
        "string"                        { return symbol(sym.STRING); }
        "true"                          { return symbol(sym.TRUE); }
        "false"                         { return symbol(sym.FALSE); }
        "null"                          { return symbol(sym.NULL); }
        "ret"                           { return symbol(sym.RET); }
        "return"                        { return symbol(sym.RETURN); }
        "new"                           { return symbol(sym.NEW); }
        "if"                            { return symbol(sym.IF); }
        "then"                          { return symbol(sym.THEN); }
        "else"                          { return symbol(sym.ELSE); }
        "while"                         { return symbol(sym.WHILE); }
        "do"                            { return symbol(sym.DO); }
        ","                             { return symbol(sym.COMMA); }
        "."                             { return symbol(sym.DOT); }
        ":="                            { return symbol(sym.ASSIGN); }
        "{"			                    { return symbol(sym.LBRACK); }
        "}"				                { return symbol(sym.RBRACK); }
        "("                             { return symbol(sym.LPAR); }
        ")"                             { return symbol(sym.RPAR); }
        ";"                             { return symbol(sym.SEMI); }
        ">="                            { return symbol(sym.GREATER_EQUAL); }
        "<="                            { return symbol(sym.LESS_EQUAL); }
        "<>"                            { return symbol(sym.NOT_EQUAL); }
	    "<"                             { return symbol(sym.LESS_THAN); }
	    ">"                             { return symbol(sym.GREATER_THAN); }
        "="                             { return symbol(sym.EQUAL); }
        "+"                             { return symbol(sym.PLUS); }
        "-"                             { return symbol(sym.MINUS); }
        "*"                             { return symbol(sym.MULTIPLY); }
        "/"                             { return symbol(sym.DIVIDE); }
        "#"                             { return symbol(sym.EXPONENT); }
        "||"                            { return symbol(sym.OR); }
        "&&"                            { return symbol(sym.AND); }
        "not"                           { return symbol(sym.NOT); }
	    {FloatLiteral}                  { return symbol(sym.FLOAT_LITERAL, yytext()); }
	    {IntLiteral}                    { return symbol(sym.INT_LITERAL, yytext()); }
	    \" { string.setLength(0); yybegin(STRING); }
        {Identifier}                    { return symbol(sym.ID,yytext()); }
}


<STRING> {
        \"                              { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, string.toString()); }
        [^\n\r\"\\]+                    { string.append( yytext() ); }
        \\t                             { string.append('\t'); }
        \\n                             { string.append('\n'); }
        \\r                             { string.append('\r'); }
        \\\"                            { string.append('\"'); }
        \\                              { string.append('\\'); }
}

.     		                { throw new Error("Illegal character '" + yytext() + "' at line " + yyline + ", column " + yycolumn + "."); }


