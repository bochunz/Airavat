import java.util.*;

public class ReducerArrayTest {    
  public static void main(String args[]) {
  	List<Double> l = new ArrayList<Double>();
  	l.add(1.0);
  	l.add(3.0);
  	l.add(2.0);
  	l.add(2.5);
  	l.add(2.5);
  	ReducerArray a = new ReducerArray(l);
  	System.out.println(a.sort(false).toString());
  	System.out.println(a.max(2).toString());
  	System.out.println(a.median().toString());
  	System.out.println(a.sum().toString());
  	System.out.println(a.mean().toString());
  }
}
