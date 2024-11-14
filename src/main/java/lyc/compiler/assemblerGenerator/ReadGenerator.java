package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;

public class ReadGenerator extends AssemblerGenerator{

	public ReadGenerator() {
		super();
		
	}

	@Override
	public StringBuilder generate() throws IOException {
		System.out.println("Read generator");
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		String res = "";
		
		String str1 = cState.getOperandStack().pop();
		
		//HACER CODIGO READ
		
		//HACER CODIGO WRITE
		res = "read " + str1 + "\n";
		
		return new StringBuilder(res);
	}

}
