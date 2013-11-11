/*
 * Mapper Interface
 *
 * Feb. 2009
 */

public interface MapperInterface<GT, KT, VT>
{
    /*returns pairs of key value*/
    String[][] map(String inputRecord);

    GT getInputGroup(String inputRecord);
    KT getOutputKey(String outputRecord);
    VT getOutputVal(String outputRecord);
    void configure(String[] args);
}
