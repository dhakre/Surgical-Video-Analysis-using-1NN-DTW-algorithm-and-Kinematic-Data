import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KinematicDataList {
	public ArrayList<Double> KXValue= new ArrayList<Double>();
	public ArrayList<Double> KYValue= new ArrayList<Double>();
	
	public void addKXValue(double kxValue)
	{
		KXValue.add(kxValue);
	}
	public void addKYValue(double kyValue)
	{
		KYValue.add(kyValue);
	}
	
	public double getXvalue(int index)
	{
		double x=KXValue.get(index);
		return x;
	}
	public double getYvalue(int index)
	{
		double y=KYValue.get(index);
		return y;
	}
	
	//function to calculate kinematic data values
	public  KinematicDataList readkinematicValue(String kfileloc) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(kfileloc)); 
	    String line,a,b;
	    double element1,element2;
	    KinematicDataList kdata =new KinematicDataList();
	    while ((line = br.readLine()) != null) 
	    {
	    	line = line.trim();
	        String[] values = line.split("  +");
	       if(values.length>=3)
	       { 
	    	   a=values[0];
	    	   b=values[1];
	    	   element1=Double.parseDouble(a);
	    	   element2=Double.parseDouble(b);
	    	   kdata.addKXValue(element1);
	    	   kdata.addKYValue(element2);
	       }
	    }
	    br.close();
	    return kdata;
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
