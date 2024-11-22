package lyc.compiler.assemblerGenerator;

import java.io.IOException;
import java.util.Stack;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;
import lyc.compiler.model.SymbolTableStruct;

public class AdditionGenerator extends AssemblerGenerator{

	public AdditionGenerator() {
		super();
		
	}



	/*@Override
	public StringBuilder generate() throws IOException {
		System.out.println("addition generator");
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		String res = "";
		String str2  = cState.getOperandStack().pop();
		String str1 = cState.getOperandStack().pop();
		
		String auxName = getAuxiliaryVariableName(cState.getAssemblerVariables());
		res.concat("MOV R1," + str1);
		res.concat("ADD R1," + str2);
		res.concat("MOV ".concat(auxName).concat(",R1"));
		
		cState.getOperandStack().push(auxName);
		
		//TENGO QUE VER QUE TIPO DE DATO SERIA LA NUEVA VARIABLE EN ASSEMBLER
		//ESO SERIA CON LA TABLA DE SINTESIS(?)
		SymbolTableStruct sts = new SymbolTableStruct(auxName,str1, 0, 0);
		cState.getAssemblerVariables().push(sts);
		
		return new StringBuilder(res);
	}*/
	@Override
	public StringBuilder generate() throws IOException {
		System.out.println("addition generator");
		CompilerState cState = CompilerImpl.getInstance().getCompilerState();
		
		String str2 = cState.getOperandStack().pop(); // Operando 2
		String str1 = cState.getOperandStack().pop(); // Operando 1
		
		// Agregar el prefijo '_' a los operandos
		String op1 = "_" + str1;  // Agregar _ al primer operando
		String op2 = "_" + str2;  // Agregar _ al segundo operando
		
		// Generar el nombre de una variable auxiliar
		String auxName = getAuxiliaryVariableName(cState.getAssemblerVariables());
		
		// Agregar el prefijo '_' a la variable auxiliar
		String auxVar = "_" + auxName;
	
		StringBuilder res = new StringBuilder();
		
		// Instrucciones en ensamblador con los operandos modificados
		res.append("MOV AX, " + op1).append("\n");   // Mover op1 a AX
		res.append("ADD AX, " + op2).append("\n");   // Sumar op2 a AX
		res.append("MOV " + auxVar + ", AX").append("\n");  // Mover el resultado a la variable auxiliar
	
		// Empujar la variable auxiliar a la pila de operandos
		cState.getOperandStack().push(auxVar);
		
		// Aquí debes manejar la tabla de símbolos
		// En este caso, estamos tomando el tipo de `op1` para asignárselo a la variable auxiliar
		SymbolTableStruct sts = new SymbolTableStruct(auxVar, op1, 0, 0); // Asegúrate de que los parámetros sean correctos
		cState.getAssemblerVariables().push(sts);
		
		return res; // Devuelve el resultado generado
	}
	


}
