import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulator extends ReducerInterface<Double> {
	static Map<String, ReducerArray> symTable = new HashMap<String, ReducerArray>();

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		double input[] = { 3.1, 2.3, 5.4, 4.5, 8.9, 12.7 };

		ReducerArray in = new ReducerArray(input);
		symTable.put("input", in);
		List<Instruction> instructions = new ArrayList<Instruction>();

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		try {
			String line = br.readLine();
			while (line != null) {
				instructions.add(new Instruction(line));
				line = br.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Instruction ins : instructions) {
			exec(ins);
		}

	}

	private static void exec(Instruction ins) {
		Opcode opc = ins.getOpcode();
		String dest = ins.getDest();
		List<String> srcs = ins.getSrcs();
		String arg = ins.getArg();
		ReducerArray result;
		int num = 1;

		switch (opc) {
		case MIN:
			if (arg != null) {
				try {
					num = Integer.parseInt(arg);
				} catch (NumberFormatException e) {
					num = (int) (double) symTable.get(arg).get(0);
				}
			}
			result = symTable.get(srcs.get(0)).max(num);
			symTable.put(dest, result);
			break;
		case MAX:
			if (arg != null) {
				try {
					num = Integer.parseInt(arg);
				} catch (NumberFormatException e) {
					num = (int) (double) symTable.get(arg).get(0);
				}
			}

			result = symTable.get(srcs.get(0)).max(num);
			symTable.put(dest, result);
			break;
		case OUTPUT:
			ReducerArray out = symTable.get(dest);
			symTable.put("output", out);
			// System.out.println(out.toString());
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
		case MEDIAN:
			symTable.put(dest, symTable.get(0).median());
			break;
		case MEAN:
			symTable.put(dest, symTable.get(0).mean());
			break;
		case ADD:
			ReducerArray add1 = symTable.get(srcs.get(0));
			try {
				double add2 = Double.parseDouble(srcs.get(1));
				result = add1.add(add2);
			} catch (NumberFormatException e) {
				ReducerArray add2 = symTable.get(srcs.get(1));
				if (add1.size() != add2.size() && add2.size() != 1) {
					System.err.println("length 2nd operand of add is wrong.");
				}
				result = add1.add(add2);
			}
			symTable.put(dest, result);
			break;
		case SUB:
			ReducerArray sub1 = symTable.get(srcs.get(0));
			try {
				double sub2 = Double.parseDouble(srcs.get(1));
				result = sub1.sub(sub2);
			} catch (NumberFormatException e) {
				ReducerArray sub2 = symTable.get(srcs.get(1));
				if (sub1.size() != sub2.size() && sub2.size() != 1) {
					System.err.println("length 2nd operand of add is wrong.");
				}
				result = sub1.sub(sub2);
			}
			symTable.put(dest, result);
			break;
		case NEGATE:
			result = symTable.get(srcs.get(0)).negate();
			symTable.put(dest, result);
			break;
		case ABS:
			result = symTable.get(srcs.get(0)).abs();
			symTable.put(dest, result);
			break;
		case MULT:
			ReducerArray op1 = symTable.get(srcs.get(0));
			try {
				double op2 = Double.parseDouble(srcs.get(1));
				result = op1.mult(op2);
			} catch (NumberFormatException e) {
				ReducerArray op2 = symTable.get(srcs.get(1));
				if (op1.size() != op2.size() && op2.size() != 1) {
					System.err.println("length 2nd operand of add is wrong.");
				}
				result = op1.mult(op2);
			}
			symTable.put(dest, result);
			break;
		case DIV:
			op1 = symTable.get(srcs.get(0));
			try {
				double op2 = Double.parseDouble(srcs.get(1));
				result = op1.div(op2);
			} catch (NumberFormatException e) {
				ReducerArray op2 = symTable.get(srcs.get(1));
				if (op1.size() != op2.size() && op2.size() != 1) {
					System.err.println("length 2nd operand of add is wrong.");
				}
				result = op1.div(op2);
			}
			symTable.put(dest, result);
			break;
		case INV:
			result = symTable.get(srcs.get(0)).inv();
			symTable.put(dest, result);
			break;
		case POWER:
			double pow = Double.parseDouble(arg);
			result = symTable.get(srcs.get(0)).power(pow);
			symTable.put(dest, result);
			break;
		case COUNT:
			result = symTable.get(srcs.get(0)).count();
			symTable.put(dest, result);
			break;
		case FILTER:
			List<String> comparator = ins.getComparator();
			List<Double> comparand = ins.getComparand();

			result = symTable.get(srcs.get(0)).filter(comparator, comparand);
			symTable.put(dest, result);
		case COPY:
			ReducerArray sources[] = new ReducerArray[srcs.size()];
			for (int i = 0; i < srcs.size(); i++) {
				sources[i] = symTable.get(srcs.get(i));
			}
			result = ReducerArray.copy(sources);
			symTable.put(dest, result);
			break;
		}
	}

	public ReducerArray reduce(ReducerArray ra) {
		symTable.put("input", ra);
		List<Instruction> instructions = new ArrayList<Instruction>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
			String line = br.readLine();
			while (line != null) {
				instructions.add(new Instruction(line));
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Instruction ins : instructions) {
			exec(ins);
		}
		return symTable.get("output");
	}

	public List<Double> reduce(List<Double> in) {
		ReducerArray ra = new ReducerArray(in);
		return reduce(ra).content;
	}
}
