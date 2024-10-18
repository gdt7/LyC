package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lyc.compiler.Lexer;
import lyc.compiler.Parser;
import lyc.compiler.model.SymbolTableStruct;
import lyc.compiler.util.StringUtil;


public class IntermediateCodeGenerator implements FileGenerator {

    Parser parser;
	Lexer lexer;

    public IntermediateCodeGenerator(Lexer lexer) {
		this.lexer = lexer;
	}
	
	public IntermediateCodeGenerator(Parser parser) {
		this.parser = parser;
	}

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
    	boolean isBreakline = false;
    	int idx = 0;
        for (String code : parser.intermediateCode) {
        	fileWriter.write(String.valueOf(idx) + "\t" + code + "\n");
        	idx++;
        }
    }
}
