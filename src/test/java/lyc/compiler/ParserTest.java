package lyc.compiler;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java_cup.runtime.Symbol;
import lyc.compiler.factories.ParserFactory;

//@Disabled
public class ParserTest {

    //@Test
    public void assignmentWithExpression() throws Exception {
    	System.out.println("---------------------------Inicio test: assignmentWithExpression");
    	Parser parser = createParser("c:=d*(e - 21)/4");
    	compilationSuccessful(parser);
//    	assertAll(
////    			() -> assertEquals(parser.symbolList.get(0).getNombre(),"c")
//    			);
        System.out.println("---------------------------Fin test: assignmentWithExpression");
    }

    //@Test
    public void syntaxError() {
    	System.out.println("---------------------------Inicio test: syntaxError");
    	Parser parser = createParser("1234");
        compilationError(parser);
        System.out.println("---------------------------Fin test: syntaxError");
    }

    //@Test
    void assignments() throws Exception {
    	System.out.println("---------------------------Inicio test: assignments");
    	Parser parser = createParser(readFromFile("assignments.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: assignments");
    }

    //@Test
    void write() throws Exception {
    	System.out.println("---------------------------Inicio test: write");
    	Parser parser = createParser(readFromFile("write.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: write");
        
    }

    //@Test
    void read() throws Exception {
    	System.out.println("---------------------------Inicio test: read");
    	Parser parser = createParser(readFromFile("read.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: read");
    }

    //@Test
    void comment() throws Exception {
    	System.out.println("---------------------------Inicio test: comment");
    	Parser parser = createParser(readFromFile("comment.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: comment");
    }

    //@Test
    void init() throws Exception {
    	System.out.println("---------------------------Inicio test: init");
    	Parser parser = createParser(readFromFile("init.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: init");
    }

    //@Test
    void and() throws Exception {
    	System.out.println("---------------------------Inicio test: and");
    	Parser parser = createParser(readFromFile("and.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: and");
    }

    //@Test
    void or() throws Exception {
    	System.out.println("---------------------------Inicio test: or");
    	Parser parser = createParser(readFromFile("or.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: or");
    }

    //@Test
    void not() throws Exception {
    	System.out.println("---------------------------Inicio test: not");
    	Parser parser = createParser(readFromFile("not.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: not");
    }

    //@Test
    void ifStatement() throws Exception {
    	System.out.println("---------------------------Inicio test: ifStatement");
    	Parser parser = createParser(readFromFile("if.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: ifStatement");
    }

    //@Test
    void whileStatement() throws Exception {
    	System.out.println("---------------------------Inicio test: whileStatement");
    	Parser parser = createParser(readFromFile("while.txt"));
    	compilationSuccessful(parser);
        System.out.println("---------------------------Fin test: whileStatement");
    }


    //@Test
    void funcionGetPenultimatePosition() throws Exception {
        compilationSuccessful(readFromFile("getPenultimatePosition.txt"));
    }

//    @Test
    void funcionTriangulo() throws Exception {
        compilationSuccessful(readFromFile("triangulo.txt"));
    }

    private void compilationSuccessful(String input) throws Exception {
        assertThat(scan(input).sym).isEqualTo(ParserSym.EOF);
    }
    
    private void compilationSuccessful(Parser parser) throws Exception { 	
    	assertThat(parser.parse().sym).isEqualTo(ParserSym.EOF); 
    }
    
    
    private void compilationError(Parser parser){
        assertThrows(Exception.class, () -> parser.parse());
    }
    
    private void compilationError(String input){
        assertThrows(Exception.class, () -> scan(input));
    }
    
    
    private static Parser createParser(String input) {
    	Parser parser = ParserFactory.create(input);
    	parser.symbolTable = new ArrayList<>();
    	return parser;
    }
    
    private Symbol scan(String input) throws Exception {
    	Parser parser = ParserFactory.create(input);
    	parser.symbolTable = new ArrayList<>();
    	return parser.parse();
    }

    /*private String readFromFile(String fileName) throws IOException {
        URL url = new URL(EXAMPLES_ROOT_DIRECTORY + "/%s".formatted(fileName));
        assertThat(url).isNotNull();
        return IOUtils.toString(url.openStream(), StandardCharsets.UTF_8);
    }*/

    private String readFromFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        assertThat(inputStream).isNotNull();
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
    


}
