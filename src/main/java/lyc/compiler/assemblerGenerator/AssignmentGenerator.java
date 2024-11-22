package lyc.compiler.assemblerGenerator;

import java.io.IOException;

import lyc.compiler.main.CompilerImpl;
import lyc.compiler.model.CompilerState;

public class AssignmentGenerator extends AssemblerGenerator{

	public AssignmentGenerator() {
		super();
	}

	/*@Override
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
	}*/

	@Override
    public StringBuilder generate() throws IOException {
        System.out.println("assignment generator");
        CompilerState cState = CompilerImpl.getInstance().getCompilerState();
        String res = "";
        
        if (cState.getOperandStack().size() >= 2) {
            String str2 = cState.getOperandStack().pop(); // El operando de destino (aquí será la variable)
            String str1 = cState.getOperandStack().pop(); // El operando de origen (el valor a asignar)

			if (str1 != null && !str1.startsWith("_")) {
				str1 = "_" + str1;
			}

			if (str2 != null && !str2.startsWith("_")) {
				str2 = "_" + str2;
			}

			//String op1 = "_" + str1;  // Agregar _ al primer operando
			//String op2 = "_" + str2;  // Agregar _ al segundo operando
            
            System.out.println(str1);
            System.out.println(str2);
            
            // Usar registros estándar como AX, BX, etc. en lugar de R1
            res = "MOV AX, " + str1 + "\n";  // Mueve el valor de str1 (origen) a AX
            res = res.concat("MOV " + str2 + ", AX\n");  // Mueve el valor de AX (valor de str1) a str2 (destino)
        }
        
        return new StringBuilder(res);  // Devuelve el código ensamblador generado
    }
}
