package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.util.AssemblerStringAnalizer;

public class WhileGenerator extends AssemblerGenerator {

	private static final List<String> logicalOperators = Arrays.asList("AND", "OR", "NOT");
	private static final Map<String, String> polacaToAssemblerJmp = Map.of("BLT", "JB", "BLE", "JNA", "BGT", "JA",
			"BGE", "JAE", "BEQ", "JE", "BNE", "JNE");
	private static final Map<String, String> polacaToAssemblerReverseJmp = Map.of(
			"JB","JAE",
			"JNA","JA",
			"JA","JNA",
			"JAE","JB",
			"JE","JNE",
			"JNE","JE"
			);

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
		String var1 = cState.getAssemblerCodeIt().next();
		var1 = "_" + var1;  // Agregar _ al primer operando
		cState.increaseIndex();
		String var2 = cState.getAssemblerCodeIt().next();
		var2 = "_" + var2;  // Agregar _ 
		cState.increaseIndex();
		cState.getAssemblerCodeIt().next();
		cState.increaseIndex();
		String comparisonType = cState.getAssemblerCodeIt().next();
		cState.increaseIndex();
		System.out.println("comparison type : " + comparisonType);
		String endIndex = cState.getAssemblerCodeIt().next();
		cState.increaseIndex();
		int currentIndex = cState.getCurrentIndex();
		// HACER ETIQUETAS DINAMICAS
		String etiqBeginWhile = "etiqWhile_" + cState.getEtiqCount();
		cState.increaseEtiqCount();
		ret = new StringBuilder(etiqBeginWhile + ": \n FILD " + var1 + " \n ");
		ret =  ret.append(new StringBuilder("FILD " + var2 + " \n "));//***************** */
		//ret = ret.append(" FCOM ").append(var2);
		ret = ret.append(" FCOM ST(1) ");
		//ret = ret.append(" \n fstsw ax \n sahf \n ");
		ret = ret.append(" \n FSTSW [TEMP] \n MOV AX, [TEMP] \n sahf \n ");
		// SEGUN EL COMPARISON TYPE TENGO QUE VER QUE JUMP HAGO
		String possibleLogicalOperator = cState.getAssemblerCodeIt().next();
		cState.setCurrentIndex(cState.getCurrentIndex() + 1);
		String jmp = polacaToAssemblerJmp.get(comparisonType);
		String etiqFinal = "et_final_" + cState.getEtiqCount();
		cState.increaseEtiqCount();
		//String etiqbloque = "etiq_bloque:" + cState.getEtiqCount();
		String etiqbloque = "etiq_bloque:";
		cState.increaseEtiqCount();
		// HACER ETIQUETAS DINAMICAS DE ALGUNA FORMA
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
			// TODO : ESTA SECCION CON EL NOT VARIA; HACERLO
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
		} else {
			ret.append(jmp + " " + etiqFinal);
			cState.getOperandStack().push(possibleLogicalOperator);
		}
		ret.append(" \n " + etiqbloque + "\n");
		System.out.println("currentIndex : " + currentIndex);
		System.out.println("endIndex : " + endIndex);
		while ((currentIndex + 3) < Integer.valueOf(endIndex)) {
			System.out.println("currentIndex : " + currentIndex);
			ret = ret.append(AssemblerStringAnalizer.analizeString(new StringBuilder()));
			currentIndex = cState.getCurrentIndex();
		}
		System.out.println("sali del loop");
		cState.getAssemblerCodeIt().next();
		cState.increaseIndex();
		cState.getAssemblerCodeIt().next();
		cState.increaseIndex();
		ret = ret.append("JMP " + etiqBeginWhile + "\n");
		ret = ret.append( etiqFinal + ":\n");

	return ret;

	}

	private String reverseJump(String s) {
		return polacaToAssemblerReverseJmp.get(s);
	}

}
