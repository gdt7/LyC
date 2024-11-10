package lyc.compiler.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import lyc.compiler.assemblerGenerator.AssemblerGenerator;
import lyc.compiler.factories.AssemblerGeneratorFactory;
import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;

public class AssemblerStringAnalizer {
	
	private static List<String> operadores = Arrays.asList("+", "-", "*", "/", ":=","CMP");
	
	public static  String analizeString(String code) throws IOException {
		CompilerState cState = CompilerImpl.getInstance().getCompilerState(); 
		String s = cState.getAssemblerCodeIt().next();
		cState.setCurrentIndex(cState.getCurrentIndex()+1);
//		System.out.println(s);
		if (isOperando(s)) {
			cState.getOperandStack().push(s);
		} else if (isOperador(s)) {
			AssemblerGenerator ag = AssemblerGeneratorFactory.create(s);
			code = code.concat(ag.generate());
//			System.out.println(code);
		} else {
//          System.out.println("Salgo por default");
		}
		return code;
	}
	
	
	private static boolean isOperador(String s) {
		return operadores.contains(s);
	}

	private static boolean isOperando(String s) {
		return !operadores.contains(s);
	}
}
