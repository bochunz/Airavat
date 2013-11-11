import java.util.*;

public interface ReducerInterface<T> {
  public List<T> reduce(List<T> in);
}
