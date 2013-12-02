import java.util.*;

public class ReducerArray {
  public List<Double> content = new ArrayList<Double>();
  double epsilon = 0.0;
  double epsilonPower = 1;

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
    epsilon = ra.epsilon;
  }
  
  public ReducerArray(double arr[]){
	  for(double d: arr) {
		  content.add(d);
	  }
  }

  public void setEpsilon(double epsilon) {
    this.epsilon = epsilon;
  }
  
  public double get(int index) {
	  return content.get(index);
  }

  public int size() {
	  return this.content.size();
  }
  
  public void push(double val) {
	  content.add(val);
  }

  public static ReducerArray copy(ReducerArray[] in) {
    ReducerArray ret = new ReducerArray();
    double maxEpsilon = 0;
    for (ReducerArray ra: in) {
      for (double val: ra.content) {
        ret.push(val);
      }
      if (ra.epsilon > maxEpsilon) {
        maxEpsilon = ra.epsilon;
      }
    }
    ret.epsilon = maxEpsilon;
    return ret;
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
        ret.push(pq.poll());
      }
      ret.epsilon = epsilon;
      return ret;
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
    //List<Double> ret = new ArrayList<Double>();
    ReducerArray ret = new ReducerArray();
    if (content.size() == 0) {
      return ret;
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
        ret.push(pq.poll());
      }
      ret.epsilon = epsilon;
      return ret;
    }
  }
  
	public ReducerArray sort(boolean isDesc) {
		ReducerArray ret = new ReducerArray(content);
    Collections.sort(ret.content);
		if(isDesc) {
			Collections.reverse(ret.content);
		}
    ret.epsilon = epsilon;
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
      ReducerArray ret = new ReducerArray(sorted.get(sorted.size()/2));
      ret.epsilon = epsilon;
      return ret;
    } else if (sorted.size() != 0) {
      double val = ((sorted.get(sorted.size()/2)) + (sorted.get(sorted.size()/2-1)))/2;
      ReducerArray ret = new ReducerArray(val);
      ret.epsilon = epsilon;
      return ret;
    } else {
      return new ReducerArray();
    }
  }

  public ReducerArray mean() {
    double sum=0.0;
    for(double val:content) {
      sum += val;
    }
    
    ReducerArray result = new ReducerArray(sum/content.size());
    return result;
  }

  public ReducerArray add(ReducerArray addend) {
    ReducerArray ret = new ReducerArray();
    if (addend.size() == this.size()) {
      for (int i = 0; i < this.size(); i++) {
        ret.push(content.get(i) + addend.get(i));
      }
      ret.epsilon = this.epsilon + addend.epsilon;
    } else if (addend.size() == 1 && this.size() > 0) {
      for (double d: content) {
        ret.push(d + addend.get(0));
      }
      ret.epsilon = this.epsilon;
    } else if (addend.size() > 0 && this.size() == 1) {
      for (double d: addend.content) {
        ret.push(d + content.get(0));
      }
      ret.epsilon = addend.epsilon;
    } 
    return ret;
  }

  public ReducerArray add(double num) {
    return add(new ReducerArray(num));
  }

  public ReducerArray negate() {
    ReducerArray ret = new ReducerArray();
    for(double val: content) {
      ret.push(-val);
    }
    ret.epsilon = epsilon;
    return ret;
  }

  public ReducerArray sub(ReducerArray subtrahend) {
    return add(subtrahend.negate());
  }

  public ReducerArray sub(double num) {
    return sub(new ReducerArray(num));
  }

  public ReducerArray inv() {
    ReducerArray ret = new ReducerArray();
    ReducerArray empty = new ReducerArray();
    for (double val: content) {
      if (val == 0) return empty;
      ret.push(1/val);
    }
    if (epsilon == 0) ret.epsilon = 0;
    else ret.epsilon = 1/epsilon;
    return ret;
  }

  public ReducerArray mult(ReducerArray multiplier) {
    ReducerArray ret = new ReducerArray();
    if (multiplier.size() == this.size()) {
      for (int i = 0; i < this.size(); i++) {
        ret.push(content.get(i) * multiplier.get(i));
      }
      ret.epsilon = this.epsilon * multiplier.epsilon;
    } else if (multiplier.size() == 1 && this.size() > 0) {
      for (double d: content) {
        ret.push(d * multiplier.get(0));
      }
      ret.epsilon = this.epsilon * multiplier.get(0);
    } else if (multiplier.size() > 0 && this.size() == 1) {
      for (double d: multiplier.content) {
        ret.push(d * content.get(0));
      }
      ret.epsilon = this.get(0) * multiplier.epsilon;
    } 
    return ret;
  }

  public ReducerArray mult(double num) {
    return mult(new ReducerArray(num));
  }

  public ReducerArray div(ReducerArray divisor) {
    return mult(divisor.inv());
  }

  public ReducerArray div(double num) {
    return div(new ReducerArray(num));
  }

  public ReducerArray count() {
    int count = content.size();
    return new ReducerArray((double)count);
  }

  public ReducerArray abs() {
    ReducerArray ret = new ReducerArray();
    for (double val: content) {
      if (val >= 0) {
        ret.push(val);
      } else {
        ret.push(-val);
      }
    }
    ret.epsilon = epsilon;
    return ret;
  }

  public ReducerArray filter(List<String> ops, List<Double> vals) {
    ReducerArray ret = new ReducerArray();
    for (double val: content) {
      Boolean sat = true;
      for (int i = 0; i < ops.size(); i++) {
        if (ops.get(i).equals(">")) {
          if (val <= vals.get(i)) {
            sat = false;
            break;
          }
        } else if (ops.get(i).equals("<")) {
          if (val >= vals.get(i)) {
            sat = false;
            break;
          }
        } else if (ops.get(i).equals("==")) {
          if (val != vals.get(i)) {
            sat = false;
            break;
          }
        } else if (ops.get(i).equals(">=")) {
          if (val < vals.get(i)) {
            sat = false;
            break;
          }
        } else if (ops.get(i).equals("<=")) {
          if (val > vals.get(i)) {
            sat = false;
            break;
          }
        } else {
          sat = false;
          break;
        }
      }

      if (sat) ret.push(val);
    }
    ret.epsilon = epsilon;
    return ret;
  }

  public ReducerArray power(double p) {
    ReducerArray ret = new ReducerArray();
    for(Double d: content) {
      ret.push(Math.pow(d, p));
    }
    ret.epsilon = epsilon;
    ret.epsilonPower *= p;
    return ret;
  }
}
