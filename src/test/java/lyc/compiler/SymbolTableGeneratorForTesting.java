package lyc.compiler;

import lyc.compiler.Parser;
import lyc.compiler.Lexer;
import lyc.compiler.model.SymbolTableStruct;

public class SymbolTableGeneratorForTesting {

	private static final String TABLE_SEPARATOR = "\t\t\t\t\t\t";
	private static final String TABLE_HEADER_SEPARATOR = "\t\t\t\t\t\t";
	private static final String TABLE_HEADER = "NOMBRE" + TABLE_HEADER_SEPARATOR + "TIPODATO" + TABLE_HEADER_SEPARATOR
			+ "VALOR" + TABLE_HEADER_SEPARATOR + "LONGITUD";

	public static void generateTable(Lexer lex) {
		System.out.println(TABLE_HEADER);
		for (SymbolTableStruct s : lex.symbolList) {
			if(s != null)
				System.out.println(s.toString().concat("\n"));
		}
	}

}
