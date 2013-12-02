import java.util.*;

public abstract interface ReducerInterface<T> {
  public List<T> reduce(List<T> in);
}
