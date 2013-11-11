import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class SumReducer implements ReducerInterface<Double> {
	public List<Double> reduce(List<Double> in){
		List<Double> list = new ArrayList<Double>();
		Double sum=0.0;
		for(Double i:in) {
			sum+=i;
		}
		list.add(sum);
		return list;
	}
}

class CountReducer implements ReducerInterface<Double>{
	public List<Double> reduce(List<Double> in){
		List<Double> list = new ArrayList<Double>();
		double count = (double)in.size();
		list.add(count);
		return list;
	}
}

class MeanReducer implements ReducerInterface<Double>{
	public List<Double> reduce(List<Double> in){
		List<Double> list = new ArrayList<Double>();
		double size = (double)in.size();
		Double sum=0.0;
		for(Double val: in) {
			sum+=val;
		}
		Double mean = sum/size;
		list.add(mean);
		return list;
	}
}

class MedianReducer implements ReducerInterface<Double>{
	public List<Double> reduce(List<Double> in){
		List<Double> list = new ArrayList<Double>();
		int size = in.size();
		Collections.sort(in);
		Double median = 0.0;
		if(size%2==1) {
			median = in.get(size/2);
		}else {
			Double a = in.get(size/2);
			Double b = in.get(size/2-1);
			median=(a+b)/2;
		}
		list.add(median);
		return list;
	}
}

class MaxReducer implements ReducerInterface<Double> {
  public List<Double> reduce (List<Double> in) {
    double max = in.get(1);
    for (int i = 1; i < in.size(); i++) {
      if (in.get(i) > max) {
        max = in.get(i);
      }
    }
    List<Double> ret = new ArrayList<Double> ();
    ret.add(max);
    return ret;
  }
}

class MinReducer implements ReducerInterface<Double> {
  public List<Double> reduce (List<Double> in) {
    double min = in.get(1);
    for (int i = 1; i < in.size(); i++) {
      if (in.get(i) < min) {
        min = in.get(i);
      }
    }
    List<Double> ret = new ArrayList<Double> ();
    ret.add(min);
    return ret;
  }
}
