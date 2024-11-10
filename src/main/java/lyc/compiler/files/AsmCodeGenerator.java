package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import lyc.compiler.assemblerGenerator.AssemblerGenerator;
import lyc.compiler.factories.AssemblerGeneratorFactory;
import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;
public class AsmCodeGenerator implements FileGenerator {

	private CompilerState cState;
	private static List<String> operadores = Arrays.asList("+","-","*","/",":=");
	
    public AsmCodeGenerator(CompilerState cState) {
		this.cState = cState;
	}

  
	@Override
    public void generate(FileWriter fileWriter) throws IOException {
		Stack<String> st = new Stack<>();
		printAssemblerInitialCode(fileWriter);
		String code = "";
        for(String s : cState.getIntermediateCode()) {
        	System.out.println(s);
        	if(isOperando(s)) {
//        		System.out.println("Pusheo a stack de operandos");
        		st.push(s);
        	}else if(isOperador(s)) {
//        		System.out.println("Detecto operador");
        		AssemblerGenerator ag = AssemblerGeneratorFactory.create(s);
        		code = code.concat(ag.generate(st,cState));
        		System.out.println(code);
        	}else {
//        		System.out.println("Salgo por default");
        	}
        }
        printVariableDeclaration(fileWriter,cState);
        printCodeHeaderSection(fileWriter);
        fileWriter.write(code);
        printCodeFooterSection(fileWriter);
    }


	private void printCodeHeaderSection(FileWriter fileWriter) throws IOException {
		fileWriter.write(".CODE \n MOV AX.@DATA \n MOV DS,AX \n MOV es,ax");
	}

	
	private void printCodeFooterSection(FileWriter fileWriter) throws IOException {
		fileWriter.write("mov ax,4c00h \n Int 21h \n End");
	}

	private String printVariableDeclaration(FileWriter fileWriter, CompilerState cState) throws IOException {
		String ret = "";
		for(SymbolTableStruct s : cState.getSymbolTable()) {
			String variableDeclaration = s.getNombre().toString().concat("\t");
			if(s.getTipoDato() != null) {
				//Habria que conertirlo aca al tipo de dato en assembler
				variableDeclaration = variableDeclaration.concat(s.getTipoDato());				
			}
			fileWriter.write(variableDeclaration.concat("\n"));
		}
		while(!cState.getAssemblerVariables().isEmpty()) {
			SymbolTableStruct s = cState.getAssemblerVariables().pop();
			String variableDeclaration = s.getNombre().toString().concat("\t");
			if(s.getTipoDato() != null) {
				//Habria que conertirlo aca al tipo de dato en assembler
				variableDeclaration = variableDeclaration.concat(s.getTipoDato());				
			}
			fileWriter.write(variableDeclaration.concat("\n"));
		}
		return ret;
	}


	private void printAssemblerInitialCode(FileWriter fileWriter) throws IOException {
		fileWriter.write(".MODEL LARGE \n .386 \n Stack 200h \n .DATA \n ");	
	}


	private void generateAssembler(String s, Stack<String> st) {
		
	}


	private boolean isOperador(String s) {
		return operadores.contains(s);
	}


	private boolean isOperando(String s) {
		return !operadores.contains(s);
	}
}
