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

  public ReducerArray(List<Double> arr) {
    for(Double d: arr) {
    	content.add(d);
    }
  }

  public ReducerArray(ReducerArray ra) {
    for(Double d: ra.content) {
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
  
  public ReducerArray max(ReducerArray in) {
    if (in.size() == 0) {
      return new ReducerArray();
    } else {
      int num_el = (int)in.get(0);
      return this.max(num_el);
    }
  }
  
  public ReducerArray max(int num) {
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
	      return new ReducerArray(ret);
	    }
	  }

  public ReducerArray min(ReducerArray in) {
    if (in.size() == 0) {
      return new ReducerArray();
    } else {
      int num_el = (int)in.get(0);
      return this.min(num_el);
    }
  }

  public ReducerArray min(int num) {
    List<Double> ret = new ArrayList<Double>();
    if (content.size() == 0) {
      return new ReducerArray(ret);
    } else {
      int num_el = num;
      PriorityQueue<Double> pq = new PriorityQueue<Double>(num_el, Collections.reverseOrder());

      for (int i = 0; i < content.size(); i++) {
        pq.offer(content.get(i));
        if (pq.size() > num_el) {
          pq.poll();
        }
      }   

      while (pq.size() > 0) {
        ret.add(pq.poll());
      }
      return new ReducerArray(ret);
    }
  }
  
	public ReducerArray sort(boolean isDesc) {
		ReducerArray ret = new ReducerArray(content);
    Collections.sort(ret.content);
		if(isDesc) {
			Collections.reverse(ret.content);
		}
		return ret;
		
	}

	public String toString() {
		// StringBuilder sb = new StringBuilder();
		// for(Double val: content) {
		// 	sb.append(val);
		// 	sb.append("\t");
		// }
		//return sb.toString();
    return content.toString();
	}
	
	public ReducerArray sum() {
		double sum=0.0;
		for(double val:content) {
			sum+=val;
		}
		
		ReducerArray result = new ReducerArray(sum);
		return result;
	}

  public ReducerArray median() {
    ReducerArray sorted = this.sort(false);
    if (sorted.size() % 2 == 1) {
      return new ReducerArray(sorted.get(sorted.size()/2));
    } else if (sorted.size() != 0) {
      double val = ((sorted.get(sorted.size()/2)) + (sorted.get(sorted.size()/2-1)))/2;
      return new ReducerArray(val);
    } else {
      return new ReducerArray();
    }
  }

  public ReducerArray mean() {
    double sum=0.0;
    for(double val:content) {
      sum+=val;
    }
    
    ReducerArray result = new ReducerArray(sum/content.size());
    return result;
  }
}
