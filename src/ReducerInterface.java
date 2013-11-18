import java.util.*;

public abstract class ReducerInterface<T> {
  public double epsilon = 0.0;
  public String filename = "input.txt";

  public List<T> reduce(List<T> in);
}
