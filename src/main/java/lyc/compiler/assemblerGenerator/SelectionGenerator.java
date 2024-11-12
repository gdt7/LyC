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
	public StringBuilder generate() throws IOException {
		System.out.println("Selection generator");
		StringBuilder ret = new StringBuilder();
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		if(cState.getOperandStack().size() >= 2) {
			String var1 = cState.getOperandStack().pop();
			String var2 = cState.getOperandStack().pop();
			String comparisonType = cState.getAssemblerCodeIt().next();
			cState.increaseIndex();
			String endIndex = cState.getAssemblerCodeIt().next();
			int currentIndex = cState.increaseIndex();
			ret = new StringBuilder("FLD " + var1 + " \n ");
			ret = ret.append(" FCOMP ").append(var2);
			ret = ret.append(" fstsw ax \n sahf \n ");
//		//SEGUN EL COMPARISON TYPE TENGO QUE VER QUE JUMP HAGO
			ret = ret.append(getJumpByComparisonType(comparisonType));
//		//HACER ETIQUETAS DINAMICAS DE ALGUNA FORMA
			ret = ret.append(" else_part \n ");
			ret = ret.append(" then_part: \n ");
			while(currentIndex < Integer.valueOf(endIndex)) {
				ret = ret.append(AssemblerStringAnalizer.analizeString(new StringBuilder()));
				currentIndex = cState.getCurrentIndex();
			}
			System.out.println("pase while selection");
			//TENDRIA QUE LEER HASTA QUE APAREZCA DE NUEVO UN OPERANDO QUE MARQUE EL FIN DEL WHILE 
			ret.append("");			
		}
		return ret;
	
	}


	//HACER FUNCION DE CONVERSION
	private String getJumpByComparisonType(String comparisonType) {
		return comparisonType;
	}

}
