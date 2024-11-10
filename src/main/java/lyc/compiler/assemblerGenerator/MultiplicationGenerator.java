package lyc.compiler.assemblerGenerator;

import java.io.IOException;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;

public class MultiplicationGenerator extends AssemblerGenerator{

	public MultiplicationGenerator() {
		super();
	}
	
	@Override
	public String generate() throws IOException {
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		String res = "";
		String str2  = cState.getOperandStack().pop();
		String str1 = cState.getOperandStack().pop();
		
		String auxName = getAuxiliaryVariableName(cState.getAssemblerVariables());
		res.concat("MOV R1," + str1);
		res.concat("MUL R1," + str2);
		res.concat("MOV ".concat(auxName).concat(",R1"));
		
		cState.getOperandStack().push(auxName);
		
		
		//TENGO QUE VER QUE TIPO DE DATO SERIA LA NUEVA VARIABLE EN ASSEMBLER
		//ESO SERIA CON LA TABLA DE SINTESIS(?)
		
		SymbolTableStruct sts = new SymbolTableStruct(auxName,str1, 0, 0);
		cState.getAssemblerVariables().push(sts);
		
		
		
		
		return res;
	}

}
