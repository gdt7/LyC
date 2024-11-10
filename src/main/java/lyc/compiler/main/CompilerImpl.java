package lyc.compiler.main;

import java.io.IOException;
import java.io.Reader;

import lyc.compiler.Lexer;
import lyc.compiler.Parser;
import lyc.compiler.factories.FileFactory;
import lyc.compiler.factories.LexerFactory;
import lyc.compiler.factories.ParserFactory;
import lyc.compiler.files.FileOutputWriter;
import lyc.compiler.files.IntermediateCodeGenerator;
import lyc.compiler.files.SymbolTableGenerator;
import lyc.compiler.model.CompilerState;

public class CompilerImpl {

	
    
	private static CompilerImpl compilerImpl;
	private CompilerState cState = new CompilerState();
	
	private CompilerImpl() {}
	
	public static CompilerImpl getInstance() {
		if(compilerImpl == null)
			compilerImpl = new CompilerImpl();
		return compilerImpl;
	}
	
	
    public void main(String[] args) throws IOException,Exception {	
        try (Reader reader = FileFactory.create(args[0])) {
        	Lexer lexer = LexerFactory.create(reader);
        	lexer.symbolList = cState.getSymbolTable();
        	Parser parser = ParserFactory.create(lexer);
        	parser.cState = cState;
            parser.parse();
            FileOutputWriter.writeOutput("symbol-table.txt", new SymbolTableGenerator(lexer));
            FileOutputWriter.writeOutput("intermediate-code.txt", new IntermediateCodeGenerator(parser));
        }
    }


    public CompilerState getCompilerState() {
    	return cState;
    }
	
}
