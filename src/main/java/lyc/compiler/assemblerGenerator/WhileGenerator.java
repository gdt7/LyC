package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.util.AssemblerStringAnalizer;

public class WhileGenerator extends AssemblerGenerator{

	public WhileGenerator() {
		super();
	}

	/*
	 * 
	 * */
	@Override
	public StringBuilder generate() throws IOException {
		System.out.println("while generator");
		StringBuilder ret = new StringBuilder();
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		String str1 = cState.getAssemblerCodeIt().next();
		cState.increaseIndex();
		String str2 = cState.getAssemblerCodeIt().next();
		cState.increaseIndex();
		
//		cState.setComparisonPendingClose(true);
//		if(cState.getOperandStack().size() >= 2) {
//			String var1 = cState.getOperandStack().pop();
//			String var2 = cState.getOperandStack().pop();
//			String comparisonType = cState.getAssemblerCodeIt().next();
//			cState.increaseIndex();
//			String endIndex = cState.getAssemblerCodeIt().next();
//			int currentIndex = cState.increaseIndex();
//			ret = new StringBuilder("FLD " + var1 + " \n ");
//			ret = ret.append(" FCOMP ").append(var2);
//			ret = ret.append(" \n fstsw ax \n sahf \n ");
////		//SEGUN EL COMPARISON TYPE TENGO QUE VER QUE JUMP HAGO
//			ret = ret.append(getJumpByComparisonType(comparisonType));
////		//HACER ETIQUETAS DINAMICAS DE ALGUNA FORMA
//			ret = ret.append(" et_final \n ");
//			int count = 0;
//			while(currentIndex < Integer.valueOf(endIndex)) {
//				System.out.println(count++);
//				ret = ret.append(AssemblerStringAnalizer.analizeString(new StringBuilder()));
//				currentIndex = cState.getCurrentIndex();
//			}
//			ret = ret.append(" et_final \n ");
//			System.out.println("pase while selection");
//			//TENDRIA QUE LEER HASTA QUE APAREZCA DE NUEVO UN OPERANDO QUE MARQUE EL FIN DEL WHILE 
//			ret.append("");			
//		}
		return ret;
	}


	//HACER FUNCION DE CONVERSION
	private String getJumpByComparisonType(String comparisonType) {
		return "JMP " + comparisonType;
	}

}
