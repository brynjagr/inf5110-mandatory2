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
%%
<YYINITIAL>{
        {WhiteSpace}                    {}
        "class"                         { return symbol(sym.CLASS); }
        "{"			        			{ return symbol(sym.LBRACK); }
        "}"								{ return symbol(sym.RBRACK); }
        "("                             { return symbol(sym.LPAR); }
        ")"                             { return symbol(sym.RPAR); }
        ";"                             { return symbol(sym.SEMI); }
		","                             { return symbol(sym.COMMA); }
        "var"                           { return symbol(sym.VAR); }
        "int"                           { return symbol(sym.INT); }
		"float"							{ return symbol(sym.FLOAT); }
		"string"						{ return symbol(sym.STRING); }
		"bool"							{ return symbol(sym.BOOL); }
		"proc"                          { return symbol(sym.PROC); }
        {Identifier}                    { return symbol(sym.ID,yytext()); }
        }

.     		                { throw new Error("Illegal character '" + yytext() + "' at line " + yyline + ", column " + yycolumn + "."); }
