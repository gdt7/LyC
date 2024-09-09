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

public class SymbolTableGenerator implements FileGenerator {

	Parser parser;
	Lexer lexer;
	private static final String TABLE_SEPARATOR = "\t\t\t\t";
	private static final String TABLE_HEADER_SEPARATOR = "\t\t";
	private static final List<String> TABLE_HEADER = Arrays.asList("NOMBRE","TIPODATO","VALOR","LONGITUD");
	
	
	public SymbolTableGenerator(Lexer lexer) {
		this.lexer = lexer;
	}
	
	public SymbolTableGenerator(Parser parser) {
		this.parser = parser;
	}

	@Override
	public void generate(FileWriter fileWriter) throws IOException {
		for(String s :TABLE_HEADER) {
			fileWriter.write(StringUtil.centrarString(s));
		}
		fileWriter.write("\n");
		for (SymbolTableStruct s :lexer.symbolList) {
			if(s != null) {
				fileWriter.write(s.toString().concat("\n"));				
			}
		}

	}
}
