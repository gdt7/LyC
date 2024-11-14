package lyc.compiler.assemblerGenerator;

import java.io.IOException;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;

public class AssignmentGenerator extends AssemblerGenerator{

	public AssignmentGenerator() {
		super();
	}

	@Override
	public StringBuilder generate() throws IOException {
		System.out.println("assignment generator");
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		String res = "";
		if(cState.getOperandStack().size() >= 2) {
			String str2 = cState.getOperandStack().pop();
			String str1 = cState.getOperandStack().pop();
			
			System.out.println(str2);
			System.out.println(str1);
			
            res = "MOV R1, " + str1 + "\n";
			res = res.concat("MOV ".concat(str2).concat(" , ").concat("R1").concat("\n"));			
		}
		return new StringBuilder(res);
	}
}
