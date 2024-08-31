package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilerException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}


%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Assig = ":""="
OpenBracket = "("
CloseBracket = ")"
OpenCurly = "{"
CloseCurly = "}"
Semicolon = ";"
Op_men = "<"
Op_may = ">"
OpenSquareBracket = "["
CloseSquareBracket = "]"

Letter = [a-zA-Z]
Digit = [0-9]
DoubleQuote = "\""
WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
StringConstant = {DoubleQuote}({Letter}|{Digit}|{WhiteSpace})+{DoubleQuote}

Comment = "/*"([^\r\n]|"\r"? "\n")*"*/"
%%


/* keywords */
/*while*/
<YYINITIAL> "mientras"  {return symbol(ParserSym.WHILE); }
<YYINITIAL> "si"  {return symbol(ParserSym.IF); }
<YYINITIAL> "sino"  {return symbol(ParserSym.ELSE); }
<YYINITIAL> "init"  {return symbol(ParserSym.INIT); }
<YYINITIAL> "AND"  {return symbol(ParserSym.AND); }
<YYINITIAL> "OR"  {return symbol(ParserSym.OR); }
<YYINITIAL> "NOT"  {return symbol(ParserSym.NOT); }
<YYINITIAL> "Float"  {return symbol(ParserSym.FLOAT); }
<YYINITIAL> "Int"  {return symbol(ParserSym.INT); }
<YYINITIAL> "String"  {return symbol(ParserSym.STRING); }
<YYINITIAL> "Leer"  {return symbol(ParserSym.READ); }
<YYINITIAL> "escribir"  {return symbol(ParserSym.WRITE); }


<YYINITIAL> {
  /* identifiers */
  {Identifier}                              {   
												//System.out.println("Token: " + yytext() + " | Tipo: ID"); return symbol(ParserSym.ID, yytext()); 
												if (yytext().length() > 50) {
													throw new InvalidLengthException("Identifier too long: " + yytext());
												  }
												  return symbol(ParserSym.ID, yytext()); 
											}
  /* Constants */
  {IntegerConstant}                         {   //System.out.println("Token: " + yytext() + " | Tipo: CONST_ENT"); return symbol(ParserSym.CONST_ENT, yytext()); 
													try {
													Integer.parseInt(yytext());
												  } catch (NumberFormatException e) {
													throw new InvalidIntegerException("Invalid integer constant: " + yytext());
												  }
												  return symbol(ParserSym.CONST_ENT, yytext());
											}
  {StringConstant} 							{   //System.out.println("Token: " + yytext() + " | Tipo: CONST_ENT"); return symbol(ParserSym.STRING_CONSTANT, yytext()); 
												if (yytext().length() > 50) {
													throw new InvalidLengthException("String constant too long: " + yytext());
												  }
												  return symbol(ParserSym.STRING_CONSTANT, yytext());
											}
  /* operators */
  {Plus}                                    { System.out.println("Token: " + yytext() + " | Tipo: OP_MAS"); return symbol(ParserSym.OP_MAS, yytext()); }
  {Sub}                                     {  System.out.println("Token: " + yytext() + " | Tipo: OP_RES"); return symbol(ParserSym.OP_RES, yytext()); }
  {Mult}                                    {  System.out.println("Token: " + yytext() + " | Tipo: OP_MULT"); return symbol(ParserSym.OP_MULT, yytext());}
  {Div}                                     {  System.out.println("Token: " + yytext() + " | Tipo: OP_DIV"); return symbol(ParserSym.OP_DIV, yytext()); }
  {Assig}                                   { System.out.println("Token: " + yytext() + " | Tipo: OP_ASIG"); return symbol(ParserSym.OP_ASIG, yytext()); }
  {OpenBracket}                             {  System.out.println("Token: " + yytext() + " | Tipo: PAR_A"); return symbol(ParserSym.PAR_A, yytext()); }
  {CloseBracket}                            { System.out.println("Token: " + yytext() + " | Tipo: PAR_C"); return symbol(ParserSym.PAR_C, yytext()); }
  {OpenCurly}                             	{ System.out.println("Token: " + yytext() + " | Tipo: LLAVE_A"); return symbol(ParserSym.LLAVE_A, yytext()); }
  {CloseCurly}                            	{  System.out.println("Token: " + yytext() + " | Tipo: LLAVE_C"); return symbol(ParserSym.LLAVE_C, yytext());}
  {OpenSquareBracket}   					{ System.out.println("Token: " + yytext() + " | Tipo: OPEN_SQUARE"); return symbol(ParserSym.OPEN_SQUARE, yytext()); }
  {CloseSquareBracket}  					{ System.out.println("Token: " + yytext() + " | Tipo: CLOSE_SQUARE"); return symbol(ParserSym.CLOSE_SQUARE, yytext()); }
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
  {Comment} 				     { /* ignore */ }
  
  {Op_men}                            		{ System.out.println("Token: " + yytext() + " | Tipo: OP_MEN"); return symbol(ParserSym.OP_MEN, yytext()); }
  {Op_may}                            		{ System.out.println("Token: " + yytext() + " | Tipo: OP_MAY"); return symbol(ParserSym.OP_MAY, yytext()); }  
  
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
