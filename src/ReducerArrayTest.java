import java.util.*;

public class ReducerArrayTest {    
  public static void main(String args[]) {
  	List<Double> l = new ArrayList<Double>();
  	l.add(1.0);
  	l.add(3.0);
  	l.add(2.0);
  	l.add(2.5);
  	l.add(2.5);
    List<Double> f = new ArrayList<Double>();
    f.add(1.1);
    f.add(3.1);
    f.add(2.1);
    f.add(2.6);
    f.add(2.6);
  	ReducerArray a = new ReducerArray(l);
  	System.out.println(a.sort(false).toString());
  	System.out.println(a.max(2).toString());
  	System.out.println(a.median().toString());
  	System.out.println(a.sum().toString());
  	System.out.println(a.mean().toString());
    System.out.println(a.sub(new ReducerArray(1.2)).toString());
    System.out.println(a.sub(1.2).toString());
    System.out.println(new ReducerArray(4.99).add(a));
    System.out.println(a.inv().toString());
    System.out.println(a.mult(new ReducerArray(4.99)).toString());

    ReducerArray ras[] = new ReducerArray[3];
    ras[0] = a;
    ras[1] = new ReducerArray(f);
    ras[2] = new ReducerArray(2);
    ReducerArray b = ReducerArray.copy(ras);
    System.out.println(b.toString());
    System.out.println(b.div(2).toString());
    System.out.println(b.div(2).sub(1).abs().toString());

    ArrayList<String> ops = new ArrayList<String>();
    ops.add(">");
    ops.add("<");
    ArrayList<Double> vals = new ArrayList<Double>();
    vals.add(2.05);
    vals.add(2.6);
    System.out.println(b.toString());
    System.out.println(b.filter(ops, vals).toString());
    System.out.println(b.power(2.0).toString());
  }
}
