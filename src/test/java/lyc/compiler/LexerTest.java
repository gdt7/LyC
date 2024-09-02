package lyc.compiler;

import lyc.compiler.factories.LexerFactory;
import lyc.compiler.model.CompilerException;
import lyc.compiler.model.InvalidIntegerException;
import lyc.compiler.model.InvalidLengthException;
import lyc.compiler.model.UnknownCharacterException;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;
import static lyc.compiler.constants.Constants.MAX_LENGTH;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LexerTest {

  private Lexer lexer;


  @Test
  public void comment() throws Exception{
    scan("*-This is a ccascascac34234234omment-*");
    assertThat(nextToken()).isEqualTo(ParserSym.EOF);
    System.out.println("---------------------------Este es el resultado del test Comment");
  }

  @Test
  public void invalidStringConstantLength() {
    assertThrows(InvalidLengthException.class, () -> {
      scan("\"%s\"".formatted(getRandomString()));
      nextToken();
    });
    System.out.println("---------------------------Este es el resultado del test invalidStringConstantLength");
  }

  @Test
  public void invalidIdLength() {
    assertThrows(InvalidLengthException.class, () -> {
      scan(getRandomString());
      nextToken();
    });
    System.out.println("---------------------------Este es el resultado del test invalidIdLength");
  }

  @Test
  public void invalidPositiveIntegerConstantValue() {
    assertThrows(InvalidIntegerException.class, () -> {
      scan("%d".formatted(9223372036854775807L));
      nextToken();
    });
    System.out.println("---------------------------Este es el resultado del test invalidPositiveIntegerConstantValue");
  }

 @Test
  public void invalidNegativeIntegerConstantValue() {
    assertThrows(InvalidIntegerException.class, () -> {
      scan("%d".formatted(-9223372036854775807L));
      nextToken();
    });
    System.out.println("---------------------------Este es el resultado del test invalidNegativeIntegerConstantValue");
  }


  //@Test
  public void assignmentWithExpressions() throws Exception {
    //scan("c:=d*(e-21)/4");
    scan("cd");

    int token = nextToken(); // Obtener el primer token
    while (token != ParserSym.EOF) {
        System.out.println("-----------TOKEN: " + token);
        token = nextToken(); // Obtener el siguiente token
    }

    assertThat(nextToken()).isEqualTo(ParserSym.ID);
    /*assertThat(nextToken()).isEqualTo(ParserSym.OP_ASIG);
    assertThat(nextToken()).isEqualTo(ParserSym.ID);
    assertThat(nextToken()).isEqualTo(ParserSym.OP_MULT);
    assertThat(nextToken()).isEqualTo(ParserSym.PAR_A);
    assertThat(nextToken()).isEqualTo(ParserSym.ID);
   // assertThat(nextToken()).isEqualTo(ParserSym.OP_RES);
    assertThat(nextToken()).isEqualTo(ParserSym.CONST_ENT);
    assertThat(nextToken()).isEqualTo(ParserSym.PAR_C);
    assertThat(nextToken()).isEqualTo(ParserSym.OP_DIV);
    assertThat(nextToken()).isEqualTo(ParserSym.CONST_ENT);
    assertThat(nextToken()).isEqualTo(ParserSym.EOF);*/
    System.out.println("---------------------------Este es el resultado del test assignmentWithExpressions");
  }

  //@Test
  public void unknownCharacter() {
    assertThrows(UnknownCharacterException.class, () -> {
      scan("#");
      nextToken();
    });
  }

  //@Test
  public void whileImpl() throws Exception {
	  scan("mientras ( a>b)");
	  assertThat(nextToken()).isEqualTo(ParserSym.WHILE);
	  assertThat(nextToken()).isEqualTo(ParserSym.PAR_A);
	  assertThat(nextToken()).isEqualTo(ParserSym.ID);
	  assertThat(nextToken()).isEqualTo(ParserSym.OP_MAY);
	  
  }
  
  //@Test
  public void whileImpl3() throws Exception {
	  scan("mientras ( a>b)");
	  assertThat(nextToken()).isEqualTo(ParserSym.WHILE);
	  assertThat(nextToken()).isEqualTo(ParserSym.PAR_A);
	  assertThat(nextToken()).isEqualTo(ParserSym.ID);
	  assertThat(nextToken()).isEqualTo(ParserSym.OP_MAY);
	  
  }
  
  //@Test
  public void whileImpl2() throws Exception {
	  scan("mientras ( a>b)");
	  assertThat(nextToken()).isEqualTo(ParserSym.WHILE);
	  assertThat(nextToken()).isEqualTo(ParserSym.PAR_A);
	  assertThat(nextToken()).isEqualTo(ParserSym.ID);
	  assertThat(nextToken()).isEqualTo(ParserSym.OP_MAY);
	  
  }
  
  //@Test
  public void ifElseImpl() throws Exception {
	  scan("si(a>b){ escribir(\"aesmasgrandequeb\")}sino{escribir(\"a es mas chico o igual a b\")}");
	  assertThat(nextToken()).isEqualTo(ParserSym.IF);
	  assertThat(nextToken()).isEqualTo(ParserSym.PAR_A);
	  assertThat(nextToken()).isEqualTo(ParserSym.ID);
	  assertThat(nextToken()).isEqualTo(ParserSym.OP_MAY);
	  assertThat(nextToken()).isEqualTo(ParserSym.ID);
	  assertThat(nextToken()).isEqualTo(ParserSym.PAR_C);
	  assertThat(nextToken()).isEqualTo(ParserSym.LLAVE_A);
	  assertThat(nextToken()).isEqualTo(ParserSym.WRITE);
	  assertThat(nextToken()).isEqualTo(ParserSym.PAR_A);
	  assertThat(nextToken()).isEqualTo(ParserSym.STRING_CONSTANT);
	  assertThat(nextToken()).isEqualTo(ParserSym.PAR_C);
	  assertThat(nextToken()).isEqualTo(ParserSym.LLAVE_C);
  }
  
  //@Test
  public void ifImpl() throws Exception {
	  scan("si ( a>b)");
	  assertThat(nextToken()).isEqualTo(ParserSym.IF);
	  assertThat(nextToken()).isEqualTo(ParserSym.PAR_A);
	  assertThat(nextToken()).isEqualTo(ParserSym.ID);
	  assertThat(nextToken()).isEqualTo(ParserSym.OP_MAY);
	  
  }
  
  
  @AfterEach
  public void resetLexer() {
    lexer = null;
  }

  private void scan(String input) {
    lexer = LexerFactory.create(input);
  }

  private int nextToken() throws IOException, CompilerException {
    return lexer.next_token().sym;
  }

  private static String getRandomString() {
    return new RandomStringGenerator.Builder()
            .filteredBy(CharacterPredicates.LETTERS)
            .withinRange('a', 'z')
            .build().generate(MAX_LENGTH * 2);
  }

}
