import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KinematicDataList {
	private ArrayList<Double> KXValue= new ArrayList<Double>();
	private ArrayList<Double> KYValue= new ArrayList<Double>();
	
	public void addKXValue(double kxValue)
	{
		KXValue.add(kxValue);
	}
	public void addKYValue(double kyValue)
	{
		KYValue.add(kyValue);
	}
	
	//function to calculate kinematic data values
	public ArrayList<Double> readkinematicValue(String kfileloc) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(kfileloc)); 
	    String line,a;
	    double element;
	    ArrayList <Double> arr2X =new ArrayList<Double>();
	    while ((line = br.readLine()) != null) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[0];
	    	   element=Double.parseDouble(a);
	    	   arr2X.add(element);
	       }
	    }
	    br.close();
	    return arr2X;
	}
	public void print(KinematicDataList k)
	{   
		ArrayList <Double> a=new ArrayList<Double>();
		//ArrayList <Double> b=new ArrayList<Double>();
		a=k.KXValue;
		for(double d :a)
		{
			System.out.println("data X="+d);
		}
		a=k.KYValue;
		for(double d :a)
		{
			System.out.println("data Y="+d);
		}
		
	}
	
	//main method
	/*public static void main(String args[]) throws IOException
	{
		String kfileloc="C:/Users/user/Documents/Intership/Knot_Tying/kinematics/AllGestures/Knot_Tying_B001.txt";;
		ArrayList<Double> kxvalue= new ArrayList<Double>();
		KinematicDataList kobj= new KinematicDataList();
		kxvalue=kobj.readkinematicValue(kfileloc);
		for(double d :kxvalue)
		{
			System.out.println("data="+d);
		}
		int size = kxvalue.size()-1;
		System.out.println("size="+size);
	}*/

}
