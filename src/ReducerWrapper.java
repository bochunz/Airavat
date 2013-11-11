/*
 * Reducer Wrapper
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Vector;
import java.lang.InstantiationException;

public class ReducerWrapper {

  static String UNIQUE_GROUP="G";

  public static void main(String args[]) {
    double epsilon = 0;
    double min = 0, max = 0, avg = 0, b = 0;
    String reducerClassName = "";
    if (args.length == 4) {
      reducerClassName = args[0];
      min = Double.parseDouble(args[1]);
      max = Double.parseDouble(args[2]);
      avg = (max-min)/2;
      b = Math.max(Math.abs(min), Math.abs(max));
      epsilon = Double.parseDouble(args[3]);
    } else {
      System.out.println("Error: Usage: ReducerWrapper <ReducerClassName> <min> <max> <epsilon>");
      System.exit(0);
    }

    String oldKey = null;
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    double value = 0;
    List targetArray = new Vector<Double>();
    try {
      ReducerInterface realReducer = (ReducerInterface) Class.forName(reducerClassName).newInstance();
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
        if (value > max || value < min)
          value = avg;
        if (!newKey.equals(oldKey)) {
          // all points of the previous key are processed
          if (oldKey != null) {
            printResult(realReducer, targetArray, b, epsilon);
          }
          oldKey = newKey;
          // sum = 0;
          targetArray.clear();
        }
        // sum += value;
        targetArray.add(targetArray.size(), value);
        line = stdin.readLine();
      }
      // print out the last key as well
      if (oldKey != null) {
        //addnoise for privacy
        printResult(realReducer, targetArray, b, epsilon);
      }
    } catch (IOException e) {
    } catch (Exception e) {
    }
    
  }

  static void printResult(ReducerInterface realReducer, List<Double> targetArray, double b, double epsilon) {
    List<Double> result = realReducer.reduce(targetArray);
    for (int i = 0; i < result.size(); i++) {
      result.set(i, result.get(i) + getNoise(b, epsilon));
    }
    if (result.size() == 1) {
      System.out.println(result.get(0));
    } else {
      System.out.println(result.toString());
    }
  }

  public static double getNoise(double sensitivity, double epsilon){
    //Replace with laplace noise generation
    return 0.0;
  }
}
