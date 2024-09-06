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
Assig = ":="
OpenBracket = "("
CloseBracket = ")"
OpenCurly = "{"
CloseCurly = "}"
CorcheteAbre = "["
CorcheteCierra = "]"
Semicolon = ";"
Op_men = "<"
Op_may = ">"
Coma = ","
DosPuntos = ":"

Letter = [a-zA-Z]
Digit = [0-9]
DoubleQuote = "\""
Arroba = "@"
Percent = "%"
WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = [-]?{Digit}+
StringConstant = {DoubleQuote}({Letter}|{Digit}|{WhiteSpace}|{Arroba}|{Percent})+{DoubleQuote}
FloatConstants = {Digit}+[.]{Digit}+ | [.]{Digit}+ | {Digit}+[.]
Comment = "*-"([^\r\n]|"\r"? "\n")*"-*"
SingleLineComment = "//"{InputCharacter}*

%%


/* keywords */
/*while*/
<YYINITIAL> "mientras"  {System.out.println("Token: " + yytext() + " | Tipo: MIENTRAS"); return symbol(ParserSym.MIENTRAS); }
<YYINITIAL> "si"  		{System.out.println("Token: " + yytext() + " | Tipo: SI"); return symbol(ParserSym.SI); }
<YYINITIAL> "sino"  	{System.out.println("Token: " + yytext() + " | Tipo: SINO");return symbol(ParserSym.SINO); }
<YYINITIAL> "init"  	{System.out.println("Token: " + yytext() + " | Tipo: INIT"); return symbol(ParserSym.INIT); }
<YYINITIAL> "AND"  		{System.out.println("Token: " + yytext() + " | Tipo: AND"); return symbol(ParserSym.AND); }
<YYINITIAL> "OR"  		{System.out.println("Token: " + yytext() + " | Tipo: OR"); return symbol(ParserSym.OR); }
<YYINITIAL> "NOT"  		{System.out.println("Token: " + yytext() + " | Tipo: NOT"); return symbol(ParserSym.NOT); }
<YYINITIAL> "Float"  	{System.out.println("Token: " + yytext() + " | Tipo: FLOAT"); return symbol(ParserSym.FLOAT); }
<YYINITIAL> "Int"  		{System.out.println("Token: " + yytext() + " | Tipo: INT"); return symbol(ParserSym.INT); }
<YYINITIAL> "String"  	{System.out.println("Token: " + yytext() + " | Tipo: STRING"); return symbol(ParserSym.STRING); }
<YYINITIAL> "leer" 		{System.out.println("Token: " + yytext() + " | Tipo: READ"); return symbol(ParserSym.READ); }
<YYINITIAL> "escribir"  {System.out.println("Token: " + yytext() + " | Tipo: WRITE"); return symbol(ParserSym.WRITE); }
<YYINITIAL> "getPenultimatePosition"  {System.out.println("Token: " + yytext() + " | Tipo: GETPENULTIMATEPOSITION"); return symbol(ParserSym.GETPENULTIMATEPOSITION); }
<YYINITIAL> "triangulo"  {System.out.println("Token: " + yytext() + " | Tipo: TRIANGULO"); return symbol(ParserSym.TRIANGULO); }


<YYINITIAL> {
  /* identifiers */
  {Identifier}                              {   
												
												if (yytext().length() > 50) {
													throw new InvalidLengthException("Identifier too long: " + yytext());
												  }
												  System.out.println("Token: " + yytext() + " | Tipo: ID"); 
												  return symbol(ParserSym.ID, yytext()); 
											}
  /* Constants */
  {IntegerConstant}                         {							
												try {
													Integer.parseInt(yytext());
												  } catch (NumberFormatException e) {
													throw new InvalidIntegerException("Invalid integer constant: " + yytext());
												  }
												  System.out.println("Token: " + yytext() + " | Tipo: CONST_ENT");
												  return symbol(ParserSym.CONST_ENT, yytext());
											}
											
  {FloatConstants}                         {
												  System.out.println("Token: " + yytext() + " | Tipo: CONST_FLT");
												  return symbol(ParserSym.CONST_FLT, yytext());
											}
  {StringConstant} 							{    
												if (yytext().length() > 50) {
													throw new InvalidLengthException("String constant too long: " + yytext());
												  }
												  System.out.println("Token: " + yytext() + " | Tipo: CONST_STR");
												  return symbol(ParserSym.CONST_STR, yytext());
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
  {CorcheteAbre}                            {  System.out.println("Token: " + yytext() + " | Tipo: COR_A"); return symbol(ParserSym.COR_A, yytext());}
  {CorcheteCierra}                          {  System.out.println("Token: " + yytext() + " | Tipo: COR_C"); return symbol(ParserSym.COR_C, yytext());}

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
  {Comment} 				     { /* ignore */ }
  {SingleLineComment}            { /* ignore single line comment */ }
  {Op_men}                            		{ System.out.println("Token: " + yytext() + " | Tipo: OP_MEN"); return symbol(ParserSym.OP_MEN, yytext()); }
  {Op_may}                            		{ System.out.println("Token: " + yytext() + " | Tipo: OP_MAY"); return symbol(ParserSym.OP_MAY, yytext()); }  
  {Semicolon}                            	{ System.out.println("Token: " + yytext() + " | Tipo: PYC"); return symbol(ParserSym.PYC, yytext()); } 
  {Coma}                            	{ System.out.println("Token: " + yytext() + " | Tipo: COMA"); return symbol(ParserSym.COMA, yytext()); } 
  {DosPuntos}                            	{ System.out.println("Token: " + yytext() + " | Tipo: DOS_PUNTOS"); return symbol(ParserSym.DOS_PUNTOS, yytext()); } 
  
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
