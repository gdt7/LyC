package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import lyc.compiler.Parser;
 
public class SymbolTableGenerator implements FileGenerator{

	Parser parser;
	
	public SymbolTableGenerator(Parser parser) {
		this.parser = parser;
	}
	
    @Override
    public void generate(FileWriter fileWriter) throws IOException {
    	fileWriter.write("ID\t\tValor\n");
    	for(Map.Entry<Object, Object> entry : parser.table.entrySet()) {
    		String registro = entry.getKey().toString().concat("\t\t").concat(entry.getValue().toString()).concat("\n");
    		fileWriter.write(registro);
    	}
    }
}
