package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.util.AssemblerStringAnalizer;

public class SelectionGenerator extends AssemblerGenerator{

	public SelectionGenerator() {
		super();
	}


	@Override
	public String generate() throws IOException {
		System.out.println("Selection generator");
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		String var1 = cState.getOperandStack().pop();
		String var2 = cState.getOperandStack().pop();
		String elseIndex = cState.getAssemblerCodeIt().next();
		int currentIndex = cState.increaseIndex();
		String ret = "FLD " + var1 + " \n ";
		ret = ret.concat(" FCOMP ").concat(var2);
		ret = ret.concat(" fstsw ax \n sahf \n jna else_part: \n then_part: \n ");
//		while(currentIndex < Integer.valueOf(elseIndex)) {
//			AssemblerStringAnalizer.analizeString(ret);
//		}
		ret.concat("");
		return ret;
	}

}
