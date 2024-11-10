package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;

public class AssignmentGenerator extends AssemblerGenerator{

	public AssignmentGenerator() {
		super();
		
	}

	@Override
	public String generate(Stack<String> stack, CompilerState cState) throws IOException {
		String res = "";
		System.out.println("ENTRE GENERATE ASSIGNMENT");
		String str2  = stack.pop();
		String str1 = stack.pop();
		System.out.println("variables sacadas de pila : ");
		System.out.println(str2);
		System.out.println(str2);
		res.concat("MOV R1, " + str1);
		res.concat("MOV ".concat(str1).concat(" , ").concat(str2));
				
		return res;
	}

}
