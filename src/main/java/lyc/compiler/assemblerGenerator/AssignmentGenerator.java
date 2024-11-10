package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;

public class AssignmentGenerator extends AssemblerGenerator{

	public AssignmentGenerator() {
		super();
	}

	@Override
	public String generate() throws IOException {
		System.out.println("assignment generate");
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		String str2 = cState.getOperandStack().pop();
		String str1 = cState.getOperandStack().pop();
		
		System.out.println(str2);
		System.out.println(str1);
		
		String res = "MOV R1, " + str1 + "\n";
		res = res.concat("MOV ".concat(str2).concat(" , ").concat("R1").concat("\n"));
		return res;
	}

	
}
