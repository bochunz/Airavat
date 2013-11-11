/*
 * The Wrapper class for untrusted mappers
 * Usage: java MapperWrapper [mapper class] 
 */

import java.io.IOException;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import org.jikesrvm.scheduler.DIFC;
import org.jikesrvm.scheduler.LabelSet;
import org.vmmagic.pragma.SecureMethod;
public class MapperWrapper 
{    
    static boolean JIKES = false;

	public static void main(String args[])
	{

		BufferedReader stdin;
		String line;
		MapperInterface<String, String, Integer> realMapper = null;
		String mapperClassName = "Mapper";
		if(args.length>0)
			mapperClassName = args[0];	
		try {
			realMapper = (MapperInterface<String, String, Integer>) Class.forName(mapperClassName).newInstance();
			if(JIKES) DIFC.startMapInvocation(DIFC.AIRAVAT_CONFIG);
			realMapper.configure(null);
			if(JIKES) DIFC.endMapInvocation();
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		stdin = new BufferedReader(new InputStreamReader(System.in));

		int iterationNumber = 0;
		try
		{
			line = stdin.readLine();
			while (line != null)
			{
				String group;
				boolean stateFull = false; /* todo, communicate value from jvm, */
				
				/* setIterationNumber */
				iterationNumber++;
				group = realMapper.getInputGroup(line);

				/* Begin invocation region here */
				if(JIKES) DIFC.startMapInvocation(iterationNumber);
				String[][] outputRecords = realMapper.map(line);
				/* End invocation region here */
				if(JIKES) DIFC.endMapInvocation();
				
				if(outputRecords!=null){	
				    for (int i = 0 ; i < outputRecords.length ; i++)
					{
					    String outKey=(String)realMapper.getOutputKey(outputRecords[i][0]);
					    Integer outVal=(Integer)realMapper.getOutputVal(outputRecords[i][1]);
					    // Attach the group-id with the output.
					    // The reducer will enforce the range based on group-id.
					    System.out.println(outputRecords[i][0]+"\t"+ group + "\t" +outputRecords[i][1]);
					}
				}
				line = stdin.readLine();
			}
		} 
		catch (IOException e) 
		{
			System.out.println(e.toString());
			return;
		}
	}  
}
