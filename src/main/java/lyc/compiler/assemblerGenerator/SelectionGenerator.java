package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.util.AssemblerStringAnalizer;

public class SelectionGenerator extends AssemblerGenerator {

	public SelectionGenerator() {
		super();
	}

	private static final List<String> logicalOperators = Arrays.asList("AND", "OR", "NOT");
	private static final Map<String, String> polacaToAssemblerJmp = Map.of(
			"BLT", "JB", 
			"BLE", "JNA", 
			"BGT", "JA",
			"BGE","JAE",
			"BEQ","JE",
			"BNE","JNE"
	);
	private static final Map<String, String> polacaToAssemblerReverseJmp = Map.of(
			"JB","JAE",
			"JNA","JA",
			"JA","JNA",
			"JAE","JB",
			"JE","JNE",
			"JNE","JE"
			);

	/*
	 * 
	 * */
	@Override
	public StringBuilder generate() throws IOException {
		System.out.println("Selection generator");
		StringBuilder ret = new StringBuilder();
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		if (cState.getOperandStack().size() >= 2) {
			String var1 = cState.getOperandStack().pop();
			String var2 = cState.getOperandStack().pop();	
			String comparisonType = cState.getAssemblerCodeIt().next();
			cState.increaseIndex();
			String endIndex = cState.getAssemblerCodeIt().next();
			int currentIndex = cState.increaseIndex();
			System.out.println("CurrentIndex: " + currentIndex);
			ret = new StringBuilder("FLD " + var1 + " \n ");
			ret = ret.append(" FCOM ").append(var2);
			ret = ret.append(" \n fstsw ax \n sahf \n ");
        	//SEGUN EL COMPARISON TYPE TENGO QUE VER QUE JUMP HAGO
			String possibleLogicalOperator = cState.getAssemblerCodeIt().next();
			cState.setCurrentIndex(cState.getCurrentIndex() + 1);
			String jmp = polacaToAssemblerJmp.get(comparisonType);
			String etiqFinal = "et_final_" + cState.getEtiqCount();
			cState.increaseEtiqCount();
			String etiqbloque = "etiq_bloque_" + cState.getEtiqCount();
			cState.increaseEtiqCount();
			//HACER ETIQUETAS DINAMICAS DE ALGUNA FORMA
			if (logicalOperators.contains(possibleLogicalOperator)) {
				switch (possibleLogicalOperator) {
				case "AND":
					ret = ret.append(jmp + " " + etiqFinal);
					break;
				case "OR":
					ret = ret.append(reverseJump(jmp) + " " + etiqbloque);
					break;
				case "NOT":
//					ret = ret.append(getJumpByComparisonType(comparisonType));
					break;
				}
				
				//TODO : ESTA SECCION CON EL NOT VARIA; HACERLO
				String firstOperand = cState.getAssemblerCodeIt().next();
				cState.increaseIndex();
				String secondOperand = cState.getAssemblerCodeIt().next();
				cState.increaseIndex();
				cState.getAssemblerCodeIt().next();
				cState.increaseIndex();
				comparisonType = cState.getAssemblerCodeIt().next();
				cState.increaseIndex();
				ret = ret.append("\n FLD " + firstOperand + " \n ").append(" FCOM ").append(secondOperand);
				ret = ret.append(" \n fstsw ax \n sahf \n ");
				ret.append(polacaToAssemblerJmp.get(comparisonType)).append(" " + etiqFinal + "\n");
			}else {
				ret = ret.append(jmp);
				ret = ret.append(etiqFinal);
				cState.getOperandStack().push(possibleLogicalOperator);
			}
			
			ret.append("\n " + etiqbloque + ": \n");
			while (currentIndex < Integer.valueOf(endIndex)) {
				ret = ret.append(AssemblerStringAnalizer.analizeString(new StringBuilder()));
				currentIndex = cState.getCurrentIndex();
			}
			ret = ret.append( etiqFinal + ": \n ");
			
			
		}
		return ret;

	}


	private String reverseJump(String s) {
		return polacaToAssemblerReverseJmp.get(s);
	}

}
