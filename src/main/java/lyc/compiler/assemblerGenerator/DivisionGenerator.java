package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;

public class DivisionGenerator extends AssemblerGenerator{

	public DivisionGenerator() {
		super();	
	}

	@Override
	public String generate(Stack<String> stack, CompilerState cState) throws IOException {
		String res = "";
		String str2  = stack.pop();
		String str1 = stack.pop();
		
		String auxName = getAuxiliaryVariableName(cState.getAssemblerVariables());
		res.concat("MOV R1," + str1);
		res.concat("DIV R1," + str2);
		res.concat("MOV ".concat(auxName).concat(",R1"));
		
		stack.push(auxName);
		
		//TENGO QUE VER QUE TIPO DE DATO SERIA LA NUEVA VARIABLE EN ASSEMBLER
		//ESO SERIA CON LA TABLA DE SINTESIS(?)
		SymbolTableStruct sts = new SymbolTableStruct(auxName,str1, 0, 0);
		cState.getAssemblerVariables().push(sts);
		
		return res;
	}

}
