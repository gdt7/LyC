package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import lyc.compiler.assemblerGenerator.AssemblerGenerator;
import lyc.compiler.factories.AssemblerGeneratorFactory;
import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;
import lyc.compiler.util.AssemblerStringAnalizer;

public class AsmCodeGenerator implements FileGenerator {

	private CompilerState cState;
	
	public AsmCodeGenerator(CompilerState cState) {
		this.cState = cState;
	}

	@Override
	public void generate(FileWriter fileWriter) throws IOException {
		try {
			printAssemblerInitialCode(fileWriter);
			StringBuilder code = new StringBuilder();
			cState.setAssemblerCodeIt(cState.getIntermediateCode().iterator());
			while (cState.getAssemblerCodeIt().hasNext()) {
				code = code.append(AssemblerStringAnalizer.analizeString(new StringBuilder()));
			}
			printVariableDeclaration(fileWriter, cState);
			printCodeHeaderSection(fileWriter);
			fileWriter.write(code.toString());
			printCodeFooterSection(fileWriter);
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

	/*private void printCodeHeaderSection(FileWriter fileWriter) throws IOException {
		fileWriter.write(".CODE \n MAIN \n MOV AX,@DATA \n MOV DS,AX \n MOV ES,AX \n");
	}*/

	private void printCodeHeaderSection(FileWriter fileWriter) throws IOException {
		fileWriter.write(".CODE\n");          // Inicia la sección de código
		fileWriter.write("MAIN:\n");          // Define el punto de entrada MAIN
		fileWriter.write("    MOV AX, @DATA\n");  // Cargar el segmento de datos en AX
		fileWriter.write("    MOV DS, AX\n");     // Mover AX a DS (registro de segmento de datos)
		fileWriter.write("    MOV ES, AX\n");     // Mover AX a ES (registro de segmento extra)
	}
	

	/*private void printCodeFooterSection(FileWriter fileWriter) throws IOException {
		fileWriter.write("mov ax,4c00h \n Int 21h \n END MAIN");
	}*/

	private void printCodeFooterSection(FileWriter fileWriter) throws IOException {
		fileWriter.write("    MOV AX, 4C00h\n");  // Código de interrupción para terminar el programa
		fileWriter.write("    INT 21h\n");         // Llamada al sistema para finalizar el programa
		fileWriter.write("END MAIN\n");            // Define el final del código y el punto de entrada
	}
	

	private String printVariableDeclaration(FileWriter fileWriter, CompilerState cState) throws IOException {
		String ret = "";
		for (SymbolTableStruct s : cState.getSymbolTable()) {
			String variableDeclaration = s.getNombre().toString().concat("\t");
			String assemblerType = "";
			String valor = "";
			if(s.getTipoDato() != null) {

				switch (s.getTipoDato().toUpperCase()) {  // Convertir el tipo de dato a mayúsculas
					case "FLOAT":
						assemblerType = "DD"; // Double-Word para float (32 bits)
						break;
					case "INT":
						assemblerType = "DW"; // Word para int (16 bits) o DD si es de 32 bits
						break;
					case "STRING":
						assemblerType = "DB"; // Byte para string, con 0 al final como terminador
						break;
					default:
						throw new IllegalArgumentException("Tipo de dato desconocido: " + s.getTipoDato());
				}
					
				if (s.getValor() != null) {
					valor = s.getValor().toString();
				}else{
					valor = "?";
				}

				 // Concatenamos el tipo de assembler con el nombre de la variable
				 variableDeclaration = variableDeclaration.concat("\t").concat(assemblerType);
				 variableDeclaration = variableDeclaration.concat("\t").concat(valor);
				 //System.out.println("SOOOOOOOOOOOOOOOY" + variableDeclaration);
			}

			fileWriter.write(variableDeclaration.concat("\n"));
		}
		while (!cState.getAssemblerVariables().isEmpty()) {
			SymbolTableStruct s = cState.getAssemblerVariables().pop();
			String variableDeclaration = s.getNombre().toString().concat("\t");

			if (s.getTipoDato() != null) {
				//variableDeclaration = variableDeclaration.concat(s.getTipoDato());
				variableDeclaration = variableDeclaration.concat("\tDW").concat("\t?");
			}
			fileWriter.write(variableDeclaration.concat("\n"));
		}

		
		return ret;
	}

	private void printAssemblerInitialCode(FileWriter fileWriter) throws IOException {
		//fileWriter.write(".MODEL LARGE \n .386 \n Stack 200h \n .DATA \n ");
		fileWriter.write(".MODEL LARGE\n");  // Agregamos la directiva .MODEL para TASM
		fileWriter.write(".STACK 200h\n");   // Establece el tamaño de la pila
		fileWriter.write(".DATA\n");          // Definimos la sección de datos
		fileWriter.write("    ; Aquí puedes agregar datos si es necesario\n"); // Comentario para sección de datos
	}

	
}
