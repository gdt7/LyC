package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;

public abstract class AssemblerGenerator {
	
	protected AssemblerGenerator() {
	}
	
	public abstract String generate(Stack<String> stack, CompilerState cState) throws IOException;
	
	
	protected String getAuxiliaryVariableName(Stack<SymbolTableStruct> stack) {
		return "_@aux".concat(String.valueOf(stack.size()));
	}

}
