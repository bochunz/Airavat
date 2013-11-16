import java.util.ArrayList;
import java.util.List;

public class Instruction{
	private Opcode opcode;
	private String dest;
	private List<String> sources = new ArrayList<String>();
	private String arg=null;
	private boolean desc = false;
	private List<String> comparator;
	private List<Double> comparand;
	
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
		case MAX:
		case MIN:
		case POWER:
			dest = ins[1];
			sources.add(ins[2]);
			if(ins.length==4) {
				arg = ins[3];
			}
			break;
		case SORT:
			dest = ins[1];
			sources.add(ins[2]);
			if(ins.length==4 && ins[3].equals("desc")) {
				desc = true;
			}
			break;
		case FILTER:
			dest = ins[1];
			sources.add(ins[2]);
			comparator = new ArrayList<String>();
			comparand = new ArrayList<Double>();
			for(int i=3;i<ins.length;i++) {
				comparator.add(ins[i++]);
				comparand.add(Double.parseDouble(ins[i]));
			}
			break;
		default:
				dest = ins[1];
				for(int pos =2;pos<ins.length;pos++) {
					sources.add(ins[pos]);
				}
				break;
		}
		
		
		
	}

	public Opcode getOpcode() {
		return this.opcode;
	}
	
	public String getDest() {
		return this.dest;
	}
	
	public List<String> getSrcs(){
		return this.sources;
	}
	
	public String getArg() {
		return this.arg;
	}
	
	public boolean isDesc() {
		return desc;
	}
	
	public List<String> getComparator(){
		return this.comparator;
	}
	
	public List<Double> getComparand(){
		return this.comparand;
	}
}
