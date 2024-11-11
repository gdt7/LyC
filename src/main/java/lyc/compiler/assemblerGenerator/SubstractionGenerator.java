package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;

public class SubstractionGenerator extends AssemblerGenerator{

	public SubstractionGenerator() {
		super();
		
	}

	@Override
	public StringBuilder generate() throws IOException {
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		String res = "";
		
		String str2  = cState.getOperandStack().pop();
		String str1 = cState.getOperandStack().pop();
		
		String auxName = getAuxiliaryVariableName(cState.getAssemblerVariables());
		res.concat("MOV R1," + str1);
		res.concat("SUB R1," + str2);
		res.concat("MOV ".concat(auxName).concat(",R1"));
		
		cState.getOperandStack().push(auxName);
		
		//TENGO QUE VER QUE TIPO DE DATO SERIA LA NUEVA VARIABLE EN ASSEMBLER
		//ESO SERIA CON LA TABLA DE SINTESIS(?)
		SymbolTableStruct sts = new SymbolTableStruct(auxName,str1, 0, 0);
		cState.getAssemblerVariables().push(sts);
		
		return new StringBuilder(res);
	}

}
