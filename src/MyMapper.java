public class MyMapper implements MapperInterface<String, String, Integer> {
  public String[][] map(String inputRecord) {
    String[][] ret = new String[1][2];
    ret[0][0] = inputRecord;
    ret[0][1] = "1";
    return ret;
  }
  
  public String getInputGroup(String inputRecord) {
    return "G";
  }

  public String getOutputKey(String outputRecord) {
    return outputRecord;
  }

  public Integer getOutputVal(String outputRecord) {
    return 2;
  }

  public void configure(String[] args) {}
}
