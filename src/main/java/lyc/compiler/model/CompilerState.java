package lyc.compiler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CompilerState {
	
	
	
	private List<SymbolTableStruct> symbolTable;
	private List<String> intermediateCode;
	private Stack<SymbolTableStruct> assemblerVariables;
	private String assemblerCode;
	
	public CompilerState() {
		symbolTable = new ArrayList<SymbolTableStruct>();
		intermediateCode = new ArrayList<String>();
		setAssemblerVariables(new Stack<SymbolTableStruct>());
	}
	
	public List<SymbolTableStruct> getSymbolTable() {
		return symbolTable;
	}
	public void setSymbolTable(List<SymbolTableStruct> symbolTable) {
		this.symbolTable = symbolTable;
	}
	public List<String> getIntermediateCode() {
		return intermediateCode;
	}
	public void setIntermediateCode(List<String> intermediateCode) {
		this.intermediateCode = intermediateCode;
	}

	public Stack<SymbolTableStruct> getAssemblerVariables() {
		return assemblerVariables;
	}

	public void setAssemblerVariables(Stack<SymbolTableStruct> assemblerVariables) {
		this.assemblerVariables = assemblerVariables;
	}
	
	public void setAssemblerCode(String assemblerCode) {
		this.assemblerCode = assemblerCode;
	}
}
