import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Simulator {
	static Map<String, ReducerArray> symTable = new HashMap<String,ReducerArray>();
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		double input[] = {3.1,2.3,5.4,4.5,8.9,12.7};
		
		ReducerArray in = new ReducerArray(input);
		symTable.put("input", in);
		List<Instruction> instructions  = new ArrayList<Instruction>();
		
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		
		try {
			String line = br.readLine();
			while(line!=null) {
				instructions.add(new Instruction(line));
				line = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Instruction ins: instructions) {
			exec(ins);
		}
		
		
	}
	
	private static void exec(Instruction ins) {
		Opcode opc = ins.getOpcode();
		String dest = ins.getDest();
		List<String> srcs =ins.getSrcs();
		String arg = ins.getArg();
		ReducerArray result;
		
		switch(opc) {
		case MAX:
			int num=1;
			if(arg!=null) {
				try {
					num = Integer.parseInt(arg);
				}catch(NumberFormatException e){
					num = (int)(double)symTable.get(arg).get(0);
				}
			}
			
			result = symTable.get(srcs.get(0)).max(num);
			symTable.put(dest, result);
			break;
		case OUTPUT:
			ReducerArray out = symTable.get(dest);
			System.out.println(out.toString());
			break;
		case SORT:
			boolean isDesc = ins.isDesc();
			result = symTable.get(srcs.get(0)).sort(isDesc);
			symTable.put(dest, result);
			break;
		case SUM:
			result = symTable.get(srcs.get(0)).sum();
			symTable.put(dest, result);
			break;
		}
		
	}

}
