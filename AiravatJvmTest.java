/**
 * Test code for airavat mapper independence
 */

import org.jikesrvm.scheduler.DIFC;
import org.jikesrvm.scheduler.LabelSet;
import org.vmmagic.pragma.SecureMethod;
import java.io.*;
import java.util.*;
import java.util.Vector;
import java.util.Random;

public class AiravatJvmTest{

  int f;
  public static int staticVar = 1;

  public AiravatTest(){ f=100;}
  public AiravatTest(int v){ f=v;}

  public static void main(String a[]){
      mapper1();
      mapper2();
      mapper3();
  }

    //This mapper should pass
    public static void mapper1(){
	DIFC.startMapInvocation(1);
	System.out.println("******** Mapper 1 ********");
	int i=0;
	int j=i+32;
	AiravatTest a = new AiravatTest(10);
	System.out.println("f="+a.f);
	DIFC.endMapInvocation();
    }

    //Failure test
    public static void mapper2(){
	DIFC.startMapInvocation(1);
	System.out.println("******** Mapper 2 ********");
	try{
	int i=1;
	int j=i+32;
	//Use a static variable : reads are allowed
	j=staticVar+5;
	System.out.println("j="+j);
	//Use a static variable : error->write not allowed
	staticVar = 12;
	System.out.println("static="+staticVar);
	}catch(Exception e){System.out.println(e);}
	DIFC.endMapInvocation();
    }

   //This mapper should pass
    public static void mapper3(){
	System.out.println("******** Mapper 3 ********");

	DIFC.startMapInvocation(DIFC.AIRAVAT_CONFIG);
	AiravatTest d = new AiravatTest(100);
	DIFC.endMapInvocation();

	DIFC.startMapInvocation(1);
	AiravatTest a = new AiravatTest(10);
	DIFC.endMapInvocation();

	System.out.println("a's inv="+DIFC.getObjectInvocationNumber(a));

	DIFC.startMapInvocation(2);	
	try{
	int i=0;
	int j=i+32;
	//COnfig info can be read anytime
	System.out.println("Config d's inv="+DIFC.getObjectInvocationNumber(d)+" : val="+d.f);
	//should not be allowed to use a already initialized object
	AiravatTest b = new AiravatTest(a.f+10);
	System.out.println("f="+b.f);
	System.out.println("b's inv="+DIFC.getObjectInvocationNumber(b));

	}catch(Exception e){System.out.println(e);}
	DIFC.endMapInvocation();

    }
    
}
