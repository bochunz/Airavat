import java.util.*;

public class RecuderArray {
  List<Double> content = new ArrayList<Double>();

  ReducerArray(double num) {
    content.add(num);
  }

  ReducerArray(int num) {
    content.add((double)num);
  }

  ReducerArray(ArrayList<Double> arr) {
    content = arr.clone();
  }

  ReducerArray max(ReducerArray in) {
    List<Double> ret = new ArrayList<Double>();
    if (content.size() == 0) {
      return ret;
    } else if (in.size() == 0) {
      return ret;
    } else {
      int num_el = (int)in.get(0);
      PriorityQueue<Double> pq = new PriorityQueue<Double>(num_el, Collections.reverseOrder());

      for (int i = 0; i < content.size(); i++) {
        pq.offer(content.get(i));
        if (pq.size > num_el) {
          pq.poll();
        }
      }

      while (pq.size() >= 0) {
        ret.push(pq.poll);
      }
      return ret;
    }
  }

  ReducerArray min(ReducerArray in) {
    List<Double> ret = new ArrayList<Double>();
    if (content.size() == 0) {
      return ret;
    } else if (in.size() == 0) {
      return ret;
    } else {
      int num_el = (int)in.get(0);
      PriorityQueue<Double> pq = new PriorityQueue<Double>(num_el);

      for (int i = 0; i < content.size(); i++) {
        pq.offer(content.get(i));
        if (pq.size > num_el) {
          pq.poll();
        }
      }

      while (pq.size() >= 0) {
        ret.push(pq.poll);
      }
      return ret;
    }
  }
}
