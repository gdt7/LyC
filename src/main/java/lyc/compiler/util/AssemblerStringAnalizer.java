package lyc.compiler.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import lyc.compiler.assemblerGenerator.AssemblerGenerator;
import lyc.compiler.factories.AssemblerGeneratorFactory;
import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;

public class AssemblerStringAnalizer {
	
	private static List<String> operadores = Arrays.asList("+", "-", "*", "/", ":=","CMP","read","write","ET");
	
	public static  StringBuilder analizeString(StringBuilder code) throws IOException {
		CompilerState cState = CompilerImpl.getInstance().getCompilerState(); 
		String s = cState.getAssemblerCodeIt().next();
		cState.increaseIndex();
//		System.out.println(s);
		if (isOperando(s)) {
			System.out.println("pusheo a pila el operando : " + s);
			cState.getOperandStack().push(s);
		} else if (isOperador(s)) {
			AssemblerGenerator ag = AssemblerGeneratorFactory.create(s);
			code = code.append(ag.generate());
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
