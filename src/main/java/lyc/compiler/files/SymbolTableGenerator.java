package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lyc.compiler.Parser;
import lyc.compiler.model.SymbolTableStruct;

public class SymbolTableGenerator implements FileGenerator {

	Parser parser;
	private static final String TABLE_SEPARATOR = "\t\t\t\t";
	private static final String TABLE_HEADER_SEPARATOR = "\t\t";
	private static final String TABLE_HEADER = "NOMBRE" + TABLE_HEADER_SEPARATOR + "TIPODATO"+ TABLE_HEADER_SEPARATOR +"VALOR" + TABLE_HEADER_SEPARATOR + "LONGITUD\n";
	
	public SymbolTableGenerator(Parser parser) {
		this.parser = parser;
	}

	@Override
	public void generate(FileWriter fileWriter) throws IOException {
		fileWriter.write(TABLE_HEADER);
//		for (Map.Entry<Object, Object> entry : parser.table.entrySet()) {
//			String registro = entry.getKey().toString().concat("\t\t").concat(entry.getValue().toString()).concat("\n");
//			fileWriter.write(registro);
//		}
		for (SymbolTableStruct s :parser.symbolList) {
			String registro = s.getNombre().toString().concat(TABLE_SEPARATOR).concat(s.getTipoDato()).concat(TABLE_SEPARATOR).concat(s.getValor().toString())
					.concat(TABLE_SEPARATOR).concat(String.valueOf(s.getValor().toString().length())).concat("\n");
			fileWriter.write(registro);
		}

	}
}
