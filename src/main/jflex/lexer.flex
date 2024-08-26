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
Letter = [a-zA-Z]
Digit = [0-9]
DoubleQuote = "\""
WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
StringConstant = {DoubleQuote}({Letter}|{Digit}|{WhiteSpace})+{DoubleQuote}

GreatThan = >
LessThan = <

CommentStart = "*""-"
CommentEnd = "-""*"

Comment = {CommentStart}({Digit}|{Letter})*{CommentEnd}
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
  {Identifier}                              { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                         { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }
  {StringConstant} 							{ return symbol(ParserSym.STRING_CONSTANT, yytext()); }
  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }
  {OpenCurly}                             	{ return symbol(ParserSym.OPEN_CURLY); }
  {CloseCurly}                            	{ return symbol(ParserSym.CLOSE_CURLY); }
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
  {Comment} 				     { /* ignore */ }
  
  {GreatThan} 								{ return symbol(ParserSym.GREAT_THAN); }
  {LessThan}								{ return symbol(ParserSym.LESS_THAN); }   
  
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
