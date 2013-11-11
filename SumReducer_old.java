/*
 * Sample Sum Reducer. Assumes that each entry belongs to a group of its own.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumReducer{
  static String UNIQUE_GROUP="G";
	public static void main(String args[]) {
	    double epsilon=0;
	    double min = 0, max=0, avg =0, b=0 ;
	    if(args.length>1){
		min =Double.parseDouble(args[0]);
		max =Double.parseDouble(args[1]);
		avg = (max-min)/2;
		b = Math.max(Math.abs(min), Math.abs(max));
	    }else{
		System.out.println("Error: Please enter the range:");
		System.exit(0);
	    }
	    if(args.length>2){
		epsilon=Double.parseDouble(args[2]);
	    }

	    String oldKey = null;
	    BufferedReader stdin = new BufferedReader(new InputStreamReader(
									    System.in));
	    double sum=0, value= 0;
	    try {
			String line = stdin.readLine();
			while (line != null) {
			    StringTokenizer st = new StringTokenizer(line, "\t");
			    // Assumes the input is well formatted
			    String newKey = st.nextToken();
			    String group = st.nextToken();
			    String values = st.nextToken();
			    //Since we know each record is in a group its own 
			    //we don't have to accumulate keys belonging to each group and enforce range
			    if(!group.equals(UNIQUE_GROUP)){
				System.out.println("Error: Privacy groups used, reducer should be changed");
				System.exit(0);
			    }
			    value = Double.parseDouble(values);
			    if(value > max || value <min)
				value = avg;
			    if (!newKey.equals(oldKey)) {
				// all points of the previous key are processed
				if (oldKey != null) {
				    double noisySum=sum+getNoise(b, epsilon);
				    System.out.println(noisySum);
				}
				oldKey = newKey;
				sum=0;
				}
			    sum+=value;
			    line = stdin.readLine();
			}
	    } catch (IOException e) {
	    }
	    // print out the last key as well
	    if (oldKey != null) {
		//addnoise for privacy
		double noisySum=sum+getNoise(b, epsilon);
		System.out.println(noisySum);
	    }
	}

    public static double getNoise(double sensitivity, double epsilon){
	//Replace with laplace noise generation
	return 0.0;
    }
}