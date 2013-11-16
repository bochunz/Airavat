import java.util.*;

public class ReducerArray {
  List<Double> content = new ArrayList<Double>();

  public ReducerArray() {
	  //default constructor.
  }
  
  public ReducerArray(double num) {
    content.add(num);
  }

  public ReducerArray(int num) {
    content.add((double)num);
  }

  public ReducerArray(ArrayList<Double> arr) {
    for(Double d: arr) {
    	content.add(d);
    }
  }
  
  public ReducerArray(double arr[]){
	  for(double d: arr) {
		  content.add(d);
	  }
  }
  
  public double get(int index) {
	  return content.get(index);
  }

  public int size() {
	  return this.content.size();
  }
  
  public void add(double val) {
	  content.add(val);
  }
  
  ReducerArray max(ReducerArray in) {
    ReducerArray ret = new ReducerArray();
    if (content.size() == 0) {
      return ret;
    } else if (in.size() == 0) {
      return ret;
    } else {
      int num_el = (int)in.get(0);
      PriorityQueue<Double> pq = new PriorityQueue<Double>(num_el);

      for (int i = 0; i < content.size(); i++) {
        pq.offer(content.get(i));
        if (pq.size() > num_el) {
          pq.poll();
        }
      }

      while (pq.size() >= 0) {
        ret.add(pq.poll());
      }
      return ret;
    }
  }
  
  ReducerArray max(int num) {
	    ReducerArray ret = new ReducerArray();
	    if (content.size() == 0) {
	      return ret;
	    } else {
	      int num_el= num;
	      PriorityQueue<Double> pq = new PriorityQueue<Double>(num_el);

	      for (int i = 0; i < content.size(); i++) {
	        pq.offer(content.get(i));
	        if (pq.size() > num_el) {
	          pq.poll();
	        }
	      }

	      while (pq.size() > 0) {
	        ret.add(pq.poll());
	      }
	      return ret;
	    }
	  }

//  ReducerArray min(ReducerArray in) {
//    List<Double> ret = new ArrayList<Double>();
//    if (content.size() == 0) {
//      return ret;
//    } else if (in.size() == 0) {
//      return ret;
//    } else {
//      int num_el = (int)in.get(0);
//      PriorityQueue<Double> pq = new PriorityQueue<Double>(num_el);
//
//      for (int i = 0; i < content.size(); i++) {
//        pq.offer(content.get(i));
//        if (pq.size > num_el) {
//          pq.poll();
//        }
//      }
//
//      while (pq.size() >= 0) {
//        ret.push(pq.poll);
//      }
//      return ret;
//    }
//  }
  
  
  	public String toString() {
  		StringBuilder sb = new StringBuilder();
  		for(Double val: content) {
  			sb.append(val);
  			sb.append("\t");
  		}
  		return sb.toString();
  	}
}
