import java.util.List;

public class Instruction{
	Opcode opcode;
	List<ReducerArray> srcs;
	ReducerArray dest;
	
	public Instruction(String instruction){
		instruction = instruction.toLowerCase();
		String ins[] = instruction.split(" ");
		String opc = ins[0];
		if(opc.equals("add")){
			opcode =Opcode.ADD;
		}
		if(opc.equals("sum")) {
			opcode =Opcode.SUM;
		}
		if(opc.equals("sub")) {
			opcode = Opcode.SUB;
		}
		if(opc.equals("negate")) {
			opcode = Opcode.NEGATE;
		}
		if(opc.equals("abs")) {
			opcode =Opcode.ABS;
		}
		if(opc.equals("mult")) {
			opcode = Opcode.MULT;
		}
		if(opc.equals("inv")) {
			opcode= Opcode.INV;
		}
		if(opc.equals("div")) {
			opcode = Opcode.DIV;
		}
		if(opc.equals("mean")) {
			opcode = Opcode.MEAN;
		}
		if(opc.equals("median")) {
			opcode = Opcode.MEDIAN;
		}
		if(opc.equals("max")) {
			opcode = Opcode.MAX;
		}
		if(opc.equals("min")) {
			opcode = Opcode.MIN;
		}
		if(opc.equals("power")) {
			opcode = Opcode.POWER;
		}
		if(opc.equals("count")) {
			opcode = Opcode.COUNT;
		}
		if(opc.equals("filter")) {
			opcode = Opcode.FILTER;
		}
		if(opc.equals("sort")) {
			opcode = Opcode.SORT;
		}
		if(opc.equals("copy")) {
			opcode = Opcode.COPY;
		}
		if(opc.equals("output")) {
			opcode = Opcode.OUTPUT;
		}
		
		switch (opcode){
			case ADD:
			case SUB:
			case MULT:
			case DIV:
//				dest = new ReducerArray();
				break;
		}
		
		
		
	}
}
