package lyc.compiler.factories;

import lyc.compiler.assemblerGenerator.AdditionGenerator;
import lyc.compiler.assemblerGenerator.AssemblerGenerator;
import lyc.compiler.assemblerGenerator.AssignmentGenerator;
import lyc.compiler.assemblerGenerator.BiggerEqualThanGenerator;
import lyc.compiler.assemblerGenerator.BiggerGenerator;
import lyc.compiler.assemblerGenerator.DivisionGenerator;
import lyc.compiler.assemblerGenerator.LessThanGenerator;
import lyc.compiler.assemblerGenerator.LesserEqualThanGenerator;
import lyc.compiler.assemblerGenerator.MultiplicationGenerator;
import lyc.compiler.assemblerGenerator.ReadGenerator;
import lyc.compiler.assemblerGenerator.SelectionGenerator;
import lyc.compiler.assemblerGenerator.SubstractionGenerator;
import lyc.compiler.assemblerGenerator.WhileGenerator;
import lyc.compiler.assemblerGenerator.WriteGenerator;

public class AssemblerGeneratorFactory {

	public static AssemblerGenerator create(String s) {
		AssemblerGenerator ag = switch (s) {
		case "+":{
			yield new AdditionGenerator();
		}
		case "-":{
			yield new SubstractionGenerator();
		}
		case "*" :{
			yield new MultiplicationGenerator();
		}
		case "/":{
			yield new DivisionGenerator();
		}
		case ":=" : {
			yield new AssignmentGenerator();
		}
		case "CMP": {
			yield new SelectionGenerator();
		}
		case "write" : {
			yield new WriteGenerator();
		}
		case "read" : {
			yield new ReadGenerator();
		}
		case "ET":{
			yield new WhileGenerator();
		}
		default:
			System.out.println("Unexpected value: " + s);
			yield null;
//			throw new IllegalArgumentException("Unexpected value: " + s);
		};

		return ag;

	}
}
