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
import lyc.compiler.assemblerGenerator.SelectionGenerator;
import lyc.compiler.assemblerGenerator.SubstractionGenerator;

public class AssemblerGeneratorFactory {

	public static AssemblerGenerator create(String s) {
		AssemblerGenerator ag = switch (s) {
		case ">": {
			yield new BiggerGenerator();
		}
		case "<": {
			yield new LessThanGenerator();
		}
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
		case ">=" : {
			yield new BiggerEqualThanGenerator();
		}
		case "<=" : {
			yield new LesserEqualThanGenerator();
		}
		case ":=" : {
			yield new AssignmentGenerator();
		}
		case "CMP": {
			yield new SelectionGenerator();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + s);
		};

		return ag;

	}
}
