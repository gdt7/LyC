package lyc.compiler.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class CompilerState {
	private List<SymbolTableStruct> symbolTable;
	private List<String> intermediateCode;
	private Stack<SymbolTableStruct> assemblerVariables;
	private String assemblerCode;
	private Iterator<String> assemblerCodeIt;
	private int currentIndex = 0;
	private Stack<String> operandStack;
	private boolean comparisonPendingClose = false;
	private int etiqCount = 0;

	public CompilerState() {
		symbolTable = new ArrayList<SymbolTableStruct>();
		intermediateCode = new ArrayList<String>();
		setAssemblerVariables(new Stack<SymbolTableStruct>());
		setOperandStack(new Stack<String>());
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

	public Iterator<String> getAssemblerCodeIt() {
		return assemblerCodeIt;
	}

	public void setAssemblerCodeIt(Iterator<String> assemblerCodeIt) {
		this.assemblerCodeIt = assemblerCodeIt;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	public int increaseIndex() {
		currentIndex++;
		return currentIndex;
	}

	public Stack<String> getOperandStack() {
		return operandStack;
	}

	public void setOperandStack(Stack<String> operandStack) {
		this.operandStack = operandStack;
	}

	public boolean isComparisonPendingClose() {
		return comparisonPendingClose;
	}
	
	public void setComparisonPendingClose(boolean value) {
		this.comparisonPendingClose = value;
	}

	public int getEtiqCount() {
		return etiqCount;
	}

	public void setEtiqCount(int etiqCount) {
		this.etiqCount = etiqCount;
	}
	
	public int increaseEtiqCount() {
		this.etiqCount++;
		return etiqCount;
	}
	
}
