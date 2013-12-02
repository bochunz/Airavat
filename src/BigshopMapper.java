public class MyMapper implements MapperInterface<String, String, Integer> {
  public String[][] map(String inputRecord) {
    String[][] ret = new String[1][2];
    String[] insplit = inputRecord.split("\t")
    if (insplit[0].equals("cherry")) {
      ret[0][0] = insplit[2];
      ret[0][1] = insplit[3];
      return ret;
    } else return new String[0][2];
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
